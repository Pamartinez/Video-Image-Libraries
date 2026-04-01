package com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryOption;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryType;
import com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/EngramQueryOptionBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramQueryOptionBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_CONTENT_FILL = "contentFill";
    public static final String BUNDLE_KEY_CONTENT_ID = "contentId";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_ENGRAM_ID = "engramId";
    public static final String BUNDLE_KEY_EXCLUDE_ACTIVITY_TYPES = "excludeActivityTypes";
    public static final String BUNDLE_KEY_EXCLUDE_TAGS = "excludeTags";
    public static final String BUNDLE_KEY_INCLUDE_ACTIVITY_TYPES = "includeActivityTypes";
    public static final String BUNDLE_KEY_INCLUDE_TAGS = "includeTags";
    public static final String BUNDLE_KEY_LATITUDE = "latitude";
    public static final String BUNDLE_KEY_LIMIT = "limit";
    public static final String BUNDLE_KEY_LONGITUDE = "longitude";
    public static final String BUNDLE_KEY_OFFSET = "offset";
    public static final String BUNDLE_KEY_QUERY_TYPE = "queryType";
    public static final String BUNDLE_KEY_RADIUS = "radius";
    public static final String BUNDLE_KEY_RESOLUTION = "resolution";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<EngramQueryOptionBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/EngramQueryOptionBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_START_TIMESTAMP", "", "BUNDLE_KEY_END_TIMESTAMP", "BUNDLE_KEY_CONTENT_ID", "BUNDLE_KEY_QUERY_TYPE", "BUNDLE_KEY_INCLUDE_TAGS", "BUNDLE_KEY_EXCLUDE_TAGS", "BUNDLE_KEY_INCLUDE_ACTIVITY_TYPES", "BUNDLE_KEY_EXCLUDE_ACTIVITY_TYPES", "BUNDLE_KEY_LIMIT", "BUNDLE_KEY_OFFSET", "BUNDLE_KEY_ENGRAM_ID", "BUNDLE_KEY_LATITUDE", "BUNDLE_KEY_LONGITUDE", "BUNDLE_KEY_RADIUS", "BUNDLE_KEY_CONTENT_FILL", "BUNDLE_KEY_RESOLUTION", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/EngramQueryOptionBundleWrapper;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramQueryOptionBundleWrapper fromOption(EngramQueryOption engramQueryOption) {
            j.e(engramQueryOption, GPPInterfaceKey.JSON_KEY_OPTION);
            Bundle bundle = new Bundle();
            Long startTimestamp = engramQueryOption.getStartTimestamp();
            if (startTimestamp != null) {
                bundle.putLong("startTimestamp", startTimestamp.longValue());
            }
            Long endTimestamp = engramQueryOption.getEndTimestamp();
            if (endTimestamp != null) {
                bundle.putLong("endTimestamp", endTimestamp.longValue());
            }
            String contentId = engramQueryOption.getContentId();
            if (contentId != null) {
                bundle.putString(EngramQueryOptionBundleWrapper.BUNDLE_KEY_CONTENT_ID, contentId);
            }
            bundle.putInt("queryType", engramQueryOption.getQueryType().getValue());
            List<String> includeTags = engramQueryOption.getIncludeTags();
            if (includeTags != null) {
                bundle.putStringArrayList(EngramQueryOptionBundleWrapper.BUNDLE_KEY_INCLUDE_TAGS, new ArrayList(includeTags));
            }
            List<String> excludeTags = engramQueryOption.getExcludeTags();
            if (excludeTags != null) {
                bundle.putStringArrayList(EngramQueryOptionBundleWrapper.BUNDLE_KEY_EXCLUDE_TAGS, new ArrayList(excludeTags));
            }
            List<ActivityType> includeActivityTypes = engramQueryOption.getIncludeActivityTypes();
            if (includeActivityTypes != null) {
                Iterable<ActivityType> iterable = includeActivityTypes;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (ActivityType value : iterable) {
                    arrayList.add(Integer.valueOf(value.getValue()));
                }
                bundle.putIntegerArrayList(EngramQueryOptionBundleWrapper.BUNDLE_KEY_INCLUDE_ACTIVITY_TYPES, new ArrayList(arrayList));
            }
            List<ActivityType> excludeActivityTypes = engramQueryOption.getExcludeActivityTypes();
            if (excludeActivityTypes != null) {
                Iterable<ActivityType> iterable2 = excludeActivityTypes;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                for (ActivityType value2 : iterable2) {
                    arrayList2.add(Integer.valueOf(value2.getValue()));
                }
                bundle.putIntegerArrayList(EngramQueryOptionBundleWrapper.BUNDLE_KEY_EXCLUDE_ACTIVITY_TYPES, new ArrayList(arrayList2));
            }
            bundle.putInt("limit", engramQueryOption.getLimit());
            bundle.putInt("offset", engramQueryOption.getOffset());
            String engramId = engramQueryOption.getEngramId();
            if (engramId != null) {
                bundle.putString("engramId", engramId);
            }
            Double latitude = engramQueryOption.getLatitude();
            if (latitude != null) {
                bundle.putDouble("latitude", latitude.doubleValue());
            }
            Double longitude = engramQueryOption.getLongitude();
            if (longitude != null) {
                bundle.putDouble("longitude", longitude.doubleValue());
            }
            Double radius = engramQueryOption.getRadius();
            if (radius != null) {
                bundle.putDouble("radius", radius.doubleValue());
            }
            bundle.putBoolean("contentFill", engramQueryOption.getContentFill());
            Integer resolution = engramQueryOption.getResolution();
            if (resolution != null) {
                bundle.putInt(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, resolution.intValue());
            }
            return new EngramQueryOptionBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramQueryOptionBundleWrapper> {
        public final EngramQueryOptionBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramQueryOptionBundleWrapper(parcel.readBundle(EngramQueryOptionBundleWrapper.class.getClassLoader()));
        }

        public final EngramQueryOptionBundleWrapper[] newArray(int i2) {
            return new EngramQueryOptionBundleWrapper[i2];
        }
    }

    public EngramQueryOptionBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ EngramQueryOption toOption() {
        EngramQueryType engramQueryType;
        ArrayList arrayList;
        Long longOrNull = BundleGetExtensionKt.getLongOrNull(this.properties, "startTimestamp");
        Long longOrNull2 = BundleGetExtensionKt.getLongOrNull(this.properties, "endTimestamp");
        String string = this.properties.getString(BUNDLE_KEY_CONTENT_ID);
        Integer intOrNull = BundleGetExtensionKt.getIntOrNull(this.properties, "queryType");
        if (intOrNull == null || (engramQueryType = EngramQueryType.Companion.fromInt(intOrNull.intValue())) == null) {
            engramQueryType = EngramQueryType.BETWEEN_TIMESTAMP;
        }
        EngramQueryType engramQueryType2 = engramQueryType;
        ArrayList<String> stringArrayList = this.properties.getStringArrayList(BUNDLE_KEY_INCLUDE_TAGS);
        ArrayList<String> stringArrayList2 = this.properties.getStringArrayList(BUNDLE_KEY_EXCLUDE_TAGS);
        ArrayList<Integer> integerArrayList = this.properties.getIntegerArrayList(BUNDLE_KEY_INCLUDE_ACTIVITY_TYPES);
        ArrayList arrayList2 = null;
        if (integerArrayList != null) {
            arrayList = new ArrayList(C1196n.w0(integerArrayList, 10));
            for (Integer num : integerArrayList) {
                ActivityType.Companion companion = ActivityType.Companion;
                j.b(num);
                arrayList.add(companion.fromInt(num.intValue()));
            }
        } else {
            arrayList = null;
        }
        ArrayList<Integer> integerArrayList2 = this.properties.getIntegerArrayList(BUNDLE_KEY_EXCLUDE_ACTIVITY_TYPES);
        if (integerArrayList2 != null) {
            arrayList2 = new ArrayList(C1196n.w0(integerArrayList2, 10));
            for (Integer num2 : integerArrayList2) {
                ActivityType.Companion companion2 = ActivityType.Companion;
                j.b(num2);
                arrayList2.add(companion2.fromInt(num2.intValue()));
            }
        }
        return new EngramQueryOption.WrapBuilder(longOrNull, longOrNull2, string, engramQueryType2, stringArrayList, stringArrayList2, arrayList, arrayList2, this.properties.getInt("limit", 20), this.properties.getInt("offset", 0), this.properties.getString("engramId"), BundleGetExtensionKt.getDoubleOrNull(this.properties, "latitude"), BundleGetExtensionKt.getDoubleOrNull(this.properties, "longitude"), BundleGetExtensionKt.getDoubleOrNull(this.properties, "radius"), this.properties.getBoolean("contentFill", false), Integer.valueOf(this.properties.getInt(BUNDLE_KEY_RESOLUTION, 6))).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
