package io.jzero.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import io.jzero.index.ApiIndex;
import io.jzero.language.ApiFileType;
import io.jzero.psi.ApiFiles;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;

/** Duplicate checks — only when idle (not on-the-fly), avoids daemon work while typing. */
public class ApiDuplicateInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        if (isOnTheFly) {
            ApiPerf.inc("inspection.skipOnTheFly");
            return PsiElementVisitor.EMPTY_VISITOR;
        }
        return ApiPerf.call("inspection.buildVisitor", () -> build(holder));
    }

    @NotNull
    private PsiElementVisitor build(@NotNull ProblemsHolder holder) {
        PsiFile file = holder.getFile();
        if (!(file.getFileType() instanceof ApiFileType)) {
            return PsiElementVisitor.EMPTY_VISITOR;
        }
        ApiIndex index = ApiFiles.index(file);
        if (index.isEmpty()) {
            return PsiElementVisitor.EMPTY_VISITOR;
        }
        return new PsiElementVisitor() {
            private boolean reported;

            @Override
            public void visitFile(@NotNull PsiFile f) {
                if (reported || f != file) {
                    return;
                }
                reported = true;
                for (ApiIndex.Issue issue : index.issues()) {
                    if (issue.element().isValid()) {
                        holder.registerProblem(issue.element(), issue.message());
                    }
                }
            }
        };
    }
}
