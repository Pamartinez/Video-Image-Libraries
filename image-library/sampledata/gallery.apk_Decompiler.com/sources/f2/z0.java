package F2;

import He.F;
import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z0 extends U {
    public final /* synthetic */ A0 f;

    public z0(A0 a0) {
        this.f = a0;
    }

    public final Object get(int i2) {
        A0 a0 = this.f;
        F.m(i2, a0.f233j);
        Object[] objArr = a0.f231h;
        int i7 = i2 * 2;
        int i8 = a0.f232i;
        Object obj = objArr[i7 + i8];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[i7 + (i8 ^ 1)];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.f.f233j;
    }

    public final boolean u() {
        return true;
    }
}
