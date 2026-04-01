package F2;

import java.util.Collection;
import java.util.Map;

/* renamed from: F2.a0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0006a0 extends O {
    public final V e;

    public C0006a0(V v) {
        this.e = v;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            Collection collection = (Collection) this.e.b().get(key);
            if (collection == null || !collection.contains(value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int size() {
        return this.e.f251i;
    }

    public final O0 v() {
        V v = this.e;
        v.getClass();
        return new Z(v);
    }
}
