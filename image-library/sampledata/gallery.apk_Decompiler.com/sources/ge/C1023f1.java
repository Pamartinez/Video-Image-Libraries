package ge;

import E2.q;
import java.util.concurrent.TimeUnit;

/* renamed from: ge.f1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1023f1 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ C1026g1 e;

    public /* synthetic */ C1023f1(C1026g1 g1Var, int i2) {
        this.d = i2;
        this.e = g1Var;
    }

    public final void run() {
        long j2;
        switch (this.d) {
            case 0:
                C1026g1 g1Var = this.e;
                if (!g1Var.f) {
                    g1Var.g = null;
                    return;
                }
                q qVar = g1Var.d;
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                qVar.getClass();
                if (qVar.f175a) {
                    j2 = System.nanoTime() - qVar.b;
                } else {
                    j2 = 0;
                }
                long convert = g1Var.e - timeUnit.convert(j2, timeUnit);
                if (convert > 0) {
                    g1Var.g = g1Var.f4512a.schedule(new C1023f1(g1Var, 1), convert, timeUnit);
                    return;
                }
                g1Var.f = false;
                g1Var.g = null;
                g1Var.f4513c.run();
                return;
            default:
                C1026g1 g1Var2 = this.e;
                g1Var2.b.execute(new C1023f1(g1Var2, 0));
                return;
        }
    }
}
