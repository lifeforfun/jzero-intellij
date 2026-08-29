package io.jzero.navigation;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import io.jzero.psi.nodes.ServiceNode;
import io.jzero.util.JzeroConfigReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Resolve handler/logic Go file from @handler name in .api.
 * Supports go-zero (internal/logic) and jzero/gin layouts (service/, handler/).
 */
public final class HandlerLogicResolver {

    private static final String[] NAME_STYLES = {"go_zero", "gozero", "goZero", "go-zero"};
    private static final Key<Long> CACHE_STAMP = Key.create("jzero.handler.cache.stamp");
    private static final Key<java.util.Map<String, PsiFile>> CACHE = Key.create("jzero.handler.cache");

    private HandlerLogicResolver() {
    }

    @Nullable
    public static PsiFile findLogicFile(@NotNull PsiElement sourceElement, @NotNull String rawHandlerName) {
        String handlerBase = stripHandlerSuffix(rawHandlerName);
        if (handlerBase.isEmpty()) {
            return null;
        }

        PsiFile apiFile = sourceElement.getContainingFile();
        if (apiFile == null) {
            return null;
        }
        long stamp = apiFile.getModificationStamp();
        Long cachedStamp = apiFile.getUserData(CACHE_STAMP);
        java.util.Map<String, PsiFile> cache = apiFile.getUserData(CACHE);
        if (cache != null && cachedStamp != null && cachedStamp == stamp && cache.containsKey(handlerBase)) {
            return cache.get(handlerBase);
        }
        if (cache == null) {
            cache = new java.util.HashMap<>();
        }

        PsiFile resolved = resolveLogicFile(sourceElement, handlerBase);
        // Do not cache misses — avoids sticky null after codegen / plugin update
        if (resolved != null) {
            cache.put(handlerBase, resolved);
            apiFile.putUserData(CACHE, cache);
            apiFile.putUserData(CACHE_STAMP, stamp);
        }
        return resolved;
    }

    @Nullable
    private static PsiFile resolveLogicFile(@NotNull PsiElement sourceElement, @NotNull String handlerBase) {
        VirtualFile sourceFile = sourceElement.getContainingFile() != null
                ? sourceElement.getContainingFile().getVirtualFile()
                : null;
        if (sourceFile == null) {
            return null;
        }

        String projectRoot = resolveProjectRoot(sourceFile);
        if (projectRoot == null) {
            return null;
        }

        Project project = sourceElement.getProject();
        String namingFormat = JzeroConfigReader.getNamingStyle(project, sourceElement.getContainingFile());
        String group = findGroupName(sourceElement);

        LocalFileSystem fs = LocalFileSystem.getInstance();
        for (String formatted : formattedNames(namingFormat, handlerBase)) {
            for (String rel : buildRelativeCandidates(group, formatted)) {
                VirtualFile vf = fs.findFileByPath(projectRoot + "/" + rel);
                if (vf == null) {
                    continue;
                }
                PsiFile psi = PsiManager.getInstance(project).findFile(vf);
                if (psi != null && containsHandlerSymbol(psi.getText(), handlerBase)) {
                    return psi;
                }
            }
        }

        PsiFile middleware = findMiddlewareFile(project, projectRoot, namingFormat, handlerBase);
        if (middleware != null) {
            return middleware;
        }

        // group: share_title → handler/share_title_handler.go (multi-handler file)
        PsiFile byGroup = findByGroupFile(project, projectRoot, group, handlerBase);
        if (byGroup != null) {
            return byGroup;
        }

        PsiFile byContent = findByContent(project, projectRoot, handlerBase);
        if (byContent != null) {
            return byContent;
        }

        return findByFilenameIndex(project, projectRoot, handlerBase);
    }

    /**
     * middleware: GatewayMiddleware → middleware/gateway_middleware.go
     */
    @Nullable
    public static PsiFile findMiddlewareFile(@NotNull PsiElement sourceElement, @NotNull String rawName) {
        PsiFile apiFile = sourceElement.getContainingFile();
        if (apiFile == null || apiFile.getVirtualFile() == null) {
            return null;
        }
        String projectRoot = resolveProjectRoot(apiFile.getVirtualFile());
        if (projectRoot == null) {
            return null;
        }
        String namingFormat = JzeroConfigReader.getNamingStyle(sourceElement.getProject(), apiFile);
        return findMiddlewareFile(sourceElement.getProject(), projectRoot, namingFormat, rawName.trim());
    }

