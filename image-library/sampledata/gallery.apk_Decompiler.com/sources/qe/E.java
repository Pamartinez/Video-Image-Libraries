package Qe;

import Ae.b;
import D0.f;
import Gf.e;
import Gf.m;
import Pe.o;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

public final class E implements b {
    public final /* synthetic */ int d;
    public final f e;

    public /* synthetic */ E(f fVar, int i2) {
        this.d = i2;
        this.e = fVar;
    }

    public final Object invoke(Object obj) {
        C0818h hVar;
        int i2;
        switch (this.d) {
            case 0:
                C1236c cVar = (C1236c) obj;
                j.e(cVar, "fqName");
                return new o((C) this.e.f, cVar, 1);
            default:
                F f = (F) obj;
                j.e(f, "<destruct>");
                C1235b bVar = f.f3657a;
                List list = f.b;
                if (!bVar.f5034c) {
                    C1235b e7 = bVar.e();
                    f fVar = this.e;
                    if (e7 != null) {
                        hVar = fVar.E(e7, C1194l.I0(list));
                    } else {
                        hVar = (C0818h) ((e) fVar.g).invoke(bVar.f5033a);
                    }
                    C0818h hVar2 = hVar;
                    boolean z = !bVar.b.e().d();
                    m mVar = (m) fVar.e;
                    C1240g f5 = bVar.f();
                    Integer num = (Integer) C1194l.N0(list);
                    if (num != null) {
                        i2 = num.intValue();
                    } else {
                        i2 = 0;
                    }
                    return new G(mVar, hVar2, f5, z, i2);
                }
                throw new UnsupportedOperationException("Unresolved local class: " + bVar);
        }
    }
}
