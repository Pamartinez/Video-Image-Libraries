package com.samsung.android.sdk.scs.ai.language;

import B8.k;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.ToneConvertServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.c0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ToneConverter {
    private static final String TAG = "ToneConverter";
    protected String featureName = Feature.FEATURE_AI_GEN_TONE;
    protected ToneConvertServiceExecutor mServiceExecutor;
    private final OnDeviceServiceExecutor onDeviceServiceExecutor;

    public ToneConverter(Context context) {
        Log.d(TAG, TAG);
        this.onDeviceServiceExecutor = new OnDeviceServiceExecutor(context);
        createExecutor(context);
    }

    /* access modifiers changed from: private */
    public void lambda$release$2(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            c0 c0Var = (c0) this.mServiceExecutor.getService();
            c0Var.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IToneConvertService");
            c0Var.f1699a.transact(5, obtain, (Parcel) null, 1);
            obtain.recycle();
            llmServiceObserver2.onNext(new ArrayList());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$3(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
        this.onDeviceServiceExecutor.deInit();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toneConvert$0(AppInfo appInfo, String str, ToneType toneType, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((c0) this.mServiceExecutor.getService()).b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, toneType.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toneConvert$1(AppInfo appInfo, String str, String str2, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((c0) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, str2, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new ToneConvertServiceExecutor(context);
        this.featureName = Feature.FEATURE_AI_GEN_TONE;
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, false, new C0582a(25, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new a(3, this));
    }

    public Task<Result> toneConvert(AppInfo appInfo, String str, ToneType toneType) {
        return toneConvert(appInfo, str, toneType, (Map<String, String>) new HashMap());
    }

    @Deprecated
    public void unLoadOndeviceModel(AppInfo appInfo) {
        OnDeviceRunnable onDeviceRunnable = new OnDeviceRunnable(this.onDeviceServiceExecutor);
        onDeviceRunnable.setAppInfo(appInfo);
        this.onDeviceServiceExecutor.execute(onDeviceRunnable);
    }

    public Task<Result> toneConvert(AppInfo appInfo, String str, ToneType toneType, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new k(this, appInfo, str, toneType, map, 15), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> toneConvert(AppInfo appInfo, String str, String str2) {
        return toneConvert(appInfo, str, str2, (Map<String, String>) new HashMap());
    }

    public Task<Result> toneConvert(AppInfo appInfo, String str, String str2, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new k(this, appInfo, str, str2, map, 16), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
