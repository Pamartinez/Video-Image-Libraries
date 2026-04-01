package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.sdk.ocr.RecognizerInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerInternal_Testing extends RecognizerInternal {
    private static final String TAG = "RecognizerOnTesting";

    public RecognizerInternal_Testing(RecognizerParams recognizerParams) {
        super(recognizerParams);
    }

    public boolean recognizeDetectedLines(Bitmap bitmap, OCRResult oCRResult) {
        return process(bitmap, new RecognizerInternal.ocr_processor() {
            public boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult) {
                Log.i(RecognizerInternal_Testing.TAG, "OCR Text Recognizing Detected Lines....");
                return iOCRecognizer.recognizeDetectedLines(bitmap, oCRResult);
            }
        }, oCRResult);
    }

    public RecognizerInternal_Testing(Context context, OCRType oCRType, OCRLanguage oCRLanguage) {
        super(context, oCRType, oCRLanguage);
    }
}
