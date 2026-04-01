package com.samsung.android.sdk.moneta.travel.internal.model;

import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
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

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\b\b\u0018\u0000 @2\u00020\u0001:\u0002A@BI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fBi\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0014J'\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010!J\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u001fJ\u0012\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b$\u0010\u001fJ\u0010\u0010%\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b'\u0010\u001fJ\u0010\u0010(\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b(\u0010)Jb\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b,\u0010\u001fJ\u0010\u0010-\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b-\u0010.J\u001a\u00100\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b0\u00101R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00102\u001a\u0004\b3\u0010\u001fR \u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u00104\u0012\u0004\b6\u00107\u001a\u0004\b5\u0010!R \u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0006\u00104\u0012\u0004\b9\u00107\u001a\u0004\b8\u0010!R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00102\u001a\u0004\b:\u0010\u001fR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u00102\u001a\u0004\b;\u0010\u001fR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010<\u001a\u0004\b=\u0010&R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u00102\u001a\u0004\b>\u0010\u001fR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010?\u001a\u0004\b\r\u0010)¨\u0006B"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "", "", "reserveNumber", "Ljava/time/ZonedDateTime;", "checkInTime", "checkOutTime", "title", "phone", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "place", "sourcePackage", "", "isAugmented", "<init>", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;Z)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;ZLkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Ljava/time/ZonedDateTime;", "component3", "component4", "component5", "component6", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "component7", "component8", "()Z", "copy", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getReserveNumber", "Ljava/time/ZonedDateTime;", "getCheckInTime", "getCheckInTime$annotations", "()V", "getCheckOutTime", "getCheckOutTime$annotations", "getTitle", "getPhone", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "getPlace", "getSourcePackage", "Z", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AccommodationInternal {
    public static final Companion Companion = new Companion((e) null);
    private final ZonedDateTime checkInTime;
    private final ZonedDateTime checkOutTime;
    private final boolean isAugmented;
    private final String phone;
    private final PlaceInternal place;
    private final String reserveNumber;
    private final String sourcePackage;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return AccommodationInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ AccommodationInternal(int i2, String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, boolean z, a0 a0Var) {
        if (255 == (i2 & ScoverState.TYPE_NFC_SMART_COVER)) {
            this.reserveNumber = str;
            this.checkInTime = zonedDateTime;
            this.checkOutTime = zonedDateTime2;
            this.title = str2;
            this.phone = str3;
            this.place = placeInternal;
            this.sourcePackage = str4;
            this.isAugmented = z;
            return;
        }
        Q.f(i2, ScoverState.TYPE_NFC_SMART_COVER, AccommodationInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ AccommodationInternal copy$default(AccommodationInternal accommodationInternal, String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = accommodationInternal.reserveNumber;
        }
        if ((i2 & 2) != 0) {
            zonedDateTime = accommodationInternal.checkInTime;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime2 = accommodationInternal.checkOutTime;
        }
        if ((i2 & 8) != 0) {
            str2 = accommodationInternal.title;
        }
        if ((i2 & 16) != 0) {
            str3 = accommodationInternal.phone;
        }
        if ((i2 & 32) != 0) {
            placeInternal = accommodationInternal.place;
        }
        if ((i2 & 64) != 0) {
            str4 = accommodationInternal.sourcePackage;
        }
        if ((i2 & 128) != 0) {
            z = accommodationInternal.isAugmented;
        }
        String str5 = str4;
        boolean z3 = z;
        String str6 = str3;
        PlaceInternal placeInternal2 = placeInternal;
        String str7 = str2;
        ZonedDateTime zonedDateTime3 = zonedDateTime;
        return accommodationInternal.copy(str, zonedDateTime3, zonedDateTime2, str7, str6, placeInternal2, str5, z3);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(AccommodationInternal accommodationInternal, b bVar, f fVar) {
        k kVar = (k) bVar;
        kVar.u(fVar, 0, accommodationInternal.reserveNumber);
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        kVar.t(fVar, 1, zonedDateTimeSerializer, accommodationInternal.checkInTime);
        kVar.t(fVar, 2, zonedDateTimeSerializer, accommodationInternal.checkOutTime);
        kVar.u(fVar, 3, accommodationInternal.title);
        kVar.c(fVar, 4, e0.f4693a, accommodationInternal.phone);
        kVar.t(fVar, 5, PlaceInternal$$serializer.INSTANCE, accommodationInternal.place);
        kVar.u(fVar, 6, accommodationInternal.sourcePackage);
        kVar.o(fVar, 7, accommodationInternal.isAugmented);
    }

    public final String component1() {
        return this.reserveNumber;
    }

    public final ZonedDateTime component2() {
        return this.checkInTime;
    }

    public final ZonedDateTime component3() {
        return this.checkOutTime;
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

    public final boolean component8() {
        return this.isAugmented;
    }

    public final AccommodationInternal copy(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "checkInTime");
        j.e(zonedDateTime2, "checkOutTime");
        j.e(str2, "title");
        j.e(placeInternal, "place");
        String str5 = str4;
        j.e(str5, "sourcePackage");
        return new AccommodationInternal(str, zonedDateTime, zonedDateTime2, str2, str3, placeInternal, str5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccommodationInternal)) {
            return false;
        }
        AccommodationInternal accommodationInternal = (AccommodationInternal) obj;
        if (j.a(this.reserveNumber, accommodationInternal.reserveNumber) && j.a(this.checkInTime, accommodationInternal.checkInTime) && j.a(this.checkOutTime, accommodationInternal.checkOutTime) && j.a(this.title, accommodationInternal.title) && j.a(this.phone, accommodationInternal.phone) && j.a(this.place, accommodationInternal.place) && j.a(this.sourcePackage, accommodationInternal.sourcePackage) && this.isAugmented == accommodationInternal.isAugmented) {
            return true;
        }
        return false;
    }

    public final ZonedDateTime getCheckInTime() {
        return this.checkInTime;
    }

    public final ZonedDateTime getCheckOutTime() {
        return this.checkOutTime;
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

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.checkInTime.hashCode();
        int d = C0212a.d((this.checkOutTime.hashCode() + ((hashCode + (this.reserveNumber.hashCode() * 31)) * 31)) * 31, 31, this.title);
        String str = this.phone;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return Boolean.hashCode(this.isAugmented) + C0212a.d((this.place.hashCode() + ((d + i2) * 31)) * 31, 31, this.sourcePackage);
    }

    public final boolean isAugmented() {
        return this.isAugmented;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AccommodationInternal(reserveNumber=");
        sb2.append(this.reserveNumber);
        sb2.append(", checkInTime=");
        sb2.append(this.checkInTime);
        sb2.append(", checkOutTime=");
        sb2.append(this.checkOutTime);
        sb2.append(", title=");
        sb2.append(this.title);
        sb2.append(", phone=");
        sb2.append(this.phone);
        sb2.append(", place=");
        sb2.append(this.place);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", isAugmented=");
        return C0086a.n(sb2, this.isAugmented, ')');
    }

    public AccommodationInternal(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, PlaceInternal placeInternal, String str4, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "checkInTime");
        j.e(zonedDateTime2, "checkOutTime");
        j.e(str2, "title");
        j.e(placeInternal, "place");
        j.e(str4, "sourcePackage");
        this.reserveNumber = str;
        this.checkInTime = zonedDateTime;
        this.checkOutTime = zonedDateTime2;
        this.title = str2;
        this.phone = str3;
        this.place = placeInternal;
        this.sourcePackage = str4;
        this.isAugmented = z;
    }

    public static /* synthetic */ void getCheckInTime$annotations() {
    }

    public static /* synthetic */ void getCheckOutTime$annotations() {
    }
}
