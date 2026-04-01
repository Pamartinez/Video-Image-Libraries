package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SharingCoverView d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ UniqueKey f;
    public final /* synthetic */ ThumbKind g;

    public /* synthetic */ b(SharingCoverView sharingCoverView, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d = sharingCoverView;
        this.e = bitmap;
        this.f = uniqueKey;
        this.g = thumbKind;
    }

    public final void run() {
        this.d.lambda$bindImageToView$0(this.e, this.f, this.g);
    }
}
