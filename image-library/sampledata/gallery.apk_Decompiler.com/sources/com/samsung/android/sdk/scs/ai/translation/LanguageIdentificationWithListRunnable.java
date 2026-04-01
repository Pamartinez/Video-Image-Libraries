package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LanguageIdentificationWithListRunnable extends TaskRunnable<List<String>> {
    private static final String CLASS_NAME = "LanguageIdentificationWithListRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    private final String fallbackLanguage;
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    private final ArrayList<String> textList;

    public LanguageIdentificationWithListRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, ArrayList<String> arrayList, String str) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.textList = arrayList;
        this.fallbackLanguage = str;
    }

    public void execute() {
        try {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("textList", this.textList);
            bundle.putString("fallbackLanguage", this.fallbackLanguage);
            ArrayList h5 = ((f) this.neuralTranslationServiceExecutor.getTranslationService()).h(bundle);
            Log.i(TAG, "LanguageIdentificationWithListRunnable -- identified language: " + h5.toString());
            this.mSource.setResult(h5);
        } catch (RemoteException e) {
            Log.e(TAG, "LanguageIdentificationWithListRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_LANGUAGE_LIST_IDENTIFICATION;
    }
}
