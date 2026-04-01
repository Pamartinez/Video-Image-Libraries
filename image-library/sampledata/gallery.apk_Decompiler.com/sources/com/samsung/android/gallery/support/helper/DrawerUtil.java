package com.samsung.android.gallery.support.helper;

import android.app.Activity;
import android.content.Context;
import com.samsung.android.gallery.support.R$bool;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DrawerUtil {
    static final boolean IS_ROS = SdkConfig.atLeast(SdkConfig.SEM.R);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TabletFeature {
        static final boolean enabled;

        static {
            boolean z;
            if (Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD) || Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
                z = true;
            } else {
                z = false;
            }
            enabled = z;
        }
    }

    public static boolean isDexMode(Context context) {
        return DeviceInfo.isDexMode(context);
    }

    public static boolean isDrawerDefaultOpen(Context context) {
        return context != null && context.getResources().getBoolean(R$bool.isDrawerDefaultOpened);
    }

    public static boolean supportDrawerLayout(Context context) {
        if (IS_ROS) {
            return TabletFeature.enabled || isDexMode(context);
        }
        return false;
    }

    public static boolean supportEssentialAlbumsLayout(Context context) {
        return SdkConfig.atLeast(SdkConfig.SEM.U) || !supportDrawerLayout(context);
    }

    public static boolean isDrawerDefaultOpen(Blackboard blackboard) {
        return blackboard != null && isDrawerDefaultOpen((Context) (Activity) blackboard.read("data://activity"));
    }

    public static boolean supportDrawerLayout(Blackboard blackboard) {
        return blackboard != null && supportDrawerLayout((Context) (Activity) blackboard.read("data://activity"));
    }

    public static boolean supportEssentialAlbumsLayout(Blackboard blackboard) {
        return SdkConfig.atLeast(SdkConfig.SEM.U) || !supportDrawerLayout(blackboard);
    }
}
