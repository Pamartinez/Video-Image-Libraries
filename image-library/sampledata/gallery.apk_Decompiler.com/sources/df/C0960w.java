package df;

import Ae.a;
import Af.b;
import Af.f;
import Af.m;
import Af.p;
import Qf.k;
import Ye.c;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1240g;

/* renamed from: df.w  reason: case insensitive filesystem */
public final class C0960w implements a {
    public final /* synthetic */ int d;
    public final C0932A e;

    public /* synthetic */ C0960w(C0932A a7, int i2) {
        this.d = i2;
        this.e = a7;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                f fVar = f.m;
                p.f3319a.getClass();
                m mVar = m.e;
                j.e(fVar, "kindFilter");
                List list = fVar.f3313a;
                c cVar = c.WHEN_GET_ALL_DESCRIPTORS;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                boolean a7 = fVar.a(f.l);
                C0932A a10 = this.e;
                if (a7) {
                    for (C1240g gVar : a10.h(fVar, mVar)) {
                        mVar.invoke(gVar);
                        k.a(linkedHashSet, a10.c(gVar, cVar));
                    }
                }
                if (fVar.a(f.f3309i) && !list.contains(b.f3304a)) {
                    for (C1240g gVar2 : a10.i(fVar, mVar)) {
                        mVar.invoke(gVar2);
                        linkedHashSet.addAll(a10.a(gVar2, cVar));
                    }
                }
                if (fVar.a(f.f3310j) && !list.contains(b.f3304a)) {
                    for (C1240g gVar3 : a10.o(fVar)) {
                        mVar.invoke(gVar3);
                        linkedHashSet.addAll(a10.f(gVar3, cVar));
                    }
                }
                return C1194l.k1(linkedHashSet);
            case 1:
                return this.e.k();
            case 2:
                return this.e.i(f.f3312p, (m) null);
            case 3:
                return this.e.o(f.q);
            default:
                return this.e.h(f.f3311o, (Ae.b) null);
        }
    }
}
