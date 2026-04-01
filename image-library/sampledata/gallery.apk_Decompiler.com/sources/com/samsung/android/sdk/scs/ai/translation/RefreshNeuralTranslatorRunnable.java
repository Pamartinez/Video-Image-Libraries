package com.samsung.android.sdk.scs.ai.translation;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RefreshNeuralTranslatorRunnable extends TaskRunnable<Map<LanguageDirection, LanguageDirectionState>> {
    private static final String CLASS_NAME = "RefreshNeuralTranslatorRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;

    public RefreshNeuralTranslatorRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
    }

    public void execute() {
        try {
            f fVar = (f) this.neuralTranslationServiceExecutor.getTranslationService();
            fVar.k();
            this.mSource.setResult(LanguageDirectionStateMapper.from(fVar.b()));
        } catch (RemoteException e) {
            Log.e(TAG, "RefreshNeuralTranslatorRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_NEURAL_TRANSLATION;
    }
}
