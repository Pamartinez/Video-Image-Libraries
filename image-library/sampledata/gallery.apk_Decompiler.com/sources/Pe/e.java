package Pe;

import He.t;
import Ne.i;
import Qe.C0814d;
import Qe.C0816f;
import Qf.a;
import kotlin.jvm.internal.j;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import tf.C1301e;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements a {
    public static final e d = new Object();

    public static C0816f b(C0816f fVar) {
        C1238e g = C1301e.g(fVar);
        String str = d.f3633a;
        C1236c cVar = (C1236c) d.k.get(g);
        if (cVar != null) {
            C0816f i2 = C1353d.e(fVar).i(cVar);
            j.d(i2, "getBuiltInClassByFqName(...)");
            return i2;
        }
        throw new IllegalArgumentException("Given class " + fVar + " is not a read-only collection");
    }

    public static C0816f c(C1236c cVar, i iVar) {
        j.e(iVar, "builtIns");
        String str = d.f3633a;
        C1235b bVar = (C1235b) d.f3635h.get(cVar.i());
        if (bVar != null) {
            return iVar.i(bVar.a());
        }
        return null;
    }

    public Iterable a(Object obj) {
        t[] tVarArr = q.f3646h;
        return ((C0814d) obj).a().h();
    }
}
