package hf;

import Hf.B;
import Hf.C0754c;
import Hf.C0762k;
import Hf.C0765n;
import Hf.C0768q;
import Hf.C0774x;
import Hf.I;
import Hf.a0;
import Hf.c0;
import kotlin.jvm.internal.j;

/* renamed from: hf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1084f extends C0765n implements C0762k {
    public final B e;

    public C1084f(B b) {
        j.e(b, "delegate");
        this.e = b;
    }

    public final c0 A0(I i2) {
        j.e(i2, "newAttributes");
        return new C1084f(this.e.A0(i2));
    }

    public final B B0(boolean z) {
        if (z) {
            return this.e.y0(true);
        }
        return this;
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return new C1084f(this.e.A0(i2));
    }

    public final B D0() {
        return this.e;
    }

    public final C0765n F0(B b) {
        return new C1084f(b);
    }

    public final boolean L() {
        return true;
    }

    public final c0 n(C0774x xVar) {
        j.e(xVar, "replacement");
        c0 x02 = xVar.x0();
        if (!a0.f(x02) && !a0.e(x02)) {
            return x02;
        }
        if (x02 instanceof B) {
            B b = (B) x02;
            B B02 = b.y0(false);
            if (!a0.f(b)) {
                return B02;
            }
            return new C1084f(B02);
        } else if (x02 instanceof C0768q) {
            C0768q qVar = (C0768q) x02;
            B b5 = qVar.e;
            B B03 = b5.y0(false);
            if (a0.f(b5)) {
                B03 = new C1084f(B03);
            }
            B b8 = qVar.f;
            B B04 = b8.y0(false);
            if (a0.f(b8)) {
                B04 = new C1084f(B04);
            }
            return C0754c.G(C0754c.f(B03, B04), C0754c.g(x02));
        } else {
            throw new IllegalStateException(("Incorrect type: " + x02).toString());
        }
    }

    public final boolean u0() {
        return false;
    }
}
