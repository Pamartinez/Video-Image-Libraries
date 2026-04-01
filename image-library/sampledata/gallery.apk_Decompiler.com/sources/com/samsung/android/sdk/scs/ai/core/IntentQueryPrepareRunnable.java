package com.samsung.android.sdk.scs.ai.core;

import a3.C0077a;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntentQueryPrepareRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "IntentQueryPrepareRunnable";
    private final ExtensionServiceExecutor mServiceExecutor;

    public IntentQueryPrepareRunnable(ExtensionServiceExecutor extensionServiceExecutor) {
        this.mServiceExecutor = extensionServiceExecutor;
    }

    public void execute() {
        Log.i(TAG, "prepare runnable execute");
        try {
            ((C0077a) this.mServiceExecutor.getExtensionService()).b(1000);
            this.mSource.setResult(Boolean.TRUE);
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_CORE_EXT_INTENT_QUERY;
    }
}
