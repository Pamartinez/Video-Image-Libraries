package F2;

import java.util.Objects;

/* renamed from: F2.b0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0008b0 extends N {
    public final /* bridge */ /* synthetic */ N b(Object obj) {
        f(obj);
        return this;
    }

    public final void f(Object obj) {
        obj.getClass();
        a(obj);
    }

    public final C0010c0 g() {
        int i2 = this.b;
        if (i2 == 0) {
            int i7 = C0010c0.f;
            return E0.m;
        } else if (i2 != 1) {
            C0010c0 x9 = C0010c0.x(i2, this.f247a);
            this.b = x9.size();
            this.f248c = true;
            return x9;
        } else {
            Object obj = this.f247a[0];
            Objects.requireNonNull(obj);
            int i8 = C0010c0.f;
            return new L0(obj);
        }
    }
}
