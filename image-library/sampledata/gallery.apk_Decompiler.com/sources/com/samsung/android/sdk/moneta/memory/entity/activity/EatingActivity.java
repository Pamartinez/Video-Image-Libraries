package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.EatingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b!\u0010\"J\\\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b%\u0010\u0019J\u0010\u0010&\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b&\u0010\u0017J\u001a\u0010*\u001a\u00020)2\b\u0010(\u001a\u0004\u0018\u00010'HÖ\u0003¢\u0006\u0004\b*\u0010+R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010\u0019R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010.\u001a\u0004\b/\u0010\u001bR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010.\u001a\u0004\b0\u0010\u001bR\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u00101\u001a\u0004\b2\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00103\u001a\u0004\b4\u0010 R\u0019\u0010\f\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u00105\u001a\u0004\b6\u0010\"¨\u00067"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "foods", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;JLjava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "component5", "()J", "component6", "()Ljava/lang/Long;", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;JLjava/lang/Long;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "getFoods", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EatingActivity extends Activity {
    public static final Parcelable.Creator<EatingActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final Long endTimestamp;
    private final List<String> foods;
    private final String id;
    private final Place location;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EatingActivity> {
        public final EatingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(EatingActivity.class, parcel, arrayList, i2, 1);
            }
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            Long l = null;
            Place createFromParcel = parcel.readInt() == 0 ? null : Place.CREATOR.createFromParcel(parcel);
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new EatingActivity(readString, arrayList, createStringArrayList, createFromParcel, readLong, l);
        }

        public final EatingActivity[] newArray(int i2) {
            return new EatingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EatingActivity(String str, List<? extends Content> list, List<String> list2, Place place, long j2, Long l) {
        super(ActivityType.Eating);
        j.e(str, "id");
        j.e(list, "contents");
        j.e(list2, EatingActivityBundleWrapper.BUNDLE_KEY_FOODS);
        this.id = str;
        this.contents = list;
        this.foods = list2;
        this.location = place;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public static /* synthetic */ EatingActivity copy$default(EatingActivity eatingActivity, String str, List<Content> list, List<String> list2, Place place, long j2, Long l, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = eatingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = eatingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            list2 = eatingActivity.foods;
        }
        if ((i2 & 8) != 0) {
            place = eatingActivity.location;
        }
        if ((i2 & 16) != 0) {
            j2 = eatingActivity.startTimestamp;
        }
        if ((i2 & 32) != 0) {
            l = eatingActivity.endTimestamp;
        }
        Long l8 = l;
        long j3 = j2;
        Place place2 = place;
        List<Content> list3 = list;
        return eatingActivity.copy(str, list3, list2, place2, j3, l8);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final List<String> component3() {
        return this.foods;
    }

    public final Place component4() {
        return this.location;
    }

    public final long component5() {
        return this.startTimestamp;
    }

    public final Long component6() {
        return this.endTimestamp;
    }

    public final EatingActivity copy(String str, List<? extends Content> list, List<String> list2, Place place, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contents");
        j.e(list2, EatingActivityBundleWrapper.BUNDLE_KEY_FOODS);
        return new EatingActivity(str, list, list2, place, j2, l);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EatingActivity)) {
            return false;
        }
        EatingActivity eatingActivity = (EatingActivity) obj;
        if (j.a(this.id, eatingActivity.id) && j.a(this.contents, eatingActivity.contents) && j.a(this.foods, eatingActivity.foods) && j.a(this.location, eatingActivity.location) && this.startTimestamp == eatingActivity.startTimestamp && j.a(this.endTimestamp, eatingActivity.endTimestamp)) {
            return true;
        }
        return false;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final List<String> getFoods() {
        return this.foods;
    }

    public String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int f = C0212a.f(this.foods, C0212a.f(this.contents, this.id.hashCode() * 31, 31), 31);
        Place place = this.location;
        int i7 = 0;
        if (place == null) {
            i2 = 0;
        } else {
            i2 = place.hashCode();
        }
        int c5 = C0212a.c((f + i2) * 31, 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l != null) {
            i7 = l.hashCode();
        }
        return c5 + i7;
    }

    public String toString() {
        return "EatingActivity(id=" + this.id + ", contents=" + this.contents + ", foods=" + this.foods + ", location=" + this.location + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeStringList(this.foods);
        Place place = this.location;
        if (place == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            place.writeToParcel(parcel, i2);
        }
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
