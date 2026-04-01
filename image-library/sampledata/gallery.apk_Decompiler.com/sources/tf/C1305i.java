package tf;

import Hf.B;
import Hf.C0774x;
import Hf.a0;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0832w;
import Qe.D;
import Qe.O;
import Qe.W;
import Qe.Y;
import Te.I;
import kotlin.jvm.internal.j;
import qf.C1236c;
import qf.C1240g;
import xf.C1353d;

/* renamed from: tf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1305i {
    static {
        C1236c cVar = new C1236c("kotlin.jvm.JvmInline");
        cVar.e();
        C1240g f = cVar.f();
        j.d(f, "shortName(...)");
        C1236c.j(f).d();
    }

    public static final boolean a(C0814d dVar) {
        C0816f fVar;
        W N6;
        j.e(dVar, "<this>");
        if (!(dVar instanceof I)) {
            return false;
        }
        O E02 = ((I) dVar).E0();
        j.d(E02, "getCorrespondingProperty(...)");
        if (E02.H() != null) {
            return false;
        }
        C0822l g = E02.g();
        if (g instanceof C0816f) {
            fVar = (C0816f) g;
        } else {
            fVar = null;
        }
        if (fVar == null || (N6 = fVar.N()) == null) {
            return false;
        }
        C1240g name = E02.getName();
        j.d(name, "getName(...)");
        if (N6.a(name)) {
            return true;
        }
        return false;
    }

    public static final boolean b(C0822l lVar) {
        j.e(lVar, "<this>");
        if (!(lVar instanceof C0816f) || !(((C0816f) lVar).N() instanceof C0832w)) {
            return false;
        }
        return true;
    }

    public static final boolean c(C0774x xVar) {
        j.e(xVar, "<this>");
        C0819i g = xVar.s0().g();
        if (g != null) {
            return b(g);
        }
        return false;
    }

    public static final boolean d(C0822l lVar) {
        j.e(lVar, "<this>");
        if (!(lVar instanceof C0816f) || !(((C0816f) lVar).N() instanceof D)) {
            return false;
        }
        return true;
    }

    public static final boolean e(Y y) {
        C0816f fVar;
        C0832w wVar;
        if (y.H() != null) {
            return false;
        }
        C0822l g = y.g();
        C1240g gVar = null;
        if (g instanceof C0816f) {
            fVar = (C0816f) g;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            int i2 = C1353d.f5167a;
            W N6 = fVar.N();
            if (N6 instanceof C0832w) {
                wVar = (C0832w) N6;
            } else {
                wVar = null;
            }
            if (wVar != null) {
                gVar = wVar.f3683a;
            }
        }
        if (j.a(gVar, y.getName())) {
            return true;
        }
        return false;
    }

    public static final boolean f(C0822l lVar) {
        j.e(lVar, "<this>");
        if (b(lVar) || d(lVar)) {
            return true;
        }
        return false;
    }

    public static final boolean g(C0774x xVar) {
        C0819i g = xVar.s0().g();
        if (g != null) {
            return f(g);
        }
        return false;
    }

    public static final boolean h(C0774x xVar) {
        j.e(xVar, "<this>");
        C0819i g = xVar.s0().g();
        if (g == null || !d(g) || a0.e(xVar)) {
            return false;
        }
        return true;
    }

    public static final B i(C0774x xVar) {
        C0816f fVar;
        C0832w wVar;
        j.e(xVar, "<this>");
        C0819i g = xVar.s0().g();
        if (g instanceof C0816f) {
            fVar = (C0816f) g;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            int i2 = C1353d.f5167a;
            W N6 = fVar.N();
            if (N6 instanceof C0832w) {
                wVar = (C0832w) N6;
            } else {
                wVar = null;
            }
            if (wVar != null) {
                return (B) wVar.b;
            }
        }
        return null;
    }
}
