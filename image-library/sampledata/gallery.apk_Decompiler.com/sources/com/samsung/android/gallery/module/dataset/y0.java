package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y0 implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3003a;
    public final /* synthetic */ MediaDataTimeline b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f3004c;
    public final /* synthetic */ CountDownLatch d;

    public /* synthetic */ y0(MediaDataTimeline mediaDataTimeline, Cursor[] cursorArr, CountDownLatch countDownLatch, int i2) {
        this.f3003a = i2;
        this.b = mediaDataTimeline;
        this.f3004c = cursorArr;
        this.d = countDownLatch;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f3003a) {
            case 0:
                return this.b.lambda$initExtraTable$3(this.f3004c, this.d, jobContext);
            case 1:
                return this.b.lambda$initExtraTable$4(this.f3004c, this.d, jobContext);
            case 2:
                return this.b.lambda$initExtraTable$1(this.f3004c, this.d, jobContext);
            default:
                return this.b.lambda$initExtraTable$2(this.f3004c, this.d, jobContext);
        }
    }
}
