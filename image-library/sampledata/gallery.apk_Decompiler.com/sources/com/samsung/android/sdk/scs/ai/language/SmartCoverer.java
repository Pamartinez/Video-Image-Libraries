package com.samsung.android.sdk.scs.ai.language;

import A4.S;
import a6.g;
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
import com.samsung.android.sdk.scs.ai.language.service.SmartCoverServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.L;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartCoverer {
    private static final String TAG = "SmartCoverer";
    private final String featureName = Feature.FEATURE_AI_GEN_SMART_COVER;
    private final SmartCoverServiceExecutor mServiceExecutor;
    private final OnDeviceServiceExecutor onDeviceServiceExecutor;

    public SmartCoverer(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new SmartCoverServiceExecutor(context);
        this.onDeviceServiceExecutor = new OnDeviceServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public void lambda$release$1(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            L l = (L) this.mServiceExecutor.getService();
            l.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISmartCoverService");
            l.f1685a.transact(5, obtain, (Parcel) null, 1);
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$smartCover$0(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((L) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SMART_COVER, false, new C0582a(20, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new g(28, this));
    }

    public Task<Result> smartCover(AppInfo appInfo, String str) {
        return smartCover(appInfo, str, new HashMap());
    }

    @Deprecated
    public void unLoadOndeviceModel(AppInfo appInfo) {
        OnDeviceRunnable onDeviceRunnable = new OnDeviceRunnable(this.onDeviceServiceExecutor);
        onDeviceRunnable.setAppInfo(appInfo);
        this.onDeviceServiceExecutor.execute(onDeviceRunnable);
    }

    public Task<Result> smartCover(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SMART_COVER, appInfo.isStreamingMode(), new S(this, appInfo, str, map, 8), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
