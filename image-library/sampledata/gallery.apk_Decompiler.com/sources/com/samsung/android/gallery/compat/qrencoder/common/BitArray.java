package com.samsung.android.gallery.compat.qrencoder.common;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    private void ensureCapacity(int i2) {
        if (i2 > this.bits.length * 32) {
            int[] makeArray = makeArray(i2);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            this.bits = makeArray;
        }
    }

    private static int[] makeArray(int i2) {
        return new int[((i2 + 31) / 32)];
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.bits;
            int i2 = this.size;
            int i7 = i2 / 32;
            iArr[i7] = (1 << (i2 & 31)) | iArr[i7];
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i2 = bitArray.size;
        ensureCapacity(this.size + i2);
        for (int i7 = 0; i7 < i2; i7++) {
            appendBit(bitArray.get(i7));
        }
    }

    public void appendBits(int i2, int i7) {
        if (i7 < 0 || i7 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i7);
        while (i7 > 0) {
            boolean z = true;
            if (((i2 >> (i7 - 1)) & 1) != 1) {
                z = false;
            }
            appendBit(z);
            i7--;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        if (this.size != bitArray.size || !Arrays.equals(this.bits, bitArray.bits)) {
            return false;
        }
        return true;
    }

    public boolean get(int i2) {
        if ((this.bits[i2 / 32] & (1 << (i2 & 31))) != 0) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return Arrays.hashCode(this.bits) + (this.size * 31);
    }

    public void toBytes(int i2, byte[] bArr, int i7, int i8) {
        for (int i10 = 0; i10 < i8; i10++) {
            int i11 = 0;
            for (int i12 = 0; i12 < 8; i12++) {
                if (get(i2)) {
                    i11 |= 1 << (7 - i12);
                }
                i2++;
            }
            bArr[i7 + i10] = (byte) i11;
        }
    }

    public String toString() {
        char c5;
        int i2 = this.size;
        StringBuilder sb2 = new StringBuilder((i2 / 8) + i2 + 1);
        for (int i7 = 0; i7 < this.size; i7++) {
            if ((i7 & 7) == 0) {
                sb2.append(' ');
            }
            if (get(i7)) {
                c5 = 'X';
            } else {
                c5 = '.';
            }
            sb2.append(c5);
        }
        return sb2.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.size == bitArray.size) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.bits;
                if (i2 < iArr.length) {
                    iArr[i2] = iArr[i2] ^ bitArray.bits[i2];
                    i2++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    public BitArray clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public BitArray(int[] iArr, int i2) {
        this.bits = iArr;
        this.size = i2;
    }
}
