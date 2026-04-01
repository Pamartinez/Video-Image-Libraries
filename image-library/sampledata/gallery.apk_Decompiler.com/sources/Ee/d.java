package Ee;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d {
    public static final a d;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: Ee.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: Ee.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: Ee.b} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.Integer r0 = we.C1347a.f5165a
            if (r0 == 0) goto L_0x0013
            int r0 = r0.intValue()
            r1 = 34
            if (r0 < r1) goto L_0x000d
            goto L_0x0013
        L_0x000d:
            Ee.b r0 = new Ee.b
            r0.<init>()
            goto L_0x0018
        L_0x0013:
            Fe.a r0 = new Fe.a
            r0.<init>()
        L_0x0018:
            d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ee.d.<clinit>():void");
    }

    public abstract int a(int i2);

    public abstract int b();

    public int c(int i2, int i7) {
        int i8;
        int b;
        int i10;
        if (i7 > i2) {
            int i11 = i7 - i2;
            if (i11 > 0 || i11 == Integer.MIN_VALUE) {
                if (((-i11) & i11) == i11) {
                    i8 = a(31 - Integer.numberOfLeadingZeros(i11));
                } else {
                    do {
                        b = b() >>> 1;
                        i10 = b % i11;
                    } while ((i11 - 1) + (b - i10) < 0);
                    i8 = i10;
                }
                return i2 + i8;
            }
            while (true) {
                int b5 = b();
                if (i2 <= b5 && b5 < i7) {
                    return b5;
                }
            }
        } else {
            Integer valueOf = Integer.valueOf(i2);
            Integer valueOf2 = Integer.valueOf(i7);
            throw new IllegalArgumentException(("Random range is empty: [" + valueOf + ArcCommonLog.TAG_COMMA + valueOf2 + ").").toString());
        }
    }
}
