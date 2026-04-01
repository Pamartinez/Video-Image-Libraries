package com.samsung.android.gallery.app.ui.list.albums.virtual;

import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesPresenter;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VirtualAlbumPicturesPresenter.VirtualAlbumPicturesMenuUpdater f2534a;

    public /* synthetic */ g(VirtualAlbumPicturesPresenter.VirtualAlbumPicturesMenuUpdater virtualAlbumPicturesMenuUpdater) {
        this.f2534a = virtualAlbumPicturesMenuUpdater;
    }

    public final boolean getAsBoolean() {
        return this.f2534a.lambda$updateOptionsMenuAction$0();
    }
}
