package com.samsung.android.vexfwk.imagetranslation;

import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguageInfo;", "", "onDeviceLangCode", "", "langPackCode", "displayLangCode", "supportOnDevice", "", "packageName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getOnDeviceLangCode", "()Ljava/lang/String;", "getLangPackCode", "getDisplayLangCode", "getSupportOnDevice", "()Z", "getPackageName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguageInfo {
    private final String displayLangCode;
    private final String langPackCode;
    private final String onDeviceLangCode;
    private final String packageName;
    private final boolean supportOnDevice;

    public VexFwkLanguageInfo(String str, String str2, String str3, boolean z, String str4) {
        j.e(str, "onDeviceLangCode");
        j.e(str2, "langPackCode");
        j.e(str3, "displayLangCode");
        j.e(str4, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        this.onDeviceLangCode = str;
        this.langPackCode = str2;
        this.displayLangCode = str3;
        this.supportOnDevice = z;
        this.packageName = str4;
    }

    public static /* synthetic */ VexFwkLanguageInfo copy$default(VexFwkLanguageInfo vexFwkLanguageInfo, String str, String str2, String str3, boolean z, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = vexFwkLanguageInfo.onDeviceLangCode;
        }
        if ((i2 & 2) != 0) {
            str2 = vexFwkLanguageInfo.langPackCode;
        }
        if ((i2 & 4) != 0) {
            str3 = vexFwkLanguageInfo.displayLangCode;
        }
        if ((i2 & 8) != 0) {
            z = vexFwkLanguageInfo.supportOnDevice;
        }
        if ((i2 & 16) != 0) {
            str4 = vexFwkLanguageInfo.packageName;
        }
        boolean z3 = z;
        String str5 = str4;
        return vexFwkLanguageInfo.copy(str, str2, str3, z3, str5);
    }

    public final String component1() {
        return this.onDeviceLangCode;
    }

    public final String component2() {
        return this.langPackCode;
    }

    public final String component3() {
        return this.displayLangCode;
    }

    public final boolean component4() {
        return this.supportOnDevice;
    }

    public final String component5() {
        return this.packageName;
    }

    public final VexFwkLanguageInfo copy(String str, String str2, String str3, boolean z, String str4) {
        j.e(str, "onDeviceLangCode");
        j.e(str2, "langPackCode");
        j.e(str3, "displayLangCode");
        j.e(str4, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        return new VexFwkLanguageInfo(str, str2, str3, z, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkLanguageInfo)) {
            return false;
        }
        VexFwkLanguageInfo vexFwkLanguageInfo = (VexFwkLanguageInfo) obj;
        if (j.a(this.onDeviceLangCode, vexFwkLanguageInfo.onDeviceLangCode) && j.a(this.langPackCode, vexFwkLanguageInfo.langPackCode) && j.a(this.displayLangCode, vexFwkLanguageInfo.displayLangCode) && this.supportOnDevice == vexFwkLanguageInfo.supportOnDevice && j.a(this.packageName, vexFwkLanguageInfo.packageName)) {
            return true;
        }
        return false;
    }

    public final String getDisplayLangCode() {
        return this.displayLangCode;
    }

    public final String getLangPackCode() {
        return this.langPackCode;
    }

    public final String getOnDeviceLangCode() {
        return this.onDeviceLangCode;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean getSupportOnDevice() {
        return this.supportOnDevice;
    }

    public int hashCode() {
        return this.packageName.hashCode() + C0212a.e(C0212a.d(C0212a.d(this.onDeviceLangCode.hashCode() * 31, 31, this.langPackCode), 31, this.displayLangCode), 31, this.supportOnDevice);
    }

    public String toString() {
        String str = this.onDeviceLangCode;
        String str2 = this.langPackCode;
        String str3 = this.displayLangCode;
        boolean z = this.supportOnDevice;
        String str4 = this.packageName;
        StringBuilder q = C0086a.q("VexFwkLanguageInfo(onDeviceLangCode=", str, ", langPackCode=", str2, ", displayLangCode=");
        q.append(str3);
        q.append(", supportOnDevice=");
        q.append(z);
        q.append(", packageName=");
        return C0212a.p(q, str4, ")");
    }
}
