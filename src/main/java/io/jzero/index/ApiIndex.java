package io.jzero.index;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import io.jzero.language.ApiFileType;
import com.intellij.psi.tree.IElementType;
import io.jzero.antlr4.ApiParser;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.nodes.ApiRootNode;
import io.jzero.psi.nodes.HandlerValueNode;
import io.jzero.psi.nodes.ServiceNode;
import io.jzero.psi.nodes.StructNameNode;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Single-pass file index; built once per PSI revision via CachedValuesManager. */
public final class ApiIndex {

    public static final class Issue {
        private final PsiElement element;
        private final String message;

        public Issue(@NotNull PsiElement element, @NotNull String message) {
            this.element = element;
            this.message = message;
        }

        @NotNull
        public PsiElement element() {
            return element;
        }

        @NotNull
        public String message() {
            return message;
        }
    }

    public static final class HandlerSite {
        private final PsiElement ident;
        private final String name;

        HandlerSite(@NotNull PsiElement ident, @NotNull String name) {
            this.ident = ident;
            this.name = name;
        }

        @NotNull
        public PsiElement ident() {
            return ident;
        }

        @NotNull
        public String name() {
            return name;
        }
    }

    public static final class StructSite {
        private final StructNameNode node;
        private final String name;

        StructSite(@NotNull StructNameNode node, @NotNull String name) {
            this.node = node;
            this.name = name;
        }

        @NotNull
        public StructNameNode node() {
            return node;
        }

        @NotNull
        public String name() {
            return name;
        }
    }

    private final Set<String> structNames;
    private final Map<String, StructNameNode> structByName;
    private final List<HandlerSite> handlers;
    private final List<StructSite> structs;
    private final List<Issue> issues;
    private final int firstContentOffset;

    private ApiIndex(@NotNull Set<String> structNames,
                     @NotNull Map<String, StructNameNode> structByName,
                     @NotNull List<HandlerSite> handlers,
                     @NotNull List<StructSite> structs,
                     @NotNull List<Issue> issues,
                     int firstContentOffset) {
        this.structNames = structNames;
        this.structByName = structByName;
        this.handlers = handlers;
        this.structs = structs;
        this.issues = issues;
        this.firstContentOffset = firstContentOffset;
    }

    @NotNull
    public static ApiIndex build(@NotNull PsiFile file) {
        return ApiPerf.call("index.build", () -> buildInternal(file, true));
    }

    @NotNull
    private static ApiIndex buildInternal(@NotNull PsiFile file, boolean followImports) {
        PsiElement root = file.getFirstChild();
        if (!(root instanceof ApiRootNode)) {
            return ApiIndex.EMPTY;
        }

        IElementType structRule = ApiParserDefinition.rule(ApiParser.RULE_structNameId);
        IElementType handlerRule = ApiParserDefinition.rule(ApiParser.RULE_handlerValue);
        IElementType routeRule = ApiParserDefinition.rule(ApiParser.RULE_httpRoute);
        IElementType importRule = ApiParserDefinition.rule(ApiParser.RULE_importValue);

        Set<String> names = new HashSet<>();
        Map<String, StructNameNode> structMap = new HashMap<>();
        List<HandlerSite> handlerSites = new ArrayList<>();
        List<StructSite> structSites = new ArrayList<>();
        List<Issue> dupIssues = new ArrayList<>();

        Map<String, Integer> handlerCounts = new HashMap<>();
        Map<String, Integer> structCounts = new HashMap<>();
        Map<String, List<PsiElement>> handlerNodes = new HashMap<>();
        Map<String, List<PsiElement>> structNodes = new HashMap<>();
        List<String> importPaths = new ArrayList<>();

        walk(root, node -> {
            IElementType type = node.getElementType();
            if (type.equals(importRule)) {
                ASTNode last = node.getLastChildNode();
                if (last != null) {
                    importPaths.add(last.getText().replace("\"", ""));
                }
                return;
            }
            PsiElement psi = node.getPsi();
            if (psi == null) {
                return;
            }
            String text = node.getText();
            if (text == null || text.isEmpty()) {
                return;
            }
            if (type.equals(structRule) && psi instanceof StructNameNode) {
                names.add(text);
                structMap.putIfAbsent(text, (StructNameNode) psi);
                structSites.add(new StructSite((StructNameNode) psi, text));
                structCounts.merge(text, 1, Integer::sum);
                structNodes.computeIfAbsent(text, k -> new ArrayList<>()).add(psi);
            } else if (type.equals(handlerRule) && psi instanceof HandlerValueNode) {
                PsiElement ident = psi.getFirstChild();
                if (ident != null) {
                    handlerSites.add(new HandlerSite(ident, text));
                    handlerCounts.merge(text, 1, Integer::sum);
                    handlerNodes.computeIfAbsent(text, k -> new ArrayList<>()).add(ident);
                }
            }
        });

        handlerCounts.forEach((name, count) -> {
            if (count > 1) {
                for (PsiElement el : handlerNodes.getOrDefault(name, Collections.emptyList())) {
                    dupIssues.add(new Issue(el, "duplicate handler " + name));
                }
            }
        });
        structCounts.forEach((name, count) -> {
            if (count > 1) {
                for (PsiElement el : structNodes.getOrDefault(name, Collections.emptyList())) {
                    dupIssues.add(new Issue(el, "duplicate struct " + name));
                }
            }
        });

        for (ServiceNode service : findServices(root)) {
            Map<String, List<PsiElement>> routes = new HashMap<>();
            walk(service, node -> {
                if (!node.getElementType().equals(routeRule)) {
                    return;
                }
                PsiElement psi = node.getPsi();
                if (psi == null) {
                    return;
                }
                String key = node.getText();
                routes.computeIfAbsent(key, k -> new ArrayList<>()).add(psi);
            });
            routes.forEach((route, els) -> {
                if (els.size() > 1) {
                    for (PsiElement el : els) {
                        dupIssues.add(new Issue(el, "duplicate route " + route));
                    }
                }
            });
        }

        if (followImports) {
            mergeImports(file, names, structMap, importPaths);
        }

        return new ApiIndex(
                Collections.unmodifiableSet(names),
                Collections.unmodifiableMap(structMap),
                Collections.unmodifiableList(handlerSites),
                Collections.unmodifiableList(structSites),
                Collections.unmodifiableList(dupIssues),
                computeFirstContentOffset(file)
        );
    }

