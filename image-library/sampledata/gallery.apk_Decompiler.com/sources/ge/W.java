package ge;

import B1.a;
import E2.k;
import ee.C0984q;
import ee.F;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class W extends F {

    /* renamed from: a  reason: collision with root package name */
    public final F0 f4486a;

    public W(F0 f02) {
        this.f4486a = f02;
    }

    public final String f() {
        return this.f4486a.t.f();
    }

    public final C0984q g(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        return this.f4486a.t.g(methodDescriptor, callOptions);
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4486a, "delegate");
        return V.toString();
    }
}
