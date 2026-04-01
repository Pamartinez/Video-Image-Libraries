package ge;

import E2.k;
import He.F;
import ee.C0964A;
import ee.C0971d;
import ee.a0;

/* renamed from: ge.r0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1053r0 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4542c = 1;
    public final C0964A d;

    public C1053r0(C0964A a7) {
        this.d = a7;
    }

    public final C0964A j() {
        switch (this.f4542c) {
            case 0:
                return this.d;
            default:
                return this.d;
        }
    }

    public final String toString() {
        switch (this.f4542c) {
            case 0:
                k kVar = new k(C1053r0.class.getSimpleName());
                kVar.a(this.d, "panicPickResult");
                return kVar.toString();
            default:
                return "FixedResultPicker(" + this.d + ")";
        }
    }

    public C1053r0(Throwable th) {
        a0 f = a0.n.g("Panic! This is a bug!").f(th);
        C0964A a7 = C0964A.d;
        F.i("drop status shouldn't be OK", !f.e());
        this.d = new C0964A((E0) null, f, true);
    }
}
