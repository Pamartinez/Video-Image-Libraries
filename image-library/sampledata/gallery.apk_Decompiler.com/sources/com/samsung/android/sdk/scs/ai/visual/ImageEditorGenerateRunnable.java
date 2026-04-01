package com.samsung.android.sdk.scs.ai.visual;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcelable;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor;
import com.samsung.android.sdk.scs.ai.gateway.imageeditor.ImageEditorGateway;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorGenerateRunnable extends TaskRunnable<ImageEditorResult> {
    private static final String TAG = "ImageEditorGenerateRunnable";
    private VisualAppInfo mAppInfo;
    private final int mMode;
    private final Bundle mRequestBundle = new Bundle();
    private final AiServiceExecutor<? extends IInterface> mServiceExecutor;

    public ImageEditorGenerateRunnable(AiServiceExecutor<? extends IInterface> aiServiceExecutor, int i2) {
        this.mServiceExecutor = aiServiceExecutor;
        this.mMode = i2;
    }

    private void integrateSingleOutputToList(Bundle bundle) {
        Parcelable parcelable;
        ArrayList arrayList;
        if (bundle.containsKey("outputBitmap") && (parcelable = bundle.getParcelable("outputBitmap")) != null) {
            if (bundle.containsKey("outputBitmapList")) {
                arrayList = bundle.getParcelableArrayList("outputBitmapList");
            } else {
                arrayList = null;
            }
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(parcelable);
            bundle.putParcelableArrayList("outputBitmapList", arrayList);
        }
    }

    private void setResult(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "generate(). retBundle is null!!");
            C0086a.t(5, "retBundle is null", this.mSource);
            return;
        }
        ImageEditorResult imageEditorResult = new ImageEditorResult();
        int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        imageEditorResult.setResult(i2);
        if (i2 != 1) {
            C0086a.u(i2, "generate(). Abnormal resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
                return;
            }
            this.mSource.setException(new VisualResultErrorException(bundle.getInt("errorCode", VisualErrorCode.NOT_SPECIFIED.getCode()), bundle.getString("errorMessage", "")));
            return;
        }
        integrateSingleOutputToList(bundle);
        imageEditorResult.setBundle(bundle);
        this.mSource.setResult(imageEditorResult);
    }

    public void execute() {
        Log.d(TAG, "generate()");
        if (getBundle() == null) {
            C0086a.s(700, this.mSource);
            return;
        }
        try {
            ImageEditorParamUtils.toBundle(getBundle(), this.mAppInfo);
            ImageEditorParamUtils.transformBitmapsInBundle(getBundle());
            getBundle().putString("taskId", getTask().getTaskId());
            setResult(new ImageEditorGateway(this.mServiceExecutor).generate(getMode(), new Bundle(getBundle())));
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public Bundle getBundle() {
        return this.mRequestBundle;
    }

    public String getFeatureName() {
        return ImageEditorParamUtils.getFeatureName(getMode(), this.mServiceExecutor.getServiceType(), getBundle());
    }

    public int getMode() {
        return this.mMode;
    }

    public void setAppInfo(VisualAppInfo visualAppInfo) {
        this.mAppInfo = visualAppInfo;
    }

    public void setBundle(Bundle bundle) {
        this.mRequestBundle.putAll(bundle);
    }
}
