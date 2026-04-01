package com.samsung.android.sdk.moneta.travel.model;

import c0.C0086a;
import i.C0212a;
import java.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\tHÆ\u0003J\t\u0010)\u001a\u00020\u000eHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003Jw\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tHÆ\u0001J\u0013\u0010-\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\tHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\n\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0014\u0010\u000b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0014\u0010\f\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001a¨\u00063"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/Flight;", "Lcom/samsung/android/sdk/moneta/travel/model/TransportationBase;", "departure", "Lcom/samsung/android/sdk/moneta/travel/model/Place;", "arrival", "departureTime", "Ljava/time/ZonedDateTime;", "arrivalTime", "transportationNumber", "", "seatInfo", "type", "sourcePackage", "isAugmented", "", "departTerminal", "departGate", "<init>", "(Lcom/samsung/android/sdk/moneta/travel/model/Place;Lcom/samsung/android/sdk/moneta/travel/model/Place;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getDeparture", "()Lcom/samsung/android/sdk/moneta/travel/model/Place;", "getArrival", "getDepartureTime", "()Ljava/time/ZonedDateTime;", "getArrivalTime", "getTransportationNumber", "()Ljava/lang/String;", "getSeatInfo", "getType", "getSourcePackage", "()Z", "getDepartTerminal", "getDepartGate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "", "hashCode", "", "toString", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Flight extends TransportationBase {
    private final Place arrival;
    private final ZonedDateTime arrivalTime;
    private final String departGate;
    private final String departTerminal;
    private final Place departure;
    private final ZonedDateTime departureTime;
    private final boolean isAugmented;
    private final String seatInfo;
    private final String sourcePackage;
    private final String transportationNumber;
    private final String type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Flight(Place place, Place place2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        super((e) null);
        j.e(place, "departure");
        j.e(place2, "arrival");
        j.e(zonedDateTime, "departureTime");
        j.e(zonedDateTime2, "arrivalTime");
        j.e(str, "transportationNumber");
        j.e(str2, "seatInfo");
        j.e(str3, "type");
        j.e(str4, "sourcePackage");
        j.e(str5, "departTerminal");
        j.e(str6, "departGate");
        this.departure = place;
        this.arrival = place2;
        this.departureTime = zonedDateTime;
        this.arrivalTime = zonedDateTime2;
        this.transportationNumber = str;
        this.seatInfo = str2;
        this.type = str3;
        this.sourcePackage = str4;
        this.isAugmented = z;
        this.departTerminal = str5;
        this.departGate = str6;
    }

    public static /* synthetic */ Flight copy$default(Flight flight, Place place, Place place2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            place = flight.departure;
        }
        if ((i2 & 2) != 0) {
            place2 = flight.arrival;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime = flight.departureTime;
        }
        if ((i2 & 8) != 0) {
            zonedDateTime2 = flight.arrivalTime;
        }
        if ((i2 & 16) != 0) {
            str = flight.transportationNumber;
        }
        if ((i2 & 32) != 0) {
            str2 = flight.seatInfo;
        }
        if ((i2 & 64) != 0) {
            str3 = flight.type;
        }
        if ((i2 & 128) != 0) {
            str4 = flight.sourcePackage;
        }
        if ((i2 & 256) != 0) {
            z = flight.isAugmented;
        }
        if ((i2 & 512) != 0) {
            str5 = flight.departTerminal;
        }
        if ((i2 & 1024) != 0) {
            str6 = flight.departGate;
        }
        String str7 = str5;
        String str8 = str6;
        String str9 = str4;
        boolean z3 = z;
        String str10 = str2;
        String str11 = str3;
        ZonedDateTime zonedDateTime3 = zonedDateTime2;
        String str12 = str;
        return flight.copy(place, place2, zonedDateTime, zonedDateTime3, str12, str10, str11, str9, z3, str7, str8);
    }

    public final Place component1() {
        return this.departure;
    }

    public final String component10() {
        return this.departTerminal;
    }

    public final String component11() {
        return this.departGate;
    }

    public final Place component2() {
        return this.arrival;
    }

    public final ZonedDateTime component3() {
        return this.departureTime;
    }

    public final ZonedDateTime component4() {
        return this.arrivalTime;
    }

    public final String component5() {
        return this.transportationNumber;
    }

    public final String component6() {
        return this.seatInfo;
    }

    public final String component7() {
        return this.type;
    }

    public final String component8() {
        return this.sourcePackage;
    }

    public final boolean component9() {
        return this.isAugmented;
    }

    public final Flight copy(Place place, Place place2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        j.e(place, "departure");
        j.e(place2, "arrival");
        j.e(zonedDateTime, "departureTime");
        ZonedDateTime zonedDateTime3 = zonedDateTime2;
        j.e(zonedDateTime3, "arrivalTime");
        String str7 = str;
        j.e(str7, "transportationNumber");
        String str8 = str2;
        j.e(str8, "seatInfo");
        String str9 = str3;
        j.e(str9, "type");
        String str10 = str4;
        j.e(str10, "sourcePackage");
        String str11 = str5;
        j.e(str11, "departTerminal");
        String str12 = str6;
        j.e(str12, "departGate");
        return new Flight(place, place2, zonedDateTime, zonedDateTime3, str7, str8, str9, str10, z, str11, str12);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Flight)) {
            return false;
        }
        Flight flight = (Flight) obj;
        if (j.a(this.departure, flight.departure) && j.a(this.arrival, flight.arrival) && j.a(this.departureTime, flight.departureTime) && j.a(this.arrivalTime, flight.arrivalTime) && j.a(this.transportationNumber, flight.transportationNumber) && j.a(this.seatInfo, flight.seatInfo) && j.a(this.type, flight.type) && j.a(this.sourcePackage, flight.sourcePackage) && this.isAugmented == flight.isAugmented && j.a(this.departTerminal, flight.departTerminal) && j.a(this.departGate, flight.departGate)) {
            return true;
        }
        return false;
    }

    public Place getArrival() {
        return this.arrival;
    }

    public ZonedDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    public final String getDepartGate() {
        return this.departGate;
    }

    public final String getDepartTerminal() {
        return this.departTerminal;
    }

    public Place getDeparture() {
        return this.departure;
    }

    public ZonedDateTime getDepartureTime() {
        return this.departureTime;
    }

    public String getSeatInfo() {
        return this.seatInfo;
    }

    public String getSourcePackage() {
        return this.sourcePackage;
    }

    public String getTransportationNumber() {
        return this.transportationNumber;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = this.arrival.hashCode();
        int hashCode2 = this.departureTime.hashCode();
        return this.departGate.hashCode() + C0212a.d(C0212a.e(C0212a.d(C0212a.d(C0212a.d(C0212a.d((this.arrivalTime.hashCode() + ((hashCode2 + ((hashCode + (this.departure.hashCode() * 31)) * 31)) * 31)) * 31, 31, this.transportationNumber), 31, this.seatInfo), 31, this.type), 31, this.sourcePackage), 31, this.isAugmented), 31, this.departTerminal);
    }

    public boolean isAugmented() {
        return this.isAugmented;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Flight(departure=");
        sb2.append(this.departure);
        sb2.append(", arrival=");
        sb2.append(this.arrival);
        sb2.append(", departureTime=");
        sb2.append(this.departureTime);
        sb2.append(", arrivalTime=");
        sb2.append(this.arrivalTime);
        sb2.append(", transportationNumber=");
        sb2.append(this.transportationNumber);
        sb2.append(", seatInfo=");
        sb2.append(this.seatInfo);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", isAugmented=");
        sb2.append(this.isAugmented);
        sb2.append(", departTerminal=");
        sb2.append(this.departTerminal);
        sb2.append(", departGate=");
        return C0086a.m(sb2, this.departGate, ')');
    }
}
