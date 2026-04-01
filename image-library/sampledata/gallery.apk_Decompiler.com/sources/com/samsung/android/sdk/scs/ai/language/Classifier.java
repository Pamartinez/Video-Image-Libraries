package com.samsung.android.sdk.scs.ai.language;

import A4.S;
import android.content.Context;
import android.os.RemoteException;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.ClassificationServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0155b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Classifier {
    private static final String TAG = "Classifier";
    private final ClassificationServiceExecutor mServiceExecutor;

    public Classifier(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new ClassificationServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$classify$0(AppInfo appInfo, String str, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0155b) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Task<Result> classify(AppInfo appInfo, String str) {
        return classify(appInfo, str, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> classify(AppInfo appInfo, String str, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_SIVS_CLASSIFICATION, appInfo.isStreamingMode(), new S(this, appInfo, str, map, 5), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
