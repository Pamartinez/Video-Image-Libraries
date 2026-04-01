package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends z implements ScheduledExecutorService {
    public final ScheduledExecutorService e;

    public C(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.e = scheduledExecutorService;
    }

    public final ScheduledFuture schedule(Callable callable, long j2, TimeUnit timeUnit) {
        G g = new G(callable);
        return new A(g, this.e.schedule(g, j2, timeUnit));
    }

    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
        B b = new B(runnable);
        return new A(b, this.e.scheduleAtFixedRate(b, j2, j3, timeUnit));
    }

    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
        B b = new B(runnable);
        return new A(b, this.e.scheduleWithFixedDelay(b, j2, j3, timeUnit));
    }

    public final ScheduledFuture schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
        G g = new G(Executors.callable(runnable, (Object) null));
        return new A(g, this.e.schedule(g, j2, timeUnit));
    }
}
