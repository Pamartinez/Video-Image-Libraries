package com.google.android.appfunctions.schema.internal.dependencies;

import L2.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X extends a {
    public final /* synthetic */ int d;

    public /* synthetic */ X(int i2) {
        this.d = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int K(java.lang.String r10, byte[] r11, int r12, int r13) {
        /*
            r9 = this;
            int r9 = r9.d
            switch(r9) {
                case 0: goto L_0x000a;
                default: goto L_0x0005;
            }
        L_0x0005:
            int r9 = L2.a.L(r10, r11, r12, r13)
            return r9
        L_0x000a:
            int r9 = r10.length()
            r0 = 0
        L_0x000f:
            int r1 = r12 + r13
            r2 = 128(0x80, float:1.794E-43)
            if (r0 >= r9) goto L_0x0025
            int r3 = r0 + r12
            if (r3 >= r1) goto L_0x0025
            char r4 = r10.charAt(r0)
            if (r4 >= r2) goto L_0x0025
            byte r1 = (byte) r4
            r11[r3] = r1
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0025:
            if (r0 != r9) goto L_0x002a
            int r12 = r12 + r9
            goto L_0x00f1
        L_0x002a:
            int r3 = r12 + r0
        L_0x002c:
            if (r0 >= r9) goto L_0x00f0
            char r4 = r10.charAt(r0)
            if (r4 >= r2) goto L_0x003e
            if (r3 >= r1) goto L_0x003e
            int r5 = r3 + 1
            byte r4 = (byte) r4
            r11[r3] = r4
            r3 = r5
            goto L_0x00c4
        L_0x003e:
            r5 = 2048(0x800, float:2.87E-42)
            if (r4 >= r5) goto L_0x0059
            int r5 = r1 + -2
            if (r3 > r5) goto L_0x0059
            int r5 = r3 + 1
            int r6 = r3 + 2
            int r7 = r4 >>> 6
            r7 = r7 | 960(0x3c0, float:1.345E-42)
            byte r7 = (byte) r7
            r11[r3] = r7
            r3 = r4 & 63
            r3 = r3 | r2
            byte r3 = (byte) r3
            r11[r5] = r3
            r3 = r6
            goto L_0x00c4
        L_0x0059:
            r5 = 57343(0xdfff, float:8.0355E-41)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r6) goto L_0x0063
            if (r4 <= r5) goto L_0x0084
        L_0x0063:
            int r7 = r1 + -3
            if (r3 > r7) goto L_0x0084
            int r5 = r3 + 1
            int r6 = r3 + 2
            int r7 = r3 + 3
            int r8 = r4 >>> 12
            r8 = r8 | 480(0x1e0, float:6.73E-43)
            byte r8 = (byte) r8
            r11[r3] = r8
            int r3 = r4 >>> 6
            r3 = r3 & 63
            r3 = r3 | r2
            byte r3 = (byte) r3
            r11[r5] = r3
            r3 = r4 & 63
            r3 = r3 | r2
            byte r3 = (byte) r3
            r11[r6] = r3
            r3 = r7
            goto L_0x00c4
        L_0x0084:
            int r7 = r1 + -4
            if (r3 > r7) goto L_0x00cd
            int r0 = r0 + 1
            int r5 = r10.length()
            if (r0 == r5) goto L_0x00c8
            char r5 = r10.charAt(r0)
            boolean r6 = java.lang.Character.isSurrogatePair(r4, r5)
            if (r6 != 0) goto L_0x009b
            goto L_0x00c8
        L_0x009b:
            int r6 = r3 + 1
            int r7 = r3 + 2
            int r8 = r3 + 3
            int r4 = java.lang.Character.toCodePoint(r4, r5)
            int r5 = r4 >>> 18
            r5 = r5 | 240(0xf0, float:3.36E-43)
            byte r5 = (byte) r5
            r11[r3] = r5
            int r5 = r4 >>> 12
            r5 = r5 & 63
            r5 = r5 | r2
            byte r5 = (byte) r5
            r11[r6] = r5
            int r5 = r4 >>> 6
            r5 = r5 & 63
            r5 = r5 | r2
            byte r5 = (byte) r5
            r11[r7] = r5
            int r3 = r3 + 4
            r4 = r4 & 63
            r4 = r4 | r2
            byte r4 = (byte) r4
            r11[r8] = r4
        L_0x00c4:
            int r0 = r0 + 1
            goto L_0x002c
        L_0x00c8:
            int r12 = L2.a.L(r10, r11, r12, r13)
            goto L_0x00f1
        L_0x00cd:
            if (r4 < r6) goto L_0x00e8
            if (r4 > r5) goto L_0x00e8
            int r0 = r0 + 1
            int r9 = r10.length()
            if (r0 == r9) goto L_0x00e3
            char r9 = r10.charAt(r0)
            boolean r9 = java.lang.Character.isSurrogatePair(r4, r9)
            if (r9 != 0) goto L_0x00e8
        L_0x00e3:
            int r12 = L2.a.L(r10, r11, r12, r13)
            goto L_0x00f1
        L_0x00e8:
            java.lang.ArrayIndexOutOfBoundsException r9 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r10 = "Not enough space in output buffer to encode UTF-8 string"
            r9.<init>(r10)
            throw r9
        L_0x00f0:
            r12 = r3
        L_0x00f1:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.X.K(java.lang.String, byte[], int, int):int");
    }
}
