package io.jzero.index;

import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiFile;
import io.jzero.language.ApiFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Per-file index with stale-while-revalidate: typing returns last index, rebuild is throttled.
 */
public final class ApiIndexHolder {

    private static final Key<Entry> KEY = Key.create("jzero.api.index");

    private static final class Entry {
        ApiIndex index;
        long stamp;
    }

    private ApiIndexHolder() {
    }

    @NotNull
    public static ApiIndex get(@NotNull PsiFile file, boolean syncIfMissing) {
        if (!(file.getFileType() instanceof ApiFileType)) {
            return ApiIndex.EMPTY;
        }
        long stamp = file.getModificationStamp();
        Entry entry = file.getUserData(KEY);
        if (entry != null && entry.index != null && entry.stamp == stamp) {
            return entry.index;
        }
        if (entry != null && entry.index != null) {
            ApiWorkQueue.get(file.getProject()).schedule(file);
            return entry.index;
        }
        if (syncIfMissing) {
            return rebuild(file);
        }
        ApiWorkQueue.get(file.getProject()).schedule(file);
        return ApiIndex.EMPTY;
    }

    public static boolean isFresh(@NotNull PsiFile file) {
        Entry entry = file.getUserData(KEY);
        return entry != null && entry.index != null && entry.stamp == file.getModificationStamp();
    }

    @NotNull
    public static ApiIndex rebuild(@NotNull PsiFile file) {
        Entry entry = file.getUserData(KEY);
        if (entry == null) {
            entry = new Entry();
            file.putUserData(KEY, entry);
        }
        entry.index = ApiIndex.build(file);
        entry.stamp = file.getModificationStamp();
        return entry.index;
    }

    public static void invalidate(@NotNull PsiFile file) {
        file.putUserData(KEY, null);
    }
}
