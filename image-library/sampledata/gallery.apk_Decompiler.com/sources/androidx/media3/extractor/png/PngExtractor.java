package androidx.media3.extractor.png;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PngExtractor implements Extractor {
    private final SingleSampleExtractor imageExtractor = new SingleSampleExtractor(35152, 2, "image/png");

    public void init(ExtractorOutput extractorOutput) {
        this.imageExtractor.init(extractorOutput);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        return this.imageExtractor.read(extractorInput, positionHolder);
    }

    public void seek(long j2, long j3) {
        this.imageExtractor.seek(j2, j3);
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return this.imageExtractor.sniff(extractorInput);
    }

    public void release() {
    }
}
