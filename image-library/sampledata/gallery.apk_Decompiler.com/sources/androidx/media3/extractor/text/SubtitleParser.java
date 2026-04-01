package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SubtitleParser {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        public static final Factory UNSUPPORTED = new Factory() {
            public SubtitleParser create(Format format) {
                throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
            }

            public int getCueReplacementBehavior(Format format) {
                return 1;
            }

            public boolean supportsFormat(Format format) {
                return false;
            }
        };

        SubtitleParser create(Format format);

        int getCueReplacementBehavior(Format format);

        boolean supportsFormat(Format format);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OutputOptions {
        private static final OutputOptions ALL = new OutputOptions(-9223372036854775807L, false);
        public final boolean outputAllCues;
        public final long startTimeUs;

        private OutputOptions(long j2, boolean z) {
            this.startTimeUs = j2;
            this.outputAllCues = z;
        }

        public static OutputOptions allCues() {
            return ALL;
        }

        public static OutputOptions cuesAfterThenRemainingCuesBefore(long j2) {
            return new OutputOptions(j2, true);
        }
    }

    int getCueReplacementBehavior();

    void parse(byte[] bArr, int i2, int i7, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    void reset() {
    }
}
