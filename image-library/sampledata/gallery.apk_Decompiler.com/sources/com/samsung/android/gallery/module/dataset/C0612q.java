package com.samsung.android.gallery.module.dataset;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* renamed from: com.samsung.android.gallery.module.dataset.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0612q implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;

    public /* synthetic */ C0612q(int i2) {
        this.d = i2;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                MediaDataCollection.lambda$preloadThumbnail$5(bitmap, uniqueKey, thumbKind);
                return;
            case 1:
                MediaDataCursor.lambda$preloadThumbnail$2(bitmap, uniqueKey, thumbKind);
                return;
            case 2:
                MediaDataEntire.lambda$preloadThumbnail$1(bitmap, uniqueKey, thumbKind);
                return;
            case 3:
                MediaDataMdeSpace.lambda$preloadThumbnail$5(bitmap, uniqueKey, thumbKind);
                return;
            case 4:
                MediaDataRemasterV2.lambda$preloadThumbnail$12(bitmap, uniqueKey, thumbKind);
                return;
            case 5:
                MediaDataStoriesBase.lambda$preloadThumbnail$4(bitmap, uniqueKey, thumbKind);
                return;
            default:
                MediaDataStoriesV7.lambda$preloadThumbnail$12(bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
