package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.MovingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b \u0010\u001fJ\u0010\u0010!\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b%\u0010&J`\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b)\u0010\u001bJ\u0010\u0010*\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b*\u0010\u0019J\u001a\u0010.\u001a\u00020-2\b\u0010,\u001a\u0004\u0018\u00010+HÖ\u0003¢\u0006\u0004\b.\u0010/R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u00100\u001a\u0004\b1\u0010\u001bR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u00102\u001a\u0004\b3\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u00104\u001a\u0004\b5\u0010\u001fR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u00104\u001a\u0004\b6\u0010\u001fR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00107\u001a\u0004\b8\u0010\"R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u00109\u001a\u0004\b:\u0010$R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010;\u001a\u0004\b<\u0010&¨\u0006="}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/MovingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "startLocation", "endLocation", "", "movingSpeed", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;FJLjava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "component4", "component5", "()F", "component6", "()J", "component7", "()Ljava/lang/Long;", "copy", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;FJLjava/lang/Long;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/MovingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getStartLocation", "getEndLocation", "F", "getMovingSpeed", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MovingActivity extends Activity {
    public static final Parcelable.Creator<MovingActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final Place endLocation;
    private final Long endTimestamp;
    private final String id;
    private final float movingSpeed;
    private final Place startLocation;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MovingActivity> {
        public final MovingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(MovingActivity.class, parcel, arrayList, i2, 1);
            }
            Parcelable.Creator<Place> creator = Place.CREATOR;
            Place createFromParcel = creator.createFromParcel(parcel);
            Long l = null;
            Place createFromParcel2 = parcel.readInt() == 0 ? null : creator.createFromParcel(parcel);
            float readFloat = parcel.readFloat();
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new MovingActivity(readString, arrayList, createFromParcel, createFromParcel2, readFloat, readLong, l);
        }

        public final MovingActivity[] newArray(int i2) {
            return new MovingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MovingActivity(String str, List<? extends Content> list, Place place, Place place2, float f, long j2, Long l) {
        super(ActivityType.Moving);
        j.e(str, "id");
        j.e(list, "contents");
        j.e(place, MovingActivityBundleWrapper.BUNDLE_KEY_START_LOCATION);
        this.id = str;
        this.contents = list;
        this.startLocation = place;
        this.endLocation = place2;
        this.movingSpeed = f;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public static /* synthetic */ MovingActivity copy$default(MovingActivity movingActivity, String str, List<Content> list, Place place, Place place2, float f, long j2, Long l, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = movingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = movingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            place = movingActivity.startLocation;
        }
        if ((i2 & 8) != 0) {
            place2 = movingActivity.endLocation;
        }
        if ((i2 & 16) != 0) {
            f = movingActivity.movingSpeed;
        }
        if ((i2 & 32) != 0) {
            j2 = movingActivity.startTimestamp;
        }
        if ((i2 & 64) != 0) {
            l = movingActivity.endTimestamp;
        }
        Long l8 = l;
        long j3 = j2;
        Place place3 = place2;
        float f5 = f;
        return movingActivity.copy(str, list, place, place3, f5, j3, l8);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final Place component3() {
        return this.startLocation;
    }

    public final Place component4() {
        return this.endLocation;
    }

    public final float component5() {
        return this.movingSpeed;
    }

    public final long component6() {
        return this.startTimestamp;
    }

    public final Long component7() {
        return this.endTimestamp;
    }

    public final MovingActivity copy(String str, List<? extends Content> list, Place place, Place place2, float f, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contents");
        j.e(place, MovingActivityBundleWrapper.BUNDLE_KEY_START_LOCATION);
        return new MovingActivity(str, list, place, place2, f, j2, l);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovingActivity)) {
            return false;
        }
        MovingActivity movingActivity = (MovingActivity) obj;
        if (j.a(this.id, movingActivity.id) && j.a(this.contents, movingActivity.contents) && j.a(this.startLocation, movingActivity.startLocation) && j.a(this.endLocation, movingActivity.endLocation) && Float.compare(this.movingSpeed, movingActivity.movingSpeed) == 0 && this.startTimestamp == movingActivity.startTimestamp && j.a(this.endTimestamp, movingActivity.endTimestamp)) {
            return true;
        }
        return false;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public final Place getEndLocation() {
        return this.endLocation;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public String getId() {
        return this.id;
    }

    public final float getMovingSpeed() {
        return this.movingSpeed;
    }

    public final Place getStartLocation() {
        return this.startLocation;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int hashCode = (this.startLocation.hashCode() + C0212a.f(this.contents, this.id.hashCode() * 31, 31)) * 31;
        Place place = this.endLocation;
        int i7 = 0;
        if (place == null) {
            i2 = 0;
        } else {
            i2 = place.hashCode();
        }
        int c5 = C0212a.c(N2.j.a(this.movingSpeed, (hashCode + i2) * 31, 31), 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l != null) {
            i7 = l.hashCode();
        }
        return c5 + i7;
    }

    public String toString() {
        return "MovingActivity(id=" + this.id + ", contents=" + this.contents + ", startLocation=" + this.startLocation + ", endLocation=" + this.endLocation + ", movingSpeed=" + this.movingSpeed + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        this.startLocation.writeToParcel(parcel, i2);
        Place place = this.endLocation;
        if (place == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            place.writeToParcel(parcel, i2);
        }
        parcel.writeFloat(this.movingSpeed);
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
