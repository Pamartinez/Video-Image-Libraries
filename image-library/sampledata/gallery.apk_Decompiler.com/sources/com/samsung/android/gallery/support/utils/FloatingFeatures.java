package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FloatingFeatures {
    public static boolean contains(String str, String str2) {
        return getString(str).contains(str2);
    }

    public static boolean getBoolean(String str) {
        return SeApiCompat.getFloatingFeatureBoolean(str);
    }

    public static int getInt(String str) {
        return SeApiCompat.getFloatingFeatureInt(str);
    }

    public static String getString(String str) {
        String floatingFeatureString = SeApiCompat.getFloatingFeatureString(str);
        if (floatingFeatureString == null) {
            return "";
        }
        return floatingFeatureString;
    }
}
