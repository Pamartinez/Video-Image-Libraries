package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VarintReader {
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public static long assembleVarint(byte[] bArr, int i2, boolean z) {
        long j2 = ((long) bArr[0]) & 255;
        if (z) {
            j2 &= ~VARINT_LENGTH_MASKS[i2 - 1];
        }
        for (int i7 = 1; i7 < i2; i7++) {
            j2 = (j2 << 8) | (((long) bArr[i7]) & 255);
        }
        return j2;
    }

    public static int parseUnsignedVarintLength(int i2) {
        int i7 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (i7 >= jArr.length) {
                return -1;
            }
            if ((jArr[i7] & ((long) i2)) != 0) {
                return i7 + 1;
            }
            i7++;
        }
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z3, int i2) {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1;
            }
            int parseUnsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & 255);
            this.length = parseUnsignedVarintLength;
            if (parseUnsignedVarintLength != -1) {
                this.state = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i7 = this.length;
        if (i7 > i2) {
            this.state = 0;
            return -2;
        }
        if (i7 != 1) {
            extractorInput.readFully(this.scratch, 1, i7 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z3);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
