package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.sdk.scs.ai.language.ResultErrorException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestRunnable$execute$observer$1", "Lcom/samsung/android/sdk/scs/ai/language/service/LlmServiceObserver;", "", "result", "Lme/x;", "onNext", "(Ljava/lang/String;)V", "Landroid/os/Bundle;", "error", "onError", "(Landroid/os/Bundle;)V", "onComplete", "()V", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageRequestRunnable$execute$observer$1 extends LlmServiceObserver {
    final /* synthetic */ LlmCloudUsageRequestRunnable this$0;

    public LlmCloudUsageRequestRunnable$execute$observer$1(LlmCloudUsageRequestRunnable llmCloudUsageRequestRunnable) {
        this.this$0 = llmCloudUsageRequestRunnable;
    }

    public void onError(Bundle bundle) {
        if (bundle != null) {
            int i2 = bundle.getInt("error_code");
            String string = bundle.getString("error_message");
            Log.e(LlmCloudUsageRequestRunnable.TAG, "onError= " + i2 + string);
            this.this$0.mSource.setException(new ResultErrorException(500, bundle.getInt("error_code"), bundle.getString("error_message")));
            return;
        }
        Log.e(LlmCloudUsageRequestRunnable.TAG, "onError= error is null");
        C0086a.t(5, "error is null", this.this$0.mSource);
    }

    public void onNext(String str) {
        if (str != null) {
            LlmCloudUsageRequestRunnable llmCloudUsageRequestRunnable = this.this$0;
            llmCloudUsageRequestRunnable.mSource.setResult(llmCloudUsageRequestRunnable.parseUsageData(str));
        }
    }

    public void onComplete() {
    }
}
