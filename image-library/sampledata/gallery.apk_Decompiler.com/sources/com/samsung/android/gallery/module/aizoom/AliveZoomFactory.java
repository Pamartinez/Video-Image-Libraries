package com.samsung.android.gallery.module.aizoom;

import A.a;
import android.text.TextUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AliveZoomFactory {
    public static final boolean SKIP_ALIVE_ZOOM_OUTPUT = PocFeatures.isEnabled(PocFeatures.SkipAliveZoomOutput);

    public static ImageScaleInterface create() {
        try {
            if (SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
                return new SCSImageScaler(AppResources.getAppContext());
            }
            return new AliveServiceImageScaler(AppResources.getAppContext());
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("create failed. e="), "AliveZoomFactory");
            return null;
        }
    }

    public static String getEngineType() {
        String floatingFeatureString = SeApiCompat.getFloatingFeatureString("SEC_FLOATING_FEATURE_GALLERY_CONFIG_ZOOM_TYPE");
        if (TextUtils.isEmpty(floatingFeatureString) || "NONE".equalsIgnoreCase(floatingFeatureString)) {
            return "ZOOM_2K";
        }
        return floatingFeatureString;
    }
}
