package com.samsung.android.sdk.moneta.travel.internal.model;

import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u0000 72\u00020\u0001:\u000287B[\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0004¢\u0006\u0004\b\u000e\u0010\u000fBs\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u000e\u0010\u0013J'\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004HÆ\u0003¢\u0006\u0004\b!\u0010 J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010 J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0003¢\u0006\u0004\b#\u0010 J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0\u0004HÆ\u0003¢\u0006\u0004\b$\u0010 Jj\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0004HÆ\u0001¢\u0006\u0004\b%\u0010&J\u0010\u0010(\u001a\u00020'HÖ\u0001¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b*\u0010\u001eJ\u001a\u0010-\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b-\u0010.R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u0010\u001eR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u00101\u001a\u0004\b2\u0010 R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\b\u00101\u001a\u0004\b3\u0010 R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00048\u0006¢\u0006\f\n\u0004\b\n\u00101\u001a\u0004\b4\u0010 R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u00101\u001a\u0004\b5\u0010 R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006¢\u0006\f\n\u0004\b\r\u00101\u001a\u0004\b6\u0010 ¨\u00069"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "", "", "dayIndex", "", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "transportationList", "Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "reservationList", "Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "accommodation", "accommodationList", "Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;", "userPlanList", "<init>", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(IILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()I", "component2", "()Ljava/util/List;", "component3", "component4", "component5", "component6", "copy", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getDayIndex", "Ljava/util/List;", "getTransportationList", "getReservationList", "getAccommodation", "getAccommodationList", "getUserPlanList", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DailyItineraryInternal {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers;
    public static final Companion Companion = new Companion((e) null);
    private final List<AccommodationInternal> accommodation;
    private final List<AccommodationInternal> accommodationList;
    private final int dayIndex;
    private final List<ReservationInternal> reservationList;
    private final List<TransportationBaseInternal> transportationList;
    private final List<UserPlanInternal> userPlanList;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return DailyItineraryInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    static {
        C1122c cVar = new C1122c(TransportationBaseInternal.Companion.serializer());
        C1122c cVar2 = new C1122c(ReservationInternal$$serializer.INSTANCE);
        AccommodationInternal$$serializer accommodationInternal$$serializer = AccommodationInternal$$serializer.INSTANCE;
        $childSerializers = new a[]{null, cVar, cVar2, new C1122c(accommodationInternal$$serializer), new C1122c(accommodationInternal$$serializer), new C1122c(UserPlanInternal$$serializer.INSTANCE)};
    }

    public /* synthetic */ DailyItineraryInternal(int i2, int i7, List list, List list2, List list3, List list4, List list5, a0 a0Var) {
        if (7 == (i2 & 7)) {
            this.dayIndex = i7;
            this.transportationList = list;
            this.reservationList = list2;
            int i8 = i2 & 8;
            C1202t tVar = C1202t.d;
            if (i8 == 0) {
                this.accommodation = tVar;
            } else {
                this.accommodation = list3;
            }
            if ((i2 & 16) == 0) {
                this.accommodationList = tVar;
            } else {
                this.accommodationList = list4;
            }
            if ((i2 & 32) == 0) {
                this.userPlanList = tVar;
            } else {
                this.userPlanList = list5;
            }
        } else {
            Q.f(i2, 7, DailyItineraryInternal$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ DailyItineraryInternal copy$default(DailyItineraryInternal dailyItineraryInternal, int i2, List<TransportationBaseInternal> list, List<ReservationInternal> list2, List<AccommodationInternal> list3, List<AccommodationInternal> list4, List<UserPlanInternal> list5, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = dailyItineraryInternal.dayIndex;
        }
        if ((i7 & 2) != 0) {
            list = dailyItineraryInternal.transportationList;
        }
        if ((i7 & 4) != 0) {
            list2 = dailyItineraryInternal.reservationList;
        }
        if ((i7 & 8) != 0) {
            list3 = dailyItineraryInternal.accommodation;
        }
        if ((i7 & 16) != 0) {
            list4 = dailyItineraryInternal.accommodationList;
        }
        if ((i7 & 32) != 0) {
            list5 = dailyItineraryInternal.userPlanList;
        }
        List<AccommodationInternal> list6 = list4;
        List<UserPlanInternal> list7 = list5;
        List<AccommodationInternal> list8 = list3;
        List<TransportationBaseInternal> list9 = list;
        return dailyItineraryInternal.copy(i2, list9, list2, list8, list6, list7);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(DailyItineraryInternal dailyItineraryInternal, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        k kVar = (k) bVar;
        kVar.r(0, dailyItineraryInternal.dayIndex, fVar);
        kVar.t(fVar, 1, aVarArr[1], dailyItineraryInternal.transportationList);
        kVar.t(fVar, 2, aVarArr[2], dailyItineraryInternal.reservationList);
        boolean d = kVar.d(fVar);
        C1202t tVar = C1202t.d;
        if (d || !j.a(dailyItineraryInternal.accommodation, tVar)) {
            kVar.t(fVar, 3, aVarArr[3], dailyItineraryInternal.accommodation);
        }
        if (kVar.d(fVar) || !j.a(dailyItineraryInternal.accommodationList, tVar)) {
            kVar.t(fVar, 4, aVarArr[4], dailyItineraryInternal.accommodationList);
        }
        if (kVar.d(fVar) || !j.a(dailyItineraryInternal.userPlanList, tVar)) {
            kVar.t(fVar, 5, aVarArr[5], dailyItineraryInternal.userPlanList);
        }
    }

    public final int component1() {
        return this.dayIndex;
    }

    public final List<TransportationBaseInternal> component2() {
        return this.transportationList;
    }

    public final List<ReservationInternal> component3() {
        return this.reservationList;
    }

    public final List<AccommodationInternal> component4() {
        return this.accommodation;
    }

    public final List<AccommodationInternal> component5() {
        return this.accommodationList;
    }

    public final List<UserPlanInternal> component6() {
        return this.userPlanList;
    }

    public final DailyItineraryInternal copy(int i2, List<? extends TransportationBaseInternal> list, List<ReservationInternal> list2, List<AccommodationInternal> list3, List<AccommodationInternal> list4, List<UserPlanInternal> list5) {
        j.e(list, "transportationList");
        j.e(list2, "reservationList");
        j.e(list3, "accommodation");
        j.e(list4, "accommodationList");
        j.e(list5, "userPlanList");
        return new DailyItineraryInternal(i2, list, list2, list3, list4, list5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DailyItineraryInternal)) {
            return false;
        }
        DailyItineraryInternal dailyItineraryInternal = (DailyItineraryInternal) obj;
        if (this.dayIndex == dailyItineraryInternal.dayIndex && j.a(this.transportationList, dailyItineraryInternal.transportationList) && j.a(this.reservationList, dailyItineraryInternal.reservationList) && j.a(this.accommodation, dailyItineraryInternal.accommodation) && j.a(this.accommodationList, dailyItineraryInternal.accommodationList) && j.a(this.userPlanList, dailyItineraryInternal.userPlanList)) {
            return true;
        }
        return false;
    }

    public final List<AccommodationInternal> getAccommodation() {
        return this.accommodation;
    }

    public final List<AccommodationInternal> getAccommodationList() {
        return this.accommodationList;
    }

    public final int getDayIndex() {
        return this.dayIndex;
    }

    public final List<ReservationInternal> getReservationList() {
        return this.reservationList;
    }

    public final List<TransportationBaseInternal> getTransportationList() {
        return this.transportationList;
    }

    public final List<UserPlanInternal> getUserPlanList() {
        return this.userPlanList;
    }

    public int hashCode() {
        return this.userPlanList.hashCode() + C0212a.f(this.accommodationList, C0212a.f(this.accommodation, C0212a.f(this.reservationList, C0212a.f(this.transportationList, Integer.hashCode(this.dayIndex) * 31, 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DailyItineraryInternal(dayIndex=");
        sb2.append(this.dayIndex);
        sb2.append(", transportationList=");
        sb2.append(this.transportationList);
        sb2.append(", reservationList=");
        sb2.append(this.reservationList);
        sb2.append(", accommodation=");
        sb2.append(this.accommodation);
        sb2.append(", accommodationList=");
        sb2.append(this.accommodationList);
        sb2.append(", userPlanList=");
        return C0212a.r(sb2, this.userPlanList, ')');
    }

    public DailyItineraryInternal(int i2, List<? extends TransportationBaseInternal> list, List<ReservationInternal> list2, List<AccommodationInternal> list3, List<AccommodationInternal> list4, List<UserPlanInternal> list5) {
        j.e(list, "transportationList");
        j.e(list2, "reservationList");
        j.e(list3, "accommodation");
        j.e(list4, "accommodationList");
        j.e(list5, "userPlanList");
        this.dayIndex = i2;
        this.transportationList = list;
        this.reservationList = list2;
        this.accommodation = list3;
        this.accommodationList = list4;
        this.userPlanList = list5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ DailyItineraryInternal(int r2, java.util.List r3, java.util.List r4, java.util.List r5, java.util.List r6, java.util.List r7, int r8, kotlin.jvm.internal.e r9) {
        /*
            r1 = this;
            r9 = r8 & 8
            ne.t r0 = ne.C1202t.d
            if (r9 == 0) goto L_0x0007
            r5 = r0
        L_0x0007:
            r9 = r8 & 16
            if (r9 == 0) goto L_0x000c
            r6 = r0
        L_0x000c:
            r8 = r8 & 32
            if (r8 == 0) goto L_0x0011
            r7 = r0
        L_0x0011:
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal.<init>(int, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, int, kotlin.jvm.internal.e):void");
    }
}
