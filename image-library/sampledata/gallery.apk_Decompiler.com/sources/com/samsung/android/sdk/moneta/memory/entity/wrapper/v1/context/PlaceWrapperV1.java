package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context;

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

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*BI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b(\u0010'R\u0019\u0010\f\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b)\u0010'¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/PlaceWrapperV1;", "Landroid/os/Parcelable;", "", "id", "name", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "type", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "address", "", "latitude", "longitude", "radius", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getName", "Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "getType", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/PlaceType;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "getAddress", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Address;", "Ljava/lang/Double;", "getLatitude", "()Ljava/lang/Double;", "getLongitude", "getRadius", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<PlaceWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Address address;
    private final String id;
    private final Double latitude;
    private final Double longitude;
    private final String name;
    private final Double radius;
    private final PlaceType type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/PlaceWrapperV1$Companion;", "", "<init>", "()V", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/PlaceWrapperV1;", "place", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PlaceWrapperV1 fromContext(Place place) {
            j.e(place, "place");
            return new PlaceWrapperV1(place.getId(), place.getName(), place.getType(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getRadius());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PlaceWrapperV1> {
        public final PlaceWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            PlaceType valueOf = PlaceType.valueOf(parcel.readString());
            Double d = null;
            Address createFromParcel = parcel.readInt() == 0 ? null : Address.CREATOR.createFromParcel(parcel);
            Double valueOf2 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            Double valueOf3 = parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble());
            if (parcel.readInt() != 0) {
                d = Double.valueOf(parcel.readDouble());
            }
            return new PlaceWrapperV1(readString, readString2, valueOf, createFromParcel, valueOf2, valueOf3, d);
        }

        public final PlaceWrapperV1[] newArray(int i2) {
            return new PlaceWrapperV1[i2];
        }
    }

    public PlaceWrapperV1(String str, String str2, PlaceType placeType, Address address2, Double d, Double d2, Double d3) {
        j.e(str, "id");
        j.e(placeType, "type");
        this.id = str;
        this.name = str2;
        this.type = placeType;
        this.address = address2;
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
    }

    public final int describeContents() {
        return 0;
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

    public final Double getRadius() {
        return this.radius;
    }

    public final PlaceType getType() {
        return this.type;
    }

    public final /* synthetic */ Place toContext() {
        return new Place(this.id, this.name, this.type, this.address, this.latitude, this.longitude, this.radius, (List) null, (List) null, 128, (e) null);
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
    }
}
