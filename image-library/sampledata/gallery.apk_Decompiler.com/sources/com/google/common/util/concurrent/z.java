package com.google.common.util.concurrent;

import G.a;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class z extends AbstractExecutorService implements y, AutoCloseable {
    public final ExecutorService d;

    public z(ExecutorService executorService) {
        executorService.getClass();
        this.d = executorService;
    }

    public final ListenableFuture a(a aVar) {
        return (ListenableFuture) super.submit(aVar);
    }

    public final boolean awaitTermination(long j2, TimeUnit timeUnit) {
        return this.d.awaitTermination(j2, timeUnit);
    }

    public final /* synthetic */ void close() {
        boolean isTerminated;
        if (this != ForkJoinPool.commonPool() && !(isTerminated = isTerminated())) {
            shutdown();
            boolean z = false;
            while (!isTerminated) {
                try {
                    isTerminated = awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public final void execute(Runnable runnable) {
        this.d.execute(runnable);
    }

    public final boolean isShutdown() {
        return this.d.isShutdown();
    }

    public final boolean isTerminated() {
        return this.d.isTerminated();
    }

    public final RunnableFuture newTaskFor(Callable callable) {
        return new G(callable);
    }

    public final void shutdown() {
        this.d.shutdown();
    }

    public final List shutdownNow() {
        return this.d.shutdownNow();
    }

    public final Future submit(Runnable runnable) {
        return (ListenableFuture) super.submit(runnable);
    }

    public final String toString() {
        return super.toString() + "[" + this.d + "]";
    }

    public final RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return new G(Executors.callable(runnable, obj));
    }

    public final Future submit(Runnable runnable, Object obj) {
        return (ListenableFuture) super.submit(runnable, obj);
    }

    public final Future submit(Callable callable) {
        return (ListenableFuture) super.submit(callable);
    }
}
