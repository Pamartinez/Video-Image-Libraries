package androidx.media3.common.util;

import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
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

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public void byteAlign() {
        if (this.bitOffset != 0) {
            this.bitOffset = 0;
            this.byteOffset++;
            assertValidOffset();
        }
    }

    public int getBytePosition() {
        boolean z;
        if (this.bitOffset == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        return this.byteOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void putInt(int i2, int i7) {
        if (i7 < 32) {
            i2 &= (1 << i7) - 1;
        }
        int min = Math.min(8 - this.bitOffset, i7);
        int i8 = this.bitOffset;
        int i10 = (8 - i8) - min;
        byte[] bArr = this.data;
        int i11 = this.byteOffset;
        byte b = (byte) (((65280 >> i8) | ((1 << i10) - 1)) & bArr[i11]);
        bArr[i11] = b;
        int i12 = i7 - min;
        bArr[i11] = (byte) (b | ((i2 >>> i12) << i10));
        int i13 = i11 + 1;
        while (i12 > 8) {
            this.data[i13] = (byte) (i2 >>> (i12 - 8));
            i12 -= 8;
            i13++;
        }
        int i14 = 8 - i12;
        byte[] bArr2 = this.data;
        byte b5 = (byte) (bArr2[i13] & ((1 << i14) - 1));
        bArr2[i13] = b5;
        bArr2[i13] = (byte) (((i2 & ((1 << i12) - 1)) << i14) | b5);
        skipBits(i7);
        assertValidOffset();
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
        if (i2 == 0) {
            return 0;
        }
        this.bitOffset += i2;
        int i8 = 0;
        while (true) {
            i7 = this.bitOffset;
            if (i7 <= 8) {
                break;
            }
            int i10 = i7 - 8;
            this.bitOffset = i10;
            byte[] bArr = this.data;
            int i11 = this.byteOffset;
            this.byteOffset = i11 + 1;
            i8 |= (bArr[i11] & 255) << i10;
        }
        byte[] bArr2 = this.data;
        int i12 = this.byteOffset;
        int i13 = (-1 >>> (32 - i2)) & (i8 | ((bArr2[i12] & 255) >> (8 - i7)));
        if (i7 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i12 + 1;
        }
        assertValidOffset();
        return i13;
    }

    public long readBitsToLong(int i2) {
        if (i2 <= 32) {
            return Util.toUnsignedLong(readBits(i2));
        }
        return Util.toLong(readBits(i2 - 32), readBits(32));
    }

    public void readBytes(byte[] bArr, int i2, int i7) {
        boolean z;
        if (this.bitOffset == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        System.arraycopy(this.data, this.byteOffset, bArr, i2, i7);
        this.byteOffset += i7;
        assertValidOffset();
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void setPosition(int i2) {
        int i7 = i2 / 8;
        this.byteOffset = i7;
        this.bitOffset = i2 - (i7 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int i2 = this.bitOffset + 1;
        this.bitOffset = i2;
        if (i2 == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public void skipBits(int i2) {
        int i7 = i2 / 8;
        int i8 = this.byteOffset + i7;
        this.byteOffset = i8;
        int i10 = (i2 - (i7 * 8)) + this.bitOffset;
        this.bitOffset = i10;
        if (i10 > 7) {
            this.byteOffset = i8 + 1;
            this.bitOffset = i10 - 8;
        }
        assertValidOffset();
    }

    public void skipBytes(int i2) {
        boolean z;
        if (this.bitOffset == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.byteOffset += i2;
        assertValidOffset();
    }

    public void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.getData(), parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i2) {
        this.data = bArr;
        this.byteLimit = i2;
    }

    public void reset(byte[] bArr, int i2) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i2;
    }

    public void readBits(byte[] bArr, int i2, int i7) {
        int i8 = (i7 >> 3) + i2;
        while (i2 < i8) {
            byte[] bArr2 = this.data;
            int i10 = this.byteOffset;
            int i11 = i10 + 1;
            this.byteOffset = i11;
            byte b = bArr2[i10];
            int i12 = this.bitOffset;
            byte b5 = (byte) (b << i12);
            bArr[i2] = b5;
            bArr[i2] = (byte) (((255 & bArr2[i11]) >> (8 - i12)) | b5);
            i2++;
        }
        int i13 = i7 & 7;
        if (i13 != 0) {
            byte b8 = (byte) (bArr[i8] & (ScoverState.TYPE_NFC_SMART_COVER >> i13));
            bArr[i8] = b8;
            int i14 = this.bitOffset;
            if (i14 + i13 > 8) {
                byte[] bArr3 = this.data;
                int i15 = this.byteOffset;
                this.byteOffset = i15 + 1;
                bArr[i8] = (byte) (b8 | ((bArr3[i15] & 255) << i14));
                this.bitOffset = i14 - 8;
            }
            int i16 = this.bitOffset + i13;
            this.bitOffset = i16;
            byte[] bArr4 = this.data;
            int i17 = this.byteOffset;
            bArr[i8] = (byte) (((byte) (((255 & bArr4[i17]) >> (8 - i16)) << (8 - i13))) | bArr[i8]);
            if (i16 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i17 + 1;
            }
            assertValidOffset();
        }
    }
}
