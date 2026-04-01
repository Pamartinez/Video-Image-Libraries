package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeekParameters {
    public static final SeekParameters CLOSEST_SYNC = new SeekParameters(Long.MAX_VALUE, Long.MAX_VALUE);
    public static final SeekParameters DEFAULT;
    public static final SeekParameters EXACT;
    public static final SeekParameters NEXT_SYNC = new SeekParameters(0, Long.MAX_VALUE);
    public static final SeekParameters PREVIOUS_SYNC = new SeekParameters(Long.MAX_VALUE, 0);
    public final long toleranceAfterUs;
    public final long toleranceBeforeUs;

    static {
        SeekParameters seekParameters = new SeekParameters(0, 0);
        EXACT = seekParameters;
        DEFAULT = seekParameters;
    }

    public SeekParameters(long j2, long j3) {
        boolean z;
        boolean z3 = false;
        if (j2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        Assertions.checkArgument(j3 >= 0 ? true : z3);
        this.toleranceBeforeUs = j2;
        this.toleranceAfterUs = j3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SeekParameters.class == obj.getClass()) {
            SeekParameters seekParameters = (SeekParameters) obj;
            if (this.toleranceBeforeUs == seekParameters.toleranceBeforeUs && this.toleranceAfterUs == seekParameters.toleranceAfterUs) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((int) this.toleranceBeforeUs) * 31) + ((int) this.toleranceAfterUs);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long resolveSeekPositionUs(long r8, long r10, long r12) {
        /*
            r7 = this;
            long r2 = r7.toleranceBeforeUs
            r0 = 0
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 != 0) goto L_0x000f
            long r4 = r7.toleranceAfterUs
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x000f
            return r8
        L_0x000f:
            r4 = -9223372036854775808
            r0 = r8
            long r8 = androidx.media3.common.util.Util.subtractWithOverflowDefault(r0, r2, r4)
            long r2 = r7.toleranceAfterUs
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            long r2 = androidx.media3.common.util.Util.addWithOverflowDefault(r0, r2, r4)
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            r4 = 0
            r5 = 1
            if (r7 > 0) goto L_0x002d
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 > 0) goto L_0x002d
            r7 = r5
            goto L_0x002e
        L_0x002d:
            r7 = r4
        L_0x002e:
            int r6 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r6 > 0) goto L_0x0037
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0037
            r4 = r5
        L_0x0037:
            if (r7 == 0) goto L_0x004c
            if (r4 == 0) goto L_0x004c
            long r7 = r10 - r0
            long r7 = java.lang.Math.abs(r7)
            long r0 = r12 - r0
            long r0 = java.lang.Math.abs(r0)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 > 0) goto L_0x0051
            goto L_0x004e
        L_0x004c:
            if (r7 == 0) goto L_0x004f
        L_0x004e:
            return r10
        L_0x004f:
            if (r4 == 0) goto L_0x0052
        L_0x0051:
            return r12
        L_0x0052:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.SeekParameters.resolveSeekPositionUs(long, long, long):long");
    }
}
