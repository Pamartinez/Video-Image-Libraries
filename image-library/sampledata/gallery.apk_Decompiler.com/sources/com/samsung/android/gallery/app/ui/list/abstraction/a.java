package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2526a;
    public final /* synthetic */ int b;

    public /* synthetic */ a(String str, int i2) {
        this.f2526a = str;
        this.b = i2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return AbsListFragment.lambda$savePinchDepth$1(this.f2526a, this.b, jobContext);
    }
}
