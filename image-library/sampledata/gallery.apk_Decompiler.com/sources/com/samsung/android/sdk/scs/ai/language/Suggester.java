package com.samsung.android.sdk.scs.ai.language;

import A4.S;
import B8.k;
import android.content.Context;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.gallery.support.utils.K;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.SuggestionServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.T;
import com.samsung.android.sivs.ai.sdkcommon.language.V;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Suggester {
    private static final String TAG = "Suggester";
    private final String featureName = Feature.FEATURE_AI_GEN_SUGGESTION;
    private final SuggestionServiceExecutor mServiceExecutor;

    public Suggester(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new SuggestionServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public void lambda$release$4(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            T t = (T) this.mServiceExecutor.getService();
            t.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            t.f1690a.transact(3, obtain, (Parcel) null, 1);
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
    public /* synthetic */ void lambda$release$5(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reportGeneration$3(AppInfo appInfo, String str, String str2, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((T) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, str2, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$suggestion$0(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((T) this.mServiceExecutor.getService()).d(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$suggestion$1(AppInfo.RequestType requestType, AppInfo appInfo, String str, SuggestionCategory suggestionCategory, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            if (requestType == AppInfo.RequestType.CLOUD) {
                ((T) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, suggestionCategory.getName(), llmServiceObserver2, map);
                return;
            }
            V service = this.mServiceExecutor.getService();
            Map<String, String> generateHeaderMap = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
            String name = suggestionCategory.getName();
            T t = (T) service;
            t.e(generateHeaderMap, str, name, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$suggestion$2(SuggestionInputParams suggestionInputParams, AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            Context context = this.mServiceExecutor.context;
            ParcelFileDescriptor parcelFileDescriptor = null;
            if (suggestionInputParams.getData() != null) {
                File file = new File(context.getFilesDir().getAbsolutePath(), "suggest_image");
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        Log.d(TAG, "suggest_image folder creation failed");
                    }
                }
                File file2 = new File(file, "suggest_image_file");
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(suggestionInputParams.getData());
                    parcelFileDescriptor = ParcelFileDescriptor.open(file2, 268435456);
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "datafile failure: " + e.getMessage());
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
            ((T) this.mServiceExecutor.getService()).b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), suggestionInputParams.getInputText(), suggestionInputParams.getCategory().getName(), suggestionInputParams.getDataMimeType(), parcelFileDescriptor2, llmServiceObserver2, map);
            return;
            throw th;
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        if (Feature.checkFeature(this.mServiceExecutor.context, Feature.FEATURE_AI_GEN_SUGGESTION_ONDEVICE) == 0) {
            LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SUGGESTION, false, new C0582a(22, this), new C0431a(22));
            this.mServiceExecutor.execute(llmServiceRunnable);
            llmServiceRunnable.getTask().addOnCompleteListener(new a(0, this));
            return;
        }
        this.mServiceExecutor.deInit();
    }

    public Task<Result> reportGeneration(AppInfo appInfo, String str, String str2, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SUGGESTION, appInfo.isStreamingMode(), new k(this, appInfo, str, str2, map, 13), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> suggestion(AppInfo appInfo, String str) {
        return suggestion(appInfo, str, (Map<String, String>) new HashMap());
    }

    public Task<Result> suggestion(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SUGGESTION, appInfo.isStreamingMode(), new S(this, appInfo, str, map, 10), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> suggestion(AppInfo appInfo, String str, SuggestionCategory suggestionCategory) {
        return suggestion(appInfo, str, suggestionCategory, new HashMap());
    }

    public Task<Result> suggestion(AppInfo appInfo, String str, SuggestionCategory suggestionCategory, Map<String, String> map) {
        String str2;
        AppInfo.RequestType requestType = appInfo.getRequestType();
        if (requestType == AppInfo.RequestType.CLOUD) {
            str2 = Feature.FEATURE_AI_GEN_SUGGESTION;
        } else {
            str2 = Feature.FEATURE_AI_GEN_SUGGESTION_ONDEVICE;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str2, appInfo.isStreamingMode(), new K(this, requestType, appInfo, str, suggestionCategory, map, 1), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> suggestion(AppInfo appInfo, SuggestionInputParams suggestionInputParams) {
        return suggestion(appInfo, suggestionInputParams, (Map<String, String>) new HashMap());
    }

    public Task<Result> suggestion(AppInfo appInfo, SuggestionInputParams suggestionInputParams, Map<String, String> map) {
        String str;
        if (appInfo.getRequestType() == AppInfo.RequestType.CLOUD) {
            str = Feature.FEATURE_AI_GEN_SUGGESTION;
        } else {
            str = Feature.FEATURE_AI_GEN_SUGGESTION_ONDEVICE;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str, appInfo.isStreamingMode(), new S(this, suggestionInputParams, appInfo, map, 11), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
