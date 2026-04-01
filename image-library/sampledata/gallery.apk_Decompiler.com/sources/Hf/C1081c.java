package hf;

import Ae.b;
import B0.a;
import Hf.C0774x;
import Qe.C0812b;
import Qe.C0814d;
import Ze.C0894a;
import bf.C0916a;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1196n;

/* renamed from: hf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1081c {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: Re.h} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01d2 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0201  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static G2.e c(Hf.B r17, Ff.j r18, int r19, hf.C1093o r20, boolean r21, boolean r22) {
        /*
            r0 = r18
            r1 = r20
            r2 = r22
            java.lang.String r3 = "<this>"
            kotlin.jvm.internal.j.e(r1, r3)
            hf.o r3 = hf.C1093o.INFLEXIBLE
            r4 = 0
            r5 = 1
            if (r1 == r3) goto L_0x0013
            r6 = r5
            goto L_0x0014
        L_0x0013:
            r6 = r4
        L_0x0014:
            if (r2 == 0) goto L_0x001b
            if (r21 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r7 = r4
            goto L_0x001c
        L_0x001b:
            r7 = r5
        L_0x001c:
            r8 = 0
            if (r6 != 0) goto L_0x002f
            java.util.List r6 = r17.e0()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x002f
            G2.e r0 = new G2.e
            r0.<init>(r8, r5, r4)
            return r0
        L_0x002f:
            Hf.M r6 = r17.s0()
            Qe.i r6 = r6.g()
            if (r6 != 0) goto L_0x003f
            G2.e r0 = new G2.e
            r0.<init>(r8, r5, r4)
            return r0
        L_0x003f:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r19)
            java.lang.Object r9 = r0.invoke(r9)
            hf.d r9 = (hf.C1082d) r9
            Re.i r10 = hf.C1096r.f4598a
            if (r1 == r3) goto L_0x00bf
            boolean r10 = r6 instanceof Qe.C0816f
            if (r10 != 0) goto L_0x0052
            goto L_0x00bf
        L_0x0052:
            hf.e r10 = r9.b
            hf.e r11 = hf.C1083e.READ_ONLY
            if (r10 != r11) goto L_0x00a0
            hf.o r10 = hf.C1093o.FLEXIBLE_LOWER
            if (r1 != r10) goto L_0x00a0
            r10 = r6
            Qe.f r10 = (Qe.C0816f) r10
            java.lang.String r11 = Pe.d.f3633a
            qf.e r11 = tf.C1301e.g(r10)
            java.util.HashMap r12 = Pe.d.f3637j
            boolean r11 = r12.containsKey(r11)
            if (r11 == 0) goto L_0x00a0
            qf.e r6 = tf.C1301e.g(r10)
            java.lang.Object r6 = r12.get(r6)
            qf.c r6 = (qf.C1236c) r6
            if (r6 == 0) goto L_0x0087
            Ne.i r10 = xf.C1353d.e(r10)
            Qe.f r6 = r10.i(r6)
            java.lang.String r10 = "getBuiltInClassByFqName(...)"
            kotlin.jvm.internal.j.d(r6, r10)
            goto L_0x00c0
        L_0x0087:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Given class "
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r2 = " is not a mutable collection"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00a0:
            hf.e r10 = r9.b
            hf.e r11 = hf.C1083e.MUTABLE
            if (r10 != r11) goto L_0x00bf
            hf.o r10 = hf.C1093o.FLEXIBLE_UPPER
            if (r1 != r10) goto L_0x00bf
            Qe.f r6 = (Qe.C0816f) r6
            java.lang.String r10 = Pe.d.f3633a
            qf.e r10 = tf.C1301e.g(r6)
            java.util.HashMap r11 = Pe.d.k
            boolean r10 = r11.containsKey(r10)
            if (r10 == 0) goto L_0x00bf
            Qe.f r6 = Pe.e.b(r6)
            goto L_0x00c0
        L_0x00bf:
            r6 = r8
        L_0x00c0:
            r10 = 2
            if (r1 == r3) goto L_0x00dc
            hf.g r1 = r9.f4586a
            if (r1 != 0) goto L_0x00c9
            r1 = -1
            goto L_0x00d1
        L_0x00c9:
            int[] r3 = hf.C1095q.f4597a
            int r1 = r1.ordinal()
            r1 = r3[r1]
        L_0x00d1:
            if (r1 == r5) goto L_0x00d9
            if (r1 == r10) goto L_0x00d6
            goto L_0x00dc
        L_0x00d6:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            goto L_0x00dd
        L_0x00d9:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            goto L_0x00dd
        L_0x00dc:
            r1 = r8
        L_0x00dd:
            if (r6 == 0) goto L_0x00e5
            Hf.M r3 = r6.q()
            if (r3 != 0) goto L_0x00e9
        L_0x00e5:
            Hf.M r3 = r17.s0()
        L_0x00e9:
            int r11 = r19 + 1
            java.util.List r12 = r17.e0()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.List r13 = r3.getParameters()
            java.lang.String r14 = "getParameters(...)"
            kotlin.jvm.internal.j.d(r13, r14)
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.Iterator r14 = r12.iterator()
            java.util.Iterator r15 = r13.iterator()
            r21 = r10
            java.util.ArrayList r10 = new java.util.ArrayList
            r5 = 10
            int r12 = ne.C1196n.w0(r12, r5)
            int r13 = ne.C1196n.w0(r13, r5)
            int r12 = java.lang.Math.min(r12, r13)
            r10.<init>(r12)
        L_0x0119:
            boolean r12 = r14.hasNext()
            if (r12 == 0) goto L_0x01ce
            boolean r12 = r15.hasNext()
            if (r12 == 0) goto L_0x01ce
            java.lang.Object r12 = r14.next()
            java.lang.Object r13 = r15.next()
            Qe.V r13 = (Qe.V) r13
            Hf.P r12 = (Hf.P) r12
            if (r7 != 0) goto L_0x0139
            Q2.a r5 = new Q2.a
            r5.<init>((Hf.c0) r8, (int) r4)
            goto L_0x0187
        L_0x0139:
            boolean r5 = r12.c()
            if (r5 != 0) goto L_0x014c
            Hf.x r5 = r12.b()
            Hf.c0 r5 = r5.x0()
            Q2.a r5 = d(r5, r0, r11, r2)
            goto L_0x0187
        L_0x014c:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r11)
            java.lang.Object r5 = r0.invoke(r5)
            hf.d r5 = (hf.C1082d) r5
            hf.g r5 = r5.f4586a
            hf.g r8 = hf.C1085g.FORCE_FLEXIBILITY
            if (r5 != r8) goto L_0x0180
            Hf.x r5 = r12.b()
            Hf.c0 r5 = r5.x0()
            Q2.a r8 = new Q2.a
            Hf.B r0 = Hf.C0754c.m(r5)
            Hf.B r0 = r0.y0(r4)
            Hf.B r5 = Hf.C0754c.E(r5)
            r4 = 1
            Hf.B r5 = r5.y0(r4)
            Hf.c0 r0 = Hf.C0754c.f(r0, r5)
            r8.<init>((Hf.c0) r0, (int) r4)
            r5 = r8
            goto L_0x0187
        L_0x0180:
            r4 = 1
            Q2.a r5 = new Q2.a
            r0 = 0
            r5.<init>((Hf.c0) r0, (int) r4)
        L_0x0187:
            int r0 = r5.f634a
            int r11 = r11 + r0
            java.lang.Object r0 = r5.b
            Hf.x r0 = (Hf.C0774x) r0
            java.lang.String r4 = "getProjectionKind(...)"
            if (r0 == 0) goto L_0x019e
            Hf.d0 r5 = r12.a()
            kotlin.jvm.internal.j.d(r5, r4)
            Hf.G r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.p(r0, r5, r13)
            goto L_0x01c3
        L_0x019e:
            if (r6 == 0) goto L_0x01bb
            boolean r0 = r12.c()
            if (r0 != 0) goto L_0x01bb
            Hf.x r0 = r12.b()
            java.lang.String r5 = "getType(...)"
            kotlin.jvm.internal.j.d(r0, r5)
            Hf.d0 r5 = r12.a()
            kotlin.jvm.internal.j.d(r5, r4)
            Hf.G r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.p(r0, r5, r13)
            goto L_0x01c3
        L_0x01bb:
            if (r6 == 0) goto L_0x01c2
            Hf.G r0 = Hf.a0.j(r13)
            goto L_0x01c3
        L_0x01c2:
            r0 = 0
        L_0x01c3:
            r10.add(r0)
            r0 = r18
            r4 = 0
            r5 = 10
            r8 = 0
            goto L_0x0119
        L_0x01ce:
            int r11 = r11 - r19
            if (r6 != 0) goto L_0x01ee
            if (r1 != 0) goto L_0x01ee
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x01db
            goto L_0x01f0
        L_0x01db:
            java.util.Iterator r0 = r10.iterator()
        L_0x01df:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x01f0
            java.lang.Object r2 = r0.next()
            Hf.P r2 = (Hf.P) r2
            if (r2 != 0) goto L_0x01ee
            goto L_0x01df
        L_0x01ee:
            r2 = 0
            goto L_0x01f8
        L_0x01f0:
            G2.e r0 = new G2.e
            r1 = 0
            r2 = 0
            r0.<init>(r2, r11, r1)
            return r0
        L_0x01f8:
            Re.h r0 = r17.getAnnotations()
            Re.i r4 = hf.C1096r.b
            if (r6 == 0) goto L_0x0201
            goto L_0x0202
        L_0x0201:
            r4 = r2
        L_0x0202:
            Re.i r5 = hf.C1096r.f4598a
            if (r1 == 0) goto L_0x0208
            r8 = r5
            goto L_0x0209
        L_0x0208:
            r8 = r2
        L_0x0209:
            r2 = 3
            Re.h[] r2 = new Re.h[r2]
            r16 = 0
            r2[r16] = r0
            r0 = 1
            r2[r0] = r4
            r2[r21] = r8
            java.util.ArrayList r2 = ne.C1192j.l0(r2)
            int r4 = r2.size()
            if (r4 == 0) goto L_0x02a0
            if (r4 == r0) goto L_0x022b
            Re.i r4 = new Re.i
            java.util.List r2 = ne.C1194l.k1(r2)
            r4.<init>(r0, r2)
            goto L_0x0232
        L_0x022b:
            java.lang.Object r2 = ne.C1194l.b1(r2)
            r4 = r2
            Re.h r4 = (Re.h) r4
        L_0x0232:
            Hf.I r2 = Hf.C0754c.C(r4)
            java.util.List r4 = r17.e0()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r5 = r10.iterator()
            java.util.Iterator r6 = r4.iterator()
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r10 = ne.C1196n.w0(r10, r8)
            int r4 = ne.C1196n.w0(r4, r8)
            int r4 = java.lang.Math.min(r10, r4)
            r7.<init>(r4)
        L_0x0257:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0277
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L_0x0277
            java.lang.Object r4 = r5.next()
            java.lang.Object r8 = r6.next()
            Hf.P r8 = (Hf.P) r8
            Hf.P r4 = (Hf.P) r4
            if (r4 != 0) goto L_0x0272
            goto L_0x0273
        L_0x0272:
            r8 = r4
        L_0x0273:
            r7.add(r8)
            goto L_0x0257
        L_0x0277:
            if (r1 == 0) goto L_0x027e
            boolean r4 = r1.booleanValue()
            goto L_0x0282
        L_0x027e:
            boolean r4 = r17.u0()
        L_0x0282:
            Hf.B r2 = Hf.C0754c.u(r2, r3, r7, r4)
            boolean r3 = r9.f4587c
            if (r3 == 0) goto L_0x0290
            hf.f r3 = new hf.f
            r3.<init>(r2)
            r2 = r3
        L_0x0290:
            if (r1 == 0) goto L_0x0298
            boolean r1 = r9.d
            if (r1 == 0) goto L_0x0298
            r4 = r0
            goto L_0x029a
        L_0x0298:
            r4 = r16
        L_0x029a:
            G2.e r0 = new G2.e
            r0.<init>(r2, r11, r4)
            return r0
        L_0x02a0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "At least one Annotations object expected"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: hf.C1081c.c(Hf.B, Ff.j, int, hf.o, boolean, boolean):G2.e");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
        if (r2 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        if (r0 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        r8 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        r1 = Hf.C0754c.f(r2, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        kotlin.jvm.internal.j.b(r2);
        r2 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Q2.a d(Hf.c0 r9, Ff.j r10, int r11, boolean r12) {
        /*
            boolean r0 = Hf.C0754c.k(r9)
            r1 = 0
            if (r0 == 0) goto L_0x000e
            Q2.a r9 = new Q2.a
            r10 = 1
            r9.<init>((Hf.c0) r1, (int) r10)
            return r9
        L_0x000e:
            boolean r0 = r9 instanceof Hf.C0768q
            if (r0 == 0) goto L_0x0077
            boolean r6 = r9 instanceof ef.i
            r0 = r9
            Hf.q r0 = (Hf.C0768q) r0
            Hf.B r8 = r0.f
            Hf.B r2 = r0.e
            hf.o r5 = hf.C1093o.FLEXIBLE_LOWER
            r3 = r10
            r4 = r11
            r7 = r12
            G2.e r10 = c(r2, r3, r4, r5, r6, r7)
            r11 = r2
            Hf.B r2 = r0.f
            hf.o r5 = hf.C1093o.FLEXIBLE_UPPER
            G2.e r12 = c(r2, r3, r4, r5, r6, r7)
            java.lang.Object r0 = r12.f291c
            Hf.B r0 = (Hf.B) r0
            java.lang.Object r2 = r10.f291c
            Hf.B r2 = (Hf.B) r2
            if (r2 != 0) goto L_0x003a
            if (r0 != 0) goto L_0x003a
            goto L_0x006f
        L_0x003a:
            boolean r1 = r10.b
            if (r1 != 0) goto L_0x005e
            boolean r12 = r12.b
            if (r12 == 0) goto L_0x0043
            goto L_0x005e
        L_0x0043:
            if (r6 == 0) goto L_0x0052
            ef.i r1 = new ef.i
            if (r2 != 0) goto L_0x004a
            r2 = r11
        L_0x004a:
            if (r0 != 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r8 = r0
        L_0x004e:
            r1.<init>(r2, r8)
            goto L_0x006f
        L_0x0052:
            if (r2 != 0) goto L_0x0055
            r2 = r11
        L_0x0055:
            if (r0 != 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r8 = r0
        L_0x0059:
            Hf.c0 r1 = Hf.C0754c.f(r2, r8)
            goto L_0x006f
        L_0x005e:
            if (r0 == 0) goto L_0x0068
            if (r2 != 0) goto L_0x0063
            r2 = r0
        L_0x0063:
            Hf.c0 r2 = Hf.C0754c.f(r2, r0)
            goto L_0x006b
        L_0x0068:
            kotlin.jvm.internal.j.b(r2)
        L_0x006b:
            Hf.c0 r1 = Hf.C0754c.G(r9, r2)
        L_0x006f:
            Q2.a r9 = new Q2.a
            int r10 = r10.f290a
            r9.<init>((Hf.c0) r1, (int) r10)
            return r9
        L_0x0077:
            r3 = r10
            r4 = r11
            r7 = r12
            boolean r10 = r9 instanceof Hf.B
            if (r10 == 0) goto L_0x009c
            r2 = r9
            Hf.B r2 = (Hf.B) r2
            hf.o r5 = hf.C1093o.INFLEXIBLE
            r6 = 0
            G2.e r10 = c(r2, r3, r4, r5, r6, r7)
            java.lang.Object r11 = r10.f291c
            Hf.B r11 = (Hf.B) r11
            Q2.a r12 = new Q2.a
            boolean r0 = r10.b
            if (r0 == 0) goto L_0x0096
            Hf.c0 r11 = Hf.C0754c.G(r9, r11)
        L_0x0096:
            int r9 = r10.f290a
            r12.<init>((Hf.c0) r11, (int) r9)
            return r12
        L_0x009c:
            Dd.a r9 = new Dd.a
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: hf.C1081c.d(Hf.c0, Ff.j, int, boolean):Q2.a");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: hf.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: hf.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: Ze.m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: Ze.m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: hf.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v27, resolved type: hf.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: hf.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v53, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v54, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: hf.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: Ze.m} */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x027d, code lost:
        if (r12 == false) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0282, code lost:
        if (r12 == false) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0290, code lost:
        if (r4.compareTo(r6) <= 0) goto L_0x0293;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0349  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0377  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0379  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x03be  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x03fe  */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x0422 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Hf.C0774x a(Jd.b r27, Hf.C0774x r28, java.util.List r29, hf.C1094p r30, boolean r31) {
        /*
            r26 = this;
            r0 = r27
            r1 = r28
            java.lang.Object r2 = r0.f3474c
            Re.a r2 = (Re.a) r2
            java.lang.Object r3 = r0.d
            B0.a r3 = (B0.a) r3
            r4 = r29
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            boolean r5 = r0.f3473a
            java.lang.String r6 = "<this>"
            kotlin.jvm.internal.j.e(r1, r6)
            java.util.ArrayList r6 = r27.j(r28)
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r8 = ne.C1196n.w0(r4, r8)
            r7.<init>(r8)
            java.util.Iterator r8 = r4.iterator()
        L_0x002a:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x003e
            java.lang.Object r9 = r8.next()
            Kf.d r9 = (Kf.d) r9
            java.util.ArrayList r9 = r0.j(r9)
            r7.add(r9)
            goto L_0x002a
        L_0x003e:
            if (r5 == 0) goto L_0x0075
            boolean r9 = r4 instanceof java.util.Collection
            if (r9 == 0) goto L_0x004e
            r9 = r4
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x004e
            goto L_0x0075
        L_0x004e:
            java.util.Iterator r4 = r4.iterator()
        L_0x0052:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x0075
            java.lang.Object r9 = r4.next()
            Kf.d r9 = (Kf.d) r9
            java.lang.String r10 = "other"
            kotlin.jvm.internal.j.e(r9, r10)
            java.lang.Object r10 = r3.d
            cf.a r10 = (cf.C0922a) r10
            If.k r10 = r10.u
            Hf.x r9 = (Hf.C0774x) r9
            If.l r10 = (If.l) r10
            boolean r9 = r10.a(r1, r9)
            if (r9 != 0) goto L_0x0052
            r4 = 1
            goto L_0x0079
        L_0x0075:
            int r4 = r6.size()
        L_0x0079:
            hf.d[] r9 = new hf.C1082d[r4]
            r11 = 0
        L_0x007c:
            if (r11 >= r4) goto L_0x043e
            java.lang.Object r12 = r6.get(r11)
            hf.a r12 = (hf.C1079a) r12
            java.lang.Object r13 = r0.e
            Ze.a r13 = (Ze.C0894a) r13
            Kf.d r14 = r12.f4583a
            Qe.V r15 = r12.f4584c
            If.m r10 = If.m.d
            if (r14 != 0) goto L_0x00b5
            if (r15 == 0) goto L_0x00a0
            Hf.d0 r8 = r15.t()
            java.lang.String r1 = "getVariance(...)"
            kotlin.jvm.internal.j.d(r8, r1)
            Kf.i r1 = B1.a.o(r8)
            goto L_0x00a1
        L_0x00a0:
            r1 = 0
        L_0x00a1:
            Kf.i r8 = Kf.i.IN
            if (r1 != r8) goto L_0x00b5
            hf.d r1 = hf.C1082d.e
            r20 = r3
            r18 = r4
            r22 = r5
            r24 = r6
            r23 = r7
            r6 = 1
            r7 = 0
            goto L_0x02ac
        L_0x00b5:
            if (r15 != 0) goto L_0x00b9
            r1 = 1
            goto L_0x00ba
        L_0x00b9:
            r1 = 0
        L_0x00ba:
            ne.t r8 = ne.C1202t.d
            if (r14 == 0) goto L_0x00cd
            r17 = r14
            Hf.x r17 = (Hf.C0774x) r17
            Re.h r17 = r17.getAnnotations()
            r25 = r17
            r17 = r1
            r1 = r25
            goto L_0x00d0
        L_0x00cd:
            r17 = r1
            r1 = r8
        L_0x00d0:
            if (r14 == 0) goto L_0x00df
            Hf.M r14 = r10.K(r14)
            if (r14 == 0) goto L_0x00df
            Qe.V r14 = If.g.s(r14)
        L_0x00dc:
            r18 = r4
            goto L_0x00e1
        L_0x00df:
            r14 = 0
            goto L_0x00dc
        L_0x00e1:
            Ze.a r4 = Ze.C0894a.TYPE_PARAMETER_BOUNDS
            if (r13 != r4) goto L_0x00e7
            r4 = 1
            goto L_0x00e8
        L_0x00e7:
            r4 = 0
        L_0x00e8:
            if (r17 != 0) goto L_0x00ed
            r19 = r4
            goto L_0x0107
        L_0x00ed:
            r19 = r4
            if (r4 != 0) goto L_0x00fa
            java.lang.Object r4 = r3.d
            cf.a r4 = (cf.C0922a) r4
            cf.b r4 = r4.t
            r4.getClass()
        L_0x00fa:
            if (r2 == 0) goto L_0x0103
            Re.h r4 = r2.getAnnotations()
            if (r4 == 0) goto L_0x0103
            r8 = r4
        L_0x0103:
            java.util.ArrayList r1 = ne.C1194l.V0(r8, r1)
        L_0x0107:
            java.lang.Object r4 = r3.d
            cf.a r4 = (cf.C0922a) r4
            Ze.b r4 = r4.q
            r4.getClass()
            java.util.Iterator r4 = r1.iterator()
            r8 = 0
        L_0x0115:
            boolean r20 = r4.hasNext()
            if (r20 == 0) goto L_0x0148
            java.lang.Object r20 = r4.next()
            r21 = r1
            qf.c r1 = Ze.C0895b.d(r20)
            r20 = r4
            java.util.Set r4 = Ze.y.n
            boolean r4 = ne.C1194l.G0(r4, r1)
            if (r4 == 0) goto L_0x0132
            hf.e r1 = hf.C1083e.READ_ONLY
            goto L_0x013c
        L_0x0132:
            java.util.Set r4 = Ze.y.f3975o
            boolean r1 = ne.C1194l.G0(r4, r1)
            if (r1 == 0) goto L_0x0143
            hf.e r1 = hf.C1083e.MUTABLE
        L_0x013c:
            if (r8 == 0) goto L_0x0142
            if (r8 == r1) goto L_0x0142
            r8 = 0
            goto L_0x014a
        L_0x0142:
            r8 = r1
        L_0x0143:
            r4 = r20
            r1 = r21
            goto L_0x0115
        L_0x0148:
            r21 = r1
        L_0x014a:
            java.lang.Object r1 = r3.d
            cf.a r1 = (cf.C0922a) r1
            Ze.b r1 = r1.q
            Ff.j r4 = new Ff.j
            r20 = r3
            r3 = 5
            r4.<init>(r3, r0, r12)
            r1.getClass()
            java.util.Iterator r3 = r21.iterator()
            r21 = r3
            r3 = 0
        L_0x0162:
            boolean r22 = r21.hasNext()
            if (r22 == 0) goto L_0x01f3
            r22 = r5
            java.lang.Object r5 = r21.next()
            java.lang.Object r23 = r4.invoke(r5)
            java.lang.Boolean r23 = (java.lang.Boolean) r23
            r24 = r6
            boolean r6 = r23.booleanValue()
            hf.h r6 = r1.g(r5, r6)
            if (r6 == 0) goto L_0x0186
            r16 = r1
            r23 = r7
        L_0x0184:
            r7 = 0
            goto L_0x01cc
        L_0x0186:
            java.lang.Object r6 = r1.i(r5)
            if (r6 != 0) goto L_0x0192
            r23 = r7
        L_0x018e:
            r16 = r1
            r7 = 0
            goto L_0x01cb
        L_0x0192:
            Ze.C r5 = r1.h(r5)
            if (r5 == 0) goto L_0x019b
        L_0x0198:
            r23 = r7
            goto L_0x01a2
        L_0x019b:
            Ze.t r5 = r1.f3938a
            Ze.v r5 = r5.f3958a
            Ze.C r5 = r5.f3960a
            goto L_0x0198
        L_0x01a2:
            Ze.C r7 = Ze.C.IGNORE
            if (r5 != r7) goto L_0x01aa
            r16 = r1
            r6 = 0
            goto L_0x0184
        L_0x01aa:
            java.lang.Object r7 = r4.invoke(r6)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            hf.h r6 = r1.g(r6, r7)
            if (r6 == 0) goto L_0x018e
            Ze.C r7 = Ze.C.WARN
            if (r5 != r7) goto L_0x01c4
            r5 = 1
        L_0x01bf:
            r16 = r1
            r1 = 1
            r7 = 0
            goto L_0x01c6
        L_0x01c4:
            r5 = 0
            goto L_0x01bf
        L_0x01c6:
            hf.h r6 = hf.C1086h.a(r6, r7, r5, r1)
            goto L_0x01cc
        L_0x01cb:
            r6 = r7
        L_0x01cc:
            if (r3 != 0) goto L_0x01cf
            goto L_0x01e5
        L_0x01cf:
            boolean r1 = r3.b
            if (r6 == 0) goto L_0x01e9
            boolean r5 = r6.equals(r3)
            if (r5 == 0) goto L_0x01da
            goto L_0x01e9
        L_0x01da:
            boolean r5 = r6.b
            if (r5 == 0) goto L_0x01e1
            if (r1 != 0) goto L_0x01e1
            goto L_0x01e9
        L_0x01e1:
            if (r5 != 0) goto L_0x01e7
            if (r1 == 0) goto L_0x01e7
        L_0x01e5:
            r3 = r6
            goto L_0x01e9
        L_0x01e7:
            r3 = r7
            goto L_0x01fa
        L_0x01e9:
            r1 = r16
            r5 = r22
            r7 = r23
            r6 = r24
            goto L_0x0162
        L_0x01f3:
            r22 = r5
            r24 = r6
            r23 = r7
            r7 = 0
        L_0x01fa:
            if (r3 == 0) goto L_0x0211
            hf.d r1 = new hf.d
            hf.g r4 = r3.f4588a
            hf.g r5 = hf.C1085g.NOT_NULL
            if (r4 != r5) goto L_0x0208
            if (r14 == 0) goto L_0x0208
            r5 = 1
            goto L_0x0209
        L_0x0208:
            r5 = 0
        L_0x0209:
            boolean r3 = r3.b
            r1.<init>(r4, r8, r5, r3)
            r6 = 1
            goto L_0x02ac
        L_0x0211:
            if (r17 != 0) goto L_0x0218
            if (r19 == 0) goto L_0x0216
            goto L_0x0218
        L_0x0216:
            Ze.a r13 = Ze.C0894a.TYPE_USE
        L_0x0218:
            Ze.u r1 = r12.b
            if (r1 == 0) goto L_0x0225
            java.util.EnumMap r1 = r1.f3959a
            java.lang.Object r1 = r1.get(r13)
            Ze.m r1 = (Ze.m) r1
            goto L_0x0226
        L_0x0225:
            r1 = r7
        L_0x0226:
            if (r14 == 0) goto L_0x022d
            hf.h r3 = Jd.b.d(r14)
            goto L_0x022e
        L_0x022d:
            r3 = r7
        L_0x022e:
            r4 = 2
            if (r3 == 0) goto L_0x0239
            hf.g r5 = hf.C1085g.NOT_NULL
            r6 = 0
            hf.h r5 = hf.C1086h.a(r3, r5, r6, r4)
            goto L_0x023f
        L_0x0239:
            if (r1 == 0) goto L_0x023e
            hf.h r5 = r1.f3947a
            goto L_0x023f
        L_0x023e:
            r5 = r7
        L_0x023f:
            if (r3 == 0) goto L_0x0244
            hf.g r3 = r3.f4588a
            goto L_0x0245
        L_0x0244:
            r3 = r7
        L_0x0245:
            hf.g r6 = hf.C1085g.NOT_NULL
            if (r3 == r6) goto L_0x0255
            if (r14 == 0) goto L_0x0253
            if (r1 == 0) goto L_0x0253
            boolean r1 = r1.f3948c
            r3 = 1
            if (r1 != r3) goto L_0x0253
            goto L_0x0255
        L_0x0253:
            r1 = 0
            goto L_0x0256
        L_0x0255:
            r1 = 1
        L_0x0256:
            if (r15 == 0) goto L_0x026c
            hf.h r3 = Jd.b.d(r15)
            if (r3 == 0) goto L_0x026c
            hf.g r6 = r3.f4588a
            hf.g r12 = hf.C1085g.NULLABLE
            if (r6 != r12) goto L_0x026d
            hf.g r6 = hf.C1085g.FORCE_FLEXIBILITY
            r12 = 0
            hf.h r3 = hf.C1086h.a(r3, r6, r12, r4)
            goto L_0x026d
        L_0x026c:
            r3 = r7
        L_0x026d:
            if (r3 != 0) goto L_0x0270
            goto L_0x0293
        L_0x0270:
            hf.g r4 = r3.f4588a
            if (r5 != 0) goto L_0x0275
            goto L_0x0292
        L_0x0275:
            hf.g r6 = r5.f4588a
            boolean r12 = r5.b
            boolean r13 = r3.b
            if (r13 == 0) goto L_0x0280
            if (r12 != 0) goto L_0x0280
            goto L_0x0293
        L_0x0280:
            if (r13 != 0) goto L_0x0285
            if (r12 == 0) goto L_0x0285
            goto L_0x0292
        L_0x0285:
            int r12 = r4.compareTo(r6)
            if (r12 >= 0) goto L_0x028c
            goto L_0x0293
        L_0x028c:
            int r4 = r4.compareTo(r6)
            if (r4 <= 0) goto L_0x0293
        L_0x0292:
            r5 = r3
        L_0x0293:
            hf.d r3 = new hf.d
            if (r5 == 0) goto L_0x029a
            hf.g r4 = r5.f4588a
            goto L_0x029b
        L_0x029a:
            r4 = r7
        L_0x029b:
            if (r5 == 0) goto L_0x02a6
            boolean r5 = r5.b
            r6 = 1
            if (r5 != r6) goto L_0x02a4
            r5 = r6
            goto L_0x02a8
        L_0x02a4:
            r5 = 0
            goto L_0x02a8
        L_0x02a6:
            r6 = 1
            goto L_0x02a4
        L_0x02a8:
            r3.<init>(r4, r8, r1, r5)
            r1 = r3
        L_0x02ac:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r4 = r23.iterator()
        L_0x02b5:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0333
            java.lang.Object r5 = r4.next()
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = ne.C1194l.O0(r11, r5)
            hf.a r5 = (hf.C1079a) r5
            if (r5 == 0) goto L_0x032c
            Kf.d r5 = r5.f4583a
            if (r5 == 0) goto L_0x032c
            hf.g r8 = Jd.b.g(r5)
            if (r8 != 0) goto L_0x02e3
            r12 = r5
            Hf.x r12 = (Hf.C0774x) r12
            Hf.x r12 = Hf.C0754c.g(r12)
            if (r12 == 0) goto L_0x02e1
            hf.g r12 = Jd.b.g(r12)
            goto L_0x02e4
        L_0x02e1:
            r12 = r7
            goto L_0x02e4
        L_0x02e3:
            r12 = r8
        L_0x02e4:
            java.lang.String r13 = Pe.d.f3633a
            Hf.B r13 = r10.o0(r5)
            qf.e r13 = Jd.b.f(r13)
            java.util.HashMap r14 = Pe.d.k
            boolean r13 = r14.containsKey(r13)
            if (r13 == 0) goto L_0x02f9
            hf.e r13 = hf.C1083e.READ_ONLY
            goto L_0x030d
        L_0x02f9:
            Hf.B r13 = r10.f(r5)
            qf.e r13 = Jd.b.f(r13)
            java.util.HashMap r14 = Pe.d.f3637j
            boolean r13 = r14.containsKey(r13)
            if (r13 == 0) goto L_0x030c
            hf.e r13 = hf.C1083e.MUTABLE
            goto L_0x030d
        L_0x030c:
            r13 = r7
        L_0x030d:
            boolean r14 = r10.G(r5)
            if (r14 != 0) goto L_0x0320
            Hf.x r5 = (Hf.C0774x) r5
            Hf.c0 r5 = r5.x0()
            boolean r5 = r5 instanceof hf.C1084f
            if (r5 == 0) goto L_0x031e
            goto L_0x0320
        L_0x031e:
            r5 = 0
            goto L_0x0321
        L_0x0320:
            r5 = r6
        L_0x0321:
            hf.d r14 = new hf.d
            if (r12 == r8) goto L_0x0327
            r8 = r6
            goto L_0x0328
        L_0x0327:
            r8 = 0
        L_0x0328:
            r14.<init>(r12, r13, r5, r8)
            goto L_0x032d
        L_0x032c:
            r14 = r7
        L_0x032d:
            if (r14 == 0) goto L_0x02b5
            r3.add(r14)
            goto L_0x02b5
        L_0x0333:
            if (r11 != 0) goto L_0x0339
            if (r22 == 0) goto L_0x0339
            r4 = r6
            goto L_0x033a
        L_0x0339:
            r4 = 0
        L_0x033a:
            if (r11 != 0) goto L_0x0349
            boolean r5 = r2 instanceof Te.Q
            if (r5 == 0) goto L_0x0349
            r5 = r2
            Te.Q r5 = (Te.Q) r5
            Hf.x r5 = r5.n
            if (r5 == 0) goto L_0x0349
            r5 = r6
            goto L_0x034a
        L_0x0349:
            r5 = 0
        L_0x034a:
            hf.g r8 = r1.f4586a
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r12 = r3.iterator()
        L_0x0355:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x036f
            java.lang.Object r13 = r12.next()
            hf.d r13 = (hf.C1082d) r13
            boolean r14 = r13.d
            if (r14 == 0) goto L_0x0367
            r13 = r7
            goto L_0x0369
        L_0x0367:
            hf.g r13 = r13.f4586a
        L_0x0369:
            if (r13 == 0) goto L_0x0355
            r10.add(r13)
            goto L_0x0355
        L_0x036f:
            java.util.Set r10 = ne.C1194l.p1(r10)
            boolean r12 = r1.d
            if (r12 == 0) goto L_0x0379
            r12 = r7
            goto L_0x037a
        L_0x0379:
            r12 = r8
        L_0x037a:
            hf.g r13 = hf.C1085g.FORCE_FLEXIBILITY
            if (r12 != r13) goto L_0x037f
            goto L_0x038a
        L_0x037f:
            hf.g r13 = hf.C1085g.NOT_NULL
            hf.g r14 = hf.C1085g.NULLABLE
            java.lang.Object r10 = B1.a.R(r10, r13, r14, r12, r4)
            r13 = r10
            hf.g r13 = (hf.C1085g) r13
        L_0x038a:
            if (r13 != 0) goto L_0x03be
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r12 = r3.iterator()
        L_0x0395:
            boolean r14 = r12.hasNext()
            if (r14 == 0) goto L_0x03a9
            java.lang.Object r14 = r12.next()
            hf.d r14 = (hf.C1082d) r14
            hf.g r14 = r14.f4586a
            if (r14 == 0) goto L_0x0395
            r10.add(r14)
            goto L_0x0395
        L_0x03a9:
            java.util.Set r10 = ne.C1194l.p1(r10)
            hf.g r12 = hf.C1085g.FORCE_FLEXIBILITY
            if (r8 != r12) goto L_0x03b2
            goto L_0x03bf
        L_0x03b2:
            hf.g r12 = hf.C1085g.NOT_NULL
            hf.g r14 = hf.C1085g.NULLABLE
            java.lang.Object r8 = B1.a.R(r10, r12, r14, r8, r4)
            r12 = r8
            hf.g r12 = (hf.C1085g) r12
            goto L_0x03bf
        L_0x03be:
            r12 = r13
        L_0x03bf:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r10 = r3.iterator()
        L_0x03c8:
            boolean r14 = r10.hasNext()
            if (r14 == 0) goto L_0x03dc
            java.lang.Object r14 = r10.next()
            hf.d r14 = (hf.C1082d) r14
            hf.e r14 = r14.b
            if (r14 == 0) goto L_0x03c8
            r8.add(r14)
            goto L_0x03c8
        L_0x03dc:
            java.util.Set r8 = ne.C1194l.p1(r8)
            hf.e r10 = hf.C1083e.MUTABLE
            hf.e r14 = hf.C1083e.READ_ONLY
            hf.e r15 = r1.b
            java.lang.Object r4 = B1.a.R(r8, r10, r14, r15, r4)
            hf.e r4 = (hf.C1083e) r4
            if (r12 == 0) goto L_0x03f9
            if (r31 != 0) goto L_0x03f9
            if (r5 == 0) goto L_0x03f7
            hf.g r5 = hf.C1085g.NULLABLE
            if (r12 != r5) goto L_0x03f7
            goto L_0x03f9
        L_0x03f7:
            r8 = r12
            goto L_0x03fa
        L_0x03f9:
            r8 = r7
        L_0x03fa:
            hf.g r5 = hf.C1085g.NOT_NULL
            if (r8 != r5) goto L_0x041f
            boolean r1 = r1.f4587c
            if (r1 != 0) goto L_0x041d
            boolean r1 = r3.isEmpty()
            if (r1 == 0) goto L_0x0409
            goto L_0x041f
        L_0x0409:
            java.util.Iterator r1 = r3.iterator()
        L_0x040d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x041f
            java.lang.Object r3 = r1.next()
            hf.d r3 = (hf.C1082d) r3
            boolean r3 = r3.f4587c
            if (r3 == 0) goto L_0x040d
        L_0x041d:
            r1 = r6
            goto L_0x0420
        L_0x041f:
            r1 = 0
        L_0x0420:
            if (r8 == 0) goto L_0x0426
            if (r13 == r12) goto L_0x0426
            r3 = r6
            goto L_0x0427
        L_0x0426:
            r3 = 0
        L_0x0427:
            hf.d r5 = new hf.d
            r5.<init>(r8, r4, r1, r3)
            r9[r11] = r5
            int r11 = r11 + 1
            r1 = r28
            r4 = r18
            r3 = r20
            r5 = r22
            r7 = r23
            r6 = r24
            goto L_0x007c
        L_0x043e:
            Ff.j r1 = new Ff.j
            r2 = 6
            r3 = r30
            r1.<init>(r2, r3, r9)
            boolean r0 = r0.b
            Hf.c0 r2 = r28.x0()
            r12 = 0
            Q2.a r0 = d(r2, r1, r12, r0)
            java.lang.Object r0 = r0.b
            Hf.x r0 = (Hf.C0774x) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: hf.C1081c.a(Jd.b, Hf.x, java.util.List, hf.p, boolean):Hf.x");
    }

    public C0774x b(C0916a aVar, C0812b bVar, boolean z, a aVar2, C0894a aVar3, C1094p pVar, boolean z3, b bVar2) {
        Jd.b bVar3 = new Jd.b(bVar, z, aVar2, aVar3, false);
        C0774x xVar = (C0774x) bVar2.invoke(aVar);
        Collection h5 = aVar.h();
        j.d(h5, "getOverriddenDescriptors(...)");
        Iterable<C0814d> iterable = h5;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C0814d dVar : iterable) {
            j.b(dVar);
            arrayList.add((C0774x) bVar2.invoke(dVar));
        }
        return a(bVar3, xVar, arrayList, pVar, z3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02dc, code lost:
        if (r12 == null) goto L_0x02df;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0231  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0251  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0330 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0217  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList e(B0.a r26, java.util.Collection r27) {
        /*
            r25 = this;
            r0 = r26
            hf.m r1 = hf.C1091m.f4593h
            java.lang.String r2 = "c"
            kotlin.jvm.internal.j.e(r0, r2)
            r2 = r27
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r5 = ne.C1196n.w0(r2, r4)
            r3.<init>(r5)
            java.util.Iterator r2 = r2.iterator()
        L_0x001c:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0343
            java.lang.Object r5 = r2.next()
            Qe.d r5 = (Qe.C0814d) r5
            boolean r6 = r5 instanceof bf.C0916a
            if (r6 != 0) goto L_0x002f
            r8 = r4
            goto L_0x033d
        L_0x002f:
            Qe.c r6 = r5.b()
            Qe.c r7 = Qe.C0813c.FAKE_OVERRIDE
            r8 = 1
            if (r6 != r7) goto L_0x0048
            Qe.d r6 = r5.a()
            java.util.Collection r6 = r6.h()
            int r6 = r6.size()
            if (r6 != r8) goto L_0x0048
            goto L_0x02df
        L_0x0048:
            Qe.i r6 = Qe.C0833x.g(r5)
            if (r6 != 0) goto L_0x0056
            r6 = r5
            Bf.a r6 = (Bf.a) r6
            Re.h r6 = r6.getAnnotations()
            goto L_0x00bd
        L_0x0056:
            boolean r9 = r6 instanceof df.C0946i
            if (r9 == 0) goto L_0x005d
            df.i r6 = (df.C0946i) r6
            goto L_0x005e
        L_0x005d:
            r6 = 0
        L_0x005e:
            if (r6 == 0) goto L_0x0069
            me.m r6 = r6.n
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            goto L_0x006a
        L_0x0069:
            r6 = 0
        L_0x006a:
            r9 = r6
            java.util.Collection r9 = (java.util.Collection) r9
            if (r9 == 0) goto L_0x00b6
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x0076
            goto L_0x00b6
        L_0x0076:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r9 = new java.util.ArrayList
            int r10 = ne.C1196n.w0(r6, r4)
            r9.<init>(r10)
            java.util.Iterator r6 = r6.iterator()
        L_0x0085:
            boolean r10 = r6.hasNext()
            if (r10 == 0) goto L_0x009a
            java.lang.Object r10 = r6.next()
            We.e r10 = (We.C0893e) r10
            df.f r11 = new df.f
            r11.<init>(r0, r10, r8)
            r9.add(r11)
            goto L_0x0085
        L_0x009a:
            r6 = r5
            Bf.a r6 = (Bf.a) r6
            Re.h r6 = r6.getAnnotations()
            java.util.ArrayList r6 = ne.C1194l.V0(r6, r9)
            boolean r9 = r6.isEmpty()
            if (r9 == 0) goto L_0x00ae
            Re.f r6 = Re.g.f3692a
            goto L_0x00bd
        L_0x00ae:
            Re.i r9 = new Re.i
            r10 = 0
            r9.<init>(r10, r6)
            r6 = r9
            goto L_0x00bd
        L_0x00b6:
            r6 = r5
            Bf.a r6 = (Bf.a) r6
            Re.h r6 = r6.getAnnotations()
        L_0x00bd:
            B0.a r13 = Gd.a.l(r0, r6)
            boolean r6 = r5 instanceof bf.h
            if (r6 == 0) goto L_0x00d2
            r6 = r5
            Te.H r6 = (Te.H) r6
            Te.I r6 = r6.f3756A
            if (r6 == 0) goto L_0x00d2
            boolean r9 = r6.f3747i
            if (r9 != 0) goto L_0x00d2
            r11 = r6
            goto L_0x00d3
        L_0x00d2:
            r11 = r5
        L_0x00d3:
            Te.u r6 = r5.H()
            if (r6 == 0) goto L_0x0118
            boolean r6 = r11 instanceof Qe.C0831v
            if (r6 == 0) goto L_0x00e1
            r6 = r11
            Qe.v r6 = (Qe.C0831v) r6
            goto L_0x00e2
        L_0x00e1:
            r6 = 0
        L_0x00e2:
            if (r6 == 0) goto L_0x00ef
            bf.e r9 = bf.g.f4001J
            java.lang.Object r6 = r6.r0(r9)
            Te.Q r6 = (Te.Q) r6
            r16 = r6
            goto L_0x00f1
        L_0x00ef:
            r16 = 0
        L_0x00f1:
            hf.m r22 = hf.C1091m.e
            r15 = r5
            bf.a r15 = (bf.C0916a) r15
            if (r16 == 0) goto L_0x0107
            r6 = r16
            Bf.a r6 = (Bf.a) r6
            Re.h r6 = r6.getAnnotations()
            B0.a r6 = Gd.a.l(r13, r6)
            r18 = r6
            goto L_0x0109
        L_0x0107:
            r18 = r13
        L_0x0109:
            Ze.a r19 = Ze.C0894a.VALUE_PARAMETER
            r17 = 0
            r20 = 0
            r21 = 0
            r14 = r25
            Hf.x r6 = r14.b(r15, r16, r17, r18, r19, r20, r21, r22)
            goto L_0x0119
        L_0x0118:
            r6 = 0
        L_0x0119:
            boolean r9 = r5 instanceof bf.g
            if (r9 == 0) goto L_0x0121
            r9 = r5
            bf.g r9 = (bf.g) r9
            goto L_0x0122
        L_0x0121:
            r9 = 0
        L_0x0122:
            if (r9 == 0) goto L_0x015f
            Qe.l r10 = r9.g()
            java.lang.String r12 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r10, r12)
            Qe.f r10 = (Qe.C0816f) r10
            r12 = 3
            java.lang.String r9 = a.C0068a.m(r9, r12)
            java.lang.String r9 = L2.a.z(r10, r9)
            if (r9 == 0) goto L_0x015f
            java.util.LinkedHashMap r10 = hf.C1089k.d
            java.lang.Object r9 = r10.get(r9)
            hf.l r9 = (hf.C1090l) r9
            if (r9 == 0) goto L_0x015f
            java.lang.String r10 = r9.f4592c
            if (r10 == 0) goto L_0x0159
            java.lang.String r12 = "2."
            boolean r12 = Tf.v.t0(r10, r12)
            if (r12 != r8) goto L_0x0151
            goto L_0x0159
        L_0x0151:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Check failed."
            r0.<init>(r1)
            throw r0
        L_0x0159:
            if (r10 != 0) goto L_0x015c
            goto L_0x0160
        L_0x015c:
            hf.l r9 = r9.d
            goto L_0x0160
        L_0x015f:
            r9 = 0
        L_0x0160:
            if (r9 == 0) goto L_0x0171
            java.util.ArrayList r10 = r9.b
            r10.size()
            r10 = r5
            bf.g r10 = (bf.g) r10
            java.util.List r10 = r10.B()
            r10.size()
        L_0x0171:
            java.lang.Object r10 = r0.d
            cf.a r10 = (cf.C0922a) r10
            Ze.t r10 = r10.v
            java.lang.String r12 = "javaTypeEnhancementState"
            kotlin.jvm.internal.j.e(r10, r12)
            Ze.s r10 = Ze.s.d
            qf.c r12 = Ze.q.f3953a
            java.lang.Object r10 = r10.invoke(r12)
            Ze.C r12 = Ze.C.STRICT
            r23 = 0
            if (r10 != r12) goto L_0x019f
            boolean r10 = r5 instanceof Qe.C0831v
            if (r10 == 0) goto L_0x01a8
            bf.e r10 = bf.g.f4002K
            java.lang.Object r10 = r5.r0(r10)
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
            boolean r10 = kotlin.jvm.internal.j.a(r10, r12)
            if (r10 == 0) goto L_0x01a8
            r21 = r8
            goto L_0x01aa
        L_0x019f:
            java.lang.Object r10 = r13.d
            cf.a r10 = (cf.C0922a) r10
            cf.b r10 = r10.t
            r10.getClass()
        L_0x01a8:
            r21 = r23
        L_0x01aa:
            java.util.List r10 = r11.B()
            java.lang.String r12 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r10, r12)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r14 = new java.util.ArrayList
            int r15 = ne.C1196n.w0(r10, r4)
            r14.<init>(r15)
            java.util.Iterator r10 = r10.iterator()
        L_0x01c2:
            boolean r15 = r10.hasNext()
            if (r15 == 0) goto L_0x0212
            java.lang.Object r15 = r10.next()
            Te.Q r15 = (Te.Q) r15
            if (r9 == 0) goto L_0x01dd
            java.util.ArrayList r4 = r9.b
            int r7 = r15.f3770j
            java.lang.Object r4 = ne.C1194l.O0(r7, r4)
            hf.p r4 = (hf.C1094p) r4
            r20 = r4
            goto L_0x01df
        L_0x01dd:
            r20 = 0
        L_0x01df:
            Df.b r4 = new Df.b
            r7 = 23
            r4.<init>(r7, r15)
            r16 = r15
            r15 = r5
            bf.a r15 = (bf.C0916a) r15
            if (r16 == 0) goto L_0x01fc
            r7 = r16
            Bf.a r7 = (Bf.a) r7
            Re.h r7 = r7.getAnnotations()
            B0.a r7 = Gd.a.l(r13, r7)
            r18 = r7
            goto L_0x01fe
        L_0x01fc:
            r18 = r13
        L_0x01fe:
            Ze.a r19 = Ze.C0894a.VALUE_PARAMETER
            r17 = 0
            r22 = r4
            r4 = r14
            r14 = r25
            Hf.x r7 = r14.b(r15, r16, r17, r18, r19, r20, r21, r22)
            r4.add(r7)
            r14 = r4
            r4 = 10
            goto L_0x01c2
        L_0x0212:
            r4 = r14
            boolean r7 = r5 instanceof Qe.O
            if (r7 == 0) goto L_0x021b
            r7 = r5
            Qe.O r7 = (Qe.O) r7
            goto L_0x021c
        L_0x021b:
            r7 = 0
        L_0x021c:
            if (r7 == 0) goto L_0x0228
            boolean r7 = Gd.a.M(r7)
            if (r7 != r8) goto L_0x0228
            Ze.a r7 = Ze.C0894a.FIELD
        L_0x0226:
            r14 = r7
            goto L_0x022b
        L_0x0228:
            Ze.a r7 = Ze.C0894a.METHOD_RETURN_TYPE
            goto L_0x0226
        L_0x022b:
            if (r9 == 0) goto L_0x0231
            hf.p r7 = r9.f4591a
            r15 = r7
            goto L_0x0232
        L_0x0231:
            r15 = 0
        L_0x0232:
            hf.m r17 = hf.C1091m.f
            r10 = r5
            bf.a r10 = (bf.C0916a) r10
            r7 = r12
            r12 = 1
            r16 = 0
            r9 = r25
            Hf.x r11 = r9.b(r10, r11, r12, r13, r14, r15, r16, r17)
            Hf.x r9 = r5.getReturnType()
            kotlin.jvm.internal.j.b(r9)
            r12 = 0
            boolean r9 = Hf.a0.c(r9, r1, r12)
            java.lang.String r13 = "getType(...)"
            if (r9 != 0) goto L_0x02a4
            Te.u r9 = r5.H()
            if (r9 == 0) goto L_0x0260
            Hf.x r9 = r9.getType()
            boolean r9 = Hf.a0.c(r9, r1, r12)
            goto L_0x0262
        L_0x0260:
            r9 = r23
        L_0x0262:
            if (r9 != 0) goto L_0x02a4
            java.util.List r9 = r5.B()
            kotlin.jvm.internal.j.d(r9, r7)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            boolean r7 = r9 instanceof java.util.Collection
            if (r7 == 0) goto L_0x027d
            r7 = r9
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x027d
        L_0x027a:
            r7 = r23
            goto L_0x029e
        L_0x027d:
            java.util.Iterator r7 = r9.iterator()
        L_0x0281:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x027a
            java.lang.Object r9 = r7.next()
            Te.Q r9 = (Te.Q) r9
            Te.S r9 = (Te.S) r9
            Hf.x r9 = r9.getType()
            kotlin.jvm.internal.j.d(r9, r13)
            r12 = 0
            boolean r9 = Hf.a0.c(r9, r1, r12)
            if (r9 == 0) goto L_0x0281
            r7 = r8
        L_0x029e:
            if (r7 == 0) goto L_0x02a1
            goto L_0x02a4
        L_0x02a1:
            r7 = r23
            goto L_0x02a5
        L_0x02a4:
            r7 = r8
        L_0x02a5:
            if (r7 == 0) goto L_0x02b4
            Ze.g r7 = new Ze.g
            r7.<init>()
            me.i r12 = new me.i
            bf.e r9 = wf.C1349b.f5166a
            r12.<init>(r9, r7)
            goto L_0x02b5
        L_0x02b4:
            r12 = 0
        L_0x02b5:
            if (r6 != 0) goto L_0x02e2
            if (r11 != 0) goto L_0x02e2
            boolean r7 = r4.isEmpty()
            if (r7 == 0) goto L_0x02c2
        L_0x02bf:
            r8 = r23
            goto L_0x02da
        L_0x02c2:
            java.util.Iterator r7 = r4.iterator()
        L_0x02c6:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x02bf
            java.lang.Object r9 = r7.next()
            Hf.x r9 = (Hf.C0774x) r9
            if (r9 == 0) goto L_0x02d6
            r9 = r8
            goto L_0x02d8
        L_0x02d6:
            r9 = r23
        L_0x02d8:
            if (r9 == 0) goto L_0x02c6
        L_0x02da:
            if (r8 != 0) goto L_0x02e2
            if (r12 == 0) goto L_0x02df
            goto L_0x02e2
        L_0x02df:
            r8 = 10
            goto L_0x033d
        L_0x02e2:
            if (r6 != 0) goto L_0x02f0
            Te.u r6 = r5.H()
            if (r6 == 0) goto L_0x02ef
            Hf.x r6 = r6.getType()
            goto L_0x02f0
        L_0x02ef:
            r6 = 0
        L_0x02f0:
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r9 = ne.C1196n.w0(r4, r8)
            r7.<init>(r9)
            java.util.Iterator r4 = r4.iterator()
        L_0x02ff:
            r9 = r23
            boolean r14 = r4.hasNext()
            if (r14 == 0) goto L_0x0330
            java.lang.Object r14 = r4.next()
            int r23 = r9 + 1
            if (r9 < 0) goto L_0x032a
            Hf.x r14 = (Hf.C0774x) r14
            if (r14 != 0) goto L_0x0326
            java.util.List r14 = r5.B()
            java.lang.Object r9 = r14.get(r9)
            Te.Q r9 = (Te.Q) r9
            Te.S r9 = (Te.S) r9
            Hf.x r14 = r9.getType()
            kotlin.jvm.internal.j.d(r14, r13)
        L_0x0326:
            r7.add(r14)
            goto L_0x02ff
        L_0x032a:
            ne.C1195m.v0()
            r24 = 0
            throw r24
        L_0x0330:
            if (r11 != 0) goto L_0x0339
            Hf.x r11 = r5.getReturnType()
            kotlin.jvm.internal.j.b(r11)
        L_0x0339:
            bf.a r5 = r10.p(r6, r7, r11, r12)
        L_0x033d:
            r3.add(r5)
            r4 = r8
            goto L_0x001c
        L_0x0343:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: hf.C1081c.e(B0.a, java.util.Collection):java.util.ArrayList");
    }
}
