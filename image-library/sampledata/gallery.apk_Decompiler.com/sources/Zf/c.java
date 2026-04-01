package Zf;

import Vf.C0884v;
import Vf.D;
import Xf.a;
import Xf.r;
import Yf.h;
import Yf.n;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1228d;
import qe.C1232h;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends b {
    public final n d;

    public c(n nVar, C1232h hVar, int i2, a aVar) {
        super(hVar, i2, aVar);
        this.d = nVar;
    }

    public final Object a(r rVar, C1227c cVar) {
        Object collect = this.d.collect(new o(rVar), cVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        x xVar = x.f4917a;
        if (collect != aVar) {
            collect = xVar;
        }
        if (collect == aVar) {
            return collect;
        }
        return xVar;
    }

    public final b b(C1232h hVar, int i2, a aVar) {
        return new c(this.d, hVar, i2, aVar);
    }

    public final Object collect(h hVar, C1227c cVar) {
        C1232h hVar2;
        int i2 = this.b;
        x xVar = x.f4917a;
        if (i2 == -3) {
            C1232h context = cVar.getContext();
            Boolean bool = Boolean.FALSE;
            C0884v vVar = new C0884v(0);
            C1232h hVar3 = this.f3978a;
            if (!((Boolean) hVar3.fold(bool, vVar)).booleanValue()) {
                hVar2 = context.plus(hVar3);
            } else {
                hVar2 = D.g(context, hVar3, false);
            }
            if (j.a(hVar2, context)) {
                Object collect = this.d.collect(hVar, cVar);
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                if (collect != aVar) {
                    collect = xVar;
                }
                if (collect == aVar) {
                    return collect;
                }
            } else {
                C1228d dVar = C1228d.d;
                if (j.a(hVar2.get(dVar), context.get(dVar))) {
                    C1232h context2 = cVar.getContext();
                    if (!(hVar instanceof o) && !(hVar instanceof j)) {
                        hVar = new q(hVar, context2);
                    }
                    Object a7 = k.a(hVar2, hVar, cg.a.k(hVar2), new a(this, (C1227c) null, 1), cVar);
                    if (a7 == C1245a.COROUTINE_SUSPENDED) {
                        return a7;
                    }
                }
            }
            return xVar;
        }
        Object collect2 = super.collect(hVar, cVar);
        if (collect2 == C1245a.COROUTINE_SUSPENDED) {
            return collect2;
        }
        return xVar;
    }

    public final String toString() {
        return this.d + " -> " + super.toString();
    }
}
