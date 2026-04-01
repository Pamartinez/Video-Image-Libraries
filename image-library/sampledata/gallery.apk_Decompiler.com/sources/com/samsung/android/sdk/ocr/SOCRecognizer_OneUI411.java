package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenIOcrEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SOCRecognizer_OneUI411 extends SOCRecognizerBase implements IOCRecognizer {
    private static final String TAG = "SOCRecognizer_OneUI411";

    public SOCRecognizer_OneUI411(SpenIOcrEngine spenIOcrEngine, RecognizerParams recognizerParams) {
        super(spenIOcrEngine, recognizerParams);
        Log.i(TAG, "SpenRecognizer(SOCRecognizer_OneUI411) is created");
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        Log.i(TAG, "recognizeBlockAt is not supported at OneUI4.1, use recognize() instead!");
        return recognizeBase(bitmap, oCRResult);
    }
}
