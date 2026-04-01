package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrepareSpeechLlmTranslationRunnable extends TaskRunnable<Boolean> {
    private static final String CLASS_NAME = "PrepareSpeechLLMTranslationRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    SpeechLlmPrePareInfo prepareInfo;

    public PrepareSpeechLlmTranslationRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, SpeechLlmPrePareInfo speechLlmPrePareInfo) {
        this.prepareInfo = speechLlmPrePareInfo;
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
    }

    public void execute() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("sourceLanguage", this.prepareInfo.getSourceLanguage());
            bundle.putString("targetLanguage", this.prepareInfo.getTargetLanguage());
            this.mSource.setResult(Boolean.valueOf(((f) this.neuralTranslationServiceExecutor.getTranslationService()).j(bundle)));
        } catch (RemoteException e) {
            Log.e(TAG, "PrepareSpeechLLMTranslationRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_NEURAL_TRANSLATION_SPEECH_LLM;
    }
}
