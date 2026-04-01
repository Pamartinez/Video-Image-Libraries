package com.arcsoft.libobjectcapture;

import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArcObjectCaptureUtil {
    public static boolean fileIsExist(String str) {
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        return true;
    }

    public static boolean isSupportObjectCapture() {
        if (!fileIsExist("/system/lib64/libobjectcapture_jni.arcsoft.so") || !fileIsExist("/system/lib64/libobjectcapture.arcsoft.so")) {
            return false;
        }
        return true;
    }
}
