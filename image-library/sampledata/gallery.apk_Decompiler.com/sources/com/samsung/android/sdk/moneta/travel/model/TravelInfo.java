package com.samsung.android.sdk.moneta.travel.model;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JB\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0002\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013¨\u0006 "}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;", "", "isDomestic", "", "startTime", "", "endTime", "travelDuration", "", "isAugmented", "<init>", "(Ljava/lang/Boolean;JJIZ)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getStartTime", "()J", "getEndTime", "getTravelDuration", "()I", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Boolean;JJIZ)Lcom/samsung/android/sdk/moneta/travel/model/TravelInfo;", "equals", "other", "hashCode", "toString", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelInfo {
    private final long endTime;
    private final boolean isAugmented;
    private final Boolean isDomestic;
    private final long startTime;
    private final int travelDuration;

    public TravelInfo(Boolean bool, long j2, long j3, int i2, boolean z) {
        this.isDomestic = bool;
        this.startTime = j2;
        this.endTime = j3;
        this.travelDuration = i2;
        this.isAugmented = z;
    }

    public static /* synthetic */ TravelInfo copy$default(TravelInfo travelInfo, Boolean bool, long j2, long j3, int i2, boolean z, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            bool = travelInfo.isDomestic;
        }
        if ((i7 & 2) != 0) {
            j2 = travelInfo.startTime;
        }
        if ((i7 & 4) != 0) {
            j3 = travelInfo.endTime;
        }
        if ((i7 & 8) != 0) {
            i2 = travelInfo.travelDuration;
        }
        if ((i7 & 16) != 0) {
            z = travelInfo.isAugmented;
        }
        long j8 = j3;
        long j10 = j2;
        return travelInfo.copy(bool, j10, j8, i2, z);
    }

    public final Boolean component1() {
        return this.isDomestic;
    }

    public final long component2() {
        return this.startTime;
    }

    public final long component3() {
        return this.endTime;
    }

    public final int component4() {
        return this.travelDuration;
    }

    public final boolean component5() {
        return this.isAugmented;
    }

    public final TravelInfo copy(Boolean bool, long j2, long j3, int i2, boolean z) {
        return new TravelInfo(bool, j2, j3, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelInfo)) {
            return false;
        }
        TravelInfo travelInfo = (TravelInfo) obj;
        if (j.a(this.isDomestic, travelInfo.isDomestic) && this.startTime == travelInfo.startTime && this.endTime == travelInfo.endTime && this.travelDuration == travelInfo.travelDuration && this.isAugmented == travelInfo.isAugmented) {
            return true;
        }
        return false;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final int getTravelDuration() {
        return this.travelDuration;
    }

    public int hashCode() {
        int i2;
        Boolean bool = this.isDomestic;
        if (bool == null) {
            i2 = 0;
        } else {
            i2 = bool.hashCode();
        }
        return Boolean.hashCode(this.isAugmented) + C0212a.b(this.travelDuration, C0212a.c(C0212a.c(i2 * 31, 31, this.startTime), 31, this.endTime), 31);
    }

    public final boolean isAugmented() {
        return this.isAugmented;
    }

    public final Boolean isDomestic() {
        return this.isDomestic;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TravelInfo(isDomestic=");
        sb2.append(this.isDomestic);
        sb2.append(", startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", travelDuration=");
        sb2.append(this.travelDuration);
        sb2.append(", isAugmented=");
        return C0086a.n(sb2, this.isAugmented, ')');
    }
}
