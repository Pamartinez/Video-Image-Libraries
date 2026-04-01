package ge;

import ee.C0970c;
import ee.C0975h;
import ee.C0988v;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.s0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1055s0 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ F0 f4546a;

    public C1055s0(F0 f02) {
        this.f4546a = f02;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        Logger logger = F0.f4398c0;
        Level level = Level.SEVERE;
        StringBuilder sb2 = new StringBuilder("[");
        F0 f02 = this.f4546a;
        sb2.append(f02.f4418a);
        sb2.append("] Uncaught exception in the SynchronizationContext. Panic!");
        logger.log(level, sb2.toString(), th);
        if (!f02.z) {
            f02.z = true;
            f02.j(true);
            f02.o(false);
            C1053r0 r0Var = new C1053r0(th);
            f02.y = r0Var;
            f02.E.h(r0Var);
            f02.f4415P.i((C0988v) null);
            f02.f4413N.b(C0970c.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            f02.r.a(C0975h.TRANSIENT_FAILURE);
        }
    }
}
