package Qe;

import kotlin.jvm.internal.j;

/* renamed from: Qe.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0826p {

    /* renamed from: a  reason: collision with root package name */
    public final j0 f3674a;
    public final /* synthetic */ int b;

    public C0826p(j0 j0Var, int i2) {
        this.b = i2;
        j.e(j0Var, "delegate");
        this.f3674a = j0Var;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02c0, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0291 A[LOOP:1: B:118:0x0291->B:129:0x02c2, LOOP_START, PHI: r8 
      PHI: (r8v2 Qe.l) = (r8v0 Qe.l), (r8v3 Qe.l) binds: [B:117:0x028e, B:129:0x02c2] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(Bf.e r6, Qe.C0825o r7, Qe.C0822l r8) {
        /*
            r5 = this;
            int r0 = r5.b
            switch(r0) {
                case 0: goto L_0x025b;
                case 1: goto L_0x0209;
                case 2: goto L_0x0161;
                case 3: goto L_0x012a;
                case 4: goto L_0x0108;
                case 5: goto L_0x00e0;
                case 6: goto L_0x00b8;
                case 7: goto L_0x0096;
                case 8: goto L_0x0074;
                case 9: goto L_0x004f;
                case 10: goto L_0x002a;
                default: goto L_0x0005;
            }
        L_0x0005:
            if (r8 == 0) goto L_0x000c
            boolean r5 = Ze.o.b(r6, r7, r8)
            return r5
        L_0x000c:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$3"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x002a:
            if (r8 == 0) goto L_0x0031
            boolean r5 = Ze.o.b(r6, r7, r8)
            return r5
        L_0x0031:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$2"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x004f:
            if (r8 == 0) goto L_0x0056
            boolean r5 = Ze.o.c(r7, r8)
            return r5
        L_0x0056:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 2
            r8 = 1
            java.lang.String r0 = "from"
            r5[r6] = r0
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$1"
            r5[r8] = r6
            java.lang.String r6 = "isVisible"
            r5[r7] = r6
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x0074:
            if (r8 == 0) goto L_0x0078
            r5 = 0
            return r5
        L_0x0078:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$9"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x0096:
            if (r8 == 0) goto L_0x009a
            r5 = 0
            return r5
        L_0x009a:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$8"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x00b8:
            if (r8 != 0) goto L_0x00d8
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$7"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x00d8:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Visibility is unknown yet"
            r5.<init>(r6)
            throw r5
        L_0x00e0:
            if (r8 != 0) goto L_0x0100
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$6"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x0100:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "This method shouldn't be invoked for LOCAL visibility"
            r5.<init>(r6)
            throw r5
        L_0x0108:
            r5 = 1
            if (r8 == 0) goto L_0x010c
            return r5
        L_0x010c:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$5"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x012a:
            r5 = 1
            if (r8 == 0) goto L_0x0143
            Qe.C r6 = tf.C1301e.d(r7)
            Qe.C r7 = tf.C1301e.d(r8)
            boolean r6 = r7.f0(r6)
            if (r6 != 0) goto L_0x013d
            r5 = 0
            goto L_0x0142
        L_0x013d:
            Nf.n r6 = Qe.C0827q.n
            r6.getClass()
        L_0x0142:
            return r5
        L_0x0143:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$4"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x0161:
            r0 = 1
            if (r8 == 0) goto L_0x01eb
            java.lang.Class<Qe.f> r1 = Qe.C0816f.class
            Qe.l r2 = tf.C1301e.i(r7, r1, r0)
            Qe.f r2 = (Qe.C0816f) r2
            r3 = 0
            Qe.l r8 = tf.C1301e.i(r8, r1, r3)
            Qe.f r8 = (Qe.C0816f) r8
            if (r8 != 0) goto L_0x0176
            goto L_0x01aa
        L_0x0176:
            if (r2 == 0) goto L_0x0195
            boolean r4 = tf.C1301e.l(r2)
            if (r4 == 0) goto L_0x0195
            Qe.l r2 = tf.C1301e.i(r2, r1, r0)
            Qe.f r2 = (Qe.C0816f) r2
            if (r2 == 0) goto L_0x0195
            Hf.B r4 = r8.i()
            Qe.f r2 = r2.a()
            boolean r2 = tf.C1301e.r(r4, r2)
            if (r2 == 0) goto L_0x0195
            goto L_0x01ea
        L_0x0195:
            boolean r2 = r7 instanceof Qe.C0814d
            if (r2 == 0) goto L_0x01a1
            r2 = r7
            Qe.d r2 = (Qe.C0814d) r2
            Qe.d r2 = tf.C1301e.t(r2)
            goto L_0x01a2
        L_0x01a1:
            r2 = r7
        L_0x01a2:
            Qe.l r1 = tf.C1301e.i(r2, r1, r0)
            Qe.f r1 = (Qe.C0816f) r1
            if (r1 != 0) goto L_0x01ac
        L_0x01aa:
            r0 = r3
            goto L_0x01ea
        L_0x01ac:
            Hf.B r3 = r8.i()
            Qe.f r1 = r1.a()
            boolean r1 = tf.C1301e.r(r3, r1)
            if (r1 == 0) goto L_0x01e2
            Qe.S r1 = Qe.C0827q.m
            if (r6 != r1) goto L_0x01bf
            goto L_0x01e2
        L_0x01bf:
            boolean r1 = r2 instanceof Qe.C0814d
            if (r1 != 0) goto L_0x01c4
            goto L_0x01ea
        L_0x01c4:
            boolean r1 = r2 instanceof Qe.C0821k
            if (r1 == 0) goto L_0x01c9
            goto L_0x01ea
        L_0x01c9:
            Qe.S r1 = Qe.C0827q.l
            if (r6 != r1) goto L_0x01ce
            goto L_0x01ea
        L_0x01ce:
            Qe.S r1 = Qe.C0827q.k
            if (r6 == r1) goto L_0x01e2
            if (r6 != 0) goto L_0x01d5
            goto L_0x01e2
        L_0x01d5:
            Hf.x r1 = r6.getType()
            boolean r2 = tf.C1301e.r(r1, r8)
            if (r2 != 0) goto L_0x01ea
            r1.x0()
        L_0x01e2:
            Qe.l r8 = r8.g()
            boolean r0 = r5.a(r6, r7, r8)
        L_0x01ea:
            return r0
        L_0x01eb:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 2
            r8 = 1
            java.lang.String r0 = "from"
            r5[r6] = r0
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$3"
            r5[r8] = r6
            java.lang.String r6 = "isVisible"
            r5[r7] = r6
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x0209:
            r5 = 1
            if (r8 == 0) goto L_0x023d
            Qe.p r0 = Qe.C0827q.f3675a
            boolean r8 = r0.a(r6, r7, r8)
            if (r8 == 0) goto L_0x023b
            Qe.S r8 = Qe.C0827q.l
            if (r6 != r8) goto L_0x0219
            goto L_0x023c
        L_0x0219:
            Qe.S r8 = Qe.C0827q.k
            if (r6 != r8) goto L_0x021e
            goto L_0x023b
        L_0x021e:
            java.lang.Class<Qe.f> r8 = Qe.C0816f.class
            Qe.l r5 = tf.C1301e.i(r7, r8, r5)
            if (r5 == 0) goto L_0x023b
            boolean r7 = r6 instanceof Bf.d
            if (r7 == 0) goto L_0x023b
            Bf.d r6 = (Bf.d) r6
            Qe.f r6 = r6.d
            Qe.f r6 = r6.a()
            Qe.l r5 = r5.a()
            boolean r5 = r6.equals(r5)
            goto L_0x023c
        L_0x023b:
            r5 = 0
        L_0x023c:
            return r5
        L_0x023d:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 1
            java.lang.String r8 = "from"
            r5[r6] = r8
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$2"
            r5[r7] = r6
            r6 = 2
            java.lang.String r7 = "isVisible"
            r5[r6] = r7
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        L_0x025b:
            if (r8 == 0) goto L_0x02c9
            boolean r5 = tf.C1301e.s(r7)
            if (r5 == 0) goto L_0x0270
            Qe.S r5 = tf.C1301e.f(r8)
            Qe.S r6 = Qe.S.e
            if (r5 == r6) goto L_0x0270
            boolean r5 = Qe.C0827q.d(r7, r8)
            goto L_0x02c8
        L_0x0270:
            boolean r5 = r7 instanceof Qe.C0821k
            if (r5 == 0) goto L_0x027a
            r5 = r7
            Qe.k r5 = (Qe.C0821k) r5
            r5.g()
        L_0x027a:
            if (r7 == 0) goto L_0x028e
            Qe.l r7 = r7.g()
            boolean r5 = r7 instanceof Qe.C0816f
            if (r5 == 0) goto L_0x028a
            boolean r5 = tf.C1301e.l(r7)
            if (r5 == 0) goto L_0x028e
        L_0x028a:
            boolean r5 = r7 instanceof Qe.H
            if (r5 == 0) goto L_0x027a
        L_0x028e:
            if (r7 != 0) goto L_0x0291
            goto L_0x02c7
        L_0x0291:
            if (r8 == 0) goto L_0x02c7
            if (r7 != r8) goto L_0x0296
            goto L_0x02c0
        L_0x0296:
            boolean r5 = r8 instanceof Qe.H
            if (r5 == 0) goto L_0x02c2
            boolean r5 = r7 instanceof Qe.H
            if (r5 == 0) goto L_0x02c7
            r5 = r7
            Qe.H r5 = (Qe.H) r5
            Te.B r5 = (Te.B) r5
            qf.c r5 = r5.f3743i
            r6 = r8
            Qe.H r6 = (Qe.H) r6
            Te.B r6 = (Te.B) r6
            qf.c r6 = r6.f3743i
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x02c7
            Qe.C r5 = tf.C1301e.d(r8)
            Qe.C r6 = tf.C1301e.d(r7)
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x02c7
        L_0x02c0:
            r5 = 1
            goto L_0x02c8
        L_0x02c2:
            Qe.l r8 = r8.g()
            goto L_0x0291
        L_0x02c7:
            r5 = 0
        L_0x02c8:
            return r5
        L_0x02c9:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r7 = 2
            r8 = 1
            java.lang.String r0 = "from"
            r5[r6] = r0
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$1"
            r5[r8] = r6
            java.lang.String r6 = "isVisible"
            r5[r7] = r6
            java.lang.String r6 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: Qe.C0826p.a(Bf.e, Qe.o, Qe.l):boolean");
    }

    public final String toString() {
        return this.f3674a.b();
    }
}
