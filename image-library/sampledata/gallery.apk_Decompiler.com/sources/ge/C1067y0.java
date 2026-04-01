package ge;

import ee.C0984q;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

/* renamed from: ge.y0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1067y0 extends Channel {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ B0 f4575a;

    public C1067y0(B0 b0) {
        this.f4575a = b0;
    }

    public final String f() {
        return this.f4575a.b;
    }

    public final C0984q g(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        ScheduledExecutorService scheduledExecutorService;
        F0 f02 = this.f4575a.d;
        Logger logger = F0.f4398c0;
        Executor executor = callOptions.b;
        if (executor == null) {
            executor = f02.f4420h;
        }
        C1059u0 u0Var = f02.a0;
        if (f02.f4409I) {
            scheduledExecutorService = null;
        } else {
            scheduledExecutorService = this.f4575a.d.f.d.l;
        }
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        C1051q qVar = new C1051q(methodDescriptor, executor, callOptions, u0Var, scheduledExecutorService2, this.f4575a.d.L);
        F0 f03 = this.f4575a.d;
        qVar.r = f03.n;
        qVar.s = f03.f4423o;
        return qVar;
    }
}
