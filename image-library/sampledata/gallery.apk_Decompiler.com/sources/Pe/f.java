package Pe;

import Ae.b;
import Ef.d;
import Qe.C;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0822l;
import Te.w;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import tf.C1301e;

public final class f implements b {
    public static final f e = new f();
    public final /* synthetic */ int d = 0;

    public /* synthetic */ f() {
    }

    public final Object invoke(Object obj) {
        boolean z = false;
        switch (this.d) {
            case 0:
                C c5 = (C) obj;
                e eVar = g.d;
                j.e(c5, "module");
                ArrayList arrayList = new ArrayList();
                for (Object next : (List) D1.f.y(((w) c5.n0(g.f)).f3807i, w.l[0])) {
                    if (next instanceof d) {
                        arrayList.add(next);
                    }
                }
                return (d) C1194l.L0(arrayList);
            default:
                C0814d dVar = (C0814d) obj;
                if (dVar.b() == C0813c.DECLARATION) {
                    C0822l g = dVar.g();
                    j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    String str = d.f3633a;
                    if (d.f3637j.containsKey(C1301e.g((C0816f) g))) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
        }
    }

    public f(q qVar) {
    }
}
