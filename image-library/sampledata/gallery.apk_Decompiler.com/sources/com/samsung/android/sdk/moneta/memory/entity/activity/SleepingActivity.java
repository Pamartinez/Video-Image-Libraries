package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0015JJ\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b!\u0010\u0017J\u0010\u0010\"\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\"\u0010\u0015J\u001a\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b&\u0010'R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b)\u0010\u0017R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b+\u0010\u0019R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010,\u001a\u0004\b-\u0010\u001bR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b/\u0010\u001dR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00100\u001a\u0004\b1\u0010\u0015¨\u00062"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/SleepingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "", "startTimestamp", "endTimestamp", "", "sleepScore", "<init>", "(Ljava/lang/String;Ljava/util/List;JLjava/lang/Long;I)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()J", "component4", "()Ljava/lang/Long;", "component5", "copy", "(Ljava/lang/String;Ljava/util/List;JLjava/lang/Long;I)Lcom/samsung/android/sdk/moneta/memory/entity/activity/SleepingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "I", "getSleepScore", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SleepingActivity extends Activity {
    public static final Parcelable.Creator<SleepingActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final Long endTimestamp;
    private final String id;
    private final int sleepScore;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<SleepingActivity> {
        public final SleepingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(SleepingActivity.class, parcel, arrayList, i2, 1);
            }
            return new SleepingActivity(readString, arrayList, parcel.readLong(), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()), parcel.readInt());
        }

        public final SleepingActivity[] newArray(int i2) {
            return new SleepingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SleepingActivity(String str, List<? extends Content> list, long j2, Long l, int i2) {
        super(ActivityType.Sleeping);
        j.e(str, "id");
        j.e(list, "contents");
        this.id = str;
        this.contents = list;
        this.startTimestamp = j2;
        this.endTimestamp = l;
        this.sleepScore = i2;
    }

    public static /* synthetic */ SleepingActivity copy$default(SleepingActivity sleepingActivity, String str, List<Content> list, long j2, Long l, int i2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str = sleepingActivity.id;
        }
        if ((i7 & 2) != 0) {
            list = sleepingActivity.contents;
        }
        if ((i7 & 4) != 0) {
            j2 = sleepingActivity.startTimestamp;
        }
        if ((i7 & 8) != 0) {
            l = sleepingActivity.endTimestamp;
        }
        if ((i7 & 16) != 0) {
            i2 = sleepingActivity.sleepScore;
        }
        long j3 = j2;
        List<Content> list2 = list;
        return sleepingActivity.copy(str, list2, j3, l, i2);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final long component3() {
        return this.startTimestamp;
    }

    public final Long component4() {
        return this.endTimestamp;
    }

    public final int component5() {
        return this.sleepScore;
    }

    public final SleepingActivity copy(String str, List<? extends Content> list, long j2, Long l, int i2) {
        j.e(str, "id");
        j.e(list, "contents");
        return new SleepingActivity(str, list, j2, l, i2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SleepingActivity)) {
            return false;
        }
        SleepingActivity sleepingActivity = (SleepingActivity) obj;
        if (j.a(this.id, sleepingActivity.id) && j.a(this.contents, sleepingActivity.contents) && this.startTimestamp == sleepingActivity.startTimestamp && j.a(this.endTimestamp, sleepingActivity.endTimestamp) && this.sleepScore == sleepingActivity.sleepScore) {
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

    public final int getSleepScore() {
        return this.sleepScore;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c(C0212a.f(this.contents, this.id.hashCode() * 31, 31), 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        return Integer.hashCode(this.sleepScore) + ((c5 + i2) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SleepingActivity(id=");
        sb2.append(this.id);
        sb2.append(", contents=");
        sb2.append(this.contents);
        sb2.append(", startTimestamp=");
        sb2.append(this.startTimestamp);
        sb2.append(", endTimestamp=");
        sb2.append(this.endTimestamp);
        sb2.append(", sleepScore=");
        return N2.j.e(sb2, this.sleepScore, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        parcel.writeInt(this.sleepScore);
    }
}
