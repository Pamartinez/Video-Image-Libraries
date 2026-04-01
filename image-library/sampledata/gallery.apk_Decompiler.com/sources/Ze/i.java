package Ze;

import Hf.C0774x;
import Hf.X;
import Qe.C0812b;
import Qe.C0816f;
import Sf.f;
import Sf.h;
import Sf.k;
import Sf.n;
import Sf.u;
import Te.K;
import bf.g;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.C1195m;
import tf.C1302f;
import tf.C1303g;
import tf.C1304h;
import tf.C1309m;
import tf.C1310n;
import tf.C1311o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements C1304h {
    public final C1303g a(C0812b bVar, C0812b bVar2, C0816f fVar) {
        C1309m mVar;
        j.e(bVar, "superDescriptor");
        j.e(bVar2, "subDescriptor");
        if (bVar2 instanceof g) {
            g gVar = (g) bVar2;
            if (gVar.getTypeParameters().isEmpty()) {
                C1310n i2 = C1311o.i(bVar, bVar2);
                C0774x xVar = null;
                if (i2 != null) {
                    mVar = i2.b();
                } else {
                    mVar = null;
                }
                if (mVar != null) {
                    return C1303g.UNKNOWN;
                }
                List B = gVar.B();
                j.d(B, "getValueParameters(...)");
                u t02 = n.t0(C1194l.F0(B), C0897d.f3939h);
                C0774x xVar2 = gVar.k;
                j.b(xVar2);
                h r0 = n.r0(C1192j.b0(new k[]{t02, C1192j.b0(new Object[]{xVar2})}));
                Te.u uVar = gVar.m;
                if (uVar != null) {
                    xVar = uVar.getType();
                }
                f fVar2 = new f(n.r0(C1192j.b0(new k[]{r0, C1194l.F0(C1195m.r0(xVar))})));
                while (fVar2.hasNext()) {
                    C0774x xVar3 = (C0774x) fVar2.next();
                    if (!xVar3.e0().isEmpty() && !(xVar3.x0() instanceof ef.i)) {
                        return C1303g.UNKNOWN;
                    }
                }
                C0812b bVar3 = (C0812b) bVar.c(new X(new ef.g()));
                if (bVar3 == null) {
                    return C1303g.UNKNOWN;
                }
                if (bVar3 instanceof K) {
                    K k = (K) bVar3;
                    if (!k.getTypeParameters().isEmpty()) {
                        bVar3 = k.q0().m().build();
                        j.b(bVar3);
                    }
                }
                C1309m b = C1311o.f5142c.n(bVar3, bVar2, false).b();
                j.d(b, "getResult(...)");
                if (C0901h.f3944a[b.ordinal()] == 1) {
                    return C1303g.OVERRIDABLE;
                }
                return C1303g.UNKNOWN;
            }
        }
        return C1303g.UNKNOWN;
    }

    public final C1302f b() {
        return C1302f.SUCCESS_ONLY;
    }
}
