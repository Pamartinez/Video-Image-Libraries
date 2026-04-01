package com.samsung.o3dp.lib3dphotography.utils;

import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StringUtil {
    private static final String TAG = "StringUtil";

    public static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static int versionFromString(String str) {
        int i2;
        String[] split = str.trim().split("\\.");
        int i7 = 0;
        int i8 = 0;
        while (i7 < 4) {
            try {
                if (i7 < split.length) {
                    i2 = Integer.parseInt(split[i7]);
                } else {
                    i2 = 0;
                }
                i8 = (i8 << 8) + i2;
                i7++;
            } catch (NumberFormatException unused) {
                LogUtil.w(TAG, "Unable to parse version string ".concat(str));
                return 0;
            }
        }
        return i8;
    }
}
