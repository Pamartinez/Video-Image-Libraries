package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlacSeekTableSeekMap implements SeekMap {
    private final long firstFrameOffset;
    private final FlacStreamMetadata flacStreamMetadata;

    public FlacSeekTableSeekMap(FlacStreamMetadata flacStreamMetadata2, long j2) {
        this.flacStreamMetadata = flacStreamMetadata2;
        this.firstFrameOffset = j2;
    }

    private SeekPoint getSeekPoint(long j2, long j3) {
        return new SeekPoint((j2 * 1000000) / ((long) this.flacStreamMetadata.sampleRate), this.firstFrameOffset + j3);
    }

    public long getDurationUs() {
        return this.flacStreamMetadata.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        long j3;
        Assertions.checkStateNotNull(this.flacStreamMetadata.seekTable);
        FlacStreamMetadata flacStreamMetadata2 = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable seekTable = flacStreamMetadata2.seekTable;
        long[] jArr = seekTable.pointSampleNumbers;
        long[] jArr2 = seekTable.pointOffsets;
        int binarySearchFloor = Util.binarySearchFloor(jArr, flacStreamMetadata2.getSampleNumber(j2), true, false);
        long j8 = 0;
        if (binarySearchFloor == -1) {
            j3 = 0;
        } else {
            j3 = jArr[binarySearchFloor];
        }
        if (binarySearchFloor != -1) {
            j8 = jArr2[binarySearchFloor];
        }
        SeekPoint seekPoint = getSeekPoint(j3, j8);
        if (seekPoint.timeUs == j2 || binarySearchFloor == jArr.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i2 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, getSeekPoint(jArr[i2], jArr2[i2]));
    }

    public boolean isSeekable() {
        return true;
    }
}
