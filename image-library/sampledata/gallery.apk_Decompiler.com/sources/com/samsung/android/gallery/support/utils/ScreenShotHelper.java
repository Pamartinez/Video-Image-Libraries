package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.provider.Settings;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ScreenShotHelper {
    public static String getScreenshotFolder(Context context) {
        String str;
        try {
            str = Settings.System.getString(context.getContentResolver(), "screenshot_current_save_dir");
        } catch (Exception e) {
            Log.e((CharSequence) "SmartCaptureHelper", "getScreenshotSaveInfoDB() Settings.System", (Throwable) e);
            str = "";
        }
        if (str == null || !str.contains(NumericEnum.SEP)) {
            str = "external_primary:DCIM/Screenshots";
        }
        return replacePrefix(str);
    }

    private static String replacePrefix(String str) {
        return str.replaceFirst("external_primary:", StorageInfo.getInstance(FileUtils.EXTERNAL_STORAGE_DIR).root + "/");
    }
}
