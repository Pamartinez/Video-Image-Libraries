package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2947a;
    public final /* synthetic */ MediaData.OnDataReadListener b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2948c;
    public final /* synthetic */ MediaDataCursor d;

    public /* synthetic */ J(int i2, int i7, MediaData.OnDataReadListener onDataReadListener, MediaDataCursor mediaDataCursor) {
        this.f2947a = i7;
        this.d = mediaDataCursor;
        this.b = onDataReadListener;
        this.f2948c = i2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2947a) {
            case 0:
                return ((MediaDataMdeSpace) this.d).lambda$readAsync$1(this.b, this.f2948c, jobContext);
            default:
                return ((MediaDataStoriesBase) this.d).lambda$readAsync$0(this.b, this.f2948c, jobContext);
        }
    }
}
