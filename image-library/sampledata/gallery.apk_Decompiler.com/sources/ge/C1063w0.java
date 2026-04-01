package ge;

import G0.c;
import He.F;
import L1.d;
import java.util.concurrent.Executor;

/* renamed from: ge.w0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1063w0 implements Executor {
    public final c d;
    public Executor e;

    public C1063w0(c cVar) {
        F.n(cVar, "executorPool");
        this.d = cVar;
    }

    public final void execute(Runnable runnable) {
        Executor executor;
        synchronized (this) {
            try {
                if (this.e == null) {
                    Executor executor2 = (Executor) L1.a((K1) this.d.e);
                    Executor executor3 = this.e;
                    if (executor2 != null) {
                        this.e = executor2;
                    } else {
                        throw new NullPointerException(d.r("%s.getObject()", executor3));
                    }
                }
                executor = this.e;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        executor.execute(runnable);
    }
}
