package Ne;

import Ae.b;
import Af.l;
import Hf.d0;
import Qe.C;
import Qe.C0816f;
import Qe.C0819i;
import Te.w;
import Te.z;
import Ye.c;
import kotlin.jvm.internal.j;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements b {
    public final /* synthetic */ int d;
    public final i e;

    public /* synthetic */ g(i iVar, int i2) {
        this.d = i2;
        this.e = iVar;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                C1240g gVar = (C1240g) obj;
                z k = this.e.k();
                C1236c cVar = q.l;
                l lVar = ((w) k.n0(cVar)).k;
                if (lVar != null) {
                    C0819i c5 = lVar.c(gVar, c.FROM_BUILTINS);
                    if (c5 == null) {
                        throw new AssertionError("Built-in class " + cVar.c(gVar) + " is not found");
                    } else if (c5 instanceof C0816f) {
                        return (C0816f) c5;
                    } else {
                        throw new AssertionError("Must be a class descriptor " + gVar + ", but was " + c5);
                    }
                } else {
                    i.a(11);
                    throw null;
                }
            default:
                C c6 = (C) obj;
                j.e(c6, "module");
                return c6.f().h(d0.INVARIANT, this.e.u());
        }
    }
}
