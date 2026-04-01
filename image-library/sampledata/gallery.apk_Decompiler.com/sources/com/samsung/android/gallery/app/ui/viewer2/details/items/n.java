package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ DetailsItemDebugExif.DebugExifThumbnailViewHolder d;
    public final /* synthetic */ String e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ n(DetailsItemDebugExif.DebugExifThumbnailViewHolder debugExifThumbnailViewHolder, String str, MediaItem mediaItem) {
        this.d = debugExifThumbnailViewHolder;
        this.e = str;
        this.f = mediaItem;
    }

    public final void run() {
        this.d.lambda$bind$2(this.e, this.f);
    }
}
