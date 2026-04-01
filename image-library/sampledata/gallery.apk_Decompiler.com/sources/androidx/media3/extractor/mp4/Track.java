package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Track {
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final Format format;
    public final int id;
    public final long mediaDurationUs;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    private final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final int sampleTransformation;
    public final long timescale;
    public final int type;

    public Track(int i2, int i7, long j2, long j3, long j8, long j10, Format format2, int i8, TrackEncryptionBox[] trackEncryptionBoxArr, int i10, long[] jArr, long[] jArr2) {
        this.id = i2;
        this.type = i7;
        this.timescale = j2;
        this.movieTimescale = j3;
        this.durationUs = j8;
        this.mediaDurationUs = j10;
        this.format = format2;
        this.sampleTransformation = i8;
        this.sampleDescriptionEncryptionBoxes = trackEncryptionBoxArr;
        this.nalUnitLengthFieldLength = i10;
        this.editListDurations = jArr;
        this.editListMediaTimes = jArr2;
    }

    public Track copyWithFormat(Format format2) {
        return new Track(this.id, this.type, this.timescale, this.movieTimescale, this.durationUs, this.mediaDurationUs, format2, this.sampleTransformation, this.sampleDescriptionEncryptionBoxes, this.nalUnitLengthFieldLength, this.editListDurations, this.editListMediaTimes);
    }

    public TrackEncryptionBox getSampleDescriptionEncryptionBox(int i2) {
        TrackEncryptionBox[] trackEncryptionBoxArr = this.sampleDescriptionEncryptionBoxes;
        if (trackEncryptionBoxArr == null) {
            return null;
        }
        return trackEncryptionBoxArr[i2];
    }
}
