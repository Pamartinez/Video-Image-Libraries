package com.samsung.android.sdk.moneta.travel.model;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/TravelPlan;", "", "travelInfo", "Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;", "dailyItineraries", "", "Lcom/samsung/android/sdk/moneta/travel/model/DailyItinerary;", "<init>", "(Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;Ljava/util/List;)V", "getTravelInfo", "()Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;", "getDailyItineraries", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelPlan {
    private final List<DailyItinerary> dailyItineraries;
    private final TravelInfo travelInfo;

    public TravelPlan(TravelInfo travelInfo2, List<DailyItinerary> list) {
        j.e(travelInfo2, "travelInfo");
        j.e(list, "dailyItineraries");
        this.travelInfo = travelInfo2;
        this.dailyItineraries = list;
    }

    public static /* synthetic */ TravelPlan copy$default(TravelPlan travelPlan, TravelInfo travelInfo2, List<DailyItinerary> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            travelInfo2 = travelPlan.travelInfo;
        }
        if ((i2 & 2) != 0) {
            list = travelPlan.dailyItineraries;
        }
        return travelPlan.copy(travelInfo2, list);
    }

    public final TravelInfo component1() {
        return this.travelInfo;
    }

    public final List<DailyItinerary> component2() {
        return this.dailyItineraries;
    }

    public final TravelPlan copy(TravelInfo travelInfo2, List<DailyItinerary> list) {
        j.e(travelInfo2, "travelInfo");
        j.e(list, "dailyItineraries");
        return new TravelPlan(travelInfo2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelPlan)) {
            return false;
        }
        TravelPlan travelPlan = (TravelPlan) obj;
        if (j.a(this.travelInfo, travelPlan.travelInfo) && j.a(this.dailyItineraries, travelPlan.dailyItineraries)) {
            return true;
        }
        return false;
    }

    public final List<DailyItinerary> getDailyItineraries() {
        return this.dailyItineraries;
    }

    public final TravelInfo getTravelInfo() {
        return this.travelInfo;
    }

    public int hashCode() {
        return this.dailyItineraries.hashCode() + (this.travelInfo.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TravelPlan(travelInfo=");
        sb2.append(this.travelInfo);
        sb2.append(", dailyItineraries=");
        return C0212a.r(sb2, this.dailyItineraries, ')');
    }
}
