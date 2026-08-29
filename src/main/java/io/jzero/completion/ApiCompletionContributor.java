package io.jzero.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import io.jzero.antlr4.ApiParser;
import io.jzero.editor.AutoInsertHandler;
import io.jzero.parser.ApiParserDefinition;

import static com.intellij.patterns.PlatformPatterns.or;
import static com.intellij.patterns.PlatformPatterns.psiElement;

/** Struct name completion — only inside type reference / route body, not every IDENT. */
public class ApiCompletionContributor extends CompletionContributor {

    public ApiCompletionContributor() {
        extend(CompletionType.BASIC, typeRefContext(),
                new ApiCompletionProvider(Priority.KEYWORD_PRIORITY, new AutoInsertHandler("")));
    }

    private static ElementPattern<PsiElement> typeRefContext() {
        return psiElement(ApiParserDefinition.IDENTIFIER).withParent(or(
                psiElement(ApiParserDefinition.rule(ApiParser.RULE_referenceId)),
                psiElement(ApiParserDefinition.rule(ApiParser.RULE_body))
        ));
    }
}
