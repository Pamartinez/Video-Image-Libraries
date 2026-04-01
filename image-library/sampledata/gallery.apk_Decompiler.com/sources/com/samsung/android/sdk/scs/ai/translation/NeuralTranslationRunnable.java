package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.d;
import com.samsung.android.sivs.ai.sdkcommon.translation.f;
import java.util.Map;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NeuralTranslationRunnable extends TaskRunnable<Void> {
    private static final String CLASS_NAME = "NeuralTranslationRunnable";
    private static final String TAG = "ScsApi@NeuralTranslator";
    Map<String, String> authHeader;
    NeuralTranslationServiceExecutor neuralTranslationServiceExecutor;
    NeuralTranslationRequest request;
    Bundle speechLlmRequest;

    public NeuralTranslationRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.request = neuralTranslationRequest;
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.neuralTranslationServiceExecutor.context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0() {
        this.request.getOnFailure().accept(NeuralTranslationErrorCode.UNKNOWN);
        this.request.deRegisterCallback();
    }

    public void execute() {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            NeuralTranslationSource source = this.request.getSource();
            Bundle bundle = new Bundle();
            final String uuid = UUID.randomUUID().toString();
            String sourceLanguage = source.getSourceLanguage();
            String targetLanguage = source.getTargetLanguage();
            Boolean formality = source.getFormality();
            String mode = source.getMode();
            boolean sentenceSplit = source.getSentenceSplit();
            Log.i(TAG, "NeuralTranslationRunnable -- [" + uuid + "] Translate requested");
            bundle.putString("sourceLanguage", sourceLanguage);
            bundle.putString("targetLanguage", targetLanguage);
            bundle.putString("sourceText", source.getSourceText());
            bundle.putString("id", source.getId());
            bundle.putString("tid", uuid);
            bundle.putBoolean("verbose", source.getVerbose().booleanValue());
            bundle.putBoolean("appendMeta", source.getAppendMeta().booleanValue());
            bundle.putBoolean("formality", formality.booleanValue());
            bundle.putString("mode", mode);
            bundle.putBoolean("forcePivot", source.getForcePivot().booleanValue());
            bundle.putString("requestingPackageName", source.getRequestingPackageName());
            bundle.putBoolean("needSentenceSplit", sentenceSplit);
            bundle.putString("requestingSourceId", source.getRequestingSourceId());
            bundle.putBoolean("endOfContext", source.getEndOfContext());
            bundle.putInt("contextCandidate", source.getContextCandidate());
            bundle.putBundle("speech-llm-bundle", this.speechLlmRequest);
            for (Map.Entry next : this.authHeader.entrySet()) {
                bundle.putString((String) next.getKey(), (String) next.getValue());
            }
            ((f) this.neuralTranslationServiceExecutor.getTranslationService()).l(bundle, new d() {
                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onFailure$1(NeuralTranslationErrorCode neuralTranslationErrorCode) {
                    NeuralTranslationRunnable.this.request.getOnFailure().accept(neuralTranslationErrorCode);
                    NeuralTranslationRunnable.this.request.deRegisterCallback();
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onSuccess$0(NeuralTranslationResult neuralTranslationResult) {
                    NeuralTranslationRunnable.this.request.getOnSuccess().accept(neuralTranslationResult);
                    NeuralTranslationRunnable.this.request.deRegisterCallback();
                }

                public void onFailure(int i2) {
                    NeuralTranslationErrorCode from = NeuralTranslationErrorCode.from(i2);
                    String str = uuid;
                    Log.e(NeuralTranslationRunnable.TAG, "NeuralTranslationRunnable -- [" + str + "] Translation fails with error code " + from);
                    handler.post(new b(2, this, from));
                }

                public void onSuccess(Bundle bundle) {
                    String string = bundle.getString("sourceLanguage");
                    String string2 = bundle.getString("targetLanguage");
                    Boolean valueOf = Boolean.valueOf(bundle.getBoolean("formality"));
                    String str = uuid;
                    Log.i(NeuralTranslationRunnable.TAG, "NeuralTranslationRunnable -- [" + str + "] Translate success");
                    handler.post(new b(1, this, NeuralTranslationResult.builder().sourceLanguage(string).targetLanguage(string2).sourceText(bundle.getString("sourceText")).targetText(bundle.getString("targetText")).id(bundle.getString("id")).verbose(Boolean.valueOf(bundle.getBoolean("verbose"))).appendMeta(Boolean.valueOf(bundle.getBoolean("appendMeta"))).formality(valueOf).forcePivot(Boolean.valueOf(bundle.getBoolean("forcePivot"))).interimResult(bundle.getString("interimResult")).isComplete(Boolean.TRUE).build()));
                }
            });
        } catch (RemoteException e) {
            Log.e(TAG, "NeuralTranslationRunnable -- Exception : " + e);
            e.printStackTrace();
            this.mSource.setException(e);
            handler.post(new c(this, 1));
        }
    }

    public String getFeatureName() {
        if (this.neuralTranslationServiceExecutor.isPublic()) {
            return Feature.FEATURE_NEURAL_TRANSLATION_FOR_EXTERNAL;
        }
        return Feature.FEATURE_NEURAL_TRANSLATION;
    }

    public NeuralTranslationRunnable(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor2, NeuralTranslationRequest neuralTranslationRequest, Bundle bundle, AppInfo appInfo) {
        this.neuralTranslationServiceExecutor = neuralTranslationServiceExecutor2;
        this.request = neuralTranslationRequest;
        this.speechLlmRequest = bundle;
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.neuralTranslationServiceExecutor.context);
    }
}
