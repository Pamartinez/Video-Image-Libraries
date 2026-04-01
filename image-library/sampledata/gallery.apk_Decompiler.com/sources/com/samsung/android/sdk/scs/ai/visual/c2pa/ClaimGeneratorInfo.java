package com.samsung.android.sdk.scs.ai.visual.c2pa;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ClaimGeneratorInfo;", "", "name", "", "version", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getVersion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ClaimGeneratorInfo {
    private final String name;
    private final String version;

    public ClaimGeneratorInfo(String str, String str2) {
        this.name = str;
        this.version = str2;
    }

    public static /* synthetic */ ClaimGeneratorInfo copy$default(ClaimGeneratorInfo claimGeneratorInfo, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = claimGeneratorInfo.name;
        }
        if ((i2 & 2) != 0) {
            str2 = claimGeneratorInfo.version;
        }
        return claimGeneratorInfo.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.version;
    }

    public final ClaimGeneratorInfo copy(String str, String str2) {
        return new ClaimGeneratorInfo(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClaimGeneratorInfo)) {
            return false;
        }
        ClaimGeneratorInfo claimGeneratorInfo = (ClaimGeneratorInfo) obj;
        if (j.a(this.name, claimGeneratorInfo.name) && j.a(this.version, claimGeneratorInfo.version)) {
            return true;
        }
        return false;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i2;
        String str = this.name;
        int i7 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i8 = i2 * 31;
        String str2 = this.version;
        if (str2 != null) {
            i7 = str2.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        return N2.j.d("ClaimGeneratorInfo(name=", this.name, ", version=", this.version, ")");
    }
}
