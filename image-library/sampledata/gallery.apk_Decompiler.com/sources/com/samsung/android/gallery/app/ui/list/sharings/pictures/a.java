package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ SharingCoverView d;

    public /* synthetic */ a(SharingCoverView sharingCoverView) {
        this.d = sharingCoverView;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$bindImageToView$0(bitmap, uniqueKey, thumbKind);
    }
}
