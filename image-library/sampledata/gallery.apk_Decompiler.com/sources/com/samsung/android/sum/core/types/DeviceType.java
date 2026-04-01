package com.samsung.android.sum.core.types;

import android.os.SemSystemProperties;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DeviceType {
    NONE,
    FLIP_7,
    FOLD_7;
    
    private static volatile DeviceType deviceType;

    public static DeviceType currentDevice() {
        if (deviceType == null) {
            synchronized (DeviceType.class) {
                try {
                    if (deviceType == null) {
                        String upperCase = ((String) Optional.ofNullable(SemSystemProperties.get("ro.product.model")).orElse("n/a")).toUpperCase();
                        if (upperCase.contains("SM-F766")) {
                            deviceType = FLIP_7;
                        } else if (upperCase.contains("SM-F966")) {
                            deviceType = FOLD_7;
                        } else {
                            deviceType = NONE;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return deviceType;
    }
}
