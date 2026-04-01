package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface IOCRecognizer {
    void cancel();

    void destroy();

    boolean detect(Bitmap bitmap, OCRResult oCRResult);

    boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr);

    boolean detectBlock(Bitmap bitmap, Point[] pointArr);

    boolean detectText(Bitmap bitmap);

    String getVersion();

    boolean hasText(Bitmap bitmap);

    boolean recognize(Bitmap bitmap, OCRResult oCRResult);

    boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult);

    boolean recognizeDetectedLines(Bitmap bitmap, OCRResult oCRResult);
}
