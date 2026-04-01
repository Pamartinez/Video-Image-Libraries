package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.b;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LanguageIdentificationAndGetCandidateRunnable extends TaskRunnable<List<b>> {
    private static final String CLASS_NAME = "LanguageIdentificationAndGetCandidateRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    private final boolean differentiate;
    private final Integer maxCandidate;
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    private final String text;
    private final boolean verbose;

    public LanguageIdentificationAndGetCandidateRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, String str, Integer num, boolean z, boolean z3) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.text = str;
        this.maxCandidate = num;
        this.differentiate = z;
        this.verbose = z3;
    }

    public void execute() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("text", this.text);
            bundle.putInt("maxCandidates", this.maxCandidate.intValue());
            bundle.putBoolean("differentiate", this.differentiate);
            bundle.putBoolean("verbose", this.verbose);
            ArrayList f = ((f) this.neuralTranslationServiceExecutor.getTranslationService()).f(bundle);
            Log.i(TAG, "LanguageIdentificationAndGetCandidateRunnable -- identified language: " + f.toString());
            this.mSource.setResult(f);
        } catch (RemoteException e) {
            Log.e(TAG, "LanguageIdentificationAndGetCandidateRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_LANGUAGE_IDENTIFICATION_AND_GET_CANDIDATE;
    }
}
