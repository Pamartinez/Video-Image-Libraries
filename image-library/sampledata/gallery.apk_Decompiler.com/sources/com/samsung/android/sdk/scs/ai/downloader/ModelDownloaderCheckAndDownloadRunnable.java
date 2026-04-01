package com.samsung.android.sdk.scs.ai.downloader;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderCheckAndDownloadRunnable extends TaskRunnable<Integer> {
    private static final String TAG = "ModelDownloaderCheckAndDownloadRunnable";
    private boolean mCheckOnly;
    private String mFeature;
    private boolean mLaunchStore;
    private final ModelDownloaderCoreExecutor mServiceExecutor;

    public ModelDownloaderCheckAndDownloadRunnable(ModelDownloaderCoreExecutor modelDownloaderCoreExecutor) {
        this.mServiceExecutor = modelDownloaderCoreExecutor;
    }

    public void execute() {
        String str = TAG;
        Log.i(str, "execute");
        try {
            int b = ((j) this.mServiceExecutor.getDownloadService()).b(this.mFeature, this.mCheckOnly, this.mLaunchStore);
            Log.d(str, "execute: result=" + b);
            if (b != 0) {
                if (b > 9000) {
                    b -= 10000;
                }
                this.mSource.setResult(Integer.valueOf(b));
                return;
            }
            throw new RemoteException("Non-existent interface");
        } catch (RemoteException e) {
            Log.e(TAG, "execute: " + e);
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_DOWNLOAD;
    }

    public void setParameter(String str, boolean z, boolean z3) {
        this.mFeature = str;
        this.mCheckOnly = z;
        this.mLaunchStore = z3;
    }
}
