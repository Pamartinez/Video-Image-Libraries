package com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.entity.context.PlaceType;
import com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption;
import com.samsung.android.sdk.moneta.memory.option.PlaceQueryType;
import com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/PlaceQueryOptionBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaceQueryOptionBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_ENGRAM_ID = "engramId";
    public static final String BUNDLE_KEY_LIMIT = "limit";
    public static final String BUNDLE_KEY_OFFSET = "offset";
    public static final String BUNDLE_KEY_PLACE_TYPES = "placeTypes";
    public static final String BUNDLE_KEY_QUERY_TYPE = "queryType";
    public static final Parcelable.Creator<PlaceQueryOptionBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/PlaceQueryOptionBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_ENGRAM_ID", "", "BUNDLE_KEY_LIMIT", "BUNDLE_KEY_OFFSET", "BUNDLE_KEY_QUERY_TYPE", "BUNDLE_KEY_PLACE_TYPES", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/PlaceQueryOptionBundleWrapper;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PlaceQueryOptionBundleWrapper fromOption(PlaceQueryOption placeQueryOption) {
            j.e(placeQueryOption, GPPInterfaceKey.JSON_KEY_OPTION);
            Bundle bundle = new Bundle();
            String engramId = placeQueryOption.getEngramId();
            if (engramId != null) {
                bundle.putString("engramId", engramId);
            }
            bundle.putInt("limit", placeQueryOption.getLimit());
            bundle.putInt("offset", placeQueryOption.getOffset());
            bundle.putInt("queryType", placeQueryOption.getQueryType().getValue());
            List<PlaceType> placeTypes = placeQueryOption.getPlaceTypes();
            if (placeTypes != null) {
                Iterable<PlaceType> iterable = placeTypes;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (PlaceType type : iterable) {
                    arrayList.add(Integer.valueOf(type.getType()));
                }
                ArrayList arrayList2 = new ArrayList();
                C1194l.i1(arrayList, arrayList2);
                bundle.putIntegerArrayList(PlaceQueryOptionBundleWrapper.BUNDLE_KEY_PLACE_TYPES, arrayList2);
            }
            return new PlaceQueryOptionBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PlaceQueryOptionBundleWrapper> {
        public final PlaceQueryOptionBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new PlaceQueryOptionBundleWrapper(parcel.readBundle(PlaceQueryOptionBundleWrapper.class.getClassLoader()));
        }

        public final PlaceQueryOptionBundleWrapper[] newArray(int i2) {
            return new PlaceQueryOptionBundleWrapper[i2];
        }
    }

    public PlaceQueryOptionBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ PlaceQueryOption toOption() {
        PlaceQueryType placeQueryType;
        ArrayList<Integer> integerArrayList;
        String string = this.properties.getString("engramId");
        int i2 = this.properties.getInt("limit", 100);
        int i7 = this.properties.getInt("offset", 0);
        Integer intOrNull = BundleGetExtensionKt.getIntOrNull(this.properties, "queryType");
        if (intOrNull == null || (placeQueryType = PlaceQueryType.Companion.fromInt(intOrNull.intValue())) == null) {
            placeQueryType = PlaceQueryType.BY_ENGRAM_ID;
        }
        PlaceQueryType placeQueryType2 = placeQueryType;
        ArrayList arrayList = null;
        if (this.properties.containsKey(BUNDLE_KEY_PLACE_TYPES) && (integerArrayList = this.properties.getIntegerArrayList(BUNDLE_KEY_PLACE_TYPES)) != null) {
            arrayList = new ArrayList(C1196n.w0(integerArrayList, 10));
            for (Integer num : integerArrayList) {
                PlaceType.Companion companion = PlaceType.Companion;
                j.b(num);
                arrayList.add(companion.fromInt(num.intValue()));
            }
        }
        return new PlaceQueryOption.WrapBuilder(string, i2, i7, placeQueryType2, arrayList).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
