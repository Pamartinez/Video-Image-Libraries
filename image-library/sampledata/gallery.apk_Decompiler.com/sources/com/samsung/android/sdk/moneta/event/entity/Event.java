package com.samsung.android.sdk.moneta.event.entity;

import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.L;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u0000 D2\u00020\u0001:\u0002EDB_\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012Bs\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0011\u0010\u0017J'\u0010 \u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010!\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b#\u0010$J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0004\b%\u0010&J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003¢\u0006\u0004\b'\u0010&J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006HÆ\u0003¢\u0006\u0004\b(\u0010&J\u0010\u0010)\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b+\u0010,Jj\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fHÆ\u0001¢\u0006\u0004\b-\u0010.J\u0010\u00100\u001a\u00020/HÖ\u0001¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b2\u00103J\u001a\u00106\u001a\u0002052\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b6\u00107R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00108\u001a\u0004\b9\u0010\"R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010:\u001a\u0004\b;\u0010$R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010<\u001a\u0004\b=\u0010&R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010<\u001a\u0004\b>\u0010&R\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00068\u0006¢\u0006\f\n\u0004\b\f\u0010<\u001a\u0004\b?\u0010&R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010@\u001a\u0004\bA\u0010*R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010B\u001a\u0004\bC\u0010,¨\u0006F"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Event;", "", "", "id", "Lcom/samsung/android/sdk/moneta/event/entity/When;", "when", "", "Lcom/samsung/android/sdk/moneta/event/entity/What;", "what", "Lcom/samsung/android/sdk/moneta/event/entity/Where;", "where", "Lcom/samsung/android/sdk/moneta/event/entity/Who;", "who", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "eventCategory", "Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;", "eventSubCategory", "<init>", "(Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/When;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/When;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/event/entity/Event;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/Long;", "component2", "()Lcom/samsung/android/sdk/moneta/event/entity/When;", "component3", "()Ljava/util/List;", "component4", "component5", "component6", "()Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "component7", "()Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;", "copy", "(Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/When;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;)Lcom/samsung/android/sdk/moneta/event/entity/Event;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Long;", "getId", "Lcom/samsung/android/sdk/moneta/event/entity/When;", "getWhen", "Ljava/util/List;", "getWhat", "getWhere", "getWho", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "getEventCategory", "Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;", "getEventSubCategory", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Event {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, new C1122c(What$$serializer.INSTANCE), new C1122c(Where$$serializer.INSTANCE), new C1122c(Who$$serializer.INSTANCE), EventCategoryEnum.Companion.serializer(), EventSubCategoryEnum.Companion.serializer()};
    public static final Companion Companion = new Companion((e) null);
    private final EventCategoryEnum eventCategory;
    private final EventSubCategoryEnum eventSubCategory;
    private final Long id;
    private final List<What> what;
    private final When when;
    private final List<Where> where;
    private final List<Who> who;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Event$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/event/entity/Event;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return Event$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ Event(int i2, Long l, When when2, List list, List list2, List list3, EventCategoryEnum eventCategoryEnum, EventSubCategoryEnum eventSubCategoryEnum, a0 a0Var) {
        if (2 == (i2 & 2)) {
            if ((i2 & 1) == 0) {
                this.id = null;
            } else {
                this.id = l;
            }
            this.when = when2;
            int i7 = i2 & 4;
            C1202t tVar = C1202t.d;
            if (i7 == 0) {
                this.what = tVar;
            } else {
                this.what = list;
            }
            if ((i2 & 8) == 0) {
                this.where = tVar;
            } else {
                this.where = list2;
            }
            if ((i2 & 16) == 0) {
                this.who = tVar;
            } else {
                this.who = list3;
            }
            if ((i2 & 32) == 0) {
                this.eventCategory = EventCategoryEnum.UNKNOWN;
            } else {
                this.eventCategory = eventCategoryEnum;
            }
            if ((i2 & 64) == 0) {
                this.eventSubCategory = EventSubCategoryEnum.UNKNOWN;
            } else {
                this.eventSubCategory = eventSubCategoryEnum;
            }
        } else {
            Q.f(i2, 2, Event$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ Event copy$default(Event event, Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategoryEnum eventCategoryEnum, EventSubCategoryEnum eventSubCategoryEnum, int i2, Object obj) {
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
            eventCategoryEnum = event.eventCategory;
        }
        if ((i2 & 64) != 0) {
            eventSubCategoryEnum = event.eventSubCategory;
        }
        EventCategoryEnum eventCategoryEnum2 = eventCategoryEnum;
        EventSubCategoryEnum eventSubCategoryEnum2 = eventSubCategoryEnum;
        List<Where> list4 = list2;
        List<Who> list5 = list3;
        return event.copy(l, when2, list, list4, list5, eventCategoryEnum2, eventSubCategoryEnum2);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(Event event, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        if (bVar.d(fVar) || event.id != null) {
            bVar.c(fVar, 0, L.f4673a, event.id);
        }
        k kVar = (k) bVar;
        kVar.t(fVar, 1, When$$serializer.INSTANCE, event.when);
        boolean d = bVar.d(fVar);
        C1202t tVar = C1202t.d;
        if (d || !j.a(event.what, tVar)) {
            kVar.t(fVar, 2, aVarArr[2], event.what);
        }
        if (bVar.d(fVar) || !j.a(event.where, tVar)) {
            kVar.t(fVar, 3, aVarArr[3], event.where);
        }
        if (bVar.d(fVar) || !j.a(event.who, tVar)) {
            kVar.t(fVar, 4, aVarArr[4], event.who);
        }
        if (bVar.d(fVar) || event.eventCategory != EventCategoryEnum.UNKNOWN) {
            kVar.t(fVar, 5, aVarArr[5], event.eventCategory);
        }
        if (bVar.d(fVar) || event.eventSubCategory != EventSubCategoryEnum.UNKNOWN) {
            kVar.t(fVar, 6, aVarArr[6], event.eventSubCategory);
        }
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

    public final EventCategoryEnum component6() {
        return this.eventCategory;
    }

    public final EventSubCategoryEnum component7() {
        return this.eventSubCategory;
    }

    public final Event copy(Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategoryEnum eventCategoryEnum, EventSubCategoryEnum eventSubCategoryEnum) {
        j.e(when2, "when");
        j.e(list, "what");
        j.e(list2, "where");
        j.e(list3, "who");
        j.e(eventCategoryEnum, "eventCategory");
        j.e(eventSubCategoryEnum, "eventSubCategory");
        return new Event(l, when2, list, list2, list3, eventCategoryEnum, eventSubCategoryEnum);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (j.a(this.id, event.id) && j.a(this.when, event.when) && j.a(this.what, event.what) && j.a(this.where, event.where) && j.a(this.who, event.who) && this.eventCategory == event.eventCategory && this.eventSubCategory == event.eventSubCategory) {
            return true;
        }
        return false;
    }

    public final EventCategoryEnum getEventCategory() {
        return this.eventCategory;
    }

    public final EventSubCategoryEnum getEventSubCategory() {
        return this.eventSubCategory;
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
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int hashCode = this.when.hashCode();
        int f = C0212a.f(this.who, C0212a.f(this.where, C0212a.f(this.what, (hashCode + (i2 * 31)) * 31, 31), 31), 31);
        return this.eventSubCategory.hashCode() + ((this.eventCategory.hashCode() + f) * 31);
    }

    public String toString() {
        return "Event(id=" + this.id + ", when=" + this.when + ", what=" + this.what + ", where=" + this.where + ", who=" + this.who + ", eventCategory=" + this.eventCategory + ", eventSubCategory=" + this.eventSubCategory + ')';
    }

    public Event(Long l, When when2, List<What> list, List<Where> list2, List<Who> list3, EventCategoryEnum eventCategoryEnum, EventSubCategoryEnum eventSubCategoryEnum) {
        j.e(when2, "when");
        j.e(list, "what");
        j.e(list2, "where");
        j.e(list3, "who");
        j.e(eventCategoryEnum, "eventCategory");
        j.e(eventSubCategoryEnum, "eventSubCategory");
        this.id = l;
        this.when = when2;
        this.what = list;
        this.where = list2;
        this.who = list3;
        this.eventCategory = eventCategoryEnum;
        this.eventSubCategory = eventSubCategoryEnum;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Event(java.lang.Long r2, com.samsung.android.sdk.moneta.event.entity.When r3, java.util.List r4, java.util.List r5, java.util.List r6, com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum r7, com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum r8, int r9, kotlin.jvm.internal.e r10) {
        /*
            r1 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0005
            r2 = 0
        L_0x0005:
            r10 = r9 & 4
            ne.t r0 = ne.C1202t.d
            if (r10 == 0) goto L_0x000c
            r4 = r0
        L_0x000c:
            r10 = r9 & 8
            if (r10 == 0) goto L_0x0011
            r5 = r0
        L_0x0011:
            r10 = r9 & 16
            if (r10 == 0) goto L_0x0016
            r6 = r0
        L_0x0016:
            r10 = r9 & 32
            if (r10 == 0) goto L_0x001c
            com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum r7 = com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum.UNKNOWN
        L_0x001c:
            r9 = r9 & 64
            if (r9 == 0) goto L_0x0022
            com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum r8 = com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum.UNKNOWN
        L_0x0022:
            r9 = r7
            r10 = r8
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.Event.<init>(java.lang.Long, com.samsung.android.sdk.moneta.event.entity.When, java.util.List, java.util.List, java.util.List, com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum, com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum, int, kotlin.jvm.internal.e):void");
    }
}
