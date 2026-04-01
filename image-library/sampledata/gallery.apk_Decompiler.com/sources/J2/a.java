package J2;

import com.adobe.internal.xmp.XMPConst;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Serializable {
    public static final a f = new a(new int[0]);
    public final int[] d;
    public final int e;

    public a(int[] iArr) {
        int length = iArr.length;
        this.d = iArr;
        this.e = length;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r7 = (J2.a) r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            if (r7 != r6) goto L_0x0003
            goto L_0x0029
        L_0x0003:
            boolean r0 = r7 instanceof J2.a
            r1 = 0
            if (r0 != 0) goto L_0x0009
            goto L_0x0025
        L_0x0009:
            J2.a r7 = (J2.a) r7
            int r0 = r7.e
            int r2 = r6.e
            if (r2 == r0) goto L_0x0012
            goto L_0x0025
        L_0x0012:
            r3 = r1
        L_0x0013:
            if (r3 >= r2) goto L_0x0029
            He.F.m(r3, r2)
            int[] r4 = r6.d
            r4 = r4[r3]
            He.F.m(r3, r0)
            int[] r5 = r7.d
            r5 = r5[r3]
            if (r4 == r5) goto L_0x0026
        L_0x0025:
            return r1
        L_0x0026:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0029:
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: J2.a.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = 0; i7 < this.e; i7++) {
            i2 = (i2 * 31) + this.d[i7];
        }
        return i2;
    }

    public final String toString() {
        int i2 = this.e;
        if (i2 == 0) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        StringBuilder sb2 = new StringBuilder(i2 * 5);
        sb2.append('[');
        int[] iArr = this.d;
        sb2.append(iArr[0]);
        for (int i7 = 1; i7 < i2; i7++) {
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(iArr[i7]);
        }
        sb2.append(']');
        return sb2.toString();
    }
}
