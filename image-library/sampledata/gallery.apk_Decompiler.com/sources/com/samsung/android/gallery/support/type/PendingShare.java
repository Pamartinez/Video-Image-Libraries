package com.samsung.android.gallery.support.type;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PendingShare {
    public static void clear() {
        PreferenceCache.PendingSharePath.clear();
    }

    public static String get() {
        return PreferenceCache.PendingSharePath.getString();
    }

    public static String getAndClear() {
        String str = get();
        if (str != null) {
            clear();
        }
        return str;
    }

    public static void set(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceCache.PendingSharePath.setString(str);
        }
    }
}
