package Hf;

import Af.p;
import Kf.d;
import java.util.List;
import kotlin.jvm.internal.j;
import sf.C1283j;

/* renamed from: Hf.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0768q extends c0 implements d {
    public final B e;
    public final B f;

    public C0768q(B b, B b5) {
        j.e(b, "lowerBound");
        j.e(b5, "upperBound");
        this.e = b;
        this.f = b5;
    }

    public p A() {
        return B0().A();
    }

    public abstract B B0();

    public abstract String C0(C1283j jVar, C1283j jVar2);

    public final List e0() {
        return B0().e0();
    }

    public final I p0() {
        return B0().p0();
    }

    public final M s0() {
        return B0().s0();
    }

    public String toString() {
        return C1283j.e.Y(this);
    }

    public final boolean u0() {
        return B0().u0();
    }
}
