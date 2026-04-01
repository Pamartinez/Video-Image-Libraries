package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((SharingHeaderViewDelegate) obj).lambda$dismissTipCard$5();
                return;
            case 1:
                ((SharingPicturesFragment.AnonymousClass1) obj).lambda$onDataChanged$0();
                return;
            case 2:
                ((ISharingPicturesView) obj).showAlbumLinkTip();
                return;
            default:
                ((SharingPicturesViewAdapter) obj).onGroupDataChangedOnUi();
                return;
        }
    }
}
