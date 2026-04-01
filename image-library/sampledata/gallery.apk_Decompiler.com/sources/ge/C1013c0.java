package ge;

import ee.C0970c;
import ee.C0975h;
import ee.C0989w;

/* renamed from: ge.c0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1013c0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ C1031i0 e;

    public /* synthetic */ C1013c0(C1031i0 i0Var, int i2) {
        this.d = i2;
        this.e = i0Var;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                C1031i0 i0Var = this.e;
                i0Var.f4522p = null;
                i0Var.f4519i.b(C0970c.INFO, "CONNECTING after backoff");
                C1031i0.f(i0Var, C0975h.CONNECTING);
                C1031i0.g(i0Var);
                return;
            case 1:
                if (this.e.w.f4298a == C0975h.IDLE) {
                    this.e.f4519i.b(C0970c.INFO, "CONNECTING as requested");
                    C1031i0.f(this.e, C0975h.CONNECTING);
                    C1031i0.g(this.e);
                    return;
                }
                return;
            default:
                C1031i0 i0Var2 = this.e;
                i0Var2.f4519i.b(C0970c.INFO, "Terminated");
                F0 f02 = ((E0) i0Var2.d.f).f4395j;
                f02.f4404A.remove(i0Var2);
                C0989w wVar = (C0989w) f02.f4414O.b.remove(Long.valueOf(i0Var2.c().f4315c));
                F0.i(f02);
                return;
        }
    }
}
