package io.jzero.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.jzero.antlr4.ApiParser;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.ApiFile;
import io.jzero.psi.ApiFileCache;
import io.jzero.psi.nodes.ApiRootNode;
import io.jzero.psi.nodes.ServiceNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Lightweight annotator: duplicate handler/route/struct only.
 * No resolve(), no per-field walks — those block the EDT on large .api files.
 */
public class ApiAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (DumbService.isDumb(element.getProject())) {
            return;
        }
        if (!(element instanceof ApiRootNode)) {
            return;
        }
        PsiFile file = element.getContainingFile();
        if (file == null) {
            return;
        }
        ApiFileCache cache = ApiFileCache.of(file);
        markDup((ApiRootNode) element, holder, cache, ApiParser.RULE_handlerValue,
                "duplicate handler ", cache::isDupHandler);
        markRouteDupsInServices((ApiRootNode) element, holder);
        markDup((ApiRootNode) element, holder, cache, ApiParser.RULE_structNameId,
                "duplicate struct ", cache::isDupStruct);
    }

    /** Routes are unique per service block; same path under different @server prefix is OK. */
    private static void markRouteDupsInServices(@NotNull ApiRootNode root,
                                                @NotNull AnnotationHolder holder) {
        for (ServiceNode service : PsiTreeUtil.findChildrenOfType(root, ServiceNode.class)) {
            List<ASTNode> routes = ApiFile.findChildren(
                    service, ApiParserDefinition.rule(ApiParser.RULE_httpRoute));
            if (routes == null || routes.isEmpty()) {
                continue;
            }
            java.util.Map<String, java.util.List<ASTNode>> grouped = new java.util.HashMap<>();
            for (ASTNode route : routes) {
                String key = route.getText();
                grouped.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(route);
            }
            for (java.util.List<ASTNode> same : grouped.values()) {
                if (same.size() <= 1) {
                    continue;
                }
                for (ASTNode node : same) {
                    holder.createErrorAnnotation(node, "duplicate route " + node.getText());
                }
            }
        }
    }

    private static void markDup(@NotNull ApiRootNode root,
                                @NotNull AnnotationHolder holder,
                                @NotNull ApiFileCache cache,
                                int rule,
                                @NotNull String prefix,
                                @NotNull java.util.function.Predicate<String> isDup) {
        List<ASTNode> nodes = ApiFile.findChildren(root, ApiParserDefinition.rule(rule));
        if (nodes == null) {
            return;
        }
        for (ASTNode node : nodes) {
            String name = node.getText();
            if (isDup.test(name)) {
                holder.createErrorAnnotation(node, prefix + name);
            }
        }
    }
}
