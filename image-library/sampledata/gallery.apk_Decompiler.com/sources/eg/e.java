package eg;

import Vf.C0886x;
import Vf.Y;
import cg.a;
import cg.u;
import java.util.concurrent.Executor;
import qe.C1232h;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends Y implements Executor {
    public static final e d = new C0886x();
    public static final C0886x e;

    /* JADX WARNING: type inference failed for: r0v0, types: [Vf.x, eg.e] */
    static {
        m mVar = m.d;
        int i2 = u.f4034a;
        if (64 >= i2) {
            i2 = 64;
        }
        e = C0886x.limitedParallelism$default(mVar, a.j(i2, 12, "kotlinx.coroutines.io.parallelism"), (String) null, 2, (Object) null);
    }

    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    public final void dispatchYield(C1232h hVar, Runnable runnable) {
        e.dispatchYield(hVar, runnable);
    }

    public final void execute(Runnable runnable) {
        i(C1233i.d, runnable);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        e.i(hVar, runnable);
    }

    public final C0886x limitedParallelism(int i2, String str) {
        return m.d.limitedParallelism(i2, str);
    }

    public final String toString() {
        return "Dispatchers.IO";
    }

    public final Executor l() {
        return this;
    }
}
