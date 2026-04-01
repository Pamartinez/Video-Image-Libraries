package com.samsung.android.sdk.moneta.travel.model;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B[\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010\"\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0018Jr\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/Place;", "", "city", "", "country", "name", "address", "code", "types", "", "lat", "", "lng", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;)V", "getCity", "()Ljava/lang/String;", "getCountry", "getName", "getAddress", "getCode", "getTypes", "()Ljava/util/List;", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLng", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;)Lcom/samsung/android/sdk/moneta/travel/model/Place;", "equals", "", "other", "hashCode", "", "toString", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Place {
    private final String address;
    private final String city;
    private final String code;
    private final String country;
    private final Double lat;
    private final Double lng;
    private final String name;
    private final List<String> types;

    public Place(String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2) {
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

    public static /* synthetic */ Place copy$default(Place place, String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = place.city;
        }
        if ((i2 & 2) != 0) {
            str2 = place.country;
        }
        if ((i2 & 4) != 0) {
            str3 = place.name;
        }
        if ((i2 & 8) != 0) {
            str4 = place.address;
        }
        if ((i2 & 16) != 0) {
            str5 = place.code;
        }
        if ((i2 & 32) != 0) {
            list = place.types;
        }
        if ((i2 & 64) != 0) {
            d = place.lat;
        }
        if ((i2 & 128) != 0) {
            d2 = place.lng;
        }
        Double d3 = d;
        Double d5 = d2;
        String str6 = str5;
        List<String> list2 = list;
        String str7 = str4;
        String str8 = str2;
        return place.copy(str, str8, str3, str7, str6, list2, d3, d5);
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

    public final Place copy(String str, String str2, String str3, String str4, String str5, List<String> list, Double d, Double d2) {
        j.e(str3, "name");
        return new Place(str, str2, str3, str4, str5, list, d, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Place)) {
            return false;
        }
        Place place = (Place) obj;
        if (j.a(this.city, place.city) && j.a(this.country, place.country) && j.a(this.name, place.name) && j.a(this.address, place.address) && j.a(this.code, place.code) && j.a(this.types, place.types) && j.a(this.lat, place.lat) && j.a(this.lng, place.lng)) {
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
        return "Place(city=" + this.city + ", country=" + this.country + ", name=" + this.name + ", address=" + this.address + ", code=" + this.code + ", types=" + this.types + ", lat=" + this.lat + ", lng=" + this.lng + ')';
    }
}
