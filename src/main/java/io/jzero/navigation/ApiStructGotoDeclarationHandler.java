package io.jzero.navigation;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.antlr4.ApiParser;
import io.jzero.index.ApiIndexHolder;
import io.jzero.language.ApiFileType;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.TypesGoLocator;
import io.jzero.psi.nodes.StructNameNode;
import org.antlr.jetbrains.adapter.lexer.RuleIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Ctrl/⌘+Click on struct name or type ref → internal/types/types.go. */
public class ApiStructGotoDeclarationHandler implements GotoDeclarationHandler {

    @Nullable
    @Override
    public PsiElement[] getGotoDeclarationTargets(@Nullable PsiElement source,
                                                  int offset,
                                                  @NotNull Editor editor) {
        if (source == null || source.getNode() == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        if (source.getNode().getElementType() != ApiParserDefinition.IDENTIFIER
                && !(source instanceof StructNameNode)) {
            return PsiElement.EMPTY_ARRAY;
        }
        String name = source instanceof StructNameNode ? source.getText() : source.getText();
        if (name == null || name.trim().isEmpty()) {
            return PsiElement.EMPTY_ARRAY;
        }
        if (!isStructContext(source)) {
            return PsiElement.EMPTY_ARRAY;
        }
        PsiFile file = source.getContainingFile();
        if (file == null || !(file.getFileType() instanceof ApiFileType)) {
            return PsiElement.EMPTY_ARRAY;
        }
        if (!ApiIndexHolder.rebuild(file).hasStruct(name)) {
            return PsiElement.EMPTY_ARRAY;
        }
        PsiFile goFile = TypesGoLocator.locate(source);
        if (goFile == null) {
            return PsiElement.EMPTY_ARRAY;
        }
        int targetOffset = findStructOffset(goFile, name);
        PsiElement at = goFile.findElementAt(targetOffset);
        return at != null ? new PsiElement[]{at} : new PsiElement[]{goFile};
    }

    private static boolean isStructContext(@NotNull PsiElement source) {
        if (source instanceof StructNameNode) {
            return true;
        }
        PsiElement parent = source.getParent();
        if (parent == null || parent.getNode() == null
                || !(parent.getNode().getElementType() instanceof RuleIElementType)) {
            return false;
        }
        int rule = ((RuleIElementType) parent.getNode().getElementType()).getRuleIndex();
        return rule == ApiParser.RULE_structNameId
                || rule == ApiParser.RULE_referenceId
                || rule == ApiParser.RULE_body;
    }

    static int findStructOffset(@NotNull PsiFile goFile, @NotNull String structName) {
        String content = goFile.getText();
        String pattern = "type " + structName + " struct";
        int idx = content.indexOf(pattern);
        return idx >= 0 ? idx : 0;
    }
}
