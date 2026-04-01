package com.samsung.android.sdk.scs.ai.language;

import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.samsung.android.sdk.scs.ai.language.data.LlmCloudUsage;
import com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestRunnable;
import com.samsung.android.sdk.scs.base.tasks.Task;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J4\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/LlmCloudUsageRequest;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "serviceExecutor", "Lcom/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor;", "requestUsage", "Lcom/samsung/android/sdk/scs/base/tasks/Task;", "Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsage;", "appInfo", "Lcom/samsung/android/sdk/scs/ai/language/AppInfo;", "domain", "", "packageName", "yyyymmddStart", "", "yyyymmddEnd", "Companion", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageRequest {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "Usage";
    private final Context context;
    private LlmCloudUsageRequestExecutor serviceExecutor;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/LlmCloudUsageRequest$Companion;", "", "<init>", "()V", "TAG", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LlmCloudUsageRequest(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        this.serviceExecutor = new LlmCloudUsageRequestExecutor(context2);
        Log.d(TAG, TAG);
    }

    public final Context getContext() {
        return this.context;
    }

    public final Task<LlmCloudUsage> requestUsage(AppInfo appInfo, String str, String str2, int i2, int i7) {
        j.e(appInfo, "appInfo");
        j.e(str, "domain");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        LlmCloudUsageRequestRunnable llmCloudUsageRequestRunnable = new LlmCloudUsageRequestRunnable(this.serviceExecutor);
        llmCloudUsageRequestRunnable.setAppInfo(appInfo);
        llmCloudUsageRequestRunnable.setDomain(str);
        llmCloudUsageRequestRunnable.setPackageName(str2);
        llmCloudUsageRequestRunnable.setYyyymmddStart(i2);
        llmCloudUsageRequestRunnable.setYyyymmddEnd(i7);
        this.serviceExecutor.execute(llmCloudUsageRequestRunnable);
        Task<LlmCloudUsage> task = llmCloudUsageRequestRunnable.getTask();
        j.d(task, "getTask(...)");
        return task;
    }
}
