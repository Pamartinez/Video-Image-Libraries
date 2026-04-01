package Hf;

import If.f;
import kotlin.jvm.internal.j;
import sf.C1283j;
import sf.C1288o;

/* renamed from: Hf.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0769s extends C0768q implements b0 {
    public final C0768q g;

    /* renamed from: h  reason: collision with root package name */
    public final C0774x f3450h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0769s(C0768q qVar, C0774x xVar) {
        super(qVar.e, qVar.f);
        j.e(qVar, "origin");
        j.e(xVar, "enhancement");
        this.g = qVar;
        this.f3450h = xVar;
    }

    public final c0 A0(I i2) {
        j.e(i2, "newAttributes");
        return C0754c.G(this.g.A0(i2), this.f3450h);
    }

    public final B B0() {
        return this.g.B0();
    }

    public final String C0(C1283j jVar, C1283j jVar2) {
        C1288o oVar = jVar2.f5084a;
        if (((Boolean) oVar.m.f(oVar, C1288o.Y[11])).booleanValue()) {
            return jVar.Y(this.f3450h);
        }
        return this.g.C0(jVar, jVar2);
    }

    public final C0774x K() {
        return this.f3450h;
    }

    public final c0 O() {
        return this.g;
    }

    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f3450h + ")] " + this.g;
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        C0768q qVar = this.g;
        j.e(qVar, "type");
        C0774x xVar = this.f3450h;
        j.e(xVar, "type");
        return new C0769s(qVar, xVar);
    }

    public final c0 y0(boolean z) {
        return C0754c.G(this.g.y0(z), this.f3450h.x0().y0(z));
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        C0768q qVar = this.g;
        j.e(qVar, "type");
        C0774x xVar = this.f3450h;
        j.e(xVar, "type");
        return new C0769s(qVar, xVar);
    }
}
