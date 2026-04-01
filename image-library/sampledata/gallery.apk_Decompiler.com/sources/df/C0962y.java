package df;

import Ae.b;

/* renamed from: df.y  reason: case insensitive filesystem */
public final class C0962y implements b {
    public final /* synthetic */ int d;
    public final C0932A e;

    public /* synthetic */ C0962y(C0932A a7, int i2) {
        this.d = i2;
        this.e = a7;
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x026d, code lost:
        if (Ne.u.a(r5) == false) goto L_0x027b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0221  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.d
            r2 = 2
            r3 = 1
            java.lang.String r4 = "name"
            df.A r0 = r0.e
            switch(r1) {
                case 0: goto L_0x02e8;
                case 1: goto L_0x00ca;
                case 2: goto L_0x0049;
                default: goto L_0x000d;
            }
        L_0x000d:
            r1 = r19
            qf.g r1 = (qf.C1240g) r1
            kotlin.jvm.internal.j.e(r1, r4)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            Gf.j r3 = r0.g
            java.lang.Object r3 = r3.invoke(r1)
            Qf.k.a(r2, r3)
            r0.n(r2, r1)
            Qe.l r1 = r0.q()
            int r3 = tf.C1301e.f5137a
            Qe.g r3 = Qe.C0817g.ANNOTATION_CLASS
            boolean r1 = tf.C1301e.n(r1, r3)
            if (r1 == 0) goto L_0x0038
            java.util.List r0 = ne.C1194l.k1(r2)
            goto L_0x0048
        L_0x0038:
            B0.a r0 = r0.b
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            hf.c r1 = r1.r
            java.util.ArrayList r0 = r1.e(r0, r2)
            java.util.List r0 = ne.C1194l.k1(r0)
        L_0x0048:
            return r0
        L_0x0049:
            r1 = r19
            qf.g r1 = (qf.C1240g) r1
            kotlin.jvm.internal.j.e(r1, r4)
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            Gf.e r5 = r0.f
            java.lang.Object r5 = r5.invoke(r1)
            java.util.Collection r5 = (java.util.Collection) r5
            r4.<init>(r5)
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r5.<init>()
            java.util.Iterator r6 = r4.iterator()
        L_0x0066:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x008b
            java.lang.Object r7 = r6.next()
            r8 = r7
            Te.K r8 = (Te.K) r8
            java.lang.String r8 = a.C0068a.m(r8, r2)
            java.lang.Object r9 = r5.get(r8)
            if (r9 != 0) goto L_0x0085
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r5.put(r8, r9)
        L_0x0085:
            java.util.List r9 = (java.util.List) r9
            r9.add(r7)
            goto L_0x0066
        L_0x008b:
            java.util.Collection r2 = r5.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x0093:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x00b4
            java.lang.Object r5 = r2.next()
            java.util.List r5 = (java.util.List) r5
            int r6 = r5.size()
            if (r6 == r3) goto L_0x0093
            java.util.Collection r5 = (java.util.Collection) r5
            df.l r6 = df.C0949l.f
            java.util.Collection r6 = tf.C1312p.o(r5, r6)
            r4.removeAll(r5)
            r4.addAll(r6)
            goto L_0x0093
        L_0x00b4:
            r0.m(r4, r1)
            B0.a r0 = r0.b
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            hf.c r1 = r1.r
            java.util.ArrayList r0 = r1.e(r0, r4)
            java.util.List r0 = ne.C1194l.k1(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        L_0x00ca:
            r1 = r19
            qf.g r1 = (qf.C1240g) r1
            kotlin.jvm.internal.j.e(r1, r4)
            df.A r4 = r0.f4231c
            if (r4 == 0) goto L_0x00df
            Gf.j r0 = r4.g
            java.lang.Object r0 = r0.invoke(r1)
            Qe.O r0 = (Qe.O) r0
            goto L_0x02e7
        L_0x00df:
            Gf.i r4 = r0.e
            java.lang.Object r4 = r4.invoke()
            df.c r4 = (df.C0940c) r4
            We.u r1 = r4.d(r1)
            r4 = 0
            if (r1 == 0) goto L_0x02e6
            java.lang.reflect.Field r5 = r1.f3894a
            boolean r6 = r5.isEnumConstant()
            if (r6 != 0) goto L_0x02e6
            kotlin.jvm.internal.u r6 = new kotlin.jvm.internal.u
            r6.<init>()
            java.lang.reflect.Member r7 = r1.b()
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            int r7 = r7.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isFinal(r7)
            r12 = r7 ^ 1
            B0.a r7 = r0.b
            cf.c r9 = He.F.O(r7, r1)
            java.lang.Object r8 = r7.d
            cf.a r8 = (cf.C0922a) r8
            Qe.l r10 = r0.q()
            r11 = r10
            Qe.A r10 = Qe.A.FINAL
            Qe.j0 r13 = r1.e()
            Qe.p r13 = Gd.a.i0(r13)
            r14 = r11
            r11 = r13
            qf.g r13 = r1.c()
            Ve.d r15 = r8.f4010j
            Ve.f r15 = r15.b(r1)
            java.lang.reflect.Member r16 = r1.b()
            java.lang.reflect.Field r16 = (java.lang.reflect.Field) r16
            int r16 = r16.getModifiers()
            boolean r16 = java.lang.reflect.Modifier.isFinal(r16)
            r17 = r2
            r2 = 0
            if (r16 == 0) goto L_0x015b
            java.lang.reflect.Member r16 = r1.b()
            java.lang.reflect.Field r16 = (java.lang.reflect.Field) r16
            int r16 = r16.getModifiers()
            boolean r16 = java.lang.reflect.Modifier.isStatic(r16)
            if (r16 == 0) goto L_0x015b
            r16 = r3
            r3 = r8
            r8 = r14
            r14 = r15
            r15 = r16
            goto L_0x0161
        L_0x015b:
            r16 = r3
            r3 = r8
            r8 = r14
            r14 = r15
            r15 = r2
        L_0x0161:
            bf.h r8 = bf.h.M0(r8, r9, r10, r11, r12, r13, r14, r15)
            r6.d = r8
            r8.I0(r4, r4, r4, r4)
            java.lang.Object r8 = r7.f34h
            A0.l r8 = (A0.l) r8
            java.lang.reflect.Type r5 = r5.getGenericType()
            java.lang.String r9 = "getGenericType(...)"
            kotlin.jvm.internal.j.d(r5, r9)
            boolean r9 = r5 instanceof java.lang.Class
            if (r9 == 0) goto L_0x018a
            r10 = r5
            java.lang.Class r10 = (java.lang.Class) r10
            boolean r11 = r10.isPrimitive()
            if (r11 == 0) goto L_0x018a
            We.z r5 = new We.z
            r5.<init>(r10)
            goto L_0x01b3
        L_0x018a:
            boolean r10 = r5 instanceof java.lang.reflect.GenericArrayType
            if (r10 != 0) goto L_0x01ad
            if (r9 == 0) goto L_0x019a
            r9 = r5
            java.lang.Class r9 = (java.lang.Class) r9
            boolean r9 = r9.isArray()
            if (r9 == 0) goto L_0x019a
            goto L_0x01ad
        L_0x019a:
            boolean r9 = r5 instanceof java.lang.reflect.WildcardType
            if (r9 == 0) goto L_0x01a7
            We.E r9 = new We.E
            java.lang.reflect.WildcardType r5 = (java.lang.reflect.WildcardType) r5
            r9.<init>(r5)
        L_0x01a5:
            r5 = r9
            goto L_0x01b3
        L_0x01a7:
            We.q r9 = new We.q
            r9.<init>(r5)
            goto L_0x01a5
        L_0x01ad:
            We.i r9 = new We.i
            r9.<init>(r5)
            goto L_0x01a5
        L_0x01b3:
            Hf.Y r9 = Hf.Y.COMMON
            r10 = 7
            ef.a r9 = a.C0068a.Y(r9, r2, r4, r10)
            Hf.x r11 = r8.p(r5, r9)
            boolean r5 = Ne.i.F(r11)
            if (r5 != 0) goto L_0x01ca
            boolean r5 = Ne.i.G(r11)
            if (r5 == 0) goto L_0x01e8
        L_0x01ca:
            java.lang.reflect.Member r5 = r1.b()
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            int r5 = r5.getModifiers()
            boolean r5 = java.lang.reflect.Modifier.isFinal(r5)
            if (r5 == 0) goto L_0x01e8
            java.lang.reflect.Member r5 = r1.b()
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            int r5 = r5.getModifiers()
            boolean r5 = java.lang.reflect.Modifier.isStatic(r5)
        L_0x01e8:
            java.lang.Object r5 = r6.d
            r10 = r5
            Te.H r10 = (Te.H) r10
            Te.u r13 = r0.p()
            r14 = 0
            ne.t r12 = ne.C1202t.d
            r15 = r12
            r10.L0(r11, r12, r13, r14, r15)
            Qe.l r5 = r0.q()
            boolean r8 = r5 instanceof Qe.C0816f
            if (r8 == 0) goto L_0x0203
            Qe.f r5 = (Qe.C0816f) r5
            goto L_0x0204
        L_0x0203:
            r5 = r4
        L_0x0204:
            if (r5 == 0) goto L_0x0214
            yf.e r8 = r3.f4013x
            java.lang.Object r9 = r6.d
            Te.H r9 = (Te.H) r9
            yf.a r8 = (yf.C1357a) r8
            Te.H r5 = r8.h(r5, r9, r7)
            r6.d = r5
        L_0x0214:
            java.lang.Object r5 = r6.d
            r7 = r5
            Qe.Y r7 = (Qe.Y) r7
            Te.H r5 = (Te.H) r5
            Hf.x r5 = r5.getType()
            if (r7 == 0) goto L_0x02e0
            if (r5 == 0) goto L_0x02da
            int r8 = tf.C1301e.f5137a
            boolean r8 = r7.G()
            if (r8 != 0) goto L_0x027b
            boolean r8 = Hf.C0754c.k(r5)
            if (r8 == 0) goto L_0x0232
            goto L_0x027b
        L_0x0232:
            boolean r8 = Hf.a0.b(r5)
            if (r8 == 0) goto L_0x0239
            goto L_0x026f
        L_0x0239:
            Ne.i r7 = xf.C1353d.e(r7)
            boolean r8 = Ne.i.F(r5)
            if (r8 != 0) goto L_0x026f
            If.l r8 = If.d.f3459a
            Hf.B r9 = r7.u()
            boolean r9 = r8.a(r9, r5)
            if (r9 != 0) goto L_0x026f
            java.lang.String r9 = "Number"
            Qe.f r9 = r7.j(r9)
            Hf.B r9 = r9.i()
            boolean r9 = r8.a(r9, r5)
            if (r9 != 0) goto L_0x026f
            Hf.B r7 = r7.e()
            boolean r7 = r8.a(r7, r5)
            if (r7 != 0) goto L_0x026f
            boolean r5 = Ne.u.a(r5)
            if (r5 == 0) goto L_0x027b
        L_0x026f:
            java.lang.Object r5 = r6.d
            Te.H r5 = (Te.H) r5
            df.x r7 = new df.x
            r7.<init>(r0, r1, r6, r2)
            r5.J0(r4, r7)
        L_0x027b:
            af.h r0 = r3.g
            java.lang.Object r1 = r6.d
            Qe.O r1 = (Qe.O) r1
            r0.getClass()
            if (r1 == 0) goto L_0x028b
            java.lang.Object r0 = r6.d
            Qe.O r0 = (Qe.O) r0
            goto L_0x02e7
        L_0x028b:
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 6
            switch(r1) {
                case 1: goto L_0x02ab;
                case 2: goto L_0x02a6;
                case 3: goto L_0x02a1;
                case 4: goto L_0x02a6;
                case 5: goto L_0x029c;
                case 6: goto L_0x02a6;
                case 7: goto L_0x0297;
                case 8: goto L_0x02a6;
                default: goto L_0x0292;
            }
        L_0x0292:
            java.lang.String r3 = "fqName"
            r0[r2] = r3
            goto L_0x02af
        L_0x0297:
            java.lang.String r3 = "javaClass"
            r0[r2] = r3
            goto L_0x02af
        L_0x029c:
            java.lang.String r3 = "field"
            r0[r2] = r3
            goto L_0x02af
        L_0x02a1:
            java.lang.String r3 = "element"
            r0[r2] = r3
            goto L_0x02af
        L_0x02a6:
            java.lang.String r3 = "descriptor"
            r0[r2] = r3
            goto L_0x02af
        L_0x02ab:
            java.lang.String r3 = "member"
            r0[r2] = r3
        L_0x02af:
            java.lang.String r2 = "kotlin/reflect/jvm/internal/impl/load/java/components/JavaResolverCache$1"
            r0[r16] = r2
            switch(r1) {
                case 1: goto L_0x02ca;
                case 2: goto L_0x02ca;
                case 3: goto L_0x02c5;
                case 4: goto L_0x02c5;
                case 5: goto L_0x02c0;
                case 6: goto L_0x02c0;
                case 7: goto L_0x02bb;
                case 8: goto L_0x02bb;
                default: goto L_0x02b6;
            }
        L_0x02b6:
            java.lang.String r1 = "getClassResolvedFromSource"
            r0[r17] = r1
            goto L_0x02ce
        L_0x02bb:
            java.lang.String r1 = "recordClass"
            r0[r17] = r1
            goto L_0x02ce
        L_0x02c0:
            java.lang.String r1 = "recordField"
            r0[r17] = r1
            goto L_0x02ce
        L_0x02c5:
            java.lang.String r1 = "recordConstructor"
            r0[r17] = r1
            goto L_0x02ce
        L_0x02ca:
            java.lang.String r1 = "recordMethod"
            r0[r17] = r1
        L_0x02ce:
            java.lang.String r1 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x02da:
            r0 = 67
            tf.C1301e.a(r0)
            throw r4
        L_0x02e0:
            r0 = 66
            tf.C1301e.a(r0)
            throw r4
        L_0x02e6:
            r0 = r4
        L_0x02e7:
            return r0
        L_0x02e8:
            r1 = r19
            qf.g r1 = (qf.C1240g) r1
            kotlin.jvm.internal.j.e(r1, r4)
            df.A r2 = r0.f4231c
            if (r2 == 0) goto L_0x02fc
            Gf.e r0 = r2.f
            java.lang.Object r0 = r0.invoke(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x033a
        L_0x02fc:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            Gf.i r3 = r0.e
            java.lang.Object r3 = r3.invoke()
            df.c r3 = (df.C0940c) r3
            java.util.Collection r3 = r3.e(r1)
            java.util.Iterator r3 = r3.iterator()
        L_0x0311:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0336
            java.lang.Object r4 = r3.next()
            We.x r4 = (We.x) r4
            bf.g r4 = r0.t(r4)
            boolean r5 = r0.r(r4)
            if (r5 == 0) goto L_0x0311
            B0.a r5 = r0.b
            java.lang.Object r5 = r5.d
            cf.a r5 = (cf.C0922a) r5
            af.h r5 = r5.g
            r5.getClass()
            r2.add(r4)
            goto L_0x0311
        L_0x0336:
            r0.j(r2, r1)
            r0 = r2
        L_0x033a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0962y.invoke(java.lang.Object):java.lang.Object");
    }
}
