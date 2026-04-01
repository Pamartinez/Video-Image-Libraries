package com.samsung.android.sdk.moneta.event.entity;

import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.Q;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\b\b\u0018\u0000 L2\u00020\u0001:\u0002MLBu\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013B\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0012\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001aJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001aJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001aJ\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001aJ\u0010\u0010\"\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b$\u0010#J\u0010\u0010%\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010HÆ\u0003¢\u0006\u0004\b'\u0010(J\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010HÆ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b+\u0010\u001aJ\u0010\u0010,\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b,\u0010-J\u001a\u0010/\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b/\u00100J'\u00109\u001a\u0002062\u0006\u00101\u001a\u00020\u00002\u0006\u00103\u001a\u0002022\u0006\u00105\u001a\u000204H\u0001¢\u0006\u0004\b7\u00108R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b;\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010:\u001a\u0004\b<\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010:\u001a\u0004\b=\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010:\u001a\u0004\b>\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010:\u001a\u0004\b?\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010:\u001a\u0004\b@\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010:\u001a\u0004\bA\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010:\u001a\u0004\bB\u0010\u001aR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010C\u001a\u0004\bD\u0010#R\u0017\u0010\r\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010C\u001a\u0004\bE\u0010#R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\bG\u0010&R(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00108\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010H\u001a\u0004\bI\u0010(\"\u0004\bJ\u0010K¨\u0006N"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Where;", "", "", "placeName", "address", "poi", "country", "city", "postalCode", "sourcePackage", "sourceUri", "", "lng", "lat", "", "augmented", "", "tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZLjava/util/List;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZLjava/util/List;Lkg/a0;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()D", "component10", "component11", "()Z", "component12", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZLjava/util/List;)Lcom/samsung/android/sdk/moneta/event/entity/Where;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/event/entity/Where;Ljg/b;Lig/f;)V", "write$Self", "Ljava/lang/String;", "getPlaceName", "getAddress", "getPoi", "getCountry", "getCity", "getPostalCode", "getSourcePackage", "getSourceUri", "D", "getLng", "getLat", "Z", "getAugmented", "Ljava/util/List;", "getTags", "setTags", "(Ljava/util/List;)V", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Where {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, new C1122c(e0.f4693a)};
    public static final Companion Companion = new Companion((e) null);
    private final String address;
    private final boolean augmented;
    private final String city;
    private final String country;
    private final double lat;
    private final double lng;
    private final String placeName;
    private final String poi;
    private final String postalCode;
    private final String sourcePackage;
    private final String sourceUri;
    private List<String> tags;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Where$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/event/entity/Where;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return Where$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ Where(int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z, List list, a0 a0Var) {
        List list2;
        if (255 == (i2 & ScoverState.TYPE_NFC_SMART_COVER)) {
            this.placeName = str;
            this.address = str2;
            this.poi = str3;
            this.country = str4;
            this.city = str5;
            this.postalCode = str6;
            this.sourcePackage = str7;
            this.sourceUri = str8;
            if ((i2 & 256) == 0) {
                this.lng = MapUtil.INVALID_LOCATION;
            } else {
                this.lng = d;
            }
            if ((i2 & 512) == 0) {
                this.lat = MapUtil.INVALID_LOCATION;
            } else {
                this.lat = d2;
            }
            this.augmented = (i2 & 1024) == 0 ? false : z;
            if ((i2 & 2048) == 0) {
                list2 = C1202t.d;
            } else {
                list2 = list;
            }
            this.tags = list2;
            return;
        }
        Q.f(i2, ScoverState.TYPE_NFC_SMART_COVER, Where$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ Where copy$default(Where where, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z, List list, int i2, Object obj) {
        int i7 = i2;
        return where.copy((i7 & 1) != 0 ? where.placeName : str, (i7 & 2) != 0 ? where.address : str2, (i7 & 4) != 0 ? where.poi : str3, (i7 & 8) != 0 ? where.country : str4, (i7 & 16) != 0 ? where.city : str5, (i7 & 32) != 0 ? where.postalCode : str6, (i7 & 64) != 0 ? where.sourcePackage : str7, (i7 & 128) != 0 ? where.sourceUri : str8, (i7 & 256) != 0 ? where.lng : d, (i7 & 512) != 0 ? where.lat : d2, (i7 & 1024) != 0 ? where.augmented : z, (i7 & 2048) != 0 ? where.tags : list);
    }

    public static final void write$Self$pde_sdk_1_0_40_release(Where where, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        k kVar = (k) bVar;
        kVar.u(fVar, 0, where.placeName);
        kVar.u(fVar, 1, where.address);
        kVar.u(fVar, 2, where.poi);
        kVar.u(fVar, 3, where.country);
        kVar.u(fVar, 4, where.city);
        kVar.u(fVar, 5, where.postalCode);
        kVar.u(fVar, 6, where.sourcePackage);
        kVar.u(fVar, 7, where.sourceUri);
        if (kVar.d(fVar) || Double.compare(where.lng, MapUtil.INVALID_LOCATION) != 0) {
            double d = where.lng;
            kVar.q(fVar, 8);
            kVar.p(d);
        }
        if (kVar.d(fVar) || Double.compare(where.lat, MapUtil.INVALID_LOCATION) != 0) {
            double d2 = where.lat;
            kVar.q(fVar, 9);
            kVar.p(d2);
        }
        if (kVar.d(fVar) || where.augmented) {
            kVar.o(fVar, 10, where.augmented);
        }
        if (kVar.d(fVar) || !j.a(where.tags, C1202t.d)) {
            kVar.t(fVar, 11, aVarArr[11], where.tags);
        }
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

    public final List<String> component12() {
        return this.tags;
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

    public final Where copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z, List<String> list) {
        String str9 = str;
        j.e(str9, "placeName");
        String str10 = str2;
        j.e(str10, "address");
        String str11 = str3;
        j.e(str11, "poi");
        String str12 = str4;
        j.e(str12, BuddyContract.Address.COUNTRY);
        String str13 = str5;
        j.e(str13, BuddyContract.Address.CITY);
        String str14 = str6;
        j.e(str14, "postalCode");
        String str15 = str7;
        j.e(str15, "sourcePackage");
        String str16 = str8;
        j.e(str16, "sourceUri");
        List<String> list2 = list;
        j.e(list2, "tags");
        return new Where(str9, str10, str11, str12, str13, str14, str15, str16, d, d2, z, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Where)) {
            return false;
        }
        Where where = (Where) obj;
        if (j.a(this.placeName, where.placeName) && j.a(this.address, where.address) && j.a(this.poi, where.poi) && j.a(this.country, where.country) && j.a(this.city, where.city) && j.a(this.postalCode, where.postalCode) && j.a(this.sourcePackage, where.sourcePackage) && j.a(this.sourceUri, where.sourceUri) && Double.compare(this.lng, where.lng) == 0 && Double.compare(this.lat, where.lat) == 0 && this.augmented == where.augmented && j.a(this.tags, where.tags)) {
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

    public final List<String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int d = C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(this.placeName.hashCode() * 31, 31, this.address), 31, this.poi), 31, this.country), 31, this.city), 31, this.postalCode), 31, this.sourcePackage), 31, this.sourceUri);
        return this.tags.hashCode() + C0212a.e((Double.hashCode(this.lat) + ((Double.hashCode(this.lng) + d) * 31)) * 31, 31, this.augmented);
    }

    public final void setTags(List<String> list) {
        j.e(list, "<set-?>");
        this.tags = list;
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
        sb2.append(this.augmented);
        sb2.append(", tags=");
        return C0212a.r(sb2, this.tags, ')');
    }

    public Where(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2, boolean z, List<String> list) {
        j.e(str, "placeName");
        j.e(str2, "address");
        j.e(str3, "poi");
        j.e(str4, BuddyContract.Address.COUNTRY);
        j.e(str5, BuddyContract.Address.CITY);
        j.e(str6, "postalCode");
        j.e(str7, "sourcePackage");
        j.e(str8, "sourceUri");
        j.e(list, "tags");
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
        this.tags = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Where(java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, double r28, double r30, boolean r32, java.util.List r33, int r34, kotlin.jvm.internal.e r35) {
        /*
            r19 = this;
            r0 = r34
            r1 = r0 & 256(0x100, float:3.59E-43)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            r13 = r2
            goto L_0x000c
        L_0x000a:
            r13 = r28
        L_0x000c:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0012
            r15 = r2
            goto L_0x0014
        L_0x0012:
            r15 = r30
        L_0x0014:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x001c
            r1 = 0
            r17 = r1
            goto L_0x001e
        L_0x001c:
            r17 = r32
        L_0x001e:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0039
            ne.t r0 = ne.C1202t.d
            r18 = r0
        L_0x0026:
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            goto L_0x003c
        L_0x0039:
            r18 = r33
            goto L_0x0026
        L_0x003c:
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r15, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.Where.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, boolean, java.util.List, int, kotlin.jvm.internal.e):void");
    }
}
