package ge;

import He.F;
import ee.a0;
import ee.b0;
import ee.c0;
import java.lang.Thread;
import java.util.List;

/* renamed from: ge.h1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1029h1 implements Thread.UncaughtExceptionHandler {
    public final void uncaughtException(Thread thread, Throwable th) {
        a0 f;
        List list = a0.d;
        F.n(th, "t");
        Throwable th2 = th;
        while (true) {
            if (th2 != null) {
                if (!(th2 instanceof b0)) {
                    if (th2 instanceof c0) {
                        f = ((c0) th2).d;
                        break;
                    }
                    th2 = th2.getCause();
                } else {
                    f = ((b0) th2).d;
                    break;
                }
            } else {
                f = a0.g.f(th);
                break;
            }
        }
        throw f.g("Uncaught exception in the SynchronizationContext. Re-thrown.").a();
    }
}
