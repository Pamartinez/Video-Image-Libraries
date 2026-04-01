package Df;

import B1.b;
import D0.e;
import Ff.x;
import Gd.a;
import Gf.j;
import Hf.B;
import Hf.C0759h;
import Hf.C0763l;
import Hf.C0774x;
import Hf.I;
import Hf.M;
import Hf.P;
import Ne.i;
import Qe.C0816f;
import Qe.C0822l;
import Qe.V;
import Re.h;
import Sf.n;
import Sf.u;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lf.Q;
import lf.W;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.C1203u;
import nf.C1209f;
import o1.C0246a;
import qf.C1235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H {

    /* renamed from: a  reason: collision with root package name */
    public final n f3338a;
    public final H b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3339c;
    public final String d;
    public final j e;
    public final j f;
    public final Object g;

    public H(n nVar, H h5, List list, String str, String str2) {
        Map map;
        kotlin.jvm.internal.j.e(list, "typeParameterProtos");
        kotlin.jvm.internal.j.e(str, "debugName");
        this.f3338a = nVar;
        this.b = h5;
        this.f3339c = str;
        this.d = str2;
        l lVar = (l) nVar.f3358a;
        this.e = lVar.f3349a.c(new D(this, 0));
        this.f = lVar.f3349a.c(new D(this, 1));
        if (list.isEmpty()) {
            map = C1203u.d;
        } else {
            map = new LinkedHashMap();
            Iterator it = list.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                W w = (W) it.next();
                map.put(Integer.valueOf(w.g), new x(this.f3338a, w, i2));
                i2++;
            }
        }
        this.g = map;
    }

    public static B a(B b5, C0774x xVar) {
        i z = c.z(b5);
        h annotations = b5.getAnnotations();
        C0774x G5 = a.G(b5);
        List v = a.v(b5);
        Iterable<P> J02 = C1194l.J0(a.I(b5));
        ArrayList arrayList = new ArrayList(C1196n.w0(J02, 10));
        for (P b8 : J02) {
            arrayList.add(b8.b());
        }
        return a.m(z, annotations, G5, v, arrayList, xVar, true).y0(b5.u0());
    }

    public static final ArrayList e(Q q, H h5) {
        Iterable iterable;
        List list = q.g;
        kotlin.jvm.internal.j.d(list, "getArgumentList(...)");
        Collection collection = list;
        Q T = a.T(q, (b) h5.f3338a.d);
        if (T != null) {
            iterable = e(T, h5);
        } else {
            iterable = null;
        }
        if (iterable == null) {
            iterable = C1202t.d;
        }
        return C1194l.X0(iterable, collection);
    }

    public static I f(List list, h hVar, M m, C0822l lVar) {
        I i2;
        Iterable<C0763l> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C0763l lVar2 : iterable) {
            lVar2.getClass();
            if (hVar.isEmpty()) {
                I.e.getClass();
                i2 = I.f;
            } else {
                e eVar = I.e;
                List e02 = C0246a.e0(new C0759h(hVar));
                eVar.getClass();
                i2 = e.D(e02);
            }
            arrayList.add(i2);
        }
        ArrayList x02 = C1196n.x0(arrayList);
        I.e.getClass();
        return e.D(x02);
    }

    public static final C0816f h(H h5, Q q, int i2) {
        n nVar = h5.f3338a;
        C1235b A10 = c.A((C1209f) nVar.b, i2);
        u t02 = n.t0(n.s0(new D(h5, 2), q), F.e);
        ArrayList arrayList = new ArrayList();
        for (Object invoke : t02.f3739a) {
            arrayList.add(t02.b.invoke(invoke));
        }
        int o0 = n.o0(n.s0(G.d, A10));
        while (arrayList.size() < o0) {
            arrayList.add(0);
        }
        return ((l) nVar.f3358a).l.E(A10, arrayList);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
    public final List b() {
        return C1194l.k1(this.g.values());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    public final V c(int i2) {
        V v = (V) this.g.get(Integer.valueOf(i2));
        if (v != null) {
            return v;
        }
        H h5 = this.b;
        if (h5 != null) {
            return h5.c(i2);
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: Qe.V} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: Qe.V} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v32, resolved type: Qe.V} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: Qe.V} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: Qe.V} */
    /* JADX WARNING: type inference failed for: r10v4, types: [Hf.e, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0360  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Hf.B d(lf.Q r19, boolean r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            Df.n r2 = r0.f3338a
            java.lang.Object r3 = r2.d
            B1.b r3 = (B1.b) r3
            java.lang.Object r4 = r2.f3358a
            Df.l r4 = (Df.l) r4
            java.lang.Object r5 = r2.f3359c
            Qe.l r5 = (Qe.C0822l) r5
            java.lang.String r6 = "proto"
            kotlin.jvm.internal.j.e(r1, r6)
            int r6 = r1.f
            r7 = r6 & 16
            r8 = 128(0x80, float:1.794E-43)
            r9 = 16
            if (r7 != r9) goto L_0x0039
            int r6 = r1.l
            java.lang.Object r7 = r2.b
            nf.f r7 = (nf.C1209f) r7
            qf.b r6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(r7, r6)
            boolean r6 = r6.f5034c
            if (r6 == 0) goto L_0x0053
            java.lang.Object r6 = r2.f3358a
            Df.l r6 = (Df.l) r6
            Df.m r6 = r6.g
            r6.getClass()
            goto L_0x0053
        L_0x0039:
            r6 = r6 & r8
            if (r6 != r8) goto L_0x0053
            int r6 = r1.f4774o
            java.lang.Object r7 = r2.b
            nf.f r7 = (nf.C1209f) r7
            qf.b r6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(r7, r6)
            boolean r6 = r6.f5034c
            if (r6 == 0) goto L_0x0053
            java.lang.Object r6 = r2.f3358a
            Df.l r6 = (Df.l) r6
            Df.m r6 = r6.g
            r6.getClass()
        L_0x0053:
            int r6 = r1.f
            r7 = r6 & 16
            java.lang.String r10 = "getTypeConstructor(...)"
            r11 = 0
            if (r7 != r9) goto L_0x0074
            int r2 = r1.l
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            Gf.j r6 = r0.e
            java.lang.Object r2 = r6.invoke(r2)
            Qe.i r2 = (Qe.C0819i) r2
            if (r2 != 0) goto L_0x0101
            int r2 = r1.l
            Qe.f r2 = h(r0, r1, r2)
            goto L_0x0101
        L_0x0074:
            r7 = r6 & 32
            r9 = 32
            if (r7 != r9) goto L_0x0098
            int r2 = r1.m
            Qe.V r2 = r0.c(r2)
            if (r2 != 0) goto L_0x0101
            Jf.l r2 = Jf.l.f3482a
            Jf.k r2 = Jf.k.CANNOT_LOAD_DESERIALIZE_TYPE_PARAMETER
            int r6 = r1.m
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = r0.d
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}
            Jf.j r2 = Jf.l.d(r2, r6)
            goto L_0x0113
        L_0x0098:
            r7 = r6 & 64
            r9 = 64
            if (r7 != r9) goto L_0x00e7
            java.lang.Object r2 = r2.b
            nf.f r2 = (nf.C1209f) r2
            int r6 = r1.n
            java.lang.String r2 = r2.getString(r6)
            java.util.List r6 = r0.b()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x00b2:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00ce
            java.lang.Object r7 = r6.next()
            r8 = r7
            Qe.V r8 = (Qe.V) r8
            qf.g r8 = r8.getName()
            java.lang.String r8 = r8.b()
            boolean r8 = kotlin.jvm.internal.j.a(r8, r2)
            if (r8 == 0) goto L_0x00b2
            goto L_0x00cf
        L_0x00ce:
            r7 = 0
        L_0x00cf:
            r6 = r7
            Qe.V r6 = (Qe.V) r6
            if (r6 != 0) goto L_0x00e5
            Jf.l r6 = Jf.l.f3482a
            Jf.k r6 = Jf.k.CANNOT_LOAD_DESERIALIZE_TYPE_PARAMETER_BY_NAME
            java.lang.String r7 = r5.toString()
            java.lang.String[] r2 = new java.lang.String[]{r2, r7}
            Jf.j r2 = Jf.l.d(r6, r2)
            goto L_0x0113
        L_0x00e5:
            r2 = r6
            goto L_0x0101
        L_0x00e7:
            r2 = r6 & 128(0x80, float:1.794E-43)
            if (r2 != r8) goto L_0x0109
            int r2 = r1.f4774o
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            Gf.j r6 = r0.f
            java.lang.Object r2 = r6.invoke(r2)
            Qe.i r2 = (Qe.C0819i) r2
            if (r2 != 0) goto L_0x0101
            int r2 = r1.f4774o
            Qe.f r2 = h(r0, r1, r2)
        L_0x0101:
            Hf.M r2 = r2.q()
            kotlin.jvm.internal.j.d(r2, r10)
            goto L_0x0113
        L_0x0109:
            Jf.l r2 = Jf.l.f3482a
            Jf.k r2 = Jf.k.UNKNOWN_TYPE
            java.lang.String[] r6 = new java.lang.String[r11]
            Jf.j r2 = Jf.l.d(r2, r6)
        L_0x0113:
            Qe.i r6 = r2.g()
            boolean r6 = Jf.l.f(r6)
            r7 = 1
            if (r6 == 0) goto L_0x013c
            Jf.l r0 = Jf.l.f3482a
            Jf.k r0 = Jf.k.TYPE_FOR_ERROR_TYPE_CONSTRUCTOR
            java.lang.String r1 = r2.toString()
            java.lang.String[] r1 = new java.lang.String[]{r1}
            java.lang.String r3 = "kind"
            kotlin.jvm.internal.j.e(r0, r3)
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r7)
            java.lang.String[] r1 = (java.lang.String[]) r1
            ne.t r3 = ne.C1202t.d
            Jf.i r0 = Jf.l.e(r0, r3, r2, r1)
            return r0
        L_0x013c:
            Ff.a r6 = new Ff.a
            Gf.m r8 = r4.f3349a
            Df.E r9 = new Df.E
            r9.<init>((int) r11, (java.lang.Object) r0, (java.lang.Object) r1)
            r6.<init>(r8, r9)
            java.util.List r8 = r4.r
            Hf.I r8 = f(r8, r6, r2, r5)
            java.util.ArrayList r9 = e(r1, r0)
            java.util.ArrayList r13 = new java.util.ArrayList
            r14 = 10
            int r14 = ne.C1196n.w0(r9, r14)
            r13.<init>(r14)
            java.util.Iterator r9 = r9.iterator()
            r14 = r11
        L_0x0162:
            boolean r15 = r9.hasNext()
            if (r15 == 0) goto L_0x0220
            java.lang.Object r15 = r9.next()
            int r16 = r14 + 1
            if (r14 < 0) goto L_0x021a
            lf.O r15 = (lf.O) r15
            java.util.List r11 = r2.getParameters()
            r17 = 0
            java.lang.String r12 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r11, r12)
            java.lang.Object r11 = ne.C1194l.O0(r14, r11)
            Qe.V r11 = (Qe.V) r11
            lf.N r12 = r15.f
            lf.N r14 = lf.N.STAR
            if (r12 != r14) goto L_0x01a0
            if (r11 != 0) goto L_0x0198
            Hf.F r11 = new Hf.F
            Qe.C r12 = r4.b
            Ne.i r12 = r12.f()
            r11.<init>(r12)
            goto L_0x0211
        L_0x0198:
            Hf.G r12 = new Hf.G
            r12.<init>((Qe.V) r11)
        L_0x019d:
            r11 = r12
            goto L_0x0211
        L_0x01a0:
            java.lang.String r11 = "getProjection(...)"
            kotlin.jvm.internal.j.d(r12, r11)
            int[] r11 = Df.A.d
            int r14 = r12.ordinal()
            r11 = r11[r14]
            r14 = 2
            if (r11 == r7) goto L_0x01d8
            if (r11 == r14) goto L_0x01d5
            r7 = 3
            if (r11 == r7) goto L_0x01d2
            r7 = 4
            if (r11 == r7) goto L_0x01be
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        L_0x01be:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Only IN, OUT and INV are supported. Actual argument: "
            r1.<init>(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01d2:
            Hf.d0 r7 = Hf.d0.INVARIANT
            goto L_0x01da
        L_0x01d5:
            Hf.d0 r7 = Hf.d0.OUT_VARIANCE
            goto L_0x01da
        L_0x01d8:
            Hf.d0 r7 = Hf.d0.IN_VARIANCE
        L_0x01da:
            int r11 = r15.e
            r12 = r11 & 2
            if (r12 != r14) goto L_0x01e3
            lf.Q r11 = r15.g
            goto L_0x01f1
        L_0x01e3:
            r11 = r11 & 4
            r12 = 4
            if (r11 != r12) goto L_0x01ef
            int r11 = r15.f4762h
            lf.Q r11 = r3.g(r11)
            goto L_0x01f1
        L_0x01ef:
            r11 = r17
        L_0x01f1:
            if (r11 != 0) goto L_0x0207
            Hf.G r11 = new Hf.G
            Jf.k r7 = Jf.k.NO_RECORDED_TYPE
            java.lang.String r12 = r15.toString()
            java.lang.String[] r12 = new java.lang.String[]{r12}
            Jf.i r7 = Jf.l.c(r7, r12)
            r11.<init>((Hf.C0774x) r7)
            goto L_0x0211
        L_0x0207:
            Hf.G r12 = new Hf.G
            Hf.x r11 = r0.g(r11)
            r12.<init>(r11, r7)
            goto L_0x019d
        L_0x0211:
            r13.add(r11)
            r14 = r16
            r7 = 1
            r11 = 0
            goto L_0x0162
        L_0x021a:
            r17 = 0
            ne.C1195m.v0()
            throw r17
        L_0x0220:
            r17 = 0
            java.util.List r7 = ne.C1194l.k1(r13)
            Qe.i r9 = r2.g()
            if (r20 == 0) goto L_0x0288
            boolean r11 = r9 instanceof Qe.U
            if (r11 == 0) goto L_0x0288
            Qe.U r9 = (Qe.U) r9
            Hf.e r10 = new Hf.e
            r10.<init>()
            r8 = r17
            D0.f r11 = Hf.C0754c.e(r8, r9, r7)
            D0.e r7 = Hf.I.e
            r7.getClass()
            Hf.I r12 = Hf.I.f
            java.lang.String r7 = "attributes"
            kotlin.jvm.internal.j.e(r12, r7)
            r14 = 0
            r15 = 1
            r13 = 0
            Hf.B r7 = r10.h(r11, r12, r13, r14, r15)
            java.util.List r4 = r4.r
            Re.h r9 = r7.getAnnotations()
            java.util.ArrayList r6 = ne.C1194l.V0(r6, r9)
            boolean r9 = r6.isEmpty()
            if (r9 == 0) goto L_0x0263
            Re.f r6 = Re.g.f3692a
            goto L_0x026a
        L_0x0263:
            Re.i r9 = new Re.i
            r10 = 0
            r9.<init>(r10, r6)
            r6 = r9
        L_0x026a:
            Hf.I r2 = f(r4, r6, r2, r5)
            boolean r4 = Hf.a0.e(r7)
            if (r4 != 0) goto L_0x027b
            boolean r4 = r1.f4771h
            if (r4 == 0) goto L_0x0279
            goto L_0x027b
        L_0x0279:
            r4 = 0
            goto L_0x027c
        L_0x027b:
            r4 = 1
        L_0x027c:
            Hf.B r4 = r7.y0(r4)
            Hf.B r2 = r4.A0(r2)
            r17 = r8
            goto L_0x03a8
        L_0x0288:
            nf.b r4 = nf.C1208e.f4965a
            int r6 = r1.t
            java.lang.Boolean r4 = r4.c(r6)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x036e
            boolean r4 = r1.f4771h
            java.util.List r6 = r2.getParameters()
            int r6 = r6.size()
            int r9 = r7.size()
            int r6 = r6 - r9
            if (r6 == 0) goto L_0x02ca
            r9 = 1
            if (r6 == r9) goto L_0x02ae
        L_0x02aa:
            r8 = r17
            goto L_0x035e
        L_0x02ae:
            int r5 = r7.size()
            int r5 = r5 - r9
            if (r5 < 0) goto L_0x02aa
            Ne.i r6 = r2.f()
            Qe.f r5 = r6.v(r5)
            Hf.M r5 = r5.q()
            kotlin.jvm.internal.j.d(r5, r10)
            Hf.B r8 = Hf.C0754c.u(r8, r5, r7, r4)
            goto L_0x035e
        L_0x02ca:
            Hf.B r8 = Hf.C0754c.u(r8, r2, r7, r4)
            Hf.M r4 = r8.s0()
            Qe.i r4 = r4.g()
            if (r4 == 0) goto L_0x02dd
            Oe.l r4 = Gd.a.x(r4)
            goto L_0x02df
        L_0x02dd:
            r4 = r17
        L_0x02df:
            Oe.h r6 = Oe.h.f3618c
            boolean r4 = kotlin.jvm.internal.j.a(r4, r6)
            if (r4 != 0) goto L_0x02e8
            goto L_0x02aa
        L_0x02e8:
            java.util.List r4 = Gd.a.I(r8)
            java.lang.Object r4 = ne.C1194l.U0(r4)
            Hf.P r4 = (Hf.P) r4
            if (r4 == 0) goto L_0x02aa
            Hf.x r4 = r4.b()
            if (r4 != 0) goto L_0x02fb
            goto L_0x02aa
        L_0x02fb:
            Hf.M r6 = r4.s0()
            Qe.i r6 = r6.g()
            if (r6 == 0) goto L_0x030a
            qf.c r6 = xf.C1353d.g(r6)
            goto L_0x030c
        L_0x030a:
            r6 = r17
        L_0x030c:
            java.util.List r9 = r4.e0()
            int r9 = r9.size()
            r10 = 1
            if (r9 != r10) goto L_0x035e
            qf.c r9 = Ne.q.g
            boolean r9 = kotlin.jvm.internal.j.a(r6, r9)
            if (r9 != 0) goto L_0x0328
            qf.c r9 = Df.I.f3340a
            boolean r6 = kotlin.jvm.internal.j.a(r6, r9)
            if (r6 != 0) goto L_0x0328
            goto L_0x035e
        L_0x0328:
            java.util.List r4 = r4.e0()
            java.lang.Object r4 = ne.C1194l.b1(r4)
            Hf.P r4 = (Hf.P) r4
            Hf.x r4 = r4.b()
            java.lang.String r6 = "getType(...)"
            kotlin.jvm.internal.j.d(r4, r6)
            boolean r6 = r5 instanceof Qe.C0812b
            if (r6 == 0) goto L_0x0342
            Qe.b r5 = (Qe.C0812b) r5
            goto L_0x0344
        L_0x0342:
            r5 = r17
        L_0x0344:
            if (r5 == 0) goto L_0x034b
            qf.c r5 = xf.C1353d.c(r5)
            goto L_0x034d
        L_0x034b:
            r5 = r17
        L_0x034d:
            qf.c r6 = Df.C.f3337a
            boolean r5 = kotlin.jvm.internal.j.a(r5, r6)
            if (r5 == 0) goto L_0x035a
            Hf.B r8 = a(r8, r4)
            goto L_0x035e
        L_0x035a:
            Hf.B r8 = a(r8, r4)
        L_0x035e:
            if (r8 != 0) goto L_0x036c
            Jf.l r4 = Jf.l.f3482a
            Jf.k r4 = Jf.k.INCONSISTENT_SUSPEND_FUNCTION
            r10 = 0
            java.lang.String[] r5 = new java.lang.String[r10]
            Jf.i r2 = Jf.l.e(r4, r7, r2, r5)
            goto L_0x03a8
        L_0x036c:
            r2 = r8
            goto L_0x03a8
        L_0x036e:
            boolean r4 = r1.f4771h
            Hf.B r2 = Hf.C0754c.u(r8, r2, r7, r4)
            nf.b r4 = nf.C1208e.b
            int r5 = r1.t
            java.lang.Boolean r4 = r4.c(r5)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x03a8
            r9 = 1
            Hf.m r4 = Hf.C0756e.o(r2, r9)
            if (r4 == 0) goto L_0x038b
            r2 = r4
            goto L_0x03a8
        L_0x038b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "null DefinitelyNotNullType for '"
            r1.<init>(r3)
            r1.append(r2)
            r2 = 39
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x03a8:
            int r4 = r1.f
            r5 = r4 & 1024(0x400, float:1.435E-42)
            r6 = 1024(0x400, float:1.435E-42)
            if (r5 != r6) goto L_0x03b3
            lf.Q r12 = r1.r
            goto L_0x03c1
        L_0x03b3:
            r5 = 2048(0x800, float:2.87E-42)
            r4 = r4 & r5
            if (r4 != r5) goto L_0x03bf
            int r1 = r1.s
            lf.Q r12 = r3.g(r1)
            goto L_0x03c1
        L_0x03bf:
            r12 = r17
        L_0x03c1:
            if (r12 == 0) goto L_0x03cd
            r10 = 0
            Hf.B r0 = r0.d(r12, r10)
            Hf.B r0 = Hf.C0754c.F(r2, r0)
            return r0
        L_0x03cd:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.H.d(lf.Q, boolean):Hf.B");
    }

    public final C0774x g(Q q) {
        Q q10;
        kotlin.jvm.internal.j.e(q, "proto");
        if ((q.f & 2) != 2) {
            return d(q, true);
        }
        n nVar = this.f3338a;
        String string = ((C1209f) nVar.b).getString(q.f4772i);
        B d2 = d(q, true);
        b bVar = (b) nVar.d;
        int i2 = q.f;
        if ((i2 & 4) == 4) {
            q10 = q.f4773j;
        } else if ((i2 & 8) == 8) {
            q10 = bVar.g(q.k);
        } else {
            q10 = null;
        }
        kotlin.jvm.internal.j.b(q10);
        return ((l) nVar.f3358a).f3353j.b(q, string, d2, d(q10, true));
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f3339c);
        H h5 = this.b;
        if (h5 == null) {
            str = "";
        } else {
            str = ". Child of " + h5.f3339c;
        }
        sb2.append(str);
        return sb2.toString();
    }
}
