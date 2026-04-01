package ge;

import He.F;
import Kd.a;
import ee.C0975h;

/* renamed from: ge.g0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1025g0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ a e;

    public /* synthetic */ C1025g0(a aVar, int i2) {
        this.d = i2;
        this.e = aVar;
    }

    public final void run() {
        boolean z;
        switch (this.d) {
            case 0:
                a aVar = this.e;
                C1031i0 i0Var = (C1031i0) aVar.f;
                i0Var.n = null;
                if (i0Var.f4523x != null) {
                    if (i0Var.v == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    F.t(z, "Unexpected non-null activeTransport");
                    a aVar2 = this.e;
                    ((C1019e0) aVar2.e).d(((C1031i0) aVar2.f).f4523x);
                    return;
                }
                C1019e0 e0Var = i0Var.u;
                C1019e0 e0Var2 = (C1019e0) aVar.e;
                if (e0Var == e0Var2) {
                    i0Var.v = e0Var2;
                    C1031i0 i0Var2 = (C1031i0) this.e.f;
                    i0Var2.u = null;
                    C1031i0.f(i0Var2, C0975h.READY);
                    return;
                }
                return;
            default:
                a aVar3 = this.e;
                ((C1031i0) aVar3.f).s.remove((C1019e0) aVar3.e);
                if (((C1031i0) this.e.f).w.f4298a == C0975h.SHUTDOWN && ((C1031i0) this.e.f).s.isEmpty()) {
                    C1031i0 i0Var3 = (C1031i0) this.e.f;
                    i0Var3.k.execute(new C1013c0(i0Var3, 2));
                    return;
                }
                return;
        }
    }
}
