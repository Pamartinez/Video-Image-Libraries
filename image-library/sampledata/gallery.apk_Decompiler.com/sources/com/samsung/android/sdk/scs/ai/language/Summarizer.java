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
import com.samsung.android.sdk.scs.ai.language.service.SummarizationServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.Z;
import com.samsung.android.sivs.ai.sdkcommon.language.b0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Summarizer {
    private static final String TAG = "Summarizer";
    protected String featureName = Feature.FEATURE_AI_GEN_SUMMARY;
    protected SummarizationServiceExecutor mServiceExecutor;
    private final OnDeviceServiceExecutor onDeviceServiceExecutor;

    public Summarizer(Context context) {
        Log.d(TAG, TAG);
        this.onDeviceServiceExecutor = new OnDeviceServiceExecutor(context);
        createExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getTokenCount$2(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((Z) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, summarizeLevel.name(), summarizeSubTask.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$3(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            Z z = (Z) this.mServiceExecutor.getService();
            z.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISummarizationService");
            z.f1694a.transact(5, obtain, (Parcel) null, 1);
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
        this.onDeviceServiceExecutor.deInit();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$summarize$0(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((Z) this.mServiceExecutor.getService()).d(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, summarizeLevel.name(), summarizeSubTask.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$summarizeOnDevice$1(AppInfo appInfo, String str, SummaryOnDeviceType summaryOnDeviceType, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            if (Feature.checkFeature(this.mServiceExecutor.context, Feature.FEATURE_AI_ONDEVICE_SUMMARY_EXTRA_PROMPT) == 0) {
                ((Z) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, summaryOnDeviceType.getName(), llmServiceObserver2, map);
                return;
            }
            b0 service = this.mServiceExecutor.getService();
            Z z = (Z) service;
            z.b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, summaryOnDeviceType.getName(), llmServiceObserver2);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new SummarizationServiceExecutor(context);
        this.featureName = Feature.FEATURE_AI_GEN_SUMMARY;
    }

    public Task<Result> getTokenCount(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask) {
        return getTokenCount(appInfo, str, summarizeLevel, summarizeSubTask, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, false, new C0582a(24, this), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        llmServiceRunnable.getTask().addOnCompleteListener(new a(2, this));
    }

    public Task<Result> summarize(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask) {
        return summarize(appInfo, str, summarizeLevel, summarizeSubTask, new HashMap());
    }

    public Task<Result> summarizeOnDevice(AppInfo appInfo, String str, SummaryOnDeviceType summaryOnDeviceType, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new k(this, appInfo, str, summaryOnDeviceType, map, 14), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    @Deprecated
    public void unLoadOndeviceModel(AppInfo appInfo) {
        OnDeviceRunnable onDeviceRunnable = new OnDeviceRunnable(this.onDeviceServiceExecutor);
        onDeviceRunnable.setAppInfo(appInfo);
        this.onDeviceServiceExecutor.execute(onDeviceRunnable);
    }

    public Task<Result> getTokenCount(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new b(this, appInfo, str, summarizeLevel, summarizeSubTask, map, 0), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> summarize(AppInfo appInfo, String str, SummarizeLevel summarizeLevel, SummarizeSubTask summarizeSubTask, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(this.featureName, appInfo.isStreamingMode(), new b(this, appInfo, str, summarizeLevel, summarizeSubTask, map, 1), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> summarizeOnDevice(AppInfo appInfo, String str, SummaryOnDeviceType summaryOnDeviceType) {
        return summarizeOnDevice(appInfo, str, summaryOnDeviceType, new HashMap());
    }
}
