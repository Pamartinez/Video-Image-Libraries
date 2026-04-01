package com.samsung.android.gallery.app.ui.list.trash;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ TrashPresenter d;
    public final /* synthetic */ Toolbar e;
    public final /* synthetic */ GalleryAppBarLayout f;
    public final /* synthetic */ int[] g;

    public /* synthetic */ e(TrashPresenter trashPresenter, Toolbar toolbar, GalleryAppBarLayout galleryAppBarLayout, int[] iArr) {
        this.d = trashPresenter;
        this.e = toolbar;
        this.f = galleryAppBarLayout;
        this.g = iArr;
    }

    public final void run() {
        this.d.lambda$updateSubTitle$5(this.e, this.f, this.g);
    }
}
