package com.samsung.android.sdk.sgpl.content.story;

import N2.j;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Utils {
    static final int SEM_PLATFORM_INT = Build.VERSION.SEM_PLATFORM_INT;
    static final int SEP_110_VERSION_INT = 110000;
    static final int SEP_140_VERSION_INT = 140000;
    static final int SEP_145_VERSION_INT = 140500;
    static final int SEP_160_VERSION_INT = 160000;
    static final int SEP_170_VERSION_INT = 170000;
    static final int SEP_175_VERSION_INT = 170500;
    private static final String TAG = "Util";
    /* access modifiers changed from: private */
    public static final Map<String, Long> sPackageVersion = new ConcurrentHashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecMedia {
        static final String PACKAGE_NAME = "com.samsung.android.providers.media";
        static final long SEC_MEDIA_VERSION_FOR_MP_STORY = 1610400000;

        public static boolean supportStory(Context context) {
            if (((Long) Utils.sPackageVersion.computeIfAbsent(PACKAGE_NAME, new g(0, context))).longValue() >= SEC_MEDIA_VERSION_FOR_MP_STORY) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static long getPackageVersion(Context context, String str) {
        PackageManager packageManager;
        if (context != null) {
            try {
                packageManager = context.getPackageManager();
            } catch (Exception e) {
                j.D(e, j.k("getPackageVersion failed=", str, ", e="), TAG);
                return -1;
            }
        } else {
            packageManager = null;
        }
        if (packageManager != null) {
            return packageManager.getPackageInfo(str, 0).getLongVersionCode();
        }
        return -1;
    }

    public static String parseId(String str) {
        return String.valueOf(toLong(str.substring(str.lastIndexOf("/") + 1), -1));
    }

    public static long parseIdToLong(String str) {
        return parseLong(parseId(str));
    }

    public static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            j.D(e, new StringBuilder("parse failed e = "), TAG);
            return 0;
        }
    }

    public static boolean supportCreateStory() {
        if (SEM_PLATFORM_INT >= SEP_110_VERSION_INT) {
            return true;
        }
        return false;
    }

    public static boolean supportCreateWithMediaId() {
        if (SEM_PLATFORM_INT >= SEP_170_VERSION_INT) {
            return true;
        }
        return false;
    }

    public static boolean supportDisplayOrder() {
        if (SEM_PLATFORM_INT >= SEP_160_VERSION_INT) {
            return true;
        }
        return false;
    }

    public static boolean supportMpStory(Context context) {
        if (SEM_PLATFORM_INT < SEP_175_VERSION_INT || !SecMedia.supportStory(context)) {
            return false;
        }
        return true;
    }

    public static boolean supportSmartCrop() {
        if (SEM_PLATFORM_INT >= SEP_140_VERSION_INT) {
            return true;
        }
        return false;
    }

    public static boolean supportTheme() {
        if (SEM_PLATFORM_INT >= SEP_145_VERSION_INT) {
            return true;
        }
        return false;
    }

    public static String tick(long j2) {
        return tick("", j2);
    }

    public static long toLong(String str, long j2) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    return Long.parseLong(str);
                }
            } catch (NumberFormatException e) {
                Log.e(TAG, "toLong failed e=" + e.getMessage());
            }
        }
        return j2;
    }

    public static void wait(int i2) {
        int i7 = i2 / 50;
        for (int i8 = 0; i8 <= i7; i8++) {
            int i10 = 50;
            if (i8 == i7) {
                try {
                    i10 = i2 % 50;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep((long) i10);
        }
    }

    public static String tick(Object obj, long j2) {
        return obj + " +" + (System.currentTimeMillis() - j2);
    }
}
