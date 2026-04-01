package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Address;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.context.PlaceType;
import com.samsung.android.sdk.moneta.memory.entity.context.Poi;
import com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PlaceBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_ADDRESS = "address";
    public static final String BUNDLE_KEY_ID = "id";
    public static final String BUNDLE_KEY_LATITUDE = "latitude";
    public static final String BUNDLE_KEY_LONGITUDE = "longitude";
    public static final String BUNDLE_KEY_NAME = "name";
    public static final String BUNDLE_KEY_NAMES = "names";
    public static final String BUNDLE_KEY_POI = "poi";
    public static final String BUNDLE_KEY_RADIUS = "radius";
    public static final String BUNDLE_KEY_TYPE = "type";
    public static final Parcelable.Creator<PlaceBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PlaceBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_ID", "", "BUNDLE_KEY_NAME", "BUNDLE_KEY_TYPE", "BUNDLE_KEY_ADDRESS", "BUNDLE_KEY_LATITUDE", "BUNDLE_KEY_LONGITUDE", "BUNDLE_KEY_RADIUS", "BUNDLE_KEY_NAMES", "BUNDLE_KEY_POI", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PlaceBundleWrapper;", "place", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getUnknownContent", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PlaceBundleWrapper fromContext(Place place) {
            j.e(place, "place");
            Bundle bundle = new Bundle();
            bundle.putString("id", place.getId());
            String name = place.getName();
            if (name != null) {
                bundle.putString("name", name);
            }
            bundle.putInt("type", place.getType().getType());
            Address address = place.getAddress();
            if (address != null) {
                bundle.putParcelable("address", AddressBundleWrapper.Companion.fromContext(address));
            }
            Double latitude = place.getLatitude();
            if (latitude != null) {
                bundle.putDouble("latitude", latitude.doubleValue());
            }
            Double longitude = place.getLongitude();
            if (longitude != null) {
                bundle.putDouble("longitude", longitude.doubleValue());
            }
            Double radius = place.getRadius();
            if (radius != null) {
                bundle.putDouble("radius", radius.doubleValue());
            }
            ArrayList arrayList = new ArrayList();
            C1194l.i1(place.getNames(), arrayList);
            bundle.putStringArrayList("names", arrayList);
            List<Poi> poi = place.getPoi();
            if (poi != null) {
                ArrayList arrayList2 = new ArrayList();
                C1194l.i1(poi, arrayList2);
                bundle.putParcelableArrayList("poi", arrayList2);
            }
            return new PlaceBundleWrapper(bundle);
        }

        public final /* synthetic */ Place getUnknownContent() {
            return new Place("", (String) null, PlaceType.Other, (Address) null, (Double) null, (Double) null, (Double) null, C1202t.d, (List<Poi>) null);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PlaceBundleWrapper> {
        public final PlaceBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new PlaceBundleWrapper(parcel.readBundle(PlaceBundleWrapper.class.getClassLoader()));
        }

        public final PlaceBundleWrapper[] newArray(int i2) {
            return new PlaceBundleWrapper[i2];
        }
    }

    public PlaceBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ Place toContext() {
        Address address;
        String string = this.properties.getString("id", "");
        j.d(string, "getString(...)");
        String string2 = this.properties.getString("name");
        PlaceType fromInt = PlaceType.Companion.fromInt(this.properties.getInt("type"));
        AddressBundleWrapper addressBundleWrapper = (AddressBundleWrapper) this.properties.getParcelable("address", AddressBundleWrapper.class);
        ArrayList arrayList = null;
        if (addressBundleWrapper != null) {
            address = addressBundleWrapper.toContext();
        } else {
            address = null;
        }
        Double doubleOrNull = BundleGetExtensionKt.getDoubleOrNull(this.properties, "latitude");
        Double doubleOrNull2 = BundleGetExtensionKt.getDoubleOrNull(this.properties, "longitude");
        Double doubleOrNull3 = BundleGetExtensionKt.getDoubleOrNull(this.properties, "radius");
        List stringArrayList = this.properties.getStringArrayList("names");
        if (stringArrayList == null) {
            stringArrayList = C1202t.d;
        }
        List list = stringArrayList;
        ArrayList<PoiBundleWrapper> y = this.properties.getParcelableArrayList("poi", PoiBundleWrapper.class);
        if (y != null) {
            arrayList = new ArrayList(C1196n.w0(y, 10));
            for (PoiBundleWrapper context : y) {
                arrayList.add(context.toContext());
            }
        }
        return new Place(string, string2, fromInt, address, doubleOrNull, doubleOrNull2, doubleOrNull3, list, arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
