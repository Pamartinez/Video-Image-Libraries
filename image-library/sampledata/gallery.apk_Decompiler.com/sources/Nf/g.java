package Nf;

import Ae.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements b {
    public static final g e = new g(0);
    public static final g f = new g(1);
    public static final g g = new g(2);

    /* renamed from: h  reason: collision with root package name */
    public static final g f3584h = new g(3);

    /* renamed from: i  reason: collision with root package name */
    public static final g f3585i = new g(4);

    /* renamed from: j  reason: collision with root package name */
    public static final g f3586j = new g(5);
    public static final g k = new g(6);
    public static final g l = new g(7);
    public static final g m = new g(8);
    public final /* synthetic */ int d;

    public /* synthetic */ g(int i2) {
        this.d = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        if (r7 == false) goto L_0x00a4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a7 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r8) {
        /*
            r7 = this;
            int r7 = r7.d
            r0 = 1
            r1 = 0
            java.lang.String r2 = "$this$Checks"
            r3 = 0
            java.lang.String r4 = "<this>"
            switch(r7) {
                case 0: goto L_0x0213;
                case 1: goto L_0x020d;
                case 2: goto L_0x0207;
                case 3: goto L_0x01e1;
                case 4: goto L_0x00aa;
                case 5: goto L_0x0034;
                case 6: goto L_0x0026;
                case 7: goto L_0x0018;
                default: goto L_0x000c;
            }
        L_0x000c:
            Ne.i r8 = (Ne.i) r8
            Nf.u r7 = Nf.u.f3604c
            kotlin.jvm.internal.j.e(r8, r4)
            Hf.B r7 = r8.w()
            return r7
        L_0x0018:
            Ne.i r8 = (Ne.i) r8
            Nf.t r7 = Nf.t.f3603c
            kotlin.jvm.internal.j.e(r8, r4)
            Ne.l r7 = Ne.l.INT
            Hf.B r7 = r8.s(r7)
            return r7
        L_0x0026:
            Ne.i r8 = (Ne.i) r8
            Nf.s r7 = Nf.s.f3602c
            kotlin.jvm.internal.j.e(r8, r4)
            Ne.l r7 = Ne.l.BOOLEAN
            Hf.B r7 = r8.s(r7)
            return r7
        L_0x0034:
            Qe.v r8 = (Qe.C0831v) r8
            java.util.List r7 = Nf.q.f3594a
            kotlin.jvm.internal.j.e(r8, r2)
            Te.u r7 = r8.E()
            if (r7 != 0) goto L_0x0045
            Te.u r7 = r8.H()
        L_0x0045:
            if (r7 == 0) goto L_0x00a4
            Hf.x r2 = r8.getReturnType()
            if (r2 == 0) goto L_0x0058
            Hf.x r4 = r7.getType()
            If.l r5 = If.d.f3459a
            boolean r2 = r5.b(r2, r4)
            goto L_0x0059
        L_0x0058:
            r2 = r1
        L_0x0059:
            if (r2 != 0) goto L_0x00a5
            Bf.e r7 = r7.E0()
            java.lang.String r2 = "getValue(...)"
            kotlin.jvm.internal.j.d(r7, r2)
            boolean r2 = r7 instanceof Bf.d
            if (r2 != 0) goto L_0x006a
        L_0x0068:
            r7 = r1
            goto L_0x00a1
        L_0x006a:
            Bf.d r7 = (Bf.d) r7
            Qe.f r7 = r7.d
            boolean r2 = r7.b0()
            if (r2 != 0) goto L_0x0075
            goto L_0x0068
        L_0x0075:
            qf.b r2 = xf.C1353d.f(r7)
            if (r2 != 0) goto L_0x007c
            goto L_0x0068
        L_0x007c:
            Qe.C r7 = xf.C1353d.j(r7)
            Qe.i r7 = Qe.C0833x.e(r7, r2)
            boolean r2 = r7 instanceof Qe.U
            if (r2 == 0) goto L_0x008b
            Qe.U r7 = (Qe.U) r7
            goto L_0x008c
        L_0x008b:
            r7 = r3
        L_0x008c:
            if (r7 != 0) goto L_0x008f
            goto L_0x0068
        L_0x008f:
            Hf.x r8 = r8.getReturnType()
            if (r8 == 0) goto L_0x0068
            Ff.w r7 = (Ff.w) r7
            Hf.B r7 = r7.F0()
            If.l r2 = If.d.f3459a
            boolean r7 = r2.b(r8, r7)
        L_0x00a1:
            if (r7 == 0) goto L_0x00a4
            goto L_0x00a5
        L_0x00a4:
            r0 = r1
        L_0x00a5:
            if (r0 != 0) goto L_0x00a9
            java.lang.String r3 = "receiver must be a supertype of the return type"
        L_0x00a9:
            return r3
        L_0x00aa:
            Qe.v r8 = (Qe.C0831v) r8
            java.util.List r7 = Nf.q.f3594a
            kotlin.jvm.internal.j.e(r8, r2)
            Qe.l r7 = r8.g()
            java.lang.String r2 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r7, r2)
            boolean r4 = r7 instanceof Qe.C0816f
            if (r4 == 0) goto L_0x00cc
            Qe.f r7 = (Qe.C0816f) r7
            qf.g r4 = Ne.i.e
            qf.e r4 = Ne.p.f3565a
            boolean r7 = Ne.i.b(r7, r4)
            if (r7 == 0) goto L_0x00cc
            goto L_0x01e0
        L_0x00cc:
            java.util.Collection r7 = r8.h()
            java.lang.String r4 = "getOverriddenDescriptors(...)"
            kotlin.jvm.internal.j.d(r7, r4)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            r4 = r7
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00e1
            goto L_0x010a
        L_0x00e1:
            java.util.Iterator r7 = r7.iterator()
        L_0x00e5:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L_0x010a
            java.lang.Object r4 = r7.next()
            Qe.v r4 = (Qe.C0831v) r4
            Qe.l r4 = r4.g()
            kotlin.jvm.internal.j.d(r4, r2)
            boolean r5 = r4 instanceof Qe.C0816f
            if (r5 == 0) goto L_0x00e5
            Qe.f r4 = (Qe.C0816f) r4
            qf.g r5 = Ne.i.e
            qf.e r5 = Ne.p.f3565a
            boolean r4 = Ne.i.b(r4, r5)
            if (r4 == 0) goto L_0x00e5
            goto L_0x01e0
        L_0x010a:
            Qe.l r7 = r8.g()
            boolean r4 = r7 instanceof Qe.C0816f
            if (r4 == 0) goto L_0x0115
            Qe.f r7 = (Qe.C0816f) r7
            goto L_0x0116
        L_0x0115:
            r7 = r3
        L_0x0116:
            if (r7 == 0) goto L_0x018f
            boolean r4 = tf.C1305i.f(r7)
            if (r4 == 0) goto L_0x011f
            goto L_0x0120
        L_0x011f:
            r7 = r3
        L_0x0120:
            if (r7 == 0) goto L_0x018f
            Hf.B r7 = r7.i()
            if (r7 == 0) goto L_0x018f
            Hf.c0 r7 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.M(r7)
            if (r7 != 0) goto L_0x012f
            goto L_0x018f
        L_0x012f:
            Hf.x r4 = r8.getReturnType()
            if (r4 != 0) goto L_0x0136
            goto L_0x018f
        L_0x0136:
            r5 = r8
            Te.m r5 = (Te.C0852m) r5
            qf.g r5 = r5.getName()
            qf.g r6 = Nf.r.d
            boolean r5 = kotlin.jvm.internal.j.a(r5, r6)
            if (r5 == 0) goto L_0x018f
            qf.g r5 = Ne.i.e
            qf.e r5 = Ne.p.f3568h
            boolean r5 = Ne.i.B(r4, r5)
            if (r5 != 0) goto L_0x0155
            boolean r4 = Ne.i.E(r4)
            if (r4 == 0) goto L_0x018f
        L_0x0155:
            java.util.List r4 = r8.B()
            int r4 = r4.size()
            if (r4 != r0) goto L_0x018f
            java.util.List r0 = r8.B()
            java.lang.Object r0 = r0.get(r1)
            Te.Q r0 = (Te.Q) r0
            Te.S r0 = (Te.S) r0
            Hf.x r0 = r0.getType()
            java.lang.String r1 = "getType(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            Hf.c0 r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.M(r0)
            boolean r7 = kotlin.jvm.internal.j.a(r0, r7)
            if (r7 == 0) goto L_0x018f
            java.util.List r7 = r8.i0()
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x018f
            Te.u r7 = r8.H()
            if (r7 != 0) goto L_0x018f
            goto L_0x01e0
        L_0x018f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "must override ''equals()'' in Any"
            r7.<init>(r0)
            Qe.l r0 = r8.g()
            kotlin.jvm.internal.j.d(r0, r2)
            boolean r0 = tf.C1305i.f(r0)
            if (r0 == 0) goto L_0x01d7
            sf.j r0 = sf.C1283j.d
            Qe.l r8 = r8.g()
            java.lang.String r1 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r8, r1)
            Qe.f r8 = (Qe.C0816f) r8
            Hf.B r8 = r8.i()
            java.lang.String r1 = "getDefaultType(...)"
            kotlin.jvm.internal.j.d(r8, r1)
            Hf.c0 r8 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.M(r8)
            java.lang.String r8 = r0.Y(r8)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = " or define ''equals(other: "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r8 = "): Boolean''"
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.append(r8)
        L_0x01d7:
            java.lang.String r3 = r7.toString()
            java.lang.String r7 = "toString(...)"
            kotlin.jvm.internal.j.d(r3, r7)
        L_0x01e0:
            return r3
        L_0x01e1:
            Qe.v r8 = (Qe.C0831v) r8
            java.util.List r7 = Nf.q.f3594a
            kotlin.jvm.internal.j.e(r8, r2)
            java.util.List r7 = r8.B()
            java.lang.String r8 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            java.lang.Object r7 = ne.C1194l.U0(r7)
            Te.Q r7 = (Te.Q) r7
            if (r7 == 0) goto L_0x0204
            boolean r8 = xf.C1353d.a(r7)
            if (r8 != 0) goto L_0x0204
            Hf.x r7 = r7.n
            if (r7 != 0) goto L_0x0204
            goto L_0x0206
        L_0x0204:
            java.lang.String r3 = "last parameter should not have a default value or be a vararg"
        L_0x0206:
            return r3
        L_0x0207:
            Qe.v r8 = (Qe.C0831v) r8
            kotlin.jvm.internal.j.e(r8, r4)
            return r3
        L_0x020d:
            Qe.v r8 = (Qe.C0831v) r8
            kotlin.jvm.internal.j.e(r8, r4)
            return r3
        L_0x0213:
            Qe.v r8 = (Qe.C0831v) r8
            kotlin.jvm.internal.j.e(r8, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: Nf.g.invoke(java.lang.Object):java.lang.Object");
    }
}
