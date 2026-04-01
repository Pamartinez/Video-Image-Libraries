package com.samsung.android.sdk.pen.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpenIOcrRecognizer {
    void cancelRequest();

    void close();

    SpenOcrError detect(Bitmap bitmap);

    SpenOcrError detect(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    String getVersion();

    SpenOcrError recognize(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    SpenOcrError recognizeBlockAt(Bitmap bitmap, Point point, boolean z, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    SpenOcrError recognize_line(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrLineData spenOcrLineData);

    void setConfiguration(String str, String str2);

    void setConfiguration(String[] strArr, String[] strArr2);
}
