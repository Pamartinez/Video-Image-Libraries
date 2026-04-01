package Hf;

import Ae.b;
import Af.p;
import If.f;
import Jf.g;
import Jf.m;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends B {
    public final M e;
    public final List f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final p f3423h;

    /* renamed from: i  reason: collision with root package name */
    public final b f3424i;

    public C(M m, List list, boolean z, p pVar, b bVar) {
        j.e(m, "constructor");
        j.e(list, "arguments");
        j.e(pVar, "memberScope");
        this.e = m;
        this.f = list;
        this.g = z;
        this.f3423h = pVar;
        this.f3424i = bVar;
        if ((pVar instanceof g) && !(pVar instanceof m)) {
            throw new IllegalStateException("SimpleTypeImpl should not be created for error type: " + pVar + 10 + m);
        }
    }

    public final p A() {
        return this.f3423h;
    }

    public final B B0(boolean z) {
        if (z == this.g) {
            return this;
        }
        if (z) {
            return new A(this, 1);
        }
        return new A(this, 0);
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        if (i2.isEmpty()) {
            return this;
        }
        return new D(this, i2);
    }

    public final List e0() {
        return this.f;
    }

    public final I p0() {
        I.e.getClass();
        return I.f;
    }

    public final M s0() {
        return this.e;
    }

    public final boolean u0() {
        return this.g;
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = (B) this.f3424i.invoke(fVar);
        if (b == null) {
            return this;
        }
        return b;
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        B b = (B) this.f3424i.invoke(fVar);
        if (b == null) {
            return this;
        }
        return b;
    }
}
