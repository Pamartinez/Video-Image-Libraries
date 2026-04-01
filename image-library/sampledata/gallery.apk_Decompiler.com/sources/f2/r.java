package F2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class r implements r0 {
    public transient Collection d;
    public transient Set e;
    public transient Collection f;
    public transient Map g;

    public boolean c(Object obj) {
        for (Collection contains : b().values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r0) {
            return b().equals(((r0) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return b().hashCode();
    }

    public boolean remove(Object obj, Object obj2) {
        Collection collection = (Collection) b().get(obj);
        if (collection == null || !collection.remove(obj2)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return b().toString();
    }
}
