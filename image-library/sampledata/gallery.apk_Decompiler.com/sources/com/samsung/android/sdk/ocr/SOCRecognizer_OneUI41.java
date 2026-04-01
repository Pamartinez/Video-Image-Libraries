package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenIOcrEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SOCRecognizer_OneUI41 extends SOCRecognizerBase implements IOCRecognizer {
    private static final String TAG = "SOCRecognizer_OneUI41";

    public SOCRecognizer_OneUI41(SpenIOcrEngine spenIOcrEngine, RecognizerParams recognizerParams) {
        super(spenIOcrEngine, recognizerParams);
        Log.i(TAG, "SpenRecognizer(SOCRecognizer_OneUI41) is created!");
    }

    public void cancel() {
        Log.w(TAG, "Cancel operation is not supported on SOCR OneUI41");
    }

    public void createMoireDetector(Context context, SpenIOcrEngine spenIOcrEngine) {
        Log.i(TAG, "Moire Detector is not Used");
    }

    public void destroyMoireDetector() {
        Log.i(TAG, "Moire Detector is not Used");
    }

    public void detectMoire(Bitmap bitmap) {
        Log.i(TAG, "Moire Detector is not Used");
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        Log.i(TAG, "recognizeBlockAt is not supported at OneUI4.1, use recognize() instead!");
        return recognizeBase(bitmap, oCRResult);
    }
}
