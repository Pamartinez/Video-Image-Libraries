package androidx.media3.extractor.ts;

import androidx.media3.common.util.Assertions;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int i2, int i7) {
        this.targetType = i2;
        byte[] bArr = new byte[(i7 + 3)];
        this.nalData = bArr;
        bArr[2] = 1;
    }

    public void appendToNalUnit(byte[] bArr, int i2, int i7) {
        if (this.isFilling) {
            int i8 = i7 - i2;
            byte[] bArr2 = this.nalData;
            int length = bArr2.length;
            int i10 = this.nalLength;
            if (length < i10 + i8) {
                this.nalData = Arrays.copyOf(bArr2, (i10 + i8) * 2);
            }
            System.arraycopy(bArr, i2, this.nalData, this.nalLength, i8);
            this.nalLength += i8;
        }
    }

    public boolean endNalUnit(int i2) {
        if (!this.isFilling) {
            return false;
        }
        this.nalLength -= i2;
        this.isFilling = false;
        this.isCompleted = true;
        return true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public void startNalUnit(int i2) {
        boolean z = true;
        Assertions.checkState(!this.isFilling);
        if (i2 != this.targetType) {
            z = false;
        }
        this.isFilling = z;
        if (z) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }
}
