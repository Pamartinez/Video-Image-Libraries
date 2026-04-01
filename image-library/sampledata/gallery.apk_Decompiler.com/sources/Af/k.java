package Af;

import Ae.a;
import ne.C1194l;

public final class k implements a {
    public final /* synthetic */ int d;
    public final a e;

    public /* synthetic */ k(a aVar, int i2) {
        this.d = i2;
        this.e = aVar;
    }

    public final Object invoke() {
        int i2 = this.d;
        a aVar = this.e;
        switch (i2) {
            case 0:
                p pVar = (p) aVar.invoke();
                if (pVar instanceof l) {
                    return ((l) pVar).h();
                }
                return pVar;
            default:
                return C1194l.p1((Iterable) aVar.invoke());
        }
    }
}
