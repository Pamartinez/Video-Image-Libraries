package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenOcrLanguage;
import com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerProvider_OneUI41 extends RecognizerProvider {
    private static final String TAG = "RecognizerProvider_OneUI41";

    public RecognizerProvider_OneUI41(RecognizerParams recognizerParams, RecognizerSupporter recognizerSupporter) {
        super(recognizerParams, recognizerSupporter);
    }

    public void createSOCRRecognizer(RecognizerParams recognizerParams) {
        if (this.mEngine.isSupportedLanguage(SpenOcrLanguage.from(recognizerParams.language))) {
            this.mSOCRecognizer = new SOCRecognizer_OneUI41(this.mEngine, recognizerParams);
            return;
        }
        String obj = recognizerParams.language.toString();
        Log.w(TAG, "SOCRecognizer does not support language(" + obj + ")");
        this.mSOCRecognizer = null;
    }

    public boolean isHandwritten(Bitmap bitmap) {
        return isHandwritten(this.mDataConverter.resizeBitmap(bitmap), true);
    }

    public boolean isPrinted(Bitmap bitmap) {
        Log.e(TAG, "This API should not be called(It supports above One UI 4.1)");
        return false;
    }

    public SpenOcrModelLoaderFactory.MODEL_LOADER whichModelLoader(boolean z) {
        if (z) {
            return SpenOcrModelLoaderFactory.MODEL_LOADER.ASSETS;
        }
        return SpenOcrModelLoaderFactory.MODEL_LOADER.ONEUI41;
    }

    private boolean isHandwritten(Bitmap bitmap, boolean z) {
        boolean z3;
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            z3 = this.mClassifier.isHandwrittenImage(this.mDataConverter.resizeBitmap(bitmap));
        } else {
            z3 = this.mClassifier.isHandwrittenImage(bitmap);
        }
        Log.i(TAG, String.format("Type Classification(handwritten=%s), Time: %d ms", new Object[]{Boolean.toString(z3), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return z3;
    }
}
