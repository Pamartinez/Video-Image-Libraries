package com.samsung.android.sdk.moneta.travel.model;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÆ\u0003JS\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/DailyItinerary;", "", "dayIndex", "", "transportationList", "", "Lcom/samsung/android/sdk/moneta/travel/model/TransportationBase;", "reservationList", "Lcom/samsung/android/sdk/moneta/travel/model/Reservation;", "accommodationList", "Lcom/samsung/android/sdk/moneta/travel/model/Accommodation;", "userPlanList", "Lcom/samsung/android/sdk/moneta/travel/model/UserPlan;", "<init>", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDayIndex", "()I", "getTransportationList", "()Ljava/util/List;", "getReservationList", "getAccommodationList", "getUserPlanList", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DailyItinerary {
    private final List<Accommodation> accommodationList;
    private final int dayIndex;
    private final List<Reservation> reservationList;
    private final List<TransportationBase> transportationList;
    private final List<UserPlan> userPlanList;

    public DailyItinerary(int i2, List<? extends TransportationBase> list, List<Reservation> list2, List<Accommodation> list3, List<UserPlan> list4) {
        j.e(list, "transportationList");
        j.e(list2, "reservationList");
        j.e(list3, "accommodationList");
        j.e(list4, "userPlanList");
        this.dayIndex = i2;
        this.transportationList = list;
        this.reservationList = list2;
        this.accommodationList = list3;
        this.userPlanList = list4;
    }

    public static /* synthetic */ DailyItinerary copy$default(DailyItinerary dailyItinerary, int i2, List<TransportationBase> list, List<Reservation> list2, List<Accommodation> list3, List<UserPlan> list4, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = dailyItinerary.dayIndex;
        }
        if ((i7 & 2) != 0) {
            list = dailyItinerary.transportationList;
        }
        if ((i7 & 4) != 0) {
            list2 = dailyItinerary.reservationList;
        }
        if ((i7 & 8) != 0) {
            list3 = dailyItinerary.accommodationList;
        }
        if ((i7 & 16) != 0) {
            list4 = dailyItinerary.userPlanList;
        }
        List<Accommodation> list5 = list3;
        List<UserPlan> list6 = list4;
        return dailyItinerary.copy(i2, list, list2, list5, list6);
    }

    public final int component1() {
        return this.dayIndex;
    }

    public final List<TransportationBase> component2() {
        return this.transportationList;
    }

    public final List<Reservation> component3() {
        return this.reservationList;
    }

    public final List<Accommodation> component4() {
        return this.accommodationList;
    }

    public final List<UserPlan> component5() {
        return this.userPlanList;
    }

    public final DailyItinerary copy(int i2, List<? extends TransportationBase> list, List<Reservation> list2, List<Accommodation> list3, List<UserPlan> list4) {
        j.e(list, "transportationList");
        j.e(list2, "reservationList");
        j.e(list3, "accommodationList");
        j.e(list4, "userPlanList");
        return new DailyItinerary(i2, list, list2, list3, list4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DailyItinerary)) {
            return false;
        }
        DailyItinerary dailyItinerary = (DailyItinerary) obj;
        if (this.dayIndex == dailyItinerary.dayIndex && j.a(this.transportationList, dailyItinerary.transportationList) && j.a(this.reservationList, dailyItinerary.reservationList) && j.a(this.accommodationList, dailyItinerary.accommodationList) && j.a(this.userPlanList, dailyItinerary.userPlanList)) {
            return true;
        }
        return false;
    }

    public final List<Accommodation> getAccommodationList() {
        return this.accommodationList;
    }

    public final int getDayIndex() {
        return this.dayIndex;
    }

    public final List<Reservation> getReservationList() {
        return this.reservationList;
    }

    public final List<TransportationBase> getTransportationList() {
        return this.transportationList;
    }

    public final List<UserPlan> getUserPlanList() {
        return this.userPlanList;
    }

    public int hashCode() {
        return this.userPlanList.hashCode() + C0212a.f(this.accommodationList, C0212a.f(this.reservationList, C0212a.f(this.transportationList, Integer.hashCode(this.dayIndex) * 31, 31), 31), 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DailyItinerary(dayIndex=");
        sb2.append(this.dayIndex);
        sb2.append(", transportationList=");
        sb2.append(this.transportationList);
        sb2.append(", reservationList=");
        sb2.append(this.reservationList);
        sb2.append(", accommodationList=");
        sb2.append(this.accommodationList);
        sb2.append(", userPlanList=");
        return C0212a.r(sb2, this.userPlanList, ')');
    }
}
