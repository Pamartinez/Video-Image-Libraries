package com.samsung.android.app.sdk.deepsky.contract.suggestion;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\b\b\u0018\u0000 L2\u00020\u0001:\u0001LBk\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011B\u0011\b\u0016\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0010\u0010\u0014J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJt\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b \u0010\u001bJ\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b&\u0010\u001fJ\u0010\u0010'\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b'\u0010\u001fJ\u0010\u0010(\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b(\u0010\u001fJ\u0012\u0010)\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b)\u0010*J\u0012\u0010+\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b+\u0010*J\u0012\u0010,\u001a\u0004\u0018\u00010\tHÂ\u0003¢\u0006\u0004\b,\u0010-J\u0012\u0010.\u001a\u0004\u0018\u00010\u000bHÂ\u0003¢\u0006\u0004\b.\u0010/J\u0012\u00100\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b0\u0010\u001fJ\u0010\u00101\u001a\u00020\u000eHÂ\u0003¢\u0006\u0004\b1\u00102R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u00103R\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00103R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u00104R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u00104R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u00105R\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\f\u00106R\u0016\u0010\r\u001a\u0004\u0018\u00010\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u00103R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000f\u00107R\u0011\u00109\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b8\u0010\u001fR\u0011\u0010;\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b:\u0010\u001fR\u0011\u0010=\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b<\u0010\u001fR\u0013\u0010?\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b>\u0010*R\u0013\u0010A\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b@\u0010*R\u0013\u0010C\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\bB\u0010-R\u0013\u0010E\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\bD\u0010/R\u0013\u0010G\u001a\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\bF\u0010\u001fR\u0011\u0010I\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bH\u00102R\u0011\u0010K\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bJ\u0010\u001f¨\u0006M"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData;", "Landroid/os/Parcelable;", "", "idParam", "titleParam", "descriptionParam", "Landroid/net/Uri;", "iconParam", "backgroundParam", "Landroid/os/Bundle;", "extrasParam", "Lorg/json/JSONObject;", "structuredDataParam", "urlParam", "", "creationTimeParam", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Landroid/net/Uri;Landroid/os/Bundle;Lorg/json/JSONObject;Ljava/lang/String;J)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Landroid/net/Uri;Landroid/os/Bundle;Lorg/json/JSONObject;Ljava/lang/String;J)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "component1", "component2", "component3", "component4", "()Landroid/net/Uri;", "component5", "component6", "()Landroid/os/Bundle;", "component7", "()Lorg/json/JSONObject;", "component8", "component9", "()J", "Ljava/lang/String;", "Landroid/net/Uri;", "Landroid/os/Bundle;", "Lorg/json/JSONObject;", "J", "getId", "id", "getTitle", "title", "getDescription", "description", "getIcon", "icon", "getBackground", "background", "getExtras", "extras", "getStructuredData", "structuredData", "getUrl", "url", "getCreationTime", "creationTime", "getSuggestionFrom", "suggestionFrom", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionData implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final Uri backgroundParam;
    private final long creationTimeParam;
    private final String descriptionParam;
    private final Bundle extrasParam;
    private final Uri iconParam;
    private final String idParam;
    private final JSONObject structuredDataParam;
    private final String titleParam;
    private final String urlParam;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SuggestionData> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private CREATOR() {
        }

        public SuggestionData createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SuggestionData(parcel);
        }

        public SuggestionData[] newArray(int i2) {
            return new SuggestionData[i2];
        }
    }

    public SuggestionData() {
        this((String) null, (String) null, (String) null, (Uri) null, (Uri) null, (Bundle) null, (JSONObject) null, (String) null, 0, 511, (e) null);
    }

    private final String component1() {
        return this.idParam;
    }

    private final String component2() {
        return this.titleParam;
    }

    private final String component3() {
        return this.descriptionParam;
    }

    private final Uri component4() {
        return this.iconParam;
    }

    private final Uri component5() {
        return this.backgroundParam;
    }

    private final Bundle component6() {
        return this.extrasParam;
    }

    private final JSONObject component7() {
        return this.structuredDataParam;
    }

    private final String component8() {
        return this.urlParam;
    }

    private final long component9() {
        return this.creationTimeParam;
    }

    public static /* synthetic */ SuggestionData copy$default(SuggestionData suggestionData, String str, String str2, String str3, Uri uri, Uri uri2, Bundle bundle, JSONObject jSONObject, String str4, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = suggestionData.idParam;
        }
        if ((i2 & 2) != 0) {
            str2 = suggestionData.titleParam;
        }
        if ((i2 & 4) != 0) {
            str3 = suggestionData.descriptionParam;
        }
        if ((i2 & 8) != 0) {
            uri = suggestionData.iconParam;
        }
        if ((i2 & 16) != 0) {
            uri2 = suggestionData.backgroundParam;
        }
        if ((i2 & 32) != 0) {
            bundle = suggestionData.extrasParam;
        }
        if ((i2 & 64) != 0) {
            jSONObject = suggestionData.structuredDataParam;
        }
        if ((i2 & 128) != 0) {
            str4 = suggestionData.urlParam;
        }
        if ((i2 & 256) != 0) {
            j2 = suggestionData.creationTimeParam;
        }
        long j3 = j2;
        JSONObject jSONObject2 = jSONObject;
        String str5 = str4;
        Uri uri3 = uri2;
        Bundle bundle2 = bundle;
        Uri uri4 = uri;
        String str6 = str2;
        return suggestionData.copy(str, str6, str3, uri4, uri3, bundle2, jSONObject2, str5, j3);
    }

    public final SuggestionData copy(String str, String str2, String str3, Uri uri, Uri uri2, Bundle bundle, JSONObject jSONObject, String str4, long j2) {
        j.e(str, "idParam");
        j.e(str2, "titleParam");
        j.e(str3, "descriptionParam");
        return new SuggestionData(str, str2, str3, uri, uri2, bundle, jSONObject, str4, j2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuggestionData)) {
            return false;
        }
        SuggestionData suggestionData = (SuggestionData) obj;
        if (j.a(this.idParam, suggestionData.idParam) && j.a(this.titleParam, suggestionData.titleParam) && j.a(this.descriptionParam, suggestionData.descriptionParam) && j.a(this.iconParam, suggestionData.iconParam) && j.a(this.backgroundParam, suggestionData.backgroundParam) && j.a(this.extrasParam, suggestionData.extrasParam) && j.a(this.structuredDataParam, suggestionData.structuredDataParam) && j.a(this.urlParam, suggestionData.urlParam) && this.creationTimeParam == suggestionData.creationTimeParam) {
            return true;
        }
        return false;
    }

    public final Uri getBackground() {
        return this.backgroundParam;
    }

    public final long getCreationTime() {
        return this.creationTimeParam;
    }

    public final String getDescription() {
        return this.descriptionParam;
    }

    public final Bundle getExtras() {
        return this.extrasParam;
    }

    public final Uri getIcon() {
        return this.iconParam;
    }

    public final String getId() {
        return this.idParam;
    }

    public final JSONObject getStructuredData() {
        return this.structuredDataParam;
    }

    public final String getSuggestionFrom() {
        String str;
        String string;
        Bundle bundle = this.extrasParam;
        if (bundle != null && (string = bundle.getString(SuggestionContract.EXTRA_SUGGESTION_FROM)) != null) {
            return string;
        }
        Bundle bundle2 = this.extrasParam;
        if (bundle2 != null) {
            str = bundle2.getString("suggestionFrom");
        } else {
            str = null;
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public final String getTitle() {
        return this.titleParam;
    }

    public final String getUrl() {
        return this.urlParam;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int d = C0212a.d(C0212a.d(this.idParam.hashCode() * 31, 31, this.titleParam), 31, this.descriptionParam);
        Uri uri = this.iconParam;
        int i11 = 0;
        if (uri == null) {
            i2 = 0;
        } else {
            i2 = uri.hashCode();
        }
        int i12 = (d + i2) * 31;
        Uri uri2 = this.backgroundParam;
        if (uri2 == null) {
            i7 = 0;
        } else {
            i7 = uri2.hashCode();
        }
        int i13 = (i12 + i7) * 31;
        Bundle bundle = this.extrasParam;
        if (bundle == null) {
            i8 = 0;
        } else {
            i8 = bundle.hashCode();
        }
        int i14 = (i13 + i8) * 31;
        JSONObject jSONObject = this.structuredDataParam;
        if (jSONObject == null) {
            i10 = 0;
        } else {
            i10 = jSONObject.hashCode();
        }
        int i15 = (i14 + i10) * 31;
        String str = this.urlParam;
        if (str != null) {
            i11 = str.hashCode();
        }
        return Long.hashCode(this.creationTimeParam) + ((i15 + i11) * 31);
    }

    public String toString() {
        String str = this.idParam;
        String str2 = this.titleParam;
        String str3 = this.descriptionParam;
        Uri uri = this.iconParam;
        Uri uri2 = this.backgroundParam;
        Bundle bundle = this.extrasParam;
        JSONObject jSONObject = this.structuredDataParam;
        String str4 = this.urlParam;
        long j2 = this.creationTimeParam;
        StringBuilder q = C0086a.q("SuggestionData(idParam=", str, ", titleParam=", str2, ", descriptionParam=");
        q.append(str3);
        q.append(", iconParam=");
        q.append(uri);
        q.append(", backgroundParam=");
        q.append(uri2);
        q.append(", extrasParam=");
        q.append(bundle);
        q.append(", structuredDataParam=");
        q.append(jSONObject);
        q.append(", urlParam=");
        q.append(str4);
        q.append(", creationTimeParam=");
        return C0212a.o(q, j2, ")");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        String str;
        j.e(parcel, "parcel");
        parcel.writeString(this.idParam);
        parcel.writeString(this.titleParam);
        parcel.writeString(this.descriptionParam);
        parcel.writeParcelable(this.iconParam, i2);
        parcel.writeParcelable(this.backgroundParam, i2);
        parcel.writeBundle(this.extrasParam);
        JSONObject jSONObject = this.structuredDataParam;
        if (jSONObject != null) {
            str = jSONObject.toString();
        } else {
            str = null;
        }
        parcel.writeString(str);
        parcel.writeString(this.urlParam);
        parcel.writeLong(this.creationTimeParam);
    }

    public SuggestionData(String str, String str2, String str3, Uri uri, Uri uri2, Bundle bundle, JSONObject jSONObject, String str4, long j2) {
        j.e(str, "idParam");
        j.e(str2, "titleParam");
        j.e(str3, "descriptionParam");
        this.idParam = str;
        this.titleParam = str2;
        this.descriptionParam = str3;
        this.iconParam = uri;
        this.backgroundParam = uri2;
        this.extrasParam = bundle;
        this.structuredDataParam = jSONObject;
        this.urlParam = str4;
        this.creationTimeParam = j2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SuggestionData(String str, String str2, String str3, Uri uri, Uri uri2, Bundle bundle, JSONObject jSONObject, String str4, long j2, int i2, e eVar) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? null : uri, (i2 & 16) != 0 ? null : uri2, (i2 & 32) != 0 ? null : bundle, (i2 & 64) != 0 ? null : jSONObject, (i2 & 128) != 0 ? null : str4, (i2 & 256) != 0 ? 0 : j2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionData(android.os.Parcel r14) {
        /*
            r13 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.j.e(r14, r0)
            java.lang.String r0 = r14.readString()
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x000f
            r3 = r1
            goto L_0x0010
        L_0x000f:
            r3 = r0
        L_0x0010:
            java.lang.String r0 = r14.readString()
            if (r0 != 0) goto L_0x0018
            r4 = r1
            goto L_0x0019
        L_0x0018:
            r4 = r0
        L_0x0019:
            java.lang.String r0 = r14.readString()
            if (r0 != 0) goto L_0x0021
            r5 = r1
            goto L_0x0022
        L_0x0021:
            r5 = r0
        L_0x0022:
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.ClassLoader r1 = r0.getClassLoader()
            android.os.Parcelable r1 = r14.readParcelable(r1)
            r6 = r1
            android.net.Uri r6 = (android.net.Uri) r6
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r14.readParcelable(r0)
            r7 = r0
            android.net.Uri r7 = (android.net.Uri) r7
            java.lang.Class<android.os.Bundle> r0 = android.os.Bundle.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Bundle r8 = r14.readBundle(r0)
            org.json.JSONObject r9 = new org.json.JSONObject
            java.lang.String r0 = r14.readString()
            if (r0 != 0) goto L_0x004f
            java.lang.String r0 = "{ }"
        L_0x004f:
            r9.<init>(r0)
            java.lang.String r10 = r14.readString()
            long r11 = r14.readLong()
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionData.<init>(android.os.Parcel):void");
    }
}
