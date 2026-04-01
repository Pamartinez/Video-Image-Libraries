package Ne;

import Gf.b;
import Gf.m;
import Hf.C0761j;
import Hf.d0;
import Jf.l;
import Pe.o;
import Qe.C0817g;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0831v;
import Te.A;
import Te.C0848i;
import Te.O;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o1.C0246a;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    public static final A f3581a;

    static {
        l lVar = l.f3482a;
        o oVar = new o(l.b, q.f, 1);
        C0817g gVar = C0817g.INTERFACE;
        C1240g f = q.g.f();
        b bVar = m.e;
        A a7 = new A(oVar, gVar, f, bVar);
        Qe.A a10 = Qe.A.ABSTRACT;
        if (a10 != null) {
            a7.k = a10;
            C0826p pVar = C0827q.e;
            if (pVar != null) {
                a7.l = pVar;
                List e02 = C0246a.e0(O.H0(a7, d0.IN_VARIANCE, C1240g.e("T"), 0, bVar));
                if (a7.n == null) {
                    ArrayList arrayList = new ArrayList(e02);
                    a7.n = arrayList;
                    a7.m = new C0761j(a7, arrayList, a7.f3741o, a7.f3742p);
                    Set<C0831v> set = Collections.EMPTY_SET;
                    if (set != null) {
                        for (C0831v vVar : set) {
                            ((C0848i) vVar).k = a7.i();
                        }
                        f3581a = a7;
                        return;
                    }
                    A.L(13);
                    throw null;
                }
                throw new IllegalStateException("Type parameters are already set for " + a7.getName());
            }
            A.L(9);
            throw null;
        }
        A.L(6);
        throw null;
    }
}
