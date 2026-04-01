package Hf;

import If.f;
import kotlin.jvm.internal.j;

/* renamed from: Hf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0752a extends C0765n {
    public final B e;
    public final B f;

    public C0752a(B b, B b5) {
        j.e(b, "delegate");
        j.e(b5, "abbreviation");
        this.e = b;
        this.f = b5;
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return new C0752a(this.e.A0(i2), this.f);
    }

    public final B D0() {
        return this.e;
    }

    public final C0765n F0(B b) {
        return new C0752a(b, this.f);
    }

    /* renamed from: G0 */
    public final C0752a y0(boolean z) {
        return new C0752a(this.e.y0(z), this.f.y0(z));
    }

    /* renamed from: H0 */
    public final C0752a z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        B b5 = this.f;
        j.e(b5, "type");
        return new C0752a(b, b5);
    }
}
