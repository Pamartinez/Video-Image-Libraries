package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TsBinarySearchSeeker extends BinarySearchSeeker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer = new ParsableByteArray();
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;
        private final int timestampSearchBytes;

        public TsPcrSeeker(int i2, TimestampAdjuster timestampAdjuster, int i7) {
            this.pcrPid = i2;
            this.pcrTimestampAdjuster = timestampAdjuster;
            this.timestampSearchBytes = i7;
        }

        private BinarySearchSeeker.TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray parsableByteArray, long j2, long j3) {
            int findSyncBytePosition;
            int i2;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            long j8 = j3;
            int limit = parsableByteArray2.limit();
            long j10 = -1;
            long j11 = -1;
            long j12 = -9223372036854775807L;
            while (parsableByteArray2.bytesLeft() >= 188 && (i2 = findSyncBytePosition + 188) <= limit) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray2, (findSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray2.getData(), parsableByteArray2.getPosition(), limit)), this.pcrPid);
                if (readPcrFromPacket != -9223372036854775807L) {
                    long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(readPcrFromPacket);
                    if (adjustTsTimestamp > j2) {
                        if (j12 == -9223372036854775807L) {
                            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j8);
                        }
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j8 + j11);
                    } else if (100000 + adjustTsTimestamp > j2) {
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(((long) findSyncBytePosition) + j8);
                    } else {
                        j11 = (long) findSyncBytePosition;
                        j12 = adjustTsTimestamp;
                    }
                }
                parsableByteArray2.setPosition(i2);
                j10 = (long) i2;
            }
            if (j12 != -9223372036854775807L) {
                return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j12, j8 + j10);
            }
            return BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2) {
            long position = extractorInput.getPosition();
            int min = (int) Math.min((long) this.timestampSearchBytes, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
            return searchForPcrValueInBuffer(this.packetBuffer, j2, position);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TsBinarySearchSeeker(androidx.media3.common.util.TimestampAdjuster r17, long r18, long r20, int r22, int r23) {
        /*
            r16 = this;
            androidx.media3.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter r1 = new androidx.media3.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter
            r1.<init>()
            androidx.media3.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker r2 = new androidx.media3.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker
            r0 = r17
            r3 = r22
            r4 = r23
            r2.<init>(r3, r0, r4)
            r3 = 1
            long r7 = r18 + r3
            r13 = 188(0xbc, double:9.3E-322)
            r15 = 940(0x3ac, float:1.317E-42)
            r5 = 0
            r9 = 0
            r0 = r16
            r3 = r18
            r11 = r20
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.TsBinarySearchSeeker.<init>(androidx.media3.common.util.TimestampAdjuster, long, long, int, int):void");
    }
}
