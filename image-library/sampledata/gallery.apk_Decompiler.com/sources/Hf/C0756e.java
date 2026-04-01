package Hf;

import Af.o;
import Ff.w;
import If.b;
import Jf.l;
import Kf.c;
import Kf.d;
import Kf.e;
import Kf.g;
import Kf.i;
import Lf.a;
import Qe.C0819i;
import Qe.U;
import Qe.V;
import Qf.f;
import Qf.h;
import Qf.k;
import Te.C0844e;
import Te.C0845f;
import Te.C0852m;
import Te.O;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import o1.C0246a;

/* renamed from: Hf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0756e {

    /* renamed from: a  reason: collision with root package name */
    public static final C0756e f3442a = new Object();

    public static final boolean b(b bVar, e eVar) {
        if (bVar.B(eVar)) {
            return true;
        }
        if (!(eVar instanceof c)) {
            return false;
        }
        P i02 = bVar.i0(bVar.f0((c) eVar));
        if (bVar.n0(i02) || !bVar.B(bVar.f(bVar.V(i02)))) {
            return false;
        }
        return true;
    }

    public static final boolean c(b bVar, L l, e eVar, e eVar2, boolean z) {
        Set<d> m02 = bVar.m0(eVar);
        if (m02 != null && m02.isEmpty()) {
            return false;
        }
        for (d dVar : m02) {
            if (j.a(bVar.K(dVar), bVar.q0(eVar2))) {
                return true;
            }
            if (z && m(f3442a, l, eVar2, dVar)) {
                return true;
            }
        }
        return false;
    }

    public static List d(L l, e eVar, g gVar) {
        C0754c cVar;
        K k = K.f3429c;
        b bVar = l.f3432c;
        bVar.s0(eVar, gVar);
        if (bVar.l(gVar) || !bVar.t(eVar)) {
            if (!bVar.j0(gVar)) {
                f fVar = new f();
                l.b();
                ArrayDeque arrayDeque = l.g;
                j.b(arrayDeque);
                h hVar = l.f3433h;
                j.b(hVar);
                arrayDeque.push(eVar);
                while (!arrayDeque.isEmpty()) {
                    if (hVar.p() <= 1000) {
                        e eVar2 = (e) arrayDeque.pop();
                        j.b(eVar2);
                        if (hVar.add(eVar2)) {
                            e i2 = bVar.i(eVar2, Kf.b.FOR_SUBTYPING);
                            if (i2 == null) {
                                i2 = eVar2;
                            }
                            if (bVar.b(bVar.q0(i2), gVar)) {
                                fVar.add(i2);
                                cVar = k;
                            } else if (bVar.c0(i2) == 0) {
                                cVar = K.b;
                            } else {
                                cVar = bVar.D(i2);
                            }
                            if (cVar.equals(k)) {
                                cVar = null;
                            }
                            if (cVar != null) {
                                for (d D : bVar.l0(bVar.q0(eVar2))) {
                                    arrayDeque.add(cVar.D(l, D));
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException(("Too many supertypes for type: " + eVar + ". Supertypes = " + C1194l.R0(hVar, (String) null, (String) null, (String) null, (Ae.b) null, 63)).toString());
                    }
                }
                l.a();
                return fVar;
            } else if (bVar.b(bVar.q0(eVar), gVar)) {
                B i7 = bVar.i(eVar, Kf.b.FOR_SUBTYPING);
                if (i7 != null) {
                    eVar = i7;
                }
                return C0246a.e0(eVar);
            }
        }
        return C1202t.d;
    }

    public static List e(L l, e eVar, g gVar) {
        List d = d(l, eVar, gVar);
        b bVar = l.f3432c;
        if (d.size() >= 2) {
            ArrayList arrayList = new ArrayList();
            for (Object next : d) {
                Kf.f e02 = bVar.e0((e) next);
                int S = bVar.S(e02);
                int i2 = 0;
                while (true) {
                    if (i2 < S) {
                        if (bVar.C(bVar.V(bVar.E(e02, i2))) != null) {
                            break;
                        }
                        i2++;
                    } else {
                        arrayList.add(next);
                        break;
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return d;
    }

    public static boolean g(L l, d dVar, d dVar2) {
        j.e(dVar, "a");
        j.e(dVar2, "b");
        b bVar = l.f3432c;
        if (dVar == dVar2) {
            return true;
        }
        if (k(bVar, dVar) && k(bVar, dVar2)) {
            c0 c5 = l.c(l.d(dVar));
            c0 c6 = l.c(l.d(dVar2));
            B o0 = bVar.o0(c5);
            if (!bVar.b(bVar.K(c5), bVar.K(c6))) {
                return false;
            }
            if (bVar.c0(o0) == 0) {
                if (bVar.N(c5) || bVar.N(c6) || bVar.h(o0) == bVar.h(bVar.o0(c6))) {
                    return true;
                }
                return false;
            }
        }
        C0756e eVar = f3442a;
        if (!m(eVar, l, dVar, dVar2) || !m(eVar, l, dVar2, dVar)) {
            return false;
        }
        return true;
    }

    public static V j(b bVar, d dVar, d dVar2) {
        c0 V;
        boolean z;
        int c02 = bVar.c0(dVar);
        int i2 = 0;
        while (true) {
            P p6 = null;
            if (i2 >= c02) {
                return null;
            }
            P L = bVar.L(dVar, i2);
            if (!bVar.n0(L)) {
                p6 = L;
            }
            if (!(p6 == null || (V = bVar.V(p6)) == null)) {
                if (!bVar.M(bVar.Z(bVar.o0(V))) || !bVar.M(bVar.Z(bVar.o0(dVar2)))) {
                    z = false;
                } else {
                    z = true;
                }
                if (!V.equals(dVar2) && (!z || !j.a(bVar.K(V), bVar.K(dVar2)))) {
                    V j2 = j(bVar, V, dVar2);
                    if (j2 != null) {
                        return j2;
                    }
                }
            }
            i2++;
        }
        return bVar.u(bVar.K(dVar), i2);
    }

    public static boolean k(b bVar, d dVar) {
        if (!bVar.Q(bVar.K(dVar))) {
            return false;
        }
        bVar.v(dVar);
        if (bVar.G(dVar) || bVar.c(dVar) || bVar.s(dVar)) {
            return false;
        }
        return true;
    }

    public static boolean l(L l, Kf.f fVar, e eVar) {
        boolean z;
        j.e(fVar, "capturedSubArguments");
        b bVar = l.f3432c;
        M q0 = bVar.q0(eVar);
        int S = bVar.S(fVar);
        int t02 = bVar.t0(q0);
        if (S == t02 && S == bVar.c0(eVar)) {
            for (int i2 = 0; i2 < t02; i2++) {
                P L = bVar.L(eVar, i2);
                if (!bVar.n0(L)) {
                    c0 V = bVar.V(L);
                    P E = bVar.E(fVar, i2);
                    bVar.j(E);
                    i iVar = i.INV;
                    c0 V5 = bVar.V(E);
                    i A10 = bVar.A(bVar.u(q0, i2));
                    i j2 = bVar.j(L);
                    j.e(A10, "declared");
                    j.e(j2, "useSite");
                    if (A10 == iVar) {
                        A10 = j2;
                    } else if (!(j2 == iVar || A10 == j2)) {
                        A10 = null;
                    }
                    if (A10 == null) {
                        return l.f3431a;
                    }
                    if (A10 == iVar) {
                        n(bVar, V5, V);
                        n(bVar, V, V5);
                    }
                    int i7 = l.f;
                    if (i7 <= 100) {
                        l.f = i7 + 1;
                        int i8 = C0755d.f3441a[A10.ordinal()];
                        if (i8 != 1) {
                            C0756e eVar2 = f3442a;
                            if (i8 == 2) {
                                z = m(eVar2, l, V5, V);
                            } else if (i8 == 3) {
                                z = m(eVar2, l, V, V5);
                            } else {
                                throw new RuntimeException();
                            }
                        } else {
                            z = g(l, V5, V);
                        }
                        l.f--;
                        if (!z) {
                        }
                    } else {
                        throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + V5).toString());
                    }
                }
            }
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r6v3, types: [Kf.f, java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02ec, code lost:
        if (c(r3, r0, r9, r8, true) != false) goto L_0x02ee;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02f7  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0416  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x0432  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x0510  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m(Hf.C0756e r24, Hf.L r25, Kf.d r26, Kf.d r27) {
        /*
            r0 = r25
            r1 = r26
            r2 = r27
            If.b r3 = r0.f3432c
            java.lang.String r4 = "subType"
            kotlin.jvm.internal.j.e(r1, r4)
            java.lang.String r4 = "superType"
            kotlin.jvm.internal.j.e(r2, r4)
            r4 = 1
            if (r1 != r2) goto L_0x0019
        L_0x0015:
            r16 = r4
            goto L_0x052b
        L_0x0019:
            r0.getClass()
            r5 = 0
            Hf.x r1 = r25.d(r26)
            Hf.c0 r1 = r0.c(r1)
            Hf.x r2 = r0.d(r2)
            Hf.c0 r2 = r0.c(r2)
            Hf.B r6 = r3.o0(r1)
            Hf.B r7 = r3.f(r2)
            boolean r8 = r3.y(r6)
            if (r8 != 0) goto L_0x0138
            boolean r8 = r3.y(r7)
            if (r8 == 0) goto L_0x0043
            goto L_0x0138
        L_0x0043:
            r3.k(r6)
            r3.d(r6)
            r3.d(r7)
            Hf.m r8 = r3.m(r7)
            if (r8 == 0) goto L_0x0058
            Hf.B r8 = r3.T(r8)
            if (r8 != 0) goto L_0x0059
        L_0x0058:
            r8 = r7
        L_0x0059:
            Kf.c r8 = r3.e(r8)
            if (r8 == 0) goto L_0x0064
            Hf.c0 r10 = r3.r(r8)
            goto L_0x0065
        L_0x0064:
            r10 = 0
        L_0x0065:
            Hf.e r11 = f3442a
            if (r8 == 0) goto L_0x00ad
            if (r10 == 0) goto L_0x00ad
            boolean r8 = r3.h(r7)
            if (r8 == 0) goto L_0x0076
            Kf.d r10 = r3.Y(r10)
            goto L_0x0080
        L_0x0076:
            boolean r8 = r3.G(r7)
            if (r8 == 0) goto L_0x0080
            Hf.c0 r10 = r3.n(r10)
        L_0x0080:
            Hf.J r8 = Hf.J.CHECK_SUBTYPE_AND_LOWER
            int[] r12 = Hf.C0755d.b
            int r8 = r8.ordinal()
            r8 = r12[r8]
            if (r8 == r4) goto L_0x00a3
            r12 = 2
            if (r8 == r12) goto L_0x0099
            r10 = 3
            if (r8 != r10) goto L_0x0093
            goto L_0x00ad
        L_0x0093:
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        L_0x0099:
            boolean r8 = m(r11, r0, r6, r10)
            if (r8 == 0) goto L_0x00ad
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            goto L_0x016d
        L_0x00a3:
            boolean r6 = m(r11, r0, r6, r10)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            goto L_0x016d
        L_0x00ad:
            Hf.M r8 = r3.q0(r7)
            boolean r10 = r3.k0(r8)
            if (r10 == 0) goto L_0x00ec
            r3.h(r7)
            java.util.Collection r7 = r3.l0(r8)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            boolean r8 = r7 instanceof java.util.Collection
            if (r8 == 0) goto L_0x00cf
            r8 = r7
            java.util.Collection r8 = (java.util.Collection) r8
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x00cf
        L_0x00cd:
            r6 = r4
            goto L_0x00e6
        L_0x00cf:
            java.util.Iterator r7 = r7.iterator()
        L_0x00d3:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00cd
            java.lang.Object r8 = r7.next()
            Kf.d r8 = (Kf.d) r8
            boolean r8 = m(r11, r0, r6, r8)
            if (r8 != 0) goto L_0x00d3
            r6 = r5
        L_0x00e6:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            goto L_0x016d
        L_0x00ec:
            Hf.M r8 = r3.q0(r6)
            boolean r10 = r6 instanceof Kf.c
            if (r10 != 0) goto L_0x0123
            boolean r10 = r3.k0(r8)
            if (r10 == 0) goto L_0x0136
            java.util.Collection r8 = r3.l0(r8)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            boolean r10 = r8 instanceof java.util.Collection
            if (r10 == 0) goto L_0x010e
            r10 = r8
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x010e
            goto L_0x0123
        L_0x010e:
            java.util.Iterator r8 = r8.iterator()
        L_0x0112:
            boolean r10 = r8.hasNext()
            if (r10 == 0) goto L_0x0123
            java.lang.Object r10 = r8.next()
            Kf.d r10 = (Kf.d) r10
            boolean r10 = r10 instanceof Kf.c
            if (r10 != 0) goto L_0x0112
            goto L_0x0136
        L_0x0123:
            Qe.V r6 = j(r3, r7, r6)
            if (r6 == 0) goto L_0x0136
            Hf.M r7 = r3.q0(r7)
            boolean r6 = r3.O(r6, r7)
            if (r6 == 0) goto L_0x0136
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            goto L_0x016d
        L_0x0136:
            r6 = 0
            goto L_0x016d
        L_0x0138:
            boolean r8 = r0.f3431a
            if (r8 == 0) goto L_0x013f
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            goto L_0x016d
        L_0x013f:
            boolean r8 = r3.h(r6)
            if (r8 == 0) goto L_0x014e
            boolean r8 = r3.h(r7)
            if (r8 != 0) goto L_0x014e
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            goto L_0x016d
        L_0x014e:
            Hf.B r6 = r3.I(r6, r5)
            Hf.B r7 = r3.I(r7, r5)
            java.lang.String r8 = "context"
            kotlin.jvm.internal.j.e(r3, r8)
            java.lang.String r8 = "a"
            kotlin.jvm.internal.j.e(r6, r8)
            java.lang.String r8 = "b"
            kotlin.jvm.internal.j.e(r7, r8)
            boolean r6 = Hf.C0754c.z(r3, r6, r7)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
        L_0x016d:
            if (r6 == 0) goto L_0x0174
            boolean r0 = r6.booleanValue()
            return r0
        L_0x0174:
            Hf.B r1 = r3.o0(r1)
            Hf.B r2 = r3.f(r2)
            Hf.K r6 = Hf.K.f3429c
            Hf.K r7 = Hf.K.b
            boolean r8 = r3.h(r2)
            java.lang.String r10 = ". Supertypes = "
            java.lang.String r11 = "Too many supertypes for type: "
            r12 = 1000(0x3e8, float:1.401E-42)
            if (r8 == 0) goto L_0x018e
            goto L_0x0275
        L_0x018e:
            boolean r8 = r3.G(r1)
            if (r8 != 0) goto L_0x0275
            boolean r8 = r3.c(r1)
            if (r8 == 0) goto L_0x019c
            goto L_0x0275
        L_0x019c:
            boolean r8 = r1 instanceof Kf.c
            if (r8 == 0) goto L_0x01ab
            r8 = r1
            Kf.c r8 = (Kf.c) r8
            boolean r8 = r3.J(r8)
            if (r8 == 0) goto L_0x01ab
            goto L_0x0275
        L_0x01ab:
            boolean r8 = Hf.C0754c.h(r0, r1, r7)
            if (r8 == 0) goto L_0x01b3
            goto L_0x0275
        L_0x01b3:
            boolean r8 = r3.G(r2)
            if (r8 == 0) goto L_0x01ba
            goto L_0x01c9
        L_0x01ba:
            Hf.K r8 = Hf.K.d
            boolean r8 = Hf.C0754c.h(r0, r2, r8)
            if (r8 == 0) goto L_0x01c3
            goto L_0x01c9
        L_0x01c3:
            boolean r8 = r3.t(r1)
            if (r8 == 0) goto L_0x01ca
        L_0x01c9:
            return r5
        L_0x01ca:
            Hf.M r8 = r3.q0(r2)
            java.lang.String r13 = "end"
            kotlin.jvm.internal.j.e(r8, r13)
            boolean r13 = Hf.C0754c.j(r0, r1, r8)
            if (r13 == 0) goto L_0x01db
            goto L_0x0275
        L_0x01db:
            r0.b()
            java.util.ArrayDeque r13 = r0.g
            kotlin.jvm.internal.j.b(r13)
            Qf.h r14 = r0.f3433h
            kotlin.jvm.internal.j.b(r14)
            r13.push(r1)
        L_0x01eb:
            boolean r15 = r13.isEmpty()
            if (r15 != 0) goto L_0x0271
            int r15 = r14.p()
            if (r15 > r12) goto L_0x0248
            java.lang.Object r15 = r13.pop()
            Kf.e r15 = (Kf.e) r15
            kotlin.jvm.internal.j.b(r15)
            boolean r16 = r14.add(r15)
            if (r16 == 0) goto L_0x01eb
            boolean r16 = r3.h(r15)
            if (r16 == 0) goto L_0x020e
            r9 = r6
            goto L_0x020f
        L_0x020e:
            r9 = r7
        L_0x020f:
            boolean r16 = r9.equals(r6)
            if (r16 != 0) goto L_0x0216
            goto L_0x0217
        L_0x0216:
            r9 = 0
        L_0x0217:
            if (r9 != 0) goto L_0x021a
            goto L_0x01eb
        L_0x021a:
            Hf.M r15 = r3.q0(r15)
            java.util.Collection r15 = r3.l0(r15)
            java.util.Iterator r15 = r15.iterator()
        L_0x0226:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x01eb
            java.lang.Object r16 = r15.next()
            r12 = r16
            Kf.d r12 = (Kf.d) r12
            Kf.e r12 = r9.D(r0, r12)
            boolean r16 = Hf.C0754c.j(r0, r12, r8)
            if (r16 == 0) goto L_0x0242
            r0.a()
            goto L_0x0275
        L_0x0242:
            r13.add(r12)
            r12 = 1000(0x3e8, float:1.401E-42)
            goto L_0x0226
        L_0x0248:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            r2.append(r1)
            r2.append(r10)
            r18 = 0
            r19 = 63
            r15 = 0
            r16 = 0
            r17 = 0
            java.lang.String r1 = ne.C1194l.R0(r14, r15, r16, r17, r18, r19)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0271:
            r0.a()
            return r5
        L_0x0275:
            Hf.B r8 = r3.o0(r1)
            Hf.B r9 = r3.f(r2)
            boolean r12 = r3.B(r8)
            if (r12 != 0) goto L_0x028b
            boolean r12 = r3.B(r9)
            if (r12 != 0) goto L_0x028b
        L_0x0289:
            r8 = 0
            goto L_0x02f0
        L_0x028b:
            boolean r12 = b(r3, r8)
            if (r12 == 0) goto L_0x029a
            boolean r12 = b(r3, r9)
            if (r12 == 0) goto L_0x029a
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            goto L_0x02f0
        L_0x029a:
            boolean r12 = r3.B(r8)
            if (r12 == 0) goto L_0x02a9
            boolean r8 = c(r3, r0, r8, r9, r5)
            if (r8 == 0) goto L_0x0289
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            goto L_0x02f0
        L_0x02a9:
            boolean r12 = r3.B(r9)
            if (r12 == 0) goto L_0x0289
            Hf.M r12 = r3.q0(r8)
            boolean r13 = r12 instanceof Hf.C0773w
            if (r13 == 0) goto L_0x02e8
            java.util.Collection r12 = r3.l0(r12)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            boolean r13 = r12 instanceof java.util.Collection
            if (r13 == 0) goto L_0x02cb
            r13 = r12
            java.util.Collection r13 = (java.util.Collection) r13
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L_0x02cb
            goto L_0x02e8
        L_0x02cb:
            java.util.Iterator r12 = r12.iterator()
        L_0x02cf:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x02e8
            java.lang.Object r13 = r12.next()
            Kf.d r13 = (Kf.d) r13
            Hf.B r13 = r3.g0(r13)
            if (r13 == 0) goto L_0x02cf
            boolean r13 = r3.B(r13)
            if (r13 != r4) goto L_0x02cf
            goto L_0x02ee
        L_0x02e8:
            boolean r8 = c(r3, r0, r9, r8, r4)
            if (r8 == 0) goto L_0x0289
        L_0x02ee:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
        L_0x02f0:
            if (r8 == 0) goto L_0x02f7
            boolean r0 = r8.booleanValue()
            return r0
        L_0x02f7:
            Hf.M r8 = r3.q0(r2)
            Hf.M r9 = r3.q0(r1)
            boolean r9 = r3.b(r9, r8)
            if (r9 == 0) goto L_0x030d
            int r9 = r3.t0(r8)
            if (r9 != 0) goto L_0x030d
            goto L_0x0015
        L_0x030d:
            Hf.M r9 = r3.q0(r2)
            boolean r9 = r3.R(r9)
            if (r9 == 0) goto L_0x0319
            goto L_0x0015
        L_0x0319:
            java.lang.String r9 = "superConstructor"
            kotlin.jvm.internal.j.e(r8, r9)
            boolean r9 = r3.t(r1)
            if (r9 == 0) goto L_0x032c
            java.util.List r9 = e(r0, r1, r8)
        L_0x0328:
            r17 = r5
            goto L_0x03fc
        L_0x032c:
            boolean r9 = r3.l(r8)
            if (r9 != 0) goto L_0x033d
            boolean r9 = r3.g(r8)
            if (r9 != 0) goto L_0x033d
            java.util.List r9 = d(r0, r1, r8)
            goto L_0x0328
        L_0x033d:
            Qf.f r9 = new Qf.f
            r9.<init>()
            r0.b()
            java.util.ArrayDeque r12 = r0.g
            kotlin.jvm.internal.j.b(r12)
            Qf.h r13 = r0.f3433h
            kotlin.jvm.internal.j.b(r13)
            r12.push(r1)
        L_0x0352:
            boolean r14 = r12.isEmpty()
            if (r14 != 0) goto L_0x03d4
            int r14 = r13.p()
            r15 = 1000(0x3e8, float:1.401E-42)
            if (r14 > r15) goto L_0x03ac
            java.lang.Object r14 = r12.pop()
            Kf.e r14 = (Kf.e) r14
            kotlin.jvm.internal.j.b(r14)
            boolean r15 = r13.add(r14)
            if (r15 == 0) goto L_0x0352
            boolean r15 = r3.t(r14)
            if (r15 == 0) goto L_0x037a
            r9.add(r14)
            r15 = r6
            goto L_0x037b
        L_0x037a:
            r15 = r7
        L_0x037b:
            boolean r16 = r15.equals(r6)
            if (r16 != 0) goto L_0x0382
            goto L_0x0383
        L_0x0382:
            r15 = 0
        L_0x0383:
            if (r15 != 0) goto L_0x0386
            goto L_0x0352
        L_0x0386:
            Hf.M r14 = r3.q0(r14)
            java.util.Collection r14 = r3.l0(r14)
            java.util.Iterator r14 = r14.iterator()
        L_0x0392:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x0352
            java.lang.Object r16 = r14.next()
            r17 = r5
            r5 = r16
            Kf.d r5 = (Kf.d) r5
            Kf.e r5 = r15.D(r0, r5)
            r12.add(r5)
            r5 = r17
            goto L_0x0392
        L_0x03ac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            r2.append(r1)
            r2.append(r10)
            r17 = 0
            r18 = 63
            r14 = 0
            r15 = 0
            r16 = 0
            java.lang.String r1 = ne.C1194l.R0(r13, r14, r15, r16, r17, r18)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x03d4:
            r17 = r5
            r0.a()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r9 = r9.iterator()
        L_0x03e2:
            boolean r12 = r9.hasNext()
            if (r12 == 0) goto L_0x03fb
            java.lang.Object r12 = r9.next()
            Kf.e r12 = (Kf.e) r12
            kotlin.jvm.internal.j.b(r12)
            java.util.List r12 = e(r0, r12, r8)
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            ne.C1200r.A0(r12, r5)
            goto L_0x03e2
        L_0x03fb:
            r9 = r5
        L_0x03fc:
            r9.size()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r5 = new java.util.ArrayList
            r12 = 10
            int r13 = ne.C1196n.w0(r9, r12)
            r5.<init>(r13)
            java.util.Iterator r9 = r9.iterator()
        L_0x0410:
            boolean r13 = r9.hasNext()
            if (r13 == 0) goto L_0x042c
            java.lang.Object r13 = r9.next()
            Kf.e r13 = (Kf.e) r13
            Hf.c0 r14 = r0.c(r13)
            Hf.B r14 = r3.g0(r14)
            if (r14 != 0) goto L_0x0427
            goto L_0x0428
        L_0x0427:
            r13 = r14
        L_0x0428:
            r5.add(r13)
            goto L_0x0410
        L_0x042c:
            int r9 = r5.size()
            if (r9 == 0) goto L_0x0510
            if (r9 == r4) goto L_0x0501
            Kf.a r6 = new Kf.a
            int r7 = r3.t0(r8)
            r6.<init>(r7)
            int r7 = r3.t0(r8)
            r9 = r17
            r10 = r9
        L_0x0444:
            if (r9 >= r7) goto L_0x04d6
            if (r10 != 0) goto L_0x0458
            Qe.V r10 = r3.u(r8, r9)
            Kf.i r10 = r3.A(r10)
            Kf.i r11 = Kf.i.OUT
            if (r10 == r11) goto L_0x0455
            goto L_0x0458
        L_0x0455:
            r10 = r17
            goto L_0x0459
        L_0x0458:
            r10 = r4
        L_0x0459:
            if (r10 != 0) goto L_0x04cc
            java.util.ArrayList r11 = new java.util.ArrayList
            int r13 = ne.C1196n.w0(r5, r12)
            r11.<init>(r13)
            java.util.Iterator r13 = r5.iterator()
        L_0x0468:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x04be
            java.lang.Object r14 = r13.next()
            Kf.e r14 = (Kf.e) r14
            Hf.P r15 = r3.H(r14, r9)
            if (r15 == 0) goto L_0x0496
            r16 = r4
            Kf.i r4 = r3.j(r15)
            Kf.i r12 = Kf.i.INV
            if (r4 != r12) goto L_0x0485
            goto L_0x0486
        L_0x0485:
            r15 = 0
        L_0x0486:
            if (r15 == 0) goto L_0x0496
            Hf.c0 r4 = r3.V(r15)
            if (r4 == 0) goto L_0x0496
            r11.add(r4)
            r4 = r16
            r12 = 10
            goto L_0x0468
        L_0x0496:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Incorrect type: "
            r3.<init>(r4)
            r3.append(r14)
            java.lang.String r4 = ", subType: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = ", superType: "
            r3.append(r1)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x04be:
            r16 = r4
            Hf.c0 r4 = r3.w(r11)
            Hf.G r4 = r3.P(r4)
            r6.add(r4)
            goto L_0x04ce
        L_0x04cc:
            r16 = r4
        L_0x04ce:
            int r9 = r9 + 1
            r4 = r16
            r12 = 10
            goto L_0x0444
        L_0x04d6:
            r16 = r4
            if (r10 != 0) goto L_0x04e1
            boolean r1 = l(r0, r6, r2)
            if (r1 == 0) goto L_0x04e1
            goto L_0x052b
        L_0x04e1:
            java.util.Iterator r1 = r5.iterator()
            r5 = r17
        L_0x04e7:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0500
            java.lang.Object r4 = r1.next()
            Kf.e r4 = (Kf.e) r4
            if (r5 == 0) goto L_0x04f6
            goto L_0x04e7
        L_0x04f6:
            Kf.f r4 = r3.e0(r4)
            boolean r4 = l(r0, r4, r2)
            r5 = r4
            goto L_0x04e7
        L_0x0500:
            return r5
        L_0x0501:
            java.lang.Object r1 = ne.C1194l.K0(r5)
            Kf.e r1 = (Kf.e) r1
            Kf.f r1 = r3.e0(r1)
            boolean r0 = l(r0, r1, r2)
            return r0
        L_0x0510:
            r16 = r4
            Hf.M r2 = r3.q0(r1)
            boolean r4 = r3.l(r2)
            if (r4 == 0) goto L_0x0521
            boolean r0 = r3.r0(r2)
            return r0
        L_0x0521:
            Hf.M r2 = r3.q0(r1)
            boolean r2 = r3.r0(r2)
            if (r2 == 0) goto L_0x052c
        L_0x052b:
            return r16
        L_0x052c:
            r0.b()
            java.util.ArrayDeque r2 = r0.g
            kotlin.jvm.internal.j.b(r2)
            Qf.h r4 = r0.f3433h
            kotlin.jvm.internal.j.b(r4)
            r2.push(r1)
        L_0x053c:
            boolean r5 = r2.isEmpty()
            if (r5 != 0) goto L_0x05c7
            int r5 = r4.p()
            r15 = 1000(0x3e8, float:1.401E-42)
            if (r5 > r15) goto L_0x059b
            java.lang.Object r5 = r2.pop()
            Kf.e r5 = (Kf.e) r5
            kotlin.jvm.internal.j.b(r5)
            boolean r8 = r4.add(r5)
            if (r8 == 0) goto L_0x053c
            boolean r8 = r3.t(r5)
            if (r8 == 0) goto L_0x0561
            r8 = r6
            goto L_0x0562
        L_0x0561:
            r8 = r7
        L_0x0562:
            boolean r9 = r8.equals(r6)
            if (r9 != 0) goto L_0x0569
            goto L_0x056a
        L_0x0569:
            r8 = 0
        L_0x056a:
            if (r8 != 0) goto L_0x056d
            goto L_0x053c
        L_0x056d:
            Hf.M r5 = r3.q0(r5)
            java.util.Collection r5 = r3.l0(r5)
            java.util.Iterator r5 = r5.iterator()
        L_0x0579:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x053c
            java.lang.Object r9 = r5.next()
            Kf.d r9 = (Kf.d) r9
            Kf.e r9 = r8.D(r0, r9)
            Hf.M r12 = r3.q0(r9)
            boolean r12 = r3.r0(r12)
            if (r12 == 0) goto L_0x0597
            r0.a()
            return r16
        L_0x0597:
            r2.add(r9)
            goto L_0x0579
        L_0x059b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            r2.append(r1)
            r2.append(r10)
            r22 = 0
            r23 = 63
            r19 = 0
            r20 = 0
            r21 = 0
            r18 = r4
            java.lang.String r1 = ne.C1194l.R0(r18, r19, r20, r21, r22, r23)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x05c7:
            r0.a()
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.C0756e.m(Hf.e, Hf.L, Kf.d, Kf.d):boolean");
    }

    public static void n(b bVar, d dVar, d dVar2) {
        B g02 = bVar.g0(dVar);
        if (g02 instanceof c) {
            c cVar = (c) g02;
            if (!bVar.p0(cVar) && bVar.n0(bVar.i0(bVar.f0(cVar))) && bVar.d0(cVar) == Kf.b.FOR_SUBTYPING) {
                bVar.K(dVar2);
            }
        }
    }

    public static C0764m o(c0 c0Var, boolean z) {
        boolean z3;
        O o2;
        j.e(c0Var, "type");
        if (c0Var instanceof C0764m) {
            return (C0764m) c0Var;
        }
        c0Var.s0();
        if ((c0Var.s0().g() instanceof V) || (c0Var instanceof If.h)) {
            C0819i g = c0Var.s0().g();
            if (g instanceof O) {
                o2 = (O) g;
            } else {
                o2 = null;
            }
            z3 = true;
            if (o2 == null || o2.f3768p) {
                if (!z || !(c0Var.s0().g() instanceof V)) {
                    z3 = true ^ C0754c.h(If.g.l(false, (If.e) null, 24), C0754c.m(c0Var), K.b);
                } else {
                    z3 = a0.e(c0Var);
                }
            }
        } else {
            z3 = false;
        }
        if (!z3) {
            return null;
        }
        if (c0Var instanceof C0768q) {
            C0768q qVar = (C0768q) c0Var;
            j.a(qVar.e.s0(), qVar.f.s0());
        }
        return new C0764m(C0754c.m(c0Var).y0(false), z);
    }

    public void a(Re.h hVar, Re.h hVar2) {
        HashSet hashSet = new HashSet();
        Iterator it = hVar.iterator();
        while (it.hasNext()) {
            hashSet.add(((Re.b) it.next()).b());
        }
        Iterator it2 = hVar2.iterator();
        while (it2.hasNext()) {
            hashSet.contains(((Re.b) it2.next()).b());
        }
    }

    public T f(M m, List list) {
        j.e(m, "typeConstructor");
        j.e(list, "arguments");
        List parameters = m.getParameters();
        j.d(parameters, "getParameters(...)");
        V v = (V) C1194l.U0(parameters);
        if (v == null || !v.J()) {
            return new C0770t((V[]) parameters.toArray(new V[0]), (P[]) list.toArray(new P[0]), false);
        }
        List parameters2 = m.getParameters();
        j.d(parameters2, "getParameters(...)");
        Iterable<V> iterable = parameters2;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (V q : iterable) {
            arrayList.add(q.q());
        }
        return new H(1, z.e0(C1194l.r1(arrayList, list)));
    }

    public B h(D0.f fVar, I i2, boolean z, int i7, boolean z3) {
        I i8;
        d0 d0Var = d0.INVARIANT;
        U u = (U) fVar.f;
        P i10 = i(new G(((w) u).G0(), d0Var), fVar, (V) null, i7);
        C0774x b = i10.b();
        j.d(b, "getType(...)");
        B b5 = C0754c.b(b);
        if (C0754c.k(b5)) {
            return b5;
        }
        i10.a();
        a(b5.getAnnotations(), C0760i.a(i2));
        if (!C0754c.k(b5)) {
            if (C0754c.k(b5)) {
                i8 = b5.p0();
            } else {
                I p02 = b5.p0();
                D0.e eVar = I.e;
                j.e(p02, "other");
                if (!i2.isEmpty() || !p02.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    Collection<Number> values = ((ConcurrentHashMap) eVar.e).values();
                    j.d(values, "<get-values>(...)");
                    for (Number intValue : values) {
                        int intValue2 = intValue.intValue();
                        C0759h hVar = (C0759h) i2.d.get(intValue2);
                        C0759h hVar2 = (C0759h) p02.d.get(intValue2);
                        if (hVar != null) {
                            if (hVar2 != null) {
                                hVar = new C0759h(com.samsung.context.sdk.samsunganalytics.internal.sender.c.k(hVar.f3445a, hVar2.f3445a));
                            }
                            hVar2 = hVar;
                        } else if (hVar2 == null) {
                            hVar2 = null;
                        } else if (hVar != null) {
                            hVar2 = new C0759h(com.samsung.context.sdk.samsunganalytics.internal.sender.c.k(hVar2.f3445a, hVar.f3445a));
                        }
                        k.a(arrayList, hVar2);
                    }
                    i8 = D0.e.D(arrayList);
                } else {
                    i8 = i2;
                }
            }
            b5 = C0754c.r(b5, (List) null, i8, 1);
        }
        B i11 = a0.i(b5, z);
        if (!z3) {
            return i11;
        }
        C0844e eVar2 = ((C0845f) u).l;
        j.d(eVar2, "getTypeConstructor(...)");
        return C0754c.F(i11, C0754c.v(o.b, i2, eVar2, (List) fVar.g, z));
    }

    public P i(P p6, D0.f fVar, V v, int i2) {
        d0 d0Var;
        d0 d0Var2;
        d0 d0Var3;
        int i7 = i2;
        U u = (U) fVar.f;
        if (i7 > 100) {
            throw new AssertionError("Too deep recursion while expanding type alias " + ((C0852m) u).getName());
        } else if (p6.c()) {
            j.b(v);
            return a0.j(v);
        } else {
            C0774x b = p6.b();
            j.d(b, "getType(...)");
            M s0 = b.s0();
            j.e(s0, "constructor");
            C0819i g = s0.g();
            P p8 = g instanceof V ? (P) ((Map) fVar.f106h).get(g) : null;
            if (p8 == null) {
                B b5 = C0754c.b(p6.b().x0());
                if (!C0754c.k(b5) && a0.c(b5, a.f, (h) null)) {
                    M s02 = b5.s0();
                    C0819i g3 = s02.g();
                    s02.getParameters().size();
                    b5.e0().size();
                    if (!(g3 instanceof V)) {
                        int i8 = 0;
                        if (g3 instanceof U) {
                            U u3 = (U) g3;
                            if (fVar.J(u3)) {
                                return new G(l.c(Jf.k.RECURSIVE_TYPE_ALIAS, ((C0852m) u3).getName().d), d0.INVARIANT);
                            }
                            Iterable e02 = b5.e0();
                            ArrayList arrayList = new ArrayList(C1196n.w0(e02, 10));
                            for (Object next : e02) {
                                int i10 = i8 + 1;
                                if (i8 >= 0) {
                                    arrayList.add(i((P) next, fVar, (V) s02.getParameters().get(i8), i7 + 1));
                                    i8 = i10;
                                } else {
                                    C1195m.v0();
                                    throw null;
                                }
                            }
                            return new G(C0754c.F(h(C0754c.e(fVar, u3, arrayList), b5.p0(), b5.u0(), i7 + 1, false), p(b5, fVar, i7)), p6.a());
                        }
                        B p10 = p(b5, fVar, i7);
                        X.d(p10);
                        for (Object next2 : p10.e0()) {
                            int i11 = i8 + 1;
                            if (i8 >= 0) {
                                P p11 = (P) next2;
                                if (!p11.c()) {
                                    C0774x b8 = p11.b();
                                    j.d(b8, "getType(...)");
                                    if (!a0.c(b8, a.e, (h) null)) {
                                        P p12 = (P) b5.e0().get(i8);
                                        V v6 = (V) b5.s0().getParameters().get(i8);
                                    }
                                }
                                i8 = i11;
                            } else {
                                C1195m.v0();
                                throw null;
                            }
                        }
                        return new G(p10, p6.a());
                    }
                }
                return p6;
            } else if (p8.c()) {
                j.b(v);
                return a0.j(v);
            } else {
                c0 x02 = p8.b().x0();
                d0 a7 = p8.a();
                j.d(a7, "getProjectionKind(...)");
                d0 a10 = p6.a();
                j.d(a10, "getProjectionKind(...)");
                if (!(a10 == a7 || a10 == (d0Var3 = d0.INVARIANT) || a7 != d0Var3)) {
                    a7 = a10;
                }
                if (v == null || (d0Var = v.t()) == null) {
                    d0Var = d0.INVARIANT;
                }
                if (!(d0Var == a7 || d0Var == (d0Var2 = d0.INVARIANT) || a7 != d0Var2)) {
                    a7 = d0Var2;
                }
                a(b.getAnnotations(), x02.getAnnotations());
                B i12 = a0.i(C0754c.b(x02), b.u0());
                I p02 = b.p0();
                if (!C0754c.k(i12)) {
                    if (C0754c.k(i12)) {
                        p02 = i12.p0();
                    } else {
                        I p03 = i12.p0();
                        p02.getClass();
                        D0.e eVar = I.e;
                        j.e(p03, "other");
                        if (!p02.isEmpty() || !p03.isEmpty()) {
                            ArrayList arrayList2 = new ArrayList();
                            Collection<Number> values = ((ConcurrentHashMap) eVar.e).values();
                            j.d(values, "<get-values>(...)");
                            for (Number intValue : values) {
                                int intValue2 = intValue.intValue();
                                C0759h hVar = (C0759h) p02.d.get(intValue2);
                                C0759h hVar2 = (C0759h) p03.d.get(intValue2);
                                if (hVar != null) {
                                    if (hVar2 != null) {
                                        hVar = new C0759h(com.samsung.context.sdk.samsunganalytics.internal.sender.c.k(hVar.f3445a, hVar2.f3445a));
                                    }
                                    hVar2 = hVar;
                                } else if (hVar2 == null) {
                                    hVar2 = null;
                                } else if (hVar != null) {
                                    hVar2 = new C0759h(com.samsung.context.sdk.samsunganalytics.internal.sender.c.k(hVar2.f3445a, hVar.f3445a));
                                }
                                k.a(arrayList2, hVar2);
                            }
                            p02 = D0.e.D(arrayList2);
                        }
                    }
                    i12 = C0754c.r(i12, (List) null, p02, 1);
                }
                return new G(i12, a7);
            }
        }
    }

    public B p(B b, D0.f fVar, int i2) {
        M s0 = b.s0();
        Iterable e02 = b.e0();
        ArrayList arrayList = new ArrayList(C1196n.w0(e02, 10));
        int i7 = 0;
        for (Object next : e02) {
            int i8 = i7 + 1;
            if (i7 >= 0) {
                P p6 = (P) next;
                P i10 = i(p6, fVar, (V) s0.getParameters().get(i7), i2 + 1);
                if (!i10.c()) {
                    i10 = new G(a0.h(i10.b(), p6.b().u0()), i10.a());
                }
                arrayList.add(i10);
                i7 = i8;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        return C0754c.r(b, arrayList, (I) null, 2);
    }
}
