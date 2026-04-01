package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LanguageIdentificationRunnable extends TaskRunnable<String> {
    private static final String CLASS_NAME = "LanguageIdentificationRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    private final String fallbackLanguage;
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    private final String text;

    public LanguageIdentificationRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, String str, String str2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.text = str;
        this.fallbackLanguage = str2;
    }

    public void execute() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("text", this.text);
            bundle.putString("fallbackLanguage", this.fallbackLanguage);
            String e = ((f) this.neuralTranslationServiceExecutor.getTranslationService()).e(bundle);
            Log.i(TAG, "LanguageIdentificationRunnable -- identified language: " + e);
            this.mSource.setResult(e);
        } catch (RemoteException e7) {
            Log.e(TAG, "LanguageIdentificationRunnable -- Exception: " + e7);
            e7.printStackTrace();
            this.mSource.setException(e7);
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_NEURAL_TRANSLATION;
    }
}
