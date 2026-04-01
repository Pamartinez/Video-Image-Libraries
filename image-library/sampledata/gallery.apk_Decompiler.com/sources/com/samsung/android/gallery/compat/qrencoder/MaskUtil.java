package com.samsung.android.gallery.compat.qrencoder;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MaskUtil {
    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int i2;
        int i7;
        byte b;
        if (z) {
            i2 = byteMatrix.getHeight();
        } else {
            i2 = byteMatrix.getWidth();
        }
        if (z) {
            i7 = byteMatrix.getWidth();
        } else {
            i7 = byteMatrix.getHeight();
        }
        byte[][] array = byteMatrix.getArray();
        int i8 = 0;
        for (int i10 = 0; i10 < i2; i10++) {
            byte b5 = -1;
            int i11 = 0;
            for (int i12 = 0; i12 < i7; i12++) {
                if (z) {
                    b = array[i10][i12];
                } else {
                    b = array[i12][i10];
                }
                if (b == b5) {
                    i11++;
                } else {
                    if (i11 >= 5) {
                        i8 += i11 - 2;
                    }
                    i11 = 1;
                    b5 = b;
                }
            }
            if (i11 >= 5) {
                i8 = (i11 - 2) + i8;
            }
        }
        return i8;
    }

    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i2 = 0;
        for (int i7 = 0; i7 < height - 1; i7++) {
            byte[] bArr = array[i7];
            int i8 = 0;
            while (i8 < width - 1) {
                byte b = bArr[i8];
                int i10 = i8 + 1;
                if (b == bArr[i10]) {
                    byte[] bArr2 = array[i7 + 1];
                    if (b == bArr2[i8] && b == bArr2[i10]) {
                        i2++;
                    }
                }
                i8 = i10;
            }
        }
        return i2 * 3;
    }

    public static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i2 = 0;
        for (int i7 = 0; i7 < height; i7++) {
            for (int i8 = 0; i8 < width; i8++) {
                byte[] bArr = array[i7];
                int i10 = i8 + 6;
                if (i10 < width && bArr[i8] == 1 && bArr[i8 + 1] == 0 && bArr[i8 + 2] == 1 && bArr[i8 + 3] == 1 && bArr[i8 + 4] == 1 && bArr[i8 + 5] == 0 && bArr[i10] == 1 && (isWhiteHorizontal(bArr, i8 - 4, i8) || isWhiteHorizontal(bArr, i8 + 7, i8 + 11))) {
                    i2++;
                }
                int i11 = i7 + 6;
                if (i11 < height && array[i7][i8] == 1 && array[i7 + 1][i8] == 0 && array[i7 + 2][i8] == 1 && array[i7 + 3][i8] == 1 && array[i7 + 4][i8] == 1 && array[i7 + 5][i8] == 0 && array[i11][i8] == 1 && (isWhiteVertical(array, i8, i7 - 4, i7) || isWhiteVertical(array, i8, i7 + 7, i7 + 11))) {
                    i2++;
                }
            }
        }
        return i2 * 40;
    }

    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i2 = 0;
        for (int i7 = 0; i7 < height; i7++) {
            byte[] bArr = array[i7];
            for (int i8 = 0; i8 < width; i8++) {
                if (bArr[i8] == 1) {
                    i2++;
                }
            }
        }
        int width2 = byteMatrix.getWidth() * byteMatrix.getHeight();
        return ((Math.abs((i2 * 2) - width2) * 10) / width2) * 10;
    }

    public static boolean getDataMaskBit(int i2, int i7, int i8) {
        int i10;
        switch (i2) {
            case 0:
                i8 += i7;
                break;
            case 1:
                break;
            case 2:
                i10 = i7 % 3;
                break;
            case 3:
                i10 = (i8 + i7) % 3;
                break;
            case 4:
                i10 = ((i7 / 3) + (i8 / 2)) & 1;
                break;
            case 5:
                int i11 = i8 * i7;
                i10 = (i11 & 1) + (i11 % 3);
                break;
            case 6:
                int i12 = i8 * i7;
                i8 = (i12 % 3) + (i12 & 1);
                break;
            case 7:
                i10 = (((i8 * i7) % 3) + ((i8 + i7) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException(C0086a.i(i2, "Invalid mask pattern: "));
        }
        i10 = i8 & 1;
        if (i10 == 0) {
            return true;
        }
        return false;
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int i2, int i7) {
        int min = Math.min(i7, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int i2, int i7, int i8) {
        int min = Math.min(i8, bArr.length);
        for (int max = Math.max(i7, 0); max < min; max++) {
            if (bArr[max][i2] == 1) {
                return false;
            }
        }
        return true;
    }
}
