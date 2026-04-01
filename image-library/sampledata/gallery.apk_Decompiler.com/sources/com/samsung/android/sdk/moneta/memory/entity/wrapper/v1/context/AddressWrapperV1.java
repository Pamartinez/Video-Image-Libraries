package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.AddressBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.context.Address;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001e\u0010\u001aR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u001f\u0010\u001aR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b \u0010\u001a¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/AddressWrapperV1;", "Landroid/os/Parcelable;", "", "fullAddress", "countryName", "adminArea", "subAdminArea", "locality", "subLocality", "streetName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getFullAddress", "()Ljava/lang/String;", "getCountryName", "getAdminArea", "getSubAdminArea", "getLocality", "getSubLocality", "getStreetName", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AddressWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<AddressWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String adminArea;
    private final String countryName;
    private final String fullAddress;
    private final String locality;
    private final String streetName;
    private final String subAdminArea;
    private final String subLocality;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/AddressWrapperV1$Companion;", "", "<init>", "()V", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/AddressWrapperV1;", "address", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ AddressWrapperV1 fromContext(Address address) {
            j.e(address, "address");
            return new AddressWrapperV1(address.getFullAddress(), address.getCountryName(), address.getAdminArea(), address.getSubAdminArea(), address.getLocality(), address.getSubLocality(), address.getStreetName());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<AddressWrapperV1> {
        public final AddressWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new AddressWrapperV1(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final AddressWrapperV1[] newArray(int i2) {
            return new AddressWrapperV1[i2];
        }
    }

    public AddressWrapperV1(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        j.e(str, AddressBundleWrapper.BUNDLE_KEY_FULL_ADDRESS);
        this.fullAddress = str;
        this.countryName = str2;
        this.adminArea = str3;
        this.subAdminArea = str4;
        this.locality = str5;
        this.subLocality = str6;
        this.streetName = str7;
    }

    public final int describeContents() {
        return 0;
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

    public final /* synthetic */ Address toContext() {
        return new Address(this.fullAddress, this.countryName, this.adminArea, this.subAdminArea, this.locality, this.subLocality, this.streetName);
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
    public /* synthetic */ AddressWrapperV1(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7);
    }
}
