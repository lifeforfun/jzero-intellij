package io.jzero.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiFile;
import io.jzero.psi.ApiFiles;
import io.jzero.psi.nodes.ApiRootNode;
import org.jetbrains.annotations.NotNull;

public class ApiCompletionProvider extends ApiProvider {

    public ApiCompletionProvider(int priority,
                                 com.intellij.codeInsight.completion.InsertHandler<com.intellij.codeInsight.lookup.LookupElement> insertHandler) {
        super(priority, insertHandler);
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  @NotNull com.intellij.util.ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        PsiFile file = parameters.getOriginalFile();
        if (file.getFirstChild() instanceof ApiRootNode) {
            for (String name : ApiFiles.index(file).structNames()) {
                result.addElement(createKeywordLookupElement(name));
            }
        }
    }
}
