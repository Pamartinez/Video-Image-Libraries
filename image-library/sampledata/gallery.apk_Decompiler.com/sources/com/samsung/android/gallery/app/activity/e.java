package com.samsung.android.gallery.app.activity;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ThumbnailLoadedListener {
    public final /* synthetic */ GalleryActivityHandler.BottomMoveBarHandlerImpl d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ e(GalleryActivityHandler.BottomMoveBarHandlerImpl bottomMoveBarHandlerImpl, MediaItem mediaItem) {
        this.d = bottomMoveBarHandlerImpl;
        this.e = mediaItem;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$initView$0(this.e, bitmap, uniqueKey, thumbKind);
    }
}
