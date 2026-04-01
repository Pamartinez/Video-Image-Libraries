package com.samsung.android.gallery.module.receiver;

import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingShapeButtonObserver extends SettingObserver {
    public String getGlobalKey() {
        return "global://setting/system/shape_button";
    }

    public String getUriString() {
        return "show_button_background";
    }

    public void updateCommonData(boolean z, boolean z3) {
        Features.recycle(Features.IS_ENABLED_SHOW_BUTTON_SHAPES, z3);
    }
}
