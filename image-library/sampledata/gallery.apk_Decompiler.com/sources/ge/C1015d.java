package ge;

import B2.A;
import D0.e;
import ee.d0;
import ee.e0;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1015d {
    public static final Logger e = Logger.getLogger(C1015d.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final ScheduledExecutorService f4500a;
    public final e0 b;

    /* renamed from: c  reason: collision with root package name */
    public S f4501c;
    public e d;

    public C1015d(Q0 q0, D0 d0, e0 e0Var) {
        this.f4500a = d0;
        this.b = e0Var;
    }

    public final void a(A a7) {
        this.b.d();
        if (this.f4501c == null) {
            this.f4501c = Q0.i();
        }
        e eVar = this.d;
        if (eVar != null) {
            d0 d0Var = (d0) eVar.e;
            if (!d0Var.f && !d0Var.e) {
                return;
            }
        }
        long a10 = this.f4501c.a();
        this.d = this.b.c(a7, a10, TimeUnit.NANOSECONDS, this.f4500a);
        e.log(Level.FINE, "Scheduling DNS resolution backoff for {0}ns", Long.valueOf(a10));
    }
}
