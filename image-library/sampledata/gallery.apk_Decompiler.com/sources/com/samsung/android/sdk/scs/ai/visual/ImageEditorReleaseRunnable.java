package com.samsung.android.sdk.scs.ai.visual;

import android.os.IInterface;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor;
import com.samsung.android.sdk.scs.ai.gateway.imageeditor.ImageEditorGateway;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorReleaseRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "ImageEditorReleaseRunnable";
    private final int mMode;
    AiServiceExecutor<? extends IInterface> mServiceExecutor;

    public ImageEditorReleaseRunnable(AiServiceExecutor<? extends IInterface> aiServiceExecutor, int i2) {
        this.mServiceExecutor = aiServiceExecutor;
        this.mMode = i2;
    }

    public void execute() {
        Log.i(TAG, "execute");
        try {
            new ImageEditorGateway(this.mServiceExecutor).release(getMode());
            this.mSource.setResult(Boolean.TRUE);
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
}
