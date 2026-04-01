package R2;

import T2.b;
import T2.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends f {
    public i(n nVar, p pVar, k kVar, l lVar) {
        super(nVar, pVar, kVar, lVar);
        int i2 = nVar.e;
        if (i2 == 5 || i2 == 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (kVar != null && i2 != 1) {
            throw new IllegalArgumentException("can't mix branchingness with result");
        }
    }

    public final void c(e eVar) {
        eVar.l(this);
    }

    public final e d() {
        return b.f;
    }
}
