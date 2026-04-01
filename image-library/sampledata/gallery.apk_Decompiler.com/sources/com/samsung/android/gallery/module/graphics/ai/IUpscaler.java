package com.samsung.android.gallery.module.graphics.ai;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IUpscaler {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Quality {
        NORMAL,
        ULTRA
    }

    Bitmap upscale(Bitmap bitmap, int i2, Quality quality);
}
