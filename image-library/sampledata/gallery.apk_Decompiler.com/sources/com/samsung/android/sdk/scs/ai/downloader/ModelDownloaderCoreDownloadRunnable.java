package com.samsung.android.sdk.scs.ai.downloader;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.h;
import com.samsung.android.visual.ai.sdkcommon.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderCoreDownloadRunnable extends TaskRunnable<Boolean> {
    /* access modifiers changed from: private */
    public static final String TAG = "ModelDownloaderCoreDownloadRunnable";
    private final h mCallback = new h() {
        {
            attachInterface(this, "com.samsung.android.visual.ai.sdkcommon.IDownloadCallback");
        }

        public void onError(String str, String str2) {
            ModelDownloaderCoreDownloadRunnable.this.mServiceExecutor.removeCallback(str);
            String b = ModelDownloaderCoreDownloadRunnable.TAG;
            Log.d(b, "removeCallback: " + str + " : " + str2);
            ModelDownloaderCoreDownloadRunnable.this.mSource.setException(new Exception(str2));
        }

        public void onSuccess(String str) {
            ModelDownloaderCoreDownloadRunnable.this.mServiceExecutor.removeCallback(str);
            String b = ModelDownloaderCoreDownloadRunnable.TAG;
            Log.d(b, "removeCallback: " + str);
            ModelDownloaderCoreDownloadRunnable.this.mSource.setResult(Boolean.TRUE);
        }
    };
    private String mName = null;
    /* access modifiers changed from: private */
    public final ModelDownloaderCoreExecutor mServiceExecutor;

    public ModelDownloaderCoreDownloadRunnable(ModelDownloaderCoreExecutor modelDownloaderCoreExecutor) {
        this.mServiceExecutor = modelDownloaderCoreExecutor;
    }

    public void execute() {
        String str = TAG;
        Log.i(str, "execute");
        try {
            String taskId = this.mSource.getTask().getTaskId();
            ((j) this.mServiceExecutor.getDownloadService()).c(this.mName, taskId, this.mCallback);
            this.mServiceExecutor.addCallback(taskId, this.mCallback);
            Log.d(str, "addCallback: " + taskId);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_DOWNLOAD;
    }

    public void setParameters(String str) {
        this.mName = str;
    }
}
