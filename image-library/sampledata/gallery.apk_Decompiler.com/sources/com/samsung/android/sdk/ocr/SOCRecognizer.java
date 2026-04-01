package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenIOcrEngine;
import com.samsung.android.sdk.pen.ocr.SpenOcrError;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SOCRecognizer extends SOCRecognizerBase implements IOCRecognizer {
    private static final String TAG = "SOCRecognizer";

    public SOCRecognizer(SpenIOcrEngine spenIOcrEngine, RecognizerParams recognizerParams) {
        super(spenIOcrEngine, recognizerParams);
        Log.i(TAG, "SpenRecognizer(SOCRecognizer) is created");
    }

    public void cancel() {
        this.mRecognizer.cancelRequest();
    }

    public void destroy() {
        super.destroy();
        Log.i(TAG, "SOCRecognizer destroyed");
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        detectMoire(bitmap);
        return detectBase(bitmap, oCRResult);
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        detectMoire(bitmap);
        return recognizeBase(bitmap, oCRResult);
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        detectMoire(bitmap);
        SpenOcrError recognizeBlockAt = this.mRecognizer.recognizeBlockAt(bitmap, point, z, new SpenRecogConfig(this.mLanguageString), this);
        if (recognizeBlockAt != SpenOcrError.OE_Success) {
            Log.e(TAG, "SpenRecognizer::recognizeBlockAt() Failed! ErrorCode : " + recognizeBlockAt.toString());
            return false;
        }
        SOCRConverter.convertPage(this.mOcrPageData, oCRResult);
        return true;
    }
}
