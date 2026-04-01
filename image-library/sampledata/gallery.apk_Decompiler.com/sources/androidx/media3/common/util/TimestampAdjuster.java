package androidx.media3.common.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TimestampAdjuster {
    private long firstSampleTimestampUs;
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    private long timestampOffsetUs;

    public TimestampAdjuster(long j2) {
        reset(j2);
    }

    public static long ptsToUs(long j2) {
        return Util.scaleLargeTimestamp(j2, 1000000, 90000);
    }

    public static long usToNonWrappedPts(long j2) {
        return Util.scaleLargeTimestamp(j2, 90000, 1000000);
    }

    public synchronized long adjustSampleTimestamp(long j2) {
        if (j2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            if (!isInitialized()) {
                long j3 = this.firstSampleTimestampUs;
                if (j3 == 9223372036854775806L) {
                    j3 = ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue();
                }
                this.timestampOffsetUs = j3 - j2;
                notifyAll();
            }
            this.lastUnadjustedTimestampUs = j2;
            return j2 + this.timestampOffsetUs;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long adjustTsTimestamp(long j2) {
        if (j2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            long j3 = this.lastUnadjustedTimestampUs;
            if (j3 != -9223372036854775807L) {
                long usToNonWrappedPts = usToNonWrappedPts(j3);
                long j8 = (4294967296L + usToNonWrappedPts) / 8589934592L;
                long j10 = ((j8 - 1) * 8589934592L) + j2;
                long j11 = (j8 * 8589934592L) + j2;
                if (Math.abs(j10 - usToNonWrappedPts) < Math.abs(j11 - usToNonWrappedPts)) {
                    j2 = j10;
                } else {
                    j2 = j11;
                }
            }
            return adjustSampleTimestamp(ptsToUs(j2));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long adjustTsTimestampGreaterThanPreviousTimestamp(long j2) {
        if (j2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            long j3 = this.lastUnadjustedTimestampUs;
            if (j3 != -9223372036854775807L) {
                long usToNonWrappedPts = usToNonWrappedPts(j3);
                long j8 = usToNonWrappedPts / 8589934592L;
                long j10 = (j8 * 8589934592L) + j2;
                long j11 = ((j8 + 1) * 8589934592L) + j2;
                if (j10 >= usToNonWrappedPts) {
                    j2 = j10;
                } else {
                    j2 = j11;
                }
            }
            return adjustSampleTimestamp(ptsToUs(j2));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j2;
        j2 = this.firstSampleTimestampUs;
        if (j2 == Long.MAX_VALUE || j2 == 9223372036854775806L) {
            j2 = -9223372036854775807L;
        }
        return j2;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long j2;
        try {
            long j3 = this.lastUnadjustedTimestampUs;
            if (j3 != -9223372036854775807L) {
                j2 = j3 + this.timestampOffsetUs;
            } else {
                j2 = getFirstSampleTimestampUs();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return j2;
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized boolean isInitialized() {
        boolean z;
        if (this.timestampOffsetUs != -9223372036854775807L) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void reset(long j2) {
        long j3;
        this.firstSampleTimestampUs = j2;
        if (j2 == Long.MAX_VALUE) {
            j3 = 0;
        } else {
            j3 = -9223372036854775807L;
        }
        this.timestampOffsetUs = j3;
        this.lastUnadjustedTimestampUs = -9223372036854775807L;
    }
}
