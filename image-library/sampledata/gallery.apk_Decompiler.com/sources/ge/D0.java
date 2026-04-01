package ge;

import He.F;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D0 implements ScheduledExecutorService, AutoCloseable {
    public final ScheduledExecutorService d;

    public D0(ScheduledExecutorService scheduledExecutorService) {
        F.n(scheduledExecutorService, "delegate");
        this.d = scheduledExecutorService;
    }

    public final boolean awaitTermination(long j2, TimeUnit timeUnit) {
        return this.d.awaitTermination(j2, timeUnit);
    }

    public final /* synthetic */ void close() {
        if (this != ForkJoinPool.commonPool() && !isTerminated()) {
            shutdown();
            throw null;
        }
    }

    public final void execute(Runnable runnable) {
        this.d.execute(runnable);
    }

    public final List invokeAll(Collection collection) {
        return this.d.invokeAll(collection);
    }

    public final Object invokeAny(Collection collection) {
        return this.d.invokeAny(collection);
    }

    public final boolean isShutdown() {
        return this.d.isShutdown();
    }

    public final boolean isTerminated() {
        return this.d.isTerminated();
    }

    public final ScheduledFuture schedule(Callable callable, long j2, TimeUnit timeUnit) {
        return this.d.schedule(callable, j2, timeUnit);
    }

    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
        return this.d.scheduleAtFixedRate(runnable, j2, j3, timeUnit);
    }

    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
        return this.d.scheduleWithFixedDelay(runnable, j2, j3, timeUnit);
    }

    public final void shutdown() {
        throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
    }

    public final List shutdownNow() {
        throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
    }

    public final Future submit(Callable callable) {
        return this.d.submit(callable);
    }

    public final List invokeAll(Collection collection, long j2, TimeUnit timeUnit) {
        return this.d.invokeAll(collection, j2, timeUnit);
    }

    public final Object invokeAny(Collection collection, long j2, TimeUnit timeUnit) {
        return this.d.invokeAny(collection, j2, timeUnit);
    }

    public final ScheduledFuture schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
        return this.d.schedule(runnable, j2, timeUnit);
    }

    public final Future submit(Runnable runnable) {
        return this.d.submit(runnable);
    }

    public final Future submit(Runnable runnable, Object obj) {
        return this.d.submit(runnable, obj);
    }
}
