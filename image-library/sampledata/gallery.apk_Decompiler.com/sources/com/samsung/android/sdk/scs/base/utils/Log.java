package com.samsung.android.sdk.scs.base.utils;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Log {
    public static final String TAG_PREFIX = "ScsApi@";
    public static final String TAG_SEPARATOR = "@";

    private static String concatPrefixTag(String str) {
        return TAG_PREFIX.concat(filterPrefixTag(str));
    }

    public static void d(String str, String str2) {
        android.util.Log.d(concatPrefixTag(str), str2);
    }

    public static void e(String str, String str2) {
        android.util.Log.e(concatPrefixTag(str), str2);
    }

    private static String filterPrefixTag(String str) {
        if (str == null) {
            return "";
        }
        return str.replace(TAG_PREFIX, "");
    }

    public static void i(String str, String str2) {
        android.util.Log.i(concatPrefixTag(str), str2);
    }

    public static void objectInfo(String str, String str2, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb2 = new StringBuilder(str2);
            for (Object obj : objArr) {
                if (obj != null) {
                    String simpleName = obj.getClass().getSimpleName();
                    String hexString = Integer.toHexString(obj.hashCode());
                    if (sb2.length() > 0) {
                        sb2.append(" >> ");
                    }
                    j.z(sb2, simpleName, TAG_SEPARATOR, hexString);
                }
            }
            i(str, sb2.toString());
        }
    }

    public static void w(String str, String str2) {
        android.util.Log.w(concatPrefixTag(str), str2);
    }

    public static void d(String str, String str2, Throwable th) {
        android.util.Log.d(concatPrefixTag(str), str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        android.util.Log.e(concatPrefixTag(str), str2, th);
    }

    public static void w(String str, String str2, Throwable th) {
        android.util.Log.w(concatPrefixTag(str), str2, th);
    }
}
