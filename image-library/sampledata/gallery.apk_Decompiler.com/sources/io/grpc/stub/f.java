package io.grpc.stub;

import ee.C0971d;
import ee.M;
import ee.a0;
import ee.c0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4633c;
    public boolean d;
    public final Object e;
    public Object f;

    public f(e eVar) {
        this.f4633c = 1;
        this.d = false;
        this.e = eVar;
    }

    public final void d(a0 a0Var, M m) {
        switch (this.f4633c) {
            case 0:
                StreamObserver streamObserver = (StreamObserver) this.e;
                if (a0Var.e()) {
                    streamObserver.onCompleted();
                    return;
                } else {
                    streamObserver.a(new c0(a0Var, m));
                    return;
                }
            default:
                e eVar = (e) this.e;
                if (a0Var.e()) {
                    if (!this.d) {
                        eVar.setException(new c0(a0.n.g("No value received for unary call"), m));
                    }
                    eVar.set(this.f);
                    return;
                }
                eVar.setException(new c0(a0Var, m));
                return;
        }
    }

    public final void f(M m) {
        int i2 = this.f4633c;
    }

    public final void g(Object obj) {
        switch (this.f4633c) {
            case 0:
                if (!this.d) {
                    this.d = true;
                    ((StreamObserver) this.e).onNext(obj);
                    return;
                }
                throw a0.n.g("More than one responses received for unary or client-streaming call").a();
            default:
                if (!this.d) {
                    this.f = obj;
                    this.d = true;
                    return;
                }
                throw a0.n.g("More than one value received for unary call").a();
        }
    }

    public void h() {
        switch (this.f4633c) {
            case 0:
                ((d) this.f).getClass();
                return;
            default:
                return;
        }
    }

    public f(StreamObserver streamObserver, d dVar) {
        this.f4633c = 0;
        this.e = streamObserver;
        this.f = dVar;
    }

    private final void k(M m) {
    }

    private final void l(M m) {
    }
}
