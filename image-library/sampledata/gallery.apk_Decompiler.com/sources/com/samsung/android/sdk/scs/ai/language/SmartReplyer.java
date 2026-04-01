package com.samsung.android.sdk.scs.ai.language;

import A4.C0371f;
import A4.S;
import B8.k;
import a6.g;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.SmartReplyServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.P;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartReplyer {
    private static final String TAG = "SmartReplyer";
    protected String featureName = Feature.FEATURE_AI_GEN_SMART_REPLY;
    protected SmartReplyServiceExecutor mServiceExecutor;

    public SmartReplyer(Context context) {
        Log.d(TAG, TAG);
        createExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$identifyLanguage$2(AppInfo appInfo, String str, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((P) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$3(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            P p6 = (P) this.mServiceExecutor.getService();
            p6.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISmartReplyService");
            p6.f1688a.transact(5, obtain, (Parcel) null, 1);
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
    public /* synthetic */ void lambda$release$4(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$smartReply$0(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((P) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$smartReply$1(AppInfo appInfo, String str, SmartReplyCategory smartReplyCategory, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((P) this.mServiceExecutor.getService()).b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, smartReplyCategory.getName(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new SmartReplyServiceExecutor(context);
        this.featureName = Feature.FEATURE_AI_GEN_SMART_REPLY;
    }

    public Task<Result> identifyLanguage(AppInfo appInfo, String str) {
        Log.i(TAG, "identifyLanguage: " + str);
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new C0371f((Object) this, (Object) appInfo, (Object) str, 29), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, false, new C0582a(21, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new g(29, this));
    }

    public Task<List<Result>> smartReply(AppInfo appInfo, String str) {
        return smartReply(appInfo, str, (Map<String, String>) new HashMap());
    }

    public Task<List<Result>> smartReply(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new S(this, appInfo, str, map, 9), new C0431a(23));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<List<Result>> smartReply(AppInfo appInfo, String str, SmartReplyCategory smartReplyCategory) {
        return smartReply(appInfo, str, smartReplyCategory, new HashMap());
    }

    public Task<List<Result>> smartReply(AppInfo appInfo, String str, SmartReplyCategory smartReplyCategory, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new k(this, appInfo, str, smartReplyCategory, map, 12), new C0431a(23));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
