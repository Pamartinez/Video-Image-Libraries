package com.samsung.android.gallery.app.ui.list.albums.virtual;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VirtualAlbumPicturesPresenter f2531a;
    public final /* synthetic */ Toolbar b;

    public /* synthetic */ d(VirtualAlbumPicturesPresenter virtualAlbumPicturesPresenter, Toolbar toolbar) {
        this.f2531a = virtualAlbumPicturesPresenter;
        this.b = toolbar;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2531a.lambda$updateToolbar$0(this.b, jobContext);
    }
}
