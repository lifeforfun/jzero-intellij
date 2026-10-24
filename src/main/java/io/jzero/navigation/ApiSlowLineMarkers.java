package io.jzero.navigation;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.icon.ApiIcon;
import io.jzero.index.ApiIndexHolder;
import io.jzero.language.ApiFileType;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.TypesGoLocator;
import io.jzero.psi.nodes.HandlerValueNode;
import io.jzero.psi.nodes.StructNameNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * Gutter icons for handler / struct / middleware navigation.
 * Fast path via {@link #getLineMarkerInfo}; slow pass intentionally empty.
 */
public class ApiSlowLineMarkers implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getNode() != null
                && element.getNode().getElementType() == ApiParserDefinition.IDENTIFIER
                && element.getParent() instanceof HandlerValueNode) {
            return handlerMarker(element, element.getText());
        }
        if (HandlerLogicResolver.isMiddlewareValue(element)) {
            return middlewareMarker(element, element.getText());
        }
        if (element instanceof StructNameNode) {
            return structMarker(element, element.getText());
        }
        if (element.getNode() != null
                && element.getNode().getElementType() == ApiParserDefinition.IDENTIFIER
                && element.getParent() instanceof StructNameNode) {
            return structMarker(element, element.getText());
        }
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull Collection<? super LineMarkerInfo<?>> result) {
        // getLineMarkerInfo covers api navigation; avoid re-adding whole-file markers here.
    }

    @Nullable
    private static LineMarkerInfo<?> handlerMarker(@NotNull PsiElement leaf, @Nullable String handlerName) {
        if (handlerName == null || handlerName.trim().isEmpty()) {
            return null;
        }
        String display = HandlerLogicResolver.stripHandlerSuffix(handlerName);
        return new LineMarkerInfo<>(
                leaf,
                leaf.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Logic: " + display,
                (e, elt) -> navigateLogic(elt, handlerName),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to " + display + " logic"
        );
    }

    @Nullable
    private static LineMarkerInfo<?> middlewareMarker(@NotNull PsiElement leaf, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new LineMarkerInfo<>(
                leaf,
                leaf.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Middleware: " + name,
                (e, elt) -> navigateMiddleware(elt, name),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to " + name + " middleware"
        );
    }

    @Nullable
    private static LineMarkerInfo<?> structMarker(@NotNull PsiElement anchor, @Nullable String structName) {
        if (structName == null || structName.isEmpty()) {
            return null;
        }
        if (!TypesGoLocator.exists(anchor)) {
            return null;
        }
        PsiFile file = anchor.getContainingFile();
        if (file == null || !(file.getFileType() instanceof ApiFileType)) {
            return null;
        }
        if (!ApiIndexHolder.get(file, false).hasStruct(structName)) {
            return null;
        }
        return new LineMarkerInfo<>(
                anchor,
                anchor.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Go Types: " + structName,
                (e, elt) -> navigateTypes(elt, structName),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to " + structName + " struct"
        );
    }

    private static void navigateLogic(@NotNull PsiElement source, @NotNull String handlerName) {
        PsiFile target = HandlerLogicResolver.findLogicFile(source, handlerName);
        if (target == null || target.getVirtualFile() == null) {
            return;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(target, handlerName);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), target.getVirtualFile(), offset).navigate(true);
    }

    private static void navigateMiddleware(@NotNull PsiElement source, @NotNull String name) {
        PsiFile target = HandlerLogicResolver.findMiddlewareFile(source, name);
        if (target == null || target.getVirtualFile() == null) {
            return;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(target, name);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), target.getVirtualFile(), offset).navigate(true);
    }

    private static void navigateTypes(@NotNull PsiElement source, @NotNull String structName) {
        PsiFile goFile = TypesGoLocator.locate(source);
        if (goFile == null || goFile.getVirtualFile() == null) {
            return;
        }
        int offset = ApiStructGotoDeclarationHandler.findStructOffset(goFile, structName);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), goFile.getVirtualFile(), offset).navigate(true);
    }
}
