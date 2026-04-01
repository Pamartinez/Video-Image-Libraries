package Te;

import Ae.b;
import Af.c;
import Af.f;
import Af.q;
import Qe.C;
import Qf.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1202t;
import ne.v;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L extends q {
    public final C b;

    /* renamed from: c  reason: collision with root package name */
    public final C1236c f3762c;

    public L(C c5, C1236c cVar) {
        j.e(c5, "moduleDescriptor");
        j.e(cVar, "fqName");
        this.b = c5;
        this.f3762c = cVar;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        if (fVar.a(f.f3308h)) {
            C1236c cVar = this.f3762c;
            if (!cVar.d() || !fVar.f3313a.contains(c.f3305a)) {
                C c5 = this.b;
                Collection<C1236c> m = c5.m(cVar, bVar);
                ArrayList arrayList = new ArrayList(m.size());
                for (C1236c f : m) {
                    C1240g f5 = f.f();
                    j.d(f5, "shortName(...)");
                    if (((Boolean) bVar.invoke(f5)).booleanValue()) {
                        w wVar = null;
                        if (!f5.e) {
                            w wVar2 = (w) c5.n0(cVar.c(f5));
                            if (!((Boolean) D1.f.y(wVar2.f3808j, w.l[1])).booleanValue()) {
                                wVar = wVar2;
                            }
                        }
                        k.a(arrayList, wVar);
                    }
                }
                return arrayList;
            }
        }
        return C1202t.d;
    }

    public final Set e() {
        return v.d;
    }

    public final String toString() {
        return "subpackages of " + this.f3762c + " from " + this.b;
    }
}
