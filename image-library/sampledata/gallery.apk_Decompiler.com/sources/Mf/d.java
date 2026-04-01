package Mf;

import Hf.G;
import Hf.M;
import Hf.N;
import Hf.P;
import Hf.d0;
import kotlin.jvm.internal.j;
import uf.C1317b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends N {
    public final P g(M m) {
        C1317b bVar;
        j.e(m, "key");
        if (m instanceof C1317b) {
            bVar = (C1317b) m;
        } else {
            bVar = null;
        }
        if (bVar == null) {
            return null;
        }
        if (!bVar.a().c()) {
            return bVar.a();
        }
        return new G(bVar.a().b(), d0.OUT_VARIANCE);
    }
}
