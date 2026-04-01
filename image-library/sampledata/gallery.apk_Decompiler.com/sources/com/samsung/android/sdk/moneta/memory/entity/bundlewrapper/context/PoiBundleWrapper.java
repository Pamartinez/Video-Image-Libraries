package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Poi;
import com.samsung.android.sdk.moneta.memory.entity.context.PoiCategory;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PoiBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "toContext", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PoiBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_LATITUDE = "latitude";
    public static final String BUNDLE_KEY_LONGITUDE = "longitude";
    public static final String BUNDLE_KEY_POI_CATEGORY = "poiCategory";
    public static final String BUNDLE_KEY_POI_CITY = "poiCity";
    public static final String BUNDLE_KEY_POI_NAME = "poiName";
    public static final Parcelable.Creator<PoiBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PoiBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_LATITUDE", "", "BUNDLE_KEY_LONGITUDE", "BUNDLE_KEY_POI_CITY", "BUNDLE_KEY_POI_NAME", "BUNDLE_KEY_POI_CATEGORY", "fromContext", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/context/PoiBundleWrapper;", "address", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PoiBundleWrapper fromContext(Poi poi) {
            j.e(poi, "address");
            Bundle bundle = new Bundle();
            bundle.putDouble("latitude", poi.getLatitude());
            bundle.putDouble("longitude", poi.getLongitude());
            bundle.putString(PoiBundleWrapper.BUNDLE_KEY_POI_CITY, poi.getPoiCity());
            bundle.putString(PoiBundleWrapper.BUNDLE_KEY_POI_NAME, poi.getPoiName());
            List<PoiCategory> poiCategory = poi.getPoiCategory();
            if (poiCategory != null) {
                ArrayList arrayList = new ArrayList();
                C1194l.i1(poiCategory, arrayList);
                bundle.putParcelableArrayList(PoiBundleWrapper.BUNDLE_KEY_POI_CATEGORY, arrayList);
            }
            return new PoiBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PoiBundleWrapper> {
        public final PoiBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new PoiBundleWrapper(parcel.readBundle(PoiBundleWrapper.class.getClassLoader()));
        }

        public final PoiBundleWrapper[] newArray(int i2) {
            return new PoiBundleWrapper[i2];
        }
    }

    public PoiBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ Poi toContext() {
        double d = this.properties.getDouble("latitude");
        double d2 = this.properties.getDouble("longitude");
        String string = this.properties.getString(BUNDLE_KEY_POI_CITY, "");
        j.d(string, "getString(...)");
        String string2 = this.properties.getString(BUNDLE_KEY_POI_NAME, "");
        j.d(string2, "getString(...)");
        return new Poi(d, d2, string, string2, this.properties.getParcelableArrayList(BUNDLE_KEY_POI_CATEGORY, PoiCategory.class));
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
