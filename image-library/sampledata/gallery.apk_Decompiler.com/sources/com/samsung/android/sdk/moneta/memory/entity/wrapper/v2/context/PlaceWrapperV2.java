package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Address;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.context.PlaceType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(BI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0005¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010\u0019R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010#\u001a\u0004\b&\u0010%R\u0019\u0010\f\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\f\u0010#\u001a\u0004\b'\u0010%¨\u0006)"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "Landroid/os/Parcelable;", "", "id", "name", "", "type", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/AddressWrapperV2;", "addressWrapper", "", "latitude", "longitude", "radius", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/AddressWrapperV2;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getName", "I", "getType", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/AddressWrapperV2;", "getAddressWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/AddressWrapperV2;", "Ljava/lang/Double;", "getLatitude", "()Ljava/lang/Double;", "getLongitude", "getRadius", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceWrapperV2 implements Parcelable {
    public static final Parcelable.Creator<PlaceWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final AddressWrapperV2 addressWrapper;
    private final String id;
    private final Double latitude;
    private final Double longitude;
    private final String name;
    private final Double radius;
    private final int type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2$Companion;", "", "<init>", "()V", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "place", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PlaceWrapperV2 fromContext(Place place) {
            AddressWrapperV2 addressWrapperV2;
            j.e(place, "place");
            String id = place.getId();
            String name = place.getName();
            int type = place.getType().getType();
            Address address = place.getAddress();
            if (address != null) {
                addressWrapperV2 = j.e(address, "<this>");
            } else {
                addressWrapperV2 = null;
            }
            return new PlaceWrapperV2(id, name, type, addressWrapperV2, place.getLatitude(), place.getLongitude(), place.getRadius());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PlaceWrapperV2> {
        public final PlaceWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            Double d = null;
            AddressWrapperV2 createFromParcel = parcel.readInt() == 0 ? null : AddressWrapperV2.CREATOR.createFromParcel(parcel);
            Double valueOf = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            Double valueOf2 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            if (parcel.readInt() != 0) {
                d = Double.valueOf(parcel.readDouble());
            }
            return new PlaceWrapperV2(readString, readString2, readInt, createFromParcel, valueOf, valueOf2, d);
        }

        public final PlaceWrapperV2[] newArray(int i2) {
            return new PlaceWrapperV2[i2];
        }
    }

    public PlaceWrapperV2(String str, String str2, int i2, AddressWrapperV2 addressWrapperV2, Double d, Double d2, Double d3) {
        j.e(str, "id");
        this.id = str;
        this.name = str2;
        this.type = i2;
        this.addressWrapper = addressWrapperV2;
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
    }

    public final int describeContents() {
        return 0;
    }

    public final AddressWrapperV2 getAddressWrapper() {
        return this.addressWrapper;
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

    public final Double getRadius() {
        return this.radius;
    }

    public final int getType() {
        return this.type;
    }

    public final /* synthetic */ Place toContext() {
        Address address;
        String str = this.id;
        String str2 = this.name;
        PlaceType fromInt = PlaceType.Companion.fromInt(this.type);
        AddressWrapperV2 addressWrapperV2 = this.addressWrapper;
        if (addressWrapperV2 != null) {
            address = addressWrapperV2.toContext();
        } else {
            address = null;
        }
        return new Place(str, str2, fromInt, address, this.latitude, this.longitude, this.radius, (List) null, (List) null, 128, (e) null);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeInt(this.type);
        AddressWrapperV2 addressWrapperV2 = this.addressWrapper;
        if (addressWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            addressWrapperV2.writeToParcel(parcel, i2);
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
    }
}
