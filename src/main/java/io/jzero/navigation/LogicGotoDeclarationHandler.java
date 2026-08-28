package io.jzero.navigation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import io.jzero.icon.ApiIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Logic → api/proto gutter navigation.
 * Prefer ~/.jzero/desc-metadata; fall back to @handler name matching for plain go-zero projects.
 */
public class LogicGotoDeclarationHandler implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null || !containingFile.getName().endsWith(".go")) {
            return null;
        }

        VirtualFile virtualFile = containingFile.getVirtualFile();
        if (virtualFile == null) {
            return null;
        }

        String filePath = virtualFile.getPath();
        if (!filePath.contains("/internal/logic/") && !filePath.contains("\\internal\\logic\\")) {
            return null;
        }

        if (!isNewFunctionNameLeaf(element)) {
            return null;
        }

        String funcName = element.getText();
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to API: " + funcName,
                (e, elt) -> navigateToDescFile(elt, funcName),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to API/Proto"
        );
    }

    private void navigateToDescFile(@NotNull PsiElement sourceElement, @NotNull String funcName) {
        PsiFile containingFile = sourceElement.getContainingFile();
        if (containingFile == null || containingFile.getVirtualFile() == null) {
            return;
        }
        String filePath = containingFile.getVirtualFile().getPath();

        List<LogicMetadata> metadataList = findAllMetadataForLogicFile(filePath);
        if (!metadataList.isEmpty()) {
            LogicMetadata metadata = metadataList.get(0);
            VirtualFile descFile = LocalFileSystem.getInstance().findFileByPath(metadata.descPath);
            if (descFile != null && descFile.exists()) {
                openFileAndNavigate(sourceElement.getProject(), descFile, metadata.descLine);
                return;
            }
        }

        ApiHit hit = findApiByHandlerConvention(sourceElement.getProject(), filePath, funcName);
        if (hit != null) {
            openFileAndNavigate(sourceElement.getProject(), hit.file, hit.line);
        }
    }

    /**
     * go-zero convention: New{Name}Logic → @handler {Name}Handler / {Name}
     * Prefer service-route .api files over type-only .api files.
     */
    @Nullable
    private ApiHit findApiByHandlerConvention(@NotNull Project project,
                                              @NotNull String logicFilePath,
                                              @NotNull String funcName) {
        String handlerBase = stripNewLogic(funcName);
        if (handlerBase.isEmpty()) {
            return null;
        }

        String[] needles = {
                "@handler " + handlerBase + "Handler",
                "@handler " + handlerBase,
                "handler: " + handlerBase + "Handler",
                "handler: " + handlerBase
        };

        String projectRoot = extractBasePath(logicFilePath);
        Collection<VirtualFile> apiFiles = FilenameIndex.getAllFilesByExt(
                project, "api", GlobalSearchScope.projectScope(project));

        ApiHit routeHit = null;
        ApiHit anyHit = null;
        for (VirtualFile apiFile : apiFiles) {
            if (projectRoot != null && !apiFile.getPath().replace('\\', '/').startsWith(projectRoot.replace('\\', '/'))) {
                continue;
            }
            String text;
            try {
                text = new String(apiFile.contentsToByteArray(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                continue;
            }
            int idx = indexOfHandler(text, needles);
            if (idx < 0) {
                continue;
            }
            int line = lineNumberAt(text, idx);
            ApiHit hit = new ApiHit(apiFile, line);
            if (isServiceRouteApi(text)) {
                if (routeHit == null) {
                    routeHit = hit;
                }
            } else if (anyHit == null) {
                anyHit = hit;
            }
        }
        return routeHit != null ? routeHit : anyHit;
    }

    private static int indexOfHandler(@NotNull String text, @NotNull String[] needles) {
        for (String needle : needles) {
            int from = 0;
            while (from < text.length()) {
                int idx = text.indexOf(needle, from);
                if (idx < 0) {
                    break;
                }
                int end = idx + needle.length();
                // avoid prefix match: @handler Foo matching @handler FooBar
                if (end >= text.length() || !isIdentChar(text.charAt(end))) {
                    return idx;
                }
                from = end;
            }
        }
        return -1;
    }

    private static boolean isIdentChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private static int lineNumberAt(@NotNull String text, int idx) {
        int line = 1;
        for (int i = 0; i < idx; i++) {
            if (text.charAt(i) == '\n') {
                line++;
            }
        }
        return line;
    }

    /** go-zero .api that declares HTTP services (not type-only import files). */
    private static boolean isServiceRouteApi(@NotNull String text) {
        return text.contains("service ") && text.contains("@handler");
    }

    /** New{Name}Logic / New{Name} → {Name} */
    @NotNull
    private static String stripNewLogic(@NotNull String funcName) {
        String name = funcName;
        if (name.startsWith("New")) {
            name = name.substring(3);
        }
        if (name.endsWith("Logic")) {
            name = name.substring(0, name.length() - "Logic".length());
        }
        return name;
    }

    @NotNull
    private List<LogicMetadata> findAllMetadataForLogicFile(@NotNull String logicFilePath) {
        List<LogicMetadata> results = new ArrayList<>();
        try {
            String basePath = extractBasePath(logicFilePath);
            if (basePath == null) {
                return results;
            }
            String metadataPath = System.getProperty("user.home")
                    + "/.jzero/desc-metadata" + basePath + "/metadata.json";
            File metadataFile = new File(metadataPath);
            if (!metadataFile.exists()) {
                return results;
            }
            String content = new String(Files.readAllBytes(Paths.get(metadataPath)), StandardCharsets.UTF_8);
            return parseAllMetadataForFile(content, logicFilePath);
        } catch (IOException e) {
            return results;
        }
    }

    @NotNull
    private List<LogicMetadata> parseAllMetadataForFile(@NotNull String jsonContent, @NotNull String logicFilePath) {
        List<LogicMetadata> results = new ArrayList<>();
        try {
            JsonObject root = new Gson().fromJson(jsonContent, JsonObject.class);
            if (root == null) {
                return results;
            }
            String normalizedTargetPath = logicFilePath.replace("\\", "/");
            collectMetadataEntries(root, "api", "routes", normalizedTargetPath, results);
            if (results.isEmpty()) {
                collectMetadataEntries(root, "proto", "rpcs", normalizedTargetPath, results);
            }
        } catch (Exception ignored) {
            // ignore malformed metadata
        }
        return results;
    }

    private void collectMetadataEntries(@NotNull JsonObject root,
                                        @NotNull String section,
                                        @NotNull String arrayKey,
                                        @NotNull String normalizedTargetPath,
                                        @NotNull List<LogicMetadata> results) {
        if (!root.has(section)) {
            return;
        }
        JsonObject obj = root.getAsJsonObject(section);
        if (!obj.has(arrayKey)) {
            return;
        }
        JsonArray entries = obj.getAsJsonArray(arrayKey);
        for (int i = 0; i < entries.size(); i++) {
            JsonObject entry = entries.get(i).getAsJsonObject();
            if (!entry.has("logic") || !entry.has("desc")) {
                continue;
            }
            String entryLogicPath = entry.get("logic").getAsString().replace("\\", "/");
            if (!entryLogicPath.equals(normalizedTargetPath)) {
                continue;
            }
            LogicMetadata metadata = new LogicMetadata();
            metadata.descPath = entry.get("desc").getAsString();
            metadata.descLine = entry.has("desc-line") ? entry.get("desc-line").getAsInt() : 0;
            results.add(metadata);
        }
    }

    @Nullable
    private String extractBasePath(@NotNull String logicFilePath) {
        int logicIndex = logicFilePath.indexOf("/internal/logic/");
        if (logicIndex == -1) {
            logicIndex = logicFilePath.indexOf("\\internal\\logic\\");
            if (logicIndex == -1) {
                return null;
            }
        }
        return logicFilePath.substring(0, logicIndex);
    }

    private void openFileAndNavigate(@NotNull Project project, @NotNull VirtualFile file, int lineNumber) {
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                project,
                file,
                Math.max(lineNumber - 1, 0),
                0
        ).navigate(true);
    }

    private boolean hasMetadataForLogicFile(@NotNull String logicFilePath) {
        return !findAllMetadataForLogicFile(logicFilePath).isEmpty();
    }

    /**
     * Leaf identifier of {@code func NewXxx(...)} — required by LineMarkerInfo.
     */
    private boolean isNewFunctionNameLeaf(@NotNull PsiElement element) {
        if (element.getChildren().length > 0) {
            return false;
        }
        String elementText = element.getText();
        if (elementText == null || !elementText.matches("New[A-Za-z0-9_]+")) {
            return false;
        }
        PsiElement prev = element.getPrevSibling();
        while (prev != null && (prev.getText() == null || prev.getText().trim().isEmpty())) {
            prev = prev.getPrevSibling();
        }
        return prev != null && "func".equals(prev.getText().trim());
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull java.util.Collection<? super LineMarkerInfo<?>> result) {
        // unused
    }

    private static class LogicMetadata {
        String descPath;
        int descLine;
    }

    private static class ApiHit {
        final VirtualFile file;
        final int line;

        ApiHit(VirtualFile file, int line) {
            this.file = file;
            this.line = line;
        }
    }
}
