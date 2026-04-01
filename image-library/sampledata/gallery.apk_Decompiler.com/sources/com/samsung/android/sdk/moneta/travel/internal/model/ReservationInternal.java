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
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\b\b\u0018\u0000 C2\u00020\u0001:\u0002DCBQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010Bs\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0015J'\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b#\u0010\"J\u0010\u0010$\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b$\u0010 J\u0012\u0010%\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b%\u0010 J\u0010\u0010&\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b(\u0010 J\u0010\u0010)\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b)\u0010 J\u0010\u0010*\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b*\u0010+Jl\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b.\u0010 J\u0010\u0010/\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b/\u00100J\u001a\u00102\u001a\u00020\r2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b2\u00103R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u0010 R \u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u00106\u0012\u0004\b8\u00109\u001a\u0004\b7\u0010\"R \u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0006\u00106\u0012\u0004\b;\u00109\u001a\u0004\b:\u0010\"R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00104\u001a\u0004\b<\u0010 R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u00104\u001a\u0004\b=\u0010 R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010>\u001a\u0004\b?\u0010'R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u00104\u001a\u0004\b@\u0010 R\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u00104\u001a\u0004\bA\u0010 R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010B\u001a\u0004\b\u000e\u0010+¨\u0006E"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "", "", "reserveNumber", "Ljava/time/ZonedDateTime;", "startTime", "endTime", "title", "phone", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "place", "sourcePackage", "type", "", "isAugmented", "<init>", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;Ljava/lang/String;Z)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;Ljava/lang/String;ZLkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Ljava/time/ZonedDateTime;", "component3", "component4", "component5", "component6", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "component7", "component8", "component9", "()Z", "copy", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getReserveNumber", "Ljava/time/ZonedDateTime;", "getStartTime", "getStartTime$annotations", "()V", "getEndTime", "getEndTime$annotations", "getTitle", "getPhone", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "getPlace", "getSourcePackage", "getType", "Z", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ReservationInternal {
    public static final Companion Companion = new Companion((e) null);
    private final ZonedDateTime endTime;
    private final boolean isAugmented;
    private final String phone;
    private final PlaceInternal place;
    private final String reserveNumber;
    private final String sourcePackage;
    private final ZonedDateTime startTime;
    private final String title;
    private final String type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return ReservationInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ ReservationInternal(int i2, String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, String str5, boolean z, a0 a0Var) {
        if (511 == (i2 & 511)) {
            this.reserveNumber = str;
            this.startTime = zonedDateTime;
            this.endTime = zonedDateTime2;
            this.title = str2;
            this.phone = str3;
            this.place = placeInternal;
            this.sourcePackage = str4;
            this.type = str5;
            this.isAugmented = z;
            return;
        }
        Q.f(i2, 511, ReservationInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ ReservationInternal copy$default(ReservationInternal reservationInternal, String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, String str5, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = reservationInternal.reserveNumber;
        }
        if ((i2 & 2) != 0) {
            zonedDateTime = reservationInternal.startTime;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime2 = reservationInternal.endTime;
        }
        if ((i2 & 8) != 0) {
            str2 = reservationInternal.title;
        }
        if ((i2 & 16) != 0) {
            str3 = reservationInternal.phone;
        }
        if ((i2 & 32) != 0) {
            placeInternal = reservationInternal.place;
        }
        if ((i2 & 64) != 0) {
            str4 = reservationInternal.sourcePackage;
        }
        if ((i2 & 128) != 0) {
            str5 = reservationInternal.type;
        }
        if ((i2 & 256) != 0) {
            z = reservationInternal.isAugmented;
        }
        String str6 = str5;
        boolean z3 = z;
        PlaceInternal placeInternal2 = placeInternal;
        String str7 = str4;
        String str8 = str2;
        String str9 = str3;
        return reservationInternal.copy(str, zonedDateTime, zonedDateTime2, str8, str9, placeInternal2, str7, str6, z3);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(ReservationInternal reservationInternal, b bVar, f fVar) {
        k kVar = (k) bVar;
        kVar.u(fVar, 0, reservationInternal.reserveNumber);
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        kVar.t(fVar, 1, zonedDateTimeSerializer, reservationInternal.startTime);
        kVar.t(fVar, 2, zonedDateTimeSerializer, reservationInternal.endTime);
        kVar.u(fVar, 3, reservationInternal.title);
        kVar.c(fVar, 4, e0.f4693a, reservationInternal.phone);
        kVar.t(fVar, 5, PlaceInternal$$serializer.INSTANCE, reservationInternal.place);
        kVar.u(fVar, 6, reservationInternal.sourcePackage);
        kVar.u(fVar, 7, reservationInternal.type);
        kVar.o(fVar, 8, reservationInternal.isAugmented);
    }

    public final String component1() {
        return this.reserveNumber;
    }

    public final ZonedDateTime component2() {
        return this.startTime;
    }

    public final ZonedDateTime component3() {
        return this.endTime;
    }

    public final String component4() {
        return this.title;
    }

    public final String component5() {
        return this.phone;
    }

    public final PlaceInternal component6() {
        return this.place;
    }

    public final String component7() {
        return this.sourcePackage;
    }

    public final String component8() {
        return this.type;
    }

    public final boolean component9() {
        return this.isAugmented;
    }

    public final ReservationInternal copy(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, String str5, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str2, "title");
        PlaceInternal placeInternal2 = placeInternal;
        j.e(placeInternal2, "place");
        String str6 = str4;
        j.e(str6, "sourcePackage");
        String str7 = str5;
        j.e(str7, "type");
        return new ReservationInternal(str, zonedDateTime, zonedDateTime2, str2, str3, placeInternal2, str6, str7, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReservationInternal)) {
            return false;
        }
        ReservationInternal reservationInternal = (ReservationInternal) obj;
        if (j.a(this.reserveNumber, reservationInternal.reserveNumber) && j.a(this.startTime, reservationInternal.startTime) && j.a(this.endTime, reservationInternal.endTime) && j.a(this.title, reservationInternal.title) && j.a(this.phone, reservationInternal.phone) && j.a(this.place, reservationInternal.place) && j.a(this.sourcePackage, reservationInternal.sourcePackage) && j.a(this.type, reservationInternal.type) && this.isAugmented == reservationInternal.isAugmented) {
            return true;
        }
        return false;
    }

    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final PlaceInternal getPlace() {
        return this.place;
    }

    public final String getReserveNumber() {
        return this.reserveNumber;
    }

    public final String getSourcePackage() {
        return this.sourcePackage;
    }

    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.startTime.hashCode();
        int d = C0212a.d((this.endTime.hashCode() + ((hashCode + (this.reserveNumber.hashCode() * 31)) * 31)) * 31, 31, this.title);
        String str = this.phone;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return Boolean.hashCode(this.isAugmented) + C0212a.d(C0212a.d((this.place.hashCode() + ((d + i2) * 31)) * 31, 31, this.sourcePackage), 31, this.type);
    }

    public final boolean isAugmented() {
        return this.isAugmented;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ReservationInternal(reserveNumber=");
        sb2.append(this.reserveNumber);
        sb2.append(", startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", title=");
        sb2.append(this.title);
        sb2.append(", phone=");
        sb2.append(this.phone);
        sb2.append(", place=");
        sb2.append(this.place);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", isAugmented=");
        return C0086a.n(sb2, this.isAugmented, ')');
    }

    public ReservationInternal(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, String str5, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str2, "title");
        j.e(placeInternal, "place");
        j.e(str4, "sourcePackage");
        j.e(str5, "type");
        this.reserveNumber = str;
        this.startTime = zonedDateTime;
        this.endTime = zonedDateTime2;
        this.title = str2;
        this.phone = str3;
        this.place = placeInternal;
        this.sourcePackage = str4;
        this.type = str5;
        this.isAugmented = z;
    }

    public static /* synthetic */ void getEndTime$annotations() {
    }

    public static /* synthetic */ void getStartTime$annotations() {
    }
}
