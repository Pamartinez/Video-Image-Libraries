package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesPresenter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ SharingPicturesPresenter.SharingPicturesMenuUpdater d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ l(SharingPicturesPresenter.SharingPicturesMenuUpdater sharingPicturesMenuUpdater, boolean z) {
        this.d = sharingPicturesMenuUpdater;
        this.e = z;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateOptionsMenuAction$0(this.e, (MenuItem) obj);
    }
}
