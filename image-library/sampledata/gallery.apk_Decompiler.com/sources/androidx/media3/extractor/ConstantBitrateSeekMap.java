package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConstantBitrateSeekMap implements SeekMap {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFrameBytePosition;
    private final int frameSize;
    private final long inputLength;

    public ConstantBitrateSeekMap(long j2, long j3, int i2, int i7, boolean z) {
        this.inputLength = j2;
        this.firstFrameBytePosition = j3;
        this.frameSize = i7 == -1 ? 1 : i7;
        this.bitrate = i2;
        this.allowSeeksIfLengthUnknown = z;
        if (j2 == -1) {
            this.dataSize = -1;
            this.durationUs = -9223372036854775807L;
            return;
        }
        this.dataSize = j2 - j3;
        this.durationUs = getTimeUsAtPosition(j2, j3, i2);
    }

    private long getFramePositionForTimeUs(long j2) {
        int i2 = this.frameSize;
        long j3 = (((j2 * ((long) this.bitrate)) / 8000000) / ((long) i2)) * ((long) i2);
        long j8 = this.dataSize;
        if (j8 != -1) {
            j3 = Math.min(j3, j8 - ((long) i2));
        }
        return this.firstFrameBytePosition + Math.max(j3, 0);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        if (this.dataSize == -1 && !this.allowSeeksIfLengthUnknown) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.firstFrameBytePosition));
        }
        long framePositionForTimeUs = getFramePositionForTimeUs(j2);
        long timeUsAtPosition = getTimeUsAtPosition(framePositionForTimeUs);
        SeekPoint seekPoint = new SeekPoint(timeUsAtPosition, framePositionForTimeUs);
        if (this.dataSize != -1 && timeUsAtPosition < j2) {
            int i2 = this.frameSize;
            if (((long) i2) + framePositionForTimeUs < this.inputLength) {
                long j3 = framePositionForTimeUs + ((long) i2);
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUsAtPosition(j3), j3));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public long getTimeUsAtPosition(long j2) {
        return getTimeUsAtPosition(j2, this.firstFrameBytePosition, this.bitrate);
    }

    public boolean isSeekable() {
        if (this.dataSize != -1 || this.allowSeeksIfLengthUnknown) {
            return true;
        }
        return false;
    }

    private static long getTimeUsAtPosition(long j2, long j3, int i2) {
        return (Math.max(0, j2 - j3) * 8000000) / ((long) i2);
    }
}
