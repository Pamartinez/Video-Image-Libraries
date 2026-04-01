package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.ai.language.ResultErrorException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.g0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TranslationRunnable2 extends TaskRunnable<List<Result>> {
    private static final String TAG = "TranslationRunnable2";
    Map<String, String> authHeader;
    private final Map<String, String> extraPrompt = new HashMap();
    String fromLanguage;
    List<String> inputTextList;
    TranslationServiceExecutor mServiceExecutor;
    String toLanguage;

    public TranslationRunnable2(TranslationServiceExecutor translationServiceExecutor, TaskCompletionSource<List<Result>> taskCompletionSource) {
        super(taskCompletionSource);
        this.mServiceExecutor = translationServiceExecutor;
    }

    public void execute() {
        try {
            AnonymousClass1 r5 = new LlmServiceObserver2() {
                public void onComplete() {
                    Log.d(TranslationRunnable2.TAG, "onComplete()");
                }

                public void onError(Bundle bundle) {
                    if (bundle == null) {
                        Log.e(TranslationRunnable2.TAG, "onError= error is null");
                        C0086a.t(5, "error is null", TranslationRunnable2.this.mSource);
                        return;
                    }
                    Log.e(TranslationRunnable2.TAG, "onError= " + bundle.getInt("error_code") + bundle.getString("error_message"));
                    TranslationRunnable2.this.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
                }

                public void onNext(List<Bundle> list) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle result : list) {
                        arrayList.add(new Result(result));
                    }
                    TranslationRunnable2.this.mSource.setResult(arrayList);
                }
            };
            ((g0) this.mServiceExecutor.getService()).c(this.authHeader, this.inputTextList, this.fromLanguage, this.toLanguage, r5, this.extraPrompt);
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        if (AppInfo.RequestType.ONDEVICE.name().equals(this.authHeader.get("request-type"))) {
            return Feature.FEATURE_AI_GEN_TRANSLATION_ONDEVICE;
        }
        return Feature.FEATURE_AI_GEN_TRANSLATION;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
    }

    public void setExtraPrompt(Map<String, String> map) {
        this.extraPrompt.putAll(map);
    }

    public void setFromLanguage(String str) {
        this.fromLanguage = str;
    }

    public void setInputText(List<String> list) {
        this.inputTextList = new ArrayList(list);
    }

    public void setToLanguage(String str) {
        this.toLanguage = str;
    }
}
