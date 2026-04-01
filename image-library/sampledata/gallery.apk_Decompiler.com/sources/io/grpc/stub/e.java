package io.grpc.stub;

import B1.a;
import E2.k;
import com.google.common.util.concurrent.q;
import ee.C0984q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends q {
    public final C0984q d;

    public e(C0984q qVar) {
        this.d = qVar;
    }

    public final void interruptTask() {
        this.d.b("GrpcFuture was cancelled", (Throwable) null);
    }

    public final String pendingToString() {
        k V = a.V(this);
        V.a(this.d, "clientCall");
        return V.toString();
    }
}
