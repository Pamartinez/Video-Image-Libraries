package com.samsung.android.gallery.module.dataset;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* renamed from: com.samsung.android.gallery.module.dataset.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0604i implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataAlbum e;
    public final /* synthetic */ Cloneable f;

    public /* synthetic */ C0604i(MediaDataAlbum mediaDataAlbum, Cloneable cloneable, int i2) {
        this.d = i2;
        this.e = mediaDataAlbum;
        this.f = cloneable;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                this.e.lambda$preloadThumbnail$11((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 1:
                this.e.lambda$preloadThumbnail$12((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            default:
                this.e.lambda$preloadThumbnail$10((MediaItem[]) this.f, bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
