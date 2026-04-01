package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.SeekMap;
import java.math.RoundingMode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class IndexSeeker implements Seeker {
    private final int averageBitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final IndexSeekMap indexSeekMap;

    public IndexSeeker(long j2, long j3, long j8) {
        long j10 = j3;
        long j11 = j8;
        this.indexSeekMap = new IndexSeekMap(new long[]{j10}, new long[]{0}, j2);
        this.dataStartPosition = j10;
        this.dataEndPosition = j11;
        int i2 = -2147483647;
        if (j2 != -9223372036854775807L) {
            long scaleLargeValue = Util.scaleLargeValue(j10 - j11, 8, j2, RoundingMode.HALF_UP);
            if (scaleLargeValue > 0 && scaleLargeValue <= 2147483647L) {
                i2 = (int) scaleLargeValue;
            }
            this.averageBitrate = i2;
            return;
        }
        this.averageBitrate = -2147483647;
    }

    public int getAverageBitrate() {
        return this.averageBitrate;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDataStartPosition() {
        return this.dataStartPosition;
    }

    public long getDurationUs() {
        return this.indexSeekMap.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        return this.indexSeekMap.getSeekPoints(j2);
    }

    public long getTimeUs(long j2) {
        return this.indexSeekMap.getTimeUs(j2);
    }

    public boolean isSeekable() {
        return this.indexSeekMap.isSeekable();
    }

    public boolean isTimeUsInIndex(long j2) {
        return this.indexSeekMap.isTimeUsInIndex(j2, 100000);
    }

    public void maybeAddSeekPoint(long j2, long j3) {
        if (!isTimeUsInIndex(j2)) {
            this.indexSeekMap.addSeekPoint(j2, j3);
        }
    }

    public void setDurationUs(long j2) {
        this.indexSeekMap.setDurationUs(j2);
    }
}
