package com.samsung.android.sdk.scs.ai.visual;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor;
import com.samsung.android.sdk.scs.ai.gateway.imageeditor.ImageEditorGateway;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorCancelRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "ImageEditorCancelRunnable";
    private final int mMode;
    AiServiceExecutor<? extends IInterface> mServiceExecutor;
    private String mTaskId;

    public ImageEditorCancelRunnable(AiServiceExecutor<? extends IInterface> aiServiceExecutor, int i2) {
        this.mServiceExecutor = aiServiceExecutor;
        this.mMode = i2;
    }

    private void setResult(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "generate(). retBundle is null!!");
            C0086a.t(5, "retBundle is null", this.mSource);
            return;
        }
        int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "cancel(). Abnormal resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
            }
        } else {
            this.mSource.setResult(Boolean.TRUE);
        }
    }

    public void execute() {
        Log.i(TAG, "execute");
        try {
            Bundle bundle = new Bundle();
            bundle.putString("taskId", this.mTaskId);
            setResult(new ImageEditorGateway(this.mServiceExecutor).cancel(getMode(), bundle));
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Exception : " + e);
            throw new RuntimeException(e);
        }
    }

    public String getFeatureName() {
        return ImageEditorParamUtils.getFeatureName(getMode(), this.mServiceExecutor.getServiceType());
    }

    public int getMode() {
        return this.mMode;
    }

    public void setTaskId(String str) {
        this.mTaskId = str;
    }
}
