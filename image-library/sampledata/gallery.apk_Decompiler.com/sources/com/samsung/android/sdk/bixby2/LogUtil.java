package com.samsung.android.sdk.bixby2;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogUtil {
    public static final String KEY_ENGINEERING_MODE = "engineeringMode";
    private static final String PREFIX_IUG = "IUG# ";
    private static boolean sEngineeringMode = false;

    public static void c(String str, String str2) {
        if (sEngineeringMode) {
            Log.i(str, PREFIX_IUG + str2);
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void setEngineeringMode(boolean z) {
        sEngineeringMode = z;
    }

    public static void d(String str, String str2) {
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }
}
