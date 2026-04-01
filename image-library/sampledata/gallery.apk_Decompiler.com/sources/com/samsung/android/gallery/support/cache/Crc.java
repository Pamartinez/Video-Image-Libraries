package com.samsung.android.gallery.support.cache;

import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Crc {
    private static final long[] CRC_TABLE = new long[256];

    static {
        long j2;
        for (int i2 = 0; i2 < 256; i2++) {
            long j3 = (long) i2;
            for (int i7 = 0; i7 < 8; i7++) {
                if ((((int) j3) & 1) != 0) {
                    j2 = -7661587058870466123L;
                } else {
                    j2 = 0;
                }
                j3 = (j3 >> 1) ^ j2;
            }
            CRC_TABLE[i2] = j3;
        }
    }

    public static long getCrc64Long(byte[] bArr) {
        long j2 = -1;
        for (byte b : bArr) {
            j2 = (j2 >> 8) ^ CRC_TABLE[(b ^ ((int) j2)) & 255];
        }
        return j2;
    }

    public static long getCrc64Long(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return getCrc64Long(str.getBytes());
    }
}
