package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItemDebugExif.DebugExifThumbnailViewHolder e;

    public /* synthetic */ o(DetailsItemDebugExif.DebugExifThumbnailViewHolder debugExifThumbnailViewHolder, int i2) {
        this.d = i2;
        this.e = debugExifThumbnailViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        DetailsItemDebugExif.DebugExifThumbnailViewHolder debugExifThumbnailViewHolder = this.e;
        switch (i2) {
            case 0:
                debugExifThumbnailViewHolder.saveBitmap();
                return;
            default:
                debugExifThumbnailViewHolder.lambda$bind$1();
                return;
        }
    }
}
