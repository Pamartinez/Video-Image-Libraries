package Ze;

import Af.g;
import L1.d;
import java.util.Map;
import kotlin.jvm.internal.j;
import ne.C1203u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v {

    /* renamed from: a  reason: collision with root package name */
    public final C f3960a;
    public final C b;

    /* renamed from: c  reason: collision with root package name */
    public final Map f3961c = C1203u.d;
    public final boolean d;

    public v(C c5, C c6) {
        boolean z;
        this.f3960a = c5;
        this.b = c6;
        d.q(new g(25, this));
        C c8 = C.IGNORE;
        if (c5 == c8 && c6 == c8) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        if (this.f3960a == vVar.f3960a && this.b == vVar.b && j.a(this.f3961c, vVar.f3961c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.f3960a.hashCode() * 31;
        C c5 = this.b;
        if (c5 == null) {
            i2 = 0;
        } else {
            i2 = c5.hashCode();
        }
        return this.f3961c.hashCode() + ((hashCode + i2) * 31);
    }

    public final String toString() {
        return "Jsr305Settings(globalLevel=" + this.f3960a + ", migrationLevel=" + this.b + ", userDefinedLevelForSpecificAnnotation=" + this.f3961c + ')';
    }
}
