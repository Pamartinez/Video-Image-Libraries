package Ff;

import Ae.b;

public final class j implements b {
    public final /* synthetic */ int d;
    public final Object e;
    public final Object f;

    public /* synthetic */ j(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: Ve.b} */
    /* JADX WARNING: type inference failed for: r10v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007b, code lost:
        if (((Ze.C0894a) r0.e) != Ze.C0894a.TYPE_PARAMETER_BOUNDS) goto L_0x007d;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r14) {
        /*
            r13 = this;
            int r0 = r13.d
            java.lang.String r1 = "name"
            r2 = 36
            r3 = 46
            r4 = 0
            r5 = 1
            r6 = 0
            switch(r0) {
                case 0: goto L_0x03d6;
                case 1: goto L_0x03c4;
                case 2: goto L_0x02c3;
                case 3: goto L_0x0296;
                case 4: goto L_0x00d7;
                case 5: goto L_0x004c;
                case 6: goto L_0x0023;
                default: goto L_0x000e;
            }
        L_0x000e:
            Qe.d r14 = (Qe.C0814d) r14
            java.lang.Object r0 = r13.e
            tf.p r0 = (tf.C1312p) r0
            java.lang.Object r13 = r13.f
            Qe.d r13 = (Qe.C0814d) r13
            java.lang.String r1 = "second"
            kotlin.jvm.internal.j.e(r14, r1)
            r0.d(r13, r14)
            me.x r13 = me.x.f4917a
            return r13
        L_0x0023:
            java.lang.Object r0 = r13.e
            hf.p r0 = (hf.C1094p) r0
            java.lang.Object r13 = r13.f
            hf.d[] r13 = (hf.C1082d[]) r13
            java.lang.Number r14 = (java.lang.Number) r14
            int r14 = r14.intValue()
            if (r0 == 0) goto L_0x0041
            java.util.LinkedHashMap r0 = r0.f4596a
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            java.lang.Object r0 = r0.get(r1)
            hf.d r0 = (hf.C1082d) r0
            if (r0 != 0) goto L_0x004b
        L_0x0041:
            if (r14 < 0) goto L_0x0049
            int r0 = r13.length
            if (r14 >= r0) goto L_0x0049
            r0 = r13[r14]
            goto L_0x004b
        L_0x0049:
            hf.d r0 = hf.C1082d.e
        L_0x004b:
            return r0
        L_0x004c:
            java.lang.Object r0 = r13.e
            Jd.b r0 = (Jd.b) r0
            java.lang.Object r1 = r0.d
            B0.a r1 = (B0.a) r1
            java.lang.Object r13 = r13.f
            hf.a r13 = (hf.C1079a) r13
            java.lang.String r2 = "$this$extractNullability"
            kotlin.jvm.internal.j.e(r14, r2)
            Kf.d r13 = r13.f4583a
            Re.b r14 = (Re.b) r14
            boolean r2 = r14 instanceof df.C0943f
            if (r2 == 0) goto L_0x007d
            java.lang.Object r2 = r1.d
            cf.a r2 = (cf.C0922a) r2
            cf.b r2 = r2.t
            r2.getClass()
            r2 = r14
            df.f r2 = (df.C0943f) r2
            boolean r2 = r2.g
            if (r2 != 0) goto L_0x00d1
            java.lang.Object r0 = r0.e
            Ze.a r0 = (Ze.C0894a) r0
            Ze.a r2 = Ze.C0894a.TYPE_PARAMETER_BOUNDS
            if (r0 == r2) goto L_0x00d1
        L_0x007d:
            if (r13 == 0) goto L_0x00d2
            Hf.x r13 = (Hf.C0774x) r13
            qf.g r0 = Ne.i.e
            Hf.M r13 = r13.s0()
            Qe.i r13 = r13.g()
            if (r13 == 0) goto L_0x00d2
            Ne.l r13 = Ne.i.r(r13)
            if (r13 == 0) goto L_0x00d2
            java.lang.Object r13 = r1.d
            cf.a r13 = (cf.C0922a) r13
            Ze.b r13 = r13.q
            r13.getClass()
            qf.c r13 = Ne.p.t
            java.lang.Object r13 = Ze.C0895b.c(r14, r13)
            if (r13 != 0) goto L_0x00a5
            goto L_0x00d2
        L_0x00a5:
            java.util.ArrayList r13 = Ze.C0895b.a(r13, r4)
            boolean r14 = r13.isEmpty()
            if (r14 == 0) goto L_0x00b0
            goto L_0x00d2
        L_0x00b0:
            java.util.Iterator r13 = r13.iterator()
        L_0x00b4:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x00d2
            java.lang.Object r14 = r13.next()
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r0 = "TYPE"
            boolean r14 = kotlin.jvm.internal.j.a(r14, r0)
            if (r14 == 0) goto L_0x00b4
            java.lang.Object r13 = r1.d
            cf.a r13 = (cf.C0922a) r13
            cf.b r13 = r13.t
            r13.getClass()
        L_0x00d1:
            r4 = r5
        L_0x00d2:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r4)
            return r13
        L_0x00d7:
            java.lang.Object r0 = r13.e
            df.v r0 = (df.C0959v) r0
            java.lang.Object r13 = r13.f
            B0.a r13 = (B0.a) r13
            java.lang.Object r1 = r13.d
            cf.a r1 = (cf.C0922a) r1
            df.r r14 = (df.C0955r) r14
            java.lang.String r4 = "request"
            kotlin.jvm.internal.j.e(r14, r4)
            qf.b r4 = new qf.b
            df.q r5 = r0.f4262o
            qf.c r7 = r5.f3743i
            qf.g r8 = r14.f4258a
            r4.<init>(r7, r8)
            We.o r14 = r14.b
            r7 = 14
            java.lang.String r8 = "jvmMetadataVersion"
            if (r14 == 0) goto L_0x012b
            B1.b r9 = r1.f4007c
            pf.f r10 = r0.w()
            r9.getClass()
            kotlin.jvm.internal.j.e(r10, r8)
            qf.c r10 = r14.c()
            if (r10 == 0) goto L_0x0129
            java.lang.String r10 = r10.b()
            java.lang.Object r9 = r9.e
            java.lang.ClassLoader r9 = (java.lang.ClassLoader) r9
            java.lang.Class r9 = He.F.R(r9, r10)
            if (r9 == 0) goto L_0x0129
            Ve.b r9 = L1.d.e(r9)
            if (r9 == 0) goto L_0x0129
            B1.b r10 = new B1.b
            r10.<init>(r7, r9)
            goto L_0x0135
        L_0x0129:
            r10 = r6
            goto L_0x0135
        L_0x012b:
            B1.b r9 = r1.f4007c
            pf.f r10 = r0.w()
            B1.b r10 = r9.d(r4, r10)
        L_0x0135:
            if (r10 == 0) goto L_0x013c
            java.lang.Object r9 = r10.e
            Ve.b r9 = (Ve.b) r9
            goto L_0x013d
        L_0x013c:
            r9 = r6
        L_0x013d:
            if (r9 == 0) goto L_0x0146
            java.lang.Class r10 = r9.f3829a
            qf.b r10 = We.C0892d.a(r10)
            goto L_0x0147
        L_0x0146:
            r10 = r6
        L_0x0147:
            if (r10 == 0) goto L_0x015b
            qf.c r11 = r10.b
            qf.c r11 = r11.e()
            boolean r11 = r11.d()
            if (r11 == 0) goto L_0x028f
            boolean r10 = r10.f5034c
            if (r10 == 0) goto L_0x015b
            goto L_0x028f
        L_0x015b:
            df.t r10 = df.C0957t.f4260a
            if (r9 != 0) goto L_0x0160
            goto L_0x0197
        L_0x0160:
            ee.P r11 = r9.b
            java.lang.Object r11 = r11.f4277c
            kf.b r11 = (kf.C1116b) r11
            kf.b r12 = kf.C1116b.CLASS
            if (r11 != r12) goto L_0x0195
            B0.a r11 = r0.b
            java.lang.Object r11 = r11.d
            cf.a r11 = (cf.C0922a) r11
            jf.g r11 = r11.d
            r11.getClass()
            Df.g r12 = r11.f(r9)
            if (r12 != 0) goto L_0x017d
            r9 = r6
            goto L_0x018d
        L_0x017d:
            Df.l r11 = r11.c()
            Df.j r11 = r11.t
            java.lang.Class r9 = r9.f3829a
            qf.b r9 = We.C0892d.a(r9)
            Qe.f r9 = r11.a(r9, r12)
        L_0x018d:
            if (r9 == 0) goto L_0x0197
            df.s r10 = new df.s
            r10.<init>(r9)
            goto L_0x0197
        L_0x0195:
            df.u r10 = df.C0958u.f4261a
        L_0x0197:
            boolean r9 = r10 instanceof df.C0956s
            if (r9 == 0) goto L_0x01a1
            df.s r10 = (df.C0956s) r10
            Qe.f r6 = r10.f4259a
            goto L_0x028f
        L_0x01a1:
            boolean r9 = r10 instanceof df.C0958u
            if (r9 == 0) goto L_0x01a7
            goto L_0x028f
        L_0x01a7:
            boolean r9 = r10 instanceof df.C0957t
            if (r9 == 0) goto L_0x0290
            if (r14 != 0) goto L_0x01ed
            G0.e r14 = r1.b
            r14.getClass()
            qf.c r9 = r4.f5033a
            qf.c r10 = r4.b
            java.lang.String r10 = r10.b()
            java.lang.String r2 = Tf.v.r0(r10, r3, r2)
            boolean r10 = r9.d()
            if (r10 == 0) goto L_0x01c5
            goto L_0x01db
        L_0x01c5:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.b()
            r10.append(r9)
            r10.append(r3)
            r10.append(r2)
            java.lang.String r2 = r10.toString()
        L_0x01db:
            java.lang.Object r14 = r14.d
            java.lang.ClassLoader r14 = (java.lang.ClassLoader) r14
            java.lang.Class r14 = He.F.R(r14, r2)
            if (r14 == 0) goto L_0x01ec
            We.o r2 = new We.o
            r2.<init>(r14)
            r14 = r2
            goto L_0x01ed
        L_0x01ec:
            r14 = r6
        L_0x01ed:
            gf.f r2 = gf.C1075f.BINARY
            if (r2 != 0) goto L_0x0267
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Couldn't find kotlin binary class for light class created by kotlin binary file\nJavaClass: "
            r2.<init>(r3)
            r2.append(r14)
            java.lang.String r3 = "\nClassId: "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = "\nfindKotlinClass(JavaClass) = "
            r2.append(r3)
            B1.b r3 = r1.f4007c
            pf.f r5 = r0.w()
            java.lang.String r9 = "<this>"
            kotlin.jvm.internal.j.e(r3, r9)
            java.lang.String r9 = "javaClass"
            kotlin.jvm.internal.j.e(r14, r9)
            kotlin.jvm.internal.j.e(r5, r8)
            qf.c r14 = r14.c()
            if (r14 == 0) goto L_0x023d
            java.lang.String r14 = r14.b()
            java.lang.Object r3 = r3.e
            java.lang.ClassLoader r3 = (java.lang.ClassLoader) r3
            java.lang.Class r14 = He.F.R(r3, r14)
            if (r14 == 0) goto L_0x023d
            Ve.b r14 = L1.d.e(r14)
            if (r14 == 0) goto L_0x023d
            B1.b r3 = new B1.b
            r3.<init>(r7, r14)
            goto L_0x023e
        L_0x023d:
            r3 = r6
        L_0x023e:
            if (r3 == 0) goto L_0x0245
            java.lang.Object r14 = r3.e
            r6 = r14
            Ve.b r6 = (Ve.b) r6
        L_0x0245:
            r2.append(r6)
            java.lang.String r14 = "\nfindKotlinClass(ClassId) = "
            r2.append(r14)
            B1.b r14 = r1.f4007c
            pf.f r0 = r0.w()
            Ve.b r14 = He.F.E(r14, r4, r0)
            r2.append(r14)
            r14 = 10
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            r13.<init>(r14)
            throw r13
        L_0x0267:
            if (r14 == 0) goto L_0x026e
            qf.c r0 = r14.c()
            goto L_0x026f
        L_0x026e:
            r0 = r6
        L_0x026f:
            if (r0 == 0) goto L_0x028f
            boolean r2 = r0.d()
            if (r2 != 0) goto L_0x028f
            qf.c r0 = r0.e()
            qf.c r2 = r5.f3743i
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0284
            goto L_0x028f
        L_0x0284:
            df.i r0 = new df.i
            r0.<init>(r13, r5, r14, r6)
            Ze.l r13 = r1.s
            r13.getClass()
            r6 = r0
        L_0x028f:
            return r6
        L_0x0290:
            Dd.a r13 = new Dd.a
            r13.<init>()
            throw r13
        L_0x0296:
            java.lang.Object r0 = r13.e
            Te.K r0 = (Te.K) r0
            java.lang.Object r13 = r13.f
            df.n r13 = (df.C0951n) r13
            qf.g r14 = (qf.C1240g) r14
            java.lang.String r1 = "accessorName"
            kotlin.jvm.internal.j.e(r14, r1)
            qf.g r1 = r0.getName()
            boolean r1 = kotlin.jvm.internal.j.a(r1, r14)
            if (r1 == 0) goto L_0x02b6
            java.util.List r13 = o1.C0246a.e0(r0)
            java.util.Collection r13 = (java.util.Collection) r13
            goto L_0x02c2
        L_0x02b6:
            java.util.ArrayList r0 = r13.N(r14)
            java.util.ArrayList r13 = r13.O(r14)
            java.util.ArrayList r13 = ne.C1194l.X0(r13, r0)
        L_0x02c2:
            return r13
        L_0x02c3:
            java.lang.Object r0 = r13.e
            df.n r0 = (df.C0951n) r0
            java.lang.Object r13 = r13.f
            B0.a r13 = (B0.a) r13
            r9 = r14
            qf.g r9 = (qf.C1240g) r9
            kotlin.jvm.internal.j.e(r9, r1)
            Gf.i r14 = r0.r
            Qe.f r1 = r0.n
            java.lang.Object r14 = r14.invoke()
            java.util.Set r14 = (java.util.Set) r14
            boolean r14 = r14.contains(r9)
            if (r14 == 0) goto L_0x0342
            java.lang.Object r14 = r13.d
            cf.a r14 = (cf.C0922a) r14
            G0.e r14 = r14.b
            qf.b r0 = xf.C1353d.f(r1)
            kotlin.jvm.internal.j.b(r0)
            qf.b r0 = r0.d(r9)
            r14.getClass()
            qf.c r4 = r0.f5033a
            qf.c r0 = r0.b
            java.lang.String r0 = r0.b()
            java.lang.String r0 = Tf.v.r0(r0, r3, r2)
            boolean r2 = r4.d()
            if (r2 == 0) goto L_0x0308
            goto L_0x031e
        L_0x0308:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r4.b()
            r2.append(r4)
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
        L_0x031e:
            java.lang.Object r14 = r14.d
            java.lang.ClassLoader r14 = (java.lang.ClassLoader) r14
            java.lang.Class r14 = He.F.R(r14, r0)
            if (r14 == 0) goto L_0x032e
            We.o r0 = new We.o
            r0.<init>(r14)
            goto L_0x032f
        L_0x032e:
            r0 = r6
        L_0x032f:
            if (r0 == 0) goto L_0x03c3
            df.i r14 = new df.i
            r14.<init>(r13, r1, r0, r6)
            java.lang.Object r13 = r13.d
            cf.a r13 = (cf.C0922a) r13
            Ze.l r13 = r13.s
            r13.getClass()
            r6 = r14
            goto L_0x03c3
        L_0x0342:
            Gf.i r14 = r0.s
            java.lang.Object r14 = r14.invoke()
            java.util.Set r14 = (java.util.Set) r14
            boolean r14 = r14.contains(r9)
            if (r14 == 0) goto L_0x038b
            oe.c r14 = o1.C0246a.R()
            java.lang.Object r0 = r13.d
            cf.a r0 = (cf.C0922a) r0
            yf.e r0 = r0.f4013x
            yf.a r0 = (yf.C1357a) r0
            r0.c(r1, r9, r14, r13)
            oe.c r13 = o1.C0246a.K(r14)
            int r14 = r13.p()
            if (r14 == 0) goto L_0x03c3
            if (r14 != r5) goto L_0x0373
            java.lang.Object r13 = ne.C1194l.b1(r13)
            r6 = r13
            Qe.f r6 = (Qe.C0816f) r6
            goto L_0x03c3
        L_0x0373:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Multiple classes with same name are generated: "
            r0.<init>(r1)
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            java.lang.String r13 = r13.toString()
            r14.<init>(r13)
            throw r14
        L_0x038b:
            Gf.i r14 = r0.t
            java.lang.Object r14 = r14.invoke()
            java.util.Map r14 = (java.util.Map) r14
            java.lang.Object r14 = r14.get(r9)
            We.u r14 = (We.u) r14
            if (r14 == 0) goto L_0x03c3
            java.lang.Object r1 = r13.d
            cf.a r1 = (cf.C0922a) r1
            Gf.p r2 = r1.f4006a
            df.k r3 = new df.k
            r4 = 2
            r3.<init>(r0, r4)
            Gf.m r2 = (Gf.m) r2
            r2.getClass()
            Gf.i r10 = new Gf.i
            r10.<init>(r2, r3)
            Gf.p r7 = r1.f4006a
            Qe.f r8 = r0.n
            cf.c r11 = He.F.O(r13, r14)
            Ve.d r13 = r1.f4010j
            Ve.f r12 = r13.b(r14)
            Te.q r6 = Te.C0856q.e0(r7, r8, r9, r10, r11, r12)
        L_0x03c3:
            return r6
        L_0x03c4:
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            java.lang.Object r14 = r13.e
            java.util.concurrent.CompletableFuture r14 = (java.util.concurrent.CompletableFuture) r14
            r14.cancel(r4)
            java.lang.Object r13 = r13.f
            ag.a r13 = (ag.a) r13
            r13.cont = r6
            me.x r13 = me.x.f4917a
            return r13
        L_0x03d6:
            java.lang.Object r0 = r13.e
            D0.f r0 = (D0.f) r0
            java.lang.Object r13 = r13.f
            r8 = r13
            Ff.k r8 = (Ff.k) r8
            Df.n r13 = r8.f3389o
            r9 = r14
            qf.g r9 = (qf.C1240g) r9
            kotlin.jvm.internal.j.e(r9, r1)
            java.lang.Object r14 = r0.e
            java.util.LinkedHashMap r14 = (java.util.LinkedHashMap) r14
            java.lang.Object r14 = r14.get(r9)
            lf.t r14 = (lf.C1166t) r14
            if (r14 == 0) goto L_0x0414
            java.lang.Object r1 = r13.f3358a
            Df.l r1 = (Df.l) r1
            Gf.m r7 = r1.f3349a
            java.lang.Object r0 = r0.g
            r10 = r0
            Gf.i r10 = (Gf.i) r10
            Ff.a r11 = new Ff.a
            java.lang.Object r13 = r13.f3358a
            Df.l r13 = (Df.l) r13
            Gf.m r13 = r13.f3349a
            Df.E r0 = new Df.E
            r0.<init>((int) r5, (java.lang.Object) r8, (java.lang.Object) r14)
            r11.<init>(r13, r0)
            Qe.S r12 = Qe.Q.f3662a
            Te.q r6 = Te.C0856q.e0(r7, r8, r9, r10, r11, r12)
        L_0x0414:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.j.invoke(java.lang.Object):java.lang.Object");
    }
}
