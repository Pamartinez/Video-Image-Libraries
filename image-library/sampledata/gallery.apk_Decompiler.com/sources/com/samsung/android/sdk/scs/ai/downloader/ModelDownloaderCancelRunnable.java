package com.samsung.android.sdk.scs.ai.downloader;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderCancelRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "ModelDownloaderCancelRunnable";
    private final ModelDownloaderCoreExecutor mServiceExecutor;
    private String mTaskId = null;

    public ModelDownloaderCancelRunnable(ModelDownloaderCoreExecutor modelDownloaderCoreExecutor) {
        this.mServiceExecutor = modelDownloaderCoreExecutor;
    }

    public void execute() {
        Log.i(TAG, "execute");
        try {
            if (((j) this.mServiceExecutor.getDownloadService()).a(this.mTaskId)) {
                this.mSource.setResult(Boolean.TRUE);
                return;
            }
            TaskCompletionSource<TResult> taskCompletionSource = this.mSource;
            taskCompletionSource.setException(new Exception("Task ID not exists: " + this.mTaskId));
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
        this.mTaskId = str;
    }
}
