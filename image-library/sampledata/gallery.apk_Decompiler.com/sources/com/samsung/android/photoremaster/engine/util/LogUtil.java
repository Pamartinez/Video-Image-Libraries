package com.samsung.android.photoremaster.engine.util;

import N2.j;
import android.os.Build;
import android.util.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogUtil {
    private static final String TAG_PREFIX = "RemasterEngine-";
    private static final String USER_BUILD = "user";

    public static void D(String str, String str2) {
        if (isDebuggable()) {
            Log.d(getLogTag(str), getLogMsg(str2));
        }
    }

    public static void E(String str, String str2) {
        Log.e(getLogTag(str), getLogMsg(str2));
    }

    public static void I(String str, String str2) {
        Log.i(getLogTag(str), getLogMsg(str2));
    }

    public static void V(String str, String str2) {
        if (isDebuggable()) {
            Log.v(getLogTag(str), getLogMsg(str2));
        }
    }

    public static void W(String str, String str2) {
        Log.w(getLogTag(str), getLogMsg(str2));
    }

    private static String getCallerMethod() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length > 3) {
            return stackTrace[3].getMethodName();
        }
        return null;
    }

    private static String getLogMsg(String str) {
        return j.f(new StringBuilder(), getCallerMethod(), ": ", str);
    }

    private static String getLogTag(String str) {
        return C0212a.l(TAG_PREFIX, str);
    }

    private static boolean isDebuggable() {
        return !USER_BUILD.equals(Build.TYPE);
    }

    public static void E(String str, String str2, Throwable th) {
        Log.e(getLogTag(str), getLogMsg(str2), th);
    }
}
