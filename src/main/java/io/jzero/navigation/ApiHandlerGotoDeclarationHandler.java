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
 * Ctrl/⌘+Click on @handler name — resolve only on explicit navigation, not during highlighting.
 */
public class ApiHandlerGotoDeclarationHandler implements GotoDeclarationHandler {

    @Nullable
    @Override
    public PsiElement[] getGotoDeclarationTargets(@Nullable PsiElement source,
                                                  int offset,
                                                  @NotNull Editor editor) {
        if (source == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        if (source.getNode() == null
                || source.getNode().getElementType() != ApiParserDefinition.IDENTIFIER
                || !(source.getParent() instanceof HandlerValueNode)) {
            return PsiElement.EMPTY_ARRAY;
        }
        String handlerName = source.getText();
        if (handlerName == null || handlerName.trim().isEmpty()) {
            return PsiElement.EMPTY_ARRAY;
        }
        PsiFile logic = HandlerLogicResolver.findLogicFile(source, handlerName);
        if (logic == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        int targetOffset = HandlerLogicResolver.findLogicTargetOffset(logic, handlerName);
        PsiElement at = logic.findElementAt(targetOffset);
        return at != null ? new PsiElement[]{at} : new PsiElement[]{logic};
    }
}
