package tf;

import Hf.M;
import If.c;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0822l;
import Qe.C0834y;
import Qe.H;
import Qe.Q;
import Qe.V;
import Te.B;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1194l;

/* renamed from: tf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1299c implements c {
    public static final C1299c d = new Object();

    public static /* synthetic */ void b(int i2) {
        Object[] objArr = new Object[3];
        if (i2 != 1) {
            objArr[0] = "a";
        } else {
            objArr[0] = "b";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
        objArr[2] = "equals";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static Q e(C0812b bVar) {
        while (bVar instanceof C0814d) {
            C0814d dVar = (C0814d) bVar;
            if (dVar.b() != C0813c.FAKE_OVERRIDE) {
                break;
            }
            Collection h5 = dVar.h();
            j.d(h5, "getOverriddenDescriptors(...)");
            bVar = (C0814d) C1194l.c1(h5);
            if (bVar == null) {
                return null;
            }
        }
        return bVar.getSource();
    }

    public boolean a(M m, M m4) {
        if (m == null) {
            b(0);
            throw null;
        } else if (m4 != null) {
            return m.equals(m4);
        } else {
            b(1);
            throw null;
        }
    }

    /* JADX WARNING: type inference failed for: r5v7, types: [If.c, java.lang.Object, Kd.a] */
    public boolean c(C0822l lVar, C0822l lVar2, boolean z) {
        boolean z3;
        if ((lVar instanceof C0816f) && (lVar2 instanceof C0816f)) {
            return j.a(((C0816f) lVar).q(), ((C0816f) lVar2).q());
        }
        if ((lVar instanceof V) && (lVar2 instanceof V)) {
            return d((V) lVar, (V) lVar2, z, C1297a.d);
        }
        if ((lVar instanceof C0812b) && (lVar2 instanceof C0812b)) {
            C0812b bVar = (C0812b) lVar;
            C0812b bVar2 = (C0812b) lVar2;
            if (!bVar.equals(bVar2)) {
                if (j.a(bVar.getName(), bVar2.getName()) && ((!(bVar instanceof C0834y) || !(bVar2 instanceof C0834y) || ((C0834y) bVar).b0() == ((C0834y) bVar2).b0()) && ((!j.a(bVar.g(), bVar2.g()) || (z && j.a(e(bVar), e(bVar2)))) && !C1301e.o(bVar) && !C1301e.o(bVar2)))) {
                    C0822l g = bVar.g();
                    C0822l g3 = bVar2.g();
                    if ((g instanceof C0814d) || (g3 instanceof C0814d)) {
                        z3 = false;
                    } else {
                        z3 = c(g, g3, z);
                    }
                    if (z3) {
                        ? obj = new Object();
                        obj.d = z;
                        obj.e = bVar;
                        obj.f = bVar2;
                        C1311o oVar = new C1311o(obj);
                        C1309m b = oVar.m(bVar, bVar2, (C0816f) null, true).b();
                        C1309m mVar = C1309m.OVERRIDABLE;
                        if (!(b == mVar && oVar.m(bVar2, bVar, (C0816f) null, true).b() == mVar)) {
                            return false;
                        }
                    }
                }
                return false;
            }
            return true;
        } else if (!(lVar instanceof H) || !(lVar2 instanceof H)) {
            return j.a(lVar, lVar2);
        } else {
            return j.a(((B) ((H) lVar)).f3743i, ((B) ((H) lVar2)).f3743i);
        }
    }

    public boolean d(V v, V v6, boolean z, Ae.c cVar) {
        boolean z3;
        j.e(v, "a");
        j.e(v6, "b");
        if (v.equals(v6)) {
            return true;
        }
        if (j.a(v.g(), v6.g())) {
            return false;
        }
        C0822l g = v.g();
        C0822l g3 = v6.g();
        if ((g instanceof C0814d) || (g3 instanceof C0814d)) {
            z3 = ((Boolean) cVar.invoke(g, g3)).booleanValue();
        } else {
            z3 = c(g, g3, z);
        }
        if (z3 && v.getIndex() == v6.getIndex()) {
            return true;
        }
        return false;
    }
}
