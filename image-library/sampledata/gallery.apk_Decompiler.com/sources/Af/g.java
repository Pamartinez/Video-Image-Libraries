package Af;

import Ae.a;

public final class g implements a {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: ne.t} */
    /* JADX WARNING: type inference failed for: r1v63, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0331 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x032e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r23 = this;
            r0 = r23
            int r1 = r0.d
            ne.t r2 = ne.C1202t.d
            ne.u r3 = ne.C1203u.d
            r4 = 3
            r5 = 10
            r6 = 0
            java.lang.Object r0 = r0.e
            switch(r1) {
                case 0: goto L_0x05f8;
                case 1: goto L_0x05e9;
                case 2: goto L_0x05db;
                case 3: goto L_0x057d;
                case 4: goto L_0x04d8;
                case 5: goto L_0x04b9;
                case 6: goto L_0x04a0;
                case 7: goto L_0x0494;
                case 8: goto L_0x0489;
                case 9: goto L_0x0478;
                case 10: goto L_0x0468;
                case 11: goto L_0x045d;
                case 12: goto L_0x0455;
                case 13: goto L_0x044d;
                case 14: goto L_0x0445;
                case 15: goto L_0x0410;
                case 16: goto L_0x0379;
                case 17: goto L_0x035e;
                case 18: goto L_0x0351;
                case 19: goto L_0x0342;
                case 20: goto L_0x01d1;
                case 21: goto L_0x01ac;
                case 22: goto L_0x0179;
                case 23: goto L_0x0176;
                case 24: goto L_0x016b;
                case 25: goto L_0x00f8;
                case 26: goto L_0x0094;
                case 27: goto L_0x005b;
                default: goto L_0x0011;
            }
        L_0x0011:
            df.d r0 = (df.C0941d) r0
            df.q r1 = r0.f4243c
            Gf.i r2 = r1.m
            He.t[] r3 = df.C0954q.q
            r3 = r3[r6]
            java.lang.Object r2 = D1.f.y(r2, r3)
            java.util.Map r2 = (java.util.Map) r2
            java.util.Collection r2 = r2.values()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x0030:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x004e
            java.lang.Object r4 = r2.next()
            Ve.b r4 = (Ve.b) r4
            B0.a r5 = r0.b
            java.lang.Object r5 = r5.d
            cf.a r5 = (cf.C0922a) r5
            jf.g r5 = r5.d
            Ff.t r4 = r5.a(r1, r4)
            if (r4 == 0) goto L_0x0030
            r3.add(r4)
            goto L_0x0030
        L_0x004e:
            Qf.f r0 = He.F.N(r3)
            Af.p[] r1 = new Af.p[r6]
            java.lang.Object[] r0 = r0.toArray(r1)
            Af.p[] r0 = (Af.p[]) r0
            return r0
        L_0x005b:
            af.j r0 = (af.C0912j) r0
            gf.a r0 = r0.d
            boolean r1 = r0 instanceof We.h
            if (r1 == 0) goto L_0x0070
            java.lang.Object r1 = af.C0907e.f3994a
            We.h r0 = (We.h) r0
            java.util.ArrayList r0 = r0.a()
            vf.b r0 = af.C0907e.a(r0)
            goto L_0x0080
        L_0x0070:
            boolean r1 = r0 instanceof We.t
            if (r1 == 0) goto L_0x007f
            java.lang.Object r1 = af.C0907e.f3994a
            java.util.List r0 = o1.C0246a.e0(r0)
            vf.b r0 = af.C0907e.a(r0)
            goto L_0x0080
        L_0x007f:
            r0 = 0
        L_0x0080:
            if (r0 == 0) goto L_0x008e
            qf.g r1 = af.C0905c.b
            me.i r2 = new me.i
            r2.<init>(r1, r0)
            java.util.Map r7 = ne.z.a0(r2)
            goto L_0x008f
        L_0x008e:
            r7 = 0
        L_0x008f:
            if (r7 != 0) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            r3 = r7
        L_0x0093:
            return r3
        L_0x0094:
            af.i r0 = (af.C0911i) r0
            java.lang.Object r1 = af.C0907e.f3994a
            gf.a r0 = r0.d
            boolean r1 = r0 instanceof We.t
            if (r1 == 0) goto L_0x00a1
            We.t r0 = (We.t) r0
            goto L_0x00a2
        L_0x00a1:
            r0 = 0
        L_0x00a2:
            if (r0 == 0) goto L_0x00e3
            java.lang.Object r1 = af.C0907e.b
            java.lang.Enum r0 = r0.b
            java.lang.String r0 = r0.name()
            qf.g r0 = qf.C1240g.e(r0)
            java.lang.String r0 = r0.b()
            java.lang.Object r0 = r1.get(r0)
            Re.m r0 = (Re.m) r0
            if (r0 == 0) goto L_0x00e3
            vf.i r1 = new vf.i
            qf.c r2 = Ne.p.v
            java.lang.String r4 = "topLevelFqName"
            kotlin.jvm.internal.j.e(r2, r4)
            qf.b r4 = new qf.b
            qf.c r5 = r2.e()
            qf.g r2 = r2.f()
            java.lang.String r6 = "shortName(...)"
            kotlin.jvm.internal.j.d(r2, r6)
            r4.<init>(r5, r2)
            java.lang.String r0 = r0.name()
            qf.g r0 = qf.C1240g.e(r0)
            r1.<init>(r4, r0)
            goto L_0x00e4
        L_0x00e3:
            r1 = 0
        L_0x00e4:
            if (r1 == 0) goto L_0x00f2
            qf.g r0 = af.C0905c.f3993c
            me.i r2 = new me.i
            r2.<init>(r0, r1)
            java.util.Map r7 = ne.z.a0(r2)
            goto L_0x00f3
        L_0x00f2:
            r7 = 0
        L_0x00f3:
            if (r7 != 0) goto L_0x00f6
            goto L_0x00f7
        L_0x00f6:
            r3 = r7
        L_0x00f7:
            return r3
        L_0x00f8:
            Ze.v r0 = (Ze.v) r0
            oe.c r1 = o1.C0246a.R()
            Ze.C r2 = r0.f3960a
            java.lang.String r2 = r2.a()
            r1.add(r2)
            Ze.C r2 = r0.b
            if (r2 == 0) goto L_0x0120
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "under-migration:"
            r3.<init>(r4)
            java.lang.String r2 = r2.a()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.add(r2)
        L_0x0120:
            java.util.Map r0 = r0.f3961c
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x012a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x015e
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "@"
            r3.<init>(r4)
            java.lang.Object r4 = r2.getKey()
            r3.append(r4)
            r4 = 58
            r3.append(r4)
            java.lang.Object r2 = r2.getValue()
            Ze.C r2 = (Ze.C) r2
            java.lang.String r2 = r2.a()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.add(r2)
            goto L_0x012a
        L_0x015e:
            oe.c r0 = o1.C0246a.K(r1)
            java.lang.String[] r1 = new java.lang.String[r6]
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            return r0
        L_0x016b:
            Te.P r0 = (Te.P) r0
            me.m r0 = r0.f3769p
            java.lang.Object r0 = r0.getValue()
            java.util.List r0 = (java.util.List) r0
            return r0
        L_0x0176:
            java.util.List r0 = (java.util.List) r0
            return r0
        L_0x0179:
            Te.p r0 = (Te.C0855p) r0
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            Te.q r2 = r0.e
            Gf.n r2 = r2.l
            java.lang.Object r2 = r2.invoke()
            java.util.Set r2 = (java.util.Set) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x018e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01ab
            java.lang.Object r3 = r2.next()
            qf.g r3 = (qf.C1240g) r3
            Ye.c r4 = Ye.c.FOR_NON_TRACKED_SCOPE
            java.util.Collection r5 = r0.a(r3, r4)
            r1.addAll(r5)
            java.util.Collection r3 = r0.f(r3, r4)
            r1.addAll(r3)
            goto L_0x018e
        L_0x01ab:
            return r1
        L_0x01ac:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Scope for type parameter "
            r1.<init>(r2)
            Df.E r0 = (Df.E) r0
            java.lang.Object r2 = r0.e
            qf.g r2 = (qf.C1240g) r2
            java.lang.String r2 = r2.b()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object r0 = r0.f
            Te.h r0 = (Te.C0847h) r0
            java.util.List r0 = r0.getUpperBounds()
            Af.p r0 = L1.d.d(r1, r0)
            return r0
        L_0x01d1:
            r10 = r0
            Te.f r10 = (Te.C0845f) r10
            r0 = r10
            Ff.w r0 = (Ff.w) r0
            Qe.f r1 = r0.E0()
            if (r1 != 0) goto L_0x01df
            goto L_0x0341
        L_0x01df:
            java.util.Collection r1 = r1.d()
            java.lang.String r3 = "getConstructors(...)"
            kotlin.jvm.internal.j.d(r1, r3)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x01f3:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0340
            java.lang.Object r4 = r1.next()
            Te.i r4 = (Te.C0848i) r4
            Te.C r8 = Te.N.f3763K
            Gf.p r9 = r10.f3774i
            kotlin.jvm.internal.j.b(r4)
            r8.getClass()
            java.lang.String r8 = "storageManager"
            kotlin.jvm.internal.j.e(r9, r8)
            Qe.f r8 = r0.E0()
            if (r8 != 0) goto L_0x0216
            r8 = 0
            goto L_0x021e
        L_0x0216:
            Hf.B r8 = r0.F0()
            Hf.X r8 = Hf.X.d(r8)
        L_0x021e:
            if (r8 != 0) goto L_0x0227
        L_0x0220:
            r23 = r0
            r12 = 0
            r21 = 0
            goto L_0x032c
        L_0x0227:
            Te.i r11 = r4.c(r8)
            if (r11 != 0) goto L_0x022e
            goto L_0x0220
        L_0x022e:
            Te.N r12 = new Te.N
            r13 = r4
            Bf.a r13 = (Bf.a) r13
            Re.h r13 = r13.getAnnotations()
            Te.t r4 = (Te.t) r4
            Qe.c r14 = r4.b()
            java.lang.String r15 = "getKind(...)"
            kotlin.jvm.internal.j.d(r14, r15)
            Qe.Q r15 = r10.getSource()
            java.lang.String r6 = "getSource(...)"
            kotlin.jvm.internal.j.d(r15, r6)
            r6 = r8
            r8 = r12
            r12 = 0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r22 = r11
            r11 = r8
            r8 = r22
            java.util.List r12 = r4.B()
            if (r12 == 0) goto L_0x0338
            r15 = 0
            r16 = 0
            r14 = 0
            r13 = r6
            java.util.ArrayList r17 = Te.t.I0(r11, r12, r13, r14, r15, r16)
            if (r17 != 0) goto L_0x0268
            goto L_0x0220
        L_0x0268:
            Te.t r8 = (Te.t) r8
            Hf.x r8 = r8.k
            Hf.c0 r8 = r8.x0()
            Hf.B r8 = Hf.C0754c.m(r8)
            Hf.B r9 = r0.i()
            Hf.B r18 = Hf.C0754c.F(r8, r9)
            Te.u r8 = r4.n
            Re.f r9 = Re.g.f3692a
            if (r8 == 0) goto L_0x0292
            Hf.x r8 = r8.getType()
            Hf.d0 r12 = Hf.d0.INVARIANT
            Hf.x r8 = r6.g(r8, r12)
            Te.u r8 = tf.C1312p.k(r11, r8, r9)
            r13 = r8
            goto L_0x0293
        L_0x0292:
            r13 = 0
        L_0x0293:
            Qe.f r8 = r0.E0()
            if (r8 == 0) goto L_0x031b
            java.util.List r4 = r4.i0()
            java.lang.String r12 = "getContextReceiverParameters(...)"
            kotlin.jvm.internal.j.d(r4, r12)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r12 = new java.util.ArrayList
            int r14 = ne.C1196n.w0(r4, r5)
            r12.<init>(r14)
            java.util.Iterator r4 = r4.iterator()
            r14 = 0
        L_0x02b2:
            boolean r15 = r4.hasNext()
            if (r15 == 0) goto L_0x0315
            java.lang.Object r15 = r4.next()
            int r16 = r14 + 1
            if (r14 < 0) goto L_0x030f
            Te.u r15 = (Te.u) r15
            Hf.x r5 = r15.getType()
            r21 = 0
            Hf.d0 r7 = Hf.d0.INVARIANT
            Hf.x r5 = r6.g(r5, r7)
            Bf.e r7 = r15.E0()
            java.lang.String r15 = "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.receivers.ImplicitContextReceiver"
            kotlin.jvm.internal.j.c(r7, r15)
            Bf.b r7 = (Bf.b) r7
            qf.g r7 = r7.C0()
            Te.u r15 = new Te.u
            r23 = r0
            Bf.b r0 = new Bf.b
            r0.<init>((Qe.C0816f) r8, (Hf.C0774x) r5, (qf.C1240g) r7)
            Tf.m r5 = qf.C1241h.f5040a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = qf.C1241h.b
            r5.append(r7)
            r7 = 95
            r5.append(r7)
            r5.append(r14)
            java.lang.String r5 = r5.toString()
            qf.g r5 = qf.C1240g.e(r5)
            r15.<init>(r8, r0, r9, r5)
            r12.add(r15)
            r0 = r23
            r14 = r16
            r5 = 10
            goto L_0x02b2
        L_0x030f:
            r21 = 0
            ne.C1195m.v0()
            throw r21
        L_0x0315:
            r15 = r12
        L_0x0316:
            r23 = r0
            r21 = 0
            goto L_0x031d
        L_0x031b:
            r15 = r2
            goto L_0x0316
        L_0x031d:
            java.util.List r16 = r10.j()
            Qe.A r19 = Qe.A.FINAL
            Qe.p r0 = r10.f3775j
            r14 = 0
            r20 = r0
            r12 = r11
            r12.J0(r13, r14, r15, r16, r17, r18, r19, r20)
        L_0x032c:
            if (r12 == 0) goto L_0x0331
            r3.add(r12)
        L_0x0331:
            r0 = r23
            r5 = 10
            r6 = 0
            goto L_0x01f3
        L_0x0338:
            r21 = 0
            r0 = 28
            Te.t.w0(r0)
            throw r21
        L_0x0340:
            r2 = r3
        L_0x0341:
            return r2
        L_0x0342:
            Re.j r0 = (Re.j) r0
            Ne.i r1 = r0.f3693a
            qf.c r0 = r0.b
            Qe.f r0 = r1.i(r0)
            Hf.B r0 = r0.i()
            return r0
        L_0x0351:
            Qe.P r0 = (Qe.P) r0
            Ae.b r0 = r0.b
            If.f r1 = If.f.f3461a
            java.lang.Object r0 = r0.invoke(r1)
            Af.p r0 = (Af.p) r0
            return r0
        L_0x035e:
            r21 = 0
            Pe.k r0 = (Pe.k) r0
            Ne.m r1 = r0.f
            if (r1 == 0) goto L_0x0371
            java.lang.Object r1 = r1.invoke()
            Pe.i r1 = (Pe.i) r1
            r2 = r21
            r0.f = r2
            return r1
        L_0x0371:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "JvmBuiltins instance has not been initialized properly"
            r0.<init>(r1)
            throw r0
        L_0x0379:
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            r6 = 0
        L_0x0384:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x040b
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            boolean r3 = r1 instanceof boolean[]
            if (r3 == 0) goto L_0x03a5
            boolean[] r1 = (boolean[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03a5:
            boolean r3 = r1 instanceof char[]
            if (r3 == 0) goto L_0x03b0
            char[] r1 = (char[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03b0:
            boolean r3 = r1 instanceof byte[]
            if (r3 == 0) goto L_0x03bb
            byte[] r1 = (byte[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03bb:
            boolean r3 = r1 instanceof short[]
            if (r3 == 0) goto L_0x03c6
            short[] r1 = (short[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03c6:
            boolean r3 = r1 instanceof int[]
            if (r3 == 0) goto L_0x03d1
            int[] r1 = (int[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03d1:
            boolean r3 = r1 instanceof float[]
            if (r3 == 0) goto L_0x03dc
            float[] r1 = (float[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03dc:
            boolean r3 = r1 instanceof long[]
            if (r3 == 0) goto L_0x03e7
            long[] r1 = (long[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03e7:
            boolean r3 = r1 instanceof double[]
            if (r3 == 0) goto L_0x03f2
            double[] r1 = (double[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03f2:
            boolean r3 = r1 instanceof java.lang.Object[]
            if (r3 == 0) goto L_0x03fd
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            int r1 = java.util.Arrays.hashCode(r1)
            goto L_0x0401
        L_0x03fd:
            int r1 = r1.hashCode()
        L_0x0401:
            int r2 = r2.hashCode()
            int r2 = r2 * 127
            r1 = r1 ^ r2
            int r6 = r6 + r1
            goto L_0x0384
        L_0x040b:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            return r0
        L_0x0410:
            Ke.t0 r0 = (Ke.t0) r0
            Qe.V r0 = r0.d
            java.util.List r0 = r0.getUpperBounds()
            java.lang.String r1 = "getUpperBounds(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = ne.C1196n.w0(r0, r2)
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x042e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0444
            java.lang.Object r2 = r0.next()
            Hf.x r2 = (Hf.C0774x) r2
            Ke.r0 r3 = new Ke.r0
            r4 = 0
            r3.<init>(r2, r4)
            r1.add(r3)
            goto L_0x042e
        L_0x0444:
            return r1
        L_0x0445:
            Ke.N r0 = (Ke.N) r0
            Ke.M r1 = new Ke.M
            r1.<init>(r0)
            return r1
        L_0x044d:
            Ke.L r0 = (Ke.L) r0
            Ke.K r1 = new Ke.K
            r1.<init>(r0)
            return r1
        L_0x0455:
            Ke.J r0 = (Ke.J) r0
            Ke.I r1 = new Ke.I
            r1.<init>(r0)
            return r1
        L_0x045d:
            Ke.F r0 = (Ke.F) r0
            java.lang.Class r0 = r0.b()
            Ve.e r0 = Ke.w0.a(r0)
            return r0
        L_0x0468:
            If.i r0 = (If.i) r0
            Ae.a r0 = r0.b
            if (r0 == 0) goto L_0x0476
            java.lang.Object r0 = r0.invoke()
            r7 = r0
            java.util.List r7 = (java.util.List) r7
            goto L_0x0477
        L_0x0476:
            r7 = 0
        L_0x0477:
            return r7
        L_0x0478:
            D0.e r0 = (D0.e) r0
            Jf.k r1 = Jf.k.CANNOT_COMPUTE_ERASED_BOUND
            java.lang.String r0 = r0.toString()
            java.lang.String[] r0 = new java.lang.String[]{r0}
            Jf.i r0 = Jf.l.c(r1, r0)
            return r0
        L_0x0489:
            Hf.G r0 = (Hf.G) r0
            java.lang.Object r0 = r0.b
            Qe.V r0 = (Qe.V) r0
            Hf.x r0 = Hf.C0754c.x(r0)
            return r0
        L_0x0494:
            Hf.g r0 = (Hf.C0758g) r0
            Hf.f r1 = new Hf.f
            java.util.Collection r0 = r0.b()
            r1.<init>(r0)
            return r1
        L_0x04a0:
            Ff.x r0 = (Ff.x) r0
            Df.n r1 = r0.f3410o
            java.lang.Object r2 = r1.f3358a
            Df.l r2 = (Df.l) r2
            Df.d r2 = r2.e
            lf.W r0 = r0.f3411p
            java.lang.Object r1 = r1.b
            nf.f r1 = (nf.C1209f) r1
            java.util.ArrayList r0 = r2.n(r0, r1)
            java.util.List r0 = ne.C1194l.k1(r0)
            return r0
        L_0x04b9:
            Ff.s r0 = (Ff.s) r0
            java.util.Set r1 = r0.n()
            if (r1 != 0) goto L_0x04c3
            r7 = 0
            goto L_0x04d7
        L_0x04c3:
            java.util.Set r2 = r0.m()
            Ff.r r0 = r0.f3397c
            java.util.LinkedHashMap r0 = r0.f3394c
            java.util.Set r0 = r0.keySet()
            java.util.LinkedHashSet r0 = ne.C1182C.b0(r2, r0)
            java.util.LinkedHashSet r7 = ne.C1182C.b0(r0, r1)
        L_0x04d7:
            return r7
        L_0x04d8:
            D0.f r0 = (D0.f) r0
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.lang.Object r0 = r0.f106h
            Ff.k r0 = (Ff.k) r0
            Ff.i r2 = r0.q
            Df.n r3 = r0.f3389o
            lf.j r0 = r0.f3386h
            java.util.List r2 = r2.h()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x04f3:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x052a
            java.lang.Object r5 = r2.next()
            Hf.x r5 = (Hf.C0774x) r5
            Af.p r5 = r5.A()
            r6 = 0
            java.util.Collection r5 = He.F.F(r5, r6, r4)
            java.util.Iterator r5 = r5.iterator()
        L_0x050c:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x04f3
            java.lang.Object r6 = r5.next()
            Qe.l r6 = (Qe.C0822l) r6
            boolean r7 = r6 instanceof Te.K
            if (r7 != 0) goto L_0x0520
            boolean r7 = r6 instanceof Qe.O
            if (r7 == 0) goto L_0x050c
        L_0x0520:
            Qe.d r6 = (Qe.C0814d) r6
            qf.g r6 = r6.getName()
            r1.add(r6)
            goto L_0x050c
        L_0x052a:
            java.util.List r2 = r0.t
            java.lang.String r4 = "getFunctionList(...)"
            kotlin.jvm.internal.j.d(r2, r4)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x0537:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0551
            java.lang.Object r4 = r2.next()
            lf.y r4 = (lf.C1171y) r4
            java.lang.Object r5 = r3.b
            nf.f r5 = (nf.C1209f) r5
            int r4 = r4.f4888i
            qf.g r4 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r5, r4)
            r1.add(r4)
            goto L_0x0537
        L_0x0551:
            java.util.List r0 = r0.u
            java.lang.String r2 = "getPropertyList(...)"
            kotlin.jvm.internal.j.d(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x055e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0578
            java.lang.Object r2 = r0.next()
            lf.G r2 = (lf.G) r2
            java.lang.Object r4 = r3.b
            nf.f r4 = (nf.C1209f) r4
            int r2 = r2.f4749i
            qf.g r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r4, r2)
            r1.add(r2)
            goto L_0x055e
        L_0x0578:
            java.util.LinkedHashSet r0 = ne.C1182C.b0(r1, r1)
            return r0
        L_0x057d:
            Ef.d r0 = (Ef.d) r0
            D0.f r0 = r0.m
            java.lang.Object r0 = r0.f106h
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.util.Set r0 = r0.keySet()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0592:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x05b7
            java.lang.Object r2 = r0.next()
            r3 = r2
            qf.b r3 = (qf.C1235b) r3
            qf.c r4 = r3.b
            qf.c r4 = r4.e()
            boolean r4 = r4.d()
            if (r4 == 0) goto L_0x0592
            java.util.Set r4 = Df.j.f3346c
            boolean r3 = r4.contains(r3)
            if (r3 != 0) goto L_0x0592
            r1.add(r2)
            goto L_0x0592
        L_0x05b7:
            java.util.ArrayList r0 = new java.util.ArrayList
            r2 = 10
            int r2 = ne.C1196n.w0(r1, r2)
            r0.<init>(r2)
            java.util.Iterator r1 = r1.iterator()
        L_0x05c6:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x05da
            java.lang.Object r2 = r1.next()
            qf.b r2 = (qf.C1235b) r2
            qf.g r2 = r2.f()
            r0.add(r2)
            goto L_0x05c6
        L_0x05da:
            return r0
        L_0x05db:
            Af.u r0 = (Af.u) r0
            Af.p r1 = r0.b
            r2 = 0
            java.util.Collection r1 = He.F.F(r1, r2, r4)
            java.util.Collection r0 = r0.i(r1)
            return r0
        L_0x05e9:
            Hf.X r0 = (Hf.X) r0
            Hf.T r0 = r0.f()
            r0.getClass()
            Hf.X r1 = new Hf.X
            r1.<init>(r0)
            return r1
        L_0x05f8:
            Af.i r0 = (Af.i) r0
            java.util.List r1 = r0.h()
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r4)
            Te.b r10 = r0.b
            Hf.M r6 = r10.q()
            java.util.Collection r6 = r6.h()
            java.lang.String r7 = "getSupertypes(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r6.iterator()
        L_0x0620:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x063b
            java.lang.Object r8 = r6.next()
            Hf.x r8 = (Hf.C0774x) r8
            Af.p r8 = r8.A()
            r9 = 0
            java.util.Collection r8 = He.F.F(r8, r9, r4)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            ne.C1200r.A0(r8, r7)
            goto L_0x0620
        L_0x063b:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r6 = r7.iterator()
        L_0x0644:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0656
            java.lang.Object r7 = r6.next()
            boolean r8 = r7 instanceof Qe.C0814d
            if (r8 == 0) goto L_0x0644
            r4.add(r7)
            goto L_0x0644
        L_0x0656:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x065f:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x0684
            java.lang.Object r7 = r4.next()
            r8 = r7
            Qe.d r8 = (Qe.C0814d) r8
            qf.g r8 = r8.getName()
            java.lang.Object r9 = r6.get(r8)
            if (r9 != 0) goto L_0x067e
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r6.put(r8, r9)
        L_0x067e:
            java.util.List r9 = (java.util.List) r9
            r9.add(r7)
            goto L_0x065f
        L_0x0684:
            java.util.Set r4 = r6.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x068c:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x073d
            java.lang.Object r6 = r4.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            java.lang.String r8 = "component1(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            qf.g r7 = (qf.C1240g) r7
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>()
            java.util.Iterator r6 = r6.iterator()
        L_0x06b4:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x06db
            java.lang.Object r9 = r6.next()
            r11 = r9
            Qe.d r11 = (Qe.C0814d) r11
            boolean r11 = r11 instanceof Qe.C0831v
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)
            java.lang.Object r12 = r8.get(r11)
            if (r12 != 0) goto L_0x06d5
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r8.put(r11, r12)
        L_0x06d5:
            java.util.List r12 = (java.util.List) r12
            r12.add(r9)
            goto L_0x06b4
        L_0x06db:
            java.util.Set r6 = r8.entrySet()
            java.util.Iterator r12 = r6.iterator()
        L_0x06e3:
            boolean r6 = r12.hasNext()
            if (r6 == 0) goto L_0x068c
            java.lang.Object r6 = r12.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r8 = r6.getKey()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            r9 = r6
            tf.o r6 = tf.C1311o.f5142c
            java.util.Collection r9 = (java.util.Collection) r9
            if (r8 == 0) goto L_0x072f
            r8 = r1
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r8 = r8.iterator()
        L_0x0712:
            boolean r13 = r8.hasNext()
            if (r13 == 0) goto L_0x0730
            java.lang.Object r13 = r8.next()
            r14 = r13
            Qe.v r14 = (Qe.C0831v) r14
            Te.m r14 = (Te.C0852m) r14
            qf.g r14 = r14.getName()
            boolean r14 = kotlin.jvm.internal.j.a(r14, r7)
            if (r14 == 0) goto L_0x0712
            r11.add(r13)
            goto L_0x0712
        L_0x072f:
            r11 = r2
        L_0x0730:
            java.util.Collection r11 = (java.util.Collection) r11
            r8 = r9
            r9 = r11
            Af.h r11 = new Af.h
            r11.<init>(r5, r0)
            r6.h(r7, r8, r9, r10, r11)
            goto L_0x06e3
        L_0x073d:
            java.util.List r0 = Qf.k.d(r5)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r0 = ne.C1194l.X0(r0, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Af.g.invoke():java.lang.Object");
    }
}
