package com.samsung.android.gallery.app.ui.viewer2.selection;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SelectionViewAdapter f2605a;
    public final /* synthetic */ MediaItem b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2606c;
    public final /* synthetic */ long d;

    public /* synthetic */ c(SelectionViewAdapter selectionViewAdapter, MediaItem mediaItem, int i2, long j2) {
        this.f2605a = selectionViewAdapter;
        this.b = mediaItem;
        this.f2606c = i2;
        this.d = j2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2605a.lambda$onPositionEstimated$7(this.b, this.f2606c, this.d, jobContext);
    }
}
