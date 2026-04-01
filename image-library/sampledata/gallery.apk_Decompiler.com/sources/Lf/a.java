package Lf;

import Ae.b;
import Hf.c0;
import Qe.C0819i;
import Qe.U;
import Qe.V;
import kotlin.jvm.internal.j;

public final class a implements b {
    public static final a e = new a(0);
    public static final a f = new a(1);
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        boolean z;
        boolean z3;
        c0 c0Var = (c0) obj;
        switch (this.d) {
            case 0:
                j.e(c0Var, "it");
                C0819i g = c0Var.s0().g();
                if (g == null || !(g instanceof V) || !(((V) g).g() instanceof U)) {
                    z = false;
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                j.e(c0Var, "it");
                C0819i g3 = c0Var.s0().g();
                if (g3 == null || (!(g3 instanceof U) && !(g3 instanceof V))) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                return Boolean.valueOf(z3);
        }
    }
}
