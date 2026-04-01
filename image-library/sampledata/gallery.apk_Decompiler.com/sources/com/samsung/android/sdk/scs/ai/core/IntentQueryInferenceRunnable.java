package com.samsung.android.sdk.scs.ai.core;

import a3.C0077a;
import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.visual.VisualErrorCode;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntentQueryInferenceRunnable extends TaskRunnable<IntentQueryResult> {
    private static final String TAG = "IntentQueryInferenceRunnable";
    private String mFeature;
    private String mInputArg;
    private int mMode;
    private final Bundle mRequestBundle = new Bundle();
    private final ExtensionServiceExecutor mServiceExecutor;

    public IntentQueryInferenceRunnable(ExtensionServiceExecutor extensionServiceExecutor) {
        this.mServiceExecutor = extensionServiceExecutor;
        this.mMode = 1000;
    }

    private int getMode() {
        return this.mMode;
    }

    private void setResult(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "generate(). retBundle is null!!");
            C0086a.t(5, "retBundle is null", this.mSource);
            return;
        }
        IntentQueryResult intentQueryResult = new IntentQueryResult();
        int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        intentQueryResult.setResult(i2);
        if (i2 != 1) {
            C0086a.u(i2, "generate(). Abnormal resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                C0086a.t(bundle.getInt("errorCode", VisualErrorCode.NOT_SPECIFIED.getCode()), bundle.getString("errorMessage", ""), this.mSource);
            }
        } else {
            intentQueryResult.setBundle(bundle);
            this.mSource.setResult(intentQueryResult);
        }
    }

    public void execute() {
        Log.d(TAG, "IntentQueryGenerateRunnable execute()");
        if (getFeatureName() == null || getBundle() == null) {
            C0086a.s(700, this.mSource);
            return;
        }
        try {
            ExtensionParamUtils.transformBitmapsInBundle(getBundle());
            ExtensionParamUtils.setBundleFeature(getBundle(), getFeature());
            ExtensionParamUtils.setBundleInputArg(getBundle(), getInputArg());
            ExtensionParamUtils.setBundleTaskId(getBundle(), getTask().getTaskId());
            setResult(((C0077a) this.mServiceExecutor.getExtensionService()).a(getMode(), new Bundle(getBundle())));
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public Bundle getBundle() {
        return this.mRequestBundle;
    }

    public String getFeature() {
        return this.mFeature;
    }

    public String getFeatureName() {
        return Feature.FEATURE_CORE_EXT_INTENT_QUERY;
    }

    public String getInputArg() {
        return this.mInputArg;
    }

    public void setBundle(Bundle bundle) {
        this.mRequestBundle.putAll(bundle);
    }

    public void setFeature(String str) {
        this.mFeature = str;
    }

    public void setInputArg(String str) {
        this.mInputArg = str;
    }

    public void setInputData(Bundle bundle) {
        setBundle(bundle);
    }
}
