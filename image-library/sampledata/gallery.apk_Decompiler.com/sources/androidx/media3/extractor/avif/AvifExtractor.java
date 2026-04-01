package androidx.media3.extractor.avif;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;
import com.samsung.android.sdk.sgpl.media.iso.IsoInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AvifExtractor implements Extractor {
    private final SingleSampleExtractor imageExtractor = new SingleSampleExtractor(-1, -1, "image/avif");
    private final ParsableByteArray scratch = new ParsableByteArray(4);

    private boolean readAndCompareFourBytes(ExtractorInput extractorInput, int i2) {
        this.scratch.reset(4);
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() == ((long) i2)) {
            return true;
        }
        return false;
    }

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
        extractorInput.advancePeekPosition(4);
        if (!readAndCompareFourBytes(extractorInput, IsoInterface.BOX_FTYP) || !readAndCompareFourBytes(extractorInput, 1635150182)) {
            return false;
        }
        return true;
    }

    public void release() {
    }
}
