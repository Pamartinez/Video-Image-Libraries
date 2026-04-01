package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientClearManifestsFromCacheRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "C2paClientSaveManifestsToCacheRunnable";
    String mParentPath;
    private final C2paServiceExecutor mServiceExecutor;

    public C2paClientClearManifestsFromCacheRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        Log.d(TAG, "execute clearManifestsFromCache()");
        try {
            this.mSource.setResult(Boolean.valueOf(((m) this.mServiceExecutor.getC2PAService()).b(this.mParentPath)));
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_C2PA;
    }

    public void setFilePath(String str) {
        this.mParentPath = str;
    }
}
