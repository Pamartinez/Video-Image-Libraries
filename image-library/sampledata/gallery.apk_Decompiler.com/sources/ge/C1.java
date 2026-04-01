package ge;

import He.F;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1 implements Executor {
    public static final Logger f = Logger.getLogger(C1.class.getName());
    public boolean d;
    public ArrayDeque e;

    public final void a() {
        while (true) {
            Runnable runnable = (Runnable) this.e.poll();
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    Level level = Level.SEVERE;
                    f.log(level, "Exception while executing runnable " + runnable, th);
                }
            } else {
                return;
            }
        }
    }

    public final void execute(Runnable runnable) {
        F.n(runnable, "'task' must not be null.");
        if (!this.d) {
            this.d = true;
            try {
                runnable.run();
                if (this.e != null) {
                    a();
                }
                this.d = false;
            } catch (Throwable th) {
                if (this.e != null) {
                    a();
                }
                this.d = false;
                throw th;
            }
        } else {
            if (this.e == null) {
                this.e = new ArrayDeque(4);
            }
            this.e.add(runnable);
        }
    }
}
