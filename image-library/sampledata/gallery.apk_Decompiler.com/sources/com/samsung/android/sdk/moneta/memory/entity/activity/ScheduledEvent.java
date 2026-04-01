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
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJB\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0016J\u0010\u0010\u001f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0014J\u001a\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010 HÖ\u0003¢\u0006\u0004\b#\u0010$R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010\u0016R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b(\u0010\u0018R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b*\u0010\u001aR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010)\u001a\u0004\b+\u0010\u001a¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()Ljava/lang/Long;", "component4", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "Ljava/lang/Long;", "getStartTimestamp", "getEndTimestamp", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledEvent extends Activity {
    public static final Parcelable.Creator<ScheduledEvent> CREATOR = new Creator();
    private final List<Content> contents;
    private final Long endTimestamp;
    private final String id;
    private final Long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ScheduledEvent> {
        public final ScheduledEvent createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(ScheduledEvent.class, parcel, arrayList, i2, 1);
            }
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new ScheduledEvent(readString, arrayList, valueOf, l);
        }

        public final ScheduledEvent[] newArray(int i2) {
            return new ScheduledEvent[i2];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ScheduledEvent(String str, List list, Long l, Long l8, int i2, e eVar) {
        this(str, list, (i2 & 4) != 0 ? null : l, (i2 & 8) != 0 ? null : l8);
    }

    public static /* synthetic */ ScheduledEvent copy$default(ScheduledEvent scheduledEvent, String str, List<Content> list, Long l, Long l8, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = scheduledEvent.id;
        }
        if ((i2 & 2) != 0) {
            list = scheduledEvent.contents;
        }
        if ((i2 & 4) != 0) {
            l = scheduledEvent.startTimestamp;
        }
        if ((i2 & 8) != 0) {
            l8 = scheduledEvent.endTimestamp;
        }
        return scheduledEvent.copy(str, list, l, l8);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final Long component3() {
        return this.startTimestamp;
    }

    public final Long component4() {
        return this.endTimestamp;
    }

    public final ScheduledEvent copy(String str, List<? extends Content> list, Long l, Long l8) {
        j.e(str, "id");
        j.e(list, "contents");
        return new ScheduledEvent(str, list, l, l8);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScheduledEvent)) {
            return false;
        }
        ScheduledEvent scheduledEvent = (ScheduledEvent) obj;
        if (j.a(this.id, scheduledEvent.id) && j.a(this.contents, scheduledEvent.contents) && j.a(this.startTimestamp, scheduledEvent.startTimestamp) && j.a(this.endTimestamp, scheduledEvent.endTimestamp)) {
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

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int f = C0212a.f(this.contents, this.id.hashCode() * 31, 31);
        Long l = this.startTimestamp;
        int i7 = 0;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int i8 = (f + i2) * 31;
        Long l8 = this.endTimestamp;
        if (l8 != null) {
            i7 = l8.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        return "ScheduledEvent(id=" + this.id + ", contents=" + this.contents + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScheduledEvent(String str, List<? extends Content> list, Long l, Long l8) {
        super(ActivityType.ScheduledEvent);
        j.e(str, "id");
        j.e(list, "contents");
        this.id = str;
        this.contents = list;
        this.startTimestamp = l;
        this.endTimestamp = l8;
    }
}
