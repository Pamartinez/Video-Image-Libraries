package Ke;

import Ae.a;

public final class h0 implements a {
    public final /* synthetic */ int d;
    public final n0 e;

    public /* synthetic */ h0(n0 n0Var, int i2) {
        this.d = i2;
        this.e = n0Var;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01c5, code lost:
        if (D1.f.D((Qe.C0816f) r8) == false) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01f0, code lost:
        if (r2 != false) goto L_0x01f2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r17 = this;
            r0 = r17
            int r1 = r0.d
            r2 = 2
            r3 = 0
            Ke.n0 r0 = r0.e
            r4 = 1
            r5 = 0
            switch(r1) {
                case 0: goto L_0x0171;
                default: goto L_0x000d;
            }
        L_0x000d:
            Ke.F r1 = r0.f3506j
            java.lang.String r6 = r0.k
            java.lang.String r0 = r0.l
            r1.getClass()
            java.lang.String r7 = "name"
            kotlin.jvm.internal.j.e(r6, r7)
            java.lang.String r7 = "signature"
            kotlin.jvm.internal.j.e(r0, r7)
            Tf.m r7 = Ke.F.d
            r7.getClass()
            java.io.Serializable r7 = r7.e
            java.util.regex.Pattern r7 = (java.util.regex.Pattern) r7
            java.util.regex.Matcher r7 = r7.matcher(r0)
            java.lang.String r8 = "matcher(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            boolean r8 = r7.matches()
            if (r8 != 0) goto L_0x0039
            goto L_0x003e
        L_0x0039:
            Tf.j r3 = new Tf.j
            r3.<init>(r7, r0)
        L_0x003e:
            if (r3 == 0) goto L_0x007b
            Tf.i r0 = r3.f3824c
            if (r0 != 0) goto L_0x004b
            Tf.i r0 = new Tf.i
            r0.<init>(r5, r3)
            r3.f3824c = r0
        L_0x004b:
            Tf.i r0 = r3.f3824c
            kotlin.jvm.internal.j.b(r0)
            java.lang.Object r0 = r0.get(r4)
            java.lang.String r0 = (java.lang.String) r0
            int r2 = java.lang.Integer.parseInt(r0)
            Qe.O r2 = r1.k(r2)
            if (r2 == 0) goto L_0x0062
            goto L_0x015f
        L_0x0062:
            Ke.v0 r2 = new Ke.v0
            java.lang.String r3 = "Local property #"
            java.lang.String r4 = " not found in "
            java.lang.StringBuilder r0 = N2.j.k(r3, r0, r4)
            java.lang.Class r1 = r1.b()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0, r5)
            throw r2
        L_0x007b:
            qf.g r3 = qf.C1240g.e(r6)
            java.util.Collection r3 = r1.r(r3)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r3 = r3.iterator()
        L_0x008e:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x00ad
            java.lang.Object r8 = r3.next()
            r9 = r8
            Qe.O r9 = (Qe.O) r9
            a.a r9 = Ke.C0.b(r9)
            java.lang.String r9 = r9.h()
            boolean r9 = kotlin.jvm.internal.j.a(r9, r0)
            if (r9 == 0) goto L_0x008e
            r7.add(r8)
            goto L_0x008e
        L_0x00ad:
            boolean r3 = r7.isEmpty()
            java.lang.String r8 = ") not resolved in "
            java.lang.String r9 = "' (JVM signature: "
            java.lang.String r10 = "Property '"
            if (r3 != 0) goto L_0x0160
            int r3 = r7.size()
            if (r3 == r4) goto L_0x0158
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x00c8:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x00ed
            java.lang.Object r11 = r7.next()
            r12 = r11
            Qe.O r12 = (Qe.O) r12
            Qe.p r12 = r12.getVisibility()
            java.lang.Object r13 = r3.get(r12)
            if (r13 != 0) goto L_0x00e7
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r3.put(r12, r13)
        L_0x00e7:
            java.util.List r13 = (java.util.List) r13
            r13.add(r11)
            goto L_0x00c8
        L_0x00ed:
            Ke.f r7 = new Ke.f
            r7.<init>(r2)
            java.util.TreeMap r2 = new java.util.TreeMap
            r2.<init>(r7)
            r2.putAll(r3)
            java.util.Collection r2 = r2.values()
            java.lang.String r3 = "<get-values>(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r2 = ne.C1194l.S0(r2)
            java.util.List r2 = (java.util.List) r2
            int r3 = r2.size()
            if (r3 != r4) goto L_0x0119
            java.lang.Object r0 = ne.C1194l.L0(r2)
            r2 = r0
            Qe.O r2 = (Qe.O) r2
            goto L_0x015f
        L_0x0119:
            qf.g r2 = qf.C1240g.e(r6)
            java.util.Collection r2 = r1.r(r2)
            r11 = r2
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            Ke.b r15 = Ke.C0779b.f3498i
            r16 = 30
            java.lang.String r12 = "\n"
            r13 = 0
            r14 = 0
            java.lang.String r2 = ne.C1194l.R0(r11, r12, r13, r14, r15, r16)
            Ke.v0 r3 = new Ke.v0
            java.lang.StringBuilder r0 = c0.C0086a.q(r10, r6, r9, r0, r8)
            r0.append(r1)
            r1 = 58
            r0.append(r1)
            int r1 = r2.length()
            if (r1 != 0) goto L_0x0147
            java.lang.String r1 = " no members found"
            goto L_0x014d
        L_0x0147:
            java.lang.String r1 = "\n"
            java.lang.String r1 = r1.concat(r2)
        L_0x014d:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0, r5)
            throw r3
        L_0x0158:
            java.lang.Object r0 = ne.C1194l.b1(r7)
            r2 = r0
            Qe.O r2 = (Qe.O) r2
        L_0x015f:
            return r2
        L_0x0160:
            Ke.v0 r2 = new Ke.v0
            java.lang.StringBuilder r0 = c0.C0086a.q(r10, r6, r9, r0, r8)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0, r5)
            throw r2
        L_0x0171:
            qf.b r1 = Ke.C0.f3487a
            Qe.O r1 = r0.j()
            Ke.F r0 = r0.f3506j
            a.a r1 = Ke.C0.b(r1)
            boolean r6 = r1 instanceof Ke.C0796n
            if (r6 == 0) goto L_0x023b
            Ke.n r1 = (Ke.C0796n) r1
            lf.G r6 = r1.e
            Qe.O r7 = r1.d
            rf.h r8 = pf.i.f5029a
            nf.f r8 = r1.g
            B1.b r1 = r1.f3503h
            pf.d r1 = pf.i.b(r6, r8, r1, r4)
            if (r1 == 0) goto L_0x024d
            Qe.c r8 = r7.b()
            Qe.c r9 = Qe.C0813c.FAKE_OVERRIDE
            if (r8 != r9) goto L_0x019d
        L_0x019b:
            r4 = r5
            goto L_0x01f2
        L_0x019d:
            Qe.l r8 = r7.g()
            if (r8 == 0) goto L_0x0220
            boolean r2 = tf.C1301e.l(r8)
            if (r2 == 0) goto L_0x01c8
            Qe.l r2 = r8.g()
            Qe.g r9 = Qe.C0817g.CLASS
            boolean r9 = tf.C1301e.n(r2, r9)
            if (r9 != 0) goto L_0x01bd
            Qe.g r9 = Qe.C0817g.ENUM_CLASS
            boolean r2 = tf.C1301e.n(r2, r9)
            if (r2 == 0) goto L_0x01c8
        L_0x01bd:
            Qe.f r8 = (Qe.C0816f) r8
            java.util.LinkedHashSet r2 = Ne.d.f3545a
            boolean r2 = D1.f.D(r8)
            if (r2 != 0) goto L_0x01c8
            goto L_0x01f2
        L_0x01c8:
            Qe.l r2 = r7.g()
            boolean r2 = tf.C1301e.l(r2)
            if (r2 == 0) goto L_0x019b
            Te.r r2 = r7.h0()
            if (r2 == 0) goto L_0x01e6
            Re.h r2 = r2.getAnnotations()
            qf.c r8 = Ze.w.f3962a
            boolean r2 = r2.g(r8)
            if (r2 == 0) goto L_0x01e6
            r2 = r4
            goto L_0x01f0
        L_0x01e6:
            Re.h r2 = r7.getAnnotations()
            qf.c r8 = Ze.w.f3962a
            boolean r2 = r2.g(r8)
        L_0x01f0:
            if (r2 == 0) goto L_0x019b
        L_0x01f2:
            if (r4 != 0) goto L_0x020f
            boolean r2 = pf.i.d(r6)
            if (r2 == 0) goto L_0x01fb
            goto L_0x020f
        L_0x01fb:
            Qe.l r2 = r7.g()
            boolean r4 = r2 instanceof Qe.C0816f
            if (r4 == 0) goto L_0x020a
            Qe.f r2 = (Qe.C0816f) r2
            java.lang.Class r0 = Ke.E0.k(r2)
            goto L_0x0217
        L_0x020a:
            java.lang.Class r0 = r0.b()
            goto L_0x0217
        L_0x020f:
            java.lang.Class r0 = r0.b()
            java.lang.Class r0 = r0.getEnclosingClass()
        L_0x0217:
            if (r0 == 0) goto L_0x024d
            java.lang.String r1 = r1.d     // Catch:{ NoSuchFieldException -> 0x024d }
            java.lang.reflect.Field r3 = r0.getDeclaredField(r1)     // Catch:{ NoSuchFieldException -> 0x024d }
            goto L_0x024d
        L_0x0220:
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = "companionObject"
            r0[r5] = r1
            java.lang.String r1 = "kotlin/reflect/jvm/internal/impl/load/java/DescriptorsJvmAbiUtil"
            r0[r4] = r1
            java.lang.String r1 = "isClassCompanionObjectWithBackingFieldsInOuter"
            r0[r2] = r1
            java.lang.String r1 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x023b:
            boolean r0 = r1 instanceof Ke.C0794l
            if (r0 == 0) goto L_0x0244
            Ke.l r1 = (Ke.C0794l) r1
            java.lang.reflect.Field r3 = r1.d
            goto L_0x024d
        L_0x0244:
            boolean r0 = r1 instanceof Ke.C0795m
            if (r0 == 0) goto L_0x0249
            goto L_0x024d
        L_0x0249:
            boolean r0 = r1 instanceof Ke.C0797o
            if (r0 == 0) goto L_0x024e
        L_0x024d:
            return r3
        L_0x024e:
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.h0.invoke():java.lang.Object");
    }
}
