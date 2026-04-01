package com.samsung.android.app.sdk.deepsky.textextraction.languagepack;

import c0.C0086a;
import com.google.gson.annotations.SerializedName;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo;", "", "languageInfo", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$LanguageInfo;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$LanguageInfo;)V", "getLanguageInfo", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$LanguageInfo;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "LanguageInfo", "SupportLanguage", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScsLanguageInfo {
    @SerializedName("translateSupportLanguageInfo")
    private final LanguageInfo languageInfo;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$LanguageInfo;", "", "supportLanguages", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$SupportLanguage;", "<init>", "(Ljava/util/List;)V", "getSupportLanguages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LanguageInfo {
        @SerializedName("default")
        private final List<SupportLanguage> supportLanguages;

        public LanguageInfo(List<SupportLanguage> list) {
            j.e(list, "supportLanguages");
            this.supportLanguages = list;
        }

        public static /* synthetic */ LanguageInfo copy$default(LanguageInfo languageInfo, List<SupportLanguage> list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                list = languageInfo.supportLanguages;
            }
            return languageInfo.copy(list);
        }

        public final List<SupportLanguage> component1() {
            return this.supportLanguages;
        }

        public final LanguageInfo copy(List<SupportLanguage> list) {
            j.e(list, "supportLanguages");
            return new LanguageInfo(list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof LanguageInfo) && j.a(this.supportLanguages, ((LanguageInfo) obj).supportLanguages)) {
                return true;
            }
            return false;
        }

        public final List<SupportLanguage> getSupportLanguages() {
            return this.supportLanguages;
        }

        public int hashCode() {
            return this.supportLanguages.hashCode();
        }

        public String toString() {
            List<SupportLanguage> list = this.supportLanguages;
            return "LanguageInfo(supportLanguages=" + list + ")";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/ScsLanguageInfo$SupportLanguage;", "", "displayLangCode", "", "langPackCode", "onDeviceLangCode", "serverLangCode", "supportOnDevice", "", "packageName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getDisplayLangCode", "()Ljava/lang/String;", "getLangPackCode", "getOnDeviceLangCode", "getServerLangCode", "getSupportOnDevice", "()Z", "getPackageName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SupportLanguage {
        @SerializedName("displayLangCode")
        private final String displayLangCode;
        @SerializedName("langpackCode")
        private final String langPackCode;
        @SerializedName("ondeviceLangCode")
        private final String onDeviceLangCode;
        @SerializedName("packageName")
        private final String packageName;
        @SerializedName("serverLangCode")
        private final String serverLangCode;
        @SerializedName("supportOndevice")
        private final boolean supportOnDevice;

        public SupportLanguage(String str, String str2, String str3, String str4, boolean z, String str5) {
            j.e(str, "displayLangCode");
            j.e(str2, "langPackCode");
            j.e(str3, "onDeviceLangCode");
            j.e(str4, "serverLangCode");
            j.e(str5, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
            this.displayLangCode = str;
            this.langPackCode = str2;
            this.onDeviceLangCode = str3;
            this.serverLangCode = str4;
            this.supportOnDevice = z;
            this.packageName = str5;
        }

        public static /* synthetic */ SupportLanguage copy$default(SupportLanguage supportLanguage, String str, String str2, String str3, String str4, boolean z, String str5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = supportLanguage.displayLangCode;
            }
            if ((i2 & 2) != 0) {
                str2 = supportLanguage.langPackCode;
            }
            if ((i2 & 4) != 0) {
                str3 = supportLanguage.onDeviceLangCode;
            }
            if ((i2 & 8) != 0) {
                str4 = supportLanguage.serverLangCode;
            }
            if ((i2 & 16) != 0) {
                z = supportLanguage.supportOnDevice;
            }
            if ((i2 & 32) != 0) {
                str5 = supportLanguage.packageName;
            }
            boolean z3 = z;
            String str6 = str5;
            String str7 = str4;
            String str8 = str2;
            return supportLanguage.copy(str, str8, str3, str7, z3, str6);
        }

        public final String component1() {
            return this.displayLangCode;
        }

        public final String component2() {
            return this.langPackCode;
        }

        public final String component3() {
            return this.onDeviceLangCode;
        }

        public final String component4() {
            return this.serverLangCode;
        }

        public final boolean component5() {
            return this.supportOnDevice;
        }

        public final String component6() {
            return this.packageName;
        }

        public final SupportLanguage copy(String str, String str2, String str3, String str4, boolean z, String str5) {
            j.e(str, "displayLangCode");
            j.e(str2, "langPackCode");
            j.e(str3, "onDeviceLangCode");
            j.e(str4, "serverLangCode");
            j.e(str5, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
            return new SupportLanguage(str, str2, str3, str4, z, str5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SupportLanguage)) {
                return false;
            }
            SupportLanguage supportLanguage = (SupportLanguage) obj;
            if (j.a(this.displayLangCode, supportLanguage.displayLangCode) && j.a(this.langPackCode, supportLanguage.langPackCode) && j.a(this.onDeviceLangCode, supportLanguage.onDeviceLangCode) && j.a(this.serverLangCode, supportLanguage.serverLangCode) && this.supportOnDevice == supportLanguage.supportOnDevice && j.a(this.packageName, supportLanguage.packageName)) {
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

        public final String getServerLangCode() {
            return this.serverLangCode;
        }

        public final boolean getSupportOnDevice() {
            return this.supportOnDevice;
        }

        public int hashCode() {
            return this.packageName.hashCode() + C0212a.e(C0212a.d(C0212a.d(C0212a.d(this.displayLangCode.hashCode() * 31, 31, this.langPackCode), 31, this.onDeviceLangCode), 31, this.serverLangCode), 31, this.supportOnDevice);
        }

        public String toString() {
            String str = this.displayLangCode;
            String str2 = this.langPackCode;
            String str3 = this.onDeviceLangCode;
            String str4 = this.serverLangCode;
            boolean z = this.supportOnDevice;
            String str5 = this.packageName;
            StringBuilder q = C0086a.q("SupportLanguage(displayLangCode=", str, ", langPackCode=", str2, ", onDeviceLangCode=");
            C0086a.z(q, str3, ", serverLangCode=", str4, ", supportOnDevice=");
            q.append(z);
            q.append(", packageName=");
            q.append(str5);
            q.append(")");
            return q.toString();
        }
    }

    public ScsLanguageInfo(LanguageInfo languageInfo2) {
        j.e(languageInfo2, "languageInfo");
        this.languageInfo = languageInfo2;
    }

    public static /* synthetic */ ScsLanguageInfo copy$default(ScsLanguageInfo scsLanguageInfo, LanguageInfo languageInfo2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            languageInfo2 = scsLanguageInfo.languageInfo;
        }
        return scsLanguageInfo.copy(languageInfo2);
    }

    public final LanguageInfo component1() {
        return this.languageInfo;
    }

    public final ScsLanguageInfo copy(LanguageInfo languageInfo2) {
        j.e(languageInfo2, "languageInfo");
        return new ScsLanguageInfo(languageInfo2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ScsLanguageInfo) && j.a(this.languageInfo, ((ScsLanguageInfo) obj).languageInfo)) {
            return true;
        }
        return false;
    }

    public final LanguageInfo getLanguageInfo() {
        return this.languageInfo;
    }

    public int hashCode() {
        return this.languageInfo.hashCode();
    }

    public String toString() {
        LanguageInfo languageInfo2 = this.languageInfo;
        return "ScsLanguageInfo(languageInfo=" + languageInfo2 + ")";
    }
}
