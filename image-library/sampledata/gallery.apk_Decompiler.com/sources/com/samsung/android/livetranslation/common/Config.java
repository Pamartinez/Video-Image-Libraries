package com.samsung.android.livetranslation.common;

import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Config {
    public static final int IS_MSER_NEEDED = 8;
    public static boolean IS_PROFILING_ENABLED = true;
    public static final boolean IS_USER_DEBUG;
    public static final int KEY_DEVICE_HEIGHT = 4;
    public static final int KEY_DEVICE_WIDTH = 3;
    public static final int KEY_LTT_ENGINE_API_VERSON = 2;
    public static final int KEY_LTT_ENGINE_VERSION = 1;
    public static final int KEY_ORIGINAL_IMAGE_HEIGHT = 6;
    public static final int KEY_ORIGINAL_IMAGE_WIDTH = 5;
    public static final int KEY_RESIZE_IMAGE_FACTOR = 7;
    public static final double LINE_SPACE_RATIO = 1.0d;
    public static final double MARGIN_RATIO_X = 0.075d;
    public static final double MARGIN_RATIO_Y = 0.1d;
    public static final int SETPROP_SUCCESS = 1;

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
