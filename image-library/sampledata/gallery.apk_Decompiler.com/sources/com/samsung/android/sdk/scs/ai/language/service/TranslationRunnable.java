package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.ResultErrorException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.g0;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TranslationRunnable extends TaskRunnable<String> {
    private static final String TAG = "TranslationRunnable";
    Map<String, String> authHeader;
    String fromLanguage;
    String inputText;
    TranslationServiceExecutor mServiceExecutor;
    String toLanguage;

    public TranslationRunnable(TranslationServiceExecutor translationServiceExecutor) {
        this.mServiceExecutor = translationServiceExecutor;
    }

    public void execute() {
        try {
            AnonymousClass1 r5 = new LlmServiceObserver() {
                public void onError(Bundle bundle) {
                    if (bundle == null) {
                        Log.e(TranslationRunnable.TAG, "onError= error is null");
                        C0086a.t(5, "error is null", TranslationRunnable.this.mSource);
                        return;
                    }
                    Log.e(TranslationRunnable.TAG, "onError= " + bundle.getInt("error_code") + bundle.getString("error_message"));
                    TranslationRunnable.this.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
                }

                public void onNext(String str) {
                    TranslationRunnable.this.mSource.setResult(str);
                }

                public void onComplete() {
                }
            };
            ((g0) this.mServiceExecutor.getService()).b(this.authHeader, this.inputText, this.fromLanguage, this.toLanguage, r5);
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_AI_GEN_TRANSLATION;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
    }

    public void setFromLanguage(String str) {
        this.fromLanguage = str;
    }

    public void setInputText(String str) {
        this.inputText = str;
    }

    public void setToLanguage(String str) {
        this.toLanguage = str;
    }
}
