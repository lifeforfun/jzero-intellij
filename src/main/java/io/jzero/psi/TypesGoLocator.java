package io.jzero.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Resolve internal/types/types.go for gutter navigation. */
public final class TypesGoLocator {

    private TypesGoLocator() {
    }

    static boolean exists(@NotNull PsiElement element) {
        return locate(element) != null;
    }

    @Nullable
    public static PsiFile locate(@NotNull PsiElement element) {
        PsiFile apiFile = element.getContainingFile();
        if (apiFile == null) {
            return null;
        }
        VirtualFile source = apiFile.getVirtualFile();
        if (source == null) {
            return null;
        }
        Project project = element.getProject();
        String goPackage = extractGoPackage(apiFile.getText());
        String typesPath = typesPath(source.getPath(), goPackage);
        if (typesPath == null) {
            return null;
        }
        VirtualFile vf = source.getFileSystem().findFileByPath(typesPath);
        if (vf == null) {
            return null;
        }
        return PsiManager.getInstance(project).findFile(vf);
    }

    @Nullable
    private static String extractGoPackage(@NotNull String content) {
        for (String line : content.split("\n")) {
            line = line.trim();
            if (!line.startsWith("go_package:")) {
                continue;
            }
            int q1 = line.indexOf('"');
            int q2 = line.lastIndexOf('"');
            if (q1 >= 0 && q2 > q1) {
                return line.substring(q1 + 1, q2);
            }
        }
        return null;
    }

    @Nullable
    private static String typesPath(@NotNull String apiPath, @Nullable String goPackage) {
        String base;
        if (apiPath.contains("/desc/api/")) {
            base = apiPath.substring(0, apiPath.indexOf("/desc/api/")) + "/internal/types";
        } else if (apiPath.contains("/api/")) {
            base = apiPath.substring(0, apiPath.indexOf("/api/")) + "/internal/types";
        } else {
            int slash = apiPath.lastIndexOf('/');
            if (slash < 0) {
                return null;
            }
            base = apiPath.substring(0, slash) + "/internal/types";
        }
        if (goPackage == null || goPackage.isEmpty()) {
            return base + "/types.go";
        }
        return base + "/" + goPackage + "/types.go";
    }
}
