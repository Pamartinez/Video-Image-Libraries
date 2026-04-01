package rf;

import F2.C0042x;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends AbstractMap {

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ int f5053i = 0;
    public final int d;
    public List e = Collections.EMPTY_LIST;
    public Map f = Collections.EMPTY_MAP;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public volatile C0042x f5054h;

    public D(int i2) {
        this.d = i2;
    }

    public final int a(Comparable comparable) {
        int i2;
        int size = this.e.size();
        int i7 = size - 1;
        if (i7 >= 0) {
            int compareTo = comparable.compareTo(((H) this.e.get(i7)).d);
            if (compareTo > 0) {
                i2 = size + 1;
                return -i2;
            } else if (compareTo == 0) {
                return i7;
            }
        }
        int i8 = 0;
        while (i8 <= i7) {
            int i10 = (i8 + i7) / 2;
            int compareTo2 = comparable.compareTo(((H) this.e.get(i10)).d);
            if (compareTo2 < 0) {
                i7 = i10 - 1;
            } else if (compareTo2 <= 0) {
                return i10;
            } else {
                i8 = i10 + 1;
            }
        }
        i2 = i8 + 1;
        return -i2;
    }

    public final void b() {
        if (this.g) {
            throw new UnsupportedOperationException();
        }
    }

    public final Iterable c() {
        if (this.f.isEmpty()) {
            return G.b;
        }
        return this.f.entrySet();
    }

    public final void clear() {
        b();
        if (!this.e.isEmpty()) {
            this.e.clear();
        }
        if (!this.f.isEmpty()) {
            this.f.clear();
        }
    }

    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        if (a(comparable) >= 0 || this.f.containsKey(comparable)) {
            return true;
        }
        return false;
    }

    public final SortedMap d() {
        b();
        if (this.f.isEmpty() && !(this.f instanceof TreeMap)) {
            this.f = new TreeMap();
        }
        return (SortedMap) this.f;
    }

    /* renamed from: e */
    public final Object put(Comparable comparable, Object obj) {
        b();
        int a7 = a(comparable);
        if (a7 >= 0) {
            return ((H) this.e.get(a7)).setValue(obj);
        }
        b();
        boolean isEmpty = this.e.isEmpty();
        int i2 = this.d;
        if (isEmpty && !(this.e instanceof ArrayList)) {
            this.e = new ArrayList(i2);
        }
        int i7 = -(a7 + 1);
        if (i7 >= i2) {
            return d().put(comparable, obj);
        }
        if (this.e.size() == i2) {
            H h5 = (H) this.e.remove(i2 - 1);
            d().put(h5.d, h5.e);
        }
        this.e.add(i7, new H(this, comparable, obj));
        return null;
    }

    public final Set entrySet() {
        if (this.f5054h == null) {
            this.f5054h = new C0042x(this, 2);
        }
        return this.f5054h;
    }

    public final Object f(int i2) {
        b();
        Object obj = ((H) this.e.remove(i2)).e;
        if (!this.f.isEmpty()) {
            Iterator it = d().entrySet().iterator();
            List list = this.e;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new H(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int a7 = a(comparable);
        if (a7 >= 0) {
            return ((H) this.e.get(a7)).e;
        }
        return this.f.get(comparable);
    }

    public final Object remove(Object obj) {
        b();
        Comparable comparable = (Comparable) obj;
        int a7 = a(comparable);
        if (a7 >= 0) {
            return f(a7);
        }
        if (this.f.isEmpty()) {
            return null;
        }
        return this.f.remove(comparable);
    }

    public final int size() {
        return this.f.size() + this.e.size();
    }
}
