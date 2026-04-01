package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.samsung.android.gallery.module.dataset.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0615u implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2997a;
    public final /* synthetic */ Cursor[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DefaultTable[] f2998c;
    public final /* synthetic */ CountDownLatch d;

    public /* synthetic */ C0615u(int i2, Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch) {
        this.f2997a = i2;
        this.b = cursorArr;
        this.f2998c = clusterTableArr;
        this.d = countDownLatch;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2997a) {
            case 0:
                CountDownLatch countDownLatch = this.d;
                return MediaDataCursor.lambda$loadClusterTables$4(this.b, (ClusterTable[]) this.f2998c, countDownLatch, jobContext);
            case 1:
                CountDownLatch countDownLatch2 = this.d;
                return MediaDataCursor.lambda$loadClusterTables$3(this.b, (ClusterTable[]) this.f2998c, countDownLatch2, jobContext);
            default:
                return MediaDataTimeline.lambda$swapClusterTables$12(this.f2998c, this.b, this.d, jobContext);
        }
    }

    public /* synthetic */ C0615u(DefaultTable[] defaultTableArr, Cursor[] cursorArr, CountDownLatch countDownLatch) {
        this.f2997a = 2;
        this.f2998c = defaultTableArr;
        this.b = cursorArr;
        this.d = countDownLatch;
    }
}
