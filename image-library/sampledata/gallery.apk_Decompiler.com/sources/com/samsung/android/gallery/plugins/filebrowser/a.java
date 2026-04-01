package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BitmapLoader f3093a;
    public final /* synthetic */ ZipEntryHolder b;

    public /* synthetic */ a(BitmapLoader bitmapLoader, ZipEntryHolder zipEntryHolder) {
        this.f3093a = bitmapLoader;
        this.b = zipEntryHolder;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f3093a.lambda$decodeThumbnail$3(this.b, jobContext);
    }
}
