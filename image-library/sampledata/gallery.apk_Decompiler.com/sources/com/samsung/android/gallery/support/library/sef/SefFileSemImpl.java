package com.samsung.android.gallery.support.library.sef;

import android.text.TextUtils;
import com.samsung.android.media.SemExtendedFormat;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefFileSemImpl implements SefFileCompat {
    public int addData(File file, String str, byte[] bArr, int i2) {
        return SemExtendedFormat.addData(file, str, bArr, i2, 1);
    }

    public boolean deleteAllData(File file) {
        return SemExtendedFormat.deleteAllData(file);
    }

    public boolean deleteData(File file, String str) {
        return SemExtendedFormat.deleteData(file, str);
    }

    public byte[] getData(File file, String str) {
        return SemExtendedFormat.getData(file, str);
    }

    public int getDataType(File file, String str) {
        return SemExtendedFormat.getDataType(file, str);
    }

    public int[] getDataTypeArray(File file) {
        return SemExtendedFormat.getDataTypeArray(file);
    }

    public String[] getKeyNameArray(File file) {
        return SemExtendedFormat.getKeyNameArray(file);
    }

    public boolean hasData(File file, String str) {
        return SemExtendedFormat.hasData(file, str);
    }

    public boolean hasVideoTimeZoneOffset(String str) {
        return hasData(new File(str), SefInfo.VIDEO_EDITED_UTC_OFFSET);
    }

    public boolean isValidFile(File file) {
        return SemExtendedFormat.isValidFile(file);
    }

    public int addData(File file, String str, byte[] bArr) {
        return SemExtendedFormat.addData(file, str, bArr, SefInfo.toKeyCode(str), 1);
    }

    public int addData(File file, String str, byte[] bArr, int i2, String str2) {
        return SemExtendedFormat.addData(file, str, bArr, i2, (TextUtils.isEmpty(str2) || (!str2.endsWith(IFormat.FORMAT_HEIC) && !str2.endsWith("heif"))) ? 1 : 3);
    }
}
