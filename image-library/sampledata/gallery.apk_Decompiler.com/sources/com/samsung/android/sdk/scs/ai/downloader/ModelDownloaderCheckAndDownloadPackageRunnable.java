package com.samsung.android.sdk.scs.ai.downloader;

import Cd.C0727a;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderCheckAndDownloadPackageRunnable extends TaskRunnable<Integer> {
    private static final String TAG = "ModelDownloaderCheckAndDownloadPackageRunnable";
    private boolean mCheckOnly;
    private boolean mLaunchStore;
    private String mPackageName;
    private final ModelDownloaderExecutor mServiceExecutor;

    public ModelDownloaderCheckAndDownloadPackageRunnable(ModelDownloaderExecutor modelDownloaderExecutor) {
        this.mServiceExecutor = modelDownloaderExecutor;
    }

    public void execute() {
        String str = TAG;
        Log.i(str, "execute");
        try {
            int b = ((C0727a) this.mServiceExecutor.getDownloadService()).b(new String[]{this.mPackageName}, this.mCheckOnly, this.mLaunchStore);
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
        this.mPackageName = str;
        this.mCheckOnly = z;
        this.mLaunchStore = z3;
    }
}
