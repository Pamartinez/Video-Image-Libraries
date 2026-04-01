package com.samsung.android.sdk.scs.ai.visual;

import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.ai.gateway.LvmServiceExecutorContainer;
import com.samsung.android.sdk.scs.ai.gateway.LvmServiceExecutorFactory;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorGenerator extends LvmServiceExecutorContainer {
    private static final String TAG = "ScsApi@ImageEditorGenerator";
    private final int mMode;
    private final AiServiceExecutorFactory.ServiceType mServiceType;

    public ImageEditorGenerator(Context context, int i2) {
        this(context, i2, RequestType.CLOUD);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Task task) {
        super.release();
    }

    public Task<Boolean> cancel(String str) {
        Log.d(TAG, "cancel()");
        ImageEditorCancelRunnable imageEditorCancelRunnable = new ImageEditorCancelRunnable(getCancelServiceExecutor(this.mServiceType), this.mMode);
        imageEditorCancelRunnable.setTaskId(str);
        getCancelServiceExecutor(this.mServiceType).execute(imageEditorCancelRunnable);
        return imageEditorCancelRunnable.getTask();
    }

    public AiServiceExecutor<? extends IInterface> createCancelServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType) {
        return new LvmServiceExecutorFactory(getContext()).createImageEditor(serviceType, ImageEditorParamUtils.getFeatureName(this.mMode, serviceType));
    }

    public AiServiceExecutor<? extends IInterface> createServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType) {
        return new LvmServiceExecutorFactory(getContext()).createImageEditor(serviceType, ImageEditorParamUtils.getFeatureName(this.mMode, serviceType));
    }

    public Task<ImageEditorResult> generate(VisualAppInfo visualAppInfo, Bundle bundle) {
        Log.d(TAG, "generate())");
        ImageEditorGenerateRunnable imageEditorGenerateRunnable = new ImageEditorGenerateRunnable(getServiceExecutor(this.mServiceType), this.mMode);
        imageEditorGenerateRunnable.setAppInfo(visualAppInfo);
        imageEditorGenerateRunnable.setBundle(bundle);
        getServiceExecutor(this.mServiceType).execute(imageEditorGenerateRunnable);
        return imageEditorGenerateRunnable.getTask();
    }

    public Task<Boolean> prepare() {
        Log.d(TAG, "prepare()");
        ImageEditorPrepareRunnable imageEditorPrepareRunnable = new ImageEditorPrepareRunnable(getServiceExecutor(this.mServiceType), this.mMode);
        getServiceExecutor(this.mServiceType).execute(imageEditorPrepareRunnable);
        return imageEditorPrepareRunnable.getTask();
    }

    public Task<Boolean> release() {
        Log.d(TAG, "release()");
        ImageEditorReleaseRunnable imageEditorReleaseRunnable = new ImageEditorReleaseRunnable(getServiceExecutor(this.mServiceType), this.mMode);
        getServiceExecutor(this.mServiceType).execute(imageEditorReleaseRunnable);
        imageEditorReleaseRunnable.getTask().addOnCompleteListener(new a(5, this));
        return imageEditorReleaseRunnable.getTask();
    }

    public ImageEditorGenerator(Context context, int i2, RequestType requestType) {
        super(context);
        Log.d(TAG, "ImageEditorGenerator");
        this.mMode = i2;
        this.mServiceType = getServiceType(requestType);
    }
}
