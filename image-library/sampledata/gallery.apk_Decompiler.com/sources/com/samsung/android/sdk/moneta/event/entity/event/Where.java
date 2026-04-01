package com.samsung.android.sdk.moneta.event.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001cJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001cJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001cJ\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001cJ\u0010\u0010\"\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001cJ\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u001cJ\u0010\u0010$\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b&\u0010%J\u0010\u0010'\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b'\u0010(J~\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b+\u0010\u001cJ\u0010\u0010,\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b,\u0010\u001aJ\u001a\u0010/\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010-HÖ\u0003¢\u0006\u0004\b/\u00100R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00101\u001a\u0004\b2\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00101\u001a\u0004\b3\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u00101\u001a\u0004\b4\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u00101\u001a\u0004\b5\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00101\u001a\u0004\b6\u0010\u001cR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u00101\u001a\u0004\b7\u0010\u001cR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u00101\u001a\u0004\b8\u0010\u001cR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u00101\u001a\u0004\b9\u0010\u001cR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\b;\u0010%R\u0017\u0010\r\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010:\u001a\u0004\b<\u0010%R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010=\u001a\u0004\b>\u0010(¨\u0006?"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/Where;", "Landroid/os/Parcelable;", "", "placeName", "address", "poi", "country", "city", "postalCode", "sourcePackage", "sourceUri", "", "lng", "lat", "", "augmented", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZ)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()D", "component10", "component11", "()Z", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZ)Lcom/samsung/android/sdk/moneta/event/entity/event/Where;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getPlaceName", "getAddress", "getPoi", "getCountry", "getCity", "getPostalCode", "getSourcePackage", "getSourceUri", "D", "getLng", "getLat", "Z", "getAugmented", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Where implements Parcelable {
    public static final Parcelable.Creator<Where> CREATOR = new Creator();
    private final /* synthetic */ String address;
    private final /* synthetic */ boolean augmented;
    private final /* synthetic */ String city;
    private final /* synthetic */ String country;
    private final /* synthetic */ double lat;
    private final /* synthetic */ double lng;
    private final /* synthetic */ String placeName;
    private final /* synthetic */ String poi;
    private final /* synthetic */ String postalCode;
    private final /* synthetic */ String sourcePackage;
    private final /* synthetic */ String sourceUri;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Where> {
        public final Where createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Where(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readInt() != 0);
        }

        public final Where[] newArray(int i2) {
            return new Where[i2];
        }
    }

    public Where(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z) {
        j.e(str, "placeName");
        j.e(str2, "address");
        j.e(str3, "poi");
        j.e(str4, BuddyContract.Address.COUNTRY);
        j.e(str5, BuddyContract.Address.CITY);
        j.e(str6, "postalCode");
        j.e(str7, "sourcePackage");
        j.e(str8, "sourceUri");
        this.placeName = str;
        this.address = str2;
        this.poi = str3;
        this.country = str4;
        this.city = str5;
        this.postalCode = str6;
        this.sourcePackage = str7;
        this.sourceUri = str8;
        this.lng = d;
        this.lat = d2;
        this.augmented = z;
    }

    public static /* synthetic */ Where copy$default(Where where, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z, int i2, Object obj) {
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        double d3;
        double d5;
        boolean z3;
        int i7 = i2;
        if ((i7 & 1) != 0) {
            str = where.placeName;
        }
        if ((i7 & 2) != 0) {
            str9 = where.address;
        } else {
            str9 = str2;
        }
        if ((i7 & 4) != 0) {
            str10 = where.poi;
        } else {
            str10 = str3;
        }
        if ((i7 & 8) != 0) {
            str11 = where.country;
        } else {
            str11 = str4;
        }
        if ((i7 & 16) != 0) {
            str12 = where.city;
        } else {
            str12 = str5;
        }
        if ((i7 & 32) != 0) {
            str13 = where.postalCode;
        } else {
            str13 = str6;
        }
        if ((i7 & 64) != 0) {
            str14 = where.sourcePackage;
        } else {
            str14 = str7;
        }
        if ((i7 & 128) != 0) {
            str15 = where.sourceUri;
        } else {
            str15 = str8;
        }
        if ((i7 & 256) != 0) {
            d3 = where.lng;
        } else {
            d3 = d;
        }
        if ((i7 & 512) != 0) {
            d5 = where.lat;
        } else {
            d5 = d2;
        }
        if ((i7 & 1024) != 0) {
            z3 = where.augmented;
        } else {
            z3 = z;
        }
        return where.copy(str, str9, str10, str11, str12, str13, str14, str15, d3, d5, z3);
    }

    public final String component1() {
        return this.placeName;
    }

    public final double component10() {
        return this.lat;
    }

    public final boolean component11() {
        return this.augmented;
    }

    public final String component2() {
        return this.address;
    }

    public final String component3() {
        return this.poi;
    }

    public final String component4() {
        return this.country;
    }

    public final String component5() {
        return this.city;
    }

    public final String component6() {
        return this.postalCode;
    }

    public final String component7() {
        return this.sourcePackage;
    }

    public final String component8() {
        return this.sourceUri;
    }

    public final double component9() {
        return this.lng;
    }

    public final Where copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z) {
        j.e(str, "placeName");
        String str9 = str2;
        j.e(str9, "address");
        String str10 = str3;
        j.e(str10, "poi");
        String str11 = str4;
        j.e(str11, BuddyContract.Address.COUNTRY);
        String str12 = str5;
        j.e(str12, BuddyContract.Address.CITY);
        String str13 = str6;
        j.e(str13, "postalCode");
        String str14 = str7;
        j.e(str14, "sourcePackage");
        String str15 = str8;
        j.e(str15, "sourceUri");
        return new Where(str, str9, str10, str11, str12, str13, str14, str15, d, d2, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Where)) {
            return false;
        }
        Where where = (Where) obj;
        if (j.a(this.placeName, where.placeName) && j.a(this.address, where.address) && j.a(this.poi, where.poi) && j.a(this.country, where.country) && j.a(this.city, where.city) && j.a(this.postalCode, where.postalCode) && j.a(this.sourcePackage, where.sourcePackage) && j.a(this.sourceUri, where.sourceUri) && Double.compare(this.lng, where.lng) == 0 && Double.compare(this.lat, where.lat) == 0 && this.augmented == where.augmented) {
            return true;
        }
        return false;
    }

    public final String getAddress() {
        return this.address;
    }

    public final boolean getAugmented() {
        return this.augmented;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCountry() {
        return this.country;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLng() {
        return this.lng;
    }

    public final String getPlaceName() {
        return this.placeName;
    }

    public final String getPoi() {
        return this.poi;
    }

    public final String getPostalCode() {
        return this.postalCode;
    }

    public final String getSourcePackage() {
        return this.sourcePackage;
    }

    public final String getSourceUri() {
        return this.sourceUri;
    }

    public int hashCode() {
        int d = C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(this.placeName.hashCode() * 31, 31, this.address), 31, this.poi), 31, this.country), 31, this.city), 31, this.postalCode), 31, this.sourcePackage), 31, this.sourceUri);
        int hashCode = Double.hashCode(this.lat);
        return Boolean.hashCode(this.augmented) + ((hashCode + ((Double.hashCode(this.lng) + d) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Where(placeName=");
        sb2.append(this.placeName);
        sb2.append(", address=");
        sb2.append(this.address);
        sb2.append(", poi=");
        sb2.append(this.poi);
        sb2.append(", country=");
        sb2.append(this.country);
        sb2.append(", city=");
        sb2.append(this.city);
        sb2.append(", postalCode=");
        sb2.append(this.postalCode);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        sb2.append(this.sourceUri);
        sb2.append(", lng=");
        sb2.append(this.lng);
        sb2.append(", lat=");
        sb2.append(this.lat);
        sb2.append(", augmented=");
        return C0086a.n(sb2, this.augmented, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.placeName);
        parcel.writeString(this.address);
        parcel.writeString(this.poi);
        parcel.writeString(this.country);
        parcel.writeString(this.city);
        parcel.writeString(this.postalCode);
        parcel.writeString(this.sourcePackage);
        parcel.writeString(this.sourceUri);
        parcel.writeDouble(this.lng);
        parcel.writeDouble(this.lat);
        parcel.writeInt(this.augmented ? 1 : 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Where(java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, double r27, double r29, boolean r31, int r32, kotlin.jvm.internal.e r33) {
        /*
            r18 = this;
            r0 = r32
            r1 = r0 & 256(0x100, float:3.59E-43)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            r13 = r2
            goto L_0x000c
        L_0x000a:
            r13 = r27
        L_0x000c:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0012
            r15 = r2
            goto L_0x0014
        L_0x0012:
            r15 = r29
        L_0x0014:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x002e
            r0 = 0
            r17 = r0
        L_0x001b:
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            goto L_0x0031
        L_0x002e:
            r17 = r31
            goto L_0x001b
        L_0x0031:
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r15, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.event.Where.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, boolean, int, kotlin.jvm.internal.e):void");
    }
}
