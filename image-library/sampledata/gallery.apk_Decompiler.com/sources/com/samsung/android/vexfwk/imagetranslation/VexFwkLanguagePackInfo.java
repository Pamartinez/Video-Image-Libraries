package com.samsung.android.vexfwk.imagetranslation;

import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "", "languagePackCode", "", "<init>", "(Ljava/lang/String;)V", "getLanguagePackCode", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguagePackInfo {
    private final String languagePackCode;

    public VexFwkLanguagePackInfo(String str) {
        j.e(str, "languagePackCode");
        this.languagePackCode = str;
    }

    public static /* synthetic */ VexFwkLanguagePackInfo copy$default(VexFwkLanguagePackInfo vexFwkLanguagePackInfo, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = vexFwkLanguagePackInfo.languagePackCode;
        }
        return vexFwkLanguagePackInfo.copy(str);
    }

    public final String component1() {
        return this.languagePackCode;
    }

    public final VexFwkLanguagePackInfo copy(String str) {
        j.e(str, "languagePackCode");
        return new VexFwkLanguagePackInfo(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VexFwkLanguagePackInfo) && j.a(this.languagePackCode, ((VexFwkLanguagePackInfo) obj).languagePackCode)) {
            return true;
        }
        return false;
    }

    public final String getLanguagePackCode() {
        return this.languagePackCode;
    }

    public int hashCode() {
        return this.languagePackCode.hashCode();
    }

    public String toString() {
        return C0212a.m("VexFwkLanguagePackInfo(languagePackCode=", this.languagePackCode, ")");
    }
}
