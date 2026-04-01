package com.samsung.android.gallery.module.extendedformat;

import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SefDualShotFormat extends SefFormat {
    private static byte[] getDataByKey(String str, String str2) {
        return SeApiCompat.getSefFileCompat().getData(new File(str), str2);
    }

    public static boolean isWideImage(String str) {
        if (str != null) {
            try {
                if (getDataByKey(str, SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.keyName) != null) {
                    return true;
                }
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
