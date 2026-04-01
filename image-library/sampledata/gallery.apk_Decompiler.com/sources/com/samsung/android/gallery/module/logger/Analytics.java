package com.samsung.android.gallery.module.logger;

import android.util.Pair;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Analytics {
    public static Pair<String, String>[] buildAlbumCountDetail(long j2, long j3) {
        return new Pair[]{new Pair(AnalyticsDetailKey.ALBUM_COUNT.toString(), AnalyticsUtils.convertSmallNumberToString(j2)), new Pair(AnalyticsDetailKey.GROUP_COUNT.toString(), AnalyticsUtils.convertSmallNumberToString(j3))};
    }

    public static Pair<String, String>[] buildCountDetail(AnalyticsDetailKey analyticsDetailKey, long j2) {
        return buildDetailPair(analyticsDetailKey.toString(), AnalyticsUtils.convertLargeNumberToString(j2));
    }

    private static Pair<String, String>[] buildDetailPair(String str, String str2) {
        return new Pair[]{new Pair(str, str2)};
    }

    public static AnalyticsEventId getEventId(String str) {
        if (str == null) {
            return null;
        }
        String removeArgs = ArgumentsUtil.removeArgs(str);
        removeArgs.getClass();
        char c5 = 65535;
        switch (removeArgs.hashCode()) {
            case -984961596:
                if (removeArgs.equals("location://mtp")) {
                    c5 = 0;
                    break;
                }
                break;
            case -440239236:
                if (removeArgs.equals("location://sharing/albums")) {
                    c5 = 1;
                    break;
                }
                break;
            case -212479357:
                if (removeArgs.equals("location://story/albums")) {
                    c5 = 2;
                    break;
                }
                break;
            case -125579287:
                if (removeArgs.equals("location://albums")) {
                    c5 = 3;
                    break;
                }
                break;
            case 263612166:
                if (removeArgs.equals("location://timeline")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return AnalyticsEventId.EVENT_TAB_MTP;
            case 1:
                return AnalyticsEventId.EVENT_TAB_SHARINGS;
            case 2:
                return AnalyticsEventId.EVENT_TAB_STORIES;
            case 3:
                return AnalyticsEventId.EVENT_TAB_ALBUMS;
            case 4:
                return AnalyticsEventId.EVENT_TAB_PICTURES;
            default:
                return null;
        }
    }

    public static boolean isStoryNotifications(String str) {
        if (LocationKey.isSuggests(str) || ArgumentsUtil.getArgValue(str, "suggestion", false)) {
            return true;
        }
        return false;
    }
}
