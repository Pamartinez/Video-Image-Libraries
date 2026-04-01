package com.glidebitmappool.internal;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface LruPoolStrategy {
    Bitmap get(int i2, int i7, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i2, int i7, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    Bitmap removeLast();
}
