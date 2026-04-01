package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Address;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/AddressBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AddressBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_ADMIN_AREA = "adminArea";
    public static final String BUNDLE_KEY_COUNTRY_NAME = "countryName";
    public static final String BUNDLE_KEY_FULL_ADDRESS = "fullAddress";
    public static final String BUNDLE_KEY_LOCALITY = "locality";
    public static final String BUNDLE_KEY_STREET_NAME = "streetName";
    public static final String BUNDLE_KEY_SUB_ADMIN_AREA = "subAdminArea";
    public static final String BUNDLE_KEY_SUB_LOCALITY = "subLocality";
    public static final Parcelable.Creator<AddressBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/AddressBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_FULL_ADDRESS", "", "BUNDLE_KEY_COUNTRY_NAME", "BUNDLE_KEY_ADMIN_AREA", "BUNDLE_KEY_SUB_ADMIN_AREA", "BUNDLE_KEY_LOCALITY", "BUNDLE_KEY_SUB_LOCALITY", "BUNDLE_KEY_STREET_NAME", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/AddressBundleWrapper;", "address", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AddressBundleWrapper fromContext(Address address) {
            j.e(address, "address");
            Bundle bundle = new Bundle();
            bundle.putString(AddressBundleWrapper.BUNDLE_KEY_FULL_ADDRESS, address.getFullAddress());
            String countryName = address.getCountryName();
            if (countryName != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_COUNTRY_NAME, countryName);
            }
            String adminArea = address.getAdminArea();
            if (adminArea != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_ADMIN_AREA, adminArea);
            }
            String subAdminArea = address.getSubAdminArea();
            if (subAdminArea != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_SUB_ADMIN_AREA, subAdminArea);
            }
            String locality = address.getLocality();
            if (locality != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_LOCALITY, locality);
            }
            String subLocality = address.getSubLocality();
            if (subLocality != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_SUB_LOCALITY, subLocality);
            }
            String streetName = address.getStreetName();
            if (streetName != null) {
                bundle.putString(AddressBundleWrapper.BUNDLE_KEY_STREET_NAME, streetName);
            }
            return new AddressBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<AddressBundleWrapper> {
        public final AddressBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new AddressBundleWrapper(parcel.readBundle(AddressBundleWrapper.class.getClassLoader()));
        }

        public final AddressBundleWrapper[] newArray(int i2) {
            return new AddressBundleWrapper[i2];
        }
    }

    public AddressBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ Address toContext() {
        String string = this.properties.getString(BUNDLE_KEY_FULL_ADDRESS, "");
        j.d(string, "getString(...)");
        return new Address(string, this.properties.getString(BUNDLE_KEY_COUNTRY_NAME), this.properties.getString(BUNDLE_KEY_ADMIN_AREA), this.properties.getString(BUNDLE_KEY_SUB_ADMIN_AREA), this.properties.getString(BUNDLE_KEY_LOCALITY), this.properties.getString(BUNDLE_KEY_SUB_LOCALITY), this.properties.getString(BUNDLE_KEY_STREET_NAME));
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
