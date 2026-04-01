package androidx.media3.extractor.flac;

import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacStreamMetadata;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FlacBinarySearchSeeker extends BinarySearchSeeker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FlacTimestampSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final FlacStreamMetadata flacStreamMetadata;
        private final int frameStartMarker;
        private final FlacFrameReader.SampleNumberHolder sampleNumberHolder;

        private long findNextFrame(ExtractorInput extractorInput) {
            while (extractorInput.getPeekPosition() < extractorInput.getLength() - 6 && !FlacFrameReader.checkFrameHeaderFromPeek(extractorInput, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder)) {
                extractorInput.advancePeekPosition(1);
            }
            if (extractorInput.getPeekPosition() < extractorInput.getLength() - 6) {
                return this.sampleNumberHolder.sampleNumber;
            }
            extractorInput.advancePeekPosition((int) (extractorInput.getLength() - extractorInput.getPeekPosition()));
            return this.flacStreamMetadata.totalSamples;
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2) {
            long position = extractorInput.getPosition();
            long findNextFrame = findNextFrame(extractorInput);
            long peekPosition = extractorInput.getPeekPosition();
            extractorInput.advancePeekPosition(Math.max(6, this.flacStreamMetadata.minFrameSize));
            long findNextFrame2 = findNextFrame(extractorInput);
            long peekPosition2 = extractorInput.getPeekPosition();
            if (findNextFrame <= j2 && findNextFrame2 > j2) {
                return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(peekPosition);
            }
            if (findNextFrame2 <= j2) {
                return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(findNextFrame2, peekPosition2);
            }
            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(findNextFrame, position);
        }

        private FlacTimestampSeeker(FlacStreamMetadata flacStreamMetadata2, int i2) {
            this.flacStreamMetadata = flacStreamMetadata2;
            this.frameStartMarker = i2;
            this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FlacBinarySearchSeeker(androidx.media3.extractor.FlacStreamMetadata r17, int r18, long r19, long r21) {
        /*
            r16 = this;
            r0 = r17
            java.util.Objects.requireNonNull(r0)
            O3.y r1 = new O3.y
            r2 = 12
            r1.<init>(r2, r0)
            androidx.media3.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker r2 = new androidx.media3.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker
            r3 = 0
            r4 = r18
            r2.<init>(r0, r4)
            long r3 = r0.getDurationUs()
            long r7 = r0.totalSamples
            long r13 = r0.getApproxBytesPerFrame()
            r5 = 6
            int r0 = r0.minFrameSize
            int r15 = java.lang.Math.max(r5, r0)
            r5 = 0
            r0 = r16
            r9 = r19
            r11 = r21
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.flac.FlacBinarySearchSeeker.<init>(androidx.media3.extractor.FlacStreamMetadata, int, long, long):void");
    }
}
