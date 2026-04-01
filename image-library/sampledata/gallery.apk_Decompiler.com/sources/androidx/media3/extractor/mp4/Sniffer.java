package androidx.media3.extractor.mp4;

import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153, 1769172332, 1885955686};

    private static boolean isCompatibleBrand(int i2, boolean z) {
        if ((i2 >>> 8) == 3368816) {
            return true;
        }
        if (i2 == 1751476579 && z) {
            return true;
        }
        for (int i7 : COMPATIBLE_BRANDS) {
            if (i7 == i2) {
                return true;
            }
        }
        return false;
    }

    public static SniffFailure sniffFragmented(ExtractorInput extractorInput) {
        return sniffInternal(extractorInput, true, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012c, code lost:
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r17 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.SniffFailure sniffInternal(androidx.media3.extractor.ExtractorInput r25, boolean r26, boolean r27) {
        /*
            r0 = r25
            r1 = r27
            long r2 = r0.getLength()
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r7 = 4096(0x1000, double:2.0237E-320)
            if (r6 == 0) goto L_0x0016
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r7 = r2
        L_0x0016:
            int r7 = (int) r7
            androidx.media3.common.util.ParsableByteArray r8 = new androidx.media3.common.util.ParsableByteArray
            r9 = 64
            r8.<init>((int) r9)
            r9 = 0
            r10 = r9
            r11 = r10
        L_0x0021:
            if (r10 >= r7) goto L_0x0033
            r13 = 8
            r8.reset((int) r13)
            byte[] r14 = r8.getData()
            r15 = 1
            boolean r14 = r0.peekFully(r14, r9, r13, r15)
            if (r14 != 0) goto L_0x0038
        L_0x0033:
            r4 = r9
            r17 = 0
            goto L_0x012c
        L_0x0038:
            long r16 = r8.readUnsignedInt()
            int r14 = r8.readInt()
            r18 = 1
            int r18 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r18 != 0) goto L_0x005e
            r18 = r4
            byte[] r4 = r8.getData()
            r0.peekFully(r4, r13, r13)
            r4 = 16
            r8.setLimit(r4)
            long r16 = r8.readLong()
            r21 = r10
        L_0x005a:
            r9 = r16
            r5 = 0
            goto L_0x007e
        L_0x005e:
            r18 = r4
            r4 = 0
            int r4 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x007b
            long r4 = r0.getLength()
            int r20 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r20 == 0) goto L_0x007b
            long r16 = r0.getPeekPosition()
            long r4 = r4 - r16
            r21 = r10
            long r9 = (long) r13
            long r16 = r4 + r9
        L_0x0079:
            r4 = r13
            goto L_0x005a
        L_0x007b:
            r21 = r10
            goto L_0x0079
        L_0x007e:
            long r12 = (long) r4
            int r17 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r17 >= 0) goto L_0x0089
            androidx.media3.extractor.mp4.AtomSizeTooSmallSniffFailure r0 = new androidx.media3.extractor.mp4.AtomSizeTooSmallSniffFailure
            r0.<init>(r14, r9, r4)
            return r0
        L_0x0089:
            int r4 = r21 + r4
            r17 = r5
            r5 = 1836019574(0x6d6f6f76, float:4.631354E27)
            if (r14 != r5) goto L_0x00a1
            int r5 = (int) r9
            int r7 = r7 + r5
            if (r6 == 0) goto L_0x009c
            long r9 = (long) r7
            int r5 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r5 <= 0) goto L_0x009c
            int r7 = (int) r2
        L_0x009c:
            r10 = r4
            r4 = r18
            r9 = 0
            goto L_0x0021
        L_0x00a1:
            r5 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            if (r14 == r5) goto L_0x012a
            r5 = 1836475768(0x6d766578, float:4.7659988E27)
            if (r14 != r5) goto L_0x00ad
            goto L_0x012a
        L_0x00ad:
            r5 = 1835295092(0x6d646174, float:4.4175247E27)
            if (r14 != r5) goto L_0x00b3
            r11 = r15
        L_0x00b3:
            r21 = r2
            long r2 = (long) r4
            long r2 = r2 + r9
            long r2 = r2 - r12
            r23 = r2
            long r2 = (long) r7
            int r2 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x00c2
            r9 = 0
            goto L_0x012d
        L_0x00c2:
            long r9 = r9 - r12
            int r2 = (int) r9
            int r10 = r4 + r2
            r3 = 1718909296(0x66747970, float:2.8862439E23)
            if (r14 != r3) goto L_0x011d
            r3 = 8
            if (r2 >= r3) goto L_0x00d6
            androidx.media3.extractor.mp4.AtomSizeTooSmallSniffFailure r0 = new androidx.media3.extractor.mp4.AtomSizeTooSmallSniffFailure
            long r1 = (long) r2
            r0.<init>(r14, r1, r3)
            return r0
        L_0x00d6:
            r8.reset((int) r2)
            byte[] r3 = r8.getData()
            r4 = 0
            r0.peekFully(r3, r4, r2)
            int r2 = r8.readInt()
            boolean r3 = isCompatibleBrand(r2, r1)
            if (r3 == 0) goto L_0x00ec
            r11 = r15
        L_0x00ec:
            r3 = 4
            r8.skipBytes(r3)
            int r5 = r8.bytesLeft()
            int r5 = r5 / r3
            if (r11 != 0) goto L_0x0110
            if (r5 <= 0) goto L_0x0110
            int[] r12 = new int[r5]
            r3 = r4
        L_0x00fc:
            if (r3 >= r5) goto L_0x010e
            int r9 = r8.readInt()
            r12[r3] = r9
            boolean r9 = isCompatibleBrand(r9, r1)
            if (r9 == 0) goto L_0x010b
            goto L_0x0113
        L_0x010b:
            int r3 = r3 + 1
            goto L_0x00fc
        L_0x010e:
            r15 = r11
            goto L_0x0113
        L_0x0110:
            r15 = r11
            r12 = r17
        L_0x0113:
            if (r15 != 0) goto L_0x011b
            androidx.media3.extractor.mp4.UnsupportedBrandsSniffFailure r0 = new androidx.media3.extractor.mp4.UnsupportedBrandsSniffFailure
            r0.<init>(r2, r12)
            return r0
        L_0x011b:
            r11 = r15
            goto L_0x0123
        L_0x011d:
            r4 = 0
            if (r2 == 0) goto L_0x0123
            r0.advancePeekPosition(r2)
        L_0x0123:
            r9 = r4
            r4 = r18
            r2 = r21
            goto L_0x0021
        L_0x012a:
            r9 = r15
            goto L_0x012d
        L_0x012c:
            r9 = r4
        L_0x012d:
            if (r11 != 0) goto L_0x0132
            androidx.media3.extractor.mp4.NoDeclaredBrandSniffFailure r0 = androidx.media3.extractor.mp4.NoDeclaredBrandSniffFailure.INSTANCE
            return r0
        L_0x0132:
            r0 = r26
            if (r0 == r9) goto L_0x013e
            if (r9 == 0) goto L_0x013b
            androidx.media3.extractor.mp4.IncorrectFragmentationSniffFailure r0 = androidx.media3.extractor.mp4.IncorrectFragmentationSniffFailure.FILE_FRAGMENTED
            return r0
        L_0x013b:
            androidx.media3.extractor.mp4.IncorrectFragmentationSniffFailure r0 = androidx.media3.extractor.mp4.IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED
            return r0
        L_0x013e:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Sniffer.sniffInternal(androidx.media3.extractor.ExtractorInput, boolean, boolean):androidx.media3.extractor.SniffFailure");
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput extractorInput, boolean z) {
        return sniffInternal(extractorInput, false, z);
    }
}
