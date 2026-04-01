package com.samsung.android.gallery.module.receiver;

import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingReduceTransObserver extends SettingObserver {
    public String getGlobalKey() {
        return "global://setting/system/reduce_transparency_and_blur";
    }

    public String getUriString() {
        return "accessibility_reduce_transparency";
    }

    public boolean isSettingEnabled() {
        return SeApiCompat.isReducedTransparency(getAppContext());
    }

    public void updateCommonData(boolean z, boolean z3) {
        Features.recycle(Features.IS_ENABLED_REDUCE_TRANSPARENCY, z3);
    }
}