    @Nullable
    private static PsiFile findMiddlewareFile(@NotNull Project project,
                                              @NotNull String projectRoot,
                                              @NotNull String namingFormat,
                                              @NotNull String name) {
        LocalFileSystem fs = LocalFileSystem.getInstance();
        for (String formatted : formattedNames(namingFormat, name)) {
            String[] rels = {
                    "middleware/" + formatted + ".go",
                    "middleware/" + formatted + "_middleware.go",
                    "internal/middleware/" + formatted + ".go",
                    "internal/middleware/" + formatted + "middleware.go",
            };
            for (String rel : rels) {
                VirtualFile vf = fs.findFileByPath(projectRoot + "/" + rel);
                if (vf == null) {
                    continue;
                }
                PsiFile psi = PsiManager.getInstance(project).findFile(vf);
                if (psi != null && containsHandlerSymbol(psi.getText(), name)) {
                    return psi;
                }
            }
        }
        // content scan middleware/
        VirtualFile folder = fs.findFileByPath(projectRoot + "/middleware");
        if (folder != null && folder.isDirectory()) {
            return scanDirForHandler(project, folder, name, 0);
        }
        return null;
    }

    /** True when leaf is the value of {@code middleware: Xxx} in @server. */
    public static boolean isMiddlewareValue(@NotNull PsiElement element) {
        if (element.getNode() == null
                || element.getNode().getElementType() != io.jzero.parser.ApiParserDefinition.IDENTIFIER) {
            return false;
        }
        PsiElement colon = com.intellij.psi.util.PsiTreeUtil.prevVisibleLeaf(element);
        if (colon == null || !":".equals(colon.getText())) {
            return false;
        }
        PsiElement key = com.intellij.psi.util.PsiTreeUtil.prevVisibleLeaf(colon);
        return key != null && "middleware".equals(key.getText());
    }

    /** Prefer group-named files when they contain the handler symbol. */
    @Nullable
    private static PsiFile findByGroupFile(@NotNull Project project,
                                           @NotNull String projectRoot,
                                           @Nullable String group,
                                           @NotNull String handlerBase) {
        if (group == null || group.isEmpty()) {
            return null;
        }
        String g = group.replace('\\', '/');
        while (g.startsWith("/")) {
            g = g.substring(1);
        }
        String[] rels = {
                "handler/" + g + "_handler.go",
                "handler/" + g + ".go",
                "service/" + g + "_logic.go",
                "service/" + g + ".go",
                "internal/handler/" + g + "handler.go",
                "internal/handler/" + g + ".go",
                "internal/logic/" + g + "logic.go",
                "internal/logic/" + g + ".go",
        };
        LocalFileSystem fs = LocalFileSystem.getInstance();
        for (String rel : rels) {
            VirtualFile vf = fs.findFileByPath(projectRoot + "/" + rel);
            if (vf == null) {
                continue;
            }
            PsiFile psi = PsiManager.getInstance(project).findFile(vf);
            if (psi != null && containsHandlerSymbol(psi.getText(), handlerBase)) {
                return psi;
            }
        }
        return null;
    }

