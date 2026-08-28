package io.jzero.navigation;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.icon.ApiIcon;
import io.jzero.psi.ApiFileCache;
import io.jzero.psi.TypesGoLocator;
import io.jzero.psi.nodes.StructNameNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class TypeGotoDeclarationHandler implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (!(element instanceof StructNameNode)) {
            return null;
        }
        String structName = element.getText();
        if (structName == null || structName.isEmpty()) {
            return null;
        }
        if (!ApiFileCache.of(element.getContainingFile()).typesGoExists(element)) {
            return null;
        }
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                ApiIcon.FILE,
                e -> "Navigate to Go Types: " + structName,
                (e, elt) -> navigateToTypes(elt, structName),
                GutterIconRenderer.Alignment.LEFT,
                () -> "Go to " + structName + " struct"
        );
    }

    private void navigateToTypes(@NotNull PsiElement source, @NotNull String structName) {
        PsiFile goFile = TypesGoLocator.locate(source);
        if (goFile == null) {
            return;
        }
        String content = goFile.getText();
        String pattern = "type " + structName + " struct";
        int idx = content.indexOf(pattern);
        if (idx < 0) {
            idx = 0;
        }
        Project project = source.getProject();
        VirtualFile vf = goFile.getVirtualFile();
        if (vf != null) {
            new com.intellij.openapi.fileEditor.OpenFileDescriptor(project, vf, idx).navigate(true);
        }
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull Collection<? super LineMarkerInfo<?>> result) {
        // unused
    }
}
