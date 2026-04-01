package Df;

import Ae.b;
import Gd.a;
import Qe.C;
import Qe.C0819i;
import Qe.C0833x;
import Qe.U;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import lf.Q;
import nf.C1209f;
import qf.C1235b;

public final class D implements b {
    public final /* synthetic */ int d;
    public final H e;

    public /* synthetic */ D(H h5, int i2) {
        this.d = i2;
        this.e = h5;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                int intValue = ((Number) obj).intValue();
                n nVar = this.e.f3338a;
                l lVar = (l) nVar.f3358a;
                C1235b A10 = c.A((C1209f) nVar.b, intValue);
                if (A10.f5034c) {
                    return lVar.b(A10);
                }
                return C0833x.e(lVar.b, A10);
            case 1:
                int intValue2 = ((Number) obj).intValue();
                n nVar2 = this.e.f3338a;
                C1235b A11 = c.A((C1209f) nVar2.b, intValue2);
                if (!A11.f5034c) {
                    C c5 = ((l) nVar2.f3358a).b;
                    j.e(c5, "<this>");
                    C0819i e7 = C0833x.e(c5, A11);
                    if (e7 instanceof U) {
                        return (U) e7;
                    }
                }
                return null;
            default:
                Q q = (Q) obj;
                j.e(q, "it");
                return a.T(q, (B1.b) this.e.f3338a.d);
        }
    }
}
