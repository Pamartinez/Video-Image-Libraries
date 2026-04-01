package com.samsung.android.gallery.module.mdebase.utils;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeMetadataParser {
    public static int getDurationFromMetadata(String str) {
        return getIntegerDataFromMetadata("duration", str);
    }

    public static String getEditedFilePathFromInstantMetadata(String str) {
        return getStringDataFromMetadata("editedSharedItemPath", str);
    }

    public static String getInstantThumbnailPathFromInstantMetadata(String str) {
        return getStringDataFromMetadata("instantThumbnailPath", str);
    }

    private static int getIntegerDataFromMetadata(String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            String[] split = str2.split(";");
            int length = split.length;
            String str3 = null;
            int i2 = 0;
            while (i2 < length) {
                String str4 = split[i2];
                if (str.equals(str3)) {
                    try {
                        return Integer.parseInt(str4);
                    } catch (NumberFormatException unused) {
                        Log.she("MdeMetadataParser", "wrong db string :".concat(str2));
                    }
                } else {
                    i2++;
                    str3 = str4;
                }
            }
        }
        return 0;
    }

    public static int getOrientationFromMetadata(String str) {
        return getIntegerDataFromMetadata("orientation", str);
    }

    public static int getOrientationTagFromMetadata(String str) {
        return getIntegerDataFromMetadata("orientation_tag", str);
    }

    private static String getStringDataFromMetadata(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str2.split(";");
        int i2 = 0;
        while (i2 < split.length) {
            int i7 = i2 + 1;
            try {
                if (str.equals(split[i2])) {
                    return split[i7];
                }
                i2 += 2;
            } catch (Exception unused) {
                Log.she("MdeMetadataParser", "wrong db string :".concat(str2));
            }
        }
        return "";
    }
}
