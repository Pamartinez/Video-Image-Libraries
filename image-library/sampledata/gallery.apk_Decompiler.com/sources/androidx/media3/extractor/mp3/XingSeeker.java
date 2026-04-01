package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class XingSeeker implements Seeker {
    private final int bitrate;
    private final long dataEndPosition;
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] tableOfContents;
    private final int xingFrameSize;

    private XingSeeker(long j2, int i2, long j3, int i7, long j8, long[] jArr) {
        this.dataStartPosition = j2;
        this.xingFrameSize = i2;
        this.durationUs = j3;
        this.bitrate = i7;
        this.dataSize = j8;
        this.tableOfContents = jArr;
        this.dataEndPosition = j8 != -1 ? j2 + j8 : -1;
    }

    public static XingSeeker create(XingFrame xingFrame, long j2) {
        long computeDurationUs = xingFrame.computeDurationUs();
        if (computeDurationUs == -9223372036854775807L) {
            return null;
        }
        MpegAudioUtil.Header header = xingFrame.header;
        return new XingSeeker(j2, header.frameSize, computeDurationUs, header.bitrate, xingFrame.dataSize, xingFrame.tableOfContents);
    }

    private long getTimeUsForTableIndex(int i2) {
        return (this.durationUs * ((long) i2)) / 100;
    }

    public int getAverageBitrate() {
        return this.bitrate;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDataStartPosition() {
        return this.dataStartPosition + ((long) this.xingFrameSize);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        double d;
        if (!isSeekable()) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.dataStartPosition + ((long) this.xingFrameSize)));
        }
        long constrainValue = Util.constrainValue(j2, 0, this.durationUs);
        double d2 = (((double) constrainValue) * 100.0d) / ((double) this.durationUs);
        double d3 = MapUtil.INVALID_LOCATION;
        if (d2 > MapUtil.INVALID_LOCATION) {
            if (d2 >= 100.0d) {
                d3 = 256.0d;
            } else {
                int i2 = (int) d2;
                long[] jArr = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
                double d5 = (double) jArr[i2];
                if (i2 == 99) {
                    d = 256.0d;
                } else {
                    d = (double) jArr[i2 + 1];
                }
                d3 = d5 + ((d - d5) * (d2 - ((double) i2)));
            }
        }
        return new SeekMap.SeekPoints(new SeekPoint(constrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d3 / 256.0d) * ((double) this.dataSize)), (long) this.xingFrameSize, this.dataSize - 1)));
    }

    public long getTimeUs(long j2) {
        long j3;
        double d;
        long j8 = j2 - this.dataStartPosition;
        if (!isSeekable() || j8 <= ((long) this.xingFrameSize)) {
            return 0;
        }
        long[] jArr = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
        double d2 = (((double) j8) * 256.0d) / ((double) this.dataSize);
        int binarySearchFloor = Util.binarySearchFloor(jArr, (long) d2, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(binarySearchFloor);
        long j10 = jArr[binarySearchFloor];
        int i2 = binarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i2);
        if (binarySearchFloor == 99) {
            j3 = 256;
        } else {
            j3 = jArr[i2];
        }
        if (j10 == j3) {
            d = MapUtil.INVALID_LOCATION;
        } else {
            d = (d2 - ((double) j10)) / ((double) (j3 - j10));
        }
        return Math.round(d * ((double) (timeUsForTableIndex2 - timeUsForTableIndex))) + timeUsForTableIndex;
    }

    public boolean isSeekable() {
        if (this.tableOfContents != null) {
            return true;
        }
        return false;
    }
}
