package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s0 implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Cursor f2994a;

    public /* synthetic */ s0(Cursor cursor) {
        this.f2994a = cursor;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return CacheProviderHelper.cacheCursor("location://search/fileList/Category/Stories", this.f2994a);
    }
}
