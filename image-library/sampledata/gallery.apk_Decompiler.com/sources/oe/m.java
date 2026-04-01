package Oe;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final l f3623a;
    public final int b;

    public m(l lVar, int i2) {
        this.f3623a = lVar;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (j.a(this.f3623a, mVar.f3623a) && this.b == mVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.f3623a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("KindWithArity(kind=");
        sb2.append(this.f3623a);
        sb2.append(", arity=");
        return N2.j.e(sb2, this.b, ')');
    }
}
