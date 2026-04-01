package ee;

import He.F;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;

/* renamed from: ee.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0972e extends Channel {

    /* renamed from: a  reason: collision with root package name */
    public final Channel f4294a;
    public final ClientInterceptor b;

    public C0972e(Channel channel, ClientInterceptor clientInterceptor) {
        this.f4294a = channel;
        F.n(clientInterceptor, "interceptor");
        this.b = clientInterceptor;
    }

    public final String f() {
        return this.f4294a.f();
    }

    public final C0984q g(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        return this.b.a();
    }
}
