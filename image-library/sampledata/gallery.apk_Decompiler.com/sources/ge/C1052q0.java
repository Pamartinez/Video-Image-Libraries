package ge;

import ee.C0970c;
import ee.C0971d;
import ee.C0975h;
import java.util.Set;

/* renamed from: ge.q0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1052q0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ F0 e;

    public /* synthetic */ C1052q0(F0 f02, int i2) {
        this.d = i2;
        this.e = f02;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.j(true);
                return;
            case 1:
                F0 f02 = this.e;
                f02.f4413N.b(C0970c.INFO, "Entering SHUTDOWN state");
                f02.r.a(C0975h.SHUTDOWN);
                return;
            default:
                F0 f03 = this.e;
                if (f03.f4425x != null) {
                    f03.o(true);
                    G g = f03.E;
                    g.h((C0971d) null);
                    f03.f4413N.b(C0970c.INFO, "Entering IDLE state");
                    f03.r.a(C0975h.IDLE);
                    C1010b0 b0Var = f03.Z;
                    Object[] objArr = {f03.f4405C, g};
                    b0Var.getClass();
                    for (int i2 = 0; i2 < 2; i2++) {
                        if (((Set) b0Var.e).contains(objArr[i2])) {
                            f03.k();
                            return;
                        }
                    }
                    return;
                }
                return;
        }
    }
}
