package com.samsung.android.gallery.support.utils;

import java.util.concurrent.CountDownLatch;

/* renamed from: com.samsung.android.gallery.support.utils.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0684w implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ LatchBuilder e;
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ CountDownLatch g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f3189h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f3190i;

    public /* synthetic */ C0684w(LatchBuilder latchBuilder, Runnable runnable, CountDownLatch countDownLatch, String str, long j2) {
        this.e = latchBuilder;
        this.f = runnable;
        this.g = countDownLatch;
        this.f3189h = str;
        this.f3190i = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                LatchBuilder.b(this.e, this.f, this.g, this.f3189h, this.f3190i);
                return;
            default:
                this.e.lambda$start$2(this.f, this.g, this.f3189h, this.f3190i);
                return;
        }
    }

    public /* synthetic */ C0684w(LatchBuilder latchBuilder, String str, Runnable runnable, CountDownLatch countDownLatch, long j2) {
        this.e = latchBuilder;
        this.f3189h = str;
        this.f = runnable;
        this.g = countDownLatch;
        this.f3190i = j2;
    }
}
