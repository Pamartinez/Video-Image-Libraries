package com.samsung.android.sdk.scs.ai.translation;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GetSpeechLlmSupportedTargetLanguageRunnable extends TaskRunnable<List<String>> {
    private static final String CLASS_NAME = "GetSpeechLLMSupportedTargetLanguageRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    private String sourceLanguageCode;

    public GetSpeechLlmSupportedTargetLanguageRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, String str) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.sourceLanguageCode = str;
    }

    public void execute() {
        try {
            this.mSource.setResult(((f) this.neuralTranslationServiceExecutor.getTranslationService()).d(this.sourceLanguageCode));
        } catch (RemoteException e) {
            Log.e(TAG, "GetSpeechLLMSupportedTargetLanguageRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_NEURAL_TRANSLATION_SPEECH_LLM;
    }
}
