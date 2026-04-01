package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paResult;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientGetManifestRunnable extends TaskRunnable<C2paResult> {
    private static final String TAG = "C2paClientGetManifestRunnable";
    private C2paManifestsCallback mCallback = new C2paManifestsCallback() {
        public void onError(String str) {
            C2paClientGetManifestRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(false).setError(str).build());
        }

        public void onResult(String str, boolean z, boolean z3) {
            C2paClientGetManifestRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(true).setTrusted(z).setCompleted(z3).setManifestResult(str).build());
        }
    };
    private String mFilePath;
    private final C2paServiceExecutor mServiceExecutor;

    public C2paClientGetManifestRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        Log.d(TAG, "execute getManifestsAsString()");
        try {
            String fileExtension = C2paUtils.getFileExtension(this.mFilePath);
            ParcelFileDescriptor parcelFileDescriptor = C2paUtils.getParcelFileDescriptor(this.mFilePath);
            if (!(parcelFileDescriptor == null || fileExtension == null)) {
                if (this.mFilePath != null) {
                    Bundle build = new C2paParam.ExtractParamBuilder().setPfd(parcelFileDescriptor).setExtensionType(fileExtension).setFilePath(this.mFilePath).build();
                    ((m) this.mServiceExecutor.getC2PAService()).e(build, this.mCallback);
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
        this.mFilePath = str;
    }
}
