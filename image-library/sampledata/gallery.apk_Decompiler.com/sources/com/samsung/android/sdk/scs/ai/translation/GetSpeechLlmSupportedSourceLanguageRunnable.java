package com.samsung.android.sdk.scs.ai.translation;

import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GetSpeechLlmSupportedSourceLanguageRunnable extends TaskRunnable<List<String>> {
    private static final String CLASS_NAME = "GetSpeechLLMSupportedSourceLanguageRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;

    public GetSpeechLlmSupportedSourceLanguageRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
    }

    public void execute() {
        Parcel obtain;
        Parcel obtain2;
        try {
            f fVar = (f) this.neuralTranslationServiceExecutor.getTranslationService();
            fVar.getClass();
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            fVar.f1723a.transact(17, obtain, obtain2, 0);
            obtain2.readException();
            ArrayList<String> createStringArrayList = obtain2.createStringArrayList();
            obtain2.recycle();
            obtain.recycle();
            this.mSource.setResult(createStringArrayList);
        } catch (RemoteException e) {
            Log.e(TAG, "GetSpeechLLMSupportedSourceLanguageRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_NEURAL_TRANSLATION_SPEECH_LLM;
    }
}
