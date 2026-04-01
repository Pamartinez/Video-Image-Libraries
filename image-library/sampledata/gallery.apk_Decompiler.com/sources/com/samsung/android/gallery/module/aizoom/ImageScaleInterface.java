package com.samsung.android.gallery.module.aizoom;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ImageScaleInterface {
    boolean createSession(int i2, String str);

    int detectScene(Bitmap bitmap);

    void endSession();

    Bitmap upscaleImage(Bitmap bitmap, int i2, Rect rect, int i7);
}
