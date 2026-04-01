package Ke;

import Ae.a;
import Qe.C0814d;
import Qe.M;
import kotlin.jvm.internal.j;

public final class r implements a {
    public final C0814d d;
    public final int e;

    public r(C0814d dVar, int i2) {
        this.d = dVar;
        this.e = i2;
    }

    public final Object invoke() {
        Object obj = this.d.B().get(this.e);
        j.d(obj, "get(...)");
        return (M) obj;
    }
}
