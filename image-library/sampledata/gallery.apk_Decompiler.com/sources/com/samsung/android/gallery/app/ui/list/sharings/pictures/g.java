package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SharingPicturesCoverViewHolder) this.e).lambda$bindImage$1((Bitmap) this.f);
                return;
            default:
                ((SharingPicturesPresenter) this.e).lambda$updateHeaderItemCount$4((int[]) this.f);
                return;
        }
    }
}
