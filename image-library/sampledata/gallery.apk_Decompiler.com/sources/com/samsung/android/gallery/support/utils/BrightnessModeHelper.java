package com.samsung.android.gallery.support.utils;

import android.app.Activity;
import com.samsung.android.gallery.support.library.SeApiCompat;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BrightnessModeHelper {
    private static final AtomicBoolean sIsLowerLimitSet = new AtomicBoolean(false);

    public static void clearBrightnessLimit(Activity activity) {
        if (!sIsLowerLimitSet.compareAndSet(true, false)) {
            Log.d("BrightnessModeHelper", "clearBrightnessLimit (skip - already cleared");
        } else {
            ThreadUtil.postOnBgThreadDelayed(new C0666d(activity, 0), 200);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setBrightnessLowerBoundLimit$0(Activity activity, int i2) {
        if (!sIsLowerLimitSet.get()) {
            Log.w("BrightnessModeHelper", "setBrightnessLowerBoundLimit (skip - already cleared)");
        } else if (SeApiCompat.isBrightnessModeAutomatic(activity)) {
            SeApiCompat.setAutoBrightnessLimit(activity, SeApiCompat.getSystemPropertiesInt("persist.sys.default_brightness", i2), -1);
        } else {
            Log.d("BrightnessModeHelper", "setBrightnessLowerBoundLimit (skip - manual)");
        }
    }

    public static void releaseBrightnessLimit() {
        sIsLowerLimitSet.compareAndSet(true, false);
    }

    public static void setBrightnessLowerBoundLimit(Activity activity, int i2) {
        if (activity.isInMultiWindowMode()) {
            Log.d("BrightnessModeHelper", "setBrightnessLowerBoundLimit (skip - multi window)");
        } else if (!sIsLowerLimitSet.compareAndSet(false, true)) {
            Log.d("BrightnessModeHelper", "setBrightnessLowerBoundLimit (skip - already set)");
        } else {
            ThreadUtil.postOnBgThreadDelayed(new C0665c(activity, i2, 0), 100);
        }
    }
}
