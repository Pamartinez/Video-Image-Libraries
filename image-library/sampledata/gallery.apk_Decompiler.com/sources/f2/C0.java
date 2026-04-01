package F2;

import He.F;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0 extends U {
    public final transient Object[] f;
    public final transient int g;

    /* renamed from: h  reason: collision with root package name */
    public final transient int f235h;

    public C0(Object[] objArr, int i2, int i7) {
        this.f = objArr;
        this.g = i2;
        this.f235h = i7;
    }

    public final Object get(int i2) {
        F.m(i2, this.f235h);
        Object obj = this.f[(i2 * 2) + this.g];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int size() {
        return this.f235h;
    }

    public final boolean u() {
        return true;
    }
}
