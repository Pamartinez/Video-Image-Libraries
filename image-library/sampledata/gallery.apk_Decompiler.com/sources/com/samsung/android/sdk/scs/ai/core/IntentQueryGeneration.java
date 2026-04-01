package com.samsung.android.sdk.scs.ai.core;

import a6.g;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntentQueryGeneration {
    private static final String TAG = "IntentQueryGenerator";
    private final ExtensionServiceExecutor mServiceExecutor;

    public IntentQueryGeneration(Context context) {
        this.mServiceExecutor = new ExtensionServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Task task) {
        this.mServiceExecutor.deInit();
    }

    public Task<IntentQueryResult> inference(String str, Bundle bundle) {
        Log.d(TAG, "inference()");
        IntentQueryInferenceRunnable intentQueryInferenceRunnable = new IntentQueryInferenceRunnable(this.mServiceExecutor);
        intentQueryInferenceRunnable.setFeature(Feature.FEATURE_CORE_EXT_INTENT_QUERY);
        intentQueryInferenceRunnable.setInputArg(str);
        intentQueryInferenceRunnable.setInputData(bundle);
        this.mServiceExecutor.execute(intentQueryInferenceRunnable);
        return intentQueryInferenceRunnable.getTask();
    }

    public Task<Boolean> prepare() {
        Log.d(TAG, "prepare()");
        IntentQueryPrepareRunnable intentQueryPrepareRunnable = new IntentQueryPrepareRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(intentQueryPrepareRunnable);
        return intentQueryPrepareRunnable.getTask();
    }

    public Task<Boolean> release() {
        Log.d(TAG, "release()");
        IntentQueryReleaseRunnable intentQueryReleaseRunnable = new IntentQueryReleaseRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(intentQueryReleaseRunnable);
        intentQueryReleaseRunnable.getTask().addOnCompleteListener(new g(23, this));
        return intentQueryReleaseRunnable.getTask();
    }
}
