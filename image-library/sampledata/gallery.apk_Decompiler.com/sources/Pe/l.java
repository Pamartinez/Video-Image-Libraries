package Pe;

import Ae.a;
import Re.e;
import Re.g;
import Re.i;
import java.util.List;
import o1.C0246a;

public final class l implements a {
    public final /* synthetic */ int d;
    public final q e;

    public /* synthetic */ l(q qVar, int i2) {
        this.d = i2;
        this.e = qVar;
    }

    public final Object invoke() {
        int i2 = this.d;
        q qVar = this.e;
        switch (i2) {
            case 0:
                List e02 = C0246a.e0(e.a(qVar.f3647a.f3811h, "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", "", "WARNING"));
                if (e02.isEmpty()) {
                    return g.f3692a;
                }
                return new i(0, e02);
            default:
                return qVar.f3647a.f3811h.e();
        }
    }
}
