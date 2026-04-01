package com.samsung.android.sdk.scs.ai.language;

import B8.k;
import a6.g;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.CorrectionServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.OnDeviceServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0161h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Corrector {
    private static final String TAG = "Corrector";
    protected String featureName = Feature.FEATURE_AI_GEN_CORRECTION;
    protected CorrectionServiceExecutor mServiceExecutor;
    private final OnDeviceServiceExecutor onDeviceServiceExecutor;

    public Corrector(Context context) {
        Log.d(TAG, TAG);
        this.onDeviceServiceExecutor = new OnDeviceServiceExecutor(context);
        createExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$correct$0(AppInfo appInfo, String str, CorrectionSubTask correctionSubTask, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0161h) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, correctionSubTask.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$1(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            C0161h hVar = (C0161h) this.mServiceExecutor.getService();
            hVar.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ICorrectionService");
            hVar.f1705a.transact(5, obtain, (Parcel) null, 1);
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
    public /* synthetic */ void lambda$release$2(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
        this.onDeviceServiceExecutor.deInit();
    }

    public Task<Result> correct(AppInfo appInfo, String str, CorrectionSubTask correctionSubTask) {
        return correct(appInfo, str, correctionSubTask, new HashMap());
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new CorrectionServiceExecutor(context);
        this.featureName = Feature.FEATURE_AI_GEN_CORRECTION;
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, false, new C0582a(17, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new g(25, this));
    }

    @Deprecated
    public void unLoadOndeviceModel(AppInfo appInfo) {
        OnDeviceRunnable onDeviceRunnable = new OnDeviceRunnable(this.onDeviceServiceExecutor);
        onDeviceRunnable.setAppInfo(appInfo);
        this.onDeviceServiceExecutor.execute(onDeviceRunnable);
    }

    public Task<Result> correct(AppInfo appInfo, String str, CorrectionSubTask correctionSubTask, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new k(this, appInfo, str, correctionSubTask, map, 5), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
