package F2;

import He.F;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y0 extends U {

    /* renamed from: h  reason: collision with root package name */
    public static final y0 f278h = new y0(0, new Object[0]);
    public final transient Object[] f;
    public final transient int g;

    public y0(int i2, Object[] objArr) {
        this.f = objArr;
        this.g = i2;
    }

    public final Object get(int i2) {
        F.m(i2, this.g);
        Object obj = this.f[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int q(int i2, Object[] objArr) {
        Object[] objArr2 = this.f;
        int i7 = this.g;
        System.arraycopy(objArr2, 0, objArr, i2, i7);
        return i2 + i7;
    }

    public final Object[] r() {
        return this.f;
    }

    public final int s() {
        return this.g;
    }

    public final int size() {
        return this.g;
    }

    public final int t() {
        return 0;
    }

    public final boolean u() {
        return false;
    }
}
