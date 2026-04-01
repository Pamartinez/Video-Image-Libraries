package ne;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x {

    /* renamed from: a  reason: collision with root package name */
    public final int f4950a;
    public final Object b;

    public x(int i2, Object obj) {
        this.f4950a = i2;
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        if (this.f4950a == xVar.f4950a && j.a(this.b, xVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = Integer.hashCode(this.f4950a) * 31;
        Object obj = this.b;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        return hashCode + i2;
    }

    public final String toString() {
        return "IndexedValue(index=" + this.f4950a + ", value=" + this.b + ')';
    }
}
