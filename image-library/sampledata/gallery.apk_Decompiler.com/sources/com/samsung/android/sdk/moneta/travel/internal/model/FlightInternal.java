package com.samsung.android.sdk.moneta.travel.internal.model;

import c0.C0086a;
import com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer;
import gg.a;
import i.C0212a;
import ig.f;
import java.time.ZonedDateTime;
import jg.b;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\b\u0018\u0000 J2\u00020\u0001:\u0002KJB_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012B\u0001\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0011\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\bHÆ\u0003¢\u0006\u0004\b \u0010\u001fJ\u0010\u0010!\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b!\u0010\u001fJ\u0010\u0010\"\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\"\u0010\u001fJ\u0010\u0010#\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b%\u0010\u001fJ\u0010\u0010&\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b&\u0010\u001fJ~\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b)\u0010\u001fJ\u0010\u0010*\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b*\u0010+J\u001a\u0010.\u001a\u00020\r2\b\u0010-\u001a\u0004\u0018\u00010,HÖ\u0003¢\u0006\u0004\b.\u0010/J'\u00108\u001a\u0002052\u0006\u00100\u001a\u00020\u00002\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u000203H\u0001¢\u0006\u0004\b6\u00107R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u00109\u001a\u0004\b:\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u00109\u001a\u0004\b;\u0010\u0019R \u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010<\u0012\u0004\b>\u0010?\u001a\u0004\b=\u0010\u001cR \u0010\u0007\u001a\u00020\u00058\u0016X\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010<\u0012\u0004\bA\u0010?\u001a\u0004\b@\u0010\u001cR\u001a\u0010\t\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010B\u001a\u0004\bC\u0010\u001fR\u001a\u0010\n\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\n\u0010B\u001a\u0004\bD\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\u000b\u0010B\u001a\u0004\bE\u0010\u001fR\u001a\u0010\f\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\f\u0010B\u001a\u0004\bF\u0010\u001fR\u001a\u0010\u000e\u001a\u00020\r8\u0016X\u0004¢\u0006\f\n\u0004\b\u000e\u0010G\u001a\u0004\b\u000e\u0010$R\u0017\u0010\u000f\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010B\u001a\u0004\bH\u0010\u001fR\u0017\u0010\u0010\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0010\u0010B\u001a\u0004\bI\u0010\u001f¨\u0006L"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "departure", "arrival", "Ljava/time/ZonedDateTime;", "departureTime", "arrivalTime", "", "transportationNumber", "seatInfo", "type", "sourcePackage", "", "isAugmented", "departTerminal", "departGate", "<init>", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lkg/a0;)V", "component1", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "component2", "component3", "()Ljava/time/ZonedDateTime;", "component4", "component5", "()Ljava/lang/String;", "component6", "component7", "component8", "component9", "()Z", "component10", "component11", "copy", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;", "toString", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;Ljg/b;Lig/f;)V", "write$Self", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "getDeparture", "getArrival", "Ljava/time/ZonedDateTime;", "getDepartureTime", "getDepartureTime$annotations", "()V", "getArrivalTime", "getArrivalTime$annotations", "Ljava/lang/String;", "getTransportationNumber", "getSeatInfo", "getType", "getSourcePackage", "Z", "getDepartTerminal", "getDepartGate", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlightInternal extends TransportationBaseInternal {
    public static final Companion Companion = new Companion((e) null);
    private final PlaceInternal arrival;
    private final ZonedDateTime arrivalTime;
    private final String departGate;
    private final String departTerminal;
    private final PlaceInternal departure;
    private final ZonedDateTime departureTime;
    private final boolean isAugmented;
    private final String seatInfo;
    private final String sourcePackage;
    private final String transportationNumber;
    private final String type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return FlightInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FlightInternal(int i2, PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6, a0 a0Var) {
        super(i2, a0Var);
        if (2047 == (i2 & 2047)) {
            this.departure = placeInternal;
            this.arrival = placeInternal2;
            this.departureTime = zonedDateTime;
            this.arrivalTime = zonedDateTime2;
            this.transportationNumber = str;
            this.seatInfo = str2;
            this.type = str3;
            this.sourcePackage = str4;
            this.isAugmented = z;
            this.departTerminal = str5;
            this.departGate = str6;
            return;
        }
        Q.f(i2, 2047, FlightInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ FlightInternal copy$default(FlightInternal flightInternal, PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            placeInternal = flightInternal.departure;
        }
        if ((i2 & 2) != 0) {
            placeInternal2 = flightInternal.arrival;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime = flightInternal.departureTime;
        }
        if ((i2 & 8) != 0) {
            zonedDateTime2 = flightInternal.arrivalTime;
        }
        if ((i2 & 16) != 0) {
            str = flightInternal.transportationNumber;
        }
        if ((i2 & 32) != 0) {
            str2 = flightInternal.seatInfo;
        }
        if ((i2 & 64) != 0) {
            str3 = flightInternal.type;
        }
        if ((i2 & 128) != 0) {
            str4 = flightInternal.sourcePackage;
        }
        if ((i2 & 256) != 0) {
            z = flightInternal.isAugmented;
        }
        if ((i2 & 512) != 0) {
            str5 = flightInternal.departTerminal;
        }
        if ((i2 & 1024) != 0) {
            str6 = flightInternal.departGate;
        }
        String str7 = str5;
        String str8 = str6;
        String str9 = str4;
        boolean z3 = z;
        String str10 = str2;
        String str11 = str3;
        ZonedDateTime zonedDateTime3 = zonedDateTime2;
        String str12 = str;
        return flightInternal.copy(placeInternal, placeInternal2, zonedDateTime, zonedDateTime3, str12, str10, str11, str9, z3, str7, str8);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(FlightInternal flightInternal, b bVar, f fVar) {
        TransportationBaseInternal.write$Self(flightInternal, bVar, fVar);
        PlaceInternal$$serializer placeInternal$$serializer = PlaceInternal$$serializer.INSTANCE;
        k kVar = (k) bVar;
        kVar.t(fVar, 0, placeInternal$$serializer, flightInternal.getDeparture());
        kVar.t(fVar, 1, placeInternal$$serializer, flightInternal.getArrival());
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        kVar.t(fVar, 2, zonedDateTimeSerializer, flightInternal.getDepartureTime());
        kVar.t(fVar, 3, zonedDateTimeSerializer, flightInternal.getArrivalTime());
        kVar.u(fVar, 4, flightInternal.getTransportationNumber());
        kVar.u(fVar, 5, flightInternal.getSeatInfo());
        kVar.u(fVar, 6, flightInternal.getType());
        kVar.u(fVar, 7, flightInternal.getSourcePackage());
        kVar.o(fVar, 8, flightInternal.isAugmented());
        kVar.u(fVar, 9, flightInternal.departTerminal);
        kVar.u(fVar, 10, flightInternal.departGate);
    }

    public final PlaceInternal component1() {
        return this.departure;
    }

    public final String component10() {
        return this.departTerminal;
    }

    public final String component11() {
        return this.departGate;
    }

    public final PlaceInternal component2() {
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

    public final FlightInternal copy(PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        j.e(placeInternal, "departure");
        j.e(placeInternal2, "arrival");
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
        return new FlightInternal(placeInternal, placeInternal2, zonedDateTime, zonedDateTime3, str7, str8, str9, str10, z, str11, str12);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlightInternal)) {
            return false;
        }
        FlightInternal flightInternal = (FlightInternal) obj;
        if (j.a(this.departure, flightInternal.departure) && j.a(this.arrival, flightInternal.arrival) && j.a(this.departureTime, flightInternal.departureTime) && j.a(this.arrivalTime, flightInternal.arrivalTime) && j.a(this.transportationNumber, flightInternal.transportationNumber) && j.a(this.seatInfo, flightInternal.seatInfo) && j.a(this.type, flightInternal.type) && j.a(this.sourcePackage, flightInternal.sourcePackage) && this.isAugmented == flightInternal.isAugmented && j.a(this.departTerminal, flightInternal.departTerminal) && j.a(this.departGate, flightInternal.departGate)) {
            return true;
        }
        return false;
    }

    public PlaceInternal getArrival() {
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

    public PlaceInternal getDeparture() {
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
        StringBuilder sb2 = new StringBuilder("FlightInternal(departure=");
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

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlightInternal(PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        super((e) null);
        j.e(placeInternal, "departure");
        j.e(placeInternal2, "arrival");
        j.e(zonedDateTime, "departureTime");
        j.e(zonedDateTime2, "arrivalTime");
        j.e(str, "transportationNumber");
        j.e(str2, "seatInfo");
        j.e(str3, "type");
        j.e(str4, "sourcePackage");
        j.e(str5, "departTerminal");
        j.e(str6, "departGate");
        this.departure = placeInternal;
        this.arrival = placeInternal2;
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

    public static /* synthetic */ void getArrivalTime$annotations() {
    }

    public static /* synthetic */ void getDepartureTime$annotations() {
    }
}
