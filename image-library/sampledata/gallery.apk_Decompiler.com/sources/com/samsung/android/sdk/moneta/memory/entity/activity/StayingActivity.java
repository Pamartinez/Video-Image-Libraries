package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u001f\u0010 JJ\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b#\u0010\u0018J\u0010\u0010$\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b$\u0010\u0016J\u001a\u0010(\u001a\u00020'2\b\u0010&\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010\u0018R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b-\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010.\u001a\u0004\b/\u0010\u001cR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00100\u001a\u0004\b1\u0010\u001eR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u00102\u001a\u0004\b3\u0010 ¨\u00064"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/StayingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;JLjava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "component4", "()J", "component5", "()Ljava/lang/Long;", "copy", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;JLjava/lang/Long;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/StayingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StayingActivity extends Activity {
    public static final Parcelable.Creator<StayingActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final Long endTimestamp;
    private final String id;
    private final Place location;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<StayingActivity> {
        public final StayingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(StayingActivity.class, parcel, arrayList, i2, 1);
            }
            return new StayingActivity(readString, arrayList, Place.CREATOR.createFromParcel(parcel), parcel.readLong(), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()));
        }

        public final StayingActivity[] newArray(int i2) {
            return new StayingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StayingActivity(String str, List<? extends Content> list, Place place, long j2, Long l) {
        super(ActivityType.Staying);
        j.e(str, "id");
        j.e(list, "contents");
        j.e(place, "location");
        this.id = str;
        this.contents = list;
        this.location = place;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public static /* synthetic */ StayingActivity copy$default(StayingActivity stayingActivity, String str, List<Content> list, Place place, long j2, Long l, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = stayingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = stayingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            place = stayingActivity.location;
        }
        if ((i2 & 8) != 0) {
            j2 = stayingActivity.startTimestamp;
        }
        if ((i2 & 16) != 0) {
            l = stayingActivity.endTimestamp;
        }
        Long l8 = l;
        long j3 = j2;
        return stayingActivity.copy(str, list, place, j3, l8);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final Place component3() {
        return this.location;
    }

    public final long component4() {
        return this.startTimestamp;
    }

    public final Long component5() {
        return this.endTimestamp;
    }

    public final StayingActivity copy(String str, List<? extends Content> list, Place place, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contents");
        j.e(place, "location");
        return new StayingActivity(str, list, place, j2, l);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StayingActivity)) {
            return false;
        }
        StayingActivity stayingActivity = (StayingActivity) obj;
        if (j.a(this.id, stayingActivity.id) && j.a(this.contents, stayingActivity.contents) && j.a(this.location, stayingActivity.location) && this.startTimestamp == stayingActivity.startTimestamp && j.a(this.endTimestamp, stayingActivity.endTimestamp)) {
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
        int c5 = C0212a.c((this.location.hashCode() + C0212a.f(this.contents, this.id.hashCode() * 31, 31)) * 31, 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        return c5 + i2;
    }

    public String toString() {
        return "StayingActivity(id=" + this.id + ", contents=" + this.contents + ", location=" + this.location + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        this.location.writeToParcel(parcel, i2);
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
