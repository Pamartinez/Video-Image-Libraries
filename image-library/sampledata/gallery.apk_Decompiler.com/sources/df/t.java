package Df;

import Ae.a;
import Qe.C0822l;
import java.util.List;
import ne.C1194l;
import ne.C1202t;
import rf.C1252b;

public final class t implements a {
    public final /* synthetic */ int d;
    public final w e;
    public final C1252b f;
    public final C0737c g;

    public /* synthetic */ t(w wVar, C1252b bVar, C0737c cVar, int i2) {
        this.d = i2;
        this.e = wVar;
        this.f = bVar;
        this.g = cVar;
    }

    public final Object invoke() {
        List list;
        List list2;
        switch (this.d) {
            case 0:
                w wVar = this.e;
                n nVar = wVar.f3367a;
                z a7 = wVar.a((C0822l) nVar.f3359c);
                if (a7 != null) {
                    list = C1194l.k1(((l) nVar.f3358a).e.i(a7, this.f, this.g));
                } else {
                    list = null;
                }
                if (list == null) {
                    return C1202t.d;
                }
                return list;
            default:
                w wVar2 = this.e;
                n nVar2 = wVar2.f3367a;
                z a10 = wVar2.a((C0822l) nVar2.f3359c);
                if (a10 != null) {
                    list2 = ((l) nVar2.f3358a).e.c(a10, this.f, this.g);
                } else {
                    list2 = null;
                }
                if (list2 == null) {
                    return C1202t.d;
                }
                return list2;
        }
    }
}
