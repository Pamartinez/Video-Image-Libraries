package ge;

import D1.f;
import He.F;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import ee.M;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import java.util.Arrays;

/* renamed from: ge.c1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1014c1 {

    /* renamed from: a  reason: collision with root package name */
    public final CallOptions f4498a;
    public final M b;

    /* renamed from: c  reason: collision with root package name */
    public final MethodDescriptor f4499c;

    public C1014c1(MethodDescriptor methodDescriptor, M m, CallOptions callOptions) {
        F.n(methodDescriptor, "method");
        this.f4499c = methodDescriptor;
        F.n(m, Contract.HEADERS);
        this.b = m;
        F.n(callOptions, "callOptions");
        this.f4498a = callOptions;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C1014c1.class == obj.getClass()) {
            C1014c1 c1Var = (C1014c1) obj;
            if (!f.p(this.f4498a, c1Var.f4498a) || !f.p(this.b, c1Var.b) || !f.p(this.f4499c, c1Var.f4499c)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4498a, this.b, this.f4499c});
    }

    public final String toString() {
        return "[method=" + this.f4499c + " headers=" + this.b + " callOptions=" + this.f4498a + "]";
    }
}
