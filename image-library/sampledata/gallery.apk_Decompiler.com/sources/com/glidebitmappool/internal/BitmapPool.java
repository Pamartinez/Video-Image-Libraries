package com.glidebitmappool.internal;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BitmapPool {
    void clearMemory();

    Bitmap get(int i2, int i7, Bitmap.Config config);

    Bitmap get(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace);

    void put(Bitmap bitmap);

    void trimMemory(int i2);
}
