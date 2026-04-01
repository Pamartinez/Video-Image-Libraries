package ge;

import D0.e;
import He.F;
import ee.C0971d;
import ee.C0984q;
import ee.S;
import ee.a0;

/* renamed from: ge.x0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1065x0 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final e f4571c;
    public final C0984q d;
    public final /* synthetic */ F0 e;

    public C1065x0(F0 f02, e eVar, A1 a12) {
        this.e = f02;
        this.f4571c = eVar;
        F.n(a12, "resolver");
        this.d = a12;
    }

    public final void e(a0 a0Var) {
        F.i("the error status must not be OK", !a0Var.e());
        this.e.m.execute(new P1.e(24, this, a0Var));
    }

    public final void i(S s) {
        this.e.m.execute(new P1.e(25, this, s));
    }
}
