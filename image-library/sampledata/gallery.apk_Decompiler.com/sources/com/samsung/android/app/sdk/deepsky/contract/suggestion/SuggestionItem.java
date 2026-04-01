package com.samsung.android.app.sdk.deepsky.contract.suggestion;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b5\b\b\u0018\u0000 Z2\u00020\u0001:\u0001ZB\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0015\u0010\u0016B\u0011\b\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0015\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0001\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b%\u0010\u001cJ\u001a\u0010(\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010&HÖ\u0003¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b*\u0010$J\u0010\u0010+\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b+\u0010$J\u0010\u0010,\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b,\u0010$J\u0012\u0010-\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b-\u0010.J\u0012\u0010/\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b/\u0010.J\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\n0\tHÂ\u0003¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\fHÂ\u0003¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\u000eHÂ\u0003¢\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0010HÂ\u0003¢\u0006\u0004\b6\u00107J\u0012\u00108\u001a\u0004\u0018\u00010\u0012HÂ\u0003¢\u0006\u0004\b8\u00109J\u0012\u0010:\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b:\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010;R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010;R\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010;R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010<R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010<R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010=R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u0010>R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010?R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010@R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010AR\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010;R\u0011\u0010C\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bB\u0010$R\u0011\u0010E\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bD\u0010$R\u0011\u0010G\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bF\u0010$R\u0013\u0010I\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\bH\u0010.R\u0013\u0010K\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\bJ\u0010.R\u0017\u0010M\u001a\b\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\bL\u00101R\u0011\u0010O\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bN\u00103R\u0011\u0010Q\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bP\u00105R\u0011\u0010S\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\bR\u00107R\u0013\u0010U\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\bT\u00109R\u001c\u0010Y\u001a\u0004\u0018\u00010\u00028FX\u0004¢\u0006\f\u0012\u0004\bW\u0010X\u001a\u0004\bV\u0010$¨\u0006["}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "Landroid/os/Parcelable;", "", "idParam", "titleParam", "descriptionParam", "Landroid/net/Uri;", "iconParam", "backgroundParam", "", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionData;", "itemsParam", "", "scoreParam", "", "validParam", "", "validTimeParam", "Landroid/os/Bundle;", "extrasParam", "urlParam", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Landroid/net/Uri;Ljava/util/List;DZJLandroid/os/Bundle;Ljava/lang/String;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "", "describeContents", "()I", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Landroid/net/Uri;Ljava/util/List;DZJLandroid/os/Bundle;Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "component1", "component2", "component3", "component4", "()Landroid/net/Uri;", "component5", "component6", "()Ljava/util/List;", "component7", "()D", "component8", "()Z", "component9", "()J", "component10", "()Landroid/os/Bundle;", "component11", "Ljava/lang/String;", "Landroid/net/Uri;", "Ljava/util/List;", "D", "Z", "J", "Landroid/os/Bundle;", "getId", "id", "getTitle", "title", "getDescription", "description", "getIcon", "icon", "getBackground", "background", "getItems", "items", "getScore", "score", "getValid", "valid", "getValidTime", "validTime", "getExtras", "extras", "getUrl", "getUrl$annotations", "()V", "url", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionItem implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final Uri backgroundParam;
    private final String descriptionParam;
    private final Bundle extrasParam;
    private final Uri iconParam;
    private final String idParam;
    private final List<SuggestionData> itemsParam;
    private final double scoreParam;
    private final String titleParam;
    private final String urlParam;
    private final boolean validParam;
    private final long validTimeParam;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/SuggestionItem;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SuggestionItem> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private CREATOR() {
        }

        public SuggestionItem createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SuggestionItem(parcel);
        }

        public SuggestionItem[] newArray(int i2) {
            return new SuggestionItem[i2];
        }
    }

    public SuggestionItem() {
        this((String) null, (String) null, (String) null, (Uri) null, (Uri) null, (List) null, MapUtil.INVALID_LOCATION, false, 0, (Bundle) null, (String) null, 2047, (e) null);
    }

    private final String component1() {
        return this.idParam;
    }

    private final Bundle component10() {
        return this.extrasParam;
    }

    private final String component11() {
        return this.urlParam;
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

    private final List<SuggestionData> component6() {
        return this.itemsParam;
    }

    private final double component7() {
        return this.scoreParam;
    }

    private final boolean component8() {
        return this.validParam;
    }

    private final long component9() {
        return this.validTimeParam;
    }

    public static /* synthetic */ SuggestionItem copy$default(SuggestionItem suggestionItem, String str, String str2, String str3, Uri uri, Uri uri2, List list, double d, boolean z, long j2, Bundle bundle, String str4, int i2, Object obj) {
        String str5;
        String str6;
        Uri uri3;
        Uri uri4;
        List list2;
        double d2;
        boolean z3;
        long j3;
        Bundle bundle2;
        String str7;
        int i7 = i2;
        if ((i7 & 1) != 0) {
            str = suggestionItem.idParam;
        }
        if ((i7 & 2) != 0) {
            str5 = suggestionItem.titleParam;
        } else {
            str5 = str2;
        }
        if ((i7 & 4) != 0) {
            str6 = suggestionItem.descriptionParam;
        } else {
            str6 = str3;
        }
        if ((i7 & 8) != 0) {
            uri3 = suggestionItem.iconParam;
        } else {
            uri3 = uri;
        }
        if ((i7 & 16) != 0) {
            uri4 = suggestionItem.backgroundParam;
        } else {
            uri4 = uri2;
        }
        if ((i7 & 32) != 0) {
            list2 = suggestionItem.itemsParam;
        } else {
            list2 = list;
        }
        if ((i7 & 64) != 0) {
            d2 = suggestionItem.scoreParam;
        } else {
            d2 = d;
        }
        if ((i7 & 128) != 0) {
            z3 = suggestionItem.validParam;
        } else {
            z3 = z;
        }
        if ((i7 & 256) != 0) {
            j3 = suggestionItem.validTimeParam;
        } else {
            j3 = j2;
        }
        if ((i7 & 512) != 0) {
            bundle2 = suggestionItem.extrasParam;
        } else {
            bundle2 = bundle;
        }
        if ((i7 & 1024) != 0) {
            str7 = suggestionItem.urlParam;
        } else {
            str7 = str4;
        }
        return suggestionItem.copy(str, str5, str6, uri3, uri4, list2, d2, z3, j3, bundle2, str7);
    }

    public final SuggestionItem copy(String str, String str2, String str3, Uri uri, Uri uri2, List<SuggestionData> list, double d, boolean z, long j2, Bundle bundle, String str4) {
        j.e(str, "idParam");
        String str5 = str2;
        j.e(str5, "titleParam");
        String str6 = str3;
        j.e(str6, "descriptionParam");
        List<SuggestionData> list2 = list;
        j.e(list2, "itemsParam");
        return new SuggestionItem(str, str5, str6, uri, uri2, list2, d, z, j2, bundle, str4);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuggestionItem)) {
            return false;
        }
        SuggestionItem suggestionItem = (SuggestionItem) obj;
        if (j.a(this.idParam, suggestionItem.idParam) && j.a(this.titleParam, suggestionItem.titleParam) && j.a(this.descriptionParam, suggestionItem.descriptionParam) && j.a(this.iconParam, suggestionItem.iconParam) && j.a(this.backgroundParam, suggestionItem.backgroundParam) && j.a(this.itemsParam, suggestionItem.itemsParam) && Double.compare(this.scoreParam, suggestionItem.scoreParam) == 0 && this.validParam == suggestionItem.validParam && this.validTimeParam == suggestionItem.validTimeParam && j.a(this.extrasParam, suggestionItem.extrasParam) && j.a(this.urlParam, suggestionItem.urlParam)) {
            return true;
        }
        return false;
    }

    public final Uri getBackground() {
        return this.backgroundParam;
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

    public final List<SuggestionData> getItems() {
        return this.itemsParam;
    }

    public final double getScore() {
        return this.scoreParam;
    }

    public final String getTitle() {
        return this.titleParam;
    }

    public final String getUrl() {
        return this.urlParam;
    }

    public final boolean getValid() {
        return this.validParam;
    }

    public final long getValidTime() {
        return this.validTimeParam;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int d = C0212a.d(C0212a.d(this.idParam.hashCode() * 31, 31, this.titleParam), 31, this.descriptionParam);
        Uri uri = this.iconParam;
        int i10 = 0;
        if (uri == null) {
            i2 = 0;
        } else {
            i2 = uri.hashCode();
        }
        int i11 = (d + i2) * 31;
        Uri uri2 = this.backgroundParam;
        if (uri2 == null) {
            i7 = 0;
        } else {
            i7 = uri2.hashCode();
        }
        int c5 = C0212a.c(C0212a.e((Double.hashCode(this.scoreParam) + C0212a.f(this.itemsParam, (i11 + i7) * 31, 31)) * 31, 31, this.validParam), 31, this.validTimeParam);
        Bundle bundle = this.extrasParam;
        if (bundle == null) {
            i8 = 0;
        } else {
            i8 = bundle.hashCode();
        }
        int i12 = (c5 + i8) * 31;
        String str = this.urlParam;
        if (str != null) {
            i10 = str.hashCode();
        }
        return i12 + i10;
    }

    public String toString() {
        String str = this.idParam;
        String str2 = this.titleParam;
        String str3 = this.descriptionParam;
        Uri uri = this.iconParam;
        Uri uri2 = this.backgroundParam;
        List<SuggestionData> list = this.itemsParam;
        double d = this.scoreParam;
        boolean z = this.validParam;
        long j2 = this.validTimeParam;
        Bundle bundle = this.extrasParam;
        String str4 = this.urlParam;
        StringBuilder q = C0086a.q("SuggestionItem(idParam=", str, ", titleParam=", str2, ", descriptionParam=");
        q.append(str3);
        q.append(", iconParam=");
        q.append(uri);
        q.append(", backgroundParam=");
        q.append(uri2);
        q.append(", itemsParam=");
        q.append(list);
        q.append(", scoreParam=");
        q.append(d);
        q.append(", validParam=");
        q.append(z);
        q.append(", validTimeParam=");
        q.append(j2);
        q.append(", extrasParam=");
        q.append(bundle);
        q.append(", urlParam=");
        q.append(str4);
        q.append(")");
        return q.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "parcel");
        parcel.writeString(this.idParam);
        parcel.writeString(this.titleParam);
        parcel.writeString(this.descriptionParam);
        parcel.writeParcelable(this.iconParam, i2);
        parcel.writeParcelable(this.backgroundParam, i2);
        parcel.writeTypedList(this.itemsParam);
        parcel.writeDouble(this.scoreParam);
        parcel.writeByte(this.validParam ? (byte) 1 : 0);
        parcel.writeLong(this.validTimeParam);
        parcel.writeBundle(this.extrasParam);
        parcel.writeString(this.urlParam);
    }

    public SuggestionItem(String str, String str2, String str3, Uri uri, Uri uri2, List<SuggestionData> list, double d, boolean z, long j2, Bundle bundle, String str4) {
        j.e(str, "idParam");
        j.e(str2, "titleParam");
        j.e(str3, "descriptionParam");
        j.e(list, "itemsParam");
        this.idParam = str;
        this.titleParam = str2;
        this.descriptionParam = str3;
        this.iconParam = uri;
        this.backgroundParam = uri2;
        this.itemsParam = list;
        this.scoreParam = d;
        this.validParam = z;
        this.validTimeParam = j2;
        this.extrasParam = bundle;
        this.urlParam = str4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SuggestionItem(java.lang.String r14, java.lang.String r15, java.lang.String r16, android.net.Uri r17, android.net.Uri r18, java.util.List r19, double r20, boolean r22, long r23, android.os.Bundle r25, java.lang.String r26, int r27, kotlin.jvm.internal.e r28) {
        /*
            r13 = this;
            r0 = r27
            r1 = r0 & 1
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0009
            r14 = r2
        L_0x0009:
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000f
            r1 = r2
            goto L_0x0010
        L_0x000f:
            r1 = r15
        L_0x0010:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r2 = r16
        L_0x0017:
            r3 = r0 & 8
            r4 = 0
            if (r3 == 0) goto L_0x001e
            r3 = r4
            goto L_0x0020
        L_0x001e:
            r3 = r17
        L_0x0020:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0026
            r5 = r4
            goto L_0x0028
        L_0x0026:
            r5 = r18
        L_0x0028:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x002f
            ne.t r6 = ne.C1202t.d
            goto L_0x0031
        L_0x002f:
            r6 = r19
        L_0x0031:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x0038
            r7 = 0
            goto L_0x003a
        L_0x0038:
            r7 = r20
        L_0x003a:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0040
            r9 = 0
            goto L_0x0042
        L_0x0040:
            r9 = r22
        L_0x0042:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0049
            r10 = 0
            goto L_0x004b
        L_0x0049:
            r10 = r23
        L_0x004b:
            r12 = r0 & 512(0x200, float:7.175E-43)
            if (r12 == 0) goto L_0x0051
            r12 = r4
            goto L_0x0053
        L_0x0051:
            r12 = r25
        L_0x0053:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x006e
            r27 = r4
        L_0x0059:
            r15 = r14
            r16 = r1
            r17 = r2
            r18 = r3
            r19 = r5
            r20 = r6
            r21 = r7
            r23 = r9
            r24 = r10
            r26 = r12
            r14 = r13
            goto L_0x0071
        L_0x006e:
            r27 = r26
            goto L_0x0059
        L_0x0071:
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r23, r24, r26, r27)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionItem.<init>(java.lang.String, java.lang.String, java.lang.String, android.net.Uri, android.net.Uri, java.util.List, double, boolean, long, android.os.Bundle, java.lang.String, int, kotlin.jvm.internal.e):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionItem(android.os.Parcel r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "parcel"
            kotlin.jvm.internal.j.e(r0, r1)
            java.lang.String r1 = r0.readString()
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x0011
            r4 = r2
            goto L_0x0012
        L_0x0011:
            r4 = r1
        L_0x0012:
            java.lang.String r1 = r0.readString()
            if (r1 != 0) goto L_0x001a
            r5 = r2
            goto L_0x001b
        L_0x001a:
            r5 = r1
        L_0x001b:
            java.lang.String r1 = r0.readString()
            if (r1 != 0) goto L_0x0023
            r6 = r2
            goto L_0x0024
        L_0x0023:
            r6 = r1
        L_0x0024:
            java.lang.Class<android.net.Uri> r1 = android.net.Uri.class
            java.lang.ClassLoader r2 = r1.getClassLoader()
            android.os.Parcelable r2 = r0.readParcelable(r2)
            r7 = r2
            android.net.Uri r7 = (android.net.Uri) r7
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Parcelable r1 = r0.readParcelable(r1)
            r8 = r1
            android.net.Uri r8 = (android.net.Uri) r8
            com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionData$CREATOR r1 = com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionData.CREATOR
            java.util.ArrayList r1 = r0.createTypedArrayList(r1)
            if (r1 == 0) goto L_0x0046
        L_0x0044:
            r9 = r1
            goto L_0x0049
        L_0x0046:
            ne.t r1 = ne.C1202t.d
            goto L_0x0044
        L_0x0049:
            double r10 = r0.readDouble()
            byte r1 = r0.readByte()
            if (r1 == 0) goto L_0x0056
            r1 = 1
        L_0x0054:
            r12 = r1
            goto L_0x0058
        L_0x0056:
            r1 = 0
            goto L_0x0054
        L_0x0058:
            long r13 = r0.readLong()
            java.lang.Class<android.os.Bundle> r1 = android.os.Bundle.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Bundle r15 = r0.readBundle(r1)
            java.lang.String r16 = r0.readString()
            r3 = r17
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r13, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.SuggestionItem.<init>(android.os.Parcel):void");
    }

    public static /* synthetic */ void getUrl$annotations() {
    }
}
