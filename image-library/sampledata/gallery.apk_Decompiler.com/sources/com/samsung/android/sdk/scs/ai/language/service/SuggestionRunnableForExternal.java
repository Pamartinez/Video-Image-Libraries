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
import com.samsung.android.sivs.ai.sdkcommon.language.W;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionRunnableForExternal extends TaskRunnable<Result> {
    private static final String TAG = "SuggestionRunnableForExternal";
    Map<String, String> authHeader;
    private final Map<String, String> extraPrompt = new HashMap();
    String inputText;
    SuggestionServiceExecutorForExternal mServiceExecutor;

    public SuggestionRunnableForExternal(SuggestionServiceExecutorForExternal suggestionServiceExecutorForExternal, TaskCompletionSource<Result> taskCompletionSource) {
        super(taskCompletionSource);
        this.mServiceExecutor = suggestionServiceExecutorForExternal;
    }

    public void execute() {
        try {
            AnonymousClass1 r0 = new LlmServiceObserver2() {
                public void onError(Bundle bundle) {
                    if (bundle == null) {
                        Log.e(SuggestionRunnableForExternal.TAG, "onError= error is null");
                        C0086a.t(5, "error is null", SuggestionRunnableForExternal.this.mSource);
                        return;
                    }
                    Log.e(SuggestionRunnableForExternal.TAG, "onError= " + bundle.getInt("error_code") + bundle.getString("error_message"));
                    SuggestionRunnableForExternal.this.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
                }

                public void onNext(List<Bundle> list) {
                    SuggestionRunnableForExternal.this.mSource.setResult(new Result(list.get(0)));
                }

                public void onComplete() {
                }
            };
            ((W) this.mServiceExecutor.getService()).a(this.authHeader, this.inputText, r0, this.extraPrompt);
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_AI_GEN_SUGGESTION;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
    }

    public void setExtraPrompt(Map<String, String> map) {
        this.extraPrompt.putAll(map);
    }

    public void setInputText(String str) {
        this.inputText = str;
    }
}
