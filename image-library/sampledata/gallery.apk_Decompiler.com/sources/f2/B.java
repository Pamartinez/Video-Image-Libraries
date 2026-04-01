package F2;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends w0 implements Serializable {
    public final Comparator d;

    public B(Comparator comparator) {
        comparator.getClass();
        this.d = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        return this.d.compare(obj, obj2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof B) {
            return this.d.equals(((B) obj).d);
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return this.d.toString();
    }
}
