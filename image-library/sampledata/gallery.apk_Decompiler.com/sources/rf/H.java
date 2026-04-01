package rf;

import N2.j;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H implements Comparable, Map.Entry {
    public final Comparable d;
    public Object e;
    public final /* synthetic */ D f;

    public H(D d2, Comparable comparable, Object obj) {
        this.f = d2;
        this.d = comparable;
        this.e = obj;
    }

    public final int compareTo(Object obj) {
        return this.d.compareTo(((H) obj).d);
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z3;
        if (obj != this) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Comparable comparable = this.d;
                if (comparable != null) {
                    z = comparable.equals(key);
                } else if (key == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    Object obj2 = this.e;
                    Object value = entry.getValue();
                    if (obj2 != null) {
                        z3 = obj2.equals(value);
                    } else if (value == null) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final Object getKey() {
        return this.d;
    }

    public final Object getValue() {
        return this.e;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        Comparable comparable = this.d;
        if (comparable == null) {
            i2 = 0;
        } else {
            i2 = comparable.hashCode();
        }
        Object obj = this.e;
        if (obj != null) {
            i7 = obj.hashCode();
        }
        return i2 ^ i7;
    }

    public final Object setValue(Object obj) {
        this.f.b();
        Object obj2 = this.e;
        this.e = obj;
        return obj2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.d);
        String valueOf2 = String.valueOf(this.e);
        return j.f(new StringBuilder(valueOf2.length() + valueOf.length() + 1), valueOf, "=", valueOf2);
    }
}
