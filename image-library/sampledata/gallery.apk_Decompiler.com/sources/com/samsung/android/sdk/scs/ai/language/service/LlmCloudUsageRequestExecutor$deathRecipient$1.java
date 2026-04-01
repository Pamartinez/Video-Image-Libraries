package com.samsung.android.sdk.scs.ai.language.service;

import android.os.IBinder;
import com.samsung.android.sivs.ai.sdkcommon.language.j0;
import com.samsung.android.sivs.ai.sdkcommon.language.l0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/sdk/scs/ai/language/service/LlmCloudUsageRequestExecutor$deathRecipient$1", "Landroid/os/IBinder$DeathRecipient;", "Lme/x;", "binderDied", "()V", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageRequestExecutor$deathRecipient$1 implements IBinder.DeathRecipient {
    final /* synthetic */ LlmCloudUsageRequestExecutor this$0;

    public LlmCloudUsageRequestExecutor$deathRecipient$1(LlmCloudUsageRequestExecutor llmCloudUsageRequestExecutor) {
        this.this$0 = llmCloudUsageRequestExecutor;
    }

    public void binderDied() {
        l0 access$getService$p = this.this$0.service;
        j.b(access$getService$p);
        ((j0) access$getService$p).f1708a.unlinkToDeath(this, 0);
    }
}
