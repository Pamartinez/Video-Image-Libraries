package Hf;

import A.a;
import Af.p;
import D0.e;
import D0.f;
import If.b;
import If.g;
import If.m;
import Jf.k;
import Kf.d;
import Ne.i;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0820j;
import Qe.C0822l;
import Qe.C0831v;
import Qe.U;
import Qe.V;
import Re.h;
import Re.l;
import Te.C0845f;
import Te.O;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import ne.C1194l;
import ne.C1196n;
import ne.z;
import o1.C0246a;
import tf.C1305i;
import xf.C1353d;

/* renamed from: Hf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0754c {
    public static X A(List list, T t, C0822l lVar, ArrayList arrayList) {
        if (t == null) {
            a(1);
            throw null;
        } else if (lVar == null) {
            a(2);
            throw null;
        } else if (arrayList != null) {
            X B = B(list, t, lVar, arrayList, (boolean[]) null);
            if (B != null) {
                return B;
            }
            throw new AssertionError("Substitution failed");
        } else {
            a(3);
            throw null;
        }
    }

    public static X B(List list, T t, C0822l lVar, List list2, boolean[] zArr) {
        X x9;
        T t3 = t;
        List list3 = list2;
        if (t3 == null) {
            a(6);
            throw null;
        } else if (lVar == null) {
            a(7);
            throw null;
        } else if (list3 != null) {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Iterator it = list.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                V v = (V) it.next();
                O G02 = O.G0(lVar, v.getAnnotations(), v.r(), v.t(), v.getName(), i2, v.F());
                hashMap.put(v.q(), new G((C0774x) G02.i()));
                hashMap2.put(v, G02);
                list3.add(G02);
                i2++;
            }
            H h5 = new H(1, hashMap);
            X e = X.e(t3, h5);
            X e7 = X.e(new S(t3, 0), h5);
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                V v6 = (V) it2.next();
                O o2 = (O) hashMap2.get(v6);
                for (C0774x xVar : v6.getUpperBounds()) {
                    C0819i g = xVar.s0().g();
                    if (!(g instanceof V) || !c.I((V) g, (M) null, 6)) {
                        x9 = e7;
                    } else {
                        x9 = e;
                    }
                    C0774x i7 = x9.i(xVar, d0.OUT_VARIANCE);
                    if (i7 == null) {
                        return null;
                    }
                    if (!(i7 == xVar || zArr == null)) {
                        zArr[0] = true;
                    }
                    if (o2.f3768p) {
                        throw new IllegalStateException("Type parameter descriptor is already initialized: " + o2.I0());
                    } else if (!k(i7)) {
                        o2.f3767o.add(i7);
                    }
                }
                if (!o2.f3768p) {
                    o2.f3768p = true;
                } else {
                    throw new IllegalStateException("Type parameter descriptor is already initialized: " + o2.I0());
                }
            }
            return e;
        } else {
            a(8);
            throw null;
        }
    }

    public static final I C(h hVar) {
        j.e(hVar, "<this>");
        if (hVar.isEmpty()) {
            I.e.getClass();
            return I.f;
        }
        e eVar = I.e;
        List e02 = C0246a.e0(new C0759h(hVar));
        eVar.getClass();
        return e.D(e02);
    }

    public static final B E(C0774x xVar) {
        j.e(xVar, "<this>");
        c0 x02 = xVar.x0();
        if (x02 instanceof C0768q) {
            return ((C0768q) x02).f;
        }
        if (x02 instanceof B) {
            return (B) x02;
        }
        throw new RuntimeException();
    }

    public static final B F(B b, B b5) {
        j.e(b, "<this>");
        j.e(b5, "abbreviatedType");
        if (k(b)) {
            return b;
        }
        return new C0752a(b, b5);
    }

    public static final c0 G(c0 c0Var, C0774x xVar) {
        j.e(c0Var, "<this>");
        if (c0Var instanceof b0) {
            return G(((b0) c0Var).O(), xVar);
        }
        if (xVar == null || xVar.equals(c0Var)) {
            return c0Var;
        }
        if (c0Var instanceof B) {
            return new E((B) c0Var, xVar);
        }
        if (c0Var instanceof C0768q) {
            return new C0769s((C0768q) c0Var, xVar);
        }
        throw new RuntimeException();
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 4) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 4) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 6:
                objArr[0] = "originalSubstitution";
                break;
            case 2:
            case 7:
                objArr[0] = "newContainingDeclaration";
                break;
            case 3:
            case 8:
                objArr[0] = "result";
                break;
            case 4:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
            default:
                objArr[0] = "typeParameters";
                break;
        }
        if (i2 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
        } else {
            objArr[1] = "substituteTypeParameters";
        }
        if (i2 != 4) {
            objArr[2] = "substituteTypeParameters";
        }
        String format = String.format(str, objArr);
        if (i2 != 4) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public static final B b(C0774x xVar) {
        B b;
        j.e(xVar, "<this>");
        c0 x02 = xVar.x0();
        if (x02 instanceof B) {
            b = (B) x02;
        } else {
            b = null;
        }
        if (b != null) {
            return b;
        }
        throw new IllegalStateException(("This is should be simple type: " + xVar).toString());
    }

    public static final C0774x c(ArrayList arrayList, List list, i iVar) {
        C0774x i2 = new X(new H(0, arrayList)).i((C0774x) C1194l.L0(list), d0.OUT_VARIANCE);
        if (i2 == null) {
            return iVar.m();
        }
        return i2;
    }

    public static final d d(d dVar, HashSet hashSet) {
        d d;
        boolean z;
        m mVar = m.d;
        M K6 = mVar.K(dVar);
        if (!hashSet.add(K6)) {
            return null;
        }
        V s = g.s(K6);
        if (s != null) {
            C0774x F4 = c.F(s);
            d d2 = d(F4, hashSet);
            if (d2 == null) {
                return null;
            }
            if (g.C(mVar.K(F4)) || ((F4 instanceof Kf.e) && g.I((Kf.e) F4))) {
                z = true;
            } else {
                z = false;
            }
            if ((d2 instanceof Kf.e) && g.I((Kf.e) d2) && g.H(dVar) && z) {
                return mVar.a(F4);
            }
            if (g.H(d2) || !(dVar instanceof Kf.e) || !g.F((Kf.e) dVar)) {
                return d2;
            }
            return mVar.a(d2);
        }
        if (g.C(K6)) {
            j.e(dVar, "$receiver");
            if (dVar instanceof C0774x) {
                B i2 = C1305i.i((C0774x) dVar);
                if (i2 == null || (d = d(i2, hashSet)) == null) {
                    return null;
                }
                if (!g.H(dVar)) {
                    return d;
                }
                if (!g.H(d) && (!(d instanceof Kf.e) || !g.I((Kf.e) d))) {
                    return mVar.a(d);
                }
            } else {
                StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
                sb2.append(dVar);
                sb2.append(ArcCommonLog.TAG_COMMA);
                throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
            }
        }
        return dVar;
    }

    public static f e(f fVar, U u, List list) {
        Iterable<V> parameters = ((C0845f) u).l.getParameters();
        ArrayList arrayList = new ArrayList(C1196n.w0(parameters, 10));
        for (V a7 : parameters) {
            arrayList.add(a7.a());
        }
        return new f(fVar, u, list, z.e0(C1194l.r1(arrayList, list)), 3);
    }

    public static final c0 f(B b, B b5) {
        j.e(b, "lowerBound");
        j.e(b5, "upperBound");
        if (b.equals(b5)) {
            return b;
        }
        return new r(b, b5);
    }

    public static final C0774x g(C0774x xVar) {
        j.e(xVar, "<this>");
        if (xVar instanceof b0) {
            return ((b0) xVar).K();
        }
        return null;
    }

    public static boolean h(L l, Kf.e eVar, C0754c cVar) {
        K k;
        K k10 = K.f3429c;
        j.e(eVar, "type");
        b bVar = l.f3432c;
        if ((bVar.t(eVar) && !bVar.h(eVar)) || bVar.G(eVar)) {
            return true;
        }
        l.b();
        ArrayDeque arrayDeque = l.g;
        j.b(arrayDeque);
        Qf.h hVar = l.f3433h;
        j.b(hVar);
        arrayDeque.push(eVar);
        while (!arrayDeque.isEmpty()) {
            if (hVar.p() <= 1000) {
                Kf.e eVar2 = (Kf.e) arrayDeque.pop();
                j.b(eVar2);
                if (hVar.add(eVar2)) {
                    if (bVar.h(eVar2)) {
                        k = k10;
                    } else {
                        k = cVar;
                    }
                    if (k.equals(k10)) {
                        k = null;
                    }
                    if (k == null) {
                        continue;
                    } else {
                        for (d D : bVar.l0(bVar.q0(eVar2))) {
                            Kf.e D4 = k.D(l, D);
                            if ((!bVar.t(D4) || bVar.h(D4)) && !bVar.G(D4)) {
                                arrayDeque.add(D4);
                            } else {
                                l.a();
                                return true;
                            }
                        }
                        continue;
                    }
                }
            } else {
                throw new IllegalStateException(("Too many supertypes for type: " + eVar + ". Supertypes = " + C1194l.R0(hVar, (String) null, (String) null, (String) null, (Ae.b) null, 63)).toString());
            }
        }
        l.a();
        return false;
    }

    public static final c0 i(c0 c0Var, C0774x xVar) {
        j.e(c0Var, "<this>");
        j.e(xVar, "origin");
        return G(c0Var, g(xVar));
    }

    public static boolean j(L l, Kf.e eVar, Kf.g gVar) {
        b bVar = l.f3432c;
        if (bVar.U(eVar)) {
            return true;
        }
        if (bVar.h(eVar)) {
            return false;
        }
        if (l.b) {
            bVar.d(eVar);
        }
        return bVar.b(bVar.q0(eVar), gVar);
    }

    public static final boolean k(C0774x xVar) {
        j.e(xVar, "<this>");
        c0 x02 = xVar.x0();
        if (x02 instanceof Jf.i) {
            return true;
        }
        if (!(x02 instanceof C0768q) || !(((C0768q) x02).B0() instanceof Jf.i)) {
            return false;
        }
        return true;
    }

    public static final boolean l(C0774x xVar) {
        j.e(xVar, "<this>");
        return xVar.x0() instanceof C0768q;
    }

    public static final B m(C0774x xVar) {
        j.e(xVar, "<this>");
        c0 x02 = xVar.x0();
        if (x02 instanceof C0768q) {
            return ((C0768q) x02).e;
        }
        if (x02 instanceof B) {
            return (B) x02;
        }
        throw new RuntimeException();
    }

    public static final c0 n(c0 c0Var, boolean z) {
        j.e(c0Var, "<this>");
        C0764m o2 = C0756e.o(c0Var, z);
        if (o2 != null) {
            return o2;
        }
        B o3 = o(c0Var);
        if (o3 != null) {
            return o3;
        }
        return c0Var.y0(false);
    }

    public static final B o(c0 c0Var) {
        C0773w wVar;
        C0773w wVar2;
        M s0 = c0Var.s0();
        if (s0 instanceof C0773w) {
            wVar = (C0773w) s0;
        } else {
            wVar = null;
        }
        if (wVar != null) {
            LinkedHashSet<C0774x> linkedHashSet = wVar.b;
            ArrayList arrayList = new ArrayList(C1196n.w0(linkedHashSet, 10));
            boolean z = false;
            for (C0774x xVar : linkedHashSet) {
                if (a0.e(xVar)) {
                    xVar = n(xVar.x0(), false);
                    z = true;
                }
                arrayList.add(xVar);
            }
            if (!z) {
                wVar2 = null;
            } else {
                C0774x xVar2 = wVar.f3452a;
                if (xVar2 == null) {
                    xVar2 = null;
                } else if (a0.e(xVar2)) {
                    xVar2 = n(xVar2.x0(), false);
                }
                arrayList.isEmpty();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet(arrayList);
                linkedHashSet2.hashCode();
                wVar2 = new C0773w(linkedHashSet2);
                wVar2.f3452a = xVar2;
            }
            if (wVar2 != null) {
                return wVar2.b();
            }
        }
        return null;
    }

    public static final B p(B b, List list, I i2) {
        j.e(b, "<this>");
        j.e(list, "newArguments");
        j.e(i2, "newAttributes");
        if (list.isEmpty() && i2 == b.p0()) {
            return b;
        }
        if (list.isEmpty()) {
            return b.A0(i2);
        }
        if (b instanceof Jf.i) {
            Jf.i iVar = (Jf.i) b;
            M m = iVar.e;
            Jf.g gVar = iVar.f;
            k kVar = iVar.g;
            boolean z = iVar.f3478i;
            String[] strArr = iVar.f3479j;
            return new Jf.i(m, gVar, kVar, list, z, (String[]) Arrays.copyOf(strArr, strArr.length));
        }
        return u(i2, b.s0(), list, b.u0());
    }

    public static C0774x q(C0774x xVar, List list, h hVar, int i2) {
        if ((i2 & 2) != 0) {
            hVar = xVar.getAnnotations();
        }
        j.e(xVar, "<this>");
        if ((list.isEmpty() || list == xVar.e0()) && hVar == xVar.getAnnotations()) {
            return xVar;
        }
        I p02 = xVar.p0();
        if ((hVar instanceof l) && ((l) hVar).isEmpty()) {
            hVar = Re.g.f3692a;
        }
        I s = s(p02, hVar);
        c0 x02 = xVar.x0();
        if (x02 instanceof C0768q) {
            C0768q qVar = (C0768q) x02;
            return f(p(qVar.e, list, s), p(qVar.f, list, s));
        } else if (x02 instanceof B) {
            return p((B) x02, list, s);
        } else {
            throw new RuntimeException();
        }
    }

    public static /* synthetic */ B r(B b, List list, I i2, int i7) {
        if ((i7 & 1) != 0) {
            list = b.e0();
        }
        if ((i7 & 2) != 0) {
            i2 = b.p0();
        }
        return p(b, list, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Hf.I s(Hf.I r5, Re.h r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r5, r0)
            Re.h r0 = Hf.C0760i.a(r5)
            if (r0 != r6) goto L_0x000c
            return r5
        L_0x000c:
            D1.i r0 = Hf.C0760i.b
            He.t[] r1 = Hf.C0760i.f3446a
            r2 = 0
            r1 = r1[r2]
            java.lang.Object r0 = r0.f(r5, r1)
            Hf.h r0 = (Hf.C0759h) r0
            if (r0 == 0) goto L_0x005f
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x0022
            goto L_0x0050
        L_0x0022:
            Ge.a r1 = r5.d
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x002d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0044
            java.lang.Object r3 = r1.next()
            r4 = r3
            Hf.h r4 = (Hf.C0759h) r4
            boolean r4 = kotlin.jvm.internal.j.a(r4, r0)
            if (r4 != 0) goto L_0x002d
            r2.add(r3)
            goto L_0x002d
        L_0x0044:
            int r0 = r2.size()
            Ge.a r1 = r5.d
            int r1 = r1.i()
            if (r0 != r1) goto L_0x0052
        L_0x0050:
            r0 = r5
            goto L_0x005b
        L_0x0052:
            D0.e r0 = Hf.I.e
            r0.getClass()
            Hf.I r0 = D0.e.D(r2)
        L_0x005b:
            if (r0 != 0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r5 = r0
        L_0x005f:
            java.util.Iterator r0 = r6.iterator()
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L_0x0070
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x0070
            goto L_0x0095
        L_0x0070:
            Hf.h r0 = new Hf.h
            r0.<init>(r6)
            D0.e r6 = Hf.I.e
            java.lang.Class<Hf.h> r1 = Hf.C0759h.class
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            He.d r1 = r2.b(r1)
            r6.getClass()
            java.lang.String r1 = r1.n()
            kotlin.jvm.internal.j.b(r1)
            int r6 = r6.J(r1)
            Ge.a r1 = r5.d
            java.lang.Object r6 = r1.get(r6)
            if (r6 == 0) goto L_0x0096
        L_0x0095:
            return r5
        L_0x0096:
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto L_0x00a6
            Hf.I r5 = new Hf.I
            java.util.List r6 = o1.C0246a.e0(r0)
            r5.<init>(r6)
            return r5
        L_0x00a6:
            java.util.List r5 = ne.C1194l.k1(r5)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.ArrayList r5 = ne.C1194l.Y0(r5, r0)
            Hf.I r5 = D0.e.D(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.C0754c.s(Hf.I, Re.h):Hf.I");
    }

    public static final B t(I i2, C0816f fVar, List list) {
        j.e(i2, "attributes");
        j.e(fVar, "descriptor");
        j.e(list, "arguments");
        M q = fVar.q();
        j.d(q, "getTypeConstructor(...)");
        return u(i2, q, list, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: Te.y} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: Qe.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: Te.y} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Hf.B u(Hf.I r7, Hf.M r8, java.util.List r9, boolean r10) {
        /*
            java.lang.String r0 = "attributes"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = "constructor"
            kotlin.jvm.internal.j.e(r8, r0)
            java.lang.String r0 = "arguments"
            kotlin.jvm.internal.j.e(r9, r0)
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L_0x0034
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x0034
            if (r10 != 0) goto L_0x0034
            Qe.i r0 = r8.g()
            if (r0 == 0) goto L_0x0034
            Qe.i r7 = r8.g()
            kotlin.jvm.internal.j.b(r7)
            Hf.B r7 = r7.i()
            java.lang.String r8 = "getDefaultType(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            return r7
        L_0x0034:
            Qe.i r0 = r8.g()
            boolean r1 = r0 instanceof Qe.V
            if (r1 == 0) goto L_0x0049
            Qe.V r0 = (Qe.V) r0
            Hf.B r0 = r0.i()
            Af.p r0 = r0.A()
        L_0x0046:
            r5 = r0
            goto L_0x00ca
        L_0x0049:
            boolean r1 = r0 instanceof Qe.C0816f
            if (r1 == 0) goto L_0x009f
            Qe.C r1 = xf.C1353d.j(r0)
            xf.C1353d.i(r1)
            boolean r1 = r9.isEmpty()
            r2 = 0
            If.f r3 = If.f.f3461a
            if (r1 == 0) goto L_0x007b
            Qe.f r0 = (Qe.C0816f) r0
            boolean r1 = r0 instanceof Te.y
            if (r1 == 0) goto L_0x0066
            r2 = r0
            Te.y r2 = (Te.y) r2
        L_0x0066:
            if (r2 == 0) goto L_0x0071
            Af.p r1 = r2.K(r3)
            if (r1 != 0) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r5 = r1
            goto L_0x00ca
        L_0x0071:
            Af.p r0 = r0.P()
            java.lang.String r1 = "getUnsubstitutedMemberScope(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            goto L_0x0046
        L_0x007b:
            Qe.f r0 = (Qe.C0816f) r0
            Hf.e r1 = Hf.N.b
            Hf.T r1 = r1.f(r8, r9)
            boolean r4 = r0 instanceof Te.y
            if (r4 == 0) goto L_0x008a
            r2 = r0
            Te.y r2 = (Te.y) r2
        L_0x008a:
            if (r2 == 0) goto L_0x0095
            Af.p r2 = r2.n(r1, r3)
            if (r2 != 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r5 = r2
            goto L_0x00ca
        L_0x0095:
            Af.p r0 = r0.a0(r1)
            java.lang.String r1 = "getMemberScope(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            goto L_0x0046
        L_0x009f:
            boolean r1 = r0 instanceof Qe.U
            if (r1 == 0) goto L_0x00b9
            Jf.h r1 = Jf.h.SCOPE_FOR_ABBREVIATION_TYPE
            Qe.U r0 = (Qe.U) r0
            Te.m r0 = (Te.C0852m) r0
            qf.g r0 = r0.getName()
            java.lang.String r0 = r0.d
            java.lang.String[] r0 = new java.lang.String[]{r0}
            r2 = 1
            Jf.g r0 = Jf.l.a(r1, r2, r0)
            goto L_0x0046
        L_0x00b9:
            boolean r1 = r8 instanceof Hf.C0773w
            if (r1 == 0) goto L_0x00d8
            r0 = r8
            Hf.w r0 = (Hf.C0773w) r0
            java.lang.String r1 = "member scope for intersection type"
            java.util.LinkedHashSet r0 = r0.b
            Af.p r0 = L1.d.d(r1, r0)
            goto L_0x0046
        L_0x00ca:
            Hf.y r6 = new Hf.y
            r6.<init>(r7, r8, r9, r10)
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            Hf.B r7 = w(r1, r2, r3, r4, r5, r6)
            return r7
        L_0x00d8:
            r2 = r8
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Unsupported classifier: "
            r8.<init>(r9)
            r8.append(r0)
            java.lang.String r9 = " for constructor: "
            r8.append(r9)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Hf.C0754c.u(Hf.I, Hf.M, java.util.List, boolean):Hf.B");
    }

    public static final B v(p pVar, I i2, M m, List list, boolean z) {
        j.e(i2, "attributes");
        j.e(m, "constructor");
        j.e(list, "arguments");
        j.e(pVar, "memberScope");
        p pVar2 = pVar;
        I i7 = i2;
        M m4 = m;
        List list2 = list;
        boolean z3 = z;
        List list3 = list2;
        C0775y yVar = new C0775y(pVar2, i7, m4, list2, z3);
        M m8 = m4;
        p pVar3 = pVar2;
        List list4 = list3;
        I i8 = i7;
        C c5 = new C(m8, list4, z3, pVar3, yVar);
        if (i8.isEmpty()) {
            return c5;
        }
        return new D(c5, i8);
    }

    public static final B w(I i2, M m, List list, boolean z, p pVar, Ae.b bVar) {
        j.e(i2, "attributes");
        j.e(m, "constructor");
        j.e(list, "arguments");
        j.e(pVar, "memberScope");
        C c5 = new C(m, list, z, pVar, bVar);
        if (i2.isEmpty()) {
            return c5;
        }
        return new D(c5, i2);
    }

    public static final C0774x x(V v) {
        j.e(v, "<this>");
        C0822l g = v.g();
        j.d(g, "getContainingDeclaration(...)");
        if (g instanceof C0820j) {
            List parameters = ((C0820j) g).q().getParameters();
            j.d(parameters, "getParameters(...)");
            Iterable<V> iterable = parameters;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (V q : iterable) {
                M q10 = q.q();
                j.d(q10, "getTypeConstructor(...)");
                arrayList.add(q10);
            }
            List upperBounds = v.getUpperBounds();
            j.d(upperBounds, "getUpperBounds(...)");
            return c(arrayList, upperBounds, C1353d.e(v));
        } else if (g instanceof C0831v) {
            List typeParameters = ((C0831v) g).getTypeParameters();
            j.d(typeParameters, "getTypeParameters(...)");
            Iterable<V> iterable2 = typeParameters;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (V q11 : iterable2) {
                M q12 = q11.q();
                j.d(q12, "getTypeConstructor(...)");
                arrayList2.add(q12);
            }
            List upperBounds2 = v.getUpperBounds();
            j.d(upperBounds2, "getUpperBounds(...)");
            return c(arrayList2, upperBounds2, C1353d.e(v));
        } else {
            throw new IllegalArgumentException("Unsupported descriptor type to build star projection type based on type parameters of it");
        }
    }

    public static boolean y(b bVar, Kf.e eVar, Kf.e eVar2) {
        boolean z;
        boolean z3;
        if (bVar.c0(eVar) == bVar.c0(eVar2) && bVar.h(eVar) == bVar.h(eVar2)) {
            if (bVar.m(eVar) == null) {
                z = true;
            } else {
                z = false;
            }
            if (bVar.m(eVar2) == null) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z == z3 && bVar.b(bVar.q0(eVar), bVar.q0(eVar2))) {
                if (!bVar.W(eVar, eVar2)) {
                    int c02 = bVar.c0(eVar);
                    int i2 = 0;
                    while (i2 < c02) {
                        P L = bVar.L(eVar, i2);
                        P L3 = bVar.L(eVar2, i2);
                        if (bVar.n0(L) == bVar.n0(L3) && (bVar.n0(L) || (bVar.j(L) == bVar.j(L3) && z(bVar, bVar.V(L), bVar.V(L3))))) {
                            i2++;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean z(b bVar, d dVar, d dVar2) {
        if (dVar == dVar2) {
            return true;
        }
        B g02 = bVar.g0(dVar);
        B g03 = bVar.g0(dVar2);
        if (g02 != null && g03 != null) {
            return y(bVar, g02, g03);
        }
        C0768q C5 = bVar.C(dVar);
        C0768q C6 = bVar.C(dVar2);
        if (C5 == null || C6 == null || !y(bVar, bVar.a0(C5), bVar.a0(C6)) || !y(bVar, bVar.p(C5), bVar.p(C6))) {
            return false;
        }
        return true;
    }

    public abstract Kf.e D(L l, d dVar);
}
