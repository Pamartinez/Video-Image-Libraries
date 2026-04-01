package com.samsung.android.imagetranslation.common;

import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Config {
    public static final boolean IS_USER_DEBUG;
    public static final int KEY_LTT_ENGINE_VERSION = 1;
    public static final double MARGIN_RATIO_X = 0.075d;
    public static final double MARGIN_RATIO_Y = 0.1d;
    public static final int MAX_RESOLUTION_SUPPORTED = 16000;
    public static final int MAX_UPSCALE_LENGTH = 1080;
    public static final double MIN_ASPECT_RATIO = 7.0d;
    public static final int RESIZE_THRESHOLD = 512;

    static {
        boolean z;
        String str = Build.TYPE;
        if (str.contentEquals("eng") || str.contentEquals("userdebug")) {
            z = true;
        } else {
            z = false;
        }
        IS_USER_DEBUG = z;
    }
}
