package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.ai.language.ResultErrorException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0158e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConfigurationRunnable extends TaskRunnable<String> {
    private static final String TAG = "ConfigurationRunnable";
    Map<String, String> authHeader = new HashMap();
    private ConfigType configType;
    private String inputText;
    ConfigurationServiceExecutor mServiceExecutor;

    /* renamed from: com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$ConfigType[] r0 = com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.ConfigType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType = r0
                com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$ConfigType r1 = com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.ConfigType.LLM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$ConfigType r1 = com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.ConfigType.LPD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$ConfigType r1 = com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.ConfigType.SAFE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable$ConfigType r1 = com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.ConfigType.APP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.ConfigurationRunnable.AnonymousClass3.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ConfigType {
        APP,
        LPD,
        LLM,
        SAFE
    }

    public ConfigurationRunnable(ConfigurationServiceExecutor configurationServiceExecutor) {
        this.mServiceExecutor = configurationServiceExecutor;
    }

    public void execute() {
        try {
            AnonymousClass1 r0 = new LlmServiceObserver() {
                public void onError(Bundle bundle) {
                    if (bundle == null) {
                        Log.e(ConfigurationRunnable.TAG, "onError= error is null");
                        C0086a.t(5, "error is null", ConfigurationRunnable.this.mSource);
                        return;
                    }
                    Log.e(ConfigurationRunnable.TAG, "onError= " + bundle.getInt("error_code") + bundle.getString("error_message"));
                    ConfigurationRunnable.this.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
                }

                public void onNext(String str) {
                    ConfigurationRunnable.this.mSource.setResult(str);
                }

                public void onComplete() {
                }
            };
            AnonymousClass2 r1 = new LlmServiceObserver2() {
                public void onError(Bundle bundle) {
                    if (bundle == null) {
                        Log.e(ConfigurationRunnable.TAG, "onError= error is null");
                        C0086a.t(5, "error is null", ConfigurationRunnable.this.mSource);
                        return;
                    }
                    Log.e(ConfigurationRunnable.TAG, "onError= " + bundle.getInt("error_code") + bundle.getString("error_message"));
                    ConfigurationRunnable.this.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
                }

                public void onNext(List<Bundle> list) {
                    ConfigurationRunnable.this.mSource.setResult(new Result(list.get(0)).getSafetyAttribute());
                }

                public void onComplete() {
                }
            };
            int i2 = AnonymousClass3.$SwitchMap$com$samsung$android$sdk$scs$ai$language$service$ConfigurationRunnable$ConfigType[this.configType.ordinal()];
            if (i2 == 1) {
                ((C0158e) this.mServiceExecutor.getService()).b(this.authHeader, r0);
            } else if (i2 == 2) {
                ((C0158e) this.mServiceExecutor.getService()).e(this.authHeader, r0);
            } else if (i2 != 3) {
                ((C0158e) this.mServiceExecutor.getService()).a(this.authHeader, r0);
            } else {
                ((C0158e) this.mServiceExecutor.getService()).d(this.authHeader, this.inputText, r1);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_SIVS_CONFIGURATION;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
    }

    public void setInputText(String str) {
        this.inputText = str;
    }

    public void setType(ConfigType configType2) {
        this.configType = configType2;
    }
}
