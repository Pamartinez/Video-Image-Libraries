package com.samsung.android.sdk.moneta.lifestyle.common;

import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "", "startTime", "", "endTime", "<init>", "(JJ)V", "getStartTime", "()J", "getEndTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TimeRange {
    private final long endTime;
    private final long startTime;

    public TimeRange(long j2, long j3) {
        this.startTime = j2;
        this.endTime = j3;
    }

    public static /* synthetic */ TimeRange copy$default(TimeRange timeRange, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = timeRange.startTime;
        }
        if ((i2 & 2) != 0) {
            j3 = timeRange.endTime;
        }
        return timeRange.copy(j2, j3);
    }

    public final long component1() {
        return this.startTime;
    }

    public final long component2() {
        return this.endTime;
    }

    public final TimeRange copy(long j2, long j3) {
        return new TimeRange(j2, j3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeRange)) {
            return false;
        }
        TimeRange timeRange = (TimeRange) obj;
        if (this.startTime == timeRange.startTime && this.endTime == timeRange.endTime) {
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

    public int hashCode() {
        return Long.hashCode(this.endTime) + (Long.hashCode(this.startTime) * 31);
    }

    public String toString() {
        return "TimeRange(startTime=" + this.startTime + ", endTime=" + this.endTime + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TimeRange(long j2, long j3, int i2, e eVar) {
        this(j2, (i2 & 2) != 0 ? System.currentTimeMillis() : j3);
    }
}
