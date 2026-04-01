package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Util;
import com.adobe.internal.xmp.options.SerializeOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FixedSampleSizeRechunker {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;
        public final long totalSize;

        private Results(long[] jArr, int[] iArr, int i2, long[] jArr2, int[] iArr2, long j2, long j3) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i2;
            this.timestamps = jArr2;
            this.flags = iArr2;
            this.duration = j2;
            this.totalSize = j3;
        }
    }

    public static Results rechunk(int i2, long[] jArr, int[] iArr, long j2) {
        int[] iArr2 = iArr;
        int i7 = SerializeOptions.SORT / i2;
        int i8 = 0;
        int i10 = 0;
        for (int ceilDivide : iArr2) {
            i10 += Util.ceilDivide(ceilDivide, i7);
        }
        long[] jArr2 = new long[i10];
        int[] iArr3 = new int[i10];
        long[] jArr3 = new long[i10];
        int[] iArr4 = new int[i10];
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i8 < iArr2.length) {
            int i15 = iArr2[i8];
            long j3 = jArr[i8];
            while (i15 > 0) {
                int min = Math.min(i7, i15);
                jArr2[i13] = j3;
                int i16 = i2 * min;
                iArr3[i13] = i16;
                i12 += i16;
                i14 = Math.max(i14, i16);
                jArr3[i13] = ((long) i11) * j2;
                iArr4[i13] = 1;
                j3 += (long) iArr3[i13];
                i11 += min;
                i15 -= min;
                i13++;
                int[] iArr5 = iArr;
                i7 = i7;
            }
            int i17 = i7;
            i8++;
            iArr2 = iArr;
        }
        return new Results(jArr2, iArr3, i14, jArr3, iArr4, j2 * ((long) i11), (long) i12);
    }
}
