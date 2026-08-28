package io.jzero.navigation;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
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
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import io.jzero.psi.ApiFileCache;
import io.jzero.runconfig.JzeroGenConfigurationFactory;
import io.jzero.runconfig.JzeroGenConfigurationType;
import io.jzero.runconfig.JzeroGenRunConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

/**
 * Gutter run buttons. Must stay O(1) per PSI element — never walk the file tree here.
 */
public class JzeroGenLineMarkerProvider implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null) {
            return null;
        }
        VirtualFile virtualFile = containingFile.getVirtualFile();
        if (virtualFile == null) {
            return null;
        }

        Project project = containingFile.getProject();
        String fileName = virtualFile.getName();
        String filePath = virtualFile.getPath();

        if (".jzero.yaml".equals(fileName)) {
            return createYamlLineMarker(element, project, virtualFile);
        }

        if (fileName.endsWith(".api")) {
            if (!filePath.contains("/desc/")) {
                return null;
            }
            if (element.getTextRange().getStartOffset() != ApiFileCache.firstContentOffset(containingFile)) {
                return null;
            }
            return createDescGenMarker(element, project);
        }

        if (fileName.endsWith(".proto")) {
            if (filePath.contains("/desc/proto/") && !filePath.contains("/desc/proto/third_party/")) {
                if (element.getTextRange().getStartOffset() != ApiFileCache.firstContentOffset(containingFile)) {
                    return null;
                }
                return createDescGenMarker(element, project);
            }
        }

        if (fileName.endsWith(".sql") && filePath.contains("/desc/sql/")) {
            if (element.getTextRange().getStartOffset() != ApiFileCache.firstContentOffset(containingFile)) {
                return null;
            }
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
        // heavy checks belong here, not in getLineMarkerInfo
    }
}
