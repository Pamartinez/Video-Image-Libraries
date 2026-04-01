package com.samsung.android.sdk.scs.ai.translation;

import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import com.samsung.android.sivs.ai.sdkcommon.translation.h;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClearAndRefreshNeuralTranslatorRunnable extends TaskRunnable<Map<LanguageDirection, LanguageDirectionState>> {
    private static final String CLASS_NAME = "ClearAndRefreshNeuralTranslatorRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;

    public ClearAndRefreshNeuralTranslatorRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
    }

    public void execute() {
        Parcel obtain;
        Parcel obtain2;
        try {
            h translationService = this.neuralTranslationServiceExecutor.getTranslationService();
            f fVar = (f) translationService;
            fVar.getClass();
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            fVar.f1723a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
            f fVar2 = (f) translationService;
            fVar2.k();
            this.mSource.setResult(LanguageDirectionStateMapper.from(fVar2.b()));
        } catch (RemoteException e) {
            Log.e(TAG, "ClearAndRefreshNeuralTranslatorRunnable -- Exception: " + e);
            e.printStackTrace();
            this.mSource.setException(e);
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_NEURAL_TRANSLATION;
    }
}
