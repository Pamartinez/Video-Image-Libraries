package Ke;

import Ae.a;

/* renamed from: Ke.p  reason: case insensitive filesystem */
public final class C0798p implements a {
    public final /* synthetic */ int d;
    public final C0800s e;

    public /* synthetic */ C0798p(C0800s sVar, int i2) {
        this.d = i2;
        this.e = sVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.reflect.Type} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [int] */
    /* JADX WARNING: type inference failed for: r4v11, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r10 = this;
            int r0 = r10.d
            r1 = 0
            r2 = 0
            r3 = 1
            Ke.s r10 = r10.e
            switch(r0) {
                case 0: goto L_0x025a;
                case 1: goto L_0x01dc;
                case 2: goto L_0x01c5;
                case 3: goto L_0x018e;
                case 4: goto L_0x0099;
                case 5: goto L_0x0063;
                default: goto L_0x000a;
            }
        L_0x000a:
            boolean r0 = r10.isSuspend()
            if (r0 == 0) goto L_0x0058
            Le.g r0 = r10.g()
            java.util.List r0 = r0.a()
            java.lang.Object r0 = ne.C1194l.U0(r0)
            boolean r2 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x0023
            java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
            goto L_0x0024
        L_0x0023:
            r0 = r1
        L_0x0024:
            if (r0 == 0) goto L_0x002b
            java.lang.reflect.Type r2 = r0.getRawType()
            goto L_0x002c
        L_0x002b:
            r2 = r1
        L_0x002c:
            java.lang.Class<qe.c> r3 = qe.C1227c.class
            boolean r2 = kotlin.jvm.internal.j.a(r2, r3)
            if (r2 == 0) goto L_0x0058
            java.lang.reflect.Type[] r0 = r0.getActualTypeArguments()
            java.lang.String r2 = "getActualTypeArguments(...)"
            kotlin.jvm.internal.j.d(r0, r2)
            java.lang.Object r0 = ne.C1192j.v0(r0)
            boolean r2 = r0 instanceof java.lang.reflect.WildcardType
            if (r2 == 0) goto L_0x0048
            java.lang.reflect.WildcardType r0 = (java.lang.reflect.WildcardType) r0
            goto L_0x0049
        L_0x0048:
            r0 = r1
        L_0x0049:
            if (r0 == 0) goto L_0x0058
            java.lang.reflect.Type[] r0 = r0.getLowerBounds()
            if (r0 == 0) goto L_0x0058
            java.lang.Object r0 = ne.C1192j.m0(r0)
            r1 = r0
            java.lang.reflect.Type r1 = (java.lang.reflect.Type) r1
        L_0x0058:
            if (r1 != 0) goto L_0x0062
            Le.g r10 = r10.g()
            java.lang.reflect.Type r1 = r10.getReturnType()
        L_0x0062:
            return r1
        L_0x0063:
            java.util.List r10 = r10.getParameters()
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            boolean r0 = r10 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0077
            r0 = r10
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0077
            goto L_0x0094
        L_0x0077:
            java.util.Iterator r10 = r10.iterator()
        L_0x007b:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0094
            java.lang.Object r0 = r10.next()
            He.m r0 = (He.m) r0
            Ke.X r0 = (Ke.X) r0
            Ke.r0 r0 = r0.f()
            boolean r0 = Ke.E0.h(r0)
            if (r0 == 0) goto L_0x007b
            r2 = r3
        L_0x0094:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r2)
            return r10
        L_0x0099:
            java.util.List r0 = r10.getParameters()
            int r4 = r0.size()
            boolean r5 = r10.isSuspend()
            int r5 = r5 + r4
            java.lang.Object r4 = r10.f3511i
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x00d9
            r4 = r0
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
            r6 = r2
        L_0x00bc:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x010d
            java.lang.Object r7 = r4.next()
            He.m r7 = (He.m) r7
            r8 = r7
            Ke.X r8 = (Ke.X) r8
            He.l r8 = r8.f
            He.l r9 = He.l.VALUE
            if (r8 != r9) goto L_0x00d6
            int r7 = r10.k(r7)
            goto L_0x00d7
        L_0x00d6:
            r7 = r2
        L_0x00d7:
            int r6 = r6 + r7
            goto L_0x00bc
        L_0x00d9:
            r10 = r0
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            boolean r4 = r10 instanceof java.util.Collection
            if (r4 == 0) goto L_0x00eb
            r4 = r10
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00eb
            r6 = r2
            goto L_0x010d
        L_0x00eb:
            java.util.Iterator r10 = r10.iterator()
            r6 = r2
        L_0x00f0:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto L_0x010d
            java.lang.Object r4 = r10.next()
            He.m r4 = (He.m) r4
            Ke.X r4 = (Ke.X) r4
            He.l r4 = r4.f
            He.l r7 = He.l.VALUE
            if (r4 != r7) goto L_0x00f0
            int r6 = r6 + 1
            if (r6 < 0) goto L_0x0109
            goto L_0x00f0
        L_0x0109:
            ne.C1195m.u0()
            throw r1
        L_0x010d:
            int r6 = r6 + 31
            int r6 = r6 / 32
            int r10 = r5 + r6
            int r10 = r10 + r3
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x011c:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x017f
            java.lang.Object r4 = r0.next()
            He.m r4 = (He.m) r4
            Ke.X r4 = (Ke.X) r4
            boolean r7 = r4.g()
            int r8 = r4.e
            if (r7 == 0) goto L_0x016e
            Ke.r0 r7 = r4.f()
            qf.c r9 = Ke.E0.f3489a
            Hf.x r7 = r7.d
            if (r7 == 0) goto L_0x0143
            boolean r7 = tf.C1305i.c(r7)
            if (r7 != r3) goto L_0x0143
            goto L_0x016e
        L_0x0143:
            Ke.r0 r4 = r4.f()
            Ke.x0 r7 = r4.e
            if (r7 == 0) goto L_0x0152
            java.lang.Object r9 = r7.invoke()
            java.lang.reflect.Type r9 = (java.lang.reflect.Type) r9
            goto L_0x0153
        L_0x0152:
            r9 = r1
        L_0x0153:
            if (r9 != 0) goto L_0x0167
            if (r7 == 0) goto L_0x015f
            java.lang.Object r7 = r7.invoke()
            java.lang.reflect.Type r7 = (java.lang.reflect.Type) r7
            r9 = r7
            goto L_0x0160
        L_0x015f:
            r9 = r1
        L_0x0160:
            if (r9 == 0) goto L_0x0163
            goto L_0x0167
        L_0x0163:
            java.lang.reflect.Type r9 = He.F.v(r4, r2)
        L_0x0167:
            java.lang.Object r4 = Ke.E0.e(r9)
            r10[r8] = r4
            goto L_0x011c
        L_0x016e:
            boolean r7 = r4.h()
            if (r7 == 0) goto L_0x011c
            Ke.r0 r4 = r4.f()
            java.lang.Object r4 = Ke.C0800s.f(r4)
            r10[r8] = r4
            goto L_0x011c
        L_0x017f:
            r0 = r2
        L_0x0180:
            if (r0 >= r6) goto L_0x018d
            int r1 = r5 + r0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r10[r1] = r3
            int r0 = r0 + 1
            goto L_0x0180
        L_0x018d:
            return r10
        L_0x018e:
            Qe.d r0 = r10.j()
            java.util.List r0 = r0.getTypeParameters()
            java.lang.String r1 = "getTypeParameters(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = ne.C1196n.w0(r0, r2)
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x01ac:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x01c4
            java.lang.Object r2 = r0.next()
            Qe.V r2 = (Qe.V) r2
            Ke.t0 r3 = new Ke.t0
            kotlin.jvm.internal.j.b(r2)
            r3.<init>(r10, r2)
            r1.add(r3)
            goto L_0x01ac
        L_0x01c4:
            return r1
        L_0x01c5:
            Ke.r0 r0 = new Ke.r0
            Qe.d r1 = r10.j()
            Hf.x r1 = r1.getReturnType()
            kotlin.jvm.internal.j.b(r1)
            Ke.p r2 = new Ke.p
            r3 = 6
            r2.<init>(r10, r3)
            r0.<init>(r1, r2)
            return r0
        L_0x01dc:
            Qe.d r0 = r10.j()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r4 = r10.q()
            if (r4 != 0) goto L_0x021c
            Te.u r4 = Ke.E0.g(r0)
            if (r4 == 0) goto L_0x0202
            Ke.X r5 = new Ke.X
            He.l r6 = He.l.INSTANCE
            Ke.q r7 = new Ke.q
            r7.<init>(r4, r2)
            r5.<init>(r10, r2, r6, r7)
            r1.add(r5)
            r4 = r3
            goto L_0x0203
        L_0x0202:
            r4 = r2
        L_0x0203:
            Te.u r5 = r0.H()
            if (r5 == 0) goto L_0x021d
            Ke.X r6 = new Ke.X
            int r7 = r4 + 1
            He.l r8 = He.l.EXTENSION_RECEIVER
            Ke.q r9 = new Ke.q
            r9.<init>(r5, r3)
            r6.<init>(r10, r4, r8, r9)
            r1.add(r6)
            r4 = r7
            goto L_0x021d
        L_0x021c:
            r4 = r2
        L_0x021d:
            java.util.List r5 = r0.B()
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
        L_0x0227:
            if (r2 >= r5) goto L_0x023e
            Ke.X r6 = new Ke.X
            int r7 = r4 + 1
            He.l r8 = He.l.VALUE
            Ke.r r9 = new Ke.r
            r9.<init>(r0, r2)
            r6.<init>(r10, r4, r8, r9)
            r1.add(r6)
            int r2 = r2 + 1
            r4 = r7
            goto L_0x0227
        L_0x023e:
            boolean r10 = r10.p()
            if (r10 == 0) goto L_0x0256
            boolean r10 = r0 instanceof bf.C0916a
            if (r10 == 0) goto L_0x0256
            int r10 = r1.size()
            if (r10 <= r3) goto L_0x0256
            Ke.f r10 = new Ke.f
            r10.<init>(r3)
            ne.C1199q.z0(r1, r10)
        L_0x0256:
            r1.trimToSize()
            return r1
        L_0x025a:
            Qe.d r10 = r10.j()
            java.util.ArrayList r10 = Ke.E0.d(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.C0798p.invoke():java.lang.Object");
    }
}
