package eg;

import Vf.Y;
import java.util.concurrent.Executor;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i extends Y {
    public d d;

    public final void dispatchYield(C1232h hVar, Runnable runnable) {
        d.f(this.d, runnable, 2);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        d.f(this.d, runnable, 6);
    }

    public final Executor l() {
        return this.d;
    }
}
