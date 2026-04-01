package Ze;

import Gd.a;
import Qe.C0812b;
import Qe.C0816f;
import Qe.O;
import kotlin.jvm.internal.j;
import tf.C1302f;
import tf.C1303g;
import tf.C1304h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements C1304h {
    public final C1303g a(C0812b bVar, C0812b bVar2, C0816f fVar) {
        j.e(bVar, "superDescriptor");
        j.e(bVar2, "subDescriptor");
        if (!(bVar2 instanceof O) || !(bVar instanceof O)) {
            return C1303g.UNKNOWN;
        }
        O o2 = (O) bVar2;
        O o3 = (O) bVar;
        if (!j.a(o2.getName(), o3.getName())) {
            return C1303g.UNKNOWN;
        }
        if (a.M(o2) && a.M(o3)) {
            return C1303g.OVERRIDABLE;
        }
        if (a.M(o2) || a.M(o3)) {
            return C1303g.INCOMPATIBLE;
        }
        return C1303g.UNKNOWN;
    }

    public final C1302f b() {
        return C1302f.BOTH;
    }
}
