package Ze;

import kotlin.jvm.internal.j;
import me.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {
    public static final r d = new r(C.STRICT, 6);

    /* renamed from: a  reason: collision with root package name */
    public final C f3955a;
    public final e b;

    /* renamed from: c  reason: collision with root package name */
    public final C f3956c;

    public r(C c5, e eVar, C c6) {
        j.e(c5, "reportLevelBefore");
        j.e(c6, "reportLevelAfter");
        this.f3955a = c5;
        this.b = eVar;
        this.f3956c = c6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (this.f3955a == rVar.f3955a && j.a(this.b, rVar.b) && this.f3956c == rVar.f3956c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.f3955a.hashCode() * 31;
        e eVar = this.b;
        if (eVar == null) {
            i2 = 0;
        } else {
            i2 = eVar.g;
        }
        return this.f3956c.hashCode() + ((hashCode + i2) * 31);
    }

    public final String toString() {
        return "JavaNullabilityAnnotationsStatus(reportLevelBefore=" + this.f3955a + ", sinceVersion=" + this.b + ", reportLevelAfter=" + this.f3956c + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public r(C c5, int i2) {
        this(c5, (i2 & 2) != 0 ? new e(1, 0, 0) : null, c5);
    }
}
