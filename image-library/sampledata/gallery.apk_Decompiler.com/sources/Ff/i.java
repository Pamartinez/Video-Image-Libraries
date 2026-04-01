package Ff;

import Hf.C0753b;
import Qe.C0816f;
import Qe.C0819i;
import Qe.S;
import Te.C0841b;
import cf.C0922a;
import df.C0946i;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends C0753b {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3385c = 0;
    public final Gf.i d;
    public final /* synthetic */ C0841b e;

    /* JADX WARNING: type inference failed for: r4v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public i(df.C0946i r4) {
        /*
            r3 = this;
            r0 = 1
            r3.f3385c = r0
            r3.e = r4
            B0.a r0 = r4.m
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            Gf.p r1 = r1.f4006a
            r3.<init>(r1)
            java.lang.Object r0 = r0.d
            cf.a r0 = (cf.C0922a) r0
            Gf.p r0 = r0.f4006a
            df.g r1 = new df.g
            r2 = 2
            r1.<init>(r4, r2)
            Gf.m r0 = (Gf.m) r0
            r0.getClass()
            Gf.i r4 = new Gf.i
            r4.<init>(r0, r1)
            r3.d = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.i.<init>(df.i):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v31, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: ne.t} */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0118, code lost:
        if (r6 == null) goto L_0x011a;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0276  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection b() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.f3385c
            java.lang.String r2 = "<this>"
            Te.b r0 = r0.e
            r3 = 10
            switch(r1) {
                case 0: goto L_0x02d7;
                default: goto L_0x000d;
            }
        L_0x000d:
            df.i r0 = (df.C0946i) r0
            B0.a r8 = r0.m
            We.o r1 = r0.k
            java.lang.Class r1 = r1.f3891a
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            boolean r6 = kotlin.jvm.internal.j.a(r1, r5)
            r7 = 2
            ne.t r12 = ne.C1202t.d
            if (r6 == 0) goto L_0x0022
            r5 = r12
            goto L_0x006d
        L_0x0022:
            D0.c r6 = new D0.c
            r6.<init>((int) r7)
            java.lang.reflect.Type r9 = r1.getGenericSuperclass()
            if (r9 != 0) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r5 = r9
        L_0x002f:
            r6.a(r5)
            java.lang.reflect.Type[] r1 = r1.getGenericInterfaces()
            r6.b(r1)
            java.util.ArrayList r1 = r6.d
            int r5 = r1.size()
            java.lang.reflect.Type[] r5 = new java.lang.reflect.Type[r5]
            java.lang.Object[] r1 = r1.toArray(r5)
            java.util.List r1 = ne.C1195m.q0(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r5 = new java.util.ArrayList
            int r6 = ne.C1196n.w0(r1, r3)
            r5.<init>(r6)
            java.util.Iterator r1 = r1.iterator()
        L_0x0058:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x006d
            java.lang.Object r6 = r1.next()
            java.lang.reflect.Type r6 = (java.lang.reflect.Type) r6
            We.q r9 = new We.q
            r9.<init>(r6)
            r5.add(r9)
            goto L_0x0058
        L_0x006d:
            java.util.ArrayList r1 = new java.util.ArrayList
            int r6 = r5.size()
            r1.<init>(r6)
            java.util.ArrayList r15 = new java.util.ArrayList
            r11 = 0
            r15.<init>(r11)
            cf.c r6 = r0.f4250x
            qf.c r9 = Ze.x.n
            java.lang.String r10 = "PURELY_IMPLEMENTS_ANNOTATION"
            kotlin.jvm.internal.j.d(r9, r10)
            Re.b r6 = r6.m(r9)
            r9 = 1
            if (r6 != 0) goto L_0x008f
        L_0x008c:
            r4 = 0
            goto L_0x00f6
        L_0x008f:
            java.util.Map r6 = r6.a()
            java.util.Collection r6 = r6.values()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.lang.Object r6 = ne.C1194l.c1(r6)
            boolean r10 = r6 instanceof vf.C1345y
            if (r10 == 0) goto L_0x00a4
            vf.y r6 = (vf.C1345y) r6
            goto L_0x00a5
        L_0x00a4:
            r6 = 0
        L_0x00a5:
            if (r6 == 0) goto L_0x008c
            java.lang.Object r6 = r6.f5158a
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L_0x00ae
            goto L_0x008c
        L_0x00ae:
            qf.k r10 = qf.C1244k.BEGINNING
            r13 = r11
        L_0x00b1:
            int r14 = r6.length()
            if (r13 >= r14) goto L_0x00ed
            char r14 = r6.charAt(r13)
            int[] r16 = qf.C1239f.f5039a
            int r17 = r10.ordinal()
            r4 = r16[r17]
            if (r4 == r9) goto L_0x00df
            if (r4 == r7) goto L_0x00df
            r7 = 3
            if (r4 != r7) goto L_0x00d9
            r4 = 46
            if (r14 != r4) goto L_0x00d2
            qf.k r4 = qf.C1244k.AFTER_DOT
        L_0x00d0:
            r10 = r4
            goto L_0x00e9
        L_0x00d2:
            boolean r4 = java.lang.Character.isJavaIdentifierPart(r14)
            if (r4 != 0) goto L_0x00e9
            goto L_0x008c
        L_0x00d9:
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        L_0x00df:
            boolean r4 = java.lang.Character.isJavaIdentifierStart(r14)
            if (r4 != 0) goto L_0x00e6
            goto L_0x008c
        L_0x00e6:
            qf.k r4 = qf.C1244k.MIDDLE
            goto L_0x00d0
        L_0x00e9:
            int r13 = r13 + 1
            r7 = 2
            goto L_0x00b1
        L_0x00ed:
            qf.k r4 = qf.C1244k.AFTER_DOT
            if (r10 == r4) goto L_0x008c
            qf.c r4 = new qf.c
            r4.<init>((java.lang.String) r6)
        L_0x00f6:
            if (r4 == 0) goto L_0x0107
            boolean r6 = r4.d()
            if (r6 != 0) goto L_0x0107
            qf.g r6 = Ne.q.k
            boolean r6 = r4.h(r6)
            if (r6 == 0) goto L_0x0107
            goto L_0x0108
        L_0x0107:
            r4 = 0
        L_0x0108:
            if (r4 != 0) goto L_0x011d
            java.util.LinkedHashMap r6 = Ze.j.f3945a
            qf.c r6 = xf.C1353d.g(r0)
            java.util.Map r7 = Ze.j.b
            java.lang.Object r6 = r7.get(r6)
            qf.c r6 = (qf.C1236c) r6
            if (r6 != 0) goto L_0x011e
        L_0x011a:
            r2 = 0
            goto L_0x01e4
        L_0x011d:
            r6 = r4
        L_0x011e:
            java.lang.Object r7 = r8.d
            cf.a r7 = (cf.C0922a) r7
            Qe.C r7 = r7.f4011o
            Ye.c r10 = Ye.c.FROM_JAVA_LOADER
            int r13 = xf.C1353d.f5167a
            kotlin.jvm.internal.j.e(r7, r2)
            java.lang.String r2 = "location"
            kotlin.jvm.internal.j.e(r10, r2)
            r6.d()
            qf.c r2 = r6.e()
            Qe.L r2 = r7.n0(r2)
            Te.w r2 = (Te.w) r2
            Af.l r2 = r2.k
            qf.g r6 = r6.f()
            java.lang.String r7 = "shortName(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            Qe.i r2 = r2.c(r6, r10)
            boolean r6 = r2 instanceof Qe.C0816f
            if (r6 == 0) goto L_0x0153
            Qe.f r2 = (Qe.C0816f) r2
            goto L_0x0154
        L_0x0153:
            r2 = 0
        L_0x0154:
            if (r2 != 0) goto L_0x0157
            goto L_0x011a
        L_0x0157:
            Hf.M r6 = r2.q()
            java.util.List r6 = r6.getParameters()
            int r6 = r6.size()
            Ff.i r7 = r0.s
            java.util.List r7 = r7.getParameters()
            java.lang.String r10 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r7, r10)
            int r10 = r7.size()
            if (r10 != r6) goto L_0x019e
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r4 = new java.util.ArrayList
            int r6 = ne.C1196n.w0(r7, r3)
            r4.<init>(r6)
            java.util.Iterator r6 = r7.iterator()
        L_0x0183:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x01d9
            java.lang.Object r7 = r6.next()
            Qe.V r7 = (Qe.V) r7
            Hf.G r9 = new Hf.G
            Hf.d0 r10 = Hf.d0.INVARIANT
            Hf.B r7 = r7.i()
            r9.<init>(r7, r10)
            r4.add(r9)
            goto L_0x0183
        L_0x019e:
            if (r10 != r9) goto L_0x011a
            if (r6 <= r9) goto L_0x011a
            if (r4 != 0) goto L_0x011a
            Hf.G r4 = new Hf.G
            Hf.d0 r10 = Hf.d0.INVARIANT
            java.lang.Object r7 = ne.C1194l.b1(r7)
            Qe.V r7 = (Qe.V) r7
            Hf.B r7 = r7.i()
            r4.<init>(r7, r10)
            Ge.e r7 = new Ge.e
            r7.<init>(r9, r6, r9)
            java.util.ArrayList r6 = new java.util.ArrayList
            int r9 = ne.C1196n.w0(r7, r3)
            r6.<init>(r9)
            java.util.Iterator r7 = r7.iterator()
        L_0x01c7:
            r9 = r7
            Ge.d r9 = (Ge.d) r9
            boolean r9 = r9.f
            if (r9 == 0) goto L_0x01d8
            r9 = r7
            ne.y r9 = (ne.y) r9
            r9.nextInt()
            r6.add(r4)
            goto L_0x01c7
        L_0x01d8:
            r4 = r6
        L_0x01d9:
            D0.e r6 = Hf.I.e
            r6.getClass()
            Hf.I r6 = Hf.I.f
            Hf.B r2 = Hf.C0754c.t(r6, r2, r4)
        L_0x01e4:
            java.util.Iterator r4 = r5.iterator()
        L_0x01e8:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x025d
            java.lang.Object r5 = r4.next()
            r13 = r5
            We.q r13 = (We.q) r13
            java.lang.Object r5 = r8.f34h
            A0.l r5 = (A0.l) r5
            Hf.Y r6 = Hf.Y.SUPERTYPE
            r7 = 7
            r14 = 0
            ef.a r6 = a.C0068a.Y(r6, r11, r14, r7)
            Hf.x r16 = r5.p(r13, r6)
            java.lang.Object r5 = r8.d
            cf.a r5 = (cf.C0922a) r5
            hf.c r5 = r5.r
            r5.getClass()
            Jd.b r10 = new Jd.b
            Ze.a r9 = Ze.C0894a.TYPE_USE
            r6 = r5
            r5 = r10
            r10 = 1
            r7 = r6
            r6 = 0
            r17 = r7
            r7 = 0
            r5.<init>(r6, r7, r8, r9, r10)
            r6 = r13
            r13 = 0
            r7 = r14
            r14 = 0
            r10 = r5
            r5 = r11
            r11 = r16
            r9 = r17
            Hf.x r16 = r9.a(r10, r11, r12, r13, r14)
            if (r16 != 0) goto L_0x022e
            goto L_0x0230
        L_0x022e:
            r11 = r16
        L_0x0230:
            Hf.M r9 = r11.s0()
            Qe.i r9 = r9.g()
            boolean r9 = r9 instanceof Qe.G
            if (r9 == 0) goto L_0x023f
            r15.add(r6)
        L_0x023f:
            Hf.M r6 = r11.s0()
            if (r2 == 0) goto L_0x024a
            Hf.M r14 = r2.s0()
            goto L_0x024b
        L_0x024a:
            r14 = r7
        L_0x024b:
            boolean r6 = kotlin.jvm.internal.j.a(r6, r14)
            if (r6 == 0) goto L_0x0253
        L_0x0251:
            r11 = r5
            goto L_0x01e8
        L_0x0253:
            boolean r6 = Ne.i.x(r11)
            if (r6 != 0) goto L_0x0251
            r1.add(r11)
            goto L_0x0251
        L_0x025d:
            r7 = 0
            Qe.f r4 = r0.l
            if (r4 == 0) goto L_0x0276
            Hf.H r5 = Gd.a.n(r4, r0)
            Hf.X r6 = new Hf.X
            r6.<init>(r5)
            Hf.B r4 = r4.i()
            Hf.d0 r5 = Hf.d0.INVARIANT
            Hf.x r4 = r6.i(r4, r5)
            goto L_0x0277
        L_0x0276:
            r4 = r7
        L_0x0277:
            Qf.k.a(r1, r4)
            Qf.k.a(r1, r2)
            boolean r2 = r15.isEmpty()
            if (r2 != 0) goto L_0x02b6
            java.lang.Object r2 = r8.d
            cf.a r2 = (cf.C0922a) r2
            Df.p r2 = r2.f
            java.util.ArrayList r4 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r15, r3)
            r4.<init>(r3)
            java.util.Iterator r3 = r15.iterator()
        L_0x0296:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x02b3
            java.lang.Object r5 = r3.next()
            gf.d r5 = (gf.C1073d) r5
            java.lang.String r6 = "null cannot be cast to non-null type org.jetbrains.kotlin.load.java.structure.JavaClassifierType"
            kotlin.jvm.internal.j.c(r5, r6)
            We.q r5 = (We.q) r5
            java.lang.reflect.Type r5 = r5.f3892a
            java.lang.String r5 = r5.toString()
            r4.add(r5)
            goto L_0x0296
        L_0x02b3:
            r2.a(r0, r4)
        L_0x02b6:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x02c3
            java.util.List r0 = ne.C1194l.k1(r1)
        L_0x02c0:
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x02d6
        L_0x02c3:
            java.lang.Object r0 = r8.d
            cf.a r0 = (cf.C0922a) r0
            Qe.C r0 = r0.f4011o
            Ne.i r0 = r0.f()
            Hf.B r0 = r0.e()
            java.util.List r0 = o1.C0246a.e0(r0)
            goto L_0x02c0
        L_0x02d6:
            return r0
        L_0x02d7:
            r7 = 0
            Ff.k r0 = (Ff.k) r0
            lf.j r1 = r0.f3386h
            Df.n r4 = r0.f3389o
            java.lang.Object r5 = r4.d
            B1.b r5 = (B1.b) r5
            kotlin.jvm.internal.j.e(r1, r2)
            java.util.List r14 = r1.k
            r2 = r14
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x02f1
            goto L_0x02f2
        L_0x02f1:
            r14 = r7
        L_0x02f2:
            if (r14 != 0) goto L_0x0325
            java.util.List r1 = r1.l
            java.lang.String r2 = "getSupertypeIdList(...)"
            kotlin.jvm.internal.j.d(r1, r2)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r14 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r1, r3)
            r14.<init>(r2)
            java.util.Iterator r1 = r1.iterator()
        L_0x030a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0325
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            kotlin.jvm.internal.j.b(r2)
            int r2 = r2.intValue()
            lf.Q r2 = r5.g(r2)
            r14.add(r2)
            goto L_0x030a
        L_0x0325:
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r14, r3)
            r1.<init>(r2)
            java.util.Iterator r2 = r14.iterator()
        L_0x0334:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x034c
            java.lang.Object r5 = r2.next()
            lf.Q r5 = (lf.Q) r5
            java.lang.Object r6 = r4.f3360h
            Df.H r6 = (Df.H) r6
            Hf.x r5 = r6.g(r5)
            r1.add(r5)
            goto L_0x0334
        L_0x034c:
            java.lang.Object r2 = r4.f3358a
            Df.l r2 = (Df.l) r2
            Se.b r2 = r2.n
            java.util.Collection r2 = r2.c(r0)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r1 = ne.C1194l.X0(r2, r1)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r5 = r1.iterator()
        L_0x0365:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0388
            java.lang.Object r6 = r5.next()
            Hf.x r6 = (Hf.C0774x) r6
            Hf.M r6 = r6.s0()
            Qe.i r6 = r6.g()
            boolean r8 = r6 instanceof Qe.G
            if (r8 == 0) goto L_0x0381
            r14 = r6
            Qe.G r14 = (Qe.G) r14
            goto L_0x0382
        L_0x0381:
            r14 = r7
        L_0x0382:
            if (r14 == 0) goto L_0x0365
            r2.add(r14)
            goto L_0x0365
        L_0x0388:
            boolean r5 = r2.isEmpty()
            if (r5 != 0) goto L_0x03d2
            java.lang.Object r4 = r4.f3358a
            Df.l r4 = (Df.l) r4
            Df.p r4 = r4.f3351h
            java.util.ArrayList r5 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r2, r3)
            r5.<init>(r3)
            java.util.Iterator r2 = r2.iterator()
        L_0x03a1:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x03cf
            java.lang.Object r3 = r2.next()
            Qe.G r3 = (Qe.G) r3
            qf.b r6 = xf.C1353d.f(r3)
            if (r6 == 0) goto L_0x03be
            qf.c r6 = r6.a()
            if (r6 == 0) goto L_0x03be
            java.lang.String r3 = r6.b()
            goto L_0x03cb
        L_0x03be:
            qf.g r3 = r3.getName()
            java.lang.String r3 = r3.b()
            java.lang.String r6 = "asString(...)"
            kotlin.jvm.internal.j.d(r3, r6)
        L_0x03cb:
            r5.add(r3)
            goto L_0x03a1
        L_0x03cf:
            r4.a(r0, r5)
        L_0x03d2:
            java.util.List r0 = ne.C1194l.k1(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.i.b():java.util.Collection");
    }

    public final S d() {
        switch (this.f3385c) {
            case 0:
                return S.f;
            default:
                return ((C0922a) ((C0946i) this.e).m.d).m;
        }
    }

    public final C0819i g() {
        switch (this.f3385c) {
            case 0:
                return (k) this.e;
            default:
                return (C0946i) this.e;
        }
    }

    public final List getParameters() {
        switch (this.f3385c) {
            case 0:
                return (List) this.d.invoke();
            default:
                return (List) this.d.invoke();
        }
    }

    public final boolean i() {
        switch (this.f3385c) {
            case 0:
                return true;
            default:
                return true;
        }
    }

    public final C0816f m() {
        switch (this.f3385c) {
            case 0:
                return (k) this.e;
            default:
                return (C0946i) this.e;
        }
    }

    public final String toString() {
        switch (this.f3385c) {
            case 0:
                String str = ((k) this.e).getName().d;
                j.d(str, "toString(...)");
                return str;
            default:
                String b = ((C0946i) this.e).getName().b();
                j.d(b, "asString(...)");
                return b;
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public i(Ff.k r4) {
        /*
            r3 = this;
            r0 = 0
            r3.f3385c = r0
            r3.e = r4
            Df.n r0 = r4.f3389o
            java.lang.Object r1 = r0.f3358a
            Df.l r1 = (Df.l) r1
            Gf.m r1 = r1.f3349a
            r3.<init>(r1)
            java.lang.Object r0 = r0.f3358a
            Df.l r0 = (Df.l) r0
            Gf.m r0 = r0.f3349a
            Ff.d r1 = new Ff.d
            r2 = 6
            r1.<init>(r4, r2)
            r0.getClass()
            Gf.i r4 = new Gf.i
            r4.<init>(r0, r1)
            r3.d = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.i.<init>(Ff.k):void");
    }
}
