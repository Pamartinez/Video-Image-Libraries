package Te;

import Ae.a;
import Af.o;
import D1.f;
import Gf.i;
import He.t;
import Qe.C0833x;
import Qe.H;
import java.util.ArrayList;
import java.util.List;
import ne.C1194l;
import ne.C1196n;
import qf.C1236c;

public final class v implements a {
    public final /* synthetic */ int d;
    public final w e;

    public /* synthetic */ v(w wVar, int i2) {
        this.d = i2;
        this.e = wVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                w wVar = this.e;
                z zVar = wVar.g;
                zVar.D0();
                return C0833x.i((C0851l) zVar.f3814o.getValue(), wVar.f3806h);
            case 1:
                w wVar2 = this.e;
                z zVar2 = wVar2.g;
                zVar2.D0();
                return Boolean.valueOf(C0833x.h((C0851l) zVar2.f3814o.getValue(), wVar2.f3806h));
            default:
                w wVar3 = this.e;
                i iVar = wVar3.f3808j;
                t[] tVarArr = w.l;
                boolean booleanValue = ((Boolean) f.y(iVar, tVarArr[1])).booleanValue();
                C1236c cVar = wVar3.f3806h;
                z zVar3 = wVar3.g;
                if (booleanValue) {
                    return o.b;
                }
                Iterable<H> iterable = (List) f.y(wVar3.f3807i, tVarArr[0]);
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (H A10 : iterable) {
                    arrayList.add(A10.A());
                }
                ArrayList Y02 = C1194l.Y0(arrayList, new L(zVar3, cVar));
                return f.m("package view scope for " + cVar + " in " + zVar3.getName(), Y02);
        }
    }
}
