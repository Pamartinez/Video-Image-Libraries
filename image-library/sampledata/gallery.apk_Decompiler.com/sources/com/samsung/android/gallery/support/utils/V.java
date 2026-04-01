package com.samsung.android.gallery.support.utils;

import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class V implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ CountDownLatch f;

    public /* synthetic */ V(Runnable runnable, CountDownLatch countDownLatch, int i2) {
        this.d = i2;
        this.e = runnable;
        this.f = countDownLatch;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                TimeoutWorker.lambda$post$0(this.e, this.f);
                return;
            default:
                TimeoutWorker.lambda$post$1(this.e, this.f);
                return;
        }
    }
}
