package com.samsung.android.sdk.moneta.travel.model;

import java.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0012\u0010\u0016\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0012\u0010\u0018\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001c\u0001\u0002\u001d\u001e¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/TransportationBase;", "", "<init>", "()V", "departure", "Lcom/samsung/android/sdk/moneta/travel/model/Place;", "getDeparture", "()Lcom/samsung/android/sdk/moneta/travel/model/Place;", "arrival", "getArrival", "departureTime", "Ljava/time/ZonedDateTime;", "getDepartureTime", "()Ljava/time/ZonedDateTime;", "arrivalTime", "getArrivalTime", "transportationNumber", "", "getTransportationNumber", "()Ljava/lang/String;", "seatInfo", "getSeatInfo", "type", "getType", "sourcePackage", "getSourcePackage", "isAugmented", "", "()Z", "Lcom/samsung/android/sdk/moneta/travel/model/Flight;", "Lcom/samsung/android/sdk/moneta/travel/model/Transportation;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TransportationBase {
    public /* synthetic */ TransportationBase(e eVar) {
        this();
    }

    public abstract Place getArrival();

    public abstract ZonedDateTime getArrivalTime();

    public abstract Place getDeparture();

    public abstract ZonedDateTime getDepartureTime();

    public abstract String getSeatInfo();

    public abstract String getSourcePackage();

    public abstract String getTransportationNumber();

    public abstract String getType();

    public abstract boolean isAugmented();

    private TransportationBase() {
    }
}
