package com.samsung.android.sdk.scs.ai.language;

import B8.k;
import android.content.Context;
import android.os.RemoteException;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.FormatConversionServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0170q;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FormatConverter {
    private static final String TAG = "FormatConverter";
    private final String featureName = Feature.FEATURE_AI_GEN_SUGGESTION;
    private final FormatConversionServiceExecutor mServiceExecutor;

    public FormatConverter(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new FormatConversionServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convert$0(AppInfo appInfo, String str, FormatConversionType formatConversionType, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0170q) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, formatConversionType.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Task<Result> convert(AppInfo appInfo, String str, FormatConversionType formatConversionType) {
        return convert(appInfo, str, formatConversionType, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> convert(AppInfo appInfo, String str, FormatConversionType formatConversionType, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_SUGGESTION, appInfo.isStreamingMode(), new k(this, appInfo, str, formatConversionType, map, 9), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
