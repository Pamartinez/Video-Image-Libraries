package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/EngramBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "toEngram", "()Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramBundleWrapper extends EngramWrapper {
    public static final String BUNDLE_KEY_ACTIVITIES = "activities";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_ID = "id";
    public static final String BUNDLE_KEY_IS_COMPLETE = "isComplete";
    public static final String BUNDLE_KEY_SPECIAL_MOMENTS = "specialMoments";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<EngramBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/EngramBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_ID", "", "BUNDLE_KEY_ACTIVITIES", "BUNDLE_KEY_START_TIMESTAMP", "BUNDLE_KEY_END_TIMESTAMP", "BUNDLE_KEY_SPECIAL_MOMENTS", "BUNDLE_KEY_IS_COMPLETE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramBundleWrapper> {
        public final EngramBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramBundleWrapper(parcel.readBundle(EngramBundleWrapper.class.getClassLoader()));
        }

        public final EngramBundleWrapper[] newArray(int i2) {
            return new EngramBundleWrapper[i2];
        }
    }

    public EngramBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public /* synthetic */ Engram toEngram() {
        ArrayList arrayList;
        List list;
        ArrayList<ActivityWrapper> e = this.properties.getParcelableArrayList(BUNDLE_KEY_ACTIVITIES, ActivityWrapper.class);
        List list2 = C1202t.d;
        if (e != null) {
            ArrayList arrayList2 = new ArrayList(C1196n.w0(e, 10));
            for (ActivityWrapper activity : e) {
                arrayList2.add(activity.toActivity());
            }
            arrayList = arrayList2;
        } else {
            arrayList = list2;
        }
        List stringArrayList = this.properties.getStringArrayList(BUNDLE_KEY_SPECIAL_MOMENTS);
        if (stringArrayList != null) {
            list = stringArrayList;
        } else {
            list = list2;
        }
        String string = this.properties.getString("id", "");
        j.d(string, "getString(...)");
        return new Engram(string, arrayList, this.properties.getLong("startTimestamp", 0), this.properties.getLong("endTimestamp", 0), list, this.properties.getBoolean("isComplete", true));
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
