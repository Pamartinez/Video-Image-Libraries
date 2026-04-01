package Re;

import Qe.I;
import Sf.f;
import Sf.h;
import Sf.n;
import Sf.s;
import a.C0068a;
import hf.C1080b;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.C1201s;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements h {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ i(int i2, List list) {
        this.d = i2;
        this.e = list;
    }

    public final boolean g(C1236c cVar) {
        switch (this.d) {
            case 0:
                return C0068a.I(this, cVar);
            case 1:
                j.e(cVar, "fqName");
                for (h g : (Iterable) C1194l.F0((List) this.e).b) {
                    if (g.g(cVar)) {
                        return true;
                    }
                }
                return false;
            default:
                return C0068a.I(this, cVar);
        }
    }

    public final boolean isEmpty() {
        switch (this.d) {
            case 0:
                return ((List) this.e).isEmpty();
            case 1:
                Iterable<h> iterable = (List) this.e;
                if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                    return true;
                }
                for (h isEmpty : iterable) {
                    if (!isEmpty.isEmpty()) {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                return ((List) this.e).iterator();
            case 1:
                return new f(new h(C1194l.F0((List) this.e), k.d, s.d));
            default:
                return C1201s.d;
        }
    }

    public final b m(C1236c cVar) {
        switch (this.d) {
            case 0:
                return C0068a.v(this, cVar);
            case 1:
                j.e(cVar, "fqName");
                return (b) n.q0(n.u0(C1194l.F0((List) this.e), new I(cVar, 1)));
            default:
                j.e(cVar, "fqName");
                if (cVar.equals((C1236c) this.e)) {
                    return C1080b.f4585a;
                }
                return null;
        }
    }

    public String toString() {
        switch (this.d) {
            case 0:
                return ((List) this.e).toString();
            default:
                return super.toString();
        }
    }

    public i(h[] hVarArr) {
        this.d = 1;
        this.e = C1192j.x0(hVarArr);
    }

    public i(C1236c cVar) {
        this.d = 2;
        j.e(cVar, "fqNameToMatch");
        this.e = cVar;
    }
}
