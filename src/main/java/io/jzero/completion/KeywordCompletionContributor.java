package io.jzero.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import io.jzero.antlr4.ApiParser;
import io.jzero.parser.ApiParserDefinition;

import static com.intellij.patterns.PlatformPatterns.not;
import static com.intellij.patterns.PlatformPatterns.or;
import static com.intellij.patterns.PlatformPatterns.psiElement;

/** Keywords — skip type-ref contexts handled by {@link ApiCompletionContributor}. */
public class KeywordCompletionContributor extends CompletionContributor {

    private static final String[] KEYWORDS = {
            "syntax", "import", "map", "type", "vo", "dto", "bool", "uint8", "uint16",
            "uint32", "uint64", "int8", "int16", "int32", "int64", "float32", "float64",
            "complex64", "complex128", "string", "int", "uint", "uintptr", "byte", "rune",
            "get", "head", "post", "put", "patch", "delete", "connect", "options", "trace",
            "info", "server", "service", "handler", "middleware", "returns", "interface{}",
            "prefix", "group", "jwt", "doc", "any", "timeout", "maxBytes", "true", "false"
    };

    public KeywordCompletionContributor() {
        extend(CompletionType.BASIC, keywordContext(),
                new ApiKeywordCompletionProvider(Priority.KEYWORD_PRIORITY, KEYWORDS));
    }

    private static ElementPattern<PsiElement> keywordContext() {
        return psiElement(ApiParserDefinition.IDENTIFIER).withParent(not(or(
                psiElement(ApiParserDefinition.rule(ApiParser.RULE_referenceId)),
                psiElement(ApiParserDefinition.rule(ApiParser.RULE_body))
        )));
    }
}
