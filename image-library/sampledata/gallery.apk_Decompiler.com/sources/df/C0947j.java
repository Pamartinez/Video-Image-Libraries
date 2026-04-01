package df;

import Ae.a;

/* renamed from: df.j  reason: case insensitive filesystem */
public final class C0947j implements a {
    public final /* synthetic */ int d = 1;
    public final B0.a e;
    public final C0951n f;

    public C0947j(B0.a aVar, C0951n nVar) {
        this.e = aVar;
        this.f = nVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r13v14, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v16, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r30 = this;
            r0 = r30
            int r1 = r0.d
            switch(r1) {
                case 0: goto L_0x001e;
                default: goto L_0x0007;
            }
        L_0x0007:
            B0.a r1 = r0.e
            java.lang.Object r2 = r1.d
            cf.a r2 = (cf.C0922a) r2
            yf.e r2 = r2.f4013x
            df.n r0 = r0.f
            Qe.f r0 = r0.n
            yf.a r2 = (yf.C1357a) r2
            java.util.ArrayList r0 = r2.f(r0, r1)
            java.util.Set r0 = ne.C1194l.p1(r0)
            return r0
        L_0x001e:
            df.n r1 = r0.f
            We.o r2 = r1.f4253o
            B0.a r8 = r1.b
            Qe.f r9 = r1.n
            java.lang.Class r3 = r2.f3891a
            java.lang.reflect.Constructor[] r3 = r3.getDeclaredConstructors()
            java.lang.String r4 = "getDeclaredConstructors(...)"
            kotlin.jvm.internal.j.d(r3, r4)
            Sf.k r3 = ne.C1192j.b0(r3)
            We.j r4 = We.j.d
            Sf.g r5 = new Sf.g
            r10 = 0
            r5.<init>(r3, r10, r4)
            We.k r3 = We.k.d
            Sf.u r3 = Sf.n.t0(r5, r3)
            java.util.List r3 = Sf.n.v0(r3)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.ArrayList r4 = new java.util.ArrayList
            int r5 = r3.size()
            r4.<init>(r5)
            java.util.Iterator r3 = r3.iterator()
        L_0x0056:
            boolean r5 = r3.hasNext()
            r11 = 1
            if (r5 == 0) goto L_0x0156
            java.lang.Object r5 = r3.next()
            We.r r5 = (We.r) r5
            cf.c r6 = He.F.O(r8, r5)
            java.lang.Object r7 = r8.d
            cf.a r7 = (cf.C0922a) r7
            Ve.d r12 = r7.f4010j
            Ve.f r12 = r12.b(r5)
            bf.b r6 = bf.C0917b.V0(r9, r6, r10, r12)
            java.util.List r12 = r9.j()
            int r12 = r12.size()
            java.lang.Object r13 = r8.f
            Ed.b r14 = new Ed.b
            r14.<init>(r8, r6, r5, r12)
            B0.a r12 = new B0.a
            r12.<init>(r7, r14, r13)
            java.lang.reflect.Constructor r7 = r5.f3893a
            java.lang.reflect.Type[] r13 = r7.getGenericParameterTypes()
            kotlin.jvm.internal.j.b(r13)
            int r14 = r13.length
            if (r14 != 0) goto L_0x0098
            ne.t r7 = ne.C1202t.d
            goto L_0x00d2
        L_0x0098:
            java.lang.Class r14 = r7.getDeclaringClass()
            java.lang.Class r15 = r14.getDeclaringClass()
            if (r15 == 0) goto L_0x00b4
            int r14 = r14.getModifiers()
            boolean r14 = java.lang.reflect.Modifier.isStatic(r14)
            if (r14 != 0) goto L_0x00b4
            int r14 = r13.length
            java.lang.Object[] r11 = ne.C1192j.i0(r13, r11, r14)
            r13 = r11
            java.lang.reflect.Type[] r13 = (java.lang.reflect.Type[]) r13
        L_0x00b4:
            java.lang.annotation.Annotation[][] r11 = r7.getParameterAnnotations()
            int r14 = r11.length
            int r15 = r13.length
            if (r14 < r15) goto L_0x0142
            int r14 = r11.length
            int r15 = r13.length
            if (r14 <= r15) goto L_0x00ca
            int r14 = r11.length
            int r15 = r13.length
            int r14 = r14 - r15
            int r15 = r11.length
            java.lang.Object[] r11 = ne.C1192j.i0(r11, r14, r15)
            java.lang.annotation.Annotation[][] r11 = (java.lang.annotation.Annotation[][]) r11
        L_0x00ca:
            boolean r7 = r7.isVarArgs()
            java.util.ArrayList r7 = r5.d(r13, r11, r7)
        L_0x00d2:
            Gf.l r7 = df.C0932A.u(r12, r6, r7)
            java.util.List r11 = r9.j()
            java.lang.String r13 = "getDeclaredTypeParameters(...)"
            kotlin.jvm.internal.j.d(r11, r13)
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.ArrayList r13 = r5.getTypeParameters()
            java.util.ArrayList r14 = new java.util.ArrayList
            r15 = 10
            int r15 = ne.C1196n.w0(r13, r15)
            r14.<init>(r15)
            java.util.Iterator r13 = r13.iterator()
        L_0x00f4:
            boolean r15 = r13.hasNext()
            if (r15 == 0) goto L_0x0110
            java.lang.Object r15 = r13.next()
            We.C r15 = (We.C) r15
            java.lang.Object r10 = r12.e
            cf.e r10 = (cf.e) r10
            Qe.V r10 = r10.a(r15)
            kotlin.jvm.internal.j.b(r10)
            r14.add(r10)
            r10 = 0
            goto L_0x00f4
        L_0x0110:
            java.util.ArrayList r10 = ne.C1194l.X0(r14, r11)
            java.lang.Object r11 = r7.f3415c
            java.util.List r11 = (java.util.List) r11
            Qe.j0 r5 = r5.e()
            Qe.p r5 = Gd.a.i0(r5)
            r6.T0(r11, r5, r10)
            r5 = 0
            r6.M0(r5)
            boolean r5 = r7.b
            r6.N0(r5)
            Hf.B r5 = r9.i()
            r6.O0(r5)
            java.lang.Object r5 = r12.d
            cf.a r5 = (cf.C0922a) r5
            af.h r5 = r5.g
            r5.getClass()
            r4.add(r6)
            r10 = 0
            goto L_0x0056
        L_0x0142:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Illegal generic signature: "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0156:
            boolean r3 = r2.g()
            java.lang.Class r5 = r2.f3891a
            java.lang.String r10 = "PROTECTED_AND_PACKAGE"
            java.lang.String r12 = "getVisibility(...)"
            r6 = 6
            B0.a r0 = r0.e
            Re.f r7 = Re.g.f3692a
            r13 = 0
            if (r3 == 0) goto L_0x0238
            java.lang.Object r3 = r8.d
            cf.a r3 = (cf.C0922a) r3
            Ve.d r3 = r3.f4010j
            Ve.f r3 = r3.b(r2)
            bf.b r17 = bf.C0917b.V0(r9, r7, r11, r3)
            java.util.ArrayList r3 = r2.f()
            java.util.ArrayList r14 = new java.util.ArrayList
            int r15 = r3.size()
            r14.<init>(r15)
            Hf.Y r15 = Hf.Y.COMMON
            r11 = 0
            ef.a r15 = a.C0068a.Y(r15, r11, r13, r6)
            java.util.Iterator r3 = r3.iterator()
            r19 = 0
        L_0x0190:
            boolean r11 = r3.hasNext()
            if (r11 == 0) goto L_0x01db
            int r11 = r19 + 1
            java.lang.Object r16 = r3.next()
            r6 = r16
            We.A r6 = (We.A) r6
            java.lang.Object r13 = r8.f34h
            A0.l r13 = (A0.l) r13
            r28 = r1
            gf.d r1 = r6.f()
            Hf.x r22 = r13.p(r1, r15)
            Te.Q r16 = new Te.Q
            qf.g r21 = r6.c()
            java.lang.Object r1 = r8.d
            cf.a r1 = (cf.C0922a) r1
            Ve.d r1 = r1.f4010j
            Ve.f r27 = r1.b(r6)
            r18 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r20 = r7
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r6 = r16
            r1 = r17
            r14.add(r6)
            r19 = r11
            r1 = r28
            r6 = 6
            r13 = 0
            goto L_0x0190
        L_0x01db:
            r28 = r1
            r1 = r17
            r11 = 0
            r1.N0(r11)
            Qe.p r3 = r9.getVisibility()
            kotlin.jvm.internal.j.d(r3, r12)
            Qe.p r6 = Ze.o.b
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x01f7
            Qe.p r3 = Ze.o.f3952c
            kotlin.jvm.internal.j.d(r3, r10)
        L_0x01f7:
            r1.S0(r14, r3)
            r1.M0(r11)
            Hf.B r3 = r9.i()
            r1.O0(r3)
            r3 = 2
            java.lang.String r6 = a.C0068a.m(r1, r3)
            boolean r11 = r4.isEmpty()
            if (r11 == 0) goto L_0x0210
            goto L_0x022b
        L_0x0210:
            java.util.Iterator r11 = r4.iterator()
        L_0x0214:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x022b
            java.lang.Object r13 = r11.next()
            Te.i r13 = (Te.C0848i) r13
            java.lang.String r13 = a.C0068a.m(r13, r3)
            boolean r13 = r13.equals(r6)
            if (r13 == 0) goto L_0x0214
            goto L_0x023a
        L_0x022b:
            r4.add(r1)
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            af.h r1 = r1.g
            r1.getClass()
            goto L_0x023a
        L_0x0238:
            r28 = r1
        L_0x023a:
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            yf.e r1 = r1.f4013x
            yf.a r1 = (yf.C1357a) r1
            r1.a(r9, r4, r0)
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            hf.c r11 = r1.r
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x0368
            boolean r1 = r5.isAnnotation()
            r5.isInterface()
            if (r1 != 0) goto L_0x025d
            r13 = 0
            goto L_0x0361
        L_0x025d:
            java.lang.Object r3 = r8.d
            cf.a r3 = (cf.C0922a) r3
            java.lang.Object r4 = r8.f34h
            r13 = r4
            A0.l r13 = (A0.l) r13
            Ve.d r3 = r3.f4010j
            Ve.f r3 = r3.b(r2)
            r4 = 1
            bf.b r3 = bf.C0917b.V0(r9, r7, r4, r3)
            if (r1 == 0) goto L_0x032f
            java.util.Collection r1 = r2.d()
            java.util.ArrayList r2 = new java.util.ArrayList
            int r5 = r1.size()
            r2.<init>(r5)
            Hf.Y r5 = Hf.Y.COMMON
            r6 = 6
            r7 = 0
            ef.a r14 = a.C0068a.Y(r5, r4, r7, r6)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0298:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x02b9
            java.lang.Object r5 = r1.next()
            r6 = r5
            We.x r6 = (We.x) r6
            qf.g r6 = r6.c()
            qf.g r7 = Ze.x.b
            boolean r6 = kotlin.jvm.internal.j.a(r6, r7)
            if (r6 == 0) goto L_0x02b5
            r4.add(r5)
            goto L_0x0298
        L_0x02b5:
            r15.add(r5)
            goto L_0x0298
        L_0x02b9:
            r4.size()
            java.lang.Object r1 = ne.C1194l.N0(r4)
            r5 = r1
            We.x r5 = (We.x) r5
            if (r5 == 0) goto L_0x02fb
            We.B r1 = r5.f()
            boolean r4 = r1 instanceof We.i
            if (r4 == 0) goto L_0x02e0
            me.i r4 = new me.i
            We.i r1 = (We.i) r1
            r6 = 1
            Hf.c0 r7 = r13.o(r1, r14, r6)
            We.B r1 = r1.b
            Hf.x r1 = r13.p(r1, r14)
            r4.<init>(r7, r1)
            goto L_0x02ea
        L_0x02e0:
            me.i r4 = new me.i
            Hf.x r1 = r13.p(r1, r14)
            r7 = 0
            r4.<init>(r1, r7)
        L_0x02ea:
            java.lang.Object r1 = r4.d
            r6 = r1
            Hf.x r6 = (Hf.C0774x) r6
            java.lang.Object r1 = r4.e
            r7 = r1
            Hf.x r7 = (Hf.C0774x) r7
            r4 = 0
            r1 = r28
            r1.v(r2, r3, r4, r5, r6, r7)
            goto L_0x02fd
        L_0x02fb:
            r1 = r28
        L_0x02fd:
            if (r5 == 0) goto L_0x0302
            r16 = 1
            goto L_0x0304
        L_0x0302:
            r16 = 0
        L_0x0304:
            java.util.Iterator r15 = r15.iterator()
            r5 = 0
        L_0x0309:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x032d
            int r17 = r5 + 1
            java.lang.Object r4 = r15.next()
            We.x r4 = (We.x) r4
            We.B r6 = r4.f()
            Hf.x r6 = r13.p(r6, r14)
            int r5 = r5 + r16
            r7 = 0
            r29 = r5
            r5 = r4
            r4 = r29
            r1.v(r2, r3, r4, r5, r6, r7)
            r5 = r17
            goto L_0x0309
        L_0x032d:
            r5 = 0
            goto L_0x0332
        L_0x032f:
            java.util.List r2 = java.util.Collections.EMPTY_LIST
            goto L_0x032d
        L_0x0332:
            r3.N0(r5)
            Qe.p r1 = r9.getVisibility()
            kotlin.jvm.internal.j.d(r1, r12)
            Qe.p r4 = Ze.o.b
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x0349
            Qe.p r1 = Ze.o.f3952c
            kotlin.jvm.internal.j.d(r1, r10)
        L_0x0349:
            r3.S0(r2, r1)
            r4 = 1
            r3.M0(r4)
            Hf.B r1 = r9.i()
            r3.O0(r1)
            java.lang.Object r1 = r8.d
            cf.a r1 = (cf.C0922a) r1
            af.h r1 = r1.g
            r1.getClass()
            r13 = r3
        L_0x0361:
            java.util.List r1 = ne.C1195m.r0(r13)
            r4 = r1
            java.util.Collection r4 = (java.util.Collection) r4
        L_0x0368:
            java.util.ArrayList r0 = r11.e(r0, r4)
            java.util.List r0 = ne.C1194l.k1(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0947j.invoke():java.lang.Object");
    }

    public C0947j(C0951n nVar, B0.a aVar) {
        this.f = nVar;
        this.e = aVar;
    }
}
