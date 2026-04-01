package Ke;

import Ae.a;
import We.C0892d;
import java.lang.reflect.Type;
import kotlin.jvm.internal.j;

public final class o0 implements a {
    public final /* synthetic */ int d;
    public final r0 e;

    public /* synthetic */ o0(r0 r0Var, int i2) {
        this.d = i2;
        this.e = r0Var;
    }

    public final Object invoke() {
        Type type;
        switch (this.d) {
            case 0:
                r0 r0Var = this.e;
                return r0Var.b(r0Var.d);
            default:
                x0 x0Var = this.e.e;
                if (x0Var != null) {
                    type = (Type) x0Var.invoke();
                } else {
                    type = null;
                }
                j.b(type);
                return C0892d.c(type);
        }
    }
}
