package com.samsung.android.sdk.pen.ocr;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpenITypeClassifier {
    void close();

    boolean isHandwrittenImage(Bitmap bitmap);

    boolean isPrintedImage(Bitmap bitmap);
}
