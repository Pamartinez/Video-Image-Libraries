package com.samsung.android.sdk.scs.ai.downloader;

import Cd.C0727a;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderCheckRunnable extends TaskRunnable<Integer> {
    private static final String TAG = "ModelDownloaderCheckRunnable";
    private String mFeature = null;
    private final ModelDownloaderExecutor mServiceExecutor;

    public ModelDownloaderCheckRunnable(ModelDownloaderExecutor modelDownloaderExecutor) {
        this.mServiceExecutor = modelDownloaderExecutor;
    }

    public void execute() {
        Log.i(TAG, "execute");
        try {
            this.mSource.setResult(Integer.valueOf(((C0727a) this.mServiceExecutor.getDownloadService()).a(this.mFeature)));
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_DOWNLOAD;
    }

    public void setParameters(String str) {
        this.mFeature = str;
    }
}
