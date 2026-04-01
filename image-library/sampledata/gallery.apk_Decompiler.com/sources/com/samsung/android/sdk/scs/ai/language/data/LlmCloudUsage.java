package com.samsung.android.sdk.scs.ai.language.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsage;", "", "remainingUsage", "", "usageList", "", "Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsageItem;", "<init>", "(JLjava/util/List;)V", "getRemainingUsage", "()J", "getUsageList", "()Ljava/util/List;", "toString", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsage {
    private final long remainingUsage;
    private final List<LlmCloudUsageItem> usageList;

    public LlmCloudUsage(long j2, List<LlmCloudUsageItem> list) {
        j.e(list, "usageList");
        this.remainingUsage = j2;
        this.usageList = list;
    }

    public final long getRemainingUsage() {
        return this.remainingUsage;
    }

    public final List<LlmCloudUsageItem> getUsageList() {
        return this.usageList;
    }

    public String toString() {
        long j2 = this.remainingUsage;
        List<LlmCloudUsageItem> list = this.usageList;
        return "remaining Usage :  " + j2 + " / UsageList : " + list;
    }
}
