package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CscFeatures {
    public static String get(String str) {
        return SeApiCompat.getCscFeatureString(str, "");
    }

    public static boolean getBoolean(String str, boolean z) {
        return SeApiCompat.getCscFeatureBoolean(str, z);
    }
}
