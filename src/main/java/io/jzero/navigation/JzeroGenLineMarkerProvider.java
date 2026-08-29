package io.jzero.navigation;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.ExecutionEnvironmentBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import io.jzero.runconfig.JzeroGenConfigurationFactory;
import io.jzero.runconfig.JzeroGenConfigurationType;
import io.jzero.runconfig.JzeroGenRunConfiguration;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JzeroGenLineMarkerProvider implements LineMarkerProvider {

    private static final Key<Long> FIRST_OFF = Key.create("jzero.gen.firstOff");
    private static final Key<Long> FIRST_STAMP = Key.create("jzero.gen.firstStamp");
    private static final AtomicInteger SAMPLES = new AtomicInteger();

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        int n = SAMPLES.incrementAndGet();
        if (n % 1000 == 0) {
            ApiPerf.inc("lineMarker.jzeroGen.calls");
        }
        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null) {
            return null;
        }
        VirtualFile virtualFile = containingFile.getVirtualFile();
        if (virtualFile == null) {
            return null;
        }

        String fileName = virtualFile.getName();
        if (!".jzero.yaml".equals(fileName)
                && !fileName.endsWith(".api")
                && !fileName.endsWith(".proto")
                && !fileName.endsWith(".sql")) {
            return null;
        }

        Project project = containingFile.getProject();
        String filePath = virtualFile.getPath();

        if (".jzero.yaml".equals(fileName)) {
            return createYamlLineMarker(element, project, virtualFile);
        }

        if (fileName.endsWith(".api")) {
            if (!filePath.contains("/desc/") || !atFirstContent(element, containingFile)) {
                return null;
            }
            return createDescGenMarker(element, project);
        }

        if (fileName.endsWith(".proto")) {
            if (filePath.contains("/desc/proto/") && !filePath.contains("/desc/proto/third_party/")
                    && atFirstContent(element, containingFile)) {
                return createDescGenMarker(element, project);
            }
        }

        if (fileName.endsWith(".sql") && filePath.contains("/desc/sql/") && atFirstContent(element, containingFile)) {
            return createDescGenMarker(element, project);
        }

        return null;
    }

    @Nullable
    private LineMarkerInfo<?> createYamlLineMarker(@NotNull PsiElement element,
                                                   @NotNull Project project,
                                                   @NotNull VirtualFile virtualFile) {
        String elementText = element.getText();
        if (elementText == null) {
            return null;
        }
        String trimmed = elementText.trim();
        String command;
        String tooltip;
        if ("gen".equals(trimmed)) {
            command = "jzero gen";
            tooltip = "Execute jzero gen";
        } else if ("zrpcclient".equals(trimmed)) {
            command = "jzero gen zrpcclient";
            tooltip = "Execute jzero gen zrpcclient";
        } else {
            return null;
        }
        String finalCommand = command;
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                AllIcons.Actions.Execute,
                psiElement -> tooltip,
                (MouseEvent e, PsiElement elt) -> executeJzeroGenCommand(project, virtualFile, finalCommand),
                GutterIconRenderer.Alignment.LEFT
        );
    }

    @NotNull
    private LineMarkerInfo<?> createDescGenMarker(@NotNull PsiElement element, @NotNull Project project) {
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                AllIcons.Actions.Execute,
                psiElement -> "Execute jzero gen --desc",
                (MouseEvent e, PsiElement elt) -> executeApiGenCommand(project, elt),
                GutterIconRenderer.Alignment.LEFT
        );
    }

    private void executeApiGenCommand(@NotNull Project project, @NotNull PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null) {
            return;
        }
        VirtualFile virtualFile = containingFile.getVirtualFile();
        if (virtualFile == null) {
            return;
        }
        virtualFile.refresh(false, false);
        String apiFilePath = virtualFile.getPath();
        String workingDir = findDescBasedWorkingDirectory(apiFilePath);
        String relativePath = calculateRelativePath(workingDir, apiFilePath);
        executeJzeroGenCommandWithWorkingDir(project, virtualFile,
                "jzero gen --desc " + relativePath, workingDir != null ? workingDir : "");
    }

    private void executeJzeroGenCommand(@NotNull Project project,
                                        @NotNull VirtualFile triggerFile,
                                        @NotNull String command) {
        String workingDir = findJzeroConfigDirectory(triggerFile);
        if (workingDir == null) {
            workingDir = triggerFile.getParent() != null ? triggerFile.getParent().getPath() : "";
        }
        executeJzeroGenCommandWithWorkingDir(project, triggerFile, command, workingDir);
    }

    private void executeJzeroGenCommandWithWorkingDir(@NotNull Project project,
                                                      @NotNull VirtualFile triggerFile,
                                                      @NotNull String command,
                                                      @NotNull String workingDir) {
        RunManager runManager = RunManager.getInstance(project);
        JzeroGenConfigurationType configurationType = new JzeroGenConfigurationType();
        JzeroGenConfigurationFactory factory =
                (JzeroGenConfigurationFactory) configurationType.getConfigurationFactories()[0];
        JzeroGenRunConfiguration runConfiguration =
                new JzeroGenRunConfiguration(project, factory, "jzero gen");
        runConfiguration.setCommand(command);
        runConfiguration.setWorkingDirectory(workingDir);
        RunnerAndConfigurationSettings settings = runManager.createConfiguration(runConfiguration, factory);
        settings.setTemporary(true);
        try {
            ExecutionEnvironmentBuilder builder = ExecutionEnvironmentBuilder
                    .create(DefaultRunExecutor.getRunExecutorInstance(), settings);
            ExecutionManager.getInstance(project).restartRunProfile(builder.build());
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to execute jzero gen command", e);
        }
    }

    @Nullable
    private String findDescBasedWorkingDirectory(@NotNull String filePath) {
        int descIndex = filePath.indexOf("/desc/");
        if (descIndex == -1) {
            return null;
        }
        return filePath.substring(0, descIndex);
    }

    @Nullable
    private String findJzeroConfigDirectory(@NotNull VirtualFile startFile) {
        VirtualFile currentDir = startFile.getParent();
        int depth = 0;
        while (currentDir != null && depth < 10) {
            VirtualFile configFile = currentDir.findChild(".jzero.yaml");
            if (configFile != null && configFile.exists()) {
                return currentDir.getPath();
            }
            currentDir = currentDir.getParent();
            depth++;
        }
        return null;
    }

    @NotNull
    private String calculateRelativePath(@Nullable String basePath, @NotNull String fullPath) {
        if (basePath == null || basePath.isEmpty()) {
            return fullPath;
        }
        try {
            java.nio.file.Path base = java.nio.file.Paths.get(basePath).normalize();
            java.nio.file.Path absolute = java.nio.file.Paths.get(fullPath).normalize();
            return base.relativize(absolute).toString();
        } catch (Exception e) {
            return fullPath;
        }
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull Collection<? super LineMarkerInfo<?>> result) {
    }

    private static boolean atFirstContent(@NotNull PsiElement element, @NotNull PsiFile file) {
        int off = firstContentOffset(file);
        if (element.getTextRange().getStartOffset() != off) {
            return false;
        }
        PsiElement leaf = file.findElementAt(off);
        return leaf != null && leaf.getTextRange().getStartOffset() == element.getTextRange().getStartOffset()
                && leaf.getTextLength() == element.getTextLength();
    }

    private static int firstContentOffset(@NotNull PsiFile file) {
        long stamp = file.getModificationStamp();
        Long cached = file.getUserData(FIRST_OFF);
        Long cachedStamp = file.getUserData(FIRST_STAMP);
        if (cached != null && cachedStamp != null && cachedStamp == stamp) {
            return cached.intValue();
        }
        int off = 0;
        PsiElement cur = file.getFirstChild();
        while (cur != null) {
            if (!(cur instanceof PsiWhiteSpace)) {
                String t = cur.getText();
                if (t != null && !t.trim().isEmpty()) {
                    off = cur.getTextRange().getStartOffset();
                    break;
                }
            }
            cur = cur.getNextSibling();
        }
        file.putUserData(FIRST_OFF, (long) off);
        file.putUserData(FIRST_STAMP, stamp);
        return off;
    }
}
