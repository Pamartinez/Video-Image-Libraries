package com.samsung.android.gallery.app.ui.list.sharings.pictures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesPresenter e;

    public /* synthetic */ i(SharingPicturesPresenter sharingPicturesPresenter, int i2) {
        this.d = i2;
        this.e = sharingPicturesPresenter;
    }

    public final void run() {
        int i2 = this.d;
        SharingPicturesPresenter sharingPicturesPresenter = this.e;
        switch (i2) {
            case 0:
                sharingPicturesPresenter.lambda$onViewResume$3();
                return;
            default:
                sharingPicturesPresenter.updateHeaderItemCount();
                return;
        }
    }
}
