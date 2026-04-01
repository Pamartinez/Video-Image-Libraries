package ge;

import He.F;
import P1.e;
import ee.C0979l;
import ee.C0984q;
import ee.C0988v;
import ee.e0;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B0 extends Channel {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference f4382a = new AtomicReference(F0.f4402i0);
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final C1067y0 f4383c = new C1067y0(this);
    public final /* synthetic */ F0 d;

    public B0(F0 f02, String str) {
        this.d = f02;
        F.n(str, "authority");
        this.b = str;
    }

    public final String f() {
        return this.b;
    }

    public final C0984q g(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        F0 f02 = this.d;
        e0 e0Var = f02.m;
        AtomicReference atomicReference = this.f4382a;
        Object obj = atomicReference.get();
        C1050p0 p0Var = F0.f4402i0;
        if (obj != p0Var) {
            return h(methodDescriptor, callOptions);
        }
        e0Var.execute(new C1069z0(this, 1));
        if (atomicReference.get() != p0Var) {
            return h(methodDescriptor, callOptions);
        }
        if (f02.f4407G.get()) {
            return new C1000A(2);
        }
        A0 a0 = new A0(this, C0979l.b(), methodDescriptor, callOptions);
        e0Var.execute(new e(26, this, a0));
        return a0;
    }

    public final C0984q h(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        C0988v vVar = (C0988v) this.f4382a.get();
        C1067y0 y0Var = this.f4383c;
        if (vVar == null) {
            return y0Var.g(methodDescriptor, callOptions);
        }
        if (!(vVar instanceof M0)) {
            return new C1061v0(vVar, y0Var, this.d.f4420h, methodDescriptor, callOptions);
        }
        N0 n02 = ((M0) vVar).b;
        L0 l0 = (L0) n02.b.get(methodDescriptor.b);
        if (l0 == null) {
            l0 = (L0) n02.f4463c.get(methodDescriptor.f4621c);
        }
        if (l0 == null) {
            l0 = n02.f4462a;
        }
        if (l0 != null) {
            callOptions = callOptions.e(L0.g, l0);
        }
        return y0Var.g(methodDescriptor, callOptions);
    }

    public final void i(C0988v vVar) {
        LinkedHashSet<A0> linkedHashSet;
        AtomicReference atomicReference = this.f4382a;
        C0988v vVar2 = (C0988v) atomicReference.get();
        atomicReference.set(vVar);
        if (vVar2 == F0.f4402i0 && (linkedHashSet = this.d.B) != null) {
            for (A0 p6 : linkedHashSet) {
                p6.p();
            }
        }
    }
}
