package Qe;

import Af.l;
import Af.p;
import D0.f;
import Hf.M;
import Sf.c;
import Sf.d;
import Sf.h;
import Sf.k;
import Sf.n;
import Sf.o;
import Sf.s;
import Te.w;
import Ye.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import tf.C1312p;
import xf.C1351b;
import xf.C1353d;

/* renamed from: Qe.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0833x {

    /* renamed from: a  reason: collision with root package name */
    public static final B f3684a = new B("InvalidModuleNotifier", 0);

    /* JADX WARNING: type inference failed for: r3v0, types: [Qe.l] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final A0.l a(Hf.B r5, Qe.C0820j r6, int r7) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x005a
            boolean r1 = Jf.l.f(r6)
            if (r1 == 0) goto L_0x000a
            goto L_0x005a
        L_0x000a:
            java.util.List r1 = r6.j()
            int r1 = r1.size()
            int r1 = r1 + r7
            boolean r2 = r6.s()
            if (r2 != 0) goto L_0x003d
            java.util.List r2 = r5.e0()
            int r2 = r2.size()
            if (r1 == r2) goto L_0x0027
            boolean r1 = tf.C1301e.o(r6)
        L_0x0027:
            A0.l r1 = new A0.l
            java.util.List r2 = r5.e0()
            java.util.List r5 = r5.e0()
            int r5 = r5.size()
            java.util.List r5 = r2.subList(r7, r5)
            r1.<init>((Qe.C0820j) r6, (java.util.List) r5, (A0.l) r0)
            return r1
        L_0x003d:
            java.util.List r2 = r5.e0()
            java.util.List r7 = r2.subList(r7, r1)
            A0.l r2 = new A0.l
            Qe.l r3 = r6.g()
            boolean r4 = r3 instanceof Qe.C0820j
            if (r4 == 0) goto L_0x0052
            r0 = r3
            Qe.j r0 = (Qe.C0820j) r0
        L_0x0052:
            A0.l r5 = a(r5, r0, r1)
            r2.<init>((Qe.C0820j) r6, (java.util.List) r7, (A0.l) r5)
            return r2
        L_0x005a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Qe.C0833x.a(Hf.B, Qe.j, int):A0.l");
    }

    public static final void b(K k, C1236c cVar, ArrayList arrayList) {
        j.e(k, "<this>");
        j.e(cVar, "fqName");
        k.a(cVar, arrayList);
    }

    public static final List c(C0820j jVar) {
        k kVar;
        k kVar2;
        List list;
        Object obj;
        M q;
        List j2 = jVar.j();
        j.d(j2, "getDeclaredTypeParameters(...)");
        if (!jVar.s() && !(jVar.g() instanceof C0812b)) {
            return j2;
        }
        int i2 = C1353d.f5167a;
        C1351b bVar = C1351b.e;
        k s0 = n.s0(bVar, jVar);
        if (s0 instanceof d) {
            kVar = ((d) s0).a();
        } else {
            kVar = new c(s0, 1);
        }
        List v02 = n.v0(new h(n.p0(new o(2, kVar), C0828s.g), C0828s.f3682h, s.d));
        k s02 = n.s0(bVar, jVar);
        if (s02 instanceof d) {
            kVar2 = ((d) s02).a();
        } else {
            kVar2 = new c(s02, 1);
        }
        Iterator it = kVar2.iterator();
        while (true) {
            list = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (obj instanceof C0816f) {
                break;
            }
        }
        C0816f fVar = (C0816f) obj;
        if (!(fVar == null || (q = fVar.q()) == null)) {
            list = q.getParameters();
        }
        if (list == null) {
            list = C1202t.d;
        }
        if (!v02.isEmpty() || !list.isEmpty()) {
            ArrayList X02 = C1194l.X0(list, v02);
            ArrayList arrayList = new ArrayList(C1196n.w0(X02, 10));
            Iterator it2 = X02.iterator();
            while (it2.hasNext()) {
                V v = (V) it2.next();
                j.b(v);
                arrayList.add(new C0815e(v, jVar, j2.size()));
            }
            return C1194l.X0(arrayList, j2);
        }
        List j3 = jVar.j();
        j.d(j3, "getDeclaredTypeParameters(...)");
        return j3;
    }

    public static final C0816f d(C c5, C1235b bVar) {
        j.e(c5, "<this>");
        j.e(bVar, "classId");
        C0819i e = e(c5, bVar);
        if (e instanceof C0816f) {
            return (C0816f) e;
        }
        return null;
    }

    public static final C0819i e(C c5, C1235b bVar) {
        j.e(c5, "<this>");
        j.e(bVar, "classId");
        if (c5.x(C1312p.f5144a) == null) {
            L n02 = c5.n0(bVar.f5033a);
            List e = bVar.b.f5036a.e();
            l lVar = ((w) n02).k;
            Object L02 = C1194l.L0(e);
            j.d(L02, "first(...)");
            C0819i c6 = lVar.c((C1240g) L02, Ye.c.FROM_DESERIALIZATION);
            if (c6 != null) {
                for (C1240g gVar : e.subList(1, e.size())) {
                    if (c6 instanceof C0816f) {
                        p M2 = ((C0816f) c6).M();
                        j.b(gVar);
                        C0819i c8 = M2.c(gVar, Ye.c.FROM_DESERIALIZATION);
                        if (c8 instanceof C0816f) {
                            c6 = (C0816f) c8;
                            continue;
                        } else {
                            c6 = null;
                            continue;
                        }
                        if (c6 == null) {
                        }
                    }
                }
                return c6;
            }
            return null;
        }
        throw new ClassCastException();
    }

    public static final C0816f f(C c5, C1235b bVar, f fVar) {
        j.e(c5, "<this>");
        j.e(bVar, "classId");
        j.e(fVar, "notFoundClasses");
        C0816f d = d(c5, bVar);
        if (d != null) {
            return d;
        }
        return fVar.E(bVar, n.v0(n.t0(n.s0(C0829t.d, bVar), C0828s.e)));
    }

    public static final C0819i g(C0822l lVar) {
        C0822l g = lVar.g();
        if (g == null || (lVar instanceof H)) {
            return null;
        }
        if (!(g.g() instanceof H)) {
            return g(g);
        }
        if (g instanceof C0819i) {
            return (C0819i) g;
        }
        return null;
    }

    public static final boolean h(K k, C1236c cVar) {
        j.e(k, "<this>");
        j.e(cVar, "fqName");
        return k.b(cVar);
    }

    public static final ArrayList i(K k, C1236c cVar) {
        j.e(k, "<this>");
        j.e(cVar, "fqName");
        ArrayList arrayList = new ArrayList();
        b(k, cVar, arrayList);
        return arrayList;
    }

    public static final C0816f j(C c5, C1236c cVar, a aVar) {
        C0816f fVar;
        C0819i iVar;
        p M2;
        j.e(c5, "<this>");
        j.e(cVar, "fqName");
        j.e(aVar, "lookupLocation");
        if (!cVar.d()) {
            l lVar = ((w) c5.n0(cVar.e())).k;
            C1240g f = cVar.f();
            j.d(f, "shortName(...)");
            C0819i c6 = lVar.c(f, aVar);
            if (c6 instanceof C0816f) {
                fVar = (C0816f) c6;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                return fVar;
            }
            C0816f j2 = j(c5, cVar.e(), aVar);
            if (j2 == null || (M2 = j2.M()) == null) {
                iVar = null;
            } else {
                C1240g f5 = cVar.f();
                j.d(f5, "shortName(...)");
                iVar = M2.c(f5, aVar);
            }
            if (iVar instanceof C0816f) {
                return (C0816f) iVar;
            }
        }
        return null;
    }
}
