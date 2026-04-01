package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CodecTrace {
    void beginSession(String str);

    void endSession(String str, Bitmap bitmap, BitmapFactory.Options options);
}
