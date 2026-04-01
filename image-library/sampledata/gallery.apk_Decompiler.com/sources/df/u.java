package Df;

import Ae.a;
import Qe.C0822l;
import java.util.List;
import lf.G;
import ne.C1194l;
import ne.C1202t;

public final class u implements a {
    public final w d;
    public final boolean e;
    public final G f;

    public u(w wVar, boolean z, G g) {
        this.d = wVar;
        this.e = z;
        this.f = g;
    }

    public final Object invoke() {
        List list;
        w wVar = this.d;
        n nVar = wVar.f3367a;
        l lVar = (l) nVar.f3358a;
        z a7 = wVar.a((C0822l) nVar.f3359c);
        if (a7 != null) {
            boolean z = this.e;
            G g = this.f;
            if (z) {
                list = C1194l.k1(lVar.e.l(a7, g));
            } else {
                list = C1194l.k1(lVar.e.m(a7, g));
            }
        } else {
            list = null;
        }
        if (list == null) {
            return C1202t.d;
        }
        return list;
    }
}
