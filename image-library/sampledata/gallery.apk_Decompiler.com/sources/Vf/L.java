package Vf;

import java.util.concurrent.Executor;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L implements Executor {
    public final C0886x d;

    public L(C0886x xVar) {
        this.d = xVar;
    }

    public final void execute(Runnable runnable) {
        C0886x xVar = this.d;
        C1233i iVar = C1233i.d;
        if (xVar.j(iVar)) {
            xVar.i(iVar, runnable);
        } else {
            runnable.run();
        }
    }

    public final String toString() {
        return this.d.toString();
    }
}
