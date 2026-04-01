package com.samsung.android.gallery.compat.qrencoder.common;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(int i2, int i7) {
        if (i2 < 1 || i7 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i2;
        this.height = i7;
        int i8 = (i2 + 31) / 32;
        this.rowSize = i8;
        this.bits = new int[(i8 * i7)];
    }

    private String buildToString(String str, String str2, String str3) {
        String str4;
        StringBuilder sb2 = new StringBuilder((this.width + 1) * this.height);
        for (int i2 = 0; i2 < this.height; i2++) {
            for (int i7 = 0; i7 < this.width; i7++) {
                if (get(i7, i2)) {
                    str4 = str;
                } else {
                    str4 = str2;
                }
                sb2.append(str4);
            }
            sb2.append(str3);
        }
        return sb2.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        if (this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits)) {
            return true;
        }
        return false;
    }

    public boolean get(int i2, int i7) {
        int i8 = i7 * this.rowSize;
        if (((this.bits[(i2 / 32) + i8] >>> (i2 & 31)) & 1) != 0) {
            return true;
        }
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i2 = this.width;
        return Arrays.hashCode(this.bits) + (((((((i2 * 31) + i2) * 31) + this.height) * 31) + this.rowSize) * 31);
    }

    public void setRegion(int i2, int i7, int i8, int i10) {
        if (i7 < 0 || i2 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i10 < 1 || i8 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i11 = i8 + i2;
            int i12 = i10 + i7;
            if (i12 > this.height || i11 > this.width) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i7 < i12) {
                int i13 = this.rowSize * i7;
                for (int i14 = i2; i14 < i11; i14++) {
                    int[] iArr = this.bits;
                    int i15 = (i14 / 32) + i13;
                    iArr[i15] = iArr[i15] | (1 << (i14 & 31));
                }
                i7++;
            }
        }
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public BitMatrix clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }

    public String toString(String str, String str2) {
        return buildToString(str, str2, "\n");
    }

    private BitMatrix(int i2, int i7, int i8, int[] iArr) {
        this.width = i2;
        this.height = i7;
        this.rowSize = i8;
        this.bits = iArr;
    }
}
