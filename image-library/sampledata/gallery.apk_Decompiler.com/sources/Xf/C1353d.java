package xf;

import Ae.b;
import If.g;
import Ne.i;
import Pe.p;
import Qe.C;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0820j;
import Qe.C0822l;
import Qe.C0823m;
import Qe.H;
import Qe.N;
import Qe.O;
import Qf.k;
import Sf.h;
import Sf.n;
import Sf.s;
import Te.B;
import Te.F;
import Te.Q;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import tf.C1301e;

/* renamed from: xf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1353d {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f5167a = 0;

    static {
        C1240g.e("value");
    }

    public static final boolean a(Q q) {
        Boolean h5 = k.h(C0246a.e0(q), C1350a.e, C1352c.d);
        j.d(h5, "ifAny(...)");
        return h5.booleanValue();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [kotlin.jvm.internal.u, java.lang.Object] */
    public static C0814d b(C0814d dVar, b bVar) {
        j.e(dVar, "<this>");
        return (C0814d) k.f(C0246a.e0(dVar), new C1350a(1), new p(new Object(), bVar));
    }

    public static final C1236c c(C0823m mVar) {
        j.e(mVar, "<this>");
        C1238e h5 = h(mVar);
        if (!h5.d()) {
            h5 = null;
        }
        if (h5 != null) {
            return h5.g();
        }
        return null;
    }

    public static final C0816f d(Re.b bVar) {
        j.e(bVar, "<this>");
        C0819i g = bVar.getType().s0().g();
        if (g instanceof C0816f) {
            return (C0816f) g;
        }
        return null;
    }

    public static final i e(C0822l lVar) {
        j.e(lVar, "<this>");
        return j(lVar).f();
    }

    public static final C1235b f(C0819i iVar) {
        C0822l g;
        C1235b f;
        if (iVar == null || (g = iVar.g()) == null) {
            return null;
        }
        if (g instanceof H) {
            C1236c cVar = ((B) ((H) g)).f3743i;
            C1240g name = iVar.getName();
            j.d(name, "getName(...)");
            return new C1235b(cVar, name);
        } else if (!(g instanceof C0820j) || (f = f((C0819i) g)) == null) {
            return null;
        } else {
            C1240g name2 = iVar.getName();
            j.d(name2, "getName(...)");
            return f.d(name2);
        }
    }

    public static final C1236c g(C0822l lVar) {
        j.e(lVar, "<this>");
        C1236c h5 = C1301e.h(lVar);
        if (h5 != null) {
            return h5;
        }
        return C1301e.g(lVar.g()).b(lVar.getName()).g();
    }

    public static final C1238e h(C0822l lVar) {
        j.e(lVar, "<this>");
        C1238e g = C1301e.g(lVar);
        j.d(g, "getFqName(...)");
        return g;
    }

    public static final void i(C c5) {
        j.e(c5, "<this>");
        if (c5.x(g.f3462a) != null) {
            throw new ClassCastException();
        }
    }

    public static final C j(C0822l lVar) {
        j.e(lVar, "<this>");
        C d = C1301e.d(lVar);
        j.d(d, "getContainingModule(...)");
        return d;
    }

    public static final C0814d k(C0814d dVar) {
        j.e(dVar, "<this>");
        if (!(dVar instanceof N)) {
            return dVar;
        }
        O E02 = ((F) ((N) dVar)).E0();
        j.d(E02, "getCorrespondingProperty(...)");
        return E02;
    }

    public static final h l(C0814d dVar) {
        j.e(dVar, "<this>");
        Sf.k b0 = C1192j.b0(new C0814d[]{dVar});
        Collection h5 = dVar.h();
        j.d(h5, "getOverriddenDescriptors(...)");
        return n.r0(C1192j.b0(new Sf.k[]{b0, new h(C1194l.F0(h5), new C1351b(1), s.d)}));
    }
}
