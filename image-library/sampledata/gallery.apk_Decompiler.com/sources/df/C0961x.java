package df;

import Ae.a;
import Gf.h;
import Gf.m;
import Gf.p;
import Qe.O;
import We.u;
import cf.C0922a;
import kotlin.jvm.internal.j;

/* renamed from: df.x  reason: case insensitive filesystem */
public final class C0961x implements a {
    public final /* synthetic */ int d;
    public final C0932A e;
    public final u f;
    public final kotlin.jvm.internal.u g;

    public /* synthetic */ C0961x(C0932A a7, u uVar, kotlin.jvm.internal.u uVar2, int i2) {
        this.d = i2;
        this.e = a7;
        this.f = uVar;
        this.g = uVar2;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                C0932A a7 = this.e;
                p pVar = ((C0922a) a7.b.d).f4006a;
                C0961x xVar = new C0961x(a7, this.f, this.g, 1);
                m mVar = (m) pVar;
                mVar.getClass();
                return new h(mVar, xVar);
            default:
                ((C0922a) this.e.b.d).f4008h.getClass();
                j.e(this.f, "field");
                j.e((O) this.g.d, "descriptor");
                return null;
        }
    }
}
