package com.google.common.util.concurrent;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ThreadFactory f1559a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AtomicLong f1560c;
    public final /* synthetic */ Boolean d;

    public E(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool) {
        this.f1559a = threadFactory;
        this.b = str;
        this.f1560c = atomicLong;
        this.d = bool;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.f1559a.newThread(runnable);
        Objects.requireNonNull(newThread);
        String str = this.b;
        if (str != null) {
            AtomicLong atomicLong = this.f1560c;
            Objects.requireNonNull(atomicLong);
            newThread.setName(String.format(Locale.ROOT, str, new Object[]{Long.valueOf(atomicLong.getAndIncrement())}));
        }
        Boolean bool = this.d;
        if (bool != null) {
            newThread.setDaemon(bool.booleanValue());
        }
        return newThread;
    }
}
