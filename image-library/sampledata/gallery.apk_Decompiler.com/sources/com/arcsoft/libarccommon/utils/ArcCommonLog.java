package com.arcsoft.libarccommon.utils;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArcCommonLog {
    public static final boolean LOG_ON = false;
    public static final String TAG_COMMA = ", ";
    public static final String TAG_COMMON = "ArcSoft_Java";
    public static final String TAG_THREAD = "thread: ";

    public static void e(String str, String str2) {
        if (str == null) {
            str = TAG_COMMON;
        }
        Log.e(str, TAG_THREAD + Thread.currentThread().getId() + TAG_COMMA + str2);
    }

    public static void version(String str, String str2) {
        if (str == null) {
            str = TAG_COMMON;
        }
        Log.d(str, TAG_THREAD + Thread.currentThread().getId() + TAG_COMMA + str2);
    }

    public static void d(String str, String str2) {
    }

    public static void i(String str, String str2) {
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }
}
