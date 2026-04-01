package com.samsung.android.gallery.widget.utils;

import android.app.Activity;
import android.content.Context;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BackPressUtils {
    public static boolean supportPredictiveBack(Context context) {
        if (!(context instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) context;
        boolean isInMultiWindowMode = activity.isInMultiWindowMode();
        boolean isGestureNaviBar = DeviceInfo.isGestureNaviBar(activity);
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) || ResourceCompat.isTablet(context) || isInMultiWindowMode || !isGestureNaviBar) {
            return false;
        }
        return true;
    }
}
