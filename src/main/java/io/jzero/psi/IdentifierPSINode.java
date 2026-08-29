package io.jzero.psi;

import io.jzero.antlr4.ApiParser;
import io.jzero.language.ApiLanguage;
import io.jzero.parser.ApiParserDefinition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.antlr.jetbrains.adapter.lexer.RuleIElementType;
import org.antlr.jetbrains.adapter.psi.AntlrPsiLeafNode;
import org.antlr.jetbrains.adapter.psi.Trees;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class IdentifierPSINode extends AntlrPsiLeafNode implements PsiNamedElement {

    public IdentifierPSINode(IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public String getName() {
        return getText();
    }


    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        if (getParent() == null) return this;
        PsiElement newID = Trees.createLeafFromText(getProject(),
                ApiLanguage.INSTANCE,
                getContext(),
                name,
                ApiParserDefinition.IDENTIFIER);
        if (newID != null) {
            return this.replace(newID);
        }
        return this;
    }

    /** No PsiReference — highlighting must not resolve; use GotoDeclarationHandler. */
    @Override
    public PsiReference getReference() {
        return null;
    }
}
