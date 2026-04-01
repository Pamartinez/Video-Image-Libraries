package androidx.media3.extractor;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ChunkIndex implements SeekMap {
    private final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.sizes = iArr;
        this.offsets = jArr;
        this.durationsUs = jArr2;
        this.timesUs = jArr3;
        int length2 = iArr.length;
        this.length = length2;
        if (length2 > 0) {
            this.durationUs = jArr2[length2 - 1] + jArr3[length2 - 1];
        } else {
            this.durationUs = 0;
        }
    }

    public int getChunkIndex(long j2) {
        return Util.binarySearchFloor(this.timesUs, j2, true, true);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        int chunkIndex = getChunkIndex(j2);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[chunkIndex], this.offsets[chunkIndex]);
        if (seekPoint.timeUs >= j2 || chunkIndex == this.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i2 = chunkIndex + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i2], this.offsets[i2]));
    }

    public boolean isSeekable() {
        return true;
    }

    public String toString() {
        return "ChunkIndex(length=" + this.length + ", sizes=" + Arrays.toString(this.sizes) + ", offsets=" + Arrays.toString(this.offsets) + ", timeUs=" + Arrays.toString(this.timesUs) + ", durationsUs=" + Arrays.toString(this.durationsUs) + ")";
    }
}
