package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ThumbnailLoadedListener, ThumbnailInterrupter {
    public final /* synthetic */ SelectionViewAdapter d;
    public final /* synthetic */ SelectionViewHolder e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ int g;

    public /* synthetic */ d(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2) {
        this.d = selectionViewAdapter;
        this.e = selectionViewHolder;
        this.f = mediaItem;
        this.g = i2;
    }

    public boolean isInterrupted() {
        return this.d.lambda$loadOrDecoding$6(this.e, this.f, this.g);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$loadOrDecoding$5(this.e, this.f, this.g, bitmap, uniqueKey, thumbKind);
    }
}
