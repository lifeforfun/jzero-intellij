package io.jzero;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import io.jzero.util.ApiPerf;
import org.jetbrains.annotations.NotNull;

/** Eager-load perf logger on project open. */
public class ApiPerfStartup implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        ApiPerf.start();
    }
}
