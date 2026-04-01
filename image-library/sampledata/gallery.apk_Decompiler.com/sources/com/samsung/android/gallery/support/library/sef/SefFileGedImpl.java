package com.samsung.android.gallery.support.library.sef;

import android.text.TextUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefFileGedImpl implements SefFileCompat {
    public int addData(File file, String str, byte[] bArr, int i2) {
        return addData(file, str, bArr, i2, 1);
    }

    public boolean deleteAllData(File file) {
        if (file == null) {
            return false;
        }
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.length() <= 0) {
            return false;
        }
        SefParser unpack = new SefParser(canonicalPath).unpack();
        if (!unpack.deleteAllData() || !unpack.apply()) {
            return false;
        }
        return true;
    }

    public boolean deleteData(File file, String str) {
        if (file == null || str == null || str.length() <= 0) {
            return false;
        }
        SefParser unpack = new SefParser(file.getCanonicalPath()).unpack();
        if (!unpack.deleteData(str) || !unpack.apply()) {
            return false;
        }
        return true;
    }

    public byte[] getData(File file, String str) {
        if (file == null || str == null || str.length() <= 0) {
            return null;
        }
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.length() > 0) {
            return new SefParser(canonicalPath).unpack().getData(str);
        }
        return null;
    }

    public int getDataType(File file, String str) {
        if (file == null) {
            return -1;
        }
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.length() > 0) {
            return new SefParser(canonicalPath).unpack().getDataType(str);
        }
        return -1;
    }

    public int[] getDataTypeArray(File file) {
        if (file != null) {
            String canonicalPath = file.getCanonicalPath();
            if (canonicalPath.length() > 0) {
                return SefParser.getDataTypeArray(canonicalPath);
            }
        }
        return new int[0];
    }

    public String[] getKeyNameArray(File file) {
        if (file != null) {
            String canonicalPath = file.getCanonicalPath();
            if (canonicalPath.length() > 0) {
                return listKeyNames(canonicalPath);
            }
        }
        return new String[0];
    }

    public boolean hasData(File file, String str) {
        if (file == null || str == null || str.length() <= 0) {
            return false;
        }
        SefParser of2 = SefParser.of(file);
        if (!of2.available() || !of2.hasData(str)) {
            return false;
        }
        return true;
    }

    public boolean hasVideoTimeZoneOffset(String str) {
        return hasData(new File(str), SefInfo.VIDEO_EDITED_UTC_OFFSET);
    }

    public boolean isValidFile(File file) {
        return SefParser.of(file).available();
    }

    public String[] listKeyNames(String str) {
        return SefParser.getKeyNameArray(str);
    }

    public int addData(File file, String str, byte[] bArr) {
        return addData(file, str, bArr, SefInfo.toKeyCode(str), 1);
    }

    public int addData(File file, String str, byte[] bArr, int i2, String str2) {
        return addData(file, str, bArr, i2, (TextUtils.isEmpty(str2) || (!str2.endsWith(IFormat.FORMAT_HEIC) && !str2.endsWith("heif"))) ? 1 : 3);
    }

    public int addData(File file, String str, byte[] bArr, int i2, int i7) {
        if (file == null || str == null || str.isEmpty() || bArr == null || bArr.length <= 0) {
            return 0;
        }
        SefParser of2 = SefParser.of(file.getCanonicalPath());
        if (!of2.addData(str, i2, bArr) || !of2.apply()) {
            return 0;
        }
        return 1;
    }
}
