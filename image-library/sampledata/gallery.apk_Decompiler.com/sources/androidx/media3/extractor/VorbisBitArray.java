package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] bArr) {
        this.data = bArr;
        this.byteLimit = bArr.length;
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

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public boolean readBit() {
        boolean z;
        if ((((this.data[this.byteOffset] & 255) >> this.bitOffset) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        skipBits(1);
        return z;
    }

    public int readBits(int i2) {
        int i7 = this.byteOffset;
        int min = Math.min(i2, 8 - this.bitOffset);
        int i8 = i7 + 1;
        int i10 = ((this.data[i7] & 255) >> this.bitOffset) & (ScoverState.TYPE_NFC_SMART_COVER >> (8 - min));
        while (min < i2) {
            i10 |= (this.data[i8] & 255) << min;
            min += 8;
            i8++;
        }
        int i11 = i10 & (-1 >>> (32 - i2));
        skipBits(i2);
        return i11;
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
}
