package com.samsung.android.gallery.module.receiver;

import com.samsung.android.gallery.support.library.SeApiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingRotationObserver extends SettingObserver {
    public String getGlobalKey() {
        return "global://setting/system/auto_rotation";
    }

    public String getUriString() {
        return "accelerometer_rotation";
    }

    public boolean isSettingEnabled() {
        return SeApiCompat.isAutoRotateEnabled(getAppContext());
    }
}
