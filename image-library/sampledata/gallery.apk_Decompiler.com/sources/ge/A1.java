package ge;

import B1.a;
import E2.k;
import e5.C0451a;
import ee.C0968a;
import ee.C0971d;
import ee.C0984q;
import ee.e0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A1 extends C0984q {
    public static final C0968a g = new C0968a("io.grpc.internal.RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY");
    public final C0984q d;
    public final C1015d e;
    public final e0 f;

    public A1(C0984q qVar, C1015d dVar, e0 e0Var) {
        this.d = qVar;
        this.e = dVar;
        this.f = e0Var;
    }

    public String c() {
        return this.d.c();
    }

    public final void g() {
        this.d.g();
    }

    public final void j() {
        this.d.j();
        C1015d dVar = this.e;
        e0 e0Var = dVar.b;
        e0Var.d();
        e0Var.execute(new C0451a(18, dVar));
    }

    public final void k(C0971d dVar) {
        this.d.k(new z1(this, dVar));
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.d, "delegate");
        return V.toString();
    }
}
