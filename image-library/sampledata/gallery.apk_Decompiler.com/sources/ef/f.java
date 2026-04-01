package ef;

import D0.e;
import Hf.C0774x;
import Hf.G;
import Hf.P;
import Hf.a0;
import Hf.d0;
import Qe.V;
import java.util.List;
import kotlin.jvm.internal.j;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {
    public static P a(V v, C0993a aVar, e eVar, C0774x xVar) {
        j.e(aVar, "typeAttr");
        j.e(eVar, "typeParameterUpperBoundEraser");
        if (!aVar.f4318c) {
            aVar = aVar.b(C0994b.INFLEXIBLE);
        }
        int i2 = C0997e.f4321a[aVar.b.ordinal()];
        if (i2 == 1) {
            return new G(xVar, d0.INVARIANT);
        }
        if (i2 != 2 && i2 != 3) {
            throw new RuntimeException();
        } else if (!v.t().a()) {
            return new G(C1353d.e(v).n(), d0.INVARIANT);
        } else {
            List parameters = xVar.s0().getParameters();
            j.d(parameters, "getParameters(...)");
            if (!parameters.isEmpty()) {
                return new G(xVar, d0.OUT_VARIANCE);
            }
            return a0.k(v, aVar);
        }
    }
}
