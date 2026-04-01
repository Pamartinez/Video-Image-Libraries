package com.samsung.o3dp.lib3dphotography.utils;

import android.util.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogUtil {
    private static final String TAG_PREFIX = "O3DP-";

    public static void d(String str, String str2) {
        Log.d(getLogTag(str), str2);
    }

    public static void e(String str, String str2) {
        Log.e(getLogTag(str), str2);
    }

    private static String getLogTag(String str) {
        return C0212a.l(TAG_PREFIX, str);
    }

    public static void i(String str, String str2) {
        Log.i(getLogTag(str), str2);
    }

    public static void v(String str, String str2) {
        Log.v(getLogTag(str), str2);
    }

    public static void w(String str, String str2) {
        Log.w(getLogTag(str), str2);
    }

    public static void wtf(String str, String str2) {
        Log.wtf(getLogTag(str), str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(getLogTag(str), str2, th);
    }
}
