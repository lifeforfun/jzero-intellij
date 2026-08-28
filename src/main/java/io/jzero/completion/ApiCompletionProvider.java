package io.jzero.completion;

import io.jzero.antlr4.ApiParser;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.ApiFile;
import io.jzero.psi.ApiFileCache;
import io.jzero.psi.nodes.ApiRootNode;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiCompletionProvider extends ApiProvider {

    public ApiCompletionProvider(int priority, @Nullable AutoCompletionPolicy completionPolicy) {
        super(priority, completionPolicy);
    }

    public ApiCompletionProvider(int priority, @Nullable InsertHandler<LookupElement> insertHandler) {
        super(priority, insertHandler);
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        PsiElement position = parameters.getPosition();
        ASTNode node = position.getNode();
        if (node == null) {
            return;
        }
        if (!inTypeContext(node)) {
            return;
        }
        PsiFile original = parameters.getOriginalFile();
        ApiRootNode root = ApiFile.getRoot(original);
        if (root == null) {
            return;
        }
        for (String name : ApiFileCache.of(original).structNames()) {
            result.addElement(createKeywordLookupElement(name));
        }
    }

    private static boolean inTypeContext(@NotNull ASTNode node) {
        IElementType type = node.getElementType();
        if (type.equals(ApiParserDefinition.rule(ApiParser.RULE_referenceId))
                || type.equals(ApiParserDefinition.rule(ApiParser.RULE_body))) {
            return true;
        }
        ASTNode parent = node.getTreeParent();
        if (parent == null) {
            return false;
        }
        IElementType pt = parent.getElementType();
        return pt.equals(ApiParserDefinition.rule(ApiParser.RULE_normalFieldType))
                || pt.equals(ApiParserDefinition.rule(ApiParser.RULE_serviceRoute))
                || pt.equals(ApiParserDefinition.rule(ApiParser.RULE_body))
                || pt.equals(ApiParserDefinition.rule(ApiParser.RULE_referenceId));
    }
}
