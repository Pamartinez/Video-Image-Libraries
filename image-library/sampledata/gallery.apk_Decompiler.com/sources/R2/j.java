package R2;

import T2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public int f651a;
    public d b;

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        int i2 = this.f651a;
        d dVar = this.b;
        if (kVar.d != i2 || !kVar.e.equals(dVar)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (this.b.hashCode() * 31) + this.f651a;
    }
}
