package com.samsung.android.sdk.moneta.memory.entity.context;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PoiBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJJ\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b \u0010\u001aJ\u0010\u0010!\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b!\u0010\u0015J\u001a\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\"HÖ\u0003¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010\u0017R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b)\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b+\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b,\u0010\u001aR\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b.\u0010\u001d¨\u0006/"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "Landroid/os/Parcelable;", "", "latitude", "longitude", "", "poiCity", "poiName", "", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PoiCategory;", "poiCategory", "<init>", "(DDLjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()D", "component2", "component3", "()Ljava/lang/String;", "component4", "component5", "()Ljava/util/List;", "copy", "(DDLjava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "D", "getLatitude", "getLongitude", "Ljava/lang/String;", "getPoiCity", "getPoiName", "Ljava/util/List;", "getPoiCategory", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Poi implements Parcelable {
    public static final Parcelable.Creator<Poi> CREATOR = new Creator();
    private final double latitude;
    private final double longitude;
    private final List<PoiCategory> poiCategory;
    private final String poiCity;
    private final String poiName;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Poi> {
        public final Poi createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            j.e(parcel, "parcel");
            double readDouble = parcel.readDouble();
            double readDouble2 = parcel.readDouble();
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            if (parcel.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(PoiCategory.CREATOR, parcel, arrayList2, i2, 1);
                }
                arrayList = arrayList2;
            }
            return new Poi(readDouble, readDouble2, readString, readString2, arrayList);
        }

        public final Poi[] newArray(int i2) {
            return new Poi[i2];
        }
    }

    public Poi(double d, double d2, String str, String str2, List<? extends PoiCategory> list) {
        j.e(str, PoiBundleWrapper.BUNDLE_KEY_POI_CITY);
        j.e(str2, PoiBundleWrapper.BUNDLE_KEY_POI_NAME);
        this.latitude = d;
        this.longitude = d2;
        this.poiCity = str;
        this.poiName = str2;
        this.poiCategory = list;
    }

    public static /* synthetic */ Poi copy$default(Poi poi, double d, double d2, String str, String str2, List<PoiCategory> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d = poi.latitude;
        }
        double d3 = d;
        if ((i2 & 2) != 0) {
            d2 = poi.longitude;
        }
        double d5 = d2;
        if ((i2 & 4) != 0) {
            str = poi.poiCity;
        }
        String str3 = str;
        if ((i2 & 8) != 0) {
            str2 = poi.poiName;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            list = poi.poiCategory;
        }
        return poi.copy(d3, d5, str3, str4, list);
    }

    public final double component1() {
        return this.latitude;
    }

    public final double component2() {
        return this.longitude;
    }

    public final String component3() {
        return this.poiCity;
    }

    public final String component4() {
        return this.poiName;
    }

    public final List<PoiCategory> component5() {
        return this.poiCategory;
    }

    public final Poi copy(double d, double d2, String str, String str2, List<? extends PoiCategory> list) {
        j.e(str, PoiBundleWrapper.BUNDLE_KEY_POI_CITY);
        j.e(str2, PoiBundleWrapper.BUNDLE_KEY_POI_NAME);
        return new Poi(d, d2, str, str2, list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Poi)) {
            return false;
        }
        Poi poi = (Poi) obj;
        if (Double.compare(this.latitude, poi.latitude) == 0 && Double.compare(this.longitude, poi.longitude) == 0 && j.a(this.poiCity, poi.poiCity) && j.a(this.poiName, poi.poiName) && j.a(this.poiCategory, poi.poiCategory)) {
            return true;
        }
        return false;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final List<PoiCategory> getPoiCategory() {
        return this.poiCategory;
    }

    public final String getPoiCity() {
        return this.poiCity;
    }

    public final String getPoiName() {
        return this.poiName;
    }

    public int hashCode() {
        int i2;
        int d = C0212a.d(C0212a.d((Double.hashCode(this.longitude) + (Double.hashCode(this.latitude) * 31)) * 31, 31, this.poiCity), 31, this.poiName);
        List<PoiCategory> list = this.poiCategory;
        if (list == null) {
            i2 = 0;
        } else {
            i2 = list.hashCode();
        }
        return d + i2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Poi(latitude=");
        sb2.append(this.latitude);
        sb2.append(", longitude=");
        sb2.append(this.longitude);
        sb2.append(", poiCity=");
        sb2.append(this.poiCity);
        sb2.append(", poiName=");
        sb2.append(this.poiName);
        sb2.append(", poiCategory=");
        return C0212a.r(sb2, this.poiCategory, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeString(this.poiCity);
        parcel.writeString(this.poiName);
        List<PoiCategory> list = this.poiCategory;
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(list.size());
        for (PoiCategory writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, i2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Poi(double d, double d2, String str, String str2, List list, int i2, e eVar) {
        this(d, d2, str, str2, (i2 & 16) != 0 ? C1202t.d : list);
    }
}
