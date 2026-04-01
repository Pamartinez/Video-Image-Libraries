package Ke;

import Ae.a;

public final class G implements a {
    public final /* synthetic */ int d;
    public final H e;

    public /* synthetic */ G(H h5, int i2) {
        this.d = i2;
        this.e = h5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r15 = this;
            int r0 = r15.d
            r1 = 4
            java.lang.String r2 = "desc"
            java.lang.String r3 = "getValueParameters(...)"
            java.lang.String r4 = "getContainingDeclaration(...)"
            r5 = 10
            Ke.H r15 = r15.e
            r6 = 1
            r7 = 0
            switch(r0) {
                case 0: goto L_0x0240;
                default: goto L_0x0012;
            }
        L_0x0012:
            qf.b r0 = Ke.C0.f3487a
            Qe.v r0 = r15.j()
            Ke.F r8 = r15.f3492j
            L2.a r0 = Ke.C0.c(r0)
            boolean r9 = r0 instanceof Ke.C0793k
            r10 = 0
            if (r9 == 0) goto L_0x0138
            Qe.v r2 = r15.j()
            Qe.l r5 = r2.g()
            kotlin.jvm.internal.j.d(r5, r4)
            boolean r5 = tf.C1305i.d(r5)
            if (r5 == 0) goto L_0x0060
            boolean r5 = r2 instanceof Qe.C0821k
            if (r5 == 0) goto L_0x0060
            Qe.k r2 = (Qe.C0821k) r2
            boolean r2 = r2.V()
            if (r2 != 0) goto L_0x0041
            goto L_0x0060
        L_0x0041:
            Ke.v0 r0 = new Ke.v0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            Qe.v r15 = r15.j()
            Qe.l r15 = r15.g()
            r1.append(r15)
            java.lang.String r15 = " cannot have default arguments"
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15, r7)
            throw r0
        L_0x0060:
            Qe.v r2 = r15.j()
            java.util.List r5 = r2.B()
            kotlin.jvm.internal.j.d(r5, r3)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r9 = r5 instanceof java.util.Collection
            if (r9 == 0) goto L_0x007b
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x007b
            goto L_0x0093
        L_0x007b:
            java.util.Iterator r5 = r5.iterator()
        L_0x007f:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x0093
            java.lang.Object r9 = r5.next()
            Te.Q r9 = (Te.Q) r9
            boolean r9 = r9.F0()
            if (r9 == 0) goto L_0x007f
            goto L_0x0101
        L_0x0093:
            Qe.l r5 = r2.g()
            kotlin.jvm.internal.j.d(r5, r4)
            boolean r4 = tf.C1305i.f(r5)
            if (r4 == 0) goto L_0x0101
            Le.g r4 = r15.g()
            java.lang.reflect.Member r4 = r4.b()
            kotlin.jvm.internal.j.b(r4)
            int r4 = r4.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x0101
            Sf.h r2 = xf.C1353d.l(r2)
            Sf.f r4 = new Sf.f
            r4.<init>((Sf.h) r2)
        L_0x00be:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x00f9
            java.lang.Object r2 = r4.next()
            r5 = r2
            Qe.d r5 = (Qe.C0814d) r5
            java.util.List r5 = r5.B()
            kotlin.jvm.internal.j.d(r5, r3)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r9 = r5 instanceof java.util.Collection
            if (r9 == 0) goto L_0x00e2
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x00e2
            goto L_0x00be
        L_0x00e2:
            java.util.Iterator r5 = r5.iterator()
        L_0x00e6:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x00be
            java.lang.Object r9 = r5.next()
            Te.Q r9 = (Te.Q) r9
            boolean r9 = r9.F0()
            if (r9 == 0) goto L_0x00e6
            goto L_0x00fa
        L_0x00f9:
            r2 = r10
        L_0x00fa:
            boolean r3 = r2 instanceof Qe.C0831v
            if (r3 == 0) goto L_0x0101
            Qe.v r2 = (Qe.C0831v) r2
            goto L_0x0102
        L_0x0101:
            r2 = r10
        L_0x0102:
            if (r2 == 0) goto L_0x0116
            L2.a r0 = Ke.C0.c(r2)
            Ke.k r0 = (Ke.C0793k) r0
            pf.e r0 = r0.d
            java.lang.String r2 = r0.d
            java.lang.String r0 = r0.e
            java.lang.reflect.Method r0 = r8.g(r2, r0, r6)
            goto L_0x01de
        L_0x0116:
            Ke.k r0 = (Ke.C0793k) r0
            pf.e r0 = r0.d
            java.lang.String r2 = r0.d
            java.lang.String r0 = r0.e
            Le.g r3 = r15.g()
            java.lang.reflect.Member r3 = r3.b()
            kotlin.jvm.internal.j.b(r3)
            int r3 = r3.getModifiers()
            boolean r3 = java.lang.reflect.Modifier.isStatic(r3)
            r3 = r3 ^ r6
            java.lang.reflect.Method r0 = r8.g(r2, r0, r3)
            goto L_0x01de
        L_0x0138:
            boolean r3 = r0 instanceof Ke.C0792j
            if (r3 == 0) goto L_0x01a2
            boolean r3 = r15.p()
            if (r3 == 0) goto L_0x017d
            java.lang.Class r0 = r8.b()
            java.util.List r15 = r15.getParameters()
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r15, r5)
            r1.<init>(r2)
            java.util.Iterator r15 = r15.iterator()
        L_0x0159:
            boolean r2 = r15.hasNext()
            if (r2 == 0) goto L_0x0172
            java.lang.Object r2 = r15.next()
            He.m r2 = (He.m) r2
            Ke.X r2 = (Ke.X) r2
            java.lang.String r2 = r2.getName()
            kotlin.jvm.internal.j.b(r2)
            r1.add(r2)
            goto L_0x0159
        L_0x0172:
            Le.a r15 = Le.C0808a.CALL_BY_NAME
            Le.b r2 = Le.C0809b.KOTLIN
            Le.c r10 = new Le.c
            r10.<init>(r0, r1, r15, r2)
            goto L_0x023f
        L_0x017d:
            Ke.j r0 = (Ke.C0792j) r0
            pf.e r0 = r0.d
            java.lang.String r0 = r0.e
            r8.getClass()
            kotlin.jvm.internal.j.e(r0, r2)
            java.lang.Class r2 = r8.b()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            D0.e r0 = r8.t(r0, r7)
            java.lang.Object r0 = r0.e
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            Ke.F.f(r3, r0, r6)
            java.lang.reflect.Constructor r0 = Ke.F.v(r2, r3)
            goto L_0x01de
        L_0x01a2:
            boolean r2 = r0 instanceof Ke.C0789g
            if (r2 == 0) goto L_0x01dd
            Ke.g r0 = (Ke.C0789g) r0
            java.util.List r14 = r0.d
            java.lang.Class r10 = r8.b()
            r15 = r14
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r11 = new java.util.ArrayList
            int r0 = ne.C1196n.w0(r15, r5)
            r11.<init>(r0)
            java.util.Iterator r15 = r15.iterator()
        L_0x01be:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x01d2
            java.lang.Object r0 = r15.next()
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            java.lang.String r0 = r0.getName()
            r11.add(r0)
            goto L_0x01be
        L_0x01d2:
            Le.a r12 = Le.C0808a.CALL_BY_NAME
            Le.b r13 = Le.C0809b.JAVA
            Le.c r9 = new Le.c
            r9.<init>(r10, r11, r12, r13, r14)
            r10 = r9
            goto L_0x023f
        L_0x01dd:
            r0 = r10
        L_0x01de:
            boolean r2 = r0 instanceof java.lang.reflect.Constructor
            if (r2 == 0) goto L_0x01ed
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            Qe.v r1 = r15.j()
            Le.w r0 = r15.r(r0, r1, r6)
            goto L_0x0235
        L_0x01ed:
            boolean r2 = r0 instanceof java.lang.reflect.Method
            if (r2 == 0) goto L_0x0234
            Qe.v r2 = r15.j()
            Bf.a r2 = (Bf.a) r2
            Re.h r2 = r2.getAnnotations()
            qf.c r3 = Ke.E0.f3489a
            Re.b r2 = r2.m(r3)
            if (r2 == 0) goto L_0x022d
            Qe.v r2 = r15.j()
            Qe.l r2 = r2.g()
            java.lang.String r3 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r2, r3)
            Qe.f r2 = (Qe.C0816f) r2
            boolean r2 = r2.T()
            if (r2 != 0) goto L_0x022d
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            boolean r2 = r15.q()
            if (r2 == 0) goto L_0x0226
            Le.s r2 = new Le.s
            r2.<init>((java.lang.reflect.Method) r0, (boolean) r7, (int) r1)
            goto L_0x022b
        L_0x0226:
            Le.v r2 = new Le.v
            r2.<init>(r0, r6, r1, r6)
        L_0x022b:
            r0 = r2
            goto L_0x0235
        L_0x022d:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            Le.q r0 = r15.s(r0)
            goto L_0x0235
        L_0x0234:
            r0 = r10
        L_0x0235:
            if (r0 == 0) goto L_0x023f
            Qe.v r15 = r15.j()
            Le.g r10 = a.C0068a.s(r0, r15, r6)
        L_0x023f:
            return r10
        L_0x0240:
            qf.b r0 = Ke.C0.f3487a
            Qe.v r0 = r15.j()
            Ke.F r8 = r15.f3492j
            L2.a r0 = Ke.C0.c(r0)
            boolean r9 = r0 instanceof Ke.C0792j
            if (r9 == 0) goto L_0x02ae
            boolean r3 = r15.p()
            if (r3 == 0) goto L_0x0291
            java.lang.Class r0 = r8.b()
            java.util.List r15 = r15.getParameters()
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r15, r5)
            r1.<init>(r2)
            java.util.Iterator r15 = r15.iterator()
        L_0x026d:
            boolean r2 = r15.hasNext()
            if (r2 == 0) goto L_0x0286
            java.lang.Object r2 = r15.next()
            He.m r2 = (He.m) r2
            Ke.X r2 = (Ke.X) r2
            java.lang.String r2 = r2.getName()
            kotlin.jvm.internal.j.b(r2)
            r1.add(r2)
            goto L_0x026d
        L_0x0286:
            Le.a r15 = Le.C0808a.POSITIONAL_CALL
            Le.b r2 = Le.C0809b.KOTLIN
            Le.c r3 = new Le.c
            r3.<init>(r0, r1, r15, r2)
            goto L_0x03df
        L_0x0291:
            Ke.j r0 = (Ke.C0792j) r0
            pf.e r0 = r0.d
            java.lang.String r0 = r0.e
            r8.getClass()
            kotlin.jvm.internal.j.e(r0, r2)
            java.lang.Class r2 = r8.b()
            D0.e r0 = r8.t(r0, r7)
            java.lang.Object r0 = r0.e
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            java.lang.reflect.Constructor r0 = Ke.F.v(r2, r0)
            goto L_0x0312
        L_0x02ae:
            boolean r2 = r0 instanceof Ke.C0793k
            if (r2 == 0) goto L_0x02f9
            Qe.v r2 = r15.j()
            Qe.l r5 = r2.g()
            kotlin.jvm.internal.j.d(r5, r4)
            boolean r4 = tf.C1305i.d(r5)
            if (r4 == 0) goto L_0x02ec
            boolean r4 = r2 instanceof Qe.C0821k
            if (r4 == 0) goto L_0x02ec
            Qe.k r2 = (Qe.C0821k) r2
            boolean r2 = r2.V()
            if (r2 == 0) goto L_0x02ec
            Le.B r1 = new Le.B
            Qe.v r2 = r15.j()
            Ke.k r0 = (Ke.C0793k) r0
            pf.e r0 = r0.d
            java.lang.String r0 = r0.e
            Qe.v r15 = r15.j()
            java.util.List r15 = r15.B()
            kotlin.jvm.internal.j.d(r15, r3)
            r1.<init>(r2, r8, r0, r15)
            r3 = r1
            goto L_0x03df
        L_0x02ec:
            Ke.k r0 = (Ke.C0793k) r0
            pf.e r0 = r0.d
            java.lang.String r2 = r0.d
            java.lang.String r0 = r0.e
            java.lang.reflect.Method r0 = r8.h(r2, r0)
            goto L_0x0312
        L_0x02f9:
            boolean r2 = r0 instanceof Ke.C0791i
            java.lang.String r3 = "null cannot be cast to non-null type java.lang.reflect.Member"
            if (r2 == 0) goto L_0x0307
            Ke.i r0 = (Ke.C0791i) r0
            java.lang.reflect.Method r0 = r0.d
            kotlin.jvm.internal.j.c(r0, r3)
            goto L_0x0312
        L_0x0307:
            boolean r2 = r0 instanceof Ke.C0790h
            if (r2 == 0) goto L_0x03a5
            Ke.h r0 = (Ke.C0790h) r0
            java.lang.reflect.Constructor r0 = r0.d
            kotlin.jvm.internal.j.c(r0, r3)
        L_0x0312:
            boolean r2 = r0 instanceof java.lang.reflect.Constructor
            if (r2 == 0) goto L_0x0321
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            Qe.v r1 = r15.j()
            Le.w r0 = r15.r(r0, r1, r7)
            goto L_0x0377
        L_0x0321:
            boolean r2 = r0 instanceof java.lang.reflect.Method
            if (r2 == 0) goto L_0x0380
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            int r2 = r0.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 != 0) goto L_0x034e
            boolean r1 = r15.q()
            if (r1 == 0) goto L_0x0347
            Le.r r1 = new Le.r
            java.lang.Object r2 = r15.l
            Qe.v r3 = r15.j()
            java.lang.Object r2 = a.C0068a.k(r2, r3)
            r1.<init>(r0, r2)
            goto L_0x034c
        L_0x0347:
            Le.v r1 = new Le.v
            r1.<init>(r0)
        L_0x034c:
            r0 = r1
            goto L_0x0377
        L_0x034e:
            Qe.v r2 = r15.j()
            Bf.a r2 = (Bf.a) r2
            Re.h r2 = r2.getAnnotations()
            qf.c r3 = Ke.E0.f3489a
            Re.b r2 = r2.m(r3)
            if (r2 == 0) goto L_0x0373
            boolean r2 = r15.q()
            if (r2 == 0) goto L_0x036c
            Le.s r2 = new Le.s
            r2.<init>((java.lang.reflect.Method) r0, (boolean) r7, (int) r1)
            goto L_0x0371
        L_0x036c:
            Le.v r2 = new Le.v
            r2.<init>(r0, r6, r1, r6)
        L_0x0371:
            r0 = r2
            goto L_0x0377
        L_0x0373:
            Le.q r0 = r15.s(r0)
        L_0x0377:
            Qe.v r15 = r15.j()
            Le.g r3 = a.C0068a.s(r0, r15, r7)
            goto L_0x03df
        L_0x0380:
            Ke.v0 r1 = new Ke.v0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Could not compute caller for function: "
            r2.<init>(r3)
            Qe.v r15 = r15.j()
            r2.append(r15)
            java.lang.String r15 = " (member = "
            r2.append(r15)
            r2.append(r0)
            r15 = 41
            r2.append(r15)
            java.lang.String r15 = r2.toString()
            r1.<init>(r15, r7)
            throw r1
        L_0x03a5:
            boolean r15 = r0 instanceof Ke.C0789g
            if (r15 == 0) goto L_0x03e0
            Ke.g r0 = (Ke.C0789g) r0
            java.util.List r14 = r0.d
            java.lang.Class r10 = r8.b()
            r15 = r14
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r11 = new java.util.ArrayList
            int r0 = ne.C1196n.w0(r15, r5)
            r11.<init>(r0)
            java.util.Iterator r15 = r15.iterator()
        L_0x03c1:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x03d5
            java.lang.Object r0 = r15.next()
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            java.lang.String r0 = r0.getName()
            r11.add(r0)
            goto L_0x03c1
        L_0x03d5:
            Le.a r12 = Le.C0808a.POSITIONAL_CALL
            Le.b r13 = Le.C0809b.JAVA
            Le.c r9 = new Le.c
            r9.<init>(r10, r11, r12, r13, r14)
            r3 = r9
        L_0x03df:
            return r3
        L_0x03e0:
            Dd.a r15 = new Dd.a
            r15.<init>()
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.G.invoke():java.lang.Object");
    }
}
