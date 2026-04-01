package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PassthroughSectionPayloadReader implements SectionPayloadReader {
    private Format format;
    private TrackOutput output;
    private TimestampAdjuster timestampAdjuster;

    public PassthroughSectionPayloadReader(String str, String str2) {
        this.format = new Format.Builder().setContainerMimeType(str2).setSampleMimeType(str).build();
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.timestampAdjuster);
        Util.castNonNull(this.output);
    }

    public void consume(ParsableByteArray parsableByteArray) {
        assertInitialized();
        long lastAdjustedTimestampUs = this.timestampAdjuster.getLastAdjustedTimestampUs();
        long timestampOffsetUs = this.timestampAdjuster.getTimestampOffsetUs();
        if (lastAdjustedTimestampUs != -9223372036854775807L && timestampOffsetUs != -9223372036854775807L) {
            Format format2 = this.format;
            if (timestampOffsetUs != format2.subsampleOffsetUs) {
                Format build = format2.buildUpon().setSubsampleOffsetUs(timestampOffsetUs).build();
                this.format = build;
                this.output.format(build);
            }
            int bytesLeft = parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, bytesLeft);
            this.output.sampleMetadata(lastAdjustedTimestampUs, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
        }
    }

    public void init(TimestampAdjuster timestampAdjuster2, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.timestampAdjuster = timestampAdjuster2;
        trackIdGenerator.generateNewId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
        this.output = track;
        track.format(this.format);
    }
}
