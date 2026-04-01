package If;

import B1.a;
import Hf.B;
import Hf.C0754c;
import Hf.C0764m;
import Hf.C0768q;
import Hf.G;
import Hf.M;
import Hf.P;
import Hf.c0;
import Hf.d0;
import Jf.k;
import Jf.l;
import Kf.b;
import Kf.c;
import Kf.d;
import Kf.e;
import Kf.f;
import Kf.g;
import Kf.i;
import Qe.V;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import hf.C1084f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import ne.C1194l;
import ne.C1196n;
import uf.C1316a;
import uf.C1317b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements b {
    public static final m d = new Object();

    public final i A(V v) {
        j.e(v, "$receiver");
        d0 t = v.t();
        j.d(t, "getVariance(...)");
        return a.o(t);
    }

    public final boolean B(e eVar) {
        j.e(eVar, "<this>");
        return g.D(g.W(eVar));
    }

    public final C0768q C(d dVar) {
        return g.g(dVar);
    }

    public final a D(e eVar) {
        return g.U(this, eVar);
    }

    public final P E(f fVar, int i2) {
        j.e(fVar, "<this>");
        if (fVar instanceof e) {
            return g.p((d) fVar, i2);
        }
        if (fVar instanceof Kf.a) {
            Object obj = ((Kf.a) fVar).get(i2);
            j.d(obj, "get(...)");
            return (P) obj;
        }
        throw new IllegalStateException(("unknown type argument list type: " + fVar + ArcCommonLog.TAG_COMMA + v.f4727a.b(fVar.getClass())).toString());
    }

    public final boolean G(d dVar) {
        C0764m mVar;
        j.e(dVar, "<this>");
        B h5 = g.h(dVar);
        if (h5 != null) {
            mVar = g.f(h5);
        } else {
            mVar = null;
        }
        if (mVar != null) {
            return true;
        }
        return false;
    }

    public final P H(e eVar, int i2) {
        j.e(eVar, "<this>");
        if (i2 < 0 || i2 >= g.c(eVar)) {
            return null;
        }
        return g.p(eVar, i2);
    }

    public final B I(e eVar, boolean z) {
        return g.Z(eVar, z);
    }

    public final boolean J(c cVar) {
        return g.J(cVar);
    }

    public final M K(d dVar) {
        j.e(dVar, "<this>");
        B h5 = g.h(dVar);
        if (h5 == null) {
            h5 = o0(dVar);
        }
        return g.W(h5);
    }

    public final P L(d dVar, int i2) {
        return g.p(dVar, i2);
    }

    public final boolean M(e eVar) {
        c cVar;
        j.e(eVar, "<this>");
        B h5 = g.h(eVar);
        if (h5 != null) {
            cVar = g.e(this, h5);
        } else {
            cVar = null;
        }
        if (cVar != null) {
            return true;
        }
        return false;
    }

    public final boolean N(c0 c0Var) {
        j.e(c0Var, "<this>");
        if (g.F(o0(c0Var)) != g.F(f(c0Var))) {
            return true;
        }
        return false;
    }

    public final boolean O(V v, g gVar) {
        return g.v(v, gVar);
    }

    public final G P(d dVar) {
        return g.i(dVar);
    }

    public final boolean Q(g gVar) {
        return g.A(gVar);
    }

    public final boolean R(g gVar) {
        return g.x(gVar);
    }

    public final int S(f fVar) {
        j.e(fVar, "<this>");
        if (fVar instanceof e) {
            return g.c((d) fVar);
        }
        if (fVar instanceof Kf.a) {
            return ((Kf.a) fVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + fVar + ArcCommonLog.TAG_COMMA + v.f4727a.b(fVar.getClass())).toString());
    }

    public final B T(C0764m mVar) {
        return g.Q(mVar);
    }

    public final boolean U(e eVar) {
        j.e(eVar, "<this>");
        if (!g.G(K(eVar)) || g.H(eVar)) {
            return false;
        }
        return true;
    }

    public final c0 V(P p6) {
        return g.r(p6);
    }

    public final boolean W(e eVar, e eVar2) {
        return g.w(eVar, eVar2);
    }

    public final d Y(d dVar) {
        return g.a0(this, dVar);
    }

    public final e Z(e eVar) {
        B Q;
        j.e(eVar, "<this>");
        C0764m f = g.f(eVar);
        if (f == null || (Q = g.Q(f)) == null) {
            return eVar;
        }
        return Q;
    }

    public final d a(d dVar) {
        B Z;
        j.e(dVar, "<this>");
        B h5 = g.h(dVar);
        if (h5 == null || (Z = g.Z(h5, true)) == null) {
            return dVar;
        }
        return Z;
    }

    public final B a0(C0768q qVar) {
        return g.N(qVar);
    }

    public final boolean b(g gVar, g gVar2) {
        return g.b(gVar, gVar2);
    }

    public final boolean c(d dVar) {
        j.e(dVar, "$receiver");
        return dVar instanceof C1084f;
    }

    public final int c0(d dVar) {
        return g.c(dVar);
    }

    public final void d(e eVar) {
        g.L(eVar);
    }

    public final b d0(c cVar) {
        return g.k(cVar);
    }

    public final c e(e eVar) {
        return g.e(this, eVar);
    }

    public final f e0(e eVar) {
        return g.d(eVar);
    }

    public final B f(d dVar) {
        B Y;
        j.e(dVar, "<this>");
        C0768q g = g.g(dVar);
        if (g != null && (Y = g.Y(g)) != null) {
            return Y;
        }
        B h5 = g.h(dVar);
        j.b(h5);
        return h5;
    }

    public final i f0(c cVar) {
        return g.X(cVar);
    }

    public final boolean g(g gVar) {
        return g.D(gVar);
    }

    public final B g0(d dVar) {
        return g.h(dVar);
    }

    public final boolean h(e eVar) {
        return g.F(eVar);
    }

    public final B i(e eVar, b bVar) {
        return g.j(eVar, bVar);
    }

    public final P i0(C1317b bVar) {
        return g.T(bVar);
    }

    public final i j(P p6) {
        return g.t(p6);
    }

    public final boolean j0(g gVar) {
        return g.z(gVar);
    }

    public final void k(e eVar) {
        g.M(eVar);
    }

    public final boolean k0(g gVar) {
        return g.E(gVar);
    }

    public final boolean l(g gVar) {
        return g.y(gVar);
    }

    public final Collection l0(g gVar) {
        return g.V(gVar);
    }

    public final C0764m m(e eVar) {
        return g.f(eVar);
    }

    public final Set m0(e eVar) {
        return g.S(this, eVar);
    }

    public final c0 n(d dVar) {
        return g.P(dVar);
    }

    public final boolean n0(P p6) {
        return g.K(p6);
    }

    public final B o0(d dVar) {
        B N6;
        j.e(dVar, "<this>");
        C0768q g = g.g(dVar);
        if (g != null && (N6 = g.N(g)) != null) {
            return N6;
        }
        B h5 = g.h(dVar);
        j.b(h5);
        return h5;
    }

    public final B p(C0768q qVar) {
        return g.Y(qVar);
    }

    public final boolean p0(c cVar) {
        return cVar instanceof C1316a;
    }

    public final M q0(e eVar) {
        return g.W(eVar);
    }

    public final c0 r(c cVar) {
        return g.O(cVar);
    }

    public final boolean r0(g gVar) {
        return g.G(gVar);
    }

    public final boolean s(d dVar) {
        j.e(dVar, "<this>");
        return !j.a(g.W(o0(dVar)), g.W(f(dVar)));
    }

    public final boolean t(e eVar) {
        j.e(eVar, "<this>");
        return g.y(g.W(eVar));
    }

    public final int t0(g gVar) {
        return g.R(gVar);
    }

    public final V u(g gVar, int i2) {
        return g.q(gVar, i2);
    }

    public final void v(d dVar) {
        j.e(dVar, "<this>");
        g.g(dVar);
    }

    public final c0 w(ArrayList arrayList) {
        B b;
        int size = arrayList.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types");
        } else if (size == 1) {
            return (c0) C1194l.a1(arrayList);
        } else {
            ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
            Iterator it = arrayList.iterator();
            boolean z = false;
            boolean z3 = false;
            while (it.hasNext()) {
                c0 c0Var = (c0) it.next();
                if (z || C0754c.k(c0Var)) {
                    z = true;
                } else {
                    z = false;
                }
                if (c0Var instanceof B) {
                    b = (B) c0Var;
                } else if (c0Var instanceof C0768q) {
                    b = ((C0768q) c0Var).e;
                    z3 = true;
                } else {
                    throw new RuntimeException();
                }
                arrayList2.add(b);
            }
            if (z) {
                return l.c(k.INTERSECTION_OF_ERROR_TYPES, arrayList.toString());
            }
            u uVar = u.f3471a;
            if (!z3) {
                return uVar.b(arrayList2);
            }
            ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList, 10));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList3.add(C0754c.E((c0) it2.next()));
            }
            return C0754c.f(uVar.b(arrayList2), uVar.b(arrayList3));
        }
    }

    public final c0 x(e eVar, e eVar2) {
        return g.m(this, eVar, eVar2);
    }

    public final boolean y(e eVar) {
        return g.B(eVar);
    }

    public final void s0(e eVar, g gVar) {
    }
}
