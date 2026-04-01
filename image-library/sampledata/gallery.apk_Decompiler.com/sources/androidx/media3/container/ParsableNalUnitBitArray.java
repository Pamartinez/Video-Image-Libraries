package androidx.media3.container;

import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int i2, int i7) {
        reset(bArr, i2, i7);
    }

    private void assertValidOffset() {
        boolean z;
        int i2;
        int i7 = this.byteOffset;
        if (i7 < 0 || (i7 >= (i2 = this.byteLimit) && !(i7 == i2 && this.bitOffset == 0))) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkState(z);
    }

    private int readExpGolombCodeNum() {
        int i2 = 0;
        int i7 = 0;
        while (!readBit()) {
            i7++;
        }
        int i8 = (1 << i7) - 1;
        if (i7 > 0) {
            i2 = readBits(i7);
        }
        return i8 + i2;
    }

    private boolean shouldSkipByte(int i2) {
        if (2 > i2 || i2 >= this.byteLimit) {
            return false;
        }
        byte[] bArr = this.data;
        if (bArr[i2] == 3 && bArr[i2 - 2] == 0 && bArr[i2 - 1] == 0) {
            return true;
        }
        return false;
    }

    public void byteAlign() {
        int i2 = this.bitOffset;
        if (i2 > 0) {
            skipBits(8 - i2);
        }
    }

    public boolean canReadBits(int i2) {
        int i7 = this.byteOffset;
        int i8 = i2 / 8;
        int i10 = i7 + i8;
        int i11 = (this.bitOffset + i2) - (i8 * 8);
        if (i11 > 7) {
            i10++;
            i11 -= 8;
        }
        while (true) {
            i7++;
            if (i7 > i10 || i10 >= this.byteLimit) {
                int i12 = this.byteLimit;
            } else if (shouldSkipByte(i7)) {
                i10++;
                i7 += 2;
            }
        }
        int i122 = this.byteLimit;
        if (i10 < i122 || (i10 == i122 && i11 == 0)) {
            return true;
        }
        return false;
    }

    public boolean canReadExpGolombCodedNum() {
        boolean z;
        int i2 = this.byteOffset;
        int i7 = this.bitOffset;
        int i8 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i8++;
        }
        if (this.byteOffset == this.byteLimit) {
            z = true;
        } else {
            z = false;
        }
        this.byteOffset = i2;
        this.bitOffset = i7;
        if (z || !canReadBits((i8 * 2) + 1)) {
            return false;
        }
        return true;
    }

    public boolean readBit() {
        boolean z;
        if ((this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0) {
            z = true;
        } else {
            z = false;
        }
        skipBit();
        return z;
    }

    public int readBits(int i2) {
        int i7;
        int i8;
        this.bitOffset += i2;
        int i10 = 0;
        while (true) {
            i7 = this.bitOffset;
            i8 = 2;
            if (i7 <= 8) {
                break;
            }
            int i11 = i7 - 8;
            this.bitOffset = i11;
            byte[] bArr = this.data;
            int i12 = this.byteOffset;
            i10 |= (bArr[i12] & 255) << i11;
            if (!shouldSkipByte(i12 + 1)) {
                i8 = 1;
            }
            this.byteOffset = i12 + i8;
        }
        byte[] bArr2 = this.data;
        int i13 = this.byteOffset;
        int i14 = (-1 >>> (32 - i2)) & (i10 | ((bArr2[i13] & 255) >> (8 - i7)));
        if (i7 == 8) {
            this.bitOffset = 0;
            if (!shouldSkipByte(i13 + 1)) {
                i8 = 1;
            }
            this.byteOffset = i13 + i8;
        }
        assertValidOffset();
        return i14;
    }

    public int readSignedExpGolombCodedInt() {
        int i2;
        int readExpGolombCodeNum = readExpGolombCodeNum();
        if (readExpGolombCodeNum % 2 == 0) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        return ((readExpGolombCodeNum + 1) / 2) * i2;
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public void reset(byte[] bArr, int i2, int i7) {
        this.data = bArr;
        this.byteOffset = i2;
        this.byteLimit = i7;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBit() {
        int i2 = 1;
        int i7 = this.bitOffset + 1;
        this.bitOffset = i7;
        if (i7 == 8) {
            this.bitOffset = 0;
            int i8 = this.byteOffset;
            if (shouldSkipByte(i8 + 1)) {
                i2 = 2;
            }
            this.byteOffset = i8 + i2;
        }
        assertValidOffset();
    }

    public void skipBits(int i2) {
        int i7 = this.byteOffset;
        int i8 = i2 / 8;
        int i10 = i7 + i8;
        this.byteOffset = i10;
        int i11 = (i2 - (i8 * 8)) + this.bitOffset;
        this.bitOffset = i11;
        if (i11 > 7) {
            this.byteOffset = i10 + 1;
            this.bitOffset = i11 - 8;
        }
        while (true) {
            i7++;
            if (i7 > this.byteOffset) {
                assertValidOffset();
                return;
            } else if (shouldSkipByte(i7)) {
                this.byteOffset++;
                i7 += 2;
            }
        }
    }
}