    public static int computeFirstContentOffset(@NotNull PsiFile file) {
        return firstOffset(file);
    }

    @NotNull
    public Set<String> structNames() {
        return structNames;
    }

    @NotNull
    public List<HandlerSite> handlers() {
        return handlers;
    }

    @NotNull
    public List<StructSite> structs() {
        return structs;
    }

    @NotNull
    public List<Issue> issues() {
        return issues;
    }

    public int firstContentOffset() {
        return firstContentOffset;
    }

    public boolean hasStruct(@Nullable String name) {
        return name != null && structNames.contains(name);
    }

    @Nullable
    public StructNameNode structNode(@Nullable String name) {
        return name == null ? null : structByName.get(name);
    }

    private static void mergeImports(@NotNull PsiFile file,
                                     @NotNull Set<String> names,
                                     @NotNull Map<String, StructNameNode> structMap,
                                     @NotNull List<String> importPaths) {
        PsiDirectory dir = file.getContainingDirectory();
        if (dir == null || dir.getVirtualFile() == null) {
            return;
        }
        Project project = file.getProject();
        for (String path : importPaths) {
            VirtualFile vf = dir.getVirtualFile().findFileByRelativePath(path);
            if (vf == null) {
                continue;
            }
            PsiFile imp = PsiManager.getInstance(project).findFile(vf);
            if (imp == null || !(imp.getFileType() instanceof ApiFileType)) {
                continue;
            }
            ApiIndex imported = buildInternal(imp, false);
            for (String name : imported.structNames()) {
                names.add(name);
                StructNameNode node = imported.structNode(name);
                if (node != null) {
                    structMap.putIfAbsent(name, node);
                }
            }
        }
    }

    private static void walk(@Nullable PsiElement element, @NotNull java.util.function.Consumer<ASTNode> fn) {
        if (element == null) {
            return;
        }
        ASTNode node = element.getNode();
        if (node != null) {
            fn.accept(node);
        }
        for (PsiElement child : element.getChildren()) {
            walk(child, fn);
        }
    }

    @NotNull
    private static List<ServiceNode> findServices(@NotNull PsiElement root) {
        Set<ServiceNode> seen = new HashSet<>();
        walk(root, node -> {
            PsiElement psi = node.getPsi();
            if (psi instanceof ServiceNode) {
                seen.add((ServiceNode) psi);
            }
        });
        return new ArrayList<>(seen);
    }

    private static int firstOffset(@NotNull PsiFile file) {
        PsiElement el = file.getFirstChild();
        while (el != null) {
            if (!(el instanceof com.intellij.psi.PsiWhiteSpace)) {
                String t = el.getText();
                if (t != null && !t.trim().isEmpty()) {
                    return el.getTextRange().getStartOffset();
                }
            }
            el = el.getNextSibling();
        }
        return 0;
    }

    @NotNull
    public static final ApiIndex EMPTY = new ApiIndex(
            Collections.emptySet(), Collections.emptyMap(),
            Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), 0);

    public boolean isEmpty() {
        return structNames.isEmpty() && handlers.isEmpty() && issues.isEmpty();
    }
}
