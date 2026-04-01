package com.samsung.android.livetranslation.util;

import android.os.Build;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LTTLogger {
    public static String LOG_TAG = "LTT_4x ";
    public static final String TIME_TAKEN_TAG = "TIME_TAKEN ";
    private static final boolean USE_VERBOSE_LOG;

    static {
        boolean z;
        String str = Build.TYPE;
        if (str.contentEquals("eng") || str.contentEquals("userdebug")) {
            z = true;
        } else {
            z = false;
        }
        USE_VERBOSE_LOG = z;
    }

    public static void d(String str, String str2) {
        if (USE_VERBOSE_LOG) {
            Log.d(str, LOG_TAG + str2);
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, LOG_TAG + str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, LOG_TAG + str2);
    }

    public static void p(String str, String str2) {
        Log.v(str, LOG_TAG + TIME_TAKEN_TAG + str2);
    }

    public static void v(String str, String str2) {
        if (USE_VERBOSE_LOG) {
            Log.v(str, LOG_TAG + str2);
        }
    }

    public static void w(String str, String str2) {
        Log.w(str, LOG_TAG + str2);
    }
}
