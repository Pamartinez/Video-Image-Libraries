package com.samsung.android.sdk.scs.ai.core;

import a6.g;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionParamExtraction {
    private static final String TAG = "ActionParamExtraction";
    private final ExtensionServiceExecutor mServiceExecutor;

    public ActionParamExtraction(Context context) {
        this.mServiceExecutor = new ExtensionServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Task task) {
        this.mServiceExecutor.deInit();
    }

    public Task<ActionParamExtractionResult> inference(String str, Bundle bundle) {
        Log.d(TAG, "generate()");
        ActionParamExtractionInferenceRunnable actionParamExtractionInferenceRunnable = new ActionParamExtractionInferenceRunnable(this.mServiceExecutor);
        actionParamExtractionInferenceRunnable.setFeature(Feature.FEATURE_CORE_EXT_ACTION_PARAM_EXTRACTION);
        actionParamExtractionInferenceRunnable.setInputArg(str);
        actionParamExtractionInferenceRunnable.setInputData(bundle);
        this.mServiceExecutor.execute(actionParamExtractionInferenceRunnable);
        return actionParamExtractionInferenceRunnable.getTask();
    }

    public Task<Boolean> prepare() {
        Log.d(TAG, "prepare()");
        ActionParamExtractionPrepareRunnable actionParamExtractionPrepareRunnable = new ActionParamExtractionPrepareRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(actionParamExtractionPrepareRunnable);
        return actionParamExtractionPrepareRunnable.getTask();
    }

    public Task<Boolean> release() {
        Log.d(TAG, "release()");
        ActionParamExtractionReleaseRunnable actionParamExtractionReleaseRunnable = new ActionParamExtractionReleaseRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(actionParamExtractionReleaseRunnable);
        actionParamExtractionReleaseRunnable.getTask().addOnCompleteListener(new g(21, this));
        return actionParamExtractionReleaseRunnable.getTask();
    }
}
