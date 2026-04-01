package com.samsung.android.gallery.support.config;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Component$PackageName {
    public static final String SHARE_SHEET_PACKAGE;

    static {
        String str;
        if (SdkConfig.atLeast(SdkConfig.GED.U)) {
            str = "com.android.intentresolver";
        } else {
            str = "android";
        }
        SHARE_SHEET_PACKAGE = str;
    }
}
