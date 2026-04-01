package Df;

import Ae.b;
import Hf.B;
import Qe.C0816f;
import ef.C0993a;
import ef.g;

/* renamed from: Df.b  reason: case insensitive filesystem */
public final class C0736b implements b {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ C0736b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: We.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v33, resolved type: java.util.List} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v4 */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v9 */
    /* JADX WARNING: type inference failed for: r10v10, types: [Hf.x] */
    /* JADX WARNING: type inference failed for: r10v11 */
    /* JADX WARNING: type inference failed for: r0v67, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v29, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v34 */
    /* JADX WARNING: type inference failed for: r10v35 */
    /* JADX WARNING: type inference failed for: r10v36 */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x045c, code lost:
        if (r0.equals("hashCode") == false) goto L_0x049e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x04a6, code lost:
        if (r0.equals("toString") != false) goto L_0x04a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x04a8, code lost:
        r0 = ((java.util.ArrayList) r1.g()).isEmpty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0650, code lost:
        if (r1 == false) goto L_0x0655;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x04b4  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x04b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            int r2 = r0.d
            java.lang.String r3 = "fqName"
            java.lang.String r4 = "kotlinTypeRefiner"
            java.lang.String r5 = "getType(...)"
            r6 = 10
            me.x r7 = me.x.f4917a
            java.lang.String r8 = "it"
            r10 = 0
            r11 = 1
            java.lang.Object r0 = r0.e
            switch(r2) {
                case 0: goto L_0x0945;
                case 1: goto L_0x086e;
                case 2: goto L_0x0823;
                case 3: goto L_0x0813;
                case 4: goto L_0x07bf;
                case 5: goto L_0x0700;
                case 6: goto L_0x06b5;
                case 7: goto L_0x06a3;
                case 8: goto L_0x0676;
                case 9: goto L_0x065b;
                case 10: goto L_0x060b;
                case 11: goto L_0x0603;
                case 12: goto L_0x05ed;
                case 13: goto L_0x0534;
                case 14: goto L_0x0520;
                case 15: goto L_0x0517;
                case 16: goto L_0x0503;
                case 17: goto L_0x04c1;
                case 18: goto L_0x040e;
                case 19: goto L_0x03f2;
                case 20: goto L_0x03e4;
                case 21: goto L_0x03d9;
                case 22: goto L_0x02b7;
                case 23: goto L_0x029a;
                case 24: goto L_0x003a;
                case 25: goto L_0x0031;
                case 26: goto L_0x0021;
                default: goto L_0x0019;
            }
        L_0x0019:
            Hf.x r0 = (Hf.C0774x) r0
            Qe.C r1 = (Qe.C) r1
            kotlin.jvm.internal.j.e(r1, r8)
            return r0
        L_0x0021:
            Ne.l r0 = (Ne.l) r0
            Qe.C r1 = (Qe.C) r1
            kotlin.jvm.internal.j.e(r1, r8)
            Ne.i r1 = r1.f()
            Hf.B r0 = r1.q(r0)
            return r0
        L_0x0031:
            Qf.h r0 = (Qf.h) r0
            kotlin.jvm.internal.j.b(r1)
            r0.add(r1)
            return r7
        L_0x003a:
            Fd.a r0 = (Fd.C0744a) r0
            Ve.b r1 = (Ve.b) r1
            java.lang.String r2 = "kotlinClass"
            kotlin.jvm.internal.j.e(r1, r2)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            ge.W0 r6 = new ge.W0
            r6.<init>((Fd.C0744a) r0, (java.util.HashMap) r2, (java.util.HashMap) r3)
            java.lang.Class r0 = r1.f3829a
            java.lang.String r1 = "klass"
            kotlin.jvm.internal.j.e(r0, r1)
            java.lang.reflect.Method[] r1 = r0.getDeclaredMethods()
            ig.i r1 = kotlin.jvm.internal.j.g(r1)
        L_0x0066:
            boolean r7 = r1.hasNext()
            java.lang.String r8 = "toString(...)"
            java.lang.String r10 = "("
            if (r7 == 0) goto L_0x0121
            java.lang.Object r7 = r1.next()
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.String r11 = r7.getName()
            qf.g r11 = qf.C1240g.e(r11)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r10)
            java.lang.Class[] r10 = r7.getParameterTypes()
            ig.i r10 = kotlin.jvm.internal.j.g(r10)
        L_0x008b:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x00a2
            java.lang.Object r13 = r10.next()
            java.lang.Class r13 = (java.lang.Class) r13
            kotlin.jvm.internal.j.b(r13)
            java.lang.String r13 = We.C0892d.b(r13)
            r12.append(r13)
            goto L_0x008b
        L_0x00a2:
            java.lang.String r10 = ")"
            r12.append(r10)
            java.lang.Class r10 = r7.getReturnType()
            java.lang.String r13 = "getReturnType(...)"
            kotlin.jvm.internal.j.d(r10, r13)
            java.lang.String r10 = We.C0892d.b(r10)
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            kotlin.jvm.internal.j.d(r10, r8)
            D0.f r8 = r6.z0(r11, r10)
            java.lang.annotation.Annotation[] r10 = r7.getDeclaredAnnotations()
            ig.i r10 = kotlin.jvm.internal.j.g(r10)
        L_0x00ca:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00dd
            java.lang.Object r11 = r10.next()
            java.lang.annotation.Annotation r11 = (java.lang.annotation.Annotation) r11
            kotlin.jvm.internal.j.b(r11)
            Gd.a.a0(r8, r11)
            goto L_0x00ca
        L_0x00dd:
            java.lang.annotation.Annotation[][] r7 = r7.getParameterAnnotations()
            java.lang.String r10 = "getParameterAnnotations(...)"
            kotlin.jvm.internal.j.d(r7, r10)
            java.lang.annotation.Annotation[][] r7 = (java.lang.annotation.Annotation[][]) r7
            int r10 = r7.length
            r11 = 0
        L_0x00ea:
            if (r11 >= r10) goto L_0x011c
            r12 = r7[r11]
            ig.i r12 = kotlin.jvm.internal.j.g(r12)
        L_0x00f2:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0119
            java.lang.Object r13 = r12.next()
            java.lang.annotation.Annotation r13 = (java.lang.annotation.Annotation) r13
            He.d r14 = a.C0068a.w(r13)
            java.lang.Class r14 = a.C0068a.A(r14)
            qf.b r15 = We.C0892d.a(r14)
            Ve.a r9 = new Ve.a
            r9.<init>(r13)
            jf.e r9 = r8.W(r11, r15, r9)
            if (r9 == 0) goto L_0x00f2
            Gd.a.b0(r9, r13, r14)
            goto L_0x00f2
        L_0x0119:
            int r11 = r11 + 1
            goto L_0x00ea
        L_0x011c:
            r8.n()
            goto L_0x0066
        L_0x0121:
            java.lang.reflect.Constructor[] r1 = r0.getDeclaredConstructors()
            ig.i r1 = kotlin.jvm.internal.j.g(r1)
        L_0x0129:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x01fa
            java.lang.Object r7 = r1.next()
            java.lang.reflect.Constructor r7 = (java.lang.reflect.Constructor) r7
            qf.g r9 = qf.C1242i.e
            kotlin.jvm.internal.j.b(r7)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r10)
            java.lang.Class[] r12 = r7.getParameterTypes()
            ig.i r12 = kotlin.jvm.internal.j.g(r12)
        L_0x0147:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x015e
            java.lang.Object r13 = r12.next()
            java.lang.Class r13 = (java.lang.Class) r13
            kotlin.jvm.internal.j.b(r13)
            java.lang.String r13 = We.C0892d.b(r13)
            r11.append(r13)
            goto L_0x0147
        L_0x015e:
            java.lang.String r12 = ")V"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            kotlin.jvm.internal.j.d(r11, r8)
            D0.f r9 = r6.z0(r9, r11)
            java.lang.annotation.Annotation[] r11 = r7.getDeclaredAnnotations()
            ig.i r11 = kotlin.jvm.internal.j.g(r11)
        L_0x0176:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0189
            java.lang.Object r12 = r11.next()
            java.lang.annotation.Annotation r12 = (java.lang.annotation.Annotation) r12
            kotlin.jvm.internal.j.b(r12)
            Gd.a.a0(r9, r12)
            goto L_0x0176
        L_0x0189:
            java.lang.annotation.Annotation[][] r11 = r7.getParameterAnnotations()
            kotlin.jvm.internal.j.b(r11)
            int r12 = r11.length
            if (r12 != 0) goto L_0x019a
        L_0x0193:
            r19 = r0
            r20 = r1
            r18 = r8
            goto L_0x01ef
        L_0x019a:
            java.lang.Class[] r7 = r7.getParameterTypes()
            int r7 = r7.length
            int r12 = r11.length
            int r7 = r7 - r12
            int r12 = r11.length
            r13 = 0
        L_0x01a3:
            if (r13 >= r12) goto L_0x0193
            r14 = r11[r13]
            ig.i r14 = kotlin.jvm.internal.j.g(r14)
        L_0x01ab:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x01e4
            java.lang.Object r15 = r14.next()
            java.lang.annotation.Annotation r15 = (java.lang.annotation.Annotation) r15
            He.d r17 = a.C0068a.w(r15)
            r19 = r0
            java.lang.Class r0 = a.C0068a.A(r17)
            r20 = r1
            int r1 = r13 + r7
            r17 = r7
            qf.b r7 = We.C0892d.a(r0)
            r18 = r8
            Ve.a r8 = new Ve.a
            r8.<init>(r15)
            jf.e r1 = r9.W(r1, r7, r8)
            if (r1 == 0) goto L_0x01db
            Gd.a.b0(r1, r15, r0)
        L_0x01db:
            r0 = r19
            r1 = r20
            r7 = r17
            r8 = r18
            goto L_0x01ab
        L_0x01e4:
            r19 = r0
            r20 = r1
            r17 = r7
            r18 = r8
            int r13 = r13 + 1
            goto L_0x01a3
        L_0x01ef:
            r9.n()
            r0 = r19
            r1 = r20
            r8 = r18
            goto L_0x0129
        L_0x01fa:
            r19 = r0
            java.lang.reflect.Field[] r0 = r19.getDeclaredFields()
            ig.i r0 = kotlin.jvm.internal.j.g(r0)
        L_0x0204:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0294
            java.lang.Object r1 = r0.next()
            java.lang.reflect.Field r1 = (java.lang.reflect.Field) r1
            java.lang.String r7 = r1.getName()
            qf.g r7 = qf.C1240g.e(r7)
            java.lang.Class r8 = r1.getType()
            kotlin.jvm.internal.j.d(r8, r5)
            java.lang.String r8 = We.C0892d.b(r8)
            java.lang.String r9 = "desc"
            kotlin.jvm.internal.j.e(r8, r9)
            java.lang.String r7 = r7.b()
            java.lang.String r9 = "asString(...)"
            kotlin.jvm.internal.j.d(r7, r9)
            jf.s r9 = new jf.s
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r7)
            r7 = 35
            r10.append(r7)
            r10.append(r8)
            java.lang.String r7 = r10.toString()
            r9.<init>(r7)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.lang.annotation.Annotation[] r1 = r1.getDeclaredAnnotations()
            ig.i r1 = kotlin.jvm.internal.j.g(r1)
        L_0x0257:
            boolean r8 = r1.hasNext()
            if (r8 == 0) goto L_0x0285
            java.lang.Object r8 = r1.next()
            java.lang.annotation.Annotation r8 = (java.lang.annotation.Annotation) r8
            kotlin.jvm.internal.j.b(r8)
            He.d r10 = a.C0068a.w(r8)
            java.lang.Class r10 = a.C0068a.A(r10)
            qf.b r11 = We.C0892d.a(r10)
            Ve.a r12 = new Ve.a
            r12.<init>(r8)
            java.lang.Object r13 = r6.e
            Fd.a r13 = (Fd.C0744a) r13
            jf.e r11 = r13.t(r11, r12, r7)
            if (r11 == 0) goto L_0x0257
            Gd.a.b0(r11, r8, r10)
            goto L_0x0257
        L_0x0285:
            boolean r1 = r7.isEmpty()
            if (r1 != 0) goto L_0x0204
            java.lang.Object r1 = r6.f
            java.util.HashMap r1 = (java.util.HashMap) r1
            r1.put(r9, r7)
            goto L_0x0204
        L_0x0294:
            jf.d r0 = new jf.d
            r0.<init>(r2, r3, r4)
            return r0
        L_0x029a:
            Te.Q r0 = (Te.Q) r0
            Qe.d r1 = (Qe.C0814d) r1
            kotlin.jvm.internal.j.e(r1, r8)
            java.util.List r1 = r1.B()
            int r0 = r0.f3770j
            java.lang.Object r0 = r1.get(r0)
            Te.Q r0 = (Te.Q) r0
            Te.S r0 = (Te.S) r0
            Hf.x r0 = r0.getType()
            kotlin.jvm.internal.j.d(r0, r5)
            return r0
        L_0x02b7:
            Jd.b r0 = (Jd.b) r0
            hf.a r1 = (hf.C1079a) r1
            kotlin.jvm.internal.j.e(r1, r8)
            Ze.u r2 = r1.b
            Kf.d r1 = r1.f4583a
            boolean r3 = r0.b
            java.lang.String r4 = "$receiver"
            java.lang.String r5 = ", "
            java.lang.String r7 = "ClassicTypeSystemContext couldn't handle: "
            if (r3 == 0) goto L_0x02fa
            if (r1 == 0) goto L_0x02fa
            kotlin.jvm.internal.j.e(r1, r4)
            boolean r3 = r1 instanceof Hf.C0774x
            if (r3 == 0) goto L_0x02db
            boolean r3 = r1 instanceof ef.i
            if (r3 != r11) goto L_0x02fa
            goto L_0x03d8
        L_0x02db:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r7)
            r0.append(r1)
            r0.append(r5)
            java.lang.Class r1 = r1.getClass()
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            java.lang.String r0 = A.a.g(r2, r1, r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x02fa:
            if (r1 == 0) goto L_0x03d8
            Hf.B r3 = If.g.h(r1)
            if (r3 != 0) goto L_0x0315
            Hf.q r3 = If.g.g(r1)
            if (r3 == 0) goto L_0x030e
            Hf.B r3 = If.g.N(r3)
            if (r3 != 0) goto L_0x0315
        L_0x030e:
            Hf.B r3 = If.g.h(r1)
            kotlin.jvm.internal.j.b(r3)
        L_0x0315:
            Hf.M r3 = If.g.W(r3)
            if (r3 == 0) goto L_0x03d8
            boolean r8 = r3 instanceof Hf.M
            if (r8 == 0) goto L_0x03b9
            Hf.M r3 = (Hf.M) r3
            java.util.List r3 = r3.getParameters()
            java.lang.String r8 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r3, r8)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            kotlin.jvm.internal.j.e(r1, r4)
            boolean r4 = r1 instanceof Hf.C0774x
            if (r4 == 0) goto L_0x039a
            Hf.x r1 = (Hf.C0774x) r1
            java.util.List r1 = r1.e0()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r4 = r3.iterator()
            java.util.Iterator r5 = r1.iterator()
            java.util.ArrayList r7 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r3, r6)
            int r1 = ne.C1196n.w0(r1, r6)
            int r1 = java.lang.Math.min(r3, r1)
            r7.<init>(r1)
        L_0x0354:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0398
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0398
            java.lang.Object r1 = r4.next()
            java.lang.Object r3 = r5.next()
            Hf.P r3 = (Hf.P) r3
            Qe.V r1 = (Qe.V) r1
            boolean r6 = If.g.K(r3)
            if (r6 == 0) goto L_0x0378
            hf.a r3 = new hf.a
            r3.<init>(r10, r2, r1)
            goto L_0x0394
        L_0x0378:
            Hf.c0 r3 = If.g.r(r3)
            hf.a r6 = new hf.a
            java.lang.Object r8 = r0.d
            B0.a r8 = (B0.a) r8
            java.lang.Object r8 = r8.d
            cf.a r8 = (cf.C0922a) r8
            Ze.b r8 = r8.q
            Re.h r9 = r3.getAnnotations()
            Ze.u r8 = r8.b(r2, r9)
            r6.<init>(r3, r8, r1)
            r3 = r6
        L_0x0394:
            r7.add(r3)
            goto L_0x0354
        L_0x0398:
            r10 = r7
            goto L_0x03d8
        L_0x039a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r7)
            r0.append(r1)
            r0.append(r5)
            java.lang.Class r1 = r1.getClass()
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            java.lang.String r0 = A.a.g(r2, r1, r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03b9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r7)
            r0.append(r3)
            r0.append(r5)
            java.lang.Class r1 = r3.getClass()
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            java.lang.String r0 = A.a.g(r2, r1, r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03d8:
            return r10
        L_0x03d9:
            Qe.f r0 = (Qe.C0816f) r0
            If.f r1 = (If.f) r1
            kotlin.jvm.internal.j.e(r1, r4)
            xf.C1353d.f(r0)
            return r10
        L_0x03e4:
            qf.g r0 = (qf.C1240g) r0
            Af.p r1 = (Af.p) r1
            kotlin.jvm.internal.j.e(r1, r8)
            Ye.c r2 = Ye.c.WHEN_GET_SUPER_MEMBERS
            java.util.Collection r0 = r1.f(r0, r2)
            return r0
        L_0x03f2:
            r3 = r0
            df.i r3 = (df.C0946i) r3
            r0 = r1
            If.f r0 = (If.f) r0
            kotlin.jvm.internal.j.e(r0, r8)
            df.n r1 = new df.n
            B0.a r2 = r3.m
            We.o r4 = r3.k
            Qe.f r0 = r3.l
            if (r0 == 0) goto L_0x0407
            r5 = r11
            goto L_0x0408
        L_0x0407:
            r5 = 0
        L_0x0408:
            df.n r6 = r3.t
            r1.<init>(r2, r3, r4, r5, r6)
            return r1
        L_0x040e:
            df.a r0 = (df.C0938a) r0
            We.x r1 = (We.x) r1
            java.lang.String r2 = "m"
            kotlin.jvm.internal.j.e(r1, r2)
            Ae.b r0 = r0.b
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x04bb
            java.lang.reflect.Member r0 = r1.b()
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            java.lang.Class r0 = r0.getDeclaringClass()
            java.lang.String r2 = "getDeclaringClass(...)"
            kotlin.jvm.internal.j.d(r0, r2)
            boolean r0 = r0.isInterface()
            if (r0 == 0) goto L_0x04b6
            qf.g r0 = r1.c()
            java.lang.String r0 = r0.b()
            int r2 = r0.hashCode()
            r3 = -1776922004(0xffffffff9616526c, float:-1.2142911E-25)
            if (r2 == r3) goto L_0x04a0
            r3 = -1295482945(0xffffffffb2c87fbf, float:-2.3341157E-8)
            if (r2 == r3) goto L_0x045f
            r3 = 147696667(0x8cdac1b, float:1.23784505E-33)
            if (r2 == r3) goto L_0x0456
            goto L_0x049e
        L_0x0456:
            java.lang.String r2 = "hashCode"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x04a8
            goto L_0x049e
        L_0x045f:
            java.lang.String r2 = "equals"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0468
            goto L_0x049e
        L_0x0468:
            java.util.List r0 = r1.g()
            java.lang.Object r0 = ne.C1194l.d1(r0)
            We.D r0 = (We.D) r0
            if (r0 == 0) goto L_0x0477
            We.B r0 = r0.f3879a
            goto L_0x0478
        L_0x0477:
            r0 = r10
        L_0x0478:
            boolean r1 = r0 instanceof We.q
            if (r1 == 0) goto L_0x047f
            r10 = r0
            We.q r10 = (We.q) r10
        L_0x047f:
            if (r10 != 0) goto L_0x0482
            goto L_0x049e
        L_0x0482:
            We.s r0 = r10.b
            boolean r1 = r0 instanceof We.o
            if (r1 == 0) goto L_0x049e
            We.o r0 = (We.o) r0
            qf.c r0 = r0.c()
            if (r0 == 0) goto L_0x049e
            java.lang.String r0 = r0.b()
            java.lang.String r1 = "java.lang.Object"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x049e
            r0 = r11
            goto L_0x04b2
        L_0x049e:
            r0 = 0
            goto L_0x04b2
        L_0x04a0:
            java.lang.String r2 = "toString"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x049e
        L_0x04a8:
            java.util.List r0 = r1.g()
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            boolean r0 = r0.isEmpty()
        L_0x04b2:
            if (r0 == 0) goto L_0x04b6
            r0 = r11
            goto L_0x04b7
        L_0x04b6:
            r0 = 0
        L_0x04b7:
            if (r0 != 0) goto L_0x04bb
            r9 = r11
            goto L_0x04bc
        L_0x04bb:
            r9 = 0
        L_0x04bc:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r9)
            return r0
        L_0x04c1:
            Ed.b r0 = (Ed.b) r0
            We.C r1 = (We.C) r1
            java.lang.String r2 = "typeParameter"
            kotlin.jvm.internal.j.e(r1, r2)
            java.lang.Object r2 = r0.e
            java.util.LinkedHashMap r2 = (java.util.LinkedHashMap) r2
            java.lang.Object r3 = r0.d
            Qe.m r3 = (Qe.C0823m) r3
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x0502
            int r2 = r2.intValue()
            df.F r10 = new df.F
            java.lang.Object r4 = r0.f3372c
            B0.a r4 = (B0.a) r4
            java.lang.String r5 = "<this>"
            kotlin.jvm.internal.j.e(r4, r5)
            B0.a r5 = new B0.a
            java.lang.Object r6 = r4.d
            cf.a r6 = (cf.C0922a) r6
            java.lang.Object r4 = r4.f
            r5.<init>(r6, r0, r4)
            Re.h r4 = r3.getAnnotations()
            B0.a r4 = Gd.a.l(r5, r4)
            int r0 = r0.b
            int r0 = r0 + r2
            r10.<init>(r4, r1, r0, r3)
        L_0x0502:
            return r10
        L_0x0503:
            cf.c r0 = (cf.c) r0
            We.e r1 = (We.C0893e) r1
            java.lang.String r2 = "annotation"
            kotlin.jvm.internal.j.e(r1, r2)
            qf.g r2 = af.C0905c.f3992a
            B0.a r2 = r0.d
            boolean r0 = r0.f
            bf.i r0 = af.C0905c.b(r2, r1, r0)
            return r0
        L_0x0517:
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            com.google.common.util.concurrent.ListenableFuture r0 = (com.google.common.util.concurrent.ListenableFuture) r0
            r1 = 0
            r0.cancel(r1)
            return r7
        L_0x0520:
            Qe.d r1 = (Qe.C0814d) r1
            if (r1 == 0) goto L_0x052c
            af.a r0 = (af.C0903a) r0
            Df.p r0 = r0.f3989c
            r0.c(r1)
            return r7
        L_0x052c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Argument for @NotNull parameter 'descriptor' of kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1$1.invoke must not be null"
            r0.<init>(r1)
            throw r0
        L_0x0534:
            D0.e r0 = (D0.e) r0
            r2 = r1
            qf.c r2 = (qf.C1236c) r2
            kotlin.jvm.internal.j.b(r2)
            java.lang.Object r0 = r0.e
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x054b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0586
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            qf.c r4 = (qf.C1236c) r4
            boolean r5 = r2.equals(r4)
            if (r5 != 0) goto L_0x057a
            java.lang.String r5 = "packageName"
            kotlin.jvm.internal.j.e(r4, r5)
            boolean r5 = r2.d()
            if (r5 == 0) goto L_0x0570
            r5 = r10
            goto L_0x0574
        L_0x0570:
            qf.c r5 = r2.e()
        L_0x0574:
            boolean r4 = kotlin.jvm.internal.j.a(r5, r4)
            if (r4 == 0) goto L_0x054b
        L_0x057a:
            java.lang.Object r4 = r3.getKey()
            java.lang.Object r3 = r3.getValue()
            r1.put(r4, r3)
            goto L_0x054b
        L_0x0586:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x058d
            goto L_0x058e
        L_0x058d:
            r1 = r10
        L_0x058e:
            if (r1 != 0) goto L_0x0591
            goto L_0x05ec
        L_0x0591:
            java.util.Set r0 = r1.entrySet()
            java.util.Iterator r3 = r0.iterator()
            boolean r0 = r3.hasNext()
            if (r0 != 0) goto L_0x05a1
            r0 = r10
            goto L_0x05e4
        L_0x05a1:
            java.lang.Object r0 = r3.next()
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L_0x05ac
            goto L_0x05e4
        L_0x05ac:
            r1 = r0
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getKey()
            qf.c r1 = (qf.C1236c) r1
            qf.c r1 = B1.a.T(r1, r2)
            java.lang.String r1 = r1.b()
            int r1 = r1.length()
        L_0x05c1:
            java.lang.Object r4 = r3.next()
            r5 = r4
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r5 = r5.getKey()
            qf.c r5 = (qf.C1236c) r5
            qf.c r5 = B1.a.T(r5, r2)
            java.lang.String r5 = r5.b()
            int r5 = r5.length()
            if (r1 <= r5) goto L_0x05de
            r0 = r4
            r1 = r5
        L_0x05de:
            boolean r4 = r3.hasNext()
            if (r4 != 0) goto L_0x05c1
        L_0x05e4:
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            if (r0 == 0) goto L_0x05ec
            java.lang.Object r10 = r0.getValue()
        L_0x05ec:
            return r10
        L_0x05ed:
            Te.K r0 = (Te.K) r0
            Qe.d r1 = (Qe.C0814d) r1
            kotlin.jvm.internal.j.e(r1, r8)
            java.util.LinkedHashMap r1 = Ze.H.f3935i
            java.lang.String r0 = a.C0068a.n(r0)
            boolean r0 = r1.containsKey(r0)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x0603:
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            Vf.l r0 = (Vf.C0875l) r0
            r0.resumeWith(r7)
            return r7
        L_0x060b:
            We.o r0 = (We.o) r0
            java.lang.reflect.Method r1 = (java.lang.reflect.Method) r1
            boolean r2 = r1.isSynthetic()
            if (r2 == 0) goto L_0x0616
            goto L_0x0653
        L_0x0616:
            java.lang.Class r0 = r0.f3891a
            boolean r0 = r0.isEnum()
            if (r0 == 0) goto L_0x0655
            java.lang.String r0 = r1.getName()
            java.lang.String r2 = "values"
            boolean r2 = kotlin.jvm.internal.j.a(r0, r2)
            if (r2 == 0) goto L_0x063a
            java.lang.Class[] r0 = r1.getParameterTypes()
            java.lang.String r1 = "getParameterTypes(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            int r0 = r0.length
            if (r0 != 0) goto L_0x0638
            r1 = r11
            goto L_0x0650
        L_0x0638:
            r1 = 0
            goto L_0x0650
        L_0x063a:
            java.lang.String r2 = "valueOf"
            boolean r0 = kotlin.jvm.internal.j.a(r0, r2)
            if (r0 == 0) goto L_0x0638
            java.lang.Class[] r0 = r1.getParameterTypes()
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            java.lang.Class[] r1 = new java.lang.Class[]{r1}
            boolean r1 = java.util.Arrays.equals(r0, r1)
        L_0x0650:
            if (r1 != 0) goto L_0x0653
            goto L_0x0655
        L_0x0653:
            r9 = 0
            goto L_0x0656
        L_0x0655:
            r9 = r11
        L_0x0656:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r9)
            return r0
        L_0x065b:
            Te.z r0 = (Te.z) r0
            qf.c r1 = (qf.C1236c) r1
            kotlin.jvm.internal.j.e(r1, r3)
            Te.E r2 = r0.f3813j
            Gf.m r3 = r0.g
            Te.D r2 = (Te.D) r2
            r2.getClass()
            java.lang.String r2 = "storageManager"
            kotlin.jvm.internal.j.e(r3, r2)
            Te.w r2 = new Te.w
            r2.<init>(r0, r1, r3)
            return r2
        L_0x0676:
            Te.f r0 = (Te.C0845f) r0
            Hf.c0 r1 = (Hf.c0) r1
            kotlin.jvm.internal.j.b(r1)
            boolean r2 = Hf.C0754c.k(r1)
            if (r2 != 0) goto L_0x069d
            Hf.M r1 = r1.s0()
            Qe.i r1 = r1.g()
            boolean r2 = r1 instanceof Qe.V
            if (r2 == 0) goto L_0x069d
            Qe.V r1 = (Qe.V) r1
            Qe.l r1 = r1.g()
            boolean r0 = kotlin.jvm.internal.j.a(r1, r0)
            if (r0 != 0) goto L_0x069d
            r9 = r11
            goto L_0x069e
        L_0x069d:
            r9 = 0
        L_0x069e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r9)
            return r0
        L_0x06a3:
            If.f r1 = (If.f) r1
            Te.a r0 = (Te.C0840a) r0
            r1.getClass()
            Te.b r0 = r0.e
            Gf.i r0 = r0.e
            java.lang.Object r0 = r0.invoke()
            Hf.B r0 = (Hf.B) r0
            return r0
        L_0x06b5:
            Pe.q r0 = (Pe.q) r0
            me.i r1 = (me.i) r1
            java.lang.String r2 = "<destruct>"
            kotlin.jvm.internal.j.e(r1, r2)
            java.lang.Object r2 = r1.d
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.e
            java.lang.String r1 = (java.lang.String) r1
            Te.z r0 = r0.f3647a
            Ne.i r0 = r0.f3811h
            java.lang.String r3 = "()' member of List is redundant in Kotlin and might be removed soon. Please use '"
            java.lang.String r4 = "()' stdlib extension instead"
            java.lang.String r5 = "'"
            java.lang.String r2 = N2.j.d(r5, r2, r3, r1, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = "()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.lang.String r3 = "HIDDEN"
            Re.j r0 = Re.e.a(r0, r2, r1, r3)
            java.util.List r0 = o1.C0246a.e0(r0)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x06f8
            Re.f r0 = Re.g.f3692a
            goto L_0x06ff
        L_0x06f8:
            Re.i r1 = new Re.i
            r2 = 0
            r1.<init>(r2, r0)
            r0 = r1
        L_0x06ff:
            return r0
        L_0x0700:
            D0.e r0 = (D0.e) r0
            Hf.O r1 = (Hf.O) r1
            Qe.V r2 = r1.f3434a
            ef.a r12 = r1.b
            java.util.Set r1 = r12.e
            if (r1 == 0) goto L_0x071c
            Qe.V r3 = r2.a()
            boolean r3 = r1.contains(r3)
            if (r3 == 0) goto L_0x071c
            Hf.c0 r0 = r0.H(r12)
            goto L_0x07be
        L_0x071c:
            Hf.B r3 = r2.i()
            java.lang.String r4 = "getDefaultType(...)"
            kotlin.jvm.internal.j.d(r3, r4)
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.v(r3, r3, r4, r1)
            int r3 = ne.C1196n.w0(r4, r6)
            int r3 = ne.z.Z(r3)
            r5 = 16
            if (r3 >= r5) goto L_0x073a
            r3 = r5
        L_0x073a:
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r5.<init>(r3)
            java.util.Iterator r3 = r4.iterator()
        L_0x0743:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0786
            java.lang.Object r4 = r3.next()
            Qe.V r4 = (Qe.V) r4
            if (r1 == 0) goto L_0x075d
            boolean r6 = r1.contains(r4)
            if (r6 != 0) goto L_0x0758
            goto L_0x075d
        L_0x0758:
            Hf.P r6 = Hf.a0.k(r4, r12)
            goto L_0x077e
        L_0x075d:
            java.util.Set r6 = r12.e
            if (r6 == 0) goto L_0x0767
            java.util.LinkedHashSet r6 = ne.C1182C.c0(r6, r2)
        L_0x0765:
            r15 = r6
            goto L_0x076c
        L_0x0767:
            java.util.Set r6 = B1.a.S(r2)
            goto L_0x0765
        L_0x076c:
            r16 = 0
            r17 = 47
            r13 = 0
            r14 = 0
            ef.a r6 = ef.C0993a.a(r12, r13, r14, r15, r16, r17)
            Hf.x r6 = r0.I(r4, r6)
            Hf.P r6 = ef.f.a(r4, r12, r0, r6)
        L_0x077e:
            Hf.M r4 = r4.q()
            r5.put(r4, r6)
            goto L_0x0743
        L_0x0786:
            Hf.H r1 = new Hf.H
            r1.<init>(r11, r5)
            Hf.X r3 = new Hf.X
            r3.<init>(r1)
            java.util.List r1 = r2.getUpperBounds()
            java.lang.String r2 = "getUpperBounds(...)"
            kotlin.jvm.internal.j.d(r1, r2)
            oe.i r1 = r0.d0(r3, r1, r12)
            oe.f r2 = r1.d
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x07ba
            oe.f r0 = r1.d
            int r0 = r0.l
            if (r0 != r11) goto L_0x07b2
            java.lang.Object r0 = ne.C1194l.a1(r1)
            Hf.x r0 = (Hf.C0774x) r0
            goto L_0x07be
        L_0x07b2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Should only be one computed upper bound if no need to intersect all bounds"
            r0.<init>(r1)
            throw r0
        L_0x07ba:
            Hf.c0 r0 = r0.H(r12)
        L_0x07be:
            return r0
        L_0x07bf:
            r2 = 0
            Hf.w r0 = (Hf.C0773w) r0
            If.f r1 = (If.f) r1
            kotlin.jvm.internal.j.e(r1, r4)
            java.util.LinkedHashSet r3 = r0.b
            java.util.ArrayList r4 = new java.util.ArrayList
            int r5 = ne.C1196n.w0(r3, r6)
            r4.<init>(r5)
            java.util.Iterator r3 = r3.iterator()
            r9 = r2
        L_0x07d7:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x07ec
            java.lang.Object r2 = r3.next()
            Hf.x r2 = (Hf.C0774x) r2
            Hf.x r2 = r2.w0(r1)
            r4.add(r2)
            r9 = r11
            goto L_0x07d7
        L_0x07ec:
            if (r9 != 0) goto L_0x07ef
            goto L_0x080a
        L_0x07ef:
            Hf.x r2 = r0.f3452a
            if (r2 == 0) goto L_0x07f7
            Hf.x r10 = r2.w0(r1)
        L_0x07f7:
            r4.isEmpty()
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>(r4)
            r1.hashCode()
            Hf.w r2 = new Hf.w
            r2.<init>(r1)
            r2.f3452a = r10
            r10 = r2
        L_0x080a:
            if (r10 != 0) goto L_0x080d
            goto L_0x080e
        L_0x080d:
            r0 = r10
        L_0x080e:
            Hf.B r0 = r0.b()
            return r0
        L_0x0813:
            Ae.b r0 = (Ae.b) r0
            Hf.x r1 = (Hf.C0774x) r1
            kotlin.jvm.internal.j.b(r1)
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x0823:
            Hf.g r0 = (Hf.C0758g) r0
            Hf.f r1 = (Hf.C0757f) r1
            java.lang.String r2 = "supertypes"
            kotlin.jvm.internal.j.e(r1, r2)
            Qe.S r2 = r0.d()
            java.util.Collection r3 = r1.f3443a
            r2.getClass()
            java.lang.String r2 = "superTypes"
            kotlin.jvm.internal.j.e(r3, r2)
            boolean r2 = r3.isEmpty()
            if (r2 == 0) goto L_0x0853
            Hf.x r2 = r0.c()
            if (r2 == 0) goto L_0x084b
            java.util.List r2 = o1.C0246a.e0(r2)
            goto L_0x084c
        L_0x084b:
            r2 = r10
        L_0x084c:
            if (r2 != 0) goto L_0x0850
            ne.t r2 = ne.C1202t.d
        L_0x0850:
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
        L_0x0853:
            boolean r2 = r3 instanceof java.util.List
            if (r2 == 0) goto L_0x085a
            r10 = r3
            java.util.List r10 = (java.util.List) r10
        L_0x085a:
            if (r10 != 0) goto L_0x0862
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.List r10 = ne.C1194l.k1(r3)
        L_0x0862:
            java.util.List r0 = r0.k(r10)
            java.lang.String r2 = "<set-?>"
            kotlin.jvm.internal.j.e(r0, r2)
            r1.b = r0
            return r7
        L_0x086e:
            Df.j r0 = (Df.j) r0
            Df.i r1 = (Df.C0743i) r1
            java.lang.String r2 = "key"
            kotlin.jvm.internal.j.e(r1, r2)
            qf.b r2 = r1.f3345a
            Df.l r3 = r0.f3347a
            java.lang.Iterable r4 = r3.k
            java.util.Iterator r4 = r4.iterator()
        L_0x0881:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0896
            java.lang.Object r5 = r4.next()
            Se.c r5 = (Se.c) r5
            Qe.f r5 = r5.a(r2)
            if (r5 == 0) goto L_0x0881
            r10 = r5
            goto L_0x0944
        L_0x0896:
            java.util.Set r4 = Df.j.f3346c
            boolean r4 = r4.contains(r2)
            if (r4 == 0) goto L_0x08a0
            goto L_0x0944
        L_0x08a0:
            Df.g r1 = r1.b
            if (r1 != 0) goto L_0x08ae
            Df.h r1 = r3.d
            Df.g r1 = r1.q(r2)
            if (r1 != 0) goto L_0x08ae
            goto L_0x0944
        L_0x08ae:
            nf.f r5 = r1.f3343a
            lf.j r11 = r1.b
            nf.a r8 = r1.f3344c
            Qe.Q r1 = r1.d
            qf.b r4 = r2.e()
            if (r4 == 0) goto L_0x08e2
            Qe.f r0 = r0.a(r4, r10)
            boolean r3 = r0 instanceof Ff.k
            if (r3 == 0) goto L_0x08c7
            Ff.k r0 = (Ff.k) r0
            goto L_0x08c8
        L_0x08c7:
            r0 = r10
        L_0x08c8:
            if (r0 != 0) goto L_0x08cc
            goto L_0x0944
        L_0x08cc:
            qf.g r2 = r2.f()
            Ff.h r3 = r0.e0()
            java.util.Set r3 = r3.m()
            boolean r2 = r3.contains(r2)
            if (r2 != 0) goto L_0x08df
            goto L_0x0944
        L_0x08df:
            Df.n r0 = r0.f3389o
            goto L_0x093a
        L_0x08e2:
            Qe.K r0 = r3.f
            qf.c r4 = r2.f5033a
            java.util.ArrayList r0 = Qe.C0833x.i(r0, r4)
            java.util.Iterator r0 = r0.iterator()
        L_0x08ee:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0916
            java.lang.Object r4 = r0.next()
            r6 = r4
            Qe.H r6 = (Qe.H) r6
            boolean r7 = r6 instanceof Ef.d
            if (r7 == 0) goto L_0x0917
            Ef.d r6 = (Ef.d) r6
            qf.g r7 = r2.f()
            Af.p r6 = r6.A()
            Ff.s r6 = (Ff.s) r6
            java.util.Set r6 = r6.m()
            boolean r6 = r6.contains(r7)
            if (r6 == 0) goto L_0x08ee
            goto L_0x0917
        L_0x0916:
            r4 = r10
        L_0x0917:
            Qe.H r4 = (Qe.H) r4
            if (r4 != 0) goto L_0x091c
            goto L_0x0944
        L_0x091c:
            B1.b r6 = new B1.b
            lf.X r0 = r11.f4846H
            java.lang.String r2 = "getTypeTable(...)"
            kotlin.jvm.internal.j.d(r0, r2)
            r6.<init>((lf.X) r0)
            nf.h r0 = nf.C1211h.b
            lf.e0 r0 = r11.f4848J
            java.lang.String r2 = "getVersionRequirementTable(...)"
            kotlin.jvm.internal.j.d(r0, r2)
            nf.h r7 = He.F.x(r0)
            r9 = 0
            Df.n r0 = r3.a(r4, r5, r6, r7, r8, r9)
        L_0x093a:
            Ff.k r4 = new Ff.k
            r9 = r1
            r7 = r5
            r6 = r11
            r5 = r0
            r4.<init>(r5, r6, r7, r8, r9)
            r10 = r4
        L_0x0944:
            return r10
        L_0x0945:
            Pe.s r0 = (Pe.s) r0
            qf.c r1 = (qf.C1236c) r1
            kotlin.jvm.internal.j.e(r1, r3)
            Ef.d r1 = r0.c(r1)
            if (r1 == 0) goto L_0x0961
            Df.l r0 = r0.f3651c
            if (r0 == 0) goto L_0x095b
            r1.F0(r0)
            r10 = r1
            goto L_0x0961
        L_0x095b:
            java.lang.String r0 = "components"
            kotlin.jvm.internal.j.k(r0)
            throw r10
        L_0x0961:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.C0736b.invoke(java.lang.Object):java.lang.Object");
    }

    public C0736b(C0816f fVar, g gVar, B b, C0993a aVar) {
        this.d = 21;
        this.e = fVar;
    }
}
