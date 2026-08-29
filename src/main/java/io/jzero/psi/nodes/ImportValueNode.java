package io.jzero.psi.nodes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public class ImportValueNode extends IPsiNode implements PsiNamedElement {
    public ImportValueNode(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        ASTNode lastChild = getNode().getLastChildNode();
        if (lastChild != null) {
            return lastChild.getText().replace("\"", "");
        }
        return "";
    }

    public PsiElement setName(@NotNull String name) {
        return this;
    }

    public PsiElement getNameIdentifier() {
        ASTNode lastChild = getNode().getLastChildNode();
        return lastChild != null ? lastChild.getPsi() : this;
    }

    @Override
    public PsiReference getReference() {
        return null;
    }
}
