package com.samsung.android.gallery.module.receiver;

import android.content.Context;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingRotationSecondObserver extends SettingObserver {
    private boolean supportDualScreen() {
        return Features.isEnabled(Features.SUPPORT_DUAL_SCREEN);
    }

    public String getGlobalKey() {
        return "global://setting/system/auto_rotation/second";
    }

    public String getUriString() {
        return "accelerometer_rotation_second";
    }

    public boolean isSettingEnabled() {
        return SeApiCompat.isAutoRotateSecondEnabled(getAppContext());
    }

    public void registerObserver(Context context) {
        if (!supportDualScreen()) {
            Log.d("SettingRotationSecondObserver", "registerObserver " + this + " skipped");
            return;
        }
        super.registerObserver(context);
    }
}
