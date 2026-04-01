package Ve;

import Tf.v;
import ee.P;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Class f3829a;
    public final P b;

    public b(Class cls, P p6) {
        this.f3829a = cls;
        this.b = p6;
    }

    public final String a() {
        return v.r0(this.f3829a.getName(), '.', '/').concat(".class");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        if (j.a(this.f3829a, ((b) obj).f3829a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3829a.hashCode();
    }

    public final String toString() {
        return b.class.getName() + ": " + this.f3829a;
    }
}
