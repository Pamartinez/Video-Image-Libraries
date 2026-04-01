package F2;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Y implements Map, Serializable {
    public static final Map.Entry[] g = new Map.Entry[0];
    public transient C0010c0 d;
    public transient C0010c0 e;
    public transient O f;

    public static Y a(Map map) {
        int i2;
        if ((map instanceof Y) && !(map instanceof SortedMap)) {
            Y y = (Y) map;
            if (!y.f()) {
                return y;
            }
        }
        Set<Map.Entry> entrySet = map.entrySet();
        if (entrySet != null) {
            i2 = entrySet.size();
        } else {
            i2 = 4;
        }
        X x9 = new X(i2);
        if (entrySet != null) {
            int size = (entrySet.size() + x9.b) * 2;
            Object[] objArr = x9.f254a;
            if (size > objArr.length) {
                x9.f254a = Arrays.copyOf(objArr, N.e(objArr.length, size));
            }
        }
        for (Map.Entry entry : entrySet) {
            x9.b(entry.getKey(), entry.getValue());
        }
        return x9.a();
    }

    public abstract C0010c0 b();

    public abstract C0010c0 c();

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public abstract O d();

    /* renamed from: e */
    public C0010c0 entrySet() {
        C0010c0 c0Var = this.d;
        if (c0Var != null) {
            return c0Var;
        }
        C0010c0 b = b();
        this.d = b;
        return b;
    }

    public final boolean equals(Object obj) {
        return C0040v.e(obj, this);
    }

    public abstract boolean f();

    /* renamed from: g */
    public C0010c0 keySet() {
        C0010c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var;
        }
        C0010c0 c5 = c();
        this.e = c5;
        return c5;
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 != null) {
            return obj3;
        }
        return obj2;
    }

    /* renamed from: h */
    public O values() {
        O o2 = this.f;
        if (o2 != null) {
            return o2;
        }
        O d2 = d();
        this.f = d2;
        return d2;
    }

    public final int hashCode() {
        return C0040v.j(entrySet());
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        C0040v.c(size, "size");
        StringBuilder sb2 = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
        sb2.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
            z = false;
        }
        sb2.append('}');
        return sb2.toString();
    }
}
