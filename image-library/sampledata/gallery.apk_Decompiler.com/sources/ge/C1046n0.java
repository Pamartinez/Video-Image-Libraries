package ge;

import E2.v;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.n0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1046n0 implements Runnable {
    public static final Logger e = Logger.getLogger(C1046n0.class.getName());
    public final Runnable d;

    public C1046n0(Runnable runnable) {
        this.d = runnable;
    }

    public final void run() {
        Runnable runnable = this.d;
        try {
            runnable.run();
        } catch (Throwable th) {
            Level level = Level.SEVERE;
            e.log(level, "Exception while executing runnable " + runnable, th);
            Object obj = v.f176a;
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            } else if (!(th instanceof Error)) {
                throw new AssertionError(th);
            } else {
                throw th;
            }
        }
    }

    public final String toString() {
        return "LogExceptionRunnable(" + this.d + ")";
    }
}
