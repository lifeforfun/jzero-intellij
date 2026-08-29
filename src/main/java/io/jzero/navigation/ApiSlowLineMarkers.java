package io.jzero.navigation;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.icon.ApiIcon;
import io.jzero.index.ApiIndex;
import io.jzero.index.ApiIndexHolder;
import io.jzero.language.ApiFileType;
import io.jzero.psi.TypesGoLocator;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Gutter icons — only emit markers for elements in the current slow-pass batch
 * (adding the whole file every call duplicates icons).
 */
public class ApiSlowLineMarkers implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements,
                                       @NotNull Collection<? super LineMarkerInfo<?>> result) {
        if (elements.isEmpty()) {
            return;
        }
        PsiFile file = elements.get(0).getContainingFile();
        if (file == null || !(file.getFileType() instanceof ApiFileType)) {
            return;
        }
        Set<PsiElement> batch = new HashSet<>(elements);
        ApiPerf.run("slowMarker.build", () -> collect(file, batch, result));
    }

    private static void collect(@NotNull PsiFile file,
                                @NotNull Set<PsiElement> batch,
                                @NotNull Collection<? super LineMarkerInfo<?>> result) {
        ApiIndex index = ApiIndexHolder.get(file, true);
        if (index.isEmpty()) {
            return;
        }
        boolean typesOk = TypesGoLocator.exists(file);

        for (ApiIndex.HandlerSite h : index.handlers()) {
            PsiElement ident = h.ident();
            if (!ident.isValid() || !batch.contains(ident)) {
                continue;
            }
            String display = HandlerLogicResolver.stripHandlerSuffix(h.name());
            result.add(new LineMarkerInfo<>(
                    ident,
                    ident.getTextRange(),
                    ApiIcon.FILE,
                    e -> "Navigate to Logic: " + display,
                    (e, elt) -> navigateLogic(elt, h.name()),
                    GutterIconRenderer.Alignment.LEFT,
                    () -> "Go to " + display + " logic"
            ));
        }

        for (PsiElement el : batch) {
            if (!el.isValid() || !HandlerLogicResolver.isMiddlewareValue(el)) {
                continue;
            }
            String name = el.getText();
            if (name == null || name.isEmpty()) {
                continue;
            }
            result.add(new LineMarkerInfo<>(
                    el,
                    el.getTextRange(),
                    ApiIcon.FILE,
                    e -> "Navigate to Middleware: " + name,
                    (e, elt) -> navigateMiddleware(elt, name),
                    GutterIconRenderer.Alignment.LEFT,
                    () -> "Go to " + name + " middleware"
            ));
        }

        if (!typesOk) {
            return;
        }
        for (ApiIndex.StructSite s : index.structs()) {
            PsiElement el = s.node();
            if (!el.isValid()) {
                continue;
            }
            PsiElement leaf = el.getFirstChild() != null ? el.getFirstChild() : el;
            if (!batch.contains(el) && !batch.contains(leaf)) {
                continue;
            }
            PsiElement anchor = batch.contains(leaf) ? leaf : el;
            String name = s.name();
            result.add(new LineMarkerInfo<>(
                    anchor,
                    anchor.getTextRange(),
                    ApiIcon.FILE,
                    e -> "Navigate to Go Types: " + name,
                    (e, elt) -> navigateTypes(elt, name),
                    GutterIconRenderer.Alignment.LEFT,
                    () -> "Go to " + name + " struct"
            ));
        }
    }

    private static void navigateLogic(@NotNull PsiElement source, @NotNull String handlerName) {
        PsiFile target = HandlerLogicResolver.findLogicFile(source, handlerName);
        if (target == null || target.getVirtualFile() == null) {
            return;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(target, handlerName);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), target.getVirtualFile(), offset).navigate(true);
    }

    private static void navigateMiddleware(@NotNull PsiElement source, @NotNull String name) {
        PsiFile target = HandlerLogicResolver.findMiddlewareFile(source, name);
        if (target == null || target.getVirtualFile() == null) {
            return;
        }
        int offset = HandlerLogicResolver.findLogicTargetOffset(target, name);
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), target.getVirtualFile(), offset).navigate(true);
    }

    private static void navigateTypes(@NotNull PsiElement source, @NotNull String structName) {
        PsiFile goFile = TypesGoLocator.locate(source);
        if (goFile == null || goFile.getVirtualFile() == null) {
            return;
        }
        String content = goFile.getText();
        String pattern = "type " + structName + " struct";
        int idx = content.indexOf(pattern);
        if (idx < 0) {
            idx = 0;
        }
        new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                source.getProject(), goFile.getVirtualFile(), idx).navigate(true);
    }
}
