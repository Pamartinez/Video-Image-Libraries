package H0;

import D0.e;

/* renamed from: H0.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0047c {

    /* renamed from: a  reason: collision with root package name */
    public static final e f306a = e.S("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");
    public static final e b = e.S("k");

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
        r1 = Gd.a.V(r0, r2, false);
        r5 = (java.util.List) r1.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        if (r5.isEmpty() == false) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
        r17 = r1;
        r11 = r4;
        r5.add(new K0.a(r2, (java.lang.Object) r3, (java.lang.Object) r3, (android.view.animation.Interpolator) null, 0.0f, java.lang.Float.valueOf(r2.m)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0088, code lost:
        r17 = r1;
        r11 = r4;
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0094, code lost:
        if (((K0.a) r8.get(0)).b != null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0096, code lost:
        r8.set(0, new K0.a(r2, (java.lang.Object) r3, (java.lang.Object) r3, (android.view.animation.Interpolator) null, 0.0f, java.lang.Float.valueOf(r2.m)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r4 = r11;
        r1 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ba, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0173, code lost:
        if (r0.b == 1.0f) goto L_0x0179;
     */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static D0.g a(I0.d r26, x0.C0332j r27) {
        /*
            r0 = r26
            r2 = r27
            r8 = 0
            java.lang.Float r3 = java.lang.Float.valueOf(r8)
            I0.b r1 = r0.n()
            I0.b r4 = I0.b.BEGIN_OBJECT
            r9 = 0
            if (r1 != r4) goto L_0x0015
            r1 = 1
            r10 = r1
            goto L_0x0016
        L_0x0015:
            r10 = r9
        L_0x0016:
            if (r10 == 0) goto L_0x001b
            r0.c()
        L_0x001b:
            r1 = 0
            r4 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r21 = 0
            r22 = 0
            r23 = 0
        L_0x0027:
            boolean r5 = r0.h()
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r5 == 0) goto L_0x00e4
            D0.e r5 = f306a
            int r5 = r0.q(r5)
            switch(r5) {
                case 0: goto L_0x00c2;
                case 1: goto L_0x00bc;
                case 2: goto L_0x00ad;
                case 3: goto L_0x0058;
                case 4: goto L_0x005d;
                case 5: goto L_0x0053;
                case 6: goto L_0x004e;
                case 7: goto L_0x0049;
                case 8: goto L_0x0044;
                case 9: goto L_0x003f;
                default: goto L_0x0038;
            }
        L_0x0038:
            r0.r()
            r0.s()
            goto L_0x0027
        L_0x003f:
            D0.b r4 = Gd.a.V(r0, r2, r9)
            goto L_0x0027
        L_0x0044:
            D0.b r15 = Gd.a.V(r0, r2, r9)
            goto L_0x0027
        L_0x0049:
            D0.b r23 = Gd.a.V(r0, r2, r9)
            goto L_0x0027
        L_0x004e:
            D0.b r22 = Gd.a.V(r0, r2, r9)
            goto L_0x0027
        L_0x0053:
            D0.a r21 = Gd.a.X(r26, r27)
            goto L_0x0027
        L_0x0058:
            java.lang.String r1 = "Lottie doesn't support 3D layers."
            r2.a(r1)
        L_0x005d:
            D0.b r1 = Gd.a.V(r0, r2, r9)
            java.lang.Object r5 = r1.e
            java.util.List r5 = (java.util.List) r5
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto L_0x0088
            r6 = r1
            K0.a r1 = new K0.a
            float r7 = r2.m
            java.lang.Float r7 = java.lang.Float.valueOf(r7)
            r16 = r5
            r5 = 0
            r17 = r6
            r6 = 0
            r18 = r4
            r4 = r3
            r8 = r16
            r11 = r18
            r1.<init>((x0.C0332j) r2, (java.lang.Object) r3, (java.lang.Object) r4, (android.view.animation.Interpolator) r5, (float) r6, (java.lang.Float) r7)
            r8.add(r1)
            goto L_0x00a7
        L_0x0088:
            r17 = r1
            r11 = r4
            r8 = r5
            java.lang.Object r1 = r8.get(r9)
            K0.a r1 = (K0.a) r1
            java.lang.Object r1 = r1.b
            if (r1 != 0) goto L_0x00a7
            K0.a r1 = new K0.a
            float r4 = r2.m
            java.lang.Float r7 = java.lang.Float.valueOf(r4)
            r5 = 0
            r6 = 0
            r4 = r3
            r1.<init>((x0.C0332j) r2, (java.lang.Object) r3, (java.lang.Object) r4, (android.view.animation.Interpolator) r5, (float) r6, (java.lang.Float) r7)
            r8.set(r9, r1)
        L_0x00a7:
            r4 = r11
            r1 = r17
        L_0x00aa:
            r8 = 0
            goto L_0x0027
        L_0x00ad:
            r11 = r4
            D0.a r14 = new D0.a
            H0.f r4 = H0.C0050f.f311j
            java.util.ArrayList r4 = H0.q.a(r0, r2, r6, r4, r9)
            r5 = 4
            r14.<init>(r5, r4)
        L_0x00ba:
            r4 = r11
            goto L_0x00aa
        L_0x00bc:
            r11 = r4
            D0.h r13 = H0.C0045a.b(r26, r27)
            goto L_0x00aa
        L_0x00c2:
            r11 = r4
            r0.c()
        L_0x00c6:
            boolean r4 = r0.h()
            if (r4 == 0) goto L_0x00e0
            D0.e r4 = b
            int r4 = r0.q(r4)
            if (r4 == 0) goto L_0x00db
            r0.r()
            r0.s()
            goto L_0x00c6
        L_0x00db:
            D0.c r12 = H0.C0045a.a(r26, r27)
            goto L_0x00c6
        L_0x00e0:
            r0.g()
            goto L_0x00ba
        L_0x00e4:
            r11 = r4
            if (r10 == 0) goto L_0x00ea
            r0.g()
        L_0x00ea:
            if (r12 == 0) goto L_0x0109
            boolean r0 = r12.u0()
            if (r0 == 0) goto L_0x0106
            java.util.ArrayList r0 = r12.d
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            android.graphics.PointF r0 = (android.graphics.PointF) r0
            r2 = 0
            boolean r0 = r0.equals(r2, r2)
            if (r0 == 0) goto L_0x0106
            goto L_0x0109
        L_0x0106:
            r17 = r12
            goto L_0x010b
        L_0x0109:
            r17 = 0
        L_0x010b:
            if (r13 == 0) goto L_0x012c
            boolean r0 = r13 instanceof D0.d
            if (r0 != 0) goto L_0x012d
            boolean r0 = r13.u0()
            if (r0 == 0) goto L_0x012d
            java.util.List r0 = r13.s0()
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            android.graphics.PointF r0 = (android.graphics.PointF) r0
            r2 = 0
            boolean r0 = r0.equals(r2, r2)
            if (r0 == 0) goto L_0x012d
        L_0x012c:
            r13 = 0
        L_0x012d:
            if (r1 == 0) goto L_0x0151
            boolean r0 = r1.u0()
            if (r0 == 0) goto L_0x014e
            java.lang.Object r0 = r1.e
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r18 = 0
            int r0 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r0 != 0) goto L_0x014e
            goto L_0x0151
        L_0x014e:
            r20 = r1
            goto L_0x0153
        L_0x0151:
            r20 = 0
        L_0x0153:
            if (r14 == 0) goto L_0x0179
            boolean r0 = r14.u0()
            if (r0 == 0) goto L_0x0176
            java.lang.Object r0 = r14.e
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            K0.c r0 = (K0.c) r0
            float r1 = r0.f378a
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x0176
            float r0 = r0.b
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x0176
            goto L_0x0179
        L_0x0176:
            r19 = r14
            goto L_0x017b
        L_0x0179:
            r19 = 0
        L_0x017b:
            if (r15 == 0) goto L_0x019f
            boolean r0 = r15.u0()
            if (r0 == 0) goto L_0x019c
            java.lang.Object r0 = r15.e
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r18 = 0
            int r0 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r0 != 0) goto L_0x019c
            goto L_0x019f
        L_0x019c:
            r24 = r15
            goto L_0x01a1
        L_0x019f:
            r24 = 0
        L_0x01a1:
            if (r11 == 0) goto L_0x01c5
            boolean r0 = r11.u0()
            if (r0 == 0) goto L_0x01c2
            java.lang.Object r0 = r11.e
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r9)
            K0.a r0 = (K0.a) r0
            java.lang.Object r0 = r0.b
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r18 = 0
            int r0 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r0 != 0) goto L_0x01c2
            goto L_0x01c5
        L_0x01c2:
            r25 = r11
            goto L_0x01c7
        L_0x01c5:
            r25 = 0
        L_0x01c7:
            D0.g r16 = new D0.g
            r18 = r13
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.C0047c.a(I0.d, x0.j):D0.g");
    }
}
