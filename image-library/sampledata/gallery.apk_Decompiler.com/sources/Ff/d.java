package Ff;

import Ae.a;

public final class d implements a {
    public final /* synthetic */ int d;
    public final k e;

    public /* synthetic */ d(k kVar, int i2) {
        this.d = i2;
        this.e = kVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v54, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: Kf.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v63, resolved type: java.util.ArrayList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r12 = this;
            int r0 = r12.d
            java.lang.String r1 = "getConstructorList(...)"
            r2 = 10
            r3 = 0
            r4 = 0
            r5 = 1
            Ff.k r6 = r12.e
            switch(r0) {
                case 0: goto L_0x0344;
                case 1: goto L_0x02ca;
                case 2: goto L_0x02a4;
                case 3: goto L_0x022b;
                case 4: goto L_0x0026;
                case 5: goto L_0x0013;
                default: goto L_0x000e;
            }
        L_0x000e:
            java.util.List r12 = Qe.C0833x.c(r6)
            return r12
        L_0x0013:
            Df.n r12 = r6.f3389o
            java.lang.Object r12 = r12.f3358a
            Df.l r12 = (Df.l) r12
            Df.d r12 = r12.e
            Df.x r0 = r6.y
            java.util.ArrayList r12 = r12.q(r0)
            java.util.List r12 = ne.C1194l.k1(r12)
            return r12
        L_0x0026:
            Df.n r12 = r6.f3389o
            boolean r0 = r6.isInline()
            if (r0 != 0) goto L_0x0036
            boolean r0 = r6.l()
            if (r0 != 0) goto L_0x0036
            goto L_0x022a
        L_0x0036:
            lf.j r0 = r6.f3386h
            java.lang.Object r1 = r12.b
            nf.f r1 = (nf.C1209f) r1
            java.lang.Object r7 = r12.d
            B1.b r7 = (B1.b) r7
            Ef.b r8 = new Ef.b
            java.lang.Object r12 = r12.f3360h
            Df.H r12 = (Df.H) r12
            r8.<init>(r5, r12, r5)
            Ef.b r12 = new Ef.b
            r9 = 2
            r12.<init>(r5, r6, r9)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r9 = "<this>"
            kotlin.jvm.internal.j.e(r0, r9)
            java.lang.String r9 = "nameResolver"
            kotlin.jvm.internal.j.e(r1, r9)
            java.util.List r9 = r0.f4843C
            int r9 = r9.size()
            if (r9 <= 0) goto L_0x015a
            java.util.List r12 = r0.f4843C
            java.lang.String r9 = "getMultiFieldValueClassUnderlyingNameList(...)"
            kotlin.jvm.internal.j.d(r12, r9)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r9 = new java.util.ArrayList
            int r10 = ne.C1196n.w0(r12, r2)
            r9.<init>(r10)
            java.util.Iterator r12 = r12.iterator()
        L_0x007b:
            boolean r10 = r12.hasNext()
            if (r10 == 0) goto L_0x0096
            java.lang.Object r10 = r12.next()
            java.lang.Integer r10 = (java.lang.Integer) r10
            kotlin.jvm.internal.j.b(r10)
            int r10 = r10.intValue()
            qf.g r10 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r1, r10)
            r9.add(r10)
            goto L_0x007b
        L_0x0096:
            java.util.List r12 = r0.f4844F
            int r12 = r12.size()
            java.util.List r10 = r0.E
            int r10 = r10.size()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            me.i r11 = new me.i
            r11.<init>(r12, r10)
            int r12 = r9.size()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            me.i r10 = new me.i
            r10.<init>(r12, r3)
            boolean r12 = r11.equals(r10)
            if (r12 == 0) goto L_0x00f3
            java.util.List r12 = r0.f4844F
            java.lang.String r0 = "getMultiFieldValueClassUnderlyingTypeIdList(...)"
            kotlin.jvm.internal.j.d(r12, r0)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = ne.C1196n.w0(r12, r2)
            r0.<init>(r1)
            java.util.Iterator r12 = r12.iterator()
        L_0x00d8:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0108
            java.lang.Object r1 = r12.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.intValue()
            lf.Q r1 = r7.g(r1)
            r0.add(r1)
            goto L_0x00d8
        L_0x00f3:
            int r12 = r9.size()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            me.i r7 = new me.i
            r7.<init>(r3, r12)
            boolean r12 = r11.equals(r7)
            if (r12 == 0) goto L_0x0137
            java.util.List r0 = r0.E
        L_0x0108:
            kotlin.jvm.internal.j.b(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r12 = new java.util.ArrayList
            int r1 = ne.C1196n.w0(r0, r2)
            r12.<init>(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x011a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x012c
            java.lang.Object r1 = r0.next()
            java.lang.Object r1 = r8.invoke(r1)
            r12.add(r1)
            goto L_0x011a
        L_0x012c:
            Qe.D r0 = new Qe.D
            java.util.ArrayList r12 = ne.C1194l.r1(r9, r12)
            r0.<init>(r12)
            goto L_0x01bf
        L_0x0137:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "class "
            r2.<init>(r3)
            int r0 = r0.f4850h
            qf.g r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r1, r0)
            r2.append(r0)
            java.lang.String r0 = " has illegal multi-field value class representation"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        L_0x015a:
            int r2 = r0.f
            r3 = 8
            r2 = r2 & r3
            if (r2 != r3) goto L_0x01be
            int r2 = r0.z
            qf.g r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r1, r2)
            int r3 = r0.f
            r9 = r3 & 16
            r10 = 16
            if (r9 != r10) goto L_0x0172
            lf.Q r3 = r0.f4842A
            goto L_0x017f
        L_0x0172:
            r9 = 32
            r3 = r3 & r9
            if (r3 != r9) goto L_0x017e
            int r3 = r0.B
            lf.Q r3 = r7.g(r3)
            goto L_0x017f
        L_0x017e:
            r3 = r4
        L_0x017f:
            if (r3 == 0) goto L_0x0189
            java.lang.Object r3 = r8.invoke(r3)
            Kf.e r3 = (Kf.e) r3
            if (r3 != 0) goto L_0x0192
        L_0x0189:
            java.lang.Object r12 = r12.invoke(r2)
            r3 = r12
            Kf.e r3 = (Kf.e) r3
            if (r3 == 0) goto L_0x0198
        L_0x0192:
            Qe.w r0 = new Qe.w
            r0.<init>(r2, r3)
            goto L_0x01bf
        L_0x0198:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "cannot determine underlying type for value class "
            r3.<init>(r4)
            int r0 = r0.f4850h
            qf.g r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r1, r0)
            r3.append(r0)
            java.lang.String r0 = " with property "
            r3.append(r0)
            r3.append(r2)
            java.lang.String r0 = r3.toString()
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        L_0x01be:
            r0 = r4
        L_0x01bf:
            if (r0 == 0) goto L_0x01c3
            r4 = r0
            goto L_0x022a
        L_0x01c3:
            nf.a r12 = r6.f3387i
            r0 = 5
            boolean r12 = r12.a(r5, r0, r5)
            if (r12 != 0) goto L_0x022a
            Te.i r12 = r6.y()
            if (r12 == 0) goto L_0x0212
            Te.t r12 = (Te.t) r12
            java.util.List r12 = r12.B()
            java.lang.String r0 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r12, r0)
            java.lang.Object r12 = ne.C1194l.L0(r12)
            Te.Q r12 = (Te.Q) r12
            Te.m r12 = (Te.C0852m) r12
            qf.g r12 = r12.getName()
            java.lang.String r0 = "getName(...)"
            kotlin.jvm.internal.j.d(r12, r0)
            Hf.B r0 = r6.p0(r12)
            if (r0 == 0) goto L_0x01fa
            Qe.w r4 = new Qe.w
            r4.<init>(r12, r0)
            goto L_0x022a
        L_0x01fa:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Value class has no underlying property: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        L_0x0212:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Inline class has no primary constructor: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            r12.<init>(r0)
            throw r12
        L_0x022a:
            return r4
        L_0x022b:
            Qe.A r12 = r6.l
            Qe.A r0 = Qe.A.SEALED
            if (r12 == r0) goto L_0x0232
            goto L_0x027a
        L_0x0232:
            lf.j r1 = r6.f3386h
            java.util.List r1 = r1.f4855x
            kotlin.jvm.internal.j.b(r1)
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0278
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.Iterator r0 = r1.iterator()
        L_0x024d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x02a3
            java.lang.Object r1 = r0.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            Df.n r2 = r6.f3389o
            java.lang.Object r3 = r2.f3358a
            Df.l r3 = (Df.l) r3
            java.lang.Object r2 = r2.b
            nf.f r2 = (nf.C1209f) r2
            kotlin.jvm.internal.j.b(r1)
            int r1 = r1.intValue()
            qf.b r1 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(r2, r1)
            Qe.f r1 = r3.b(r1)
            if (r1 == 0) goto L_0x024d
            r12.add(r1)
            goto L_0x024d
        L_0x0278:
            if (r12 == r0) goto L_0x027d
        L_0x027a:
            ne.t r12 = ne.C1202t.d
            goto L_0x02a3
        L_0x027d:
            java.util.LinkedHashSet r12 = new java.util.LinkedHashSet
            r12.<init>()
            Qe.l r0 = r6.t
            boolean r1 = r0 instanceof Qe.H
            if (r1 == 0) goto L_0x0291
            Qe.H r0 = (Qe.H) r0
            Af.p r0 = r0.A()
            tf.C1312p.c(r6, r12, r0, r3)
        L_0x0291:
            Af.p r0 = r6.M()
            tf.C1312p.c(r6, r12, r0, r5)
            tf.j r0 = new tf.j
            r0.<init>(r5)
            java.util.List r12 = ne.C1194l.g1(r12, r0)
            java.util.Collection r12 = (java.util.Collection) r12
        L_0x02a3:
            return r12
        L_0x02a4:
            lf.j r12 = r6.f3386h
            int r0 = r12.f
            r1 = 4
            r0 = r0 & r1
            if (r0 != r1) goto L_0x02c9
            Df.n r0 = r6.f3389o
            java.lang.Object r0 = r0.b
            nf.f r0 = (nf.C1209f) r0
            int r12 = r12.f4851i
            qf.g r12 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r0, r12)
            Ff.h r0 = r6.e0()
            Ye.c r1 = Ye.c.FROM_DESERIALIZATION
            Qe.i r12 = r0.c(r12, r1)
            boolean r0 = r12 instanceof Qe.C0816f
            if (r0 == 0) goto L_0x02c9
            r4 = r12
            Qe.f r4 = (Qe.C0816f) r4
        L_0x02c9:
            return r4
        L_0x02ca:
            Df.n r12 = r6.f3389o
            lf.j r0 = r6.f3386h
            java.util.List r0 = r0.s
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x02de:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x02fd
            java.lang.Object r4 = r0.next()
            r5 = r4
            lf.l r5 = (lf.C1159l) r5
            nf.b r7 = nf.C1208e.n
            int r5 = r5.g
            java.lang.Boolean r5 = r7.c(r5)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x02de
            r1.add(r4)
            goto L_0x02de
        L_0x02fd:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r1, r2)
            r0.<init>(r2)
            java.util.Iterator r1 = r1.iterator()
        L_0x030a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0325
            java.lang.Object r2 = r1.next()
            lf.l r2 = (lf.C1159l) r2
            java.lang.Object r4 = r12.f3361i
            Df.w r4 = (Df.w) r4
            kotlin.jvm.internal.j.b(r2)
            Ff.c r2 = r4.d(r2, r3)
            r0.add(r2)
            goto L_0x030a
        L_0x0325:
            Te.i r1 = r6.y()
            java.util.List r1 = ne.C1195m.r0(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r0 = ne.C1194l.X0(r1, r0)
            java.lang.Object r12 = r12.f3358a
            Df.l r12 = (Df.l) r12
            Se.b r12 = r12.n
            java.util.Collection r12 = r12.a(r6)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r12 = ne.C1194l.X0(r12, r0)
            return r12
        L_0x0344:
            Ff.k r6 = r12.e
            Qe.g r12 = r6.n
            boolean r0 = r12.a()
            if (r0 == 0) goto L_0x03ac
            tf.d r5 = new tf.d
            r9 = 1
            Qe.c r10 = Qe.C0813c.DECLARATION
            r7 = 0
            Re.f r8 = Re.g.f3692a
            Qe.S r11 = Qe.Q.f3662a
            r5.<init>(r6, r7, r8, r9, r10, r11)
            java.util.List r0 = java.util.Collections.EMPTY_LIST
            int r1 = tf.C1301e.f5137a
            Qe.g r1 = Qe.C0817g.ENUM_CLASS
            if (r12 == r1) goto L_0x0397
            boolean r12 = r12.a()
            if (r12 == 0) goto L_0x036a
            goto L_0x0397
        L_0x036a:
            boolean r12 = tf.C1301e.q(r6)
            if (r12 == 0) goto L_0x037b
            Qe.p r12 = Qe.C0827q.f3675a
            if (r12 == 0) goto L_0x0375
            goto L_0x039b
        L_0x0375:
            r12 = 51
            tf.C1301e.a(r12)
            throw r4
        L_0x037b:
            boolean r12 = tf.C1301e.k(r6)
            if (r12 == 0) goto L_0x038c
            Qe.p r12 = Qe.C0827q.f3679j
            if (r12 == 0) goto L_0x0386
            goto L_0x039b
        L_0x0386:
            r12 = 52
            tf.C1301e.a(r12)
            throw r4
        L_0x038c:
            Qe.p r12 = Qe.C0827q.e
            if (r12 == 0) goto L_0x0391
            goto L_0x039b
        L_0x0391:
            r12 = 53
            tf.C1301e.a(r12)
            throw r4
        L_0x0397:
            Qe.p r12 = Qe.C0827q.f3675a
            if (r12 == 0) goto L_0x03a6
        L_0x039b:
            r5.S0(r0, r12)
            Hf.B r12 = r6.i()
            r5.k = r12
            r4 = r5
            goto L_0x03e4
        L_0x03a6:
            r12 = 49
            tf.C1301e.a(r12)
            throw r4
        L_0x03ac:
            lf.j r12 = r6.f3386h
            java.util.List r12 = r12.s
            kotlin.jvm.internal.j.d(r12, r1)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Iterator r12 = r12.iterator()
        L_0x03b9:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x03d5
            java.lang.Object r0 = r12.next()
            r1 = r0
            lf.l r1 = (lf.C1159l) r1
            nf.b r2 = nf.C1208e.n
            int r1 = r1.g
            java.lang.Boolean r1 = r2.c(r1)
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x03b9
            goto L_0x03d6
        L_0x03d5:
            r0 = r4
        L_0x03d6:
            lf.l r0 = (lf.C1159l) r0
            if (r0 == 0) goto L_0x03e4
            Df.n r12 = r6.f3389o
            java.lang.Object r12 = r12.f3361i
            Df.w r12 = (Df.w) r12
            Ff.c r4 = r12.d(r0, r5)
        L_0x03e4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.d.invoke():java.lang.Object");
    }
}
