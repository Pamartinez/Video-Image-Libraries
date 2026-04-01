package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B0 implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2939a;
    public final /* synthetic */ MediaDataTimeline b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f2940c;
    public final /* synthetic */ ClusterTable[] d;
    public final /* synthetic */ CountDownLatch e;

    public /* synthetic */ B0(MediaDataTimeline mediaDataTimeline, Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch, int i2) {
        this.f2939a = i2;
        this.b = mediaDataTimeline;
        this.f2940c = cursorArr;
        this.d = clusterTableArr;
        this.e = countDownLatch;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2939a) {
            case 0:
                return this.b.lambda$swapClusterTables$9(this.f2940c, this.d, this.e, jobContext);
            default:
                return this.b.lambda$swapClusterTables$10(this.f2940c, this.d, this.e, jobContext);
        }
    }
}
