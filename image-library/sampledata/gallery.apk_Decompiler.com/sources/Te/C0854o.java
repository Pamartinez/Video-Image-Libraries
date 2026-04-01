package Te;

import Ae.b;
import Ye.c;
import qf.C1240g;

/* renamed from: Te.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0854o implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0855p e;

    public /* synthetic */ C0854o(C0855p pVar, int i2) {
        this.d = i2;
        this.e = pVar;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                C1240g gVar = (C1240g) obj;
                if (gVar != null) {
                    C0855p pVar = this.e;
                    return pVar.j(gVar, pVar.i().a(gVar, c.FOR_NON_TRACKED_SCOPE));
                }
                C0855p.h(8);
                throw null;
            default:
                C1240g gVar2 = (C1240g) obj;
                if (gVar2 != null) {
                    C0855p pVar2 = this.e;
                    return pVar2.j(gVar2, pVar2.i().f(gVar2, c.FOR_NON_TRACKED_SCOPE));
                }
                C0855p.h(4);
                throw null;
        }
    }
}
