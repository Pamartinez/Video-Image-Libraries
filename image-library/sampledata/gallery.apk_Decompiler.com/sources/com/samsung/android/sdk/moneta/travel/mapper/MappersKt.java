package com.samsung.android.sdk.moneta.travel.mapper;

import com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.FlightInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.TransportationBaseInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.TransportationInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternal;
import com.samsung.android.sdk.moneta.travel.internal.model.UserPlanInternal;
import com.samsung.android.sdk.moneta.travel.model.Accommodation;
import com.samsung.android.sdk.moneta.travel.model.DailyItinerary;
import com.samsung.android.sdk.moneta.travel.model.Flight;
import com.samsung.android.sdk.moneta.travel.model.Place;
import com.samsung.android.sdk.moneta.travel.model.Reservation;
import com.samsung.android.sdk.moneta.travel.model.Transportation;
import com.samsung.android.sdk.moneta.travel.model.TransportationBase;
import com.samsung.android.sdk.moneta.travel.model.TravelInfo;
import com.samsung.android.sdk.moneta.travel.model.TravelPlan;
import com.samsung.android.sdk.moneta.travel.model.UserPlan;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0005*\u00020\u0006H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0007*\u00020\bH\u0000\u001a\f\u0010\u0000\u001a\u00020\t*\u00020\nH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000b*\u00020\fH\u0000\u001a\f\u0010\u0000\u001a\u00020\r*\u00020\u000eH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0011*\u00020\u0012H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0013*\u00020\u0014H\u0000¨\u0006\u0015"}, d2 = {"toExternal", "Lcom/samsung/android/sdk/moneta/travel/model/TravelPlan;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/DailyItinerary;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/TransportationBase;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/Transportation;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/Flight;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/Reservation;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/Accommodation;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/UserPlan;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;", "Lcom/samsung/android/sdk/moneta/travel/model/Place;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MappersKt {
    public static final TravelPlan toExternal(TravelPlanInternal travelPlanInternal) {
        j.e(travelPlanInternal, "<this>");
        TravelInfo external = toExternal(travelPlanInternal.getTravelInfo());
        Iterable<DailyItineraryInternal> dailyItineraries = travelPlanInternal.getDailyItineraries();
        ArrayList arrayList = new ArrayList(C1196n.w0(dailyItineraries, 10));
        for (DailyItineraryInternal external2 : dailyItineraries) {
            arrayList.add(toExternal(external2));
        }
        return new TravelPlan(external, arrayList);
    }

    public static final DailyItinerary toExternal(DailyItineraryInternal dailyItineraryInternal) {
        j.e(dailyItineraryInternal, "<this>");
        int dayIndex = dailyItineraryInternal.getDayIndex();
        Iterable<TransportationBaseInternal> transportationList = dailyItineraryInternal.getTransportationList();
        ArrayList arrayList = new ArrayList(C1196n.w0(transportationList, 10));
        for (TransportationBaseInternal external : transportationList) {
            arrayList.add(toExternal(external));
        }
        Iterable<ReservationInternal> reservationList = dailyItineraryInternal.getReservationList();
        ArrayList arrayList2 = new ArrayList(C1196n.w0(reservationList, 10));
        for (ReservationInternal external2 : reservationList) {
            arrayList2.add(toExternal(external2));
        }
        ArrayList X02 = C1194l.X0(dailyItineraryInternal.getAccommodationList(), dailyItineraryInternal.getAccommodation());
        ArrayList arrayList3 = new ArrayList(C1196n.w0(X02, 10));
        Iterator it = X02.iterator();
        while (it.hasNext()) {
            arrayList3.add(toExternal((AccommodationInternal) it.next()));
        }
        Iterable<UserPlanInternal> userPlanList = dailyItineraryInternal.getUserPlanList();
        ArrayList arrayList4 = new ArrayList(C1196n.w0(userPlanList, 10));
        for (UserPlanInternal external3 : userPlanList) {
            arrayList4.add(toExternal(external3));
        }
        return new DailyItinerary(dayIndex, arrayList, arrayList2, arrayList3, arrayList4);
    }

    public static final TravelInfo toExternal(TravelInfoInternal travelInfoInternal) {
        j.e(travelInfoInternal, "<this>");
        return new TravelInfo(travelInfoInternal.isDomestic(), travelInfoInternal.getStartTime(), travelInfoInternal.getEndTime(), travelInfoInternal.getTravelDuration(), travelInfoInternal.isAugmented());
    }

    public static final TransportationBase toExternal(TransportationBaseInternal transportationBaseInternal) {
        j.e(transportationBaseInternal, "<this>");
        if (transportationBaseInternal instanceof TransportationInternal) {
            return toExternal((TransportationInternal) transportationBaseInternal);
        }
        if (transportationBaseInternal instanceof FlightInternal) {
            return toExternal((FlightInternal) transportationBaseInternal);
        }
        throw new RuntimeException();
    }

    public static final Transportation toExternal(TransportationInternal transportationInternal) {
        j.e(transportationInternal, "<this>");
        return new Transportation(toExternal(transportationInternal.getDeparture()), toExternal(transportationInternal.getArrival()), transportationInternal.getDepartureTime(), transportationInternal.getArrivalTime(), transportationInternal.getTransportationNumber(), transportationInternal.getSeatInfo(), transportationInternal.getType(), transportationInternal.getSourcePackage(), transportationInternal.isAugmented());
    }

    public static final Flight toExternal(FlightInternal flightInternal) {
        j.e(flightInternal, "<this>");
        return new Flight(toExternal(flightInternal.getDeparture()), toExternal(flightInternal.getArrival()), flightInternal.getDepartureTime(), flightInternal.getArrivalTime(), flightInternal.getTransportationNumber(), flightInternal.getSeatInfo(), flightInternal.getType(), flightInternal.getSourcePackage(), flightInternal.isAugmented(), flightInternal.getDepartTerminal(), flightInternal.getDepartGate());
    }

    public static final Reservation toExternal(ReservationInternal reservationInternal) {
        j.e(reservationInternal, "<this>");
        return new Reservation(reservationInternal.getReserveNumber(), reservationInternal.getStartTime(), reservationInternal.getEndTime(), reservationInternal.getTitle(), reservationInternal.getPhone(), toExternal(reservationInternal.getPlace()), reservationInternal.getSourcePackage(), reservationInternal.getType(), reservationInternal.isAugmented());
    }

    public static final Accommodation toExternal(AccommodationInternal accommodationInternal) {
        j.e(accommodationInternal, "<this>");
        return new Accommodation(accommodationInternal.getReserveNumber(), accommodationInternal.getCheckInTime(), accommodationInternal.getCheckOutTime(), accommodationInternal.getTitle(), accommodationInternal.getPhone(), toExternal(accommodationInternal.getPlace()), accommodationInternal.getSourcePackage(), accommodationInternal.isAugmented());
    }

    public static final UserPlan toExternal(UserPlanInternal userPlanInternal) {
        j.e(userPlanInternal, "<this>");
        ZonedDateTime startTime = userPlanInternal.getStartTime();
        ZonedDateTime endTime = userPlanInternal.getEndTime();
        String title = userPlanInternal.getTitle();
        Iterable<PlaceInternal> places = userPlanInternal.getPlaces();
        ArrayList arrayList = new ArrayList(C1196n.w0(places, 10));
        for (PlaceInternal external : places) {
            arrayList.add(toExternal(external));
        }
        return new UserPlan(startTime, endTime, title, arrayList);
    }

    public static final Place toExternal(PlaceInternal placeInternal) {
        j.e(placeInternal, "<this>");
        return new Place(placeInternal.getCity(), placeInternal.getCountry(), placeInternal.getName(), placeInternal.getAddress(), placeInternal.getCode(), placeInternal.getTypes(), placeInternal.getLat(), placeInternal.getLng());
    }
}
