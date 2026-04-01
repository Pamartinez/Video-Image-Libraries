package df;

import Ae.b;
import B0.a;
import D1.f;
import Gf.h;
import Gf.i;
import Gf.j;
import Gf.m;
import Gf.p;
import He.F;
import Hf.C0774x;
import Hf.Y;
import Hf.a0;
import Hf.c0;
import If.d;
import If.l;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0830u;
import Qe.C0831v;
import Qe.O;
import Te.I;
import Te.J;
import Te.K;
import Te.Q;
import Te.r;
import Te.u;
import Tf.v;
import We.A;
import We.C0892d;
import We.o;
import We.t;
import We.x;
import Ye.c;
import Ze.C0896c;
import Ze.C0897d;
import Ze.C0898e;
import Ze.C0899f;
import Ze.H;
import Ze.w;
import a.C0068a;
import bf.C0917b;
import bf.g;
import cf.C0922a;
import java.lang.annotation.Annotation;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ne.C1182C;
import ne.C1192j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1200r;
import ne.C1202t;
import og.k;
import qf.C1236c;
import qf.C1240g;
import tf.C1301e;
import tf.C1309m;
import tf.C1311o;
import tf.C1312p;
import xf.C1353d;
import yf.C1357a;

/* renamed from: df.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0951n extends C0932A {
    public static final /* synthetic */ int v = 0;
    public final C0816f n;

    /* renamed from: o  reason: collision with root package name */
    public final o f4253o;

    /* renamed from: p  reason: collision with root package name */
    public final boolean f4254p;
    public final i q;
    public final i r;
    public final i s;
    public final i t;
    public final j u;

    /* JADX WARNING: type inference failed for: r6v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r6v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r6v3, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r6v4, types: [Gf.h, Gf.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0951n(a aVar, C0816f fVar, o oVar, boolean z, C0951n nVar) {
        super(aVar, nVar);
        kotlin.jvm.internal.j.e(aVar, "c");
        kotlin.jvm.internal.j.e(oVar, "jClass");
        this.n = fVar;
        this.f4253o = oVar;
        this.f4254p = z;
        p pVar = ((C0922a) aVar.d).f4006a;
        C0947j jVar = new C0947j(this, aVar);
        m mVar = (m) pVar;
        mVar.getClass();
        this.q = new h(mVar, jVar);
        C0948k kVar = new C0948k(this, 0);
        m mVar2 = (m) pVar;
        mVar2.getClass();
        this.r = new h(mVar2, kVar);
        C0947j jVar2 = new C0947j(aVar, this);
        m mVar3 = (m) pVar;
        mVar3.getClass();
        this.s = new h(mVar3, jVar2);
        C0948k kVar2 = new C0948k(this, 1);
        m mVar4 = (m) pVar;
        mVar4.getClass();
        this.t = new h(mVar4, kVar2);
        this.u = ((m) pVar).c(new Ff.j(2, this, aVar));
    }

    public static K A(K k, C0831v vVar, AbstractCollection abstractCollection) {
        if (abstractCollection.isEmpty()) {
            return k;
        }
        Iterator it = abstractCollection.iterator();
        while (it.hasNext()) {
            K k10 = (K) it.next();
            if (!k.equals(k10) && k10.f3797F == null && D(k10, vVar)) {
                C0831v build = k.q0().q().build();
                kotlin.jvm.internal.j.b(build);
                return (K) build;
            }
        }
        return k;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Te.K B(Te.K r5) {
        /*
            java.util.List r0 = r5.B()
            java.lang.String r1 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Object r0 = ne.C1194l.U0(r0)
            Te.Q r0 = (Te.Q) r0
            r2 = 0
            if (r0 == 0) goto L_0x007c
            r3 = r0
            Te.S r3 = (Te.S) r3
            Hf.x r3 = r3.getType()
            Hf.M r3 = r3.s0()
            Qe.i r3 = r3.g()
            if (r3 == 0) goto L_0x0036
            qf.e r3 = xf.C1353d.h(r3)
            boolean r4 = r3.d()
            if (r4 == 0) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r3 = r2
        L_0x002f:
            if (r3 == 0) goto L_0x0036
            qf.c r3 = r3.g()
            goto L_0x0037
        L_0x0036:
            r3 = r2
        L_0x0037:
            qf.c r4 = Ne.q.g
            boolean r3 = kotlin.jvm.internal.j.a(r3, r4)
            if (r3 == 0) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r0 = r2
        L_0x0041:
            if (r0 != 0) goto L_0x0044
            goto L_0x007c
        L_0x0044:
            Qe.u r2 = r5.q0()
            java.util.List r5 = r5.B()
            kotlin.jvm.internal.j.d(r5, r1)
            java.util.List r5 = ne.C1194l.J0(r5)
            Qe.u r5 = r2.a(r5)
            Te.S r0 = (Te.S) r0
            Hf.x r0 = r0.getType()
            java.util.List r0 = r0.e0()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            Hf.P r0 = (Hf.P) r0
            Hf.x r0 = r0.b()
            Qe.u r5 = r5.F(r0)
            Qe.v r5 = r5.build()
            Te.K r5 = (Te.K) r5
            if (r5 == 0) goto L_0x007b
            r0 = 1
            r5.y = r0
        L_0x007b:
            return r5
        L_0x007c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0951n.B(Te.K):Te.K");
    }

    public static boolean D(C0831v vVar, C0831v vVar2) {
        C1309m b = C1311o.f5142c.n(vVar2, vVar, true).b();
        kotlin.jvm.internal.j.d(b, "getResult(...)");
        if (b != C1309m.OVERRIDABLE || k.n(vVar2, vVar)) {
            return false;
        }
        return true;
    }

    public static boolean E(K k, K k10) {
        int i2 = C0896c.l;
        kotlin.jvm.internal.j.e(k, "<this>");
        if (kotlin.jvm.internal.j.a(k.getName().b(), "removeAt") && kotlin.jvm.internal.j.a(C0068a.n(k), H.g.e)) {
            k10 = k10.a();
        }
        kotlin.jvm.internal.j.b(k10);
        return D(k10, k);
    }

    public static K F(O o2, String str, b bVar) {
        K k;
        boolean z;
        Iterator it = ((Iterable) bVar.invoke(C1240g.e(str))).iterator();
        do {
            k = null;
            if (!it.hasNext()) {
                break;
            }
            K k10 = (K) it.next();
            if (k10.B().size() == 0) {
                l lVar = d.f3459a;
                C0774x xVar = k10.k;
                if (xVar == null) {
                    z = false;
                } else {
                    z = lVar.b(xVar, o2.getType());
                }
                if (z) {
                    k = k10;
                    continue;
                } else {
                    continue;
                }
            }
        } while (k == null);
        return k;
    }

    public static K H(O o2, b bVar) {
        K k;
        C0774x xVar;
        String b = o2.getName().b();
        kotlin.jvm.internal.j.d(b, "asString(...)");
        Iterator it = ((Iterable) bVar.invoke(C1240g.e(w.b(b)))).iterator();
        do {
            k = null;
            if (!it.hasNext()) {
                break;
            }
            K k10 = (K) it.next();
            if (k10.B().size() == 1 && (xVar = k10.k) != null) {
                C1240g gVar = Ne.i.e;
                if (!Ne.i.D(xVar, Ne.p.d)) {
                    continue;
                } else {
                    l lVar = d.f3459a;
                    List B = k10.B();
                    kotlin.jvm.internal.j.d(B, "getValueParameters(...)");
                    if (lVar.a(((Q) C1194l.b1(B)).getType(), o2.getType())) {
                        k = k10;
                        continue;
                    } else {
                        continue;
                    }
                }
            }
        } while (k == null);
        return k;
    }

    public static boolean K(K k, C0831v vVar) {
        String m = C0068a.m(k, 2);
        C0831v a7 = vVar.a();
        kotlin.jvm.internal.j.d(a7, "getOriginal(...)");
        if (!m.equals(C0068a.m(a7, 2)) || D(k, vVar)) {
            return false;
        }
        return true;
    }

    public final boolean C(O o2, b bVar) {
        if (Gd.a.M(o2)) {
            return false;
        }
        K G5 = G(o2, bVar);
        K H5 = H(o2, bVar);
        if (G5 == null) {
            return false;
        }
        if (!o2.G()) {
            return true;
        }
        if (H5 == null || H5.k() != G5.k()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.util.Map, java.lang.Object] */
    public final K G(O o2, b bVar) {
        I i2;
        C1240g gVar;
        I getter = o2.getGetter();
        String str = null;
        if (getter != null) {
            i2 = (I) f.u(getter);
        } else {
            i2 = null;
        }
        if (i2 != null) {
            Ne.i.z(i2);
            C0814d b = C1353d.b(C1353d.k(i2), C0897d.g);
            if (!(b == null || (gVar = (C1240g) C0899f.f3942a.get(C1353d.g(b))) == null)) {
                str = gVar.b();
            }
        }
        if (str != null && !f.z(this.n, i2)) {
            return F(o2, str, bVar);
        }
        String b5 = o2.getName().b();
        kotlin.jvm.internal.j.d(b5, "asString(...)");
        return F(o2, w.a(b5), bVar);
    }

    public final LinkedHashSet I(C1240g gVar) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C0774x A10 : z()) {
            C1200r.A0(A10.A().a(gVar, c.WHEN_GET_SUPER_MEMBERS), linkedHashSet);
        }
        return linkedHashSet;
    }

    public final Set J(C1240g gVar) {
        ArrayList arrayList = new ArrayList();
        for (C0774x A10 : z()) {
            Iterable<O> f = A10.A().f(gVar, c.WHEN_GET_SUPER_MEMBERS);
            ArrayList arrayList2 = new ArrayList(C1196n.w0(f, 10));
            for (O add : f) {
                arrayList2.add(add);
            }
            C1200r.A0(arrayList2, arrayList);
        }
        return C1194l.p1(arrayList);
    }

    public final boolean L(K k) {
        Iterable iterable;
        C1240g name = k.getName();
        kotlin.jvm.internal.j.d(name, "getName(...)");
        String b = name.b();
        kotlin.jvm.internal.j.d(b, "asString(...)");
        C1236c cVar = w.f3962a;
        if (v.t0(b, "get") || v.t0(b, "is")) {
            C1240g O4 = B1.a.O(name, "get", (String) null, 12);
            if (O4 == null) {
                O4 = B1.a.O(name, "is", (String) null, 8);
            }
            iterable = C1195m.r0(O4);
        } else if (v.t0(b, "set")) {
            iterable = C1192j.l0(new C1240g[]{B1.a.O(name, "set", (String) null, 4), B1.a.O(name, "set", "is", 4)});
        } else {
            iterable = (List) C0899f.b.get(name);
            if (iterable == null) {
                iterable = C1202t.d;
            }
        }
        Iterable<C1240g> iterable2 = iterable;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            for (C1240g J4 : iterable2) {
                Set<O> J7 = J(J4);
                if (!J7.isEmpty()) {
                    for (O o2 : J7) {
                        if (C(o2, new Ff.j(3, k, this))) {
                            if (o2.G()) {
                                return false;
                            }
                            String b5 = k.getName().b();
                            kotlin.jvm.internal.j.d(b5, "asString(...)");
                            if (!v.t0(b5, "set")) {
                                return false;
                            }
                        }
                    }
                    continue;
                }
            }
        }
        ArrayList arrayList = H.f3932a;
        C1240g name2 = k.getName();
        kotlin.jvm.internal.j.d(name2, "getName(...)");
        C1240g gVar = (C1240g) H.k.get(name2);
        if (gVar != null) {
            LinkedHashSet I6 = I(gVar);
            ArrayList arrayList2 = new ArrayList();
            for (Object next : I6) {
                K k10 = (K) next;
                kotlin.jvm.internal.j.e(k10, "<this>");
                if (f.u(k10) != null) {
                    arrayList2.add(next);
                }
            }
            if (!arrayList2.isEmpty()) {
                C0830u q0 = k.q0();
                q0.A(gVar);
                q0.J();
                q0.z();
                C0831v build = q0.build();
                kotlin.jvm.internal.j.b(build);
                K k11 = (K) build;
                if (!arrayList2.isEmpty()) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        if (E((K) it.next(), k11)) {
                            return false;
                        }
                    }
                }
            }
        }
        int i2 = C0898e.l;
        C1240g name3 = k.getName();
        kotlin.jvm.internal.j.d(name3, "getName(...)");
        if (C0898e.b(name3)) {
            C1240g name4 = k.getName();
            kotlin.jvm.internal.j.d(name4, "getName(...)");
            LinkedHashSet<K> I8 = I(name4);
            ArrayList arrayList3 = new ArrayList();
            for (K a7 : I8) {
                C0831v a10 = C0898e.a(a7);
                if (a10 != null) {
                    arrayList3.add(a10);
                }
            }
            if (!arrayList3.isEmpty()) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    if (K(k, (C0831v) it2.next())) {
                        return false;
                    }
                }
            }
        }
        K B = B(k);
        if (B == null) {
            return true;
        }
        C1240g name5 = k.getName();
        kotlin.jvm.internal.j.d(name5, "getName(...)");
        LinkedHashSet<K> I9 = I(name5);
        if (I9.isEmpty()) {
            return true;
        }
        for (K k12 : I9) {
            if (k12.isSuspend() && D(B, k12)) {
                return false;
            }
        }
        return true;
    }

    public final void M(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        kotlin.jvm.internal.j.e(((C0922a) this.b.d).n, "<this>");
        kotlin.jvm.internal.j.e(this.n, "scopeOwner");
    }

    public final ArrayList N(C1240g gVar) {
        Iterable<x> e = ((C0940c) this.e.invoke()).e(gVar);
        ArrayList arrayList = new ArrayList(C1196n.w0(e, 10));
        for (x t3 : e) {
            arrayList.add(t(t3));
        }
        return arrayList;
    }

    public final ArrayList O(C1240g gVar) {
        LinkedHashSet I6 = I(gVar);
        ArrayList arrayList = new ArrayList();
        for (Object next : I6) {
            K k = (K) next;
            kotlin.jvm.internal.j.e(k, "<this>");
            if (f.u(k) == null && C0898e.a(k) == null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final Collection a(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        M(gVar, aVar);
        return super.a(gVar, aVar);
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        j jVar;
        C0816f fVar;
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        M(gVar, aVar);
        C0951n nVar = (C0951n) this.f4231c;
        if (nVar == null || (jVar = nVar.u) == null || (fVar = (C0816f) jVar.invoke(gVar)) == null) {
            return (C0819i) this.u.invoke(gVar);
        }
        return fVar;
    }

    public final Collection f(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        M(gVar, aVar);
        return super.f(gVar, aVar);
    }

    public final Set h(Af.f fVar, b bVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        return C1182C.b0((Set) this.r.invoke(), ((Map) this.t.invoke()).keySet());
    }

    public final Set i(Af.f fVar, Af.m mVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        C0816f fVar2 = this.n;
        Collection<C0774x> h5 = fVar2.q().h();
        kotlin.jvm.internal.j.d(h5, "getSupertypes(...)");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C0774x A10 : h5) {
            C1200r.A0(A10.A().b(), linkedHashSet);
        }
        i iVar = this.e;
        linkedHashSet.addAll(((C0940c) iVar.invoke()).a());
        linkedHashSet.addAll(((C0940c) iVar.invoke()).b());
        linkedHashSet.addAll(h(fVar, mVar));
        a aVar = this.b;
        linkedHashSet.addAll(((C1357a) ((C0922a) aVar.d).f4013x).e(fVar2, aVar));
        return linkedHashSet;
    }

    public final void j(ArrayList arrayList, C1240g gVar) {
        ArrayList arrayList2 = arrayList;
        C1240g gVar2 = gVar;
        kotlin.jvm.internal.j.e(gVar2, "name");
        boolean g = this.f4253o.g();
        C0816f fVar = this.n;
        a aVar = this.b;
        if (g) {
            i iVar = this.e;
            if (((C0940c) iVar.invoke()).f(gVar2) != null) {
                if (!arrayList2.isEmpty()) {
                    Iterator it = arrayList2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((K) it.next()).B().isEmpty()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                A f = ((C0940c) iVar.invoke()).f(gVar2);
                kotlin.jvm.internal.j.b(f);
                cf.c O4 = F.O(aVar, f);
                C0922a aVar2 = (C0922a) aVar.d;
                g T0 = g.T0(fVar, O4, f.c(), aVar2.f4010j.b(f), true);
                C0774x p6 = ((A0.l) aVar.f34h).p(f.f(), C0068a.Y(Y.COMMON, false, (C0937F) null, 6));
                u p8 = p();
                Qe.A.Companion.getClass();
                Qe.A a7 = Qe.A.OPEN;
                C0826p pVar = C0827q.e;
                C1202t tVar = C1202t.d;
                T0.S0((u) null, p8, tVar, tVar, tVar, p6, a7, pVar, (Map) null);
                T0.U0(false, false);
                aVar2.g.getClass();
                arrayList2.add(T0);
            }
        }
        ((C1357a) ((C0922a) aVar.d).f4013x).b(fVar, gVar2, arrayList2, aVar);
    }

    public final C0940c k() {
        return new C0938a(this.f4253o, C0949l.e);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m(java.util.LinkedHashSet r11, qf.C1240g r12) {
        /*
            r10 = this;
            java.lang.String r3 = "name"
            kotlin.jvm.internal.j.e(r12, r3)
            java.util.LinkedHashSet r4 = r10.I(r12)
            java.util.ArrayList r3 = Ze.H.f3932a
            java.util.HashSet r3 = Ze.H.f3936j
            boolean r3 = r3.contains(r12)
            if (r3 != 0) goto L_0x005c
            boolean r3 = Ze.C0898e.b(r12)
            if (r3 != 0) goto L_0x005c
            boolean r3 = r4.isEmpty()
            if (r3 == 0) goto L_0x0020
            goto L_0x0037
        L_0x0020:
            java.util.Iterator r3 = r4.iterator()
        L_0x0024:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0037
            java.lang.Object r5 = r3.next()
            Qe.v r5 = (Qe.C0831v) r5
            boolean r5 = r5.isSuspend()
            if (r5 == 0) goto L_0x0024
            goto L_0x005c
        L_0x0037:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x0040:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0057
            java.lang.Object r5 = r4.next()
            r6 = r5
            Te.K r6 = (Te.K) r6
            boolean r6 = r10.L(r6)
            if (r6 == 0) goto L_0x0040
            r3.add(r5)
            goto L_0x0040
        L_0x0057:
            r4 = 0
            r10.w(r11, r12, r3, r4)
            return
        L_0x005c:
            int r3 = Qf.h.f
            Qf.h r9 = Qf.k.e()
            B0.a r3 = r10.b
            java.lang.Object r3 = r3.d
            cf.a r3 = (cf.C0922a) r3
            If.k r3 = r3.u
            If.l r3 = (If.l) r3
            tf.o r8 = r3.d
            ne.t r5 = ne.C1202t.d
            Qe.f r6 = r10.n
            Df.m r7 = Df.p.f3362a
            r3 = r12
            java.util.LinkedHashSet r1 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.O(r3, r4, r5, r6, r7, r8)
            r6 = r4
            Ef.b r5 = new Ef.b
            r3 = 5
            r7 = 1
            r5.<init>(r7, r10, r3)
            r4 = r11
            r0 = r10
            r2 = r11
            r3 = r1
            r1 = r12
            r0.x(r1, r2, r3, r4, r5)
            Ef.b r5 = new Ef.b
            r1 = 6
            r5.<init>(r7, r10, r1)
            r1 = r12
            r4 = r9
            r0.x(r1, r2, r3, r4, r5)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r5 = r6.iterator()
        L_0x009d:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00b4
            java.lang.Object r6 = r5.next()
            r8 = r6
            Te.K r8 = (Te.K) r8
            boolean r8 = r10.L(r8)
            if (r8 == 0) goto L_0x009d
            r3.add(r6)
            goto L_0x009d
        L_0x00b4:
            java.util.ArrayList r3 = ne.C1194l.X0(r4, r3)
            r10.w(r11, r12, r3, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0951n.m(java.util.LinkedHashSet, qf.g):void");
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [me.f, java.lang.Object] */
    public final void n(ArrayList arrayList, C1240g gVar) {
        Set set;
        x xVar;
        ArrayList arrayList2 = arrayList;
        C1240g gVar2 = gVar;
        kotlin.jvm.internal.j.e(gVar2, "name");
        boolean isAnnotation = this.f4253o.f3891a.isAnnotation();
        a aVar = this.b;
        if (isAnnotation && (xVar = (x) C1194l.c1(((C0940c) this.e.invoke()).e(gVar2))) != null) {
            Qe.A a7 = Qe.A.FINAL;
            bf.h M02 = bf.h.M0(this.n, F.O(aVar, xVar), a7, Gd.a.i0(xVar.e()), false, xVar.c(), ((C0922a) aVar.d).f4010j.b(xVar), false);
            I f = C1312p.f(M02, Re.g.f3692a);
            M02.I0(f, (J) null, (r) null, (r) null);
            kotlin.jvm.internal.j.e(aVar, "<this>");
            C0774x l = C0932A.l(xVar, new a((C0922a) aVar.d, new Ed.b(aVar, M02, xVar, 0), aVar.f));
            u p6 = p();
            C1202t tVar = C1202t.d;
            M02.L0(l, tVar, p6, (u) null, tVar);
            f.q = l;
            arrayList2.add(M02);
        }
        Set J4 = J(gVar2);
        if (!J4.isEmpty()) {
            int i2 = Qf.h.f;
            Qf.h e = Qf.k.e();
            Qf.h e7 = Qf.k.e();
            y(J4, arrayList2, e, new C0950m(this, 0));
            if (e.isEmpty()) {
                set = C1194l.p1(J4);
            } else {
                set = new LinkedHashSet();
                for (Object next : J4) {
                    if (!e.contains(next)) {
                        set.add(next);
                    }
                }
            }
            y(set, e7, (Qf.h) null, new C0950m(this, 1));
            LinkedHashSet b0 = C1182C.b0(J4, e7);
            C0922a aVar2 = (C0922a) aVar.d;
            Df.p pVar = aVar2.f;
            C1311o oVar = ((l) aVar2.u).d;
            LinkedHashSet linkedHashSet = b0;
            C0816f fVar = this.n;
            arrayList2.addAll(com.samsung.context.sdk.samsunganalytics.internal.sender.c.O(gVar2, linkedHashSet, arrayList2, fVar, pVar, oVar));
        }
    }

    public final Set o(Af.f fVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        if (this.f4253o.f3891a.isAnnotation()) {
            return b();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((C0940c) this.e.invoke()).c());
        Collection<C0774x> h5 = this.n.q().h();
        kotlin.jvm.internal.j.d(h5, "getSupertypes(...)");
        for (C0774x A10 : h5) {
            C1200r.A0(A10.A().g(), linkedHashSet);
        }
        return linkedHashSet;
    }

    public final u p() {
        C0816f fVar = this.n;
        if (fVar != null) {
            int i2 = C1301e.f5137a;
            return fVar.v0();
        }
        C1301e.a(0);
        throw null;
    }

    public final C0822l q() {
        return this.n;
    }

    public final boolean r(g gVar) {
        if (this.f4253o.f3891a.isAnnotation()) {
            return false;
        }
        return L(gVar);
    }

    public final C0963z s(x xVar, ArrayList arrayList, C0774x xVar2, List list) {
        kotlin.jvm.internal.j.e(xVar, "method");
        ((C0922a) this.b.d).e.getClass();
        if (this.n != null) {
            List list2 = Collections.EMPTY_LIST;
            if (list2 != null) {
                return new C0963z(xVar2, list, arrayList, list2);
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[]{"signatureErrors", "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature", "<init>"}));
        }
        Object[] objArr = new Object[3];
        switch (1) {
            case 1:
                objArr[0] = "owner";
                break;
            case 2:
                objArr[0] = "returnType";
                break;
            case 3:
                objArr[0] = "valueParameters";
                break;
            case 4:
                objArr[0] = "typeParameters";
                break;
            case 5:
                objArr[0] = "descriptor";
                break;
            case 6:
                objArr[0] = "signatureErrors";
                break;
            default:
                objArr[0] = "method";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$1";
        objArr[2] = "resolvePropagatedSignature";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final String toString() {
        return "Lazy Java member scope for " + this.f4253o.c();
    }

    public final void v(ArrayList arrayList, C0917b bVar, int i2, x xVar, C0774x xVar2, C0774x xVar3) {
        Object obj;
        boolean z;
        x xVar4 = xVar;
        C0774x xVar5 = xVar2;
        C0774x xVar6 = xVar3;
        C1240g c5 = xVar4.c();
        c0 c0Var = null;
        if (xVar5 != null) {
            c0 g = a0.g(xVar5, false);
            Object defaultValue = xVar4.f3895a.getDefaultValue();
            if (defaultValue != null) {
                Class<?> cls = defaultValue.getClass();
                List list = C0892d.f3885a;
                if (Enum.class.isAssignableFrom(cls)) {
                    obj = new t((C1240g) null, (Enum) defaultValue);
                } else if (defaultValue instanceof Annotation) {
                    obj = new We.g((C1240g) null, (Annotation) defaultValue);
                } else if (defaultValue instanceof Object[]) {
                    obj = new We.h((C1240g) null, (Object[]) defaultValue);
                } else if (defaultValue instanceof Class) {
                    obj = new We.p((C1240g) null, (Class) defaultValue);
                } else {
                    obj = new We.v((C1240g) null, defaultValue);
                }
            } else {
                obj = null;
            }
            if (obj != null) {
                z = true;
            } else {
                z = false;
            }
            if (xVar6 != null) {
                c0Var = a0.g(xVar6, false);
            }
            arrayList.add(new Q(bVar, (Q) null, i2, Re.g.f3692a, c5, g, z, false, false, c0Var, ((C0922a) this.b.d).f4010j.b(xVar4)));
            return;
        }
        a0.a(2);
        throw null;
    }

    public final void w(LinkedHashSet linkedHashSet, C1240g gVar, ArrayList arrayList, boolean z) {
        C0922a aVar = (C0922a) this.b.d;
        LinkedHashSet linkedHashSet2 = linkedHashSet;
        C1240g gVar2 = gVar;
        ArrayList arrayList2 = arrayList;
        LinkedHashSet<K> O4 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.O(gVar2, arrayList2, linkedHashSet2, this.n, aVar.f, ((l) aVar.u).d);
        if (!z) {
            linkedHashSet2.addAll(O4);
            return;
        }
        ArrayList X02 = C1194l.X0(O4, linkedHashSet2);
        ArrayList arrayList3 = new ArrayList(C1196n.w0(O4, 10));
        for (K k : O4) {
            K k10 = (K) f.v(k);
            if (k10 != null) {
                k = A(k, k10, X02);
            }
            arrayList3.add(k);
        }
        linkedHashSet2.addAll(arrayList3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0134 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void x(qf.C1240g r11, java.util.LinkedHashSet r12, java.util.LinkedHashSet r13, java.util.AbstractSet r14, Ae.b r15) {
        /*
            r10 = this;
            java.util.Iterator r13 = r13.iterator()
        L_0x0004:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x0139
            java.lang.Object r0 = r13.next()
            Te.K r0 = (Te.K) r0
            Qe.d r1 = D1.f.u(r0)
            Te.K r1 = (Te.K) r1
            r2 = 0
            if (r1 != 0) goto L_0x001b
        L_0x0019:
            r1 = r2
            goto L_0x005c
        L_0x001b:
            java.lang.String r3 = D1.f.s(r1)
            kotlin.jvm.internal.j.b(r3)
            qf.g r3 = qf.C1240g.e(r3)
            java.lang.Object r3 = r15.invoke(r3)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x0030:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0019
            java.lang.Object r4 = r3.next()
            Te.K r4 = (Te.K) r4
            Qe.u r4 = r4.q0()
            r4.A(r11)
            r4.J()
            r4.z()
            Qe.v r4 = r4.build()
            kotlin.jvm.internal.j.b(r4)
            Te.K r4 = (Te.K) r4
            boolean r5 = E(r1, r4)
            if (r5 == 0) goto L_0x0030
            Te.K r1 = A(r4, r1, r12)
        L_0x005c:
            Qf.k.a(r14, r1)
            Qe.v r1 = Ze.C0898e.a(r0)
            java.lang.String r3 = "getName(...)"
            if (r1 != 0) goto L_0x006a
        L_0x0067:
            r1 = r2
            goto L_0x00fc
        L_0x006a:
            r4 = r1
            Te.m r4 = (Te.C0852m) r4
            qf.g r4 = r4.getName()
            kotlin.jvm.internal.j.d(r4, r3)
            java.lang.Object r4 = r15.invoke(r4)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x007e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0092
            java.lang.Object r5 = r4.next()
            r6 = r5
            Te.K r6 = (Te.K) r6
            boolean r6 = K(r6, r1)
            if (r6 == 0) goto L_0x007e
            goto L_0x0093
        L_0x0092:
            r5 = r2
        L_0x0093:
            Te.K r5 = (Te.K) r5
            if (r5 == 0) goto L_0x00eb
            Qe.u r4 = r5.q0()
            java.util.List r6 = r1.B()
            java.lang.String r7 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r8 = new java.util.ArrayList
            r9 = 10
            int r9 = ne.C1196n.w0(r6, r9)
            r8.<init>(r9)
            java.util.Iterator r6 = r6.iterator()
        L_0x00b5:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x00cb
            java.lang.Object r9 = r6.next()
            Te.Q r9 = (Te.Q) r9
            Te.S r9 = (Te.S) r9
            Hf.x r9 = r9.getType()
            r8.add(r9)
            goto L_0x00b5
        L_0x00cb:
            java.util.List r5 = r5.B()
            kotlin.jvm.internal.j.d(r5, r7)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.ArrayList r5 = B1.a.p(r8, r5, r1)
            r4.a(r5)
            r4.J()
            r4.z()
            r4.B()
            Qe.v r4 = r4.build()
            Te.K r4 = (Te.K) r4
            goto L_0x00ec
        L_0x00eb:
            r4 = r2
        L_0x00ec:
            if (r4 == 0) goto L_0x0067
            boolean r5 = r10.L(r4)
            if (r5 == 0) goto L_0x00f5
            goto L_0x00f6
        L_0x00f5:
            r4 = r2
        L_0x00f6:
            if (r4 == 0) goto L_0x0067
            Te.K r1 = A(r4, r1, r12)
        L_0x00fc:
            Qf.k.a(r14, r1)
            boolean r1 = r0.isSuspend()
            if (r1 != 0) goto L_0x0106
            goto L_0x0134
        L_0x0106:
            qf.g r1 = r0.getName()
            kotlin.jvm.internal.j.d(r1, r3)
            java.lang.Object r1 = r15.invoke(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0117:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0134
            java.lang.Object r3 = r1.next()
            Te.K r3 = (Te.K) r3
            Te.K r3 = B(r3)
            if (r3 == 0) goto L_0x0130
            boolean r4 = D(r3, r0)
            if (r4 == 0) goto L_0x0130
            goto L_0x0131
        L_0x0130:
            r3 = r2
        L_0x0131:
            if (r3 == 0) goto L_0x0117
            r2 = r3
        L_0x0134:
            Qf.k.a(r14, r2)
            goto L_0x0004
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0951n.x(qf.g, java.util.LinkedHashSet, java.util.LinkedHashSet, java.util.AbstractSet, Ae.b):void");
    }

    public final void y(Set set, AbstractCollection abstractCollection, Qf.h hVar, b bVar) {
        bf.h hVar2;
        K k;
        boolean z;
        J j2;
        Qf.h hVar3 = hVar;
        b bVar2 = bVar;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            O o2 = (O) it.next();
            if (!C(o2, bVar2)) {
                hVar2 = null;
            } else {
                K G5 = G(o2, bVar2);
                kotlin.jvm.internal.j.b(G5);
                if (o2.G()) {
                    k = H(o2, bVar2);
                    kotlin.jvm.internal.j.b(k);
                } else {
                    k = null;
                }
                if (k != null) {
                    k.k();
                    G5.k();
                }
                C0816f fVar = this.n;
                kotlin.jvm.internal.j.e(fVar, "ownerDescriptor");
                Qe.A k10 = G5.k();
                C0826p visibility = G5.getVisibility();
                if (k != null) {
                    z = true;
                } else {
                    z = false;
                }
                bf.h hVar4 = new bf.h(fVar, Re.g.f3692a, k10, visibility, z, o2.getName(), G5.getSource(), (O) null, C0813c.DECLARATION, false, (me.i) null);
                C0774x xVar = G5.k;
                kotlin.jvm.internal.j.b(xVar);
                u p6 = p();
                C1202t tVar = C1202t.d;
                hVar4.L0(xVar, tVar, p6, (u) null, tVar);
                I l = C1312p.l(hVar4, G5.getAnnotations(), false, G5.getSource());
                l.f3750p = G5;
                l.H0(hVar4.getType());
                if (k != null) {
                    List B = k.B();
                    kotlin.jvm.internal.j.d(B, "getValueParameters(...)");
                    Q q10 = (Q) C1194l.N0(B);
                    if (q10 != null) {
                        j2 = C1312p.m(hVar4, k.getAnnotations(), q10.getAnnotations(), false, k.getVisibility(), k.getSource());
                        j2.f3750p = k;
                    } else {
                        throw new AssertionError("No parameter found for " + k);
                    }
                } else {
                    j2 = null;
                }
                hVar4.I0(l, j2, (r) null, (r) null);
                hVar2 = hVar4;
            }
            AbstractCollection abstractCollection2 = abstractCollection;
            if (hVar2 != null) {
                abstractCollection2.add(hVar2);
                if (hVar3 != null) {
                    hVar3.add(o2);
                    return;
                }
                return;
            }
        }
    }

    public final Collection z() {
        boolean z = this.f4254p;
        C0816f fVar = this.n;
        if (z) {
            Collection h5 = fVar.q().h();
            kotlin.jvm.internal.j.d(h5, "getSupertypes(...)");
            return h5;
        }
        ((l) ((C0922a) this.b.d).u).getClass();
        kotlin.jvm.internal.j.e(fVar, "classDescriptor");
        Collection h6 = fVar.q().h();
        kotlin.jvm.internal.j.d(h6, "getSupertypes(...)");
        return h6;
    }
}
