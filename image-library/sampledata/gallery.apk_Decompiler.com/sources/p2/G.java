package P2;

import L2.a;
import P0.b;
import S2.t;
import S2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends n {
    public final void a(C0056f fVar) {
        C c5 = fVar.e;
        u uVar = this.e;
        if (uVar.e == null) {
            uVar.e = new t(uVar.d.d);
        }
        c5.o(uVar.e);
    }

    public final q b() {
        return q.TYPE_TYPE_ID_ITEM;
    }

    public final int c() {
        return 4;
    }

    public final void d(C0056f fVar, b bVar) {
        u uVar = this.e;
        if (uVar.e == null) {
            uVar.e = new t(uVar.d.d);
        }
        t tVar = uVar.e;
        int l = fVar.e.l(tVar);
        if (bVar.d()) {
            bVar.b(0, f() + ' ' + tVar.a());
            bVar.b(4, "  descriptor_idx: ".concat(a.E(l)));
        }
        bVar.l(l);
    }
}
