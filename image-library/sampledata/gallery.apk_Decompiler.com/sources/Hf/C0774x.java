package Hf;

import Af.p;
import If.f;
import If.m;
import Kf.d;
import Re.a;
import Re.h;
import java.util.List;

/* renamed from: Hf.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0774x implements a, d {
    public int d;

    public abstract p A();

    public abstract List e0();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0774x)) {
            return false;
        }
        C0774x xVar = (C0774x) obj;
        if (u0() != xVar.u0()) {
            return false;
        }
        if (C0754c.z(m.d, x0(), xVar.x0())) {
            return true;
        }
        return false;
    }

    public final h getAnnotations() {
        return C0760i.a(p0());
    }

    public final int hashCode() {
        int i2;
        int i7 = this.d;
        if (i7 != 0) {
            return i7;
        }
        if (C0754c.k(this)) {
            i2 = super.hashCode();
        } else {
            int hashCode = e0().hashCode();
            i2 = (u0() ? 1 : 0) + ((hashCode + (s0().hashCode() * 31)) * 31);
        }
        this.d = i2;
        return i2;
    }

    public abstract I p0();

    public abstract M s0();

    public abstract boolean u0();

    public abstract C0774x w0(f fVar);

    public abstract c0 x0();
}
