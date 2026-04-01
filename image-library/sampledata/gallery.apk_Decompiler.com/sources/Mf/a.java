package Mf;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f3535a;
    public final Object b;

    public a(Object obj, Object obj2) {
        this.f3535a = obj;
        this.b = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (j.a(this.f3535a, aVar.f3535a) && j.a(this.b, aVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        Object obj = this.f3535a;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        int i8 = i2 * 31;
        Object obj2 = this.b;
        if (obj2 != null) {
            i7 = obj2.hashCode();
        }
        return i8 + i7;
    }

    public final String toString() {
        return "ApproximationBounds(lower=" + this.f3535a + ", upper=" + this.b + ')';
    }
}
