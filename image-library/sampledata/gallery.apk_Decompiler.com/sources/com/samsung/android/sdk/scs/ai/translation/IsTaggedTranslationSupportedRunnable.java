package com.samsung.android.sdk.scs.ai.translation;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import com.samsung.android.sivs.ai.sdkcommon.translation.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IsTaggedTranslationSupportedRunnable extends TaskRunnable<Boolean> {
    private static final String CLASS_NAME = "IsTaggedTranslationSupported";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    private final String sourceLanguageCode;
    private final String targetLanguageCode;

    public IsTaggedTranslationSupportedRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, String str, String str2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.sourceLanguageCode = str;
        this.targetLanguageCode = str2;
    }

    public void execute() {
        try {
            h translationService = this.neuralTranslationServiceExecutor.getTranslationService();
            Log.d(TAG, "isTaggedTranslationSupported is calling : " + this.sourceLanguageCode + " " + this.targetLanguageCode);
            this.mSource.setResult(Boolean.valueOf(((f) translationService).i(this.sourceLanguageCode, this.targetLanguageCode)));
        } catch (RemoteException e) {
            Log.e(TAG, "IsTaggedTranslationSupported -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_NEURAL_TRANSLATION_TAG_SUPPORTED;
    }
}
