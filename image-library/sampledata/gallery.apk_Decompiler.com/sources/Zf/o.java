package Zf;

import Xf.r;
import Xf.u;
import Yf.h;
import me.x;
import qe.C1227c;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements h {
    public final u d;

    public o(r rVar) {
        this.d = rVar;
    }

    public final Object emit(Object obj, C1227c cVar) {
        Object i2 = this.d.i(obj, cVar);
        if (i2 == C1245a.COROUTINE_SUSPENDED) {
            return i2;
        }
        return x.f4917a;
    }
}
