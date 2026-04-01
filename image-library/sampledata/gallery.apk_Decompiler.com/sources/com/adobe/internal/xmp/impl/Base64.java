package com.adobe.internal.xmp.impl;

import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Base64 {
    private static final byte EQUAL = -3;
    private static final byte INVALID = -1;
    private static final byte WHITESPACE = -2;
    private static byte[] ascii = new byte[ScoverState.TYPE_NFC_SMART_COVER];
    private static byte[] base64 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    static {
        int i2 = 0;
        for (int i7 = 0; i7 < 255; i7++) {
            ascii[i7] = INVALID;
        }
        while (true) {
            byte[] bArr = base64;
            if (i2 < bArr.length) {
                ascii[bArr[i2]] = (byte) i2;
                i2++;
            } else {
                byte[] bArr2 = ascii;
                bArr2[9] = WHITESPACE;
                bArr2[10] = WHITESPACE;
                bArr2[13] = WHITESPACE;
                bArr2[32] = WHITESPACE;
                bArr2[61] = EQUAL;
                return;
            }
        }
    }

    public static final byte[] decode(byte[] bArr) {
        int i2 = 0;
        int i7 = 0;
        for (byte b : bArr) {
            byte b5 = ascii[b];
            if (b5 >= 0) {
                bArr[i7] = b5;
                i7++;
            } else if (b5 == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (i7 > 0 && bArr[i7 - 1] == -3) {
            i7--;
        }
        int i8 = (i7 * 3) / 4;
        byte[] bArr2 = new byte[i8];
        int i10 = 0;
        while (i2 < i8 - 2) {
            int i11 = i10 + 1;
            bArr2[i2] = (byte) (((bArr[i10] << 2) & ScoverState.TYPE_NFC_SMART_COVER) | ((bArr[i11] >>> 4) & 3));
            int i12 = i10 + 2;
            bArr2[i2 + 1] = (byte) (((bArr[i11] << 4) & ScoverState.TYPE_NFC_SMART_COVER) | ((bArr[i12] >>> 2) & 15));
            bArr2[i2 + 2] = (byte) (((bArr[i12] << 6) & INVALID) | (bArr[i10 + 3] & 63));
            i10 += 4;
            i2 += 3;
        }
        if (i2 < i8) {
            bArr2[i2] = (byte) (((bArr[i10] << 2) & ScoverState.TYPE_NFC_SMART_COVER) | ((bArr[i10 + 1] >>> 4) & 3));
        }
        int i13 = i2 + 1;
        if (i13 < i8) {
            bArr2[i13] = (byte) (((bArr[i10 + 2] >>> 2) & 15) | ((bArr[i10 + 1] << 4) & ScoverState.TYPE_NFC_SMART_COVER));
        }
        return bArr2;
    }

    public static final byte[] encode(byte[] bArr) {
        return encode(bArr, 0);
    }

    public static final byte[] encode(byte[] bArr, int i2) {
        int i7 = (i2 / 4) * 4;
        int i8 = 0;
        if (i7 < 0) {
            i7 = 0;
        }
        int length = ((bArr.length + 2) / 3) * 4;
        if (i7 > 0) {
            length = C0086a.D(length, 1, i7, length);
        }
        byte[] bArr2 = new byte[length];
        int i10 = 0;
        int i11 = 0;
        while (i8 + 3 <= bArr.length) {
            int i12 = i8 + 2;
            int i13 = (bArr[i8 + 1] & INVALID) << 8;
            i8 += 3;
            byte b = i13 | ((bArr[i8] & INVALID) << 16) | (bArr[i12] & INVALID);
            byte[] bArr3 = base64;
            bArr2[i10] = bArr3[(b & 16515072) >> 18];
            bArr2[i10 + 1] = bArr3[(b & 258048) >> 12];
            bArr2[i10 + 2] = bArr3[(b & 4032) >> 6];
            int i14 = i10 + 4;
            bArr2[i10 + 3] = bArr3[b & 63];
            i11 += 4;
            if (i14 >= length || i7 <= 0 || i11 % i7 != 0) {
                i10 = i14;
            } else {
                i10 += 5;
                bArr2[i14] = 10;
            }
        }
        if (bArr.length - i8 == 2) {
            int i15 = ((bArr[i8 + 1] & INVALID) << 8) | ((bArr[i8] & INVALID) << 16);
            byte[] bArr4 = base64;
            bArr2[i10] = bArr4[(i15 & 16515072) >> 18];
            bArr2[i10 + 1] = bArr4[(i15 & 258048) >> 12];
            bArr2[i10 + 2] = bArr4[(i15 & 4032) >> 6];
            bArr2[i10 + 3] = 61;
            return bArr2;
        }
        if (bArr.length - i8 == 1) {
            int i16 = (bArr[i8] & INVALID) << 16;
            byte[] bArr5 = base64;
            bArr2[i10] = bArr5[(i16 & 16515072) >> 18];
            bArr2[i10 + 1] = bArr5[(i16 & 258048) >> 12];
            bArr2[i10 + 2] = 61;
            bArr2[i10 + 3] = 61;
        }
        return bArr2;
    }

    public static final String decode(String str) {
        return new String(decode(str.getBytes()));
    }

    public static final String encode(String str) {
        return new String(encode(str.getBytes()));
    }
}
