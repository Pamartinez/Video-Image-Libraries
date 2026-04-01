package androidx.media3.extractor.mkv;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class Sniffer {
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    private long readUint(ExtractorInput extractorInput) {
        int i2 = 0;
        extractorInput.peekFully(this.scratch.getData(), 0, 1);
        byte b = this.scratch.getData()[0] & 255;
        if (b == 0) {
            return Long.MIN_VALUE;
        }
        int i7 = 128;
        int i8 = 0;
        while ((b & i7) == 0) {
            i7 >>= 1;
            i8++;
        }
        int i10 = b & (~i7);
        extractorInput.peekFully(this.scratch.getData(), 1, i8);
        while (i2 < i8) {
            i2++;
            i10 = (this.scratch.getData()[i2] & 255) + (i10 << 8);
        }
        this.peekLength = i8 + 1 + this.peekLength;
        return (long) i10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009a, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sniff(androidx.media3.extractor.ExtractorInput r14) {
        /*
            r13 = this;
            long r0 = r14.getLength()
            r2 = -1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 1024(0x400, double:5.06E-321)
            if (r2 == 0) goto L_0x0012
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0011
            goto L_0x0012
        L_0x0011:
            r3 = r0
        L_0x0012:
            int r3 = (int) r3
            androidx.media3.common.util.ParsableByteArray r4 = r13.scratch
            byte[] r4 = r4.getData()
            r5 = 0
            r6 = 4
            r14.peekFully(r4, r5, r6)
            androidx.media3.common.util.ParsableByteArray r4 = r13.scratch
            long r7 = r4.readUnsignedInt()
            r13.peekLength = r6
        L_0x0026:
            r9 = 440786851(0x1a45dfa3, double:2.1777764E-315)
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r6 = 1
            if (r4 == 0) goto L_0x0054
            int r4 = r13.peekLength
            int r4 = r4 + r6
            r13.peekLength = r4
            if (r4 != r3) goto L_0x0036
            return r5
        L_0x0036:
            androidx.media3.common.util.ParsableByteArray r4 = r13.scratch
            byte[] r4 = r4.getData()
            r14.peekFully(r4, r5, r6)
            r4 = 8
            long r6 = r7 << r4
            r8 = -256(0xffffffffffffff00, double:NaN)
            long r6 = r6 & r8
            androidx.media3.common.util.ParsableByteArray r4 = r13.scratch
            byte[] r4 = r4.getData()
            byte r4 = r4[r5]
            r4 = r4 & 255(0xff, float:3.57E-43)
            long r8 = (long) r4
            long r7 = r6 | r8
            goto L_0x0026
        L_0x0054:
            long r3 = r13.readUint(r14)
            int r7 = r13.peekLength
            long r7 = (long) r7
            r9 = -9223372036854775808
            int r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x00a1
            if (r2 == 0) goto L_0x006a
            long r11 = r7 + r3
            int r0 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x006a
            goto L_0x00a1
        L_0x006a:
            int r0 = r13.peekLength
            long r1 = (long) r0
            long r11 = r7 + r3
            int r1 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x009b
            long r0 = r13.readUint(r14)
            int r0 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x007c
            return r5
        L_0x007c:
            long r0 = r13.readUint(r14)
            r11 = 0
            int r2 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r2 < 0) goto L_0x009a
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r11 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r11 <= 0) goto L_0x008e
            goto L_0x009a
        L_0x008e:
            if (r2 == 0) goto L_0x006a
            int r0 = (int) r0
            r14.advancePeekPosition(r0)
            int r1 = r13.peekLength
            int r1 = r1 + r0
            r13.peekLength = r1
            goto L_0x006a
        L_0x009a:
            return r5
        L_0x009b:
            long r13 = (long) r0
            int r13 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r13 != 0) goto L_0x00a1
            return r6
        L_0x00a1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.Sniffer.sniff(androidx.media3.extractor.ExtractorInput):boolean");
    }
}
