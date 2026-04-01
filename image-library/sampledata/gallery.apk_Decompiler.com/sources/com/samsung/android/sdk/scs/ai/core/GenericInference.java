package com.samsung.android.sdk.scs.ai.core;

import B3.b;
import B3.e;
import a6.g;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericInference {
    private static final String TAG = "GenericInference";
    private b mAdapterInfo;
    private e mModelInfo;
    private final ExtensionServiceExecutor mServiceExecutor;

    public GenericInference(Context context) {
        this.mServiceExecutor = new ExtensionServiceExecutor(context);
    }

    private b getAdapterInfo() {
        return this.mAdapterInfo;
    }

    private e getModelInfo() {
        return this.mModelInfo;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Task task) {
        this.mServiceExecutor.deInit();
    }

    private boolean setDefaultInputData(Bundle bundle) {
        if (getModelInfo() == null || getAdapterInfo() == null) {
            return false;
        }
        bundle.putParcelable("genericInferenceModelInfo", getModelInfo());
        bundle.putParcelable("genericInferenceAdapterInfo", getAdapterInfo());
        return true;
    }

    public Task<GenericInferenceResult> inference(Bundle bundle) {
        Log.d(TAG, "inference()");
        GenericInferenceInferenceRunnable genericInferenceInferenceRunnable = new GenericInferenceInferenceRunnable(this.mServiceExecutor);
        if (!setDefaultInputData(bundle)) {
            Log.w(TAG, "The required info was not provided");
            return null;
        }
        genericInferenceInferenceRunnable.setFeature(Feature.FEATURE_CORE_EXT_GENERIC_INFERENCE);
        genericInferenceInferenceRunnable.setInputData(bundle);
        this.mServiceExecutor.execute(genericInferenceInferenceRunnable);
        return genericInferenceInferenceRunnable.getTask();
    }

    public Task<Boolean> prepare() {
        Log.d(TAG, "prepare()");
        GenericInferencePrepareRunnable genericInferencePrepareRunnable = new GenericInferencePrepareRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(genericInferencePrepareRunnable);
        return genericInferencePrepareRunnable.getTask();
    }

    public Task<Boolean> release() {
        Log.d(TAG, "release()");
        GenericInferenceReleaseRunnable genericInferenceReleaseRunnable = new GenericInferenceReleaseRunnable(this.mServiceExecutor);
        this.mServiceExecutor.execute(genericInferenceReleaseRunnable);
        genericInferenceReleaseRunnable.getTask().addOnCompleteListener(new g(22, this));
        return genericInferenceReleaseRunnable.getTask();
    }

    public void setAdapterInfo(b bVar) {
        this.mAdapterInfo = bVar;
    }

    public void setModelInfo(e eVar) {
        this.mModelInfo = eVar;
    }
}
