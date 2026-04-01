package com.samsung.android.gallery.support.library.sef;

import android.util.Log;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SefFileCompat {
    int addData(File file, SefInfo sefInfo, byte[] bArr) {
        if (sefInfo != null) {
            try {
                return addData(file, sefInfo.keyName, bArr, sefInfo.keyCode);
            } catch (IOException e) {
                Log.e("SefFileCompat", "addData " + sefInfo + " failed. e=" + e.getMessage());
            }
        }
        return 0;
    }

    int addData(File file, String str, byte[] bArr);

    int addData(File file, String str, byte[] bArr, int i2);

    int addData(File file, String str, byte[] bArr, int i2, String str2);

    boolean deleteAllData(File file);

    boolean deleteData(File file, String str);

    byte[] getData(File file, String str);

    int getDataType(File file, String str);

    int[] getDataTypeArray(File file);

    String[] getKeyNameArray(File file);

    boolean hasData(File file, SefInfo sefInfo) {
        try {
            return hasData(file, sefInfo.keyName);
        } catch (IOException e) {
            Log.e("SefFileCompat", "hasData " + sefInfo + " failed. e=" + e.getMessage());
            return false;
        }
    }

    boolean hasData(File file, String str);

    boolean hasVideoTimeZoneOffset(String str);

    boolean isValidFile(File file);
}
