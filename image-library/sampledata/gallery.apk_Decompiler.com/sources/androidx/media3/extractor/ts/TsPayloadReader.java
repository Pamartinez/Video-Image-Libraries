package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TsPayloadReader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DvbSubtitleInfo {
        public final byte[] initializationData;
        public final String language;
        public final int type;

        public DvbSubtitleInfo(String str, int i2, byte[] bArr) {
            this.language = str;
            this.type = i2;
            this.initializationData = bArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EsInfo {
        public final int audioType;
        public final byte[] descriptorBytes;
        public final List<DvbSubtitleInfo> dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        public EsInfo(int i2, String str, int i7, List<DvbSubtitleInfo> list, byte[] bArr) {
            List<DvbSubtitleInfo> list2;
            this.streamType = i2;
            this.language = str;
            this.audioType = i7;
            if (list == null) {
                list2 = Collections.EMPTY_LIST;
            } else {
                list2 = Collections.unmodifiableList(list);
            }
            this.dvbSubtitleInfos = list2;
            this.descriptorBytes = bArr;
        }

        public int getRoleFlags() {
            int i2 = this.audioType;
            if (i2 == 2) {
                return 2048;
            }
            if (i2 != 3) {
                return 0;
            }
            return 512;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        SparseArray<TsPayloadReader> createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int i2, EsInfo esInfo);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrackIdGenerator {
        private final int firstTrackId;
        private String formatId;
        private final String formatIdPrefix;
        private int trackId;
        private final int trackIdIncrement;

        public TrackIdGenerator(int i2, int i7) {
            this(Integer.MIN_VALUE, i2, i7);
        }

        private void maybeThrowUninitializedError() {
            if (this.trackId == Integer.MIN_VALUE) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }

        public void generateNewId() {
            int i2;
            int i7 = this.trackId;
            if (i7 == Integer.MIN_VALUE) {
                i2 = this.firstTrackId;
            } else {
                i2 = i7 + this.trackIdIncrement;
            }
            this.trackId = i2;
            this.formatId = this.formatIdPrefix + this.trackId;
        }

        public String getFormatId() {
            maybeThrowUninitializedError();
            return this.formatId;
        }

        public int getTrackId() {
            maybeThrowUninitializedError();
            return this.trackId;
        }

        public TrackIdGenerator(int i2, int i7, int i8) {
            String str;
            if (i2 != Integer.MIN_VALUE) {
                str = i2 + "/";
            } else {
                str = "";
            }
            this.formatIdPrefix = str;
            this.firstTrackId = i7;
            this.trackIdIncrement = i8;
            this.trackId = Integer.MIN_VALUE;
            this.formatId = "";
        }
    }

    void consume(ParsableByteArray parsableByteArray, int i2);

    void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator);

    void seek();
}
