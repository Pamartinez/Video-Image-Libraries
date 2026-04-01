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

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0015\b\b\u0018\u0000 D2\u00020\u0001:\u0002EDBO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010Bs\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0015J'\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010 J\u0010\u0010\"\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b$\u0010#J\u0010\u0010%\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b'\u0010&J\u0010\u0010(\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b(\u0010&J\u0010\u0010)\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b)\u0010&J\u0010\u0010*\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b*\u0010+Jj\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b.\u0010&J\u0010\u0010/\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b/\u00100J\u001a\u00103\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u000101HÖ\u0003¢\u0006\u0004\b3\u00104R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u00105\u001a\u0004\b6\u0010 R\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0004\u00105\u001a\u0004\b7\u0010 R \u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\u0012\n\u0004\b\u0006\u00108\u0012\u0004\b:\u0010;\u001a\u0004\b9\u0010#R \u0010\u0007\u001a\u00020\u00058\u0016X\u0004¢\u0006\u0012\n\u0004\b\u0007\u00108\u0012\u0004\b=\u0010;\u001a\u0004\b<\u0010#R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010>\u001a\u0004\b?\u0010&R\u001a\u0010\n\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\n\u0010>\u001a\u0004\b@\u0010&R\u001a\u0010\u000b\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\u000b\u0010>\u001a\u0004\bA\u0010&R\u001a\u0010\f\u001a\u00020\b8\u0016X\u0004¢\u0006\f\n\u0004\b\f\u0010>\u001a\u0004\bB\u0010&R\u001a\u0010\u000e\u001a\u00020\r8\u0016X\u0004¢\u0006\f\n\u0004\b\u000e\u0010C\u001a\u0004\b\u000e\u0010+¨\u0006F"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "departure", "arrival", "Ljava/time/ZonedDateTime;", "departureTime", "arrivalTime", "", "transportationNumber", "seatInfo", "type", "sourcePackage", "", "isAugmented", "<init>", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "component2", "component3", "()Ljava/time/ZonedDateTime;", "component4", "component5", "()Ljava/lang/String;", "component6", "component7", "component8", "component9", "()Z", "copy", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;", "toString", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "getDeparture", "getArrival", "Ljava/time/ZonedDateTime;", "getDepartureTime", "getDepartureTime$annotations", "()V", "getArrivalTime", "getArrivalTime$annotations", "Ljava/lang/String;", "getTransportationNumber", "getSeatInfo", "getType", "getSourcePackage", "Z", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TransportationInternal extends TransportationBaseInternal {
    public static final Companion Companion = new Companion((e) null);
    private final PlaceInternal arrival;
    private final ZonedDateTime arrivalTime;
    private final PlaceInternal departure;
    private final ZonedDateTime departureTime;
    private final boolean isAugmented;
    private final String seatInfo;
    private final String sourcePackage;
    private final String transportationNumber;
    private final String type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return TransportationInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TransportationInternal(int i2, PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, a0 a0Var) {
        super(i2, a0Var);
        if (511 == (i2 & 511)) {
            this.departure = placeInternal;
            this.arrival = placeInternal2;
            this.departureTime = zonedDateTime;
            this.arrivalTime = zonedDateTime2;
            this.transportationNumber = str;
            this.seatInfo = str2;
            this.type = str3;
            this.sourcePackage = str4;
            this.isAugmented = z;
            return;
        }
        Q.f(i2, 511, TransportationInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ TransportationInternal copy$default(TransportationInternal transportationInternal, PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            placeInternal = transportationInternal.departure;
        }
        if ((i2 & 2) != 0) {
            placeInternal2 = transportationInternal.arrival;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime = transportationInternal.departureTime;
        }
        if ((i2 & 8) != 0) {
            zonedDateTime2 = transportationInternal.arrivalTime;
        }
        if ((i2 & 16) != 0) {
            str = transportationInternal.transportationNumber;
        }
        if ((i2 & 32) != 0) {
            str2 = transportationInternal.seatInfo;
        }
        if ((i2 & 64) != 0) {
            str3 = transportationInternal.type;
        }
        if ((i2 & 128) != 0) {
            str4 = transportationInternal.sourcePackage;
        }
        if ((i2 & 256) != 0) {
            z = transportationInternal.isAugmented;
        }
        String str5 = str4;
        boolean z3 = z;
        String str6 = str2;
        String str7 = str3;
        ZonedDateTime zonedDateTime3 = zonedDateTime2;
        String str8 = str;
        return transportationInternal.copy(placeInternal, placeInternal2, zonedDateTime, zonedDateTime3, str8, str6, str7, str5, z3);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(TransportationInternal transportationInternal, b bVar, f fVar) {
        TransportationBaseInternal.write$Self(transportationInternal, bVar, fVar);
        PlaceInternal$$serializer placeInternal$$serializer = PlaceInternal$$serializer.INSTANCE;
        k kVar = (k) bVar;
        kVar.t(fVar, 0, placeInternal$$serializer, transportationInternal.getDeparture());
        kVar.t(fVar, 1, placeInternal$$serializer, transportationInternal.getArrival());
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        kVar.t(fVar, 2, zonedDateTimeSerializer, transportationInternal.getDepartureTime());
        kVar.t(fVar, 3, zonedDateTimeSerializer, transportationInternal.getArrivalTime());
        kVar.u(fVar, 4, transportationInternal.getTransportationNumber());
        kVar.u(fVar, 5, transportationInternal.getSeatInfo());
        kVar.u(fVar, 6, transportationInternal.getType());
        kVar.u(fVar, 7, transportationInternal.getSourcePackage());
        kVar.o(fVar, 8, transportationInternal.isAugmented());
    }

    public final PlaceInternal component1() {
        return this.departure;
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

    public final TransportationInternal copy(PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z) {
        j.e(placeInternal, "departure");
        j.e(placeInternal2, "arrival");
        j.e(zonedDateTime, "departureTime");
        j.e(zonedDateTime2, "arrivalTime");
        j.e(str, "transportationNumber");
        String str5 = str2;
        j.e(str5, "seatInfo");
        String str6 = str3;
        j.e(str6, "type");
        String str7 = str4;
        j.e(str7, "sourcePackage");
        return new TransportationInternal(placeInternal, placeInternal2, zonedDateTime, zonedDateTime2, str, str5, str6, str7, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransportationInternal)) {
            return false;
        }
        TransportationInternal transportationInternal = (TransportationInternal) obj;
        if (j.a(this.departure, transportationInternal.departure) && j.a(this.arrival, transportationInternal.arrival) && j.a(this.departureTime, transportationInternal.departureTime) && j.a(this.arrivalTime, transportationInternal.arrivalTime) && j.a(this.transportationNumber, transportationInternal.transportationNumber) && j.a(this.seatInfo, transportationInternal.seatInfo) && j.a(this.type, transportationInternal.type) && j.a(this.sourcePackage, transportationInternal.sourcePackage) && this.isAugmented == transportationInternal.isAugmented) {
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
        return Boolean.hashCode(this.isAugmented) + C0212a.d(C0212a.d(C0212a.d(C0212a.d((this.arrivalTime.hashCode() + ((hashCode2 + ((hashCode + (this.departure.hashCode() * 31)) * 31)) * 31)) * 31, 31, this.transportationNumber), 31, this.seatInfo), 31, this.type), 31, this.sourcePackage);
    }

    public boolean isAugmented() {
        return this.isAugmented;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TransportationInternal(departure=");
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
        return C0086a.n(sb2, this.isAugmented, ')');
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TransportationInternal(PlaceInternal placeInternal, PlaceInternal placeInternal2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, String str2, String str3, String str4, boolean z) {
        super((e) null);
        j.e(placeInternal, "departure");
        j.e(placeInternal2, "arrival");
        j.e(zonedDateTime, "departureTime");
        j.e(zonedDateTime2, "arrivalTime");
        j.e(str, "transportationNumber");
        j.e(str2, "seatInfo");
        j.e(str3, "type");
        j.e(str4, "sourcePackage");
        this.departure = placeInternal;
        this.arrival = placeInternal2;
        this.departureTime = zonedDateTime;
        this.arrivalTime = zonedDateTime2;
        this.transportationNumber = str;
        this.seatInfo = str2;
        this.type = str3;
        this.sourcePackage = str4;
        this.isAugmented = z;
    }

    public static /* synthetic */ void getArrivalTime$annotations() {
    }

    public static /* synthetic */ void getDepartureTime$annotations() {
    }
}
