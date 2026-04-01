package ge;

import He.F;
import ee.C0990x;
import ee.M;
import ee.a0;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import io.grpc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class U implements C1058u {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f4480a;
    public final C1054s b;

    public U(a0 a0Var, C1054s sVar) {
        F.i("error must not be OK", !a0Var.e());
        this.f4480a = a0Var;
        this.b = sVar;
    }

    public final r a(MethodDescriptor methodDescriptor, M m, CallOptions callOptions, a[] aVarArr) {
        return new T(this.f4480a, this.b, aVarArr);
    }

    public final C0990x c() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}
