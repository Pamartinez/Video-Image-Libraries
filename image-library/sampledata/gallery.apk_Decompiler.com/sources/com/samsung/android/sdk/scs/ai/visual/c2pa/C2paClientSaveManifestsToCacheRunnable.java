package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.ParcelFileDescriptor;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientSaveManifestsToCacheRunnable extends TaskRunnable<String> {
    private static final String TAG = "C2paClientSaveManifestsToCacheRunnable";
    private final C2paServiceExecutor mServiceExecutor;
    String mfilePath;

    public C2paClientSaveManifestsToCacheRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        Log.d(TAG, "execute saveManifestsToCache()");
        try {
            String fileExtension = C2paUtils.getFileExtension(this.mfilePath);
            ParcelFileDescriptor parcelFileDescriptor = C2paUtils.getParcelFileDescriptor(this.mfilePath);
            if (!(parcelFileDescriptor == null || fileExtension == null)) {
                if (this.mfilePath != null) {
                    this.mSource.setResult(((m) this.mServiceExecutor.getC2PAService()).i(new C2paParam.SaveToCacheParamBuilder().setPfd(parcelFileDescriptor).setExtensionType(fileExtension).setFilePath(this.mfilePath).build()));
                    return;
                }
            }
            if (parcelFileDescriptor != null) {
                try {
                    if (parcelFileDescriptor.getFileDescriptor().valid()) {
                        parcelFileDescriptor.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            throw new NullPointerException("Target PFD/Extension is NULL");
        } catch (Exception e7) {
            e7.printStackTrace();
            this.mSource.setException(e7);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_C2PA;
    }

    public void setFilePath(String str) {
        this.mfilePath = str;
    }
}
