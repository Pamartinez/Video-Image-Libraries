package com.samsung.android.gallery.module.abstraction;

import android.content.res.Configuration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThemeType {
    public static int getNightMode(String str) {
        if ("night".equals(str)) {
            return 2;
        }
        return "light".equals(str) ? 1 : -1;
    }

    public static int getNightMode(Configuration configuration) {
        int i2 = configuration.uiMode & 48;
        if (i2 == 32) {
            return 2;
        }
        return i2 == 16 ? 1 : -1;
    }
}
