package a1;

import He.F;
import h1.C0204d;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends F {

    /* renamed from: a  reason: collision with root package name */
    public final Class f972a;
    public final List b = Collections.EMPTY_LIST;

    static {
        List list = Collections.EMPTY_LIST;
    }

    public a(Class cls) {
        this.f972a = cls;
        C0204d dVar = C0204d.f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        Iterator it = i1.a.f1782a;
        if (obj != null && obj.getClass() == a.class && ((a) obj).f972a == this.f972a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f972a.getName().hashCode();
    }

    public final String toString() {
        return "[AnnotedClass " + this.f972a.getName() + "]";
    }
}
