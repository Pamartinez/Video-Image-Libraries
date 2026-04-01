package com.samsung.android.app.sdk.deepsky.textextraction.languagepack;

import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000f\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0012\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0016\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0017\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0018\u0010\rR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\b\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u001b\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "", "", "code", "langPackCode", "displayLangCode", "name", "", "isDownloadable", "packageName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getCode", "getLangPackCode", "getDisplayLangCode", "getName", "Z", "()Z", "getPackageName", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OnDeviceLanguage {
    /* access modifiers changed from: private */
    public static final OnDeviceLanguage AUTO = new OnDeviceLanguage("Auto", "", "auto", (String) null, false, (String) null, 40, (e) null);
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final OnDeviceLanguage EN = new OnDeviceLanguage("en", "", "en", (String) null, false, (String) null, 40, (e) null);
    private final String code;
    private final String displayLangCode;
    private final boolean isDownloadable;
    private final String langPackCode;
    private final String name;
    private final String packageName;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage$Companion;", "", "<init>", "()V", "EN", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "getEN", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "AUTO", "getAUTO", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final OnDeviceLanguage getAUTO() {
            return OnDeviceLanguage.AUTO;
        }

        public final OnDeviceLanguage getEN() {
            return OnDeviceLanguage.EN;
        }

        private Companion() {
        }
    }

    public OnDeviceLanguage(String str, String str2, String str3, String str4, boolean z, String str5) {
        j.e(str, "code");
        j.e(str2, "langPackCode");
        j.e(str3, "displayLangCode");
        j.e(str4, "name");
        j.e(str5, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        this.code = str;
        this.langPackCode = str2;
        this.displayLangCode = str3;
        this.name = str4;
        this.isDownloadable = z;
        this.packageName = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OnDeviceLanguage)) {
            return false;
        }
        OnDeviceLanguage onDeviceLanguage = (OnDeviceLanguage) obj;
        if (j.a(this.code, onDeviceLanguage.code) && j.a(this.langPackCode, onDeviceLanguage.langPackCode) && j.a(this.displayLangCode, onDeviceLanguage.displayLangCode) && j.a(this.name, onDeviceLanguage.name) && this.isDownloadable == onDeviceLanguage.isDownloadable && j.a(this.packageName, onDeviceLanguage.packageName)) {
            return true;
        }
        return false;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getDisplayLangCode() {
        return this.displayLangCode;
    }

    public final String getLangPackCode() {
        return this.langPackCode;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return this.packageName.hashCode() + C0212a.e(C0212a.d(C0212a.d(C0212a.d(this.code.hashCode() * 31, 31, this.langPackCode), 31, this.displayLangCode), 31, this.name), 31, this.isDownloadable);
    }

    public String toString() {
        String str = this.code;
        String str2 = this.langPackCode;
        String str3 = this.displayLangCode;
        String str4 = this.name;
        boolean z = this.isDownloadable;
        String str5 = this.packageName;
        StringBuilder q = C0086a.q("OnDeviceLanguage(code=", str, ", langPackCode=", str2, ", displayLangCode=");
        C0086a.z(q, str3, ", name=", str4, ", isDownloadable=");
        q.append(z);
        q.append(", packageName=");
        q.append(str5);
        q.append(")");
        return q.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ OnDeviceLanguage(java.lang.String r1, java.lang.String r2, java.lang.String r3, java.lang.String r4, boolean r5, java.lang.String r6, int r7, kotlin.jvm.internal.e r8) {
        /*
            r0 = this;
            r8 = r7 & 4
            if (r8 == 0) goto L_0x0005
            r3 = r2
        L_0x0005:
            r8 = r7 & 8
            if (r8 == 0) goto L_0x0015
            java.util.Locale r4 = java.util.Locale.ROOT
            java.lang.String r4 = r1.toUpperCase(r4)
            java.lang.String r8 = "toUpperCase(...)"
            kotlin.jvm.internal.j.d(r4, r8)
        L_0x0015:
            r8 = r7 & 16
            if (r8 == 0) goto L_0x0022
            int r5 = r2.length()
            if (r5 <= 0) goto L_0x0021
            r5 = 1
            goto L_0x0022
        L_0x0021:
            r5 = 0
        L_0x0022:
            r7 = r7 & 32
            if (r7 == 0) goto L_0x0028
            java.lang.String r6 = ""
        L_0x0028:
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, int, kotlin.jvm.internal.e):void");
    }
}
