package com.samsung.android.sdk.scs.ai.language.service;

import android.os.RemoteException;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.data.LlmCloudUsage;
import com.samsung.android.sdk.scs.ai.language.data.LlmCloudUsageItem;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sivs.ai.sdkcommon.language.j0;
import com.samsung.android.sivs.ai.sdkcommon.language.l0;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1203u;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\n\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ5\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0007¢\u0006\u0004\b\u001d\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b \u0010\u001fR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b\"\u0010#R.\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070$8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0016\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010+R\u0016\u0010\u0013\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010+R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010,R\u0016\u0010\u0016\u001a\u00020\u00148\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010,¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestRunnable;", "Lcom/samsung/android/sdk/scs/base/tasks/TaskRunnable;", "Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsage;", "Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;", "serviceExecutor", "<init>", "(Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;)V", "", "result", "parseUsageData", "(Ljava/lang/String;)Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsage;", "getFeatureName", "()Ljava/lang/String;", "Lme/x;", "execute", "()V", "Lcom/samsung/android/sdk/scs/ai/language/AppInfo;", "appInfo", "domain", "packageName", "", "yyyymmddStart", "yyyymmddEnd", "setParams", "(Lcom/samsung/android/sdk/scs/ai/language/AppInfo;Ljava/lang/String;Ljava/lang/String;II)V", "setAppInfo", "(Lcom/samsung/android/sdk/scs/ai/language/AppInfo;)V", "setDomain", "(Ljava/lang/String;)V", "setPackageName", "setYyyymmddStart", "(I)V", "setYyyymmddEnd", "Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;", "getServiceExecutor", "()Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;", "", "authHeader", "Ljava/util/Map;", "getAuthHeader", "()Ljava/util/Map;", "setAuthHeader", "(Ljava/util/Map;)V", "Ljava/lang/String;", "I", "Companion", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageRequestRunnable extends TaskRunnable<LlmCloudUsage> {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "UsageRunnable";
    private Map<String, String> authHeader = C1203u.d;
    private String domain = "";
    private String packageName = "";
    private final LlmCloudUsageRequestExecutor serviceExecutor;
    private int yyyymmddEnd;
    private int yyyymmddStart;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestRunnable$Companion;", "", "<init>", "()V", "TAG", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LlmCloudUsageRequestRunnable(LlmCloudUsageRequestExecutor llmCloudUsageRequestExecutor) {
        j.e(llmCloudUsageRequestExecutor, "serviceExecutor");
        this.serviceExecutor = llmCloudUsageRequestExecutor;
    }

    /* access modifiers changed from: private */
    public final LlmCloudUsage parseUsageData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        long j2 = jSONObject.getLong("remainingUsage");
        JSONArray jSONArray = jSONObject.getJSONArray("usage");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            String string = jSONArray.getJSONObject(i2).getString(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
            j.d(string, "getString(...)");
            String string2 = jSONArray.getJSONObject(i2).getString("domain");
            j.d(string2, "getString(...)");
            arrayList.add(new LlmCloudUsageItem(string, string2, jSONArray.getJSONObject(i2).getLong("usedUsage")));
        }
        return new LlmCloudUsage(j2, arrayList);
    }

    public void execute() {
        try {
            LlmCloudUsageRequestRunnable$execute$observer$1 llmCloudUsageRequestRunnable$execute$observer$1 = new LlmCloudUsageRequestRunnable$execute$observer$1(this);
            l0 service = this.serviceExecutor.getService();
            if (service != null) {
                ((j0) service).a(this.authHeader, this.domain, this.packageName, this.yyyymmddStart, this.yyyymmddEnd, llmCloudUsageRequestRunnable$execute$observer$1);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public final Map<String, String> getAuthHeader() {
        return this.authHeader;
    }

    public String getFeatureName() {
        return Feature.FEATURE_AI_GEN_USAGE;
    }

    public final LlmCloudUsageRequestExecutor getServiceExecutor() {
        return this.serviceExecutor;
    }

    public final void setAppInfo(AppInfo appInfo) {
        j.e(appInfo, "appInfo");
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.serviceExecutor.getContext());
    }

    public final void setAuthHeader(Map<String, String> map) {
        j.e(map, "<set-?>");
        this.authHeader = map;
    }

    public final void setDomain(String str) {
        j.e(str, "domain");
        this.domain = str;
    }

    public final void setPackageName(String str) {
        j.e(str, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        this.packageName = str;
    }

    public final void setParams(AppInfo appInfo, String str, String str2, int i2, int i7) {
        j.e(appInfo, "appInfo");
        j.e(str, "domain");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.serviceExecutor.getContext());
        this.domain = str;
        this.packageName = str2;
        this.yyyymmddStart = i2;
        this.yyyymmddEnd = i7;
    }

    public final void setYyyymmddEnd(int i2) {
        this.yyyymmddEnd = i2;
    }

    public final void setYyyymmddStart(int i2) {
        this.yyyymmddStart = i2;
    }
}
