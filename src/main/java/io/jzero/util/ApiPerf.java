package io.jzero.util;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.concurrency.AppExecutorUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/** Hot-path profiler — flush to /tmp/jzero-api-perf.log every second. */
public final class ApiPerf {

    private static final Logger LOG = Logger.getInstance(ApiPerf.class);
    private static final Path LOG_FILE = Path.of("/tmp/jzero-api-perf.log");
    private static final long SLOW_NS = 5_000_000L;

    private static final ConcurrentHashMap<String, AtomicLong> COUNTS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicLong> TOTAL_NS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicLong> SLOW = new ConcurrentHashMap<>();

    private static volatile boolean started;

    private ApiPerf() {
    }

    public static void start() {
        if (started) {
            return;
        }
        synchronized (ApiPerf.class) {
            if (started) {
                return;
            }
            try {
                Files.writeString(LOG_FILE, "=== jzero api perf " + System.currentTimeMillis() + " ===\n",
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ignored) {
            }
            AppExecutorUtil.getAppScheduledExecutorService()
                    .scheduleWithFixedDelay(ApiPerf::flush, 1, 1, TimeUnit.SECONDS);
            started = true;
        }
    }

    public static void inc(@NotNull String key) {
        ensure();
        COUNTS.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
    }

    public static void run(@NotNull String key, @NotNull Runnable task) {
        ensure();
        long start = System.nanoTime();
        try {
            task.run();
        } finally {
            record(key, System.nanoTime() - start);
        }
    }

    public static <T> T call(@NotNull String key, @NotNull java.util.function.Supplier<T> task) {
        ensure();
        long start = System.nanoTime();
        try {
            return task.get();
        } finally {
            record(key, System.nanoTime() - start);
        }
    }

    private static void ensure() {
        if (!started) {
            start();
        }
    }

    private static void record(@NotNull String key, long ns) {
        COUNTS.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
        TOTAL_NS.computeIfAbsent(key, k -> new AtomicLong()).addAndGet(ns);
        if (ns >= SLOW_NS) {
            SLOW.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
            String line = String.format("SLOW %s %.2fms thread=%s%n", key, ns / 1_000_000.0,
                    Thread.currentThread().getName());
            LOG.warn(line.trim());
            append(line);
        }
    }

    private static void flush() {
        if (COUNTS.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(System.currentTimeMillis()).append(" ---\n");
        for (Map.Entry<String, AtomicLong> e : COUNTS.entrySet()) {
            String k = e.getKey();
            long cnt = e.getValue().get();
            long ns = TOTAL_NS.getOrDefault(k, new AtomicLong()).get();
            long slow = SLOW.getOrDefault(k, new AtomicLong()).get();
            sb.append(String.format("%s count=%d totalMs=%.1f avgUs=%.0f slow=%d%n",
                    k, cnt, ns / 1_000_000.0, cnt > 0 ? (ns / cnt) / 1000.0 : 0, slow));
        }
        sb.append('\n');
        append(sb.toString());
        COUNTS.clear();
        TOTAL_NS.clear();
        SLOW.clear();
    }

    private static void append(@NotNull String line) {
        try {
            Files.writeString(LOG_FILE, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOG.warn("perf log write failed: " + e.getMessage());
        }
    }
}
