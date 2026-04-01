package ge;

import Bf.a;
import ee.C0989w;

/* renamed from: ge.b0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1010b0 extends a {
    public final /* synthetic */ int f;
    public final /* synthetic */ C0989w g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1010b0(C0989w wVar, int i2) {
        super(3);
        this.f = i2;
        this.g = wVar;
    }

    public final void y0() {
        switch (this.f) {
            case 0:
                C1031i0 i0Var = (C1031i0) this.g;
                ((E0) i0Var.d.f).f4395j.Z.A0(i0Var, true);
                return;
            default:
                ((F0) this.g).k();
                return;
        }
    }

    public final void z0() {
        switch (this.f) {
            case 0:
                C1031i0 i0Var = (C1031i0) this.g;
                ((E0) i0Var.d.f).f4395j.Z.A0(i0Var, false);
                return;
            default:
                F0 f02 = (F0) this.g;
                if (!f02.f4407G.get()) {
                    f02.m();
                    return;
                }
                return;
        }
    }
}
