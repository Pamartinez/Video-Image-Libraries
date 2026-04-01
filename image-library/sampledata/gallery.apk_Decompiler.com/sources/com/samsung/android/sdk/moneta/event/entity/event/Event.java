package com.samsung.android.sdk.moneta.event.entity.event;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003¢\u0006\u0004\b \u0010\u001fJ\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006HÆ\u0003¢\u0006\u0004\b!\u0010\u001fJ\u0012\u0010\"\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\"\u0010#Jb\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010'\u001a\u00020&HÖ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b)\u0010\u0019J\u001a\u0010-\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010*HÖ\u0003¢\u0006\u0004\b-\u0010.R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00101\u001a\u0004\b2\u0010\u001dR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u00103\u001a\u0004\b4\u0010\u001fR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0006¢\u0006\f\n\u0004\b\n\u00103\u001a\u0004\b5\u0010\u001fR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00068\u0006¢\u0006\f\n\u0004\b\f\u00103\u001a\u0004\b6\u0010\u001fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00107\u001a\u0004\b8\u0010#¨\u00069"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/Event;", "Landroid/os/Parcelable;", "", "id", "Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "when", "", "Lcom/samsung/android/sdk/moneta/event/entity/event/What;", "what", "Lcom/samsung/android/sdk/moneta/event/entity/event/Where;", "where", "Lcom/samsung/android/sdk/moneta/event/entity/event/Who;", "who", "Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;", "eventCategory", "<init>", "(Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/event/When;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/Long;", "component2", "()Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "component3", "()Ljava/util/List;", "component4", "component5", "component6", "()Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;", "copy", "(Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/event/When;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;)Lcom/samsung/android/sdk/moneta/event/entity/event/Event;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Long;", "getId", "Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "getWhen", "Ljava/util/List;", "getWhat", "getWhere", "getWho", "Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;", "getEventCategory", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Creator();
    private final /* synthetic */ EventCategory eventCategory;
    private final /* synthetic */ Long id;
    private final /* synthetic */ List<What> what;
    private final /* synthetic */ When when;
    private final /* synthetic */ List<Where> where;
    private final /* synthetic */ List<Who> who;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Event> {
        public final Event createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            EventCategory eventCategory = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            When createFromParcel = When.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            int i7 = 0;
            while (i7 != readInt) {
                i7 = a.a(What.CREATOR, parcel, arrayList, i7, 1);
            }
            int readInt2 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt2);
            int i8 = 0;
            while (i8 != readInt2) {
                i8 = a.a(Where.CREATOR, parcel, arrayList2, i8, 1);
            }
            int readInt3 = parcel.readInt();
            ArrayList arrayList3 = new ArrayList(readInt3);
            while (i2 != readInt3) {
                i2 = a.a(Who.CREATOR, parcel, arrayList3, i2, 1);
            }
            if (parcel.readInt() != 0) {
                eventCategory = EventCategory.CREATOR.createFromParcel(parcel);
            }
            return new Event(valueOf, createFromParcel, arrayList, arrayList2, arrayList3, eventCategory);
        }

        public final Event[] newArray(int i2) {
            return new Event[i2];
        }
    }

    public Event(Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategory eventCategory2) {
        j.e(when2, "when");
        j.e(list, "what");
        j.e(list2, "where");
        j.e(list3, "who");
        this.id = l;
        this.when = when2;
        this.what = list;
        this.where = list2;
        this.who = list3;
        this.eventCategory = eventCategory2;
    }

    public static /* synthetic */ Event copy$default(Event event, Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategory eventCategory2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l = event.id;
        }
        if ((i2 & 2) != 0) {
            when2 = event.when;
        }
        if ((i2 & 4) != 0) {
            list = event.what;
        }
        if ((i2 & 8) != 0) {
            list2 = event.where;
        }
        if ((i2 & 16) != 0) {
            list3 = event.who;
        }
        if ((i2 & 32) != 0) {
            eventCategory2 = event.eventCategory;
        }
        List<Who> list4 = list3;
        EventCategory eventCategory3 = eventCategory2;
        List<Where> list5 = list2;
        When when3 = when2;
        return event.copy(l, when3, list, list5, list4, eventCategory3);
    }

    public final Long component1() {
        return this.id;
    }

    public final When component2() {
        return this.when;
    }

    public final List<What> component3() {
        return this.what;
    }

    public final List<Where> component4() {
        return this.where;
    }

    public final List<Who> component5() {
        return this.who;
    }

    public final EventCategory component6() {
        return this.eventCategory;
    }

    public final Event copy(Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategory eventCategory2) {
        j.e(when2, "when");
        j.e(list, "what");
        j.e(list2, "where");
        j.e(list3, "who");
        return new Event(l, when2, list, list2, list3, eventCategory2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (j.a(this.id, event.id) && j.a(this.when, event.when) && j.a(this.what, event.what) && j.a(this.where, event.where) && j.a(this.who, event.who) && j.a(this.eventCategory, event.eventCategory)) {
            return true;
        }
        return false;
    }

    public final EventCategory getEventCategory() {
        return this.eventCategory;
    }

    public final Long getId() {
        return this.id;
    }

    public final List<What> getWhat() {
        return this.what;
    }

    public final When getWhen() {
        return this.when;
    }

    public final List<Where> getWhere() {
        return this.where;
    }

    public final List<Who> getWho() {
        return this.who;
    }

    public int hashCode() {
        int i2;
        Long l = this.id;
        int i7 = 0;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int f = C0212a.f(this.who, C0212a.f(this.where, C0212a.f(this.what, (this.when.hashCode() + (i2 * 31)) * 31, 31), 31), 31);
        EventCategory eventCategory2 = this.eventCategory;
        if (eventCategory2 != null) {
            i7 = eventCategory2.hashCode();
        }
        return f + i7;
    }

    public String toString() {
        return "Event(id=" + this.id + ", when=" + this.when + ", what=" + this.what + ", where=" + this.where + ", who=" + this.who + ", eventCategory=" + this.eventCategory + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Long l = this.id;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        this.when.writeToParcel(parcel, i2);
        Iterator j2 = a.j(parcel, this.what);
        while (j2.hasNext()) {
            ((What) j2.next()).writeToParcel(parcel, i2);
        }
        Iterator j3 = a.j(parcel, this.where);
        while (j3.hasNext()) {
            ((Where) j3.next()).writeToParcel(parcel, i2);
        }
        Iterator j8 = a.j(parcel, this.who);
        while (j8.hasNext()) {
            ((Who) j8.next()).writeToParcel(parcel, i2);
        }
        EventCategory eventCategory2 = this.eventCategory;
        if (eventCategory2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        eventCategory2.writeToParcel(parcel, i2);
    }
}
