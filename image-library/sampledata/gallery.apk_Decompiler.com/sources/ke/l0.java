package Ke;

import Ae.a;
import Re.g;
import Te.J;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import tf.C1312p;

public final class l0 implements a {
    public final /* synthetic */ int d;
    public final m0 e;

    public /* synthetic */ l0(m0 m0Var, int i2) {
        this.d = i2;
        this.e = m0Var;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                m0 m0Var = this.e;
                J setter = m0Var.s().j().getSetter();
                if (setter == null) {
                    return C1312p.g(m0Var.s().j(), g.f3692a);
                }
                return setter;
            default:
                return c.e(this.e, false);
        }
    }
}
