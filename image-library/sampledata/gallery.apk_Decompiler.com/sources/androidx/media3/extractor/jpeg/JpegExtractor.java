package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JpegExtractor implements Extractor {
    private final Extractor extractor;

    public JpegExtractor(int i2) {
        if ((i2 & 1) != 0) {
            this.extractor = new SingleSampleExtractor(65496, 2, "image/jpeg");
        } else {
            this.extractor = new JpegMotionPhotoExtractor();
        }
    }

    public void init(ExtractorOutput extractorOutput) {
        this.extractor.init(extractorOutput);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        return this.extractor.read(extractorInput, positionHolder);
    }

    public void release() {
        this.extractor.release();
    }

    public void seek(long j2, long j3) {
        this.extractor.seek(j2, j3);
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return this.extractor.sniff(extractorInput);
    }
}
