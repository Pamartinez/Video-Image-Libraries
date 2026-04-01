package Hf;

import If.f;
import Qe.V;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends C0768q implements C0762k {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r(B b, B b5) {
        super(b, b5);
        j.e(b, "lowerBound");
        j.e(b5, "upperBound");
    }

    public final c0 A0(I i2) {
        j.e(i2, "newAttributes");
        return C0754c.f(this.e.A0(i2), this.f.A0(i2));
    }

    public final B B0() {
        return this.e;
    }

    public final String C0(C1283j jVar, C1283j jVar2) {
        boolean n = jVar2.f5084a.n();
        B b = this.f;
        B b5 = this.e;
        if (!n) {
            return jVar.F(jVar.Y(b5), jVar.Y(b), c.z(this));
        }
        return "(" + jVar.Y(b5) + ".." + jVar.Y(b) + ')';
    }

    public final boolean L() {
        B b = this.e;
        if (!(b.s0().g() instanceof V) || !j.a(b.s0(), this.f.s0())) {
            return false;
        }
        return true;
    }

    public final c0 n(C0774x xVar) {
        c0 c0Var;
        j.e(xVar, "replacement");
        c0 x02 = xVar.x0();
        if (x02 instanceof C0768q) {
            c0Var = x02;
        } else if (x02 instanceof B) {
            B b = (B) x02;
            c0Var = C0754c.f(b, b.y0(true));
        } else {
            throw new RuntimeException();
        }
        return C0754c.i(c0Var, x02);
    }

    public final String toString() {
        return "(" + this.e + ".." + this.f + ')';
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        B b5 = this.f;
        j.e(b5, "type");
        return new r(b, b5);
    }

    public final c0 y0(boolean z) {
        return C0754c.f(this.e.y0(z), this.f.y0(z));
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = this.e;
        j.e(b, "type");
        B b5 = this.f;
        j.e(b5, "type");
        return new r(b, b5);
    }
}
