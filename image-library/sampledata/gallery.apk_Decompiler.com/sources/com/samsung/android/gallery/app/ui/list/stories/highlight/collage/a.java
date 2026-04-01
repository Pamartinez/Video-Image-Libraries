package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CollageAdapter e;
    public final /* synthetic */ CollageViewHolder f;
    public final /* synthetic */ MediaItem g;

    public /* synthetic */ a(CollageAdapter collageAdapter, CollageViewHolder collageViewHolder, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = collageAdapter;
        this.f = collageViewHolder;
        this.g = mediaItem;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                Bitmap bitmap2 = bitmap;
                MediaItem mediaItem = this.g;
                Bitmap bitmap3 = bitmap2;
                UniqueKey uniqueKey2 = uniqueKey;
                this.e.lambda$onBindViewHolder$0(this.f, mediaItem, bitmap3, uniqueKey2, thumbKind);
                return;
            default:
                this.e.lambda$onBindViewHolder$1(this.f, this.g, bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
