package com.samsung.android.sdk.scs.ai.language.data;

import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/data/LlmCloudUsageItem;", "", "packageName", "", "domain", "usedUsage", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getPackageName", "()Ljava/lang/String;", "getDomain", "getUsedUsage", "()J", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LlmCloudUsageItem {
    private final String domain;
    private final String packageName;
    private final long usedUsage;

    public LlmCloudUsageItem(String str, String str2, long j2) {
        j.e(str, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        j.e(str2, "domain");
        this.packageName = str;
        this.domain = str2;
        this.usedUsage = j2;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getUsedUsage() {
        return this.usedUsage;
    }

    public String toString() {
        String str = this.packageName;
        String str2 = this.domain;
        return C0212a.o(C0086a.q("packageName : ", str, " \ndomain : ", str2, "\nusedUsage : "), this.usedUsage, "\n\n");
    }
}
