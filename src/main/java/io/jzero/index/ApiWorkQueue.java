package io.jzero.index;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.concurrency.AppExecutorUtil;
import io.jzero.language.ApiFileType;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Debounced index rebuild queue — coalesce rapid edits, never block EDT with full index walk.
 */
@Service(Service.Level.PROJECT)
public final class ApiWorkQueue {

    private static final long DEBOUNCE_MS = 800;

    private final Project project;
    private final ConcurrentHashMap<VirtualFile, ScheduledFuture<?>> pending = new ConcurrentHashMap<>();

    public ApiWorkQueue(@NotNull Project project) {
        this.project = project;
    }

    @NotNull
    public static ApiWorkQueue get(@NotNull Project project) {
        return project.getService(ApiWorkQueue.class);
    }

    public void schedule(@NotNull PsiFile file) {
        ApiPerf.inc("queue.schedule");
        VirtualFile vf = file.getVirtualFile();
        if (vf == null || !(file.getFileType() instanceof ApiFileType)) {
            return;
        }
        ScheduledFuture<?> prev = pending.get(vf);
        if (prev != null) {
            prev.cancel(false);
        }
        ScheduledFuture<?> task = AppExecutorUtil.getAppScheduledExecutorService().schedule(
                () -> runRebuild(vf),
                DEBOUNCE_MS,
                TimeUnit.MILLISECONDS
        );
        pending.put(vf, task);
    }

    private void runRebuild(@NotNull VirtualFile vf) {
        ApiPerf.run("queue.rebuild", () -> runRebuild0(vf));
    }

    private void runRebuild0(@NotNull VirtualFile vf) {
        pending.remove(vf);
        if (!vf.isValid() || project.isDisposed()) {
            return;
        }
        ApplicationManager.getApplication().invokeLater(() -> {
            if (project.isDisposed() || !vf.isValid()) {
                return;
            }
            ApplicationManager.getApplication().runReadAction(() -> {
                PsiFile psi = PsiManager.getInstance(project).findFile(vf);
                if (psi == null || !psi.isValid()) {
                    return;
                }
                ApiIndexHolder.rebuild(psi);
                PsiFile latest = psi;
                ApplicationManager.getApplication().invokeLater(() -> {
                    if (!latest.isValid() || project.isDisposed()) {
                        return;
                    }
                    DaemonCodeAnalyzer.getInstance(project).restart(latest);
                });
            });
        });
    }
}
