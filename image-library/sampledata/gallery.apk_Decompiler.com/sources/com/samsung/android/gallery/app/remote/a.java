package com.samsung.android.gallery.app.remote;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Slideshow f2512a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ a(Slideshow slideshow, MediaItem mediaItem) {
        this.f2512a = slideshow;
        this.b = mediaItem;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2512a.lambda$prepareBitmap$1(this.b, jobContext);
    }
}
