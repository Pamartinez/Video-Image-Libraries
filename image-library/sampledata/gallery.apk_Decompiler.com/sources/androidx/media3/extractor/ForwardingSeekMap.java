package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ForwardingSeekMap implements SeekMap {
    private final SeekMap seekMap;

    public ForwardingSeekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
    }

    public long getDurationUs() {
        return this.seekMap.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        return this.seekMap.getSeekPoints(j2);
    }

    public boolean isSeekable() {
        return this.seekMap.isSeekable();
    }
}
