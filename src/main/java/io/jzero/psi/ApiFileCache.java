package io.jzero.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import io.jzero.antlr4.ApiParser;
import io.jzero.navigation.HandlerLogicResolver;
import io.jzero.parser.ApiParserDefinition;
import io.jzero.psi.nodes.ApiRootNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Per-api-file snapshot built in a single PSI walk.
 * All hot paths (annotator, line markers, completion) must read from here.
 */
public final class ApiFileCache {

    private static final Key<ApiFileCache> CACHE_KEY = Key.create("jzero.api.cache");
    private static final Key<Long> CACHE_STAMP_KEY = Key.create("jzero.api.cache.stamp");
    private static final Key<Integer> FIRST_OFFSET_KEY = Key.create("jzero.api.firstOffset");
    private static final Key<Long> FIRST_OFFSET_STAMP_KEY = Key.create("jzero.api.firstOffset.stamp");

    private final Set<String> structNames;
    private final Set<String> dupHandlers;
    private final Set<String> dupStructs;

    private final Map<String, Boolean> handlerResolved = new HashMap<>();
    private Boolean typesGoExists;

    private ApiFileCache(@NotNull Set<String> structNames,
                         @NotNull Set<String> dupHandlers,
                         @NotNull Set<String> dupStructs) {
        this.structNames = structNames;
        this.dupHandlers = dupHandlers;
        this.dupStructs = dupStructs;
    }

    @NotNull
    public static ApiFileCache of(@NotNull PsiFile file) {
        long stamp = file.getModificationStamp();
        Long cachedStamp = file.getUserData(CACHE_STAMP_KEY);
        ApiFileCache cached = file.getUserData(CACHE_KEY);
        if (cached != null && cachedStamp != null && cachedStamp == stamp) {
            return cached;
        }
        ApiFileCache built = build(file);
        file.putUserData(CACHE_KEY, built);
        file.putUserData(CACHE_STAMP_KEY, stamp);
        return built;
    }

    /** Offset of first non-whitespace leaf; used by line markers to avoid O(n²) scans. */
    public static int firstContentOffset(@NotNull PsiFile file) {
        long stamp = file.getModificationStamp();
        Long cachedStamp = file.getUserData(FIRST_OFFSET_STAMP_KEY);
        Integer cached = file.getUserData(FIRST_OFFSET_KEY);
        if (cached != null && cachedStamp != null && cachedStamp == stamp) {
            return cached;
        }
        int offset = computeFirstContentOffset(file);
        file.putUserData(FIRST_OFFSET_KEY, offset);
        file.putUserData(FIRST_OFFSET_STAMP_KEY, stamp);
        return offset;
    }

    @NotNull
    public Set<String> structNames() {
        return structNames;
    }

    public boolean hasStruct(@Nullable String name) {
        return name != null && !name.isEmpty() && structNames.contains(name);
    }

    public boolean isDupHandler(@Nullable String name) {
        return name != null && dupHandlers.contains(name);
    }

    public boolean isDupStruct(@Nullable String name) {
        return name != null && dupStructs.contains(name);
    }

    /** Lazy; at most one filesystem lookup per handler name per file revision. */
    public boolean handlerResolvable(@NotNull PsiElement ctx, @NotNull String handlerName) {
        String key = handlerName.trim();
        if (key.isEmpty()) {
            return false;
        }
        Boolean hit = handlerResolved.get(key);
        if (hit != null) {
            return hit;
        }
        boolean ok = HandlerLogicResolver.findLogicFile(ctx, key) != null;
        handlerResolved.put(key, ok);
        return ok;
    }

    public boolean typesGoExists(@NotNull PsiElement ctx) {
        if (typesGoExists != null) {
            return typesGoExists;
        }
        typesGoExists = TypesGoLocator.exists(ctx);
        return typesGoExists;
    }

    @NotNull
    private static ApiFileCache build(@NotNull PsiFile file) {
        PsiElement root = file.getFirstChild();
        if (!(root instanceof ApiRootNode)) {
            return empty();
        }

        IElementType structRule = ApiParserDefinition.rule(ApiParser.RULE_structNameId);
        IElementType handlerRule = ApiParserDefinition.rule(ApiParser.RULE_handlerValue);

        Map<String, Integer> structCounts = new HashMap<>();
        Map<String, Integer> handlerCounts = new HashMap<>();
        Set<String> structs = new HashSet<>();

        walk(root, node -> {
            IElementType type = node.getElementType();
            String text = node.getText();
            if (text == null || text.isEmpty()) {
                return;
            }
            if (type.equals(structRule)) {
                structs.add(text);
                structCounts.merge(text, 1, Integer::sum);
            } else if (type.equals(handlerRule)) {
                handlerCounts.merge(text, 1, Integer::sum);
            }
        });

        return new ApiFileCache(
                Collections.unmodifiableSet(structs),
                dups(handlerCounts),
                dups(structCounts)
        );
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
    private static Set<String> dups(@NotNull Map<String, Integer> counts) {
        Set<String> dup = new HashSet<>();
        counts.forEach((k, c) -> {
            if (c > 1) {
                dup.add(k);
            }
        });
        return dup;
    }

    private static int computeFirstContentOffset(@NotNull PsiFile file) {
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
    private static ApiFileCache empty() {
        return new ApiFileCache(Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
    }
}
