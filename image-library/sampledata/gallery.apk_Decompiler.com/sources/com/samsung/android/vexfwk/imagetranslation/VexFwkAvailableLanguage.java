package com.samsung.android.vexfwk.imagetranslation;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/imagetranslation/VexFwkAvailableLanguage;", "", "lang", "", "downloadRequired", "", "<init>", "(Ljava/lang/String;Z)V", "getLang", "()Ljava/lang/String;", "getDownloadRequired", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkAvailableLanguage {
    private final boolean downloadRequired;
    private final String lang;

    public VexFwkAvailableLanguage(String str, boolean z) {
        j.e(str, "lang");
        this.lang = str;
        this.downloadRequired = z;
    }

    public static /* synthetic */ VexFwkAvailableLanguage copy$default(VexFwkAvailableLanguage vexFwkAvailableLanguage, String str, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = vexFwkAvailableLanguage.lang;
        }
        if ((i2 & 2) != 0) {
            z = vexFwkAvailableLanguage.downloadRequired;
        }
        return vexFwkAvailableLanguage.copy(str, z);
    }

    public final String component1() {
        return this.lang;
    }

    public final boolean component2() {
        return this.downloadRequired;
    }

    public final VexFwkAvailableLanguage copy(String str, boolean z) {
        j.e(str, "lang");
        return new VexFwkAvailableLanguage(str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkAvailableLanguage)) {
            return false;
        }
        VexFwkAvailableLanguage vexFwkAvailableLanguage = (VexFwkAvailableLanguage) obj;
        if (j.a(this.lang, vexFwkAvailableLanguage.lang) && this.downloadRequired == vexFwkAvailableLanguage.downloadRequired) {
            return true;
        }
        return false;
    }

    public final boolean getDownloadRequired() {
        return this.downloadRequired;
    }

    public final String getLang() {
        return this.lang;
    }

    public int hashCode() {
        return Boolean.hashCode(this.downloadRequired) + (this.lang.hashCode() * 31);
    }

    public String toString() {
        String str = this.lang;
        boolean z = this.downloadRequired;
        return "VexFwkAvailableLanguage(lang=" + str + ", downloadRequired=" + z + ")";
    }
}
