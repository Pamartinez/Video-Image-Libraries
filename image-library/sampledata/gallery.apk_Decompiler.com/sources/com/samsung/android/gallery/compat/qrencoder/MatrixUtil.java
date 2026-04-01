package com.samsung.android.gallery.compat.qrencoder;

import com.samsung.android.gallery.compat.qrencoder.common.BitArray;
import com.samsung.android.gallery.compat.qrencoder.common.Version;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MatrixUtil {
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, MOCRLang.MALAYALAM}};
    private static final int[][] POSITION_DETECTION_PATTERN = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] TYPE_INFO_COORDINATES;

    static {
        int[] iArr = {8, 8};
        int[] iArr2 = {8, 5};
        int[] iArr3 = {7, 8};
        TYPE_INFO_COORDINATES = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, iArr2, new int[]{8, 7}, iArr, iArr3, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    }

    public static void buildMatrix(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int i2, ByteMatrix byteMatrix) {
        clearMatrix(byteMatrix);
        embedBasicPatterns(version, byteMatrix);
        embedTypeInfo(errorCorrectionLevel, i2, byteMatrix);
        maybeEmbedVersionInfo(version, byteMatrix);
        embedDataBits(bitArray, i2, byteMatrix);
    }

    public static int calculateBCHCode(int i2, int i7) {
        if (i7 != 0) {
            int findMSBSet = findMSBSet(i7);
            int i8 = i2 << (findMSBSet - 1);
            while (findMSBSet(i8) >= findMSBSet) {
                i8 ^= i7 << (findMSBSet(i8) - findMSBSet);
            }
            return i8;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    public static void clearMatrix(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }

    public static void embedBasicPatterns(Version version, ByteMatrix byteMatrix) {
        embedPositionDetectionPatternsAndSeparators(byteMatrix);
        embedDarkDotAtLeftBottomCorner(byteMatrix);
        maybeEmbedPositionAdjustmentPatterns(version, byteMatrix);
        embedTimingPatterns(byteMatrix);
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix byteMatrix) {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) != 0) {
            byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    public static void embedDataBits(BitArray bitArray, int i2, ByteMatrix byteMatrix) {
        boolean z;
        int width = byteMatrix.getWidth() - 1;
        int height = byteMatrix.getHeight() - 1;
        int i7 = 0;
        int i8 = -1;
        while (width > 0) {
            if (width == 6) {
                width--;
            }
            while (height >= 0 && height < byteMatrix.getHeight()) {
                for (int i10 = 0; i10 < 2; i10++) {
                    int i11 = width - i10;
                    if (isEmpty(byteMatrix.get(i11, height))) {
                        if (i7 < bitArray.getSize()) {
                            z = bitArray.get(i7);
                            i7++;
                        } else {
                            z = false;
                        }
                        if (i2 != -1 && MaskUtil.getDataMaskBit(i2, i11, height)) {
                            z = !z;
                        }
                        byteMatrix.set(i11, height, z);
                    }
                }
                height += i8;
            }
            i8 = -i8;
            height += i8;
            width -= 2;
        }
        if (i7 != bitArray.getSize()) {
            throw new WriterException("Not all bits consumed: " + i7 + '/' + bitArray.getSize());
        }
    }

    private static void embedHorizontalSeparationPattern(int i2, int i7, ByteMatrix byteMatrix) {
        int i8 = 0;
        while (i8 < 8) {
            int i10 = i2 + i8;
            if (isEmpty(byteMatrix.get(i10, i7))) {
                byteMatrix.set(i10, i7, 0);
                i8++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedPositionAdjustmentPattern(int i2, int i7, ByteMatrix byteMatrix) {
        for (int i8 = 0; i8 < 5; i8++) {
            int[] iArr = POSITION_ADJUSTMENT_PATTERN[i8];
            for (int i10 = 0; i10 < 5; i10++) {
                byteMatrix.set(i2 + i10, i7 + i8, iArr[i10]);
            }
        }
    }

    private static void embedPositionDetectionPattern(int i2, int i7, ByteMatrix byteMatrix) {
        for (int i8 = 0; i8 < 7; i8++) {
            int[] iArr = POSITION_DETECTION_PATTERN[i8];
            for (int i10 = 0; i10 < 7; i10++) {
                byteMatrix.set(i2 + i10, i7 + i8, iArr[i10]);
            }
        }
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix byteMatrix) {
        int length = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, byteMatrix);
        embedPositionDetectionPattern(byteMatrix.getWidth() - length, 0, byteMatrix);
        embedPositionDetectionPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        embedHorizontalSeparationPattern(0, 7, byteMatrix);
        embedHorizontalSeparationPattern(byteMatrix.getWidth() - 8, 7, byteMatrix);
        embedHorizontalSeparationPattern(0, byteMatrix.getWidth() - 8, byteMatrix);
        embedVerticalSeparationPattern(7, 0, byteMatrix);
        embedVerticalSeparationPattern(byteMatrix.getHeight() - 8, 0, byteMatrix);
        embedVerticalSeparationPattern(7, byteMatrix.getHeight() - 7, byteMatrix);
    }

    private static void embedTimingPatterns(ByteMatrix byteMatrix) {
        int i2 = 8;
        while (i2 < byteMatrix.getWidth() - 8) {
            int i7 = i2 + 1;
            int i8 = i7 % 2;
            if (isEmpty(byteMatrix.get(i2, 6))) {
                byteMatrix.set(i2, 6, i8);
            }
            if (isEmpty(byteMatrix.get(6, i2))) {
                byteMatrix.set(6, i2, i8);
            }
            i2 = i7;
        }
    }

    public static void embedTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, int i2, ByteMatrix byteMatrix) {
        int i7;
        BitArray bitArray = new BitArray();
        makeTypeInfoBits(errorCorrectionLevel, i2, bitArray);
        for (int i8 = 0; i8 < bitArray.getSize(); i8++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - i8);
            int[] iArr = TYPE_INFO_COORDINATES[i8];
            byteMatrix.set(iArr[0], iArr[1], z);
            int i10 = 8;
            if (i8 < 8) {
                i7 = 8;
                i10 = (byteMatrix.getWidth() - i8) - 1;
            } else {
                i7 = (i8 - 8) + (byteMatrix.getHeight() - 7);
            }
            byteMatrix.set(i10, i7, z);
        }
    }

    private static void embedVerticalSeparationPattern(int i2, int i7, ByteMatrix byteMatrix) {
        int i8 = 0;
        while (i8 < 7) {
            int i10 = i7 + i8;
            if (isEmpty(byteMatrix.get(i2, i10))) {
                byteMatrix.set(i2, i10, 0);
                i8++;
            } else {
                throw new WriterException();
            }
        }
    }

    public static int findMSBSet(int i2) {
        return 32 - Integer.numberOfLeadingZeros(i2);
    }

    private static boolean isEmpty(int i2) {
        if (i2 == -1) {
            return true;
        }
        return false;
    }

    public static void makeTypeInfoBits(ErrorCorrectionLevel errorCorrectionLevel, int i2, BitArray bitArray) {
        if (QRCode.isValidMaskPattern(i2)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i2;
            bitArray.appendBits(bits, 5);
            bitArray.appendBits(calculateBCHCode(bits, 1335), 10);
            BitArray bitArray2 = new BitArray();
            bitArray2.appendBits(21522, 15);
            bitArray.xor(bitArray2);
            if (bitArray.getSize() != 15) {
                throw new WriterException("should not happen but we got: " + bitArray.getSize());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    public static void makeVersionInfoBits(Version version, BitArray bitArray) {
        bitArray.appendBits(version.getVersionNumber(), 6);
        bitArray.appendBits(calculateBCHCode(version.getVersionNumber(), 7973), 12);
        if (bitArray.getSize() != 18) {
            throw new WriterException("should not happen but we got: " + bitArray.getSize());
        }
    }

    private static void maybeEmbedPositionAdjustmentPatterns(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() >= 2) {
            int[] iArr = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[version.getVersionNumber() - 1];
            for (int i2 : iArr) {
                if (i2 >= 0) {
                    for (int i7 : iArr) {
                        if (i7 >= 0 && isEmpty(byteMatrix.get(i7, i2))) {
                            embedPositionAdjustmentPattern(i7 - 2, i2 - 2, byteMatrix);
                        }
                    }
                }
            }
        }
    }

    public static void maybeEmbedVersionInfo(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() >= 7) {
            BitArray bitArray = new BitArray();
            makeVersionInfoBits(version, bitArray);
            int i2 = 17;
            for (int i7 = 0; i7 < 6; i7++) {
                for (int i8 = 0; i8 < 3; i8++) {
                    boolean z = bitArray.get(i2);
                    i2--;
                    byteMatrix.set(i7, (byteMatrix.getHeight() - 11) + i8, z);
                    byteMatrix.set((byteMatrix.getHeight() - 11) + i8, i7, z);
                }
            }
        }
    }
}
