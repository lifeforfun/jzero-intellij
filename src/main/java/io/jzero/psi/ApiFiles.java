package io.jzero.psi;

import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiFile;
import io.jzero.index.ApiIndex;
import io.jzero.index.ApiIndexHolder;
import org.jetbrains.annotations.NotNull;

public final class ApiFiles {

    private static final Key<Long> FIRST_OFFSET = Key.create("jzero.api.firstOff");
    private static final Key<Long> FIRST_OFFSET_STAMP = Key.create("jzero.api.firstOff.stamp");

    private ApiFiles() {
    }

    /** Latest index; may be stale while typing — never triggers sync rebuild on stale hit. */
    @NotNull
    public static ApiIndex index(@NotNull PsiFile file) {
        return ApiIndexHolder.get(file, true);
    }

    /** For daemon on-the-fly: skip sync build, use stale or empty. */
    @NotNull
    public static ApiIndex indexIfReady(@NotNull PsiFile file) {
        if (ApiIndexHolder.isFresh(file)) {
            return ApiIndexHolder.get(file, false);
        }
        ApiIndex stale = ApiIndexHolder.get(file, false);
        return stale.isEmpty() ? ApiIndex.EMPTY : stale;
    }

    public static int firstContentOffset(@NotNull PsiFile file) {
        long stamp = file.getModificationStamp();
        Long cached = file.getUserData(FIRST_OFFSET);
        Long cachedStamp = file.getUserData(FIRST_OFFSET_STAMP);
        if (cached != null && cachedStamp != null && cachedStamp == stamp) {
            return cached.intValue();
        }
        int off = ApiIndex.computeFirstContentOffset(file);
        file.putUserData(FIRST_OFFSET, (long) off);
        file.putUserData(FIRST_OFFSET_STAMP, stamp);
        return off;
    }
}
