package ge;

import B1.a;
import E2.k;
import He.F;
import java.util.Arrays;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f4432a;
    public final Map b;

    public G1(String str, Map map) {
        F.n(str, "policyName");
        this.f4432a = str;
        F.n(map, "rawConfigValue");
        this.b = map;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof G1) {
            G1 g12 = (G1) obj;
            if (!this.f4432a.equals(g12.f4432a) || !this.b.equals(g12.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4432a, this.b});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4432a, "policyName");
        V.a(this.b, "rawConfigValue");
        return V.toString();
    }
}
