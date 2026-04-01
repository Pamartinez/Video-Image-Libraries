package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleSampleSeekMap implements SeekMap {
    private final long durationUs;
    private final long startPosition;

    public SingleSampleSeekMap(long j2) {
        this(j2, 0);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        return new SeekMap.SeekPoints(new SeekPoint(j2, this.startPosition));
    }

    public boolean isSeekable() {
        return true;
    }

    public SingleSampleSeekMap(long j2, long j3) {
        this.durationUs = j2;
        this.startPosition = j3;
    }
}
