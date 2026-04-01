package com.samsung.android.gallery.app.ui.list.albums.virtual;

import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VirtualAlbumPicturesPresenter f2532a;
    public final /* synthetic */ GalleryToolbar b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ CharSequence f2533c;

    public /* synthetic */ f(VirtualAlbumPicturesPresenter virtualAlbumPicturesPresenter, GalleryToolbar galleryToolbar, CharSequence charSequence) {
        this.f2532a = virtualAlbumPicturesPresenter;
        this.b = galleryToolbar;
        this.f2533c = charSequence;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2532a.lambda$updateSubTitle$3(this.b, this.f2533c, jobContext);
    }
}
