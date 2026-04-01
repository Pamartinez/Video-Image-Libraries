package io.grpc.stub;

import He.F;
import ee.C0984q;
import ee.c0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements StreamObserver {

    /* renamed from: a  reason: collision with root package name */
    public final C0984q f4631a;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public boolean f4632c = false;

    public d(C0984q qVar) {
        this.f4631a = qVar;
    }

    public final void a(c0 c0Var) {
        this.f4631a.b("Cancelled by client with StreamObserver.onError()", c0Var);
        this.b = true;
    }

    public final void onCompleted() {
        this.f4631a.d();
        this.f4632c = true;
    }

    public final void onNext(Object obj) {
        F.t(!this.b, "Stream was terminated by error, no further calls are allowed");
        F.t(!this.f4632c, "Stream is already completed, no further calls are allowed");
        this.f4631a.i(obj);
    }
}
