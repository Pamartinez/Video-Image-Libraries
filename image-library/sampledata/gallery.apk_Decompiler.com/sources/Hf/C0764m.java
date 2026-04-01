package Hf;

import Kf.e;
import Qe.V;
import kotlin.jvm.internal.j;

/* renamed from: Hf.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0764m extends C0765n implements C0762k, e {
    public final B e;
    public final boolean f;

    public C0764m(B b, boolean z) {
        this.e = b;
        this.f = z;
    }

    public final B B0(boolean z) {
        if (z) {
            return this.e.y0(z);
        }
        return this;
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return new C0764m(this.e.A0(i2), this.f);
    }

    public final B D0() {
        return this.e;
    }

    public final C0765n F0(B b) {
        return new C0764m(b, this.f);
    }

    public final boolean L() {
        B b = this.e;
        b.s0();
        if (b.s0().g() instanceof V) {
            return true;
        }
        return false;
    }

    public final c0 n(C0774x xVar) {
        j.e(xVar, "replacement");
        return C0754c.n(xVar.x0(), this.f);
    }

    public final String toString() {
        return this.e + " & Any";
    }

    public final boolean u0() {
        return false;
    }
}
