package com.samsung.android.gallery.module.dataset;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesV5;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q0 implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2992a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2993c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Serializable e;

    public /* synthetic */ q0(int i2, Serializable serializable, Object obj, Object obj2, Object obj3) {
        this.f2992a = i2;
        this.f2993c = obj;
        this.d = obj2;
        this.e = serializable;
        this.b = obj3;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2992a) {
            case 0:
                return ((MediaDataStoriesV5) this.f2993c).lambda$swap$1((MediaDataStoriesV5.DataInfo) this.d, (AtomicInteger) this.e, (CountDownLatch) this.b, jobContext);
            case 1:
                return ((MediaDataTimeline) this.f2993c).lambda$swapClusterTables$11((DefaultTable[]) this.d, (Cursor[]) this.e, (CountDownLatch) this.b, jobContext);
            default:
                return ((LocalAlbumDBUpdater) this.f2993c).lambda$updateOnUi$0((Context) this.d, (ArrayList) this.e, (ArrayList) this.b, jobContext);
        }
    }
}
