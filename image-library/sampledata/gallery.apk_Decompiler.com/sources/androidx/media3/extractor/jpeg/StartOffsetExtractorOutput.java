package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StartOffsetExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput extractorOutput;
    /* access modifiers changed from: private */
    public final long startOffset;

    public StartOffsetExtractorOutput(long j2, ExtractorOutput extractorOutput2) {
        this.startOffset = j2;
        this.extractorOutput = extractorOutput2;
    }

    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    public void seekMap(final SeekMap seekMap) {
        this.extractorOutput.seekMap(new ForwardingSeekMap(seekMap) {
            public SeekMap.SeekPoints getSeekPoints(long j2) {
                SeekMap.SeekPoints seekPoints = seekMap.getSeekPoints(j2);
                SeekPoint seekPoint = seekPoints.first;
                SeekPoint seekPoint2 = new SeekPoint(seekPoint.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint.position);
                SeekPoint seekPoint3 = seekPoints.second;
                return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(seekPoint3.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint3.position));
            }
        });
    }

    public TrackOutput track(int i2, int i7) {
        return this.extractorOutput.track(i2, i7);
    }
}
