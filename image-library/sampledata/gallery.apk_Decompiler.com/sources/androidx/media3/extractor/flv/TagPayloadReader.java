package androidx.media3.extractor.flv;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TagPayloadReader {
    protected final TrackOutput output;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String str) {
            super(str, (Throwable) null, false, 1);
        }
    }

    public TagPayloadReader(TrackOutput trackOutput) {
        this.output = trackOutput;
    }

    public final boolean consume(ParsableByteArray parsableByteArray, long j2) {
        if (!parseHeader(parsableByteArray) || !parsePayload(parsableByteArray, j2)) {
            return false;
        }
        return true;
    }

    public abstract boolean parseHeader(ParsableByteArray parsableByteArray);

    public abstract boolean parsePayload(ParsableByteArray parsableByteArray, long j2);
}
