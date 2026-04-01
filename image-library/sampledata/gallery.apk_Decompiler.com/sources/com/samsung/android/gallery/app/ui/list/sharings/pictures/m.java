package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesPresenter;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharingPicturesPresenter.SharingPicturesMenuUpdater f2551a;

    public /* synthetic */ m(SharingPicturesPresenter.SharingPicturesMenuUpdater sharingPicturesMenuUpdater) {
        this.f2551a = sharingPicturesMenuUpdater;
    }

    public final boolean getAsBoolean() {
        return this.f2551a.lambda$updateOptionsMenuAction$2();
    }
}
