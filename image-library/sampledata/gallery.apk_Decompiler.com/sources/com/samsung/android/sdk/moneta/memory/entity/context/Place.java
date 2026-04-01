package com.samsung.android.sdk.moneta.memory.entity.context;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b%\u0010$J\u0012\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b&\u0010$J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020\rHÆ\u0003¢\u0006\u0004\b'\u0010(J\u0018\u0010)\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b)\u0010(J\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rHÆ\u0001¢\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b,\u0010\u001dJ\u0010\u0010-\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b-\u0010\u001bJ\u001a\u00101\u001a\u0002002\b\u0010/\u001a\u0004\u0018\u00010.HÖ\u0003¢\u0006\u0004\b1\u00102R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00103\u001a\u0004\b4\u0010\u001dR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00103\u001a\u0004\b5\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u00106\u001a\u0004\b7\u0010 R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u00108\u001a\u0004\b9\u0010\"R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010:\u001a\u0004\b;\u0010$R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010:\u001a\u0004\b<\u0010$R\u0019\u0010\f\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\b=\u0010$R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010>\u001a\u0004\b?\u0010(R\u001f\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0010\u0010>\u001a\u0004\b@\u0010(¨\u0006A"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Landroid/os/Parcelable;", "", "id", "name", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "type", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "address", "", "latitude", "longitude", "radius", "", "names", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "poi", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "component5", "()Ljava/lang/Double;", "component6", "component7", "component8", "()Ljava/util/List;", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getName", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "getType", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "getAddress", "Ljava/lang/Double;", "getLatitude", "getLongitude", "getRadius", "Ljava/util/List;", "getNames", "getPoi", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Place implements Parcelable {
    public static final Parcelable.Creator<Place> CREATOR = new Creator();
    private final Address address;
    private final String id;
    private final Double latitude;
    private final Double longitude;
    private final String name;
    private final List<String> names;
    private final List<Poi> poi;
    private final Double radius;
    private final PlaceType type;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Place> {
        public final Place createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            PlaceType valueOf = PlaceType.valueOf(parcel.readString());
            ArrayList arrayList = null;
            Address createFromParcel = parcel.readInt() == 0 ? null : Address.CREATOR.createFromParcel(parcel);
            Double valueOf2 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            Double valueOf3 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            Double valueOf4 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(Poi.CREATOR, parcel, arrayList, i2, 1);
                }
            }
            return new Place(readString, readString2, valueOf, createFromParcel, valueOf2, valueOf3, valueOf4, createStringArrayList, arrayList);
        }

        public final Place[] newArray(int i2) {
            return new Place[i2];
        }
    }

    public Place(String str, String str2, PlaceType placeType, Address address2, Double d, Double d2, Double d3, List<String> list, List<Poi> list2) {
        j.e(str, "id");
        j.e(placeType, "type");
        j.e(list, "names");
        this.id = str;
        this.name = str2;
        this.type = placeType;
        this.address = address2;
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
        this.names = list;
        this.poi = list2;
    }

    public static /* synthetic */ Place copy$default(Place place, String str, String str2, PlaceType placeType, Address address2, Double d, Double d2, Double d3, List<String> list, List<Poi> list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = place.id;
        }
        if ((i2 & 2) != 0) {
            str2 = place.name;
        }
        if ((i2 & 4) != 0) {
            placeType = place.type;
        }
        if ((i2 & 8) != 0) {
            address2 = place.address;
        }
        if ((i2 & 16) != 0) {
            d = place.latitude;
        }
        if ((i2 & 32) != 0) {
            d2 = place.longitude;
        }
        if ((i2 & 64) != 0) {
            d3 = place.radius;
        }
        if ((i2 & 128) != 0) {
            list = place.names;
        }
        if ((i2 & 256) != 0) {
            list2 = place.poi;
        }
        List<String> list3 = list;
        List<Poi> list4 = list2;
        Double d5 = d2;
        Double d6 = d3;
        Address address3 = address2;
        Double d7 = d;
        return place.copy(str, str2, placeType, address3, d7, d5, d6, list3, list4);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final PlaceType component3() {
        return this.type;
    }

    public final Address component4() {
        return this.address;
    }

    public final Double component5() {
        return this.latitude;
    }

    public final Double component6() {
        return this.longitude;
    }

    public final Double component7() {
        return this.radius;
    }

    public final List<String> component8() {
        return this.names;
    }

    public final List<Poi> component9() {
        return this.poi;
    }

    public final Place copy(String str, String str2, PlaceType placeType, Address address2, Double d, Double d2, Double d3, List<String> list, List<Poi> list2) {
        j.e(str, "id");
        j.e(placeType, "type");
        List<String> list3 = list;
        j.e(list3, "names");
        return new Place(str, str2, placeType, address2, d, d2, d3, list3, list2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Place)) {
            return false;
        }
        Place place = (Place) obj;
        if (j.a(this.id, place.id) && j.a(this.name, place.name) && this.type == place.type && j.a(this.address, place.address) && j.a(this.latitude, place.latitude) && j.a(this.longitude, place.longitude) && j.a(this.radius, place.radius) && j.a(this.names, place.names) && j.a(this.poi, place.poi)) {
            return true;
        }
        return false;
    }

    public final Address getAddress() {
        return this.address;
    }

    public final String getId() {
        return this.id;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getNames() {
        return this.names;
    }

    public final List<Poi> getPoi() {
        return this.poi;
    }

    public final Double getRadius() {
        return this.radius;
    }

    public final PlaceType getType() {
        return this.type;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int hashCode = this.id.hashCode() * 31;
        String str = this.name;
        int i12 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int hashCode2 = (this.type.hashCode() + ((hashCode + i2) * 31)) * 31;
        Address address2 = this.address;
        if (address2 == null) {
            i7 = 0;
        } else {
            i7 = address2.hashCode();
        }
        int i13 = (hashCode2 + i7) * 31;
        Double d = this.latitude;
        if (d == null) {
            i8 = 0;
        } else {
            i8 = d.hashCode();
        }
        int i14 = (i13 + i8) * 31;
        Double d2 = this.longitude;
        if (d2 == null) {
            i10 = 0;
        } else {
            i10 = d2.hashCode();
        }
        int i15 = (i14 + i10) * 31;
        Double d3 = this.radius;
        if (d3 == null) {
            i11 = 0;
        } else {
            i11 = d3.hashCode();
        }
        int f = C0212a.f(this.names, (i15 + i11) * 31, 31);
        List<Poi> list = this.poi;
        if (list != null) {
            i12 = list.hashCode();
        }
        return f + i12;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Place(id=");
        sb2.append(this.id);
        sb2.append(", name=");
        sb2.append(this.name);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", address=");
        sb2.append(this.address);
        sb2.append(", latitude=");
        sb2.append(this.latitude);
        sb2.append(", longitude=");
        sb2.append(this.longitude);
        sb2.append(", radius=");
        sb2.append(this.radius);
        sb2.append(", names=");
        sb2.append(this.names);
        sb2.append(", poi=");
        return C0212a.r(sb2, this.poi, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.type.name());
        Address address2 = this.address;
        if (address2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            address2.writeToParcel(parcel, i2);
        }
        Double d = this.latitude;
        if (d == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d);
        }
        Double d2 = this.longitude;
        if (d2 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d2);
        }
        Double d3 = this.radius;
        if (d3 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d3);
        }
        parcel.writeStringList(this.names);
        List<Poi> list = this.poi;
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(list.size());
        for (Poi writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, i2);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Place(java.lang.String r13, java.lang.String r14, com.samsung.android.sdk.moneta.memory.entity.context.PlaceType r15, com.samsung.android.sdk.moneta.memory.entity.context.Address r16, java.lang.Double r17, java.lang.Double r18, java.lang.Double r19, java.util.List r20, java.util.List r21, int r22, kotlin.jvm.internal.e r23) {
        /*
            r12 = this;
            r0 = r22
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x000a
            ne.t r1 = ne.C1202t.d
            r10 = r1
            goto L_0x000c
        L_0x000a:
            r10 = r20
        L_0x000c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x001f
            r0 = 0
            r11 = r0
        L_0x0012:
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            goto L_0x0022
        L_0x001f:
            r11 = r21
            goto L_0x0012
        L_0x0022:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.context.Place.<init>(java.lang.String, java.lang.String, com.samsung.android.sdk.moneta.memory.entity.context.PlaceType, com.samsung.android.sdk.moneta.memory.entity.context.Address, java.lang.Double, java.lang.Double, java.lang.Double, java.util.List, java.util.List, int, kotlin.jvm.internal.e):void");
    }
}
