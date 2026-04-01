package com.samsung.android.gallery.support.utils;

import android.os.Environment;
import android.os.StatFs;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StorageUtil {
    private static final File EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory();
    private static volatile boolean sLowStorage = false;

    public static boolean checkLowStorage(boolean z) {
        long j2;
        try {
            StatFs statFs = new StatFs(EXTERNAL_STORAGE_DIRECTORY.toString());
            long availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            long blockCountLong = statFs.getBlockCountLong() * statFs.getBlockSizeLong();
            if (z) {
                j2 = 52428800;
            } else {
                j2 = 524288000;
            }
            Log.i("StorageUtil", "Storage(MB){T=" + (blockCountLong >> 20) + ",A=" + (availableBlocksLong >> 20) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + (j2 >> 20) + "}");
            if (j2 > availableBlocksLong) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            Log.e((CharSequence) "StorageUtil", "checkLowStorage failed {" + EXTERNAL_STORAGE_DIRECTORY.toString() + "}", (Throwable) e);
            return false;
        }
    }

    public static double getStorageUsage() {
        try {
            StatFs statFs = new StatFs(EXTERNAL_STORAGE_DIRECTORY.toString());
            long availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            long blockCountLong = statFs.getBlockCountLong() * statFs.getBlockSizeLong();
            return ((double) (blockCountLong - availableBlocksLong)) / ((double) blockCountLong);
        } catch (Exception e) {
            Log.e((CharSequence) "StorageUtil", "getStorageUsage failed {" + EXTERNAL_STORAGE_DIRECTORY.toString() + "}", (Throwable) e);
            return MapUtil.INVALID_LOCATION;
        }
    }

    public static boolean isLowStorage() {
        return sLowStorage;
    }

    public static void setLowStorage(boolean z) {
        sLowStorage = z;
    }
}
