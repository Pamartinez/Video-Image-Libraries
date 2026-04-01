package Qe;

import Ae.b;
import Sf.n;
import Te.B;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J implements K {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f3659a;

    public J(ArrayList arrayList) {
        this.f3659a = arrayList;
    }

    public final void a(C1236c cVar, ArrayList arrayList) {
        j.e(cVar, "fqName");
        for (Object next : this.f3659a) {
            if (j.a(((B) ((H) next)).f3743i, cVar)) {
                arrayList.add(next);
            }
        }
    }

    public final boolean b(C1236c cVar) {
        j.e(cVar, "fqName");
        ArrayList<H> arrayList = this.f3659a;
        if (arrayList.isEmpty()) {
            return true;
        }
        for (H h5 : arrayList) {
            if (j.a(((B) h5).f3743i, cVar)) {
                return false;
            }
        }
        return true;
    }

    public final Collection m(C1236c cVar, b bVar) {
        j.e(cVar, "fqName");
        return n.v0(n.p0(n.t0(C1194l.F0(this.f3659a), C0828s.f), new I(cVar, 0)));
    }
}
