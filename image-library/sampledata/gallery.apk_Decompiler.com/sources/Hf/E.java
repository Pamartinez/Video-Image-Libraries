package Hf;

import If.f;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E extends C0765n implements b0 {
    public final B e;
    public final C0774x f;

    public E(B b, C0774x xVar) {
        j.e(b, "delegate");
        j.e(xVar, "enhancement");
        this.e = b;
        this.f = xVar;
    }

    public final B B0(boolean z) {
        c0 G5 = C0754c.G(this.e.y0(z), this.f.x0().y0(z));
        j.c(G5, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (B) G5;
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        c0 G5 = C0754c.G(this.e.A0(i2), this.f);
        j.c(G5, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (B) G5;
    }

    public final B D0() {
        return this.e;
    }

    public final C0765n F0(B b) {
        return new E(b, this.f);
    }

    /* renamed from: G0 */
    public final E z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        C0774x xVar = this.f;
        j.e(xVar, "type");
        return new E(b, xVar);
    }

    public final C0774x K() {
        return this.f;
    }

    public final c0 O() {
        return this.e;
    }

    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f + ")] " + this.e;
    }
}
