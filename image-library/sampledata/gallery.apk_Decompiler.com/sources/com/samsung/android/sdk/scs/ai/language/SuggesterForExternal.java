package com.samsung.android.sdk.scs.ai.language;

import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.SuggestionRunnableForExternal;
import com.samsung.android.sdk.scs.ai.language.service.SuggestionServiceExecutorForExternal;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource;
import com.samsung.android.sdk.scs.base.tasks.TaskStreamingCompletionSource;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.W;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggesterForExternal {
    private static final String TAG = "SuggesterForExternal";
    private final String featureName = Feature.FEATURE_AI_GEN_SUGGESTION;
    private final SuggestionServiceExecutorForExternal mServiceExecutor;

    public SuggesterForExternal(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new SuggestionServiceExecutorForExternal(context);
    }

    /* access modifiers changed from: private */
    public void lambda$release$0(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            W w = (W) this.mServiceExecutor.getService();
            w.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionServiceForExternal");
            w.f1692a.transact(3, obtain, (Parcel) null, 1);
            obtain.recycle();
            llmServiceObserver2.onNext(new ArrayList());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$1(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SUGGESTION, false, new C0582a(23, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new a(1, this));
    }

    public Task<Result> suggestion(AppInfo appInfo, String str) {
        return suggestion(appInfo, str, new HashMap());
    }

    public Task<Result> suggestion(AppInfo appInfo, String str, Map<String, String> map) {
        SuggestionRunnableForExternal suggestionRunnableForExternal = new SuggestionRunnableForExternal(this.mServiceExecutor, appInfo.isStreamingMode() ? new TaskStreamingCompletionSource() : new TaskCompletionSource());
        Log.i(TAG, "created: " + suggestionRunnableForExternal.hashCode());
        suggestionRunnableForExternal.setAppInfo(appInfo);
        suggestionRunnableForExternal.setInputText(str);
        suggestionRunnableForExternal.setExtraPrompt(map);
        this.mServiceExecutor.execute(suggestionRunnableForExternal);
        Log.i(TAG, "executed: " + suggestionRunnableForExternal.hashCode());
        return suggestionRunnableForExternal.getTask();
    }
}
