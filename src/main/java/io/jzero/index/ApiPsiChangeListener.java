package io.jzero.index;

import com.intellij.lang.Language;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;
import io.jzero.language.ApiLanguage;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;

/** Enqueue throttled index rebuild when .api PSI changes. */
public final class ApiPsiChangeListener extends PsiTreeChangeAdapter {

    @Override
    public void childrenChanged(@NotNull PsiTreeChangeEvent event) {
        ApiPerf.inc("psi.childrenChanged");
        PsiFile file = event.getFile();
        if (file == null) {
            return;
        }
        Language lang = file.getLanguage();
        if (!lang.isKindOf(ApiLanguage.INSTANCE)) {
            return;
        }
        ApiWorkQueue.get(file.getProject()).schedule(file);
    }
}
