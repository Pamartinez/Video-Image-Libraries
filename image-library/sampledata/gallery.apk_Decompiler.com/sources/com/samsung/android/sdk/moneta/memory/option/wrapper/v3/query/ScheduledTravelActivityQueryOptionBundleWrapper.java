package com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.option.ActivityQueryType;
import com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption;
import com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/ScheduledTravelActivityQueryOptionBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledTravelActivityQueryOptionBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_CONTENT_FILL = "contentFill";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_ID = "id";
    public static final String BUNDLE_KEY_IS_REALIZED = "isRealized";
    public static final String BUNDLE_KEY_LIMIT = "limit";
    public static final String BUNDLE_KEY_OFFSET = "offset";
    public static final String BUNDLE_KEY_QUERY_TYPE = "queryType";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<ScheduledTravelActivityQueryOptionBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/ScheduledTravelActivityQueryOptionBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_START_TIMESTAMP", "", "BUNDLE_KEY_END_TIMESTAMP", "BUNDLE_KEY_QUERY_TYPE", "BUNDLE_KEY_ID", "BUNDLE_KEY_LIMIT", "BUNDLE_KEY_OFFSET", "BUNDLE_KEY_CONTENT_FILL", "BUNDLE_KEY_IS_REALIZED", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/query/ScheduledTravelActivityQueryOptionBundleWrapper;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ScheduledTravelActivityQueryOptionBundleWrapper fromOption(ScheduledTravelActivityQueryOption scheduledTravelActivityQueryOption) {
            j.e(scheduledTravelActivityQueryOption, GPPInterfaceKey.JSON_KEY_OPTION);
            Bundle bundle = new Bundle();
            Long startTimestamp = scheduledTravelActivityQueryOption.getStartTimestamp();
            if (startTimestamp != null) {
                bundle.putLong("startTimestamp", startTimestamp.longValue());
            }
            Long endTimestamp = scheduledTravelActivityQueryOption.getEndTimestamp();
            if (endTimestamp != null) {
                bundle.putLong("endTimestamp", endTimestamp.longValue());
            }
            bundle.putInt("queryType", scheduledTravelActivityQueryOption.getQueryType().getValue());
            String id = scheduledTravelActivityQueryOption.getId();
            if (id != null) {
                bundle.putString("id", id);
            }
            bundle.putInt("limit", scheduledTravelActivityQueryOption.getLimit());
            bundle.putInt("offset", scheduledTravelActivityQueryOption.getOffset());
            bundle.putBoolean("contentFill", scheduledTravelActivityQueryOption.getContentFill());
            bundle.putBoolean(ScheduledTravelActivityQueryOptionBundleWrapper.BUNDLE_KEY_IS_REALIZED, scheduledTravelActivityQueryOption.isRealized());
            return new ScheduledTravelActivityQueryOptionBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ScheduledTravelActivityQueryOptionBundleWrapper> {
        public final ScheduledTravelActivityQueryOptionBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ScheduledTravelActivityQueryOptionBundleWrapper(parcel.readBundle(ScheduledTravelActivityQueryOptionBundleWrapper.class.getClassLoader()));
        }

        public final ScheduledTravelActivityQueryOptionBundleWrapper[] newArray(int i2) {
            return new ScheduledTravelActivityQueryOptionBundleWrapper[i2];
        }
    }

    public ScheduledTravelActivityQueryOptionBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ ScheduledTravelActivityQueryOption toOption() {
        ActivityQueryType activityQueryType;
        Long longOrNull = BundleGetExtensionKt.getLongOrNull(this.properties, "startTimestamp");
        Long longOrNull2 = BundleGetExtensionKt.getLongOrNull(this.properties, "endTimestamp");
        Integer intOrNull = BundleGetExtensionKt.getIntOrNull(this.properties, "queryType");
        if (intOrNull == null || (activityQueryType = ActivityQueryType.Companion.fromInt(intOrNull.intValue())) == null) {
            activityQueryType = ActivityQueryType.BETWEEN_TIMESTAMP;
        }
        return new ScheduledTravelActivityQueryOption.WrapBuilder(longOrNull, longOrNull2, activityQueryType, this.properties.getString("id"), this.properties.getInt("limit", 20), this.properties.getInt("offset", 0), this.properties.getBoolean("contentFill", true), this.properties.getBoolean(BUNDLE_KEY_IS_REALIZED, false)).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
