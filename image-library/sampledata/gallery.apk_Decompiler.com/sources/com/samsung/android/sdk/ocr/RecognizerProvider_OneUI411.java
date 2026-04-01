package com.samsung.android.sdk.ocr;

import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenOcrLanguage;
import com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerProvider_OneUI411 extends RecognizerProvider {
    private static final String TAG = "RecognizerProvider_OneUI411";

    public RecognizerProvider_OneUI411(RecognizerParams recognizerParams, RecognizerSupporter recognizerSupporter) {
        super(recognizerParams, recognizerSupporter);
    }

    public void createSOCRRecognizer(RecognizerParams recognizerParams) {
        if (this.mEngine.isSupportedLanguage(SpenOcrLanguage.from(recognizerParams.language))) {
            this.mSOCRecognizer = new SOCRecognizer_OneUI411(this.mEngine, recognizerParams);
            return;
        }
        String obj = recognizerParams.language.toString();
        Log.w(TAG, "SOCRecognizer does not support language(" + obj + ")");
        this.mSOCRecognizer = null;
    }

    public SpenOcrModelLoaderFactory.MODEL_LOADER whichModelLoader(boolean z) {
        if (z) {
            return SpenOcrModelLoaderFactory.MODEL_LOADER.ASSETS;
        }
        return SpenOcrModelLoaderFactory.MODEL_LOADER.PROVIDER_ONEUI411;
    }
}
