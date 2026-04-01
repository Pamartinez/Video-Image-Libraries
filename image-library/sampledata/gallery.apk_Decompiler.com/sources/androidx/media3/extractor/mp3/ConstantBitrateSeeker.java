package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataEndPosition;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long j2, long j3, MpegAudioUtil.Header header, boolean z) {
        this(j2, j3, header.bitrate, header.frameSize, z);
    }

    public ConstantBitrateSeeker copyWithNewDataEndPosition(long j2) {
        return new ConstantBitrateSeeker(j2, this.firstFramePosition, this.bitrate, this.frameSize, this.allowSeeksIfLengthUnknown);
    }

    public int getAverageBitrate() {
        return this.bitrate;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDataStartPosition() {
        return this.firstFramePosition;
    }

    public long getTimeUs(long j2) {
        return getTimeUsAtPosition(j2);
    }

    public ConstantBitrateSeeker(long j2, long j3, int i2, int i7, boolean z) {
        super(j2, j3, i2, i7, z);
        this.firstFramePosition = j3;
        this.bitrate = i2;
        this.frameSize = i7;
        this.allowSeeksIfLengthUnknown = z;
        this.dataEndPosition = j2 == -1 ? -1 : j2;
    }
}
