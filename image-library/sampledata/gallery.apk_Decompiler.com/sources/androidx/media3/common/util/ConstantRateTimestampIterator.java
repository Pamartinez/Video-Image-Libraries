package androidx.media3.common.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConstantRateTimestampIterator implements TimestampIterator {
    private final long endPositionUs;
    private final float frameRate;
    private int framesAdded;
    private final double framesDurationUs;
    private final long startPositionUs;
    private final int totalNumberOfFramesToAdd;

    public ConstantRateTimestampIterator(long j2, float f) {
        this(0, j2, f);
    }

    private long getTimestampUsAfter(int i2) {
        boolean z;
        long round = Math.round(this.framesDurationUs * ((double) i2)) + this.startPositionUs;
        if (round >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        return round;
    }

    public boolean hasNext() {
        if (this.framesAdded < this.totalNumberOfFramesToAdd) {
            return true;
        }
        return false;
    }

    public long next() {
        Assertions.checkState(hasNext());
        int i2 = this.framesAdded;
        this.framesAdded = i2 + 1;
        return getTimestampUsAfter(i2);
    }

    public ConstantRateTimestampIterator(long j2, long j3, float f) {
        boolean z = false;
        Assertions.checkArgument(j3 > 0);
        Assertions.checkArgument(f > 0.0f);
        if (0 <= j2 && j2 < j3) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.startPositionUs = j2;
        this.endPositionUs = j3;
        this.frameRate = f;
        this.totalNumberOfFramesToAdd = Math.max(Math.round((((float) (j3 - j2)) / 1000000.0f) * f), 1);
        this.framesDurationUs = (double) (1000000.0f / f);
    }

    public ConstantRateTimestampIterator copyOf() {
        return new ConstantRateTimestampIterator(this.startPositionUs, this.endPositionUs, this.frameRate);
    }
}
