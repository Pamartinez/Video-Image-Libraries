package Hf;

import If.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class P {
    public abstract d0 a();

    public abstract C0774x b();

    public abstract boolean c();

    public abstract P d(f fVar);

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof P)) {
            return false;
        }
        P p6 = (P) obj;
        if (c() == p6.c() && a() == p6.a() && b().equals(p6.b())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = a().hashCode();
        if (a0.l(b())) {
            return (hashCode * 31) + 19;
        }
        int i7 = hashCode * 31;
        if (c()) {
            i2 = 17;
        } else {
            i2 = b().hashCode();
        }
        return i7 + i2;
    }

    public final String toString() {
        if (c()) {
            return "*";
        }
        if (a() == d0.INVARIANT) {
            return b().toString();
        }
        return a() + " " + b();
    }
}
