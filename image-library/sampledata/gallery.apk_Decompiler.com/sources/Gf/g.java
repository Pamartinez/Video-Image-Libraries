package Gf;

import Ae.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final Object f3413a;
    public final a b;

    public g(Object obj, a aVar) {
        this.f3413a = obj;
        this.b = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass() || !this.f3413a.equals(((g) obj).f3413a)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f3413a.hashCode();
    }
}
