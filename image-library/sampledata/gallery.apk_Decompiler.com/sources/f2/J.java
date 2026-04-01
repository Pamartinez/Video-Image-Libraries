package F2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class J extends K implements Map {
    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(Object obj) {
        return delegate().containsKey(obj);
    }

    public abstract Map delegate();

    public Set entrySet() {
        return delegate().entrySet();
    }

    public Object get(Object obj) {
        return delegate().get(obj);
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Set keySet() {
        return delegate().keySet();
    }

    public Object put(Object obj, Object obj2) {
        return delegate().put(obj, obj2);
    }

    public void putAll(Map<Object, Object> map) {
        delegate().putAll(map);
    }

    public Object remove(Object obj) {
        return delegate().remove(obj);
    }

    public int size() {
        return delegate().size();
    }

    public boolean standardContainsValue(Object obj) {
        N0 n02 = new N0(entrySet().iterator());
        if (obj == null) {
            while (n02.hasNext()) {
                if (n02.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (n02.hasNext()) {
            if (obj.equals(n02.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean standardEquals(Object obj) {
        return C0040v.e(obj, this);
    }

    public int standardHashCode() {
        return C0040v.j(entrySet());
    }

    public Collection<Object> values() {
        return delegate().values();
    }
}
