package androidx.media3.common.util;

import androidx.media3.common.audio.SpeedProvider;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SpeedProviderUtil {
    public static long getDurationAfterSpeedProviderApplied(SpeedProvider speedProvider, long j2) {
        long j3 = 0;
        double d = MapUtil.INVALID_LOCATION;
        while (j3 < j2) {
            long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(j3);
            if (nextSpeedChangeTimeUs == -9223372036854775807L) {
                nextSpeedChangeTimeUs = Long.MAX_VALUE;
            }
            d += ((double) (Math.min(nextSpeedChangeTimeUs, j2) - j3)) / ((double) speedProvider.getSpeed(j3));
            j3 = nextSpeedChangeTimeUs;
        }
        return (long) Math.floor(d);
    }

    public static long getNextSpeedChangeSamplePosition(SpeedProvider speedProvider, long j2, int i2) {
        boolean z;
        boolean z3 = false;
        if (j2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (i2 > 0) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(Util.sampleCountToDurationUs(j2, i2));
        if (nextSpeedChangeTimeUs == -9223372036854775807L) {
            return -1;
        }
        return Util.durationUsToSampleCount(nextSpeedChangeTimeUs, i2);
    }

    public static float getSampleAlignedSpeed(SpeedProvider speedProvider, long j2, int i2) {
        boolean z;
        boolean z3 = false;
        if (j2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (i2 > 0) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        return speedProvider.getSpeed(Util.sampleCountToDurationUs(j2, i2));
    }
}
