package com.samsung.android.gallery.compat.qrencoder.common.reedsolomon;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GenericGF {
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int i2, int i7, int i8) {
        this.primitive = i2;
        this.size = i7;
        this.generatorBase = i8;
        this.expTable = new int[i7];
        this.logTable = new int[i7];
        int i10 = 1;
        for (int i11 = 0; i11 < i7; i11++) {
            this.expTable[i11] = i10;
            i10 *= 2;
            if (i10 >= i7) {
                i10 = (i10 ^ i2) & (i7 - 1);
            }
        }
        for (int i12 = 0; i12 < i7 - 1; i12++) {
            this.logTable[this.expTable[i12]] = i12;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    public static int addOrSubtract(int i2, int i7) {
        return i2 ^ i7;
    }

    public GenericGFPoly buildMonomial(int i2, int i7) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (i7 == 0) {
            return this.zero;
        } else {
            int[] iArr = new int[(i2 + 1)];
            iArr[0] = i7;
            return new GenericGFPoly(this, iArr);
        }
    }

    public int exp(int i2) {
        return this.expTable[i2];
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public GenericGFPoly getZero() {
        return this.zero;
    }

    public int inverse(int i2) {
        if (i2 != 0) {
            return this.expTable[(this.size - this.logTable[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int log(int i2) {
        if (i2 != 0) {
            return this.logTable[i2];
        }
        throw new IllegalArgumentException();
    }

    public int multiply(int i2, int i7) {
        if (i2 == 0 || i7 == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[i2] + iArr2[i7]) % (this.size - 1)];
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GF(0x");
        sb2.append(Integer.toHexString(this.primitive));
        sb2.append(',');
        return j.e(sb2, this.size, ')');
    }
}
