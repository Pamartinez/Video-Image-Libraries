package ge;

import io.grpc.binder.c;
import java.net.SocketAddress;

/* renamed from: ge.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1021f implements C1062w {
    public final c d;
    public final C1063w0 e;

    public C1021f(c cVar, C1063w0 w0Var) {
        this.d = cVar;
        this.e = w0Var;
    }

    public final void close() {
        this.d.close();
    }

    public final C1064x o(SocketAddress socketAddress, C1060v vVar, C1028h0 h0Var) {
        return new C1018e(this, this.d.o(socketAddress, vVar, h0Var), vVar.f4562a);
    }
}
