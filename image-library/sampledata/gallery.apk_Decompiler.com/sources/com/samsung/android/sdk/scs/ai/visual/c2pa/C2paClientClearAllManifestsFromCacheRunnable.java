package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientClearAllManifestsFromCacheRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "C2paClientSaveManifestsToCacheRunnable";
    String mParentPath;
    private final C2paServiceExecutor mServiceExecutor;

    public C2paClientClearAllManifestsFromCacheRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        Log.d(TAG, "execute clearAllManifestsFromCache()");
        try {
            ((m) this.mServiceExecutor.getC2PAService()).a();
            this.mSource.setResult(Boolean.TRUE);
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_C2PA;
    }

    public String getParentPath() {
        return this.mParentPath;
    }

    public void setParentPath(String str) {
        this.mParentPath = str;
    }
}
