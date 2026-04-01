package com.samsung.android.sdk.moneta.memory.entity.context;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.AddressBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0016Jb\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0016J\u0010\u0010 \u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b \u0010\u0014J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0016R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b(\u0010\u0016R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b)\u0010\u0016R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b*\u0010\u0016R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b+\u0010\u0016R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b,\u0010\u0016R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b-\u0010\u0016¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "Landroid/os/Parcelable;", "", "fullAddress", "countryName", "adminArea", "subAdminArea", "locality", "subLocality", "streetName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getFullAddress", "getCountryName", "getAdminArea", "getSubAdminArea", "getLocality", "getSubLocality", "getStreetName", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Address implements Parcelable {
    public static final Parcelable.Creator<Address> CREATOR = new Creator();
    private final String adminArea;
    private final String countryName;
    private final String fullAddress;
    private final String locality;
    private final String streetName;
    private final String subAdminArea;
    private final String subLocality;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Address> {
        public final Address createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Address(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final Address[] newArray(int i2) {
            return new Address[i2];
        }
    }

    public Address(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        j.e(str, AddressBundleWrapper.BUNDLE_KEY_FULL_ADDRESS);
        this.fullAddress = str;
        this.countryName = str2;
        this.adminArea = str3;
        this.subAdminArea = str4;
        this.locality = str5;
        this.subLocality = str6;
        this.streetName = str7;
    }

    public static /* synthetic */ Address copy$default(Address address, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = address.fullAddress;
        }
        if ((i2 & 2) != 0) {
            str2 = address.countryName;
        }
        if ((i2 & 4) != 0) {
            str3 = address.adminArea;
        }
        if ((i2 & 8) != 0) {
            str4 = address.subAdminArea;
        }
        if ((i2 & 16) != 0) {
            str5 = address.locality;
        }
        if ((i2 & 32) != 0) {
            str6 = address.subLocality;
        }
        if ((i2 & 64) != 0) {
            str7 = address.streetName;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str4;
        String str11 = str5;
        return address.copy(str, str2, str3, str10, str11, str8, str9);
    }

    public final String component1() {
        return this.fullAddress;
    }

    public final String component2() {
        return this.countryName;
    }

    public final String component3() {
        return this.adminArea;
    }

    public final String component4() {
        return this.subAdminArea;
    }

    public final String component5() {
        return this.locality;
    }

    public final String component6() {
        return this.subLocality;
    }

    public final String component7() {
        return this.streetName;
    }

    public final Address copy(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        j.e(str, AddressBundleWrapper.BUNDLE_KEY_FULL_ADDRESS);
        return new Address(str, str2, str3, str4, str5, str6, str7);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        if (j.a(this.fullAddress, address.fullAddress) && j.a(this.countryName, address.countryName) && j.a(this.adminArea, address.adminArea) && j.a(this.subAdminArea, address.subAdminArea) && j.a(this.locality, address.locality) && j.a(this.subLocality, address.subLocality) && j.a(this.streetName, address.streetName)) {
            return true;
        }
        return false;
    }

    public final String getAdminArea() {
        return this.adminArea;
    }

    public final String getCountryName() {
        return this.countryName;
    }

    public final String getFullAddress() {
        return this.fullAddress;
    }

    public final String getLocality() {
        return this.locality;
    }

    public final String getStreetName() {
        return this.streetName;
    }

    public final String getSubAdminArea() {
        return this.subAdminArea;
    }

    public final String getSubLocality() {
        return this.subLocality;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int hashCode = this.fullAddress.hashCode() * 31;
        String str = this.countryName;
        int i12 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i13 = (hashCode + i2) * 31;
        String str2 = this.adminArea;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        String str3 = this.subAdminArea;
        if (str3 == null) {
            i8 = 0;
        } else {
            i8 = str3.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        String str4 = this.locality;
        if (str4 == null) {
            i10 = 0;
        } else {
            i10 = str4.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        String str5 = this.subLocality;
        if (str5 == null) {
            i11 = 0;
        } else {
            i11 = str5.hashCode();
        }
        int i17 = (i16 + i11) * 31;
        String str6 = this.streetName;
        if (str6 != null) {
            i12 = str6.hashCode();
        }
        return i17 + i12;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Address(fullAddress=");
        sb2.append(this.fullAddress);
        sb2.append(", countryName=");
        sb2.append(this.countryName);
        sb2.append(", adminArea=");
        sb2.append(this.adminArea);
        sb2.append(", subAdminArea=");
        sb2.append(this.subAdminArea);
        sb2.append(", locality=");
        sb2.append(this.locality);
        sb2.append(", subLocality=");
        sb2.append(this.subLocality);
        sb2.append(", streetName=");
        return C0086a.m(sb2, this.streetName, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.fullAddress);
        parcel.writeString(this.countryName);
        parcel.writeString(this.adminArea);
        parcel.writeString(this.subAdminArea);
        parcel.writeString(this.locality);
        parcel.writeString(this.subLocality);
        parcel.writeString(this.streetName);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Address(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7);
    }
}
