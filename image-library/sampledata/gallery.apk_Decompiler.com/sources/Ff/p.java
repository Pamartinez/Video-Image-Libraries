package Ff;

import Ae.a;
import ne.C1182C;

public final class p implements a {
    public final /* synthetic */ int d;
    public final r e;
    public final s f;

    public /* synthetic */ p(r rVar, s sVar, int i2) {
        this.d = i2;
        this.e = rVar;
        this.f = sVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return C1182C.b0(this.e.f3393a.keySet(), this.f.o());
            default:
                return C1182C.b0(this.e.b.keySet(), this.f.p());
        }
    }
}
