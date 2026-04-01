package com.google.common.util.concurrent;

import F2.K;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends K implements ScheduledFuture, ListenableFuture, Future {
    public final q d;
    public final ScheduledFuture e;

    public A(q qVar, ScheduledFuture scheduledFuture) {
        this.d = qVar;
        this.e = scheduledFuture;
    }

    public final boolean a(boolean z) {
        return this.d.cancel(z);
    }

    public final void addListener(Runnable runnable, Executor executor) {
        this.d.addListener(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        boolean a7 = a(z);
        if (a7) {
            this.e.cancel(z);
        }
        return a7;
    }

    public final int compareTo(Object obj) {
        return this.e.compareTo((Delayed) obj);
    }

    public final Object delegate() {
        return this.d;
    }

    public final Object get() {
        return this.d.get();
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.e.getDelay(timeUnit);
    }

    public final boolean isCancelled() {
        return this.d.isCancelled();
    }

    public final boolean isDone() {
        return this.d.isDone();
    }

    public final Object get(long j2, TimeUnit timeUnit) {
        return this.d.get(j2, timeUnit);
    }
}
