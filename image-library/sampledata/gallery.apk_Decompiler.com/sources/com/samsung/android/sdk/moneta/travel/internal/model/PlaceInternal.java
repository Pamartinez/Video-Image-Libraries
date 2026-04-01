package com.samsung.android.sdk.moneta.travel.internal.model;

import com.samsung.android.sdk.cover.ScoverState;
import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.C1136q;
import kg.Q;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0010\b\b\u0018\u0000 <2\u00020\u0001:\u0002=<B[\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eBq\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0013J'\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001eJ\u0012\u0010!\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001eJ\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001eJ\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0012\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b'\u0010&Jt\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b*\u0010\u001eJ\u0010\u0010+\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b+\u0010,J\u001a\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b/\u00100R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00101\u001a\u0004\b2\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00101\u001a\u0004\b3\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u00101\u001a\u0004\b4\u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u00101\u001a\u0004\b5\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00101\u001a\u0004\b6\u0010\u001eR\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u00107\u001a\u0004\b8\u0010$R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00109\u001a\u0004\b:\u0010&R\u0019\u0010\f\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u00109\u001a\u0004\b;\u0010&¨\u0006>"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "", "", "city", "country", "name", "address", "code", "", "types", "", "lat", "lng", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "()Ljava/util/List;", "component7", "()Ljava/lang/Double;", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;)Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getCity", "getCountry", "getName", "getAddress", "getCode", "Ljava/util/List;", "getTypes", "Ljava/lang/Double;", "getLat", "getLng", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceInternal {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, null, null, null, new C1122c(e0.f4693a), null, null};
    public static final Companion Companion = new Companion((e) null);
    private final String address;
    private final String city;
    private final String code;
    private final String country;
    private final Double lat;
    private final Double lng;
    private final String name;
    private final List<String> types;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return PlaceInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ PlaceInternal(int i2, String str, String str2, String str3, String str4, String str5, List list, Double d, Double d2, a0 a0Var) {
        if (255 == (i2 & ScoverState.TYPE_NFC_SMART_COVER)) {
            this.city = str;
            this.country = str2;
            this.name = str3;
            this.address = str4;
            this.code = str5;
            this.types = list;
            this.lat = d;
            this.lng = d2;
            return;
        }
        Q.f(i2, ScoverState.TYPE_NFC_SMART_COVER, PlaceInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ PlaceInternal copy$default(PlaceInternal placeInternal, String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = placeInternal.city;
        }
        if ((i2 & 2) != 0) {
            str2 = placeInternal.country;
        }
        if ((i2 & 4) != 0) {
            str3 = placeInternal.name;
        }
        if ((i2 & 8) != 0) {
            str4 = placeInternal.address;
        }
        if ((i2 & 16) != 0) {
            str5 = placeInternal.code;
        }
        if ((i2 & 32) != 0) {
            list = placeInternal.types;
        }
        if ((i2 & 64) != 0) {
            d = placeInternal.lat;
        }
        if ((i2 & 128) != 0) {
            d2 = placeInternal.lng;
        }
        Double d3 = d;
        Double d5 = d2;
        String str6 = str5;
        List<String> list2 = list;
        String str7 = str4;
        String str8 = str2;
        return placeInternal.copy(str, str8, str3, str7, str6, list2, d3, d5);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(PlaceInternal placeInternal, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        e0 e0Var = e0.f4693a;
        bVar.c(fVar, 0, e0Var, placeInternal.city);
        bVar.c(fVar, 1, e0Var, placeInternal.country);
        ((k) bVar).u(fVar, 2, placeInternal.name);
        bVar.c(fVar, 3, e0Var, placeInternal.address);
        bVar.c(fVar, 4, e0Var, placeInternal.code);
        bVar.c(fVar, 5, aVarArr[5], placeInternal.types);
        C1136q qVar = C1136q.f4714a;
        bVar.c(fVar, 6, qVar, placeInternal.lat);
        bVar.c(fVar, 7, qVar, placeInternal.lng);
    }

    public final String component1() {
        return this.city;
    }

    public final String component2() {
        return this.country;
    }

    public final String component3() {
        return this.name;
    }

    public final String component4() {
        return this.address;
    }

    public final String component5() {
        return this.code;
    }

    public final List<String> component6() {
        return this.types;
    }

    public final Double component7() {
        return this.lat;
    }

    public final Double component8() {
        return this.lng;
    }

    public final PlaceInternal copy(String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2) {
        j.e(str3, "name");
        return new PlaceInternal(str, str2, str3, str4, str5, list, d, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceInternal)) {
            return false;
        }
        PlaceInternal placeInternal = (PlaceInternal) obj;
        if (j.a(this.city, placeInternal.city) && j.a(this.country, placeInternal.country) && j.a(this.name, placeInternal.name) && j.a(this.address, placeInternal.address) && j.a(this.code, placeInternal.code) && j.a(this.types, placeInternal.types) && j.a(this.lat, placeInternal.lat) && j.a(this.lng, placeInternal.lng)) {
            return true;
        }
        return false;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getCountry() {
        return this.country;
    }

    public final Double getLat() {
        return this.lat;
    }

    public final Double getLng() {
        return this.lng;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getTypes() {
        return this.types;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        String str = this.city;
        int i13 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i14 = i2 * 31;
        String str2 = this.country;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int d = C0212a.d((i14 + i7) * 31, 31, this.name);
        String str3 = this.address;
        if (str3 == null) {
            i8 = 0;
        } else {
            i8 = str3.hashCode();
        }
        int i15 = (d + i8) * 31;
        String str4 = this.code;
        if (str4 == null) {
            i10 = 0;
        } else {
            i10 = str4.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        List<String> list = this.types;
        if (list == null) {
            i11 = 0;
        } else {
            i11 = list.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        Double d2 = this.lat;
        if (d2 == null) {
            i12 = 0;
        } else {
            i12 = d2.hashCode();
        }
        int i18 = (i17 + i12) * 31;
        Double d3 = this.lng;
        if (d3 != null) {
            i13 = d3.hashCode();
        }
        return i18 + i13;
    }

    public String toString() {
        return "PlaceInternal(city=" + this.city + ", country=" + this.country + ", name=" + this.name + ", address=" + this.address + ", code=" + this.code + ", types=" + this.types + ", lat=" + this.lat + ", lng=" + this.lng + ')';
    }

    public PlaceInternal(String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2) {
        j.e(str3, "name");
        this.city = str;
        this.country = str2;
        this.name = str3;
        this.address = str4;
        this.code = str5;
        this.types = list;
        this.lat = d;
        this.lng = d2;
    }
}
