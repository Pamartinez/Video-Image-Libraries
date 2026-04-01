package com.samsung.android.gallery.module.story.transcode.util;

import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AudioTranscodingHelper {
    private static final double DOLBY_COEFFICIENT = (1.0d / Math.sqrt(2.0d));

    public static short[] asStereo(short[] sArr, int i2) {
        if (i2 == 1) {
            return monoToStereo(sArr);
        }
        if (i2 == 6) {
            return dolbyToStereo(sArr);
        }
        return sArr;
    }

    private static short[] decimate(short[] sArr, double d) {
        int length = (int) (((double) sArr.length) / d);
        short[] sArr2 = new short[length];
        double d2 = MapUtil.INVALID_LOCATION;
        int i2 = 0;
        for (int i7 = 0; i7 < length / 2; i7++) {
            int i8 = i7 * 2;
            sArr2[i8] = sArr[i2];
            sArr2[i8 + 1] = sArr[i2 + 1];
            double d3 = (d - 1.0d) + d2;
            i2 = (int) ((Math.floor(d3) * 2.0d) + 2.0d + ((double) i2));
            d2 = d3 % 1.0d;
        }
        return sArr2;
    }

    private static short[] dolbyToStereo(short[] sArr) {
        short[] sArr2 = sArr;
        int length = sArr2.length / 3;
        short[] sArr3 = new short[length];
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i7 = i2 * 6;
            short s = sArr2[i7];
            short s5 = sArr2[i7 + 1];
            short s6 = sArr2[i7 + 2];
            short s10 = sArr2[i7 + 3];
            short s11 = sArr2[i7 + 4];
            short s12 = sArr2[i7 + 5];
            double d = (double) s6;
            double d2 = DOLBY_COEFFICIENT;
            double d3 = (double) s10;
            double d5 = d3 * d2;
            int i8 = i2 * 2;
            sArr3[i8] = (short) ((int) Math.round((d3 * d2) + (d * d2) + ((double) (s + s11))));
            int i10 = i8 + 1;
            sArr3[i10] = (short) ((int) Math.round(d5 + (d * d2) + ((double) (s5 + s12))));
        }
        return sArr3;
    }

    private static short[] interpolate(short[] sArr, double d) {
        short[] sArr2 = sArr;
        int length = (int) (((double) sArr2.length) / d);
        short[] sArr3 = new short[length];
        int i2 = -2;
        double d2 = 1.0d;
        for (int i7 = 0; i7 < length / 2; i7++) {
            if (i2 > sArr2.length - 4) {
                int i8 = i7 * 2;
                sArr3[i8] = sArr2[i2];
                sArr3[i8 + 1] = sArr2[i2 + 1];
            } else if (d2 >= 1.0d) {
                int i10 = i2 + 2;
                int i11 = i7 * 2;
                sArr3[i11] = sArr2[i10];
                sArr3[i11 + 1] = sArr2[i2 + 3];
                d2 %= 1.0d;
                i2 = i10;
            } else {
                int i12 = i7 * 2;
                double d3 = 1.0d - d2;
                sArr3[i12] = (short) ((int) Math.round((((double) sArr2[i2 + 2]) * d3) + (((double) sArr2[i2]) * d2)));
                sArr3[i12 + 1] = (short) ((int) Math.round((((double) sArr2[i2 + 3]) * d3) + (((double) sArr2[i2 + 1]) * d2)));
            }
            d2 += d;
        }
        return sArr3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: short} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] mix(short[] r6, short[] r7) {
        /*
            int r0 = r6.length
            int r1 = r0 * 2
            byte[] r1 = new byte[r1]
            r2 = 0
        L_0x0006:
            if (r2 >= r0) goto L_0x0035
            int r3 = r7.length
            if (r3 <= r2) goto L_0x0011
            short r3 = r6[r2]
            short r4 = r7[r2]
            int r3 = r3 + r4
            goto L_0x0013
        L_0x0011:
            short r3 = r6[r2]
        L_0x0013:
            r4 = 32767(0x7fff, float:4.5916E-41)
            int r3 = java.lang.Math.min(r4, r3)
            r4 = -32768(0xffffffffffff8000, float:NaN)
            int r3 = java.lang.Math.max(r3, r4)
            short r3 = (short) r3
            int r4 = r2 * 2
            r5 = r3 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r4 + 1
            r5 = 65280(0xff00, float:9.1477E-41)
            r3 = r3 & r5
            int r3 = r3 >> 8
            byte r3 = (byte) r3
            r1[r4] = r3
            int r2 = r2 + 1
            goto L_0x0006
        L_0x0035:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.util.AudioTranscodingHelper.mix(short[], short[]):byte[]");
    }

    private static short[] monoToStereo(short[] sArr) {
        short[] sArr2 = new short[(sArr.length * 2)];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            int i7 = i2 * 2;
            sArr2[i7] = sArr[i2];
            sArr2[i7 + 1] = sArr[i2];
        }
        return sArr2;
    }

    public static short[] resample(short[] sArr, double d) {
        if (d < 1.0d) {
            return interpolate(sArr, d);
        }
        return decimate(sArr, d);
    }

    public static short[] scale(short[] sArr, float f) {
        if (f == 1.0f) {
            return sArr;
        }
        short[] sArr2 = new short[sArr.length];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            sArr2[i2] = (short) ((int) (((float) sArr[i2]) * f));
        }
        return sArr2;
    }
}
