package com.samsung.android.gallery.database.dbtype;

import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SortByType {
    public static int getAlbumsOrder() {
        return GalleryPreference.getInstance().loadInt(PreferenceName.SORT_BY_ALBUM, 41);
    }

    public static int getGroupByDate(int i2) {
        return (i2 / 100) * 100;
    }

    public static int getOrderBy(int i2) {
        return i2 % 10;
    }

    public static int getSharingOrder() {
        return GalleryPreference.getInstance().loadInt(PreferenceName.SORT_BY_SHARING, 22);
    }

    public static int getSortAndOrderBy(int i2) {
        return i2 - getGroupByDate(i2);
    }

    public static int getSortBy(int i2) {
        return ((i2 / 10) % 10) * 10;
    }

    public static String getSortByAnalyticsDetail(int i2) {
        if (i2 == 11) {
            return AnalyticsDetail.SORT_BY_TAKEN_TIME_ASC.toString();
        }
        if (i2 == 12) {
            return AnalyticsDetail.SORT_BY_TAKEN_TIME_DESC.toString();
        }
        if (i2 == 21) {
            return AnalyticsDetail.SORT_BY_MODIFIED_TIME_ASC.toString();
        }
        if (i2 == 22) {
            return AnalyticsDetail.SORT_BY_MODIFIED_TIME_DESC.toString();
        }
        if (i2 == 31) {
            return AnalyticsDetail.SORT_BY_NAME_ASC.toString();
        }
        if (i2 != 32) {
            return AnalyticsDetail.SORT_BY_TAKEN_TIME_DESC.toString();
        }
        return AnalyticsDetail.SORT_BY_NAME_DESC.toString();
    }

    private static boolean hasTimeline(int i2) {
        if (getGroupByDate(i2) != 100 || !isByTime(i2)) {
            return false;
        }
        return true;
    }

    public static boolean isByTime(int i2) {
        int sortBy = getSortBy(i2);
        if (sortBy == 10 || sortBy == 20) {
            return true;
        }
        return false;
    }

    public static boolean isGroupByDate(String str) {
        if (str == null) {
            return false;
        }
        int loadSortBy = GalleryPreference.getInstance().loadSortBy(str, 12);
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.RecentAlbumTimeline) || !BucketUtils.isRecent(UnsafeCast.toInt(str))) {
            return hasTimeline(loadSortBy);
        }
        return true;
    }

    public static boolean isSortByCustom() {
        if (getSortBy(getAlbumsOrder()) == 40) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001e, code lost:
        if (r0 != 0) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0021, code lost:
        r3 = java.lang.String.valueOf(r0 / 100);
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toDebugString(int r5) {
        /*
            int r0 = getGroupByDate(r5)
            int r1 = getSortBy(r5)
            int r5 = getOrderBy(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = ""
            r2.<init>(r3)
            r4 = 100
            if (r0 != r4) goto L_0x001e
            r0 = 71
            java.lang.Character r3 = java.lang.Character.valueOf(r0)
            goto L_0x0026
        L_0x001e:
            if (r0 != 0) goto L_0x0021
            goto L_0x0026
        L_0x0021:
            int r0 = r0 / r4
            java.lang.String r3 = java.lang.String.valueOf(r0)
        L_0x0026:
            r2.append(r3)
            r0 = 10
            if (r1 != r0) goto L_0x0034
            r0 = 84
        L_0x002f:
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            goto L_0x0059
        L_0x0034:
            r3 = 20
            if (r1 != r3) goto L_0x003b
            r0 = 77
            goto L_0x002f
        L_0x003b:
            r3 = 30
            if (r1 != r3) goto L_0x0042
            r0 = 78
            goto L_0x002f
        L_0x0042:
            r3 = 40
            if (r1 != r3) goto L_0x004d
            r0 = 67
        L_0x0048:
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            goto L_0x0059
        L_0x004d:
            r3 = 50
            if (r1 != r3) goto L_0x0054
            r0 = 85
            goto L_0x0048
        L_0x0054:
            int r1 = r1 / r0
            java.lang.String r0 = java.lang.String.valueOf(r1)
        L_0x0059:
            r2.append(r0)
            r0 = 1
            if (r5 != r0) goto L_0x0066
            r5 = 65
        L_0x0061:
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            goto L_0x0070
        L_0x0066:
            r0 = 2
            if (r5 != r0) goto L_0x006c
            r5 = 68
            goto L_0x0061
        L_0x006c:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L_0x0070:
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dbtype.SortByType.toDebugString(int):java.lang.String");
    }
}
