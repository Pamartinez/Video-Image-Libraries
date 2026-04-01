package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ CountDownLatch g;

    public /* synthetic */ f(long j2, ArrayList arrayList, CountDownLatch countDownLatch, int i2) {
        this.d = i2;
        this.e = j2;
        this.f = arrayList;
        this.g = countDownLatch;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SimpleThreadPool.getInstance().execute(new f(this.e, this.f, this.g, 1));
                return;
            default:
                ConnectionWrap.lambda$scanFiles$4(this.e, this.f, this.g);
                return;
        }
    }
}