    /** Scan handler/service dirs for func UpdateShareTitleHandler / NewXxxLogic. */
    @Nullable
    private static PsiFile findByContent(@NotNull Project project,
                                         @NotNull String projectRoot,
                                         @NotNull String handlerBase) {
        LocalFileSystem fs = LocalFileSystem.getInstance();
        String[] dirs = {"handler", "service", "middleware", "internal/handler", "internal/logic", "internal/middleware"};
        for (String dir : dirs) {
            VirtualFile folder = fs.findFileByPath(projectRoot + "/" + dir);
            if (folder == null || !folder.isDirectory()) {
                continue;
            }
            PsiFile hit = scanDirForHandler(project, folder, handlerBase, 0);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    @Nullable
    private static PsiFile scanDirForHandler(@NotNull Project project,
                                             @NotNull VirtualFile dir,
                                             @NotNull String handlerBase,
                                             int depth) {
        if (depth > 3) {
            return null;
        }
        for (VirtualFile child : dir.getChildren()) {
            if (child.isDirectory()) {
                if ("runtime".equals(child.getName()) || child.getName().startsWith(".")) {
                    continue;
                }
                PsiFile nested = scanDirForHandler(project, child, handlerBase, depth + 1);
                if (nested != null) {
                    return nested;
                }
                continue;
            }
            if (!"go".equals(child.getExtension()) || child.getName().endsWith("_test.go")) {
                continue;
            }
            PsiFile psi = PsiManager.getInstance(project).findFile(child);
            if (psi != null && containsHandlerSymbol(psi.getText(), handlerBase)) {
                return psi;
            }
        }
        return null;
    }

    private static boolean containsHandlerSymbol(@Nullable String content, @NotNull String handlerBase) {
        if (content == null || content.isEmpty()) {
            return false;
        }
        String handlerFn = handlerBase.endsWith("Handler") ? handlerBase : handlerBase + "Handler";
        return content.contains("func " + handlerFn + "(")
                || content.contains("func " + handlerBase + "(")
                || content.contains("func (s *Service) " + handlerBase + "(")
                || content.contains("func New" + handlerBase + "Logic(")
                || content.contains("func New" + handlerBase + "(")
                || content.contains("func (l *" + handlerBase + "Logic) ")
                || content.contains("func (l *" + handlerBase + ") ");
    }

    /** Avoid {@code PsiElement.getText()} on large service blocks. */
    @Nullable
    private static String findGroupName(@NotNull PsiElement element) {
        PsiElement service = element;
        while (service != null && !(service instanceof ServiceNode)) {
            service = service.getParent();
        }
        if (service == null) {
            return null;
        }
        PsiElement scan = service.getPrevSibling();
        while (scan != null) {
            String fromAnnot = groupFromAtServer(scan);
            if (fromAnnot != null) {
                return fromAnnot;
            }
            scan = scan.getPrevSibling();
        }
        return groupFromAtServer(service.getParent());
    }

    @Nullable
    private static String groupFromAtServer(@Nullable PsiElement block) {
        if (block == null) {
            return null;
        }
        String text = block.getText();
        if (text == null || !text.contains("@server") || !text.contains("group:")) {
            return null;
        }
        int start = text.indexOf("@server");
        int end = text.indexOf("service", start);
        if (end < 0) {
            end = Math.min(text.length(), start + 512);
        }
        return parseGroup(text.substring(start, end));
    }

    public static int findLogicTargetOffset(@NotNull PsiFile goFile, @NotNull String rawHandlerName) {
        String handlerBase = stripHandlerSuffix(rawHandlerName);
        String handlerFn = handlerBase.endsWith("Handler") ? handlerBase : handlerBase + "Handler";
        String content = goFile.getText();
        String[] patterns = {
                "func (s *Service) " + handlerBase + "(",
                "func (l *" + handlerBase + "Logic) " + handlerBase + "(",
                "func (l *" + handlerBase + ") " + handlerBase + "(",
                "func New" + handlerBase + "Logic(",
                "func New" + handlerBase + "(",
                "func " + handlerFn + "(",
                "func " + handlerBase + "(",
        };
        for (String pattern : patterns) {
            int idx = content.indexOf(pattern);
            if (idx >= 0) {
                return idx;
            }
        }
        return 0;
    }

    @NotNull
    public static String stripHandlerSuffix(@NotNull String handlerName) {
        String name = handlerName.trim();
        if (name.endsWith("Handler")) {
            return name.substring(0, name.length() - "Handler".length());
        }
        return name;
    }

    @NotNull
    private static List<String> formattedNames(@NotNull String primaryStyle, @NotNull String handlerBase) {
        Set<String> names = new LinkedHashSet<>();
        names.add(JzeroConfigReader.formatFileName(primaryStyle, handlerBase));
        for (String style : NAME_STYLES) {
            names.add(JzeroConfigReader.formatFileName(style, handlerBase));
        }
        return new ArrayList<>(names);
    }

    @Nullable
    private static PsiFile findByFilenameIndex(@NotNull Project project,
                                               @NotNull String projectRoot,
                                               @NotNull String handlerBase) {
        String root = projectRoot.replace('\\', '/');
        Collection<VirtualFile> goFiles = FilenameIndex.getAllFilesByExt(
                project, "go", GlobalSearchScope.projectScope(project));

        String[] suffixes = {"_logic.go", "_handler.go", "logic.go", "handler.go", "_middleware.go", ".go"};
        for (String style : NAME_STYLES) {
            String formatted = JzeroConfigReader.formatFileName(style, handlerBase);
            for (VirtualFile vf : goFiles) {
                String path = vf.getPath().replace('\\', '/');
                if (!path.startsWith(root)) {
                    continue;
                }
                String name = vf.getName();
                boolean nameOk = false;
                for (String suffix : suffixes) {
                    if (name.equals(formatted + suffix)
                            || name.equals(formatted.replace("_", "") + suffix)
                            || (suffix.equals(".go") && name.equals(formatted + ".go"))) {
                        nameOk = true;
                        break;
                    }
                }
                if (!nameOk) {
                    continue;
                }
                if (!(path.contains("/service/") || path.contains("/handler/")
                        || path.contains("/middleware/")
                        || path.contains("/internal/logic/") || path.contains("/internal/handler/")
                        || path.contains("/internal/middleware/"))) {
                    continue;
                }
                PsiFile psi = PsiManager.getInstance(project).findFile(vf);
                if (psi != null && containsHandlerSymbol(psi.getText(), handlerBase)) {
                    return psi;
                }
            }
        }
        return null;
    }

    @Nullable
    private static String parseGroup(@NotNull String annotationText) {
        for (String line : annotationText.split("\n")) {
            line = line.trim();
            if (!line.contains("group:")) {
                continue;
            }
            String value = line.substring(line.indexOf("group:") + 6).trim();
            if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                value = value.substring(1, value.length() - 1);
            }
            int space = value.indexOf(' ');
            if (space > 0) {
                value = value.substring(0, space);
            }
            if (!value.isEmpty()) {
                return value;
            }
        }
        return null;
    }

    @Nullable
    public static String resolveProjectRoot(@NotNull VirtualFile sourceFile) {
        String path = sourceFile.getPath().replace('\\', '/');
        String[] markers = {"/desc/api/", "/desc/proto/", "/api/", "/proto/"};
        for (String marker : markers) {
            int idx = path.indexOf(marker);
            if (idx >= 0) {
                return path.substring(0, idx);
            }
        }
        if (path.endsWith(".api")) {
            VirtualFile parent = sourceFile.getParent();
            if (parent != null) {
                return parent.getPath();
            }
        }
        VirtualFile parent = sourceFile.getParent();
        return parent != null ? parent.getPath() : null;
    }

    @NotNull
    private static String[] buildRelativeCandidates(@Nullable String group, @NotNull String formatted) {
        List<String> paths = new ArrayList<>();
        // jzero / gin flat layout
        paths.add("service/" + formatted + "_logic.go");
        paths.add("handler/" + formatted + "_handler.go");
        paths.add("service/" + formatted + ".go");
        paths.add("handler/" + formatted + ".go");

        String groupDir = group != null && !group.isEmpty() ? group.toLowerCase() + "/" : "";
        // go-zero internal layout
        paths.add("internal/logic/" + groupDir + formatted + "logic.go");
        paths.add("internal/logic/" + groupDir + formatted + ".go");
        paths.add("internal/handler/" + groupDir + formatted + "handler.go");
        paths.add("internal/handler/" + groupDir + formatted + ".go");
        if (group != null && !group.isEmpty()) {
            paths.add("internal/logic/" + formatted + "logic.go");
            paths.add("internal/handler/" + formatted + "handler.go");
        }
        return paths.toArray(new String[0]);
    }
}
