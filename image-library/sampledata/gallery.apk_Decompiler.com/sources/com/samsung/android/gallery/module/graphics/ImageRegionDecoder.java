package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ImageRegionDecoder {
    Bitmap decodeRegion(Rect rect, BitmapOptions bitmapOptions);

    int getHeight();

    int getWidth();

    boolean isRecycled();

    void recycle();
}
