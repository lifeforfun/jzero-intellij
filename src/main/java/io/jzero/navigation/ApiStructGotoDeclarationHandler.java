package io.jzero.navigation;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.antlr4.ApiParser;
import io.jzero.index.ApiIndexHolder;
import io.jzero.language.ApiFileType;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.nodes.StructNameNode;
import org.antlr.jetbrains.adapter.lexer.RuleIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Ctrl/⌘+Click on type ref — resolve only on navigation. */
public class ApiStructGotoDeclarationHandler implements GotoDeclarationHandler {

    @Nullable
    @Override
    public PsiElement[] getGotoDeclarationTargets(@Nullable PsiElement source,
                                                  int offset,
                                                  @NotNull Editor editor) {
        if (source == null || source.getNode() == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        if (source.getNode().getElementType() != ApiParserDefinition.IDENTIFIER) {
            return PsiElement.EMPTY_ARRAY;
        }
        PsiElement parent = source.getParent();
        if (parent == null || parent.getNode() == null
                || !(parent.getNode().getElementType() instanceof RuleIElementType)) {
            return PsiElement.EMPTY_ARRAY;
        }
        int rule = ((RuleIElementType) parent.getNode().getElementType()).getRuleIndex();
        if (rule != ApiParser.RULE_referenceId && rule != ApiParser.RULE_body) {
            return PsiElement.EMPTY_ARRAY;
        }
        PsiFile file = source.getContainingFile();
        if (file == null || !(file.getFileType() instanceof ApiFileType)) {
            return PsiElement.EMPTY_ARRAY;
        }
        StructNameNode node = ApiIndexHolder.rebuild(file).structNode(source.getText());
        return node != null ? new PsiElement[]{node} : PsiElement.EMPTY_ARRAY;
    }
}
