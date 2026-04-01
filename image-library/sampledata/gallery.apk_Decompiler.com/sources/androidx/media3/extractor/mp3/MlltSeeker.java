package androidx.media3.extractor.mp3;

import android.util.Pair;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MlltSeeker implements Seeker {
    private final long durationUs;
    private final long[] referencePositions;
    private final long[] referenceTimesMs;

    private MlltSeeker(long[] jArr, long[] jArr2, long j2) {
        this.referencePositions = jArr;
        this.referenceTimesMs = jArr2;
        this.durationUs = j2 == -9223372036854775807L ? Util.msToUs(jArr2[jArr2.length - 1]) : j2;
    }

    public static MlltSeeker create(long j2, MlltFrame mlltFrame, long j3) {
        int length = mlltFrame.bytesDeviations.length;
        int i2 = length + 1;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        jArr[0] = j2;
        long j8 = 0;
        jArr2[0] = 0;
        for (int i7 = 1; i7 <= length; i7++) {
            int i8 = i7 - 1;
            j2 += (long) (mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i8]);
            j8 += (long) (mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i8]);
            jArr[i7] = j2;
            jArr2[i7] = j8;
        }
        return new MlltSeeker(jArr, jArr2, j3);
    }

    private static Pair<Long, Long> linearlyInterpolate(long j2, long[] jArr, long[] jArr2) {
        double d;
        int binarySearchFloor = Util.binarySearchFloor(jArr, j2, true, true);
        long j3 = jArr[binarySearchFloor];
        long j8 = jArr2[binarySearchFloor];
        int i2 = binarySearchFloor + 1;
        if (i2 == jArr.length) {
            return Pair.create(Long.valueOf(j3), Long.valueOf(j8));
        }
        long j10 = jArr[i2];
        long j11 = jArr2[i2];
        if (j10 == j3) {
            d = MapUtil.INVALID_LOCATION;
        } else {
            d = (((double) j2) - ((double) j3)) / ((double) (j10 - j3));
        }
        return Pair.create(Long.valueOf(j2), Long.valueOf(((long) (d * ((double) (j11 - j8)))) + j8));
    }

    public int getAverageBitrate() {
        return -2147483647;
    }

    public long getDataEndPosition() {
        return -1;
    }

    public long getDataStartPosition() {
        return 0;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        Pair<Long, Long> linearlyInterpolate = linearlyInterpolate(Util.usToMs(Util.constrainValue(j2, 0, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        return new SeekMap.SeekPoints(new SeekPoint(Util.msToUs(((Long) linearlyInterpolate.first).longValue()), ((Long) linearlyInterpolate.second).longValue()));
    }

    public long getTimeUs(long j2) {
        return Util.msToUs(((Long) linearlyInterpolate(j2, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    public boolean isSeekable() {
        return true;
    }
}
