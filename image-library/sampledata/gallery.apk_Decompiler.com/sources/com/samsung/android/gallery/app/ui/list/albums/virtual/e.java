package com.samsung.android.gallery.app.ui.list.albums.virtual;

import androidx.appcompat.widget.Toolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ VirtualAlbumPicturesPresenter d;
    public final /* synthetic */ Toolbar e;
    public final /* synthetic */ String f;

    public /* synthetic */ e(VirtualAlbumPicturesPresenter virtualAlbumPicturesPresenter, Toolbar toolbar, String str) {
        this.d = virtualAlbumPicturesPresenter;
        this.e = toolbar;
        this.f = str;
    }

    public final void run() {
        this.d.lambda$updateSubTitle$2(this.e, this.f);
    }
}
