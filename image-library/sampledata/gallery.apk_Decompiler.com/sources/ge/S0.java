package ge;

import E2.k;
import He.F;
import ee.C0964A;
import ee.C0971d;
import ee.a0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S0 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4475c = 0;
    public final Object d;

    public S0(C0964A a7) {
        F.n(a7, "result");
        this.d = a7;
    }

    public final C0964A j() {
        switch (this.f4475c) {
            case 0:
                return (C0964A) this.d;
            default:
                return C0964A.a((a0) this.d);
        }
    }

    public String toString() {
        switch (this.f4475c) {
            case 0:
                k kVar = new k(S0.class.getSimpleName());
                kVar.a((C0964A) this.d, "result");
                return kVar.toString();
            default:
                return super.toString();
        }
    }

    public S0(a0 a0Var) {
        this.d = a0Var;
    }
}
