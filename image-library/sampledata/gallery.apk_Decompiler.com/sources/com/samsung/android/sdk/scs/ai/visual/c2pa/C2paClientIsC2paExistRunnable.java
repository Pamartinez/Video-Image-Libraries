package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.ParcelFileDescriptor;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientIsC2paExistRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "C2paClientIsC2paExistRunnable";
    String mFilePath;
    private final C2paServiceExecutor mServiceExecutor;

    public C2paClientIsC2paExistRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        Log.d(TAG, "execute isC2paExist()");
        try {
            String fileExtension = C2paUtils.getFileExtension(this.mFilePath);
            ParcelFileDescriptor parcelFileDescriptor = C2paUtils.getParcelFileDescriptor(this.mFilePath);
            if (parcelFileDescriptor != null) {
                if (fileExtension != null) {
                    this.mSource.setResult(Boolean.valueOf(((m) this.mServiceExecutor.getC2PAService()).g(new C2paParam.C2paExistParamBuilder().setPfd(parcelFileDescriptor).setExtensionType(fileExtension).build())));
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

    public void setParentPath(String str) {
        this.mFilePath = str;
    }
}
