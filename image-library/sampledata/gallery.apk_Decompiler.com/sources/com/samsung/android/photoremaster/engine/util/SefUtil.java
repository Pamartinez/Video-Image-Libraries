package com.samsung.android.photoremaster.engine.util;

import android.util.Log;
import com.samsung.android.media.SemExtendedFormat;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefUtil {
    private static final String TAG = "Remaster-SefUtil";

    public static boolean addSEFData(String str, String str2, byte[] bArr, int i2) {
        return addSEFData(new File(str), str2, bArr, i2);
    }

    public static boolean copyAllSEFData(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "copyAllSefData - E");
        boolean copyAllSEFData = copyAllSEFData(new File(str), new File(str2));
        Log.d(str3, "copyAllSefData - X");
        return copyAllSEFData;
    }

    public static boolean deleteAllSEFData(String str) {
        return deleteAllSEFData(new File(str));
    }

    public static boolean deleteSEFData(String str, String str2) {
        return deleteSEFData(new File(str), str2);
    }

    public static byte[] getSEFData(String str, String str2) {
        return getSEFData(new File(str), str2);
    }

    public static int getSEFDataCount(String str) {
        return getSEFDataCount(new File(str));
    }

    public static int getSEFDataType(String str, String str2) {
        return getSEFDataType(new File(str), str2);
    }

    public static boolean hasSEFData(String str, String str2) {
        return hasSEFData(new File(str), str2);
    }

    public static boolean addSEFData(File file, String str, byte[] bArr, int i2) {
        try {
            if (SemExtendedFormat.addData(file, str, bArr, i2, 1) == 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAllSEFData(File file) {
        try {
            return SemExtendedFormat.deleteAllData(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSEFData(File file, String str) {
        try {
            return SemExtendedFormat.deleteData(file, str);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] getSEFData(File file, String str) {
        try {
            return SemExtendedFormat.getData(file, str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getSEFDataCount(File file) {
        try {
            return SemExtendedFormat.getDataCount(file);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getSEFDataType(File file, String str) {
        try {
            return SemExtendedFormat.getDataType(file, str);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean hasSEFData(File file, String str) {
        try {
            if (SemExtendedFormat.hasData(file, str)) {
                String str2 = TAG;
                Log.i(str2, "This file has keyName : " + str);
                return true;
            }
            String str3 = TAG;
            Log.i(str3, "This file doesn't have keyName : " + str);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyAllSEFData(File file, File file2) {
        try {
            return SemExtendedFormat.copyAllData(file, file2) == 1;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
