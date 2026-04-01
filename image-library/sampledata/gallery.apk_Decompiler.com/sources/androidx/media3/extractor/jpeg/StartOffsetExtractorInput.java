package androidx.media3.extractor.jpeg;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ForwardingExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StartOffsetExtractorInput extends ForwardingExtractorInput {
    private final long startOffset;

    public StartOffsetExtractorInput(ExtractorInput extractorInput, long j2) {
        super(extractorInput);
        boolean z;
        if (extractorInput.getPosition() >= j2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.startOffset = j2;
    }

    public long getLength() {
        return super.getLength() - this.startOffset;
    }

    public long getPeekPosition() {
        return super.getPeekPosition() - this.startOffset;
    }

    public long getPosition() {
        return super.getPosition() - this.startOffset;
    }
}
