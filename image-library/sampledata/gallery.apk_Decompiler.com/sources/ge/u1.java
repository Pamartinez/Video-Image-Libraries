package ge;

import A0.l;
import ee.M;
import ee.a0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u1 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ W0 e;

    public /* synthetic */ u1(W0 w02, int i2) {
        this.d = i2;
        this.e = w02;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                C1057t0 t0Var = (C1057t0) this.e.f;
                t0Var.f4552C = true;
                C1056t tVar = t0Var.f4560x;
                l lVar = t0Var.v;
                tVar.u0((a0) lVar.e, (C1054s) lVar.f, (M) lVar.g);
                return;
            default:
                C1057t0 t0Var2 = (C1057t0) this.e.f;
                if (!t0Var2.f4552C) {
                    t0Var2.f4560x.b0();
                    return;
                }
                return;
        }
    }
}
