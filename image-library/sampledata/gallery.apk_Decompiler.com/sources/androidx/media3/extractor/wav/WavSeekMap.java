package androidx.media3.extractor.wav;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WavSeekMap implements SeekMap {
    private final long blockCount;
    private final long durationUs;
    private final long firstBlockPosition;
    private final int framesPerBlock;
    private final WavFormat wavFormat;

    public WavSeekMap(WavFormat wavFormat2, int i2, long j2, long j3) {
        this.wavFormat = wavFormat2;
        this.framesPerBlock = i2;
        this.firstBlockPosition = j2;
        long j8 = (j3 - j2) / ((long) wavFormat2.blockSize);
        this.blockCount = j8;
        this.durationUs = blockIndexToTimeUs(j8);
    }

    private long blockIndexToTimeUs(long j2) {
        return Util.scaleLargeTimestamp(j2 * ((long) this.framesPerBlock), 1000000, (long) this.wavFormat.frameRateHz);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        long constrainValue = Util.constrainValue((((long) this.wavFormat.frameRateHz) * j2) / (((long) this.framesPerBlock) * 1000000), 0, this.blockCount - 1);
        long j3 = (((long) this.wavFormat.blockSize) * constrainValue) + this.firstBlockPosition;
        long blockIndexToTimeUs = blockIndexToTimeUs(constrainValue);
        SeekPoint seekPoint = new SeekPoint(blockIndexToTimeUs, j3);
        if (blockIndexToTimeUs >= j2 || constrainValue == this.blockCount - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        long j8 = constrainValue + 1;
        long j10 = this.firstBlockPosition;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(blockIndexToTimeUs(j8), (((long) this.wavFormat.blockSize) * j8) + j10));
    }

    public boolean isSeekable() {
        return true;
    }
}
