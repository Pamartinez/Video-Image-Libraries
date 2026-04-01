package com.samsung.android.gallery.support.utils;

import N2.j;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PerformanceLog {
    static boolean enabled = PocFeatures.isEnabled(PocFeatures.PerformanceLog);

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean setEnabled(boolean z) {
        enabled = z;
        try {
            Log.d("PerformanceLog", "setEnabled " + z);
            if (z) {
                File file = new File(AppResources.getConfigPath(), "performance.log");
                FileUtils.makeDirectoryIfAbsent(file.getParentFile());
                return file.createNewFile();
            }
            new File(AppResources.getConfigPath(), "performance.log").delete();
            return true;
        } catch (IOException e) {
            j.r(e, new StringBuilder("setPerformanceLogEnabled failed e="), "PerformanceLog");
            return false;
        }
    }
}
