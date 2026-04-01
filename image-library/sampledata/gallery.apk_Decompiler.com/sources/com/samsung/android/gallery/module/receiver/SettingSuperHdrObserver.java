package com.samsung.android.gallery.module.receiver;

import com.samsung.android.gallery.support.utils.SuperHdrConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingSuperHdrObserver extends SettingObserver {
    public String getGlobalKey() {
        return "global://setting/system/super_hdr";
    }

    public String getUriString() {
        return "sec_superhdr";
    }

    public boolean isSettingEnabled() {
        return SuperHdrConfig.isSystemEnabled();
    }
}
