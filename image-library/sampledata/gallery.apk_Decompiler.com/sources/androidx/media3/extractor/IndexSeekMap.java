package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IndexSeekMap implements SeekMap {
    private long durationUs;
    private final LongArray positions;
    private final LongArray timesUs;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j2) {
        boolean z;
        if (jArr.length == jArr2.length) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        int length = jArr2.length;
        if (length <= 0 || jArr2[0] <= 0) {
            this.positions = new LongArray(length);
            this.timesUs = new LongArray(length);
        } else {
            int i2 = length + 1;
            LongArray longArray = new LongArray(i2);
            this.positions = longArray;
            LongArray longArray2 = new LongArray(i2);
            this.timesUs = longArray2;
            longArray.add(0);
            longArray2.add(0);
        }
        this.positions.addAll(jArr);
        this.timesUs.addAll(jArr2);
        this.durationUs = j2;
    }

    public void addSeekPoint(long j2, long j3) {
        if (this.timesUs.size() == 0 && j2 > 0) {
            this.positions.add(0);
            this.timesUs.add(0);
        }
        this.positions.add(j3);
        this.timesUs.add(j2);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        if (this.timesUs.size() == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j2, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs.get(binarySearchFloor), this.positions.get(binarySearchFloor));
        if (seekPoint.timeUs == j2 || binarySearchFloor == this.timesUs.size() - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i2 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs.get(i2), this.positions.get(i2)));
    }

    public long getTimeUs(long j2) {
        if (this.timesUs.size() == 0) {
            return -9223372036854775807L;
        }
        return this.timesUs.get(Util.binarySearchFloor(this.positions, j2, true, true));
    }

    public boolean isSeekable() {
        if (this.timesUs.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isTimeUsInIndex(long j2, long j3) {
        if (this.timesUs.size() == 0) {
            return false;
        }
        LongArray longArray = this.timesUs;
        if (j2 - longArray.get(longArray.size() - 1) < j3) {
            return true;
        }
        return false;
    }

    public void setDurationUs(long j2) {
        this.durationUs = j2;
    }
}
