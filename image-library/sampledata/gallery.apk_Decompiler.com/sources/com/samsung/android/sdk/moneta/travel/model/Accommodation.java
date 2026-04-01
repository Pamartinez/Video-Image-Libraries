package com.samsung.android.sdk.moneta.travel.model;

import c0.C0086a;
import i.C0212a;
import java.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\nHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\rHÆ\u0003J[\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010$\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001a¨\u0006)"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/Accommodation;", "", "reserveNumber", "", "checkInTime", "Ljava/time/ZonedDateTime;", "checkOutTime", "title", "phone", "place", "Lcom/samsung/android/sdk/moneta/travel/model/Place;", "sourcePackage", "isAugmented", "", "<init>", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/travel/model/Place;Ljava/lang/String;Z)V", "getReserveNumber", "()Ljava/lang/String;", "getCheckInTime", "()Ljava/time/ZonedDateTime;", "getCheckOutTime", "getTitle", "getPhone", "getPlace", "()Lcom/samsung/android/sdk/moneta/travel/model/Place;", "getSourcePackage", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Accommodation {
    private final ZonedDateTime checkInTime;
    private final ZonedDateTime checkOutTime;
    private final boolean isAugmented;
    private final String phone;
    private final Place place;
    private final String reserveNumber;
    private final String sourcePackage;
    private final String title;

    public Accommodation(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, Place place2, String str4, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "checkInTime");
        j.e(zonedDateTime2, "checkOutTime");
        j.e(str2, "title");
        j.e(place2, "place");
        j.e(str4, "sourcePackage");
        this.reserveNumber = str;
        this.checkInTime = zonedDateTime;
        this.checkOutTime = zonedDateTime2;
        this.title = str2;
        this.phone = str3;
        this.place = place2;
        this.sourcePackage = str4;
        this.isAugmented = z;
    }

    public static /* synthetic */ Accommodation copy$default(Accommodation accommodation, String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, Place place2, String str4, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = accommodation.reserveNumber;
        }
        if ((i2 & 2) != 0) {
            zonedDateTime = accommodation.checkInTime;
        }
        if ((i2 & 4) != 0) {
            zonedDateTime2 = accommodation.checkOutTime;
        }
        if ((i2 & 8) != 0) {
            str2 = accommodation.title;
        }
        if ((i2 & 16) != 0) {
            str3 = accommodation.phone;
        }
        if ((i2 & 32) != 0) {
            place2 = accommodation.place;
        }
        if ((i2 & 64) != 0) {
            str4 = accommodation.sourcePackage;
        }
        if ((i2 & 128) != 0) {
            z = accommodation.isAugmented;
        }
        String str5 = str4;
        boolean z3 = z;
        String str6 = str3;
        Place place3 = place2;
        String str7 = str2;
        ZonedDateTime zonedDateTime3 = zonedDateTime;
        return accommodation.copy(str, zonedDateTime3, zonedDateTime2, str7, str6, place3, str5, z3);
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

    public final Place component6() {
        return this.place;
    }

    public final String component7() {
        return this.sourcePackage;
    }

    public final boolean component8() {
        return this.isAugmented;
    }

    public final Accommodation copy(String str, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str2, String str3, Place place2, String str4, boolean z) {
        j.e(str, "reserveNumber");
        j.e(zonedDateTime, "checkInTime");
        j.e(zonedDateTime2, "checkOutTime");
        j.e(str2, "title");
        j.e(place2, "place");
        String str5 = str4;
        j.e(str5, "sourcePackage");
        return new Accommodation(str, zonedDateTime, zonedDateTime2, str2, str3, place2, str5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Accommodation)) {
            return false;
        }
        Accommodation accommodation = (Accommodation) obj;
        if (j.a(this.reserveNumber, accommodation.reserveNumber) && j.a(this.checkInTime, accommodation.checkInTime) && j.a(this.checkOutTime, accommodation.checkOutTime) && j.a(this.title, accommodation.title) && j.a(this.phone, accommodation.phone) && j.a(this.place, accommodation.place) && j.a(this.sourcePackage, accommodation.sourcePackage) && this.isAugmented == accommodation.isAugmented) {
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

    public final Place getPlace() {
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
        StringBuilder sb2 = new StringBuilder("Accommodation(reserveNumber=");
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
}
