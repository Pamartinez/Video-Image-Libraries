package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* renamed from: com.google.common.util.concurrent.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0126e {
    public static final C0126e d = new C0126e();

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f1564a;
    public final Executor b;

    /* renamed from: c  reason: collision with root package name */
    public C0126e f1565c;

    public C0126e(Runnable runnable, Executor executor) {
        this.f1564a = runnable;
        this.b = executor;
    }

    public C0126e() {
        this.f1564a = null;
        this.b = null;
    }
}
