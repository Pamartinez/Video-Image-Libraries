package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SuperHdrConfig {
    static final boolean ALWAYS_ON;
    public static final boolean SUPPORT;

    static {
        boolean z;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_PHOTO_HDR);
        SUPPORT = isEnabled;
        if (!isEnabled || !SdkConfig.atLeast(SdkConfig.SEM.V)) {
            z = false;
        } else {
            z = true;
        }
        ALWAYS_ON = z;
    }

    public static boolean isEnabled() {
        if (ALWAYS_ON) {
            return true;
        }
        if (!SUPPORT || !PreferenceFeatures.isEnabled(PreferenceFeatures.ShowHdrImages)) {
            return false;
        }
        return true;
    }

    public static boolean isSystemEnabled() {
        if (!ALWAYS_ON || SeApiCompat.getSettingsSystemInt(AppResources.getAppContext(), "sec_superhdr", 1) > 0) {
            return true;
        }
        return false;
    }

    public static boolean supportSystemConfig() {
        return ALWAYS_ON;
    }
}
