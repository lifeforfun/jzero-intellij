package io.jzero.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import io.jzero.navigation.HandlerLogicResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Ctrl/⌘+Click from @handler XxxHandler to NewXxxLogic in internal/logic.
 */
public class HandlerReference extends PsiReferenceBase<IdentifierPSINode> {

    public HandlerReference(@NotNull IdentifierPSINode element) {
        super(element, TextRange.from(0, element.getTextLength()));
    }

    @Override
    public @Nullable PsiElement resolve() {
        String handlerName = myElement.getText();
        if (handlerName == null || handlerName.trim().isEmpty()) {
            return null;
        }
        PsiFile logicFile = HandlerLogicResolver.findLogicFile(myElement, handlerName);
        if (logicFile == null) {
            return null;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(logicFile, handlerName);
        PsiElement at = logicFile.findElementAt(offset);
        return at != null ? at : logicFile;
    }
}
