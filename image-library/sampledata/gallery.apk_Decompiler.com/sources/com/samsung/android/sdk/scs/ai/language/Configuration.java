package com.samsung.android.sdk.scs.ai.language;

import A4.C0371f;
import A4.S;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable;
import com.samsung.android.sdk.scs.ai.language.service.ConfigurationServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0158e;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Configuration {
    private static final String TAG = "Configuration";
    public static final String TASK_STRUCTURED_DATA_EXTRACTION = "structured_data_extraction";
    private final String METADATA_FUNCTION_INFO = "com.samsung.android.offline.languagemodel.FUNCTION_INFO";
    private final String PACKAGE_LANGUAGE_PACK = "com.samsung.android.offline.languagemodel";
    private final Context mContext;
    private final ConfigurationServiceExecutor mServiceExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface OnDeviceLlmTaskName {
    }

    public Configuration(Context context) {
        Log.d(TAG, TAG);
        this.mContext = context;
        this.mServiceExecutor = new ConfigurationServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getModelInfo$0(AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0158e) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prewarmLlm$1(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0158e) this.mServiceExecutor.getService()).f(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Task<String> getConfig(AppInfo appInfo) {
        ConfigurationRunnable configurationRunnable = new ConfigurationRunnable(this.mServiceExecutor);
        configurationRunnable.setAppInfo(appInfo);
        configurationRunnable.setType(ConfigurationRunnable.ConfigType.APP);
        this.mServiceExecutor.execute(configurationRunnable);
        return configurationRunnable.getTask();
    }

    public Task<String> getLlmSupportLanguage(AppInfo appInfo) {
        ConfigurationRunnable configurationRunnable = new ConfigurationRunnable(this.mServiceExecutor);
        configurationRunnable.setAppInfo(appInfo);
        configurationRunnable.setType(ConfigurationRunnable.ConfigType.LLM);
        this.mServiceExecutor.execute(configurationRunnable);
        return configurationRunnable.getTask();
    }

    public Task<Result> getModelInfo(AppInfo appInfo) {
        return getModelInfo(appInfo, new HashMap());
    }

    public String getOndeviceCapability() {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo("com.samsung.android.offline.languagemodel", 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return "";
            }
            return bundle.getString("com.samsung.android.offline.languagemodel.FUNCTION_INFO");
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "language pack is not installed");
            return "";
        }
    }

    public Task<String> getOndeviceSafetyCheck(String str) {
        ConfigurationRunnable configurationRunnable = new ConfigurationRunnable(this.mServiceExecutor);
        configurationRunnable.setAppInfo(new AppInfo.Builder().setRequestType(AppInfo.RequestType.ONDEVICE).build());
        configurationRunnable.setType(ConfigurationRunnable.ConfigType.SAFE);
        configurationRunnable.setInputText(str);
        this.mServiceExecutor.execute(configurationRunnable);
        return configurationRunnable.getTask();
    }

    public Task<String> getTranslateSupportLanguage() {
        ConfigurationRunnable configurationRunnable = new ConfigurationRunnable(this.mServiceExecutor);
        configurationRunnable.setType(ConfigurationRunnable.ConfigType.LPD);
        this.mServiceExecutor.execute(configurationRunnable);
        return configurationRunnable.getTask();
    }

    public Task<Result> prewarmLlm(AppInfo appInfo, String str) {
        return prewarmLlm(appInfo, str, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> getModelInfo(AppInfo appInfo, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_ONDEVICE_LLM_PREWARM, false, new C0371f((Object) this, (Object) appInfo, (Object) map, 28), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> prewarmLlm(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_ONDEVICE_LLM_PREWARM, false, new S(this, appInfo, str, map, 6), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
