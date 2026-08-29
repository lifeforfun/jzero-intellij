package io.jzero.navigation;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.nodes.HandlerValueNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Ctrl/⌘+Click: @handler Xxx → logic/handler go file;
 * {@code middleware: Xxx} → middleware/xxx.go.
 */
public class ApiHandlerGotoDeclarationHandler implements GotoDeclarationHandler {

    @Nullable
    @Override
    public PsiElement[] getGotoDeclarationTargets(@Nullable PsiElement source,
                                                  int offset,
                                                  @NotNull Editor editor) {
        if (source == null || source.getNode() == null
                || source.getNode().getElementType() != ApiParserDefinition.IDENTIFIER) {
            return PsiElement.EMPTY_ARRAY;
        }
        String name = source.getText();
        if (name == null || name.trim().isEmpty()) {
            return PsiElement.EMPTY_ARRAY;
        }

        PsiFile target = null;
        if (source.getParent() instanceof HandlerValueNode) {
            target = HandlerLogicResolver.findLogicFile(source, name);
        } else if (HandlerLogicResolver.isMiddlewareValue(source)) {
            target = HandlerLogicResolver.findMiddlewareFile(source, name);
        }
        if (target == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        int targetOffset = HandlerLogicResolver.findLogicTargetOffset(target, name);
        PsiElement at = target.findElementAt(targetOffset);
        return at != null ? new PsiElement[]{at} : new PsiElement[]{target};
    }
}
