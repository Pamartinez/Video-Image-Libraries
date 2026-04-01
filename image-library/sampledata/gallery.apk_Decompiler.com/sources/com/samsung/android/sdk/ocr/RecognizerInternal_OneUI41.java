package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.sdk.ocr.RecognizerInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerInternal_OneUI41 extends RecognizerInternal {
    private static final String TAG = "RecognizerInternal_OneUI41";

    public RecognizerInternal_OneUI41(RecognizerParams recognizerParams) {
        super(recognizerParams);
        Log.i(TAG, "OCR Recognizer(Internal_OneUI41) is initialized with version: 3.4.251128");
    }

    public void cancel() {
        Log.w(TAG, "Cancel operation is not supported in Recognizer OneUI4.1");
    }

    public void createRecognizerProvider(RecognizerParams recognizerParams) {
        closeRecognizerProvider();
        this.mRecognizerProvider = new RecognizerProvider_OneUI41(recognizerParams, this.mRecognizerSupporter);
    }

    public boolean hasText(Bitmap bitmap) {
        return process(bitmap, new RecognizerInternal.ocr_processor() {
            public boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult) {
                Log.i(RecognizerInternal_OneUI41.TAG, "OCR Text Detecting(hasText)....");
                return iOCRecognizer.hasText(bitmap);
            }
        }, new OCRResult());
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        Log.w(TAG, "recognizeBlockAt(...) is not supported in Recognizer OneUI4.1");
        return false;
    }

    public RecognizerInternal_OneUI41(Context context, OCRType oCRType, OCRLanguage oCRLanguage) {
        super(context, oCRType, oCRLanguage);
        Log.i(TAG, "OCR Recognizer(Internal_OneUI41) is initialized with version: 3.4.251128");
    }
}
