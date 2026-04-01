package ge;

import D0.e;
import Df.n;
import He.F;
import ee.C0971d;
import ee.C0979l;
import ee.C0983p;
import ee.C0984q;
import ee.C0988v;
import ee.M;
import ee.a0;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import java.util.concurrent.Executor;

/* renamed from: ge.v0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1061v0 extends C0983p {
    public final C0988v d;
    public final Channel e;
    public final Executor f;
    public final MethodDescriptor g;

    /* renamed from: h  reason: collision with root package name */
    public final C0979l f4564h;

    /* renamed from: i  reason: collision with root package name */
    public CallOptions f4565i;

    /* renamed from: j  reason: collision with root package name */
    public C0984q f4566j;

    public C1061v0(C0988v vVar, C1067y0 y0Var, Executor executor, MethodDescriptor methodDescriptor, CallOptions callOptions) {
        this.d = vVar;
        this.e = y0Var;
        this.g = methodDescriptor;
        Executor executor2 = callOptions.b;
        executor = executor2 != null ? executor2 : executor;
        this.f = executor;
        n b = CallOptions.b(callOptions);
        b.b = executor;
        this.f4565i = new CallOptions(b);
        this.f4564h = C0979l.b();
    }

    public final void b(String str, Throwable th) {
        C0984q qVar = this.f4566j;
        if (qVar != null) {
            qVar.b(str, th);
        }
    }

    public final void l(C0971d dVar, M m) {
        CallOptions callOptions = this.f4565i;
        MethodDescriptor methodDescriptor = this.g;
        F.n(methodDescriptor, "method");
        F.n(callOptions, "callOptions");
        e a7 = this.d.a();
        a0 a0Var = (a0) a7.e;
        if (!a0Var.e()) {
            this.f.execute(new C1001B(this, dVar, Z.e(a0Var)));
            this.f4566j = F0.f4403j0;
            return;
        }
        N0 n02 = (N0) a7.f;
        L0 l0 = (L0) n02.b.get(methodDescriptor.b);
        if (l0 == null) {
            l0 = (L0) n02.f4463c.get(methodDescriptor.f4621c);
        }
        if (l0 == null) {
            l0 = n02.f4462a;
        }
        if (l0 != null) {
            this.f4565i = this.f4565i.e(L0.g, l0);
        }
        C0984q g3 = this.e.g(methodDescriptor, this.f4565i);
        this.f4566j = g3;
        g3.l(dVar, m);
    }
}
