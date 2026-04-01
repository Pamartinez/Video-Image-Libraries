package ge;

import G0.e;
import He.F;

/* renamed from: ge.E  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1004E implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ e e;

    public /* synthetic */ C1004E(e eVar, int i2) {
        this.d = i2;
        this.e = eVar;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.N(true);
                return;
            case 1:
                this.e.N(false);
                return;
            default:
                F0 f02 = (F0) this.e.d;
                F.t(f02.f4407G.get(), "Channel must have been shut down");
                f02.f4408H = true;
                f02.o(false);
                f02.getClass();
                F0.i(f02);
                return;
        }
    }
}
