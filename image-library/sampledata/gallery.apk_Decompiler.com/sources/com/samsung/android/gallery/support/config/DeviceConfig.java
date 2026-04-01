package com.samsung.android.gallery.support.config;

import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeviceConfig {
    public static boolean DEBUG_BINARY = false;
    public static boolean UNIT_TEST = false;

    static {
        boolean z;
        String str = Build.TYPE;
        if ("eng".equals(str) || "userdebug".equals(str)) {
            z = true;
        } else {
            z = false;
        }
        DEBUG_BINARY = z;
    }
}
