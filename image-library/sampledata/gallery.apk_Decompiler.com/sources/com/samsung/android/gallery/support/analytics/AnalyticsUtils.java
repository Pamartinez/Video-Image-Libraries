package com.samsung.android.gallery.support.analytics;

import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnalyticsUtils {
    public static String convertLargeNumberToString(long j2) {
        long j3 = j2 / 10000;
        if (j2 == 0) {
            return "0";
        }
        if (j3 == 0) {
            long j8 = j2 / 1000;
            return j8 + "000~" + j8 + "999";
        } else if (j3 >= 50) {
            return "500000~";
        } else {
            return j3 + "0000~" + j3 + "9999";
        }
    }

    public static String convertSmallNumberToString(long j2) {
        if (j2 < 50) {
            return String.valueOf(j2);
        }
        if (j2 < 100) {
            return "50~99";
        }
        if (j2 >= Def.SURFACE_CHANNEL_TIMEOUT_MILLIS) {
            return "5000~";
        }
        long j3 = j2 / 100;
        return j3 + "00~" + j3 + "99";
    }
}
