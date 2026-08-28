package io.jzero.navigation;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBList;
import io.jzero.icon.ApiIcon;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.nodes.HandlerValueNode;
import io.jzero.psi.nodes.ServiceNode;
import io.jzero.util.JzeroConfigReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Handler / middleware gutter navigation.
 * getLineMarkerInfo must not touch FilenameIndex or walk the project.
 */
public class ApiGotoDeclarationHandler implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getNode() != null
                && element.getNode().getElementType() == ApiParserDefinition.IDENTIFIER
                && element.getParent() instanceof HandlerValueNode) {
            return handlerMarker(element, element.getText());
        }
        if (isMiddlewareKeyword(element)) {
            return middlewareMarker(element);
        }
        return null;
    }

    @Nullable
    private LineMarkerInfo<?> handlerMarker(@NotNull PsiElement leaf, @NotNull String handlerName) {
        if (handlerName.trim().isEmpty()) {
            return null;
        }
        String display = HandlerLogicResolver.stripHandlerSuffix(handlerName);
        return new LineMarkerInfo<>(
                leaf,
                leaf.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Logic: " + display,
                (e, elt) -> navigateToLogic(elt, handlerName),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to " + display + " logic"
        );
    }

    private void navigateToLogic(@NotNull PsiElement source, @NotNull String handlerName) {
        PsiFile target = HandlerLogicResolver.findLogicFile(source, handlerName);
        if (target == null || target.getVirtualFile() == null) {
            return;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(target, handlerName);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), target.getVirtualFile(), offset).navigate(true);
    }

    @NotNull
    private LineMarkerInfo<?> middlewareMarker(@NotNull PsiElement element) {
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Middleware",
                (e, elt) -> showMiddlewarePopup(elt),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Choose middleware to navigate"
        );
    }

    private void showMiddlewarePopup(@NotNull PsiElement source) {
        List<String> names = extractMiddlewareNames(source);
        if (names.isEmpty()) {
            return;
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String n : names) {
            model.addElement(n);
        }
        JBList<String> list = new JBList<>(model);
        Runnable onChoose = () -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                navigateToMiddleware(source, selected);
            }
        };
        Editor editor = com.intellij.openapi.fileEditor.FileEditorManager
                .getInstance(source.getProject()).getSelectedTextEditor();
        if (editor != null && source.getContainingFile() != null
                && source.getContainingFile().getViewProvider().getDocument() != null) {
            int line = source.getContainingFile().getViewProvider().getDocument()
                    .getLineNumber(source.getTextRange().getStartOffset());
            java.awt.Point point = editor.logicalPositionToXY(new com.intellij.openapi.editor.LogicalPosition(line, 0));
            new PopupChooserBuilder<>(list)
                    .setTitle("Choose Middleware")
                    .setItemChoosenCallback(onChoose)
                    .createPopup()
                    .show(new RelativePoint(editor.getContentComponent(), point));
        } else {
            new PopupChooserBuilder<>(list)
                    .setTitle("Choose Middleware")
                    .setItemChoosenCallback(onChoose)
                    .createPopup()
                    .showCenteredInCurrentWindow(source.getProject());
        }
    }

    @NotNull
    private List<String> extractMiddlewareNames(@NotNull PsiElement element) {
        List<String> names = new ArrayList<>();
        PsiFile file = element.getContainingFile();
        if (file == null) {
            return names;
        }
        String fileText = file.getText();
        int elementOffset = element.getTextRange().getStartOffset();
        int serverStart = fileText.lastIndexOf("@server", elementOffset);
        if (serverStart == -1) {
            return names;
        }
        int annotationEnd = fileText.indexOf("service", serverStart);
        if (annotationEnd == -1) {
            annotationEnd = fileText.length();
        }
        for (String line : fileText.substring(serverStart, annotationEnd).split("\n")) {
            line = line.trim();
            if (!line.startsWith("middleware:")) {
                continue;
            }
            String value = line.substring("middleware:".length()).trim();
            if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
                value = value.substring(1, value.length() - 1);
            }
            for (String part : value.split(",")) {
                String name = part.trim();
                if (!name.isEmpty()) {
                    names.add(name);
                }
            }
        }
        return names;
    }

    private void navigateToMiddleware(@NotNull PsiElement source, @NotNull String middlewareName) {
        String naming = JzeroConfigReader.getNamingStyle(source.getProject(), source.getContainingFile());
        String formatted = JzeroConfigReader.formatFileName(naming, middlewareName);
        String rel = "internal/middleware/" + formatted + "middleware.go";
        PsiFile target = findMiddlewareFile(source, rel);
        if (target != null) {
            navigateToMiddlewareFn(source.getProject(), target, middlewareName);
        }
    }

    @Nullable
    private PsiFile findMiddlewareFile(@NotNull PsiElement source, @NotNull String rel) {
        VirtualFile api = source.getContainingFile().getVirtualFile();
        if (api == null) {
            return null;
        }
        String root = HandlerLogicResolver.resolveProjectRoot(api);
        if (root == null) {
            return null;
        }
        VirtualFile vf = api.getFileSystem().findFileByPath(root + "/" + rel);
        return vf != null ? PsiManager.getInstance(source.getProject()).findFile(vf) : null;
    }

    private void navigateToMiddlewareFn(@NotNull Project project, @NotNull PsiFile goFile,
                                        @NotNull String middlewareName) {
        String content = goFile.getText();
        String[] patterns = {
                "func " + middlewareName + "(",
                "func New" + middlewareName + "(",
                "func " + Character.toUpperCase(middlewareName.charAt(0))
                        + middlewareName.substring(1) + "("
        };
        for (String p : patterns) {
            int idx = content.indexOf(p);
            if (idx >= 0) {
                new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                        project, goFile.getVirtualFile(), idx).navigate(true);
                return;
            }
        }
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                project, goFile.getVirtualFile(), 0).navigate(true);
    }

    private boolean isMiddlewareKeyword(@NotNull PsiElement element) {
        if (!"middleware".equals(element.getText())) {
            return false;
        }
        PsiElement next = element.getNextSibling();
        if (next == null || !next.getText().trim().startsWith(":")) {
            return false;
        }
        return PsiTreeUtil.getParentOfType(element, ServiceNode.class) != null
                || hasAncestorWithAtServer(element);
    }

    private boolean hasAncestorWithAtServer(@NotNull PsiElement element) {
        PsiElement cur = element.getParent();
        while (cur != null) {
            if (cur instanceof ServiceNode) {
                break;
            }
            cur = cur.getParent();
        }
        return cur != null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull Collection<? super LineMarkerInfo<?>> result) {
        // intentionally empty — fast path only
    }
}
