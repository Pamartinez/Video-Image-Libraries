package com.samsung.o3dp.lib3dphotography.utils;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugUtil {
    public static final boolean DEBUG_BINARY;
    private static final String TAG = "DebugUtil";

    static {
        boolean z;
        String str = Build.TYPE;
        if ("eng".equals(str) || "userdebug".equals(str)) {
            z = true;
        } else {
            z = false;
        }
        DEBUG_BINARY = z;
    }

    public static final String getDebugOutputDirectory() {
        String str = Environment.getExternalStorageDirectory().toString() + "/Documents/LiveEffectSDK_Debug/";
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.i(TAG, "Make dir failed: " + str);
        }
        return str;
    }
}
