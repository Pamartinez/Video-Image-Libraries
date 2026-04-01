package F2;

import D1.f;
import java.util.Map;

/* renamed from: F2.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0035p implements Map.Entry {
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            if (!f.p(getKey(), entry.getKey()) || !f.p(getValue(), entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        Object key = getKey();
        Object value = getValue();
        int i7 = 0;
        if (key == null) {
            i2 = 0;
        } else {
            i2 = key.hashCode();
        }
        if (value != null) {
            i7 = value.hashCode();
        }
        return i2 ^ i7;
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
