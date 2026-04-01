package Ke;

import Ae.a;
import Re.g;
import Te.I;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import tf.C1312p;

public final class j0 implements a {
    public final /* synthetic */ int d;
    public final k0 e;

    public /* synthetic */ j0(k0 k0Var, int i2) {
        this.d = i2;
        this.e = k0Var;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                k0 k0Var = this.e;
                I getter = k0Var.s().j().getGetter();
                if (getter == null) {
                    return C1312p.f(k0Var.s().j(), g.f3692a);
                }
                return getter;
            default:
                return c.e(this.e, true);
        }
    }
}
