package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.EatingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ExercisingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ListeningToMusicActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.MovingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.PayingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ScheduledEventBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ScheduledTravelBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.SearchingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.SleepingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.SpeakingOnPhoneActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.StayingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.TakingPicturesActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.UnknownActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.WatchingVideoActivityBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J.\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0016J\u0010\u0010\u001d\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0014J\u001a\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010#\u001a\u0004\b%\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b'\u0010\u0019¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/ActivityBundleWrapper;", "Landroid/os/Parcelable;", "", "activityType", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/ActivityBundleWrapper;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getActivityType", "getId", "Landroid/os/Bundle;", "getProperties", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActivityBundleWrapper implements Parcelable {
    public static final Parcelable.Creator<ActivityBundleWrapper> CREATOR = new Creator();
    private final String activityType;
    private final String id;
    private final Bundle properties;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ActivityBundleWrapper> {
        public final ActivityBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ActivityBundleWrapper(parcel.readString(), parcel.readString(), parcel.readBundle(ActivityBundleWrapper.class.getClassLoader()));
        }

        public final ActivityBundleWrapper[] newArray(int i2) {
            return new ActivityBundleWrapper[i2];
        }
    }

    public ActivityBundleWrapper(String str, String str2, Bundle bundle) {
        j.e(str, "activityType");
        j.e(str2, "id");
        j.e(bundle, "properties");
        this.activityType = str;
        this.id = str2;
        this.properties = bundle;
    }

    public static /* synthetic */ ActivityBundleWrapper copy$default(ActivityBundleWrapper activityBundleWrapper, String str, String str2, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = activityBundleWrapper.activityType;
        }
        if ((i2 & 2) != 0) {
            str2 = activityBundleWrapper.id;
        }
        if ((i2 & 4) != 0) {
            bundle = activityBundleWrapper.properties;
        }
        return activityBundleWrapper.copy(str, str2, bundle);
    }

    public final String component1() {
        return this.activityType;
    }

    public final String component2() {
        return this.id;
    }

    public final Bundle component3() {
        return this.properties;
    }

    public final ActivityBundleWrapper copy(String str, String str2, Bundle bundle) {
        j.e(str, "activityType");
        j.e(str2, "id");
        j.e(bundle, "properties");
        return new ActivityBundleWrapper(str, str2, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityBundleWrapper)) {
            return false;
        }
        ActivityBundleWrapper activityBundleWrapper = (ActivityBundleWrapper) obj;
        if (j.a(this.activityType, activityBundleWrapper.activityType) && j.a(this.id, activityBundleWrapper.id) && j.a(this.properties, activityBundleWrapper.properties)) {
            return true;
        }
        return false;
    }

    public final String getActivityType() {
        return this.activityType;
    }

    public final String getId() {
        return this.id;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public int hashCode() {
        return this.properties.hashCode() + C0212a.d(this.activityType.hashCode() * 31, 31, this.id);
    }

    public final Activity toActivity() {
        String str = this.activityType;
        switch (str.hashCode()) {
            case -1984392082:
                if (str.equals("Moving")) {
                    return new MovingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -1911344550:
                if (str.equals("Paying")) {
                    return new PayingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -1859525451:
                if (str.equals("Exercising")) {
                    return new ExercisingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -1321219637:
                if (str.equals("Sleeping")) {
                    return new SleepingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -512782054:
                if (str.equals("Searching")) {
                    return new SearchingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -429067763:
                if (str.equals("ScheduledEvent")) {
                    return new ScheduledEventBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -232333623:
                if (str.equals("Staying")) {
                    return new StayingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case -113415745:
                if (str.equals("SpeakingOnPhone")) {
                    return new SpeakingOnPhoneActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case 9432583:
                if (str.equals("ScheduledTravel")) {
                    return new ScheduledTravelBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case 799334248:
                if (str.equals("WatchingVideo")) {
                    return new WatchingVideoActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case 808552271:
                if (str.equals("ListeningToMusic")) {
                    return new ListeningToMusicActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case 1021704953:
                if (str.equals("TakingPictures")) {
                    return new TakingPicturesActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
            case 2068553130:
                if (str.equals("Eating")) {
                    return new EatingActivityBundleWrapper(this.id, this.properties).toActivityV4();
                }
                break;
        }
        return new UnknownActivityBundleWrapper(this.id, this.properties).toActivityV4();
    }

    public String toString() {
        return "ActivityBundleWrapper(activityType=" + this.activityType + ", id=" + this.id + ", properties=" + this.properties + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.activityType);
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
