package E2;

import java.io.Serializable;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements l, Serializable {
    public final List d;

    public m(List list) {
        this.d = list;
    }

    public final boolean apply(Object obj) {
        int i2 = 0;
        while (true) {
            List list = this.d;
            if (i2 >= list.size()) {
                return true;
            }
            if (!((l) list.get(i2)).apply(obj)) {
                return false;
            }
            i2++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return this.d.equals(((m) obj).d);
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + 306654252;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Predicates.and(");
        boolean z = true;
        for (Object next : this.d) {
            if (!z) {
                sb2.append(',');
            }
            sb2.append(next);
            z = false;
        }
        sb2.append(')');
        return sb2.toString();
    }
}
