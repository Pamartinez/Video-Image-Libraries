package F2;

import D1.f;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import rf.D;
import rf.I;

/* renamed from: F2.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0042x extends AbstractSet {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbstractMap e;

    public /* synthetic */ C0042x(AbstractMap abstractMap, int i2) {
        this.d = i2;
        this.e = abstractMap;
    }

    public boolean add(Object obj) {
        switch (this.d) {
            case 2:
                Map.Entry entry = (Map.Entry) obj;
                if (contains(entry)) {
                    return false;
                }
                ((D) this.e).put((Comparable) entry.getKey(), entry.getValue());
                return true;
            default:
                return super.add(obj);
        }
    }

    public final void clear() {
        switch (this.d) {
            case 0:
                ((A) this.e).clear();
                return;
            case 1:
                ((A) this.e).clear();
                return;
            default:
                ((D) this.e).clear();
                return;
        }
    }

    public final boolean contains(Object obj) {
        switch (this.d) {
            case 0:
                A a7 = (A) this.e;
                Map c5 = a7.c();
                if (c5 != null) {
                    return c5.entrySet().contains(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    int e7 = a7.e(entry.getKey());
                    if (e7 != -1 && f.p(a7.l()[e7], entry.getValue())) {
                        return true;
                    }
                }
                return false;
            case 1:
                return ((A) this.e).containsKey(obj);
            default:
                Map.Entry entry2 = (Map.Entry) obj;
                Object obj2 = ((D) this.e).get(entry2.getKey());
                Object value = entry2.getValue();
                if (obj2 == value || (obj2 != null && obj2.equals(value))) {
                    return true;
                }
                return false;
        }
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                A a7 = (A) this.e;
                Map c5 = a7.c();
                if (c5 != null) {
                    return c5.entrySet().iterator();
                }
                return new C0041w(a7, 1);
            case 1:
                A a10 = (A) this.e;
                Map c6 = a10.c();
                if (c6 != null) {
                    return c6.keySet().iterator();
                }
                return new C0041w(a10, 0);
            default:
                return new I((D) this.e);
        }
    }

    public final boolean remove(Object obj) {
        switch (this.d) {
            case 0:
                A a7 = (A) this.e;
                Map c5 = a7.c();
                if (c5 != null) {
                    return c5.entrySet().remove(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    if (!a7.g()) {
                        int d2 = a7.d();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        Object obj2 = a7.d;
                        Objects.requireNonNull(obj2);
                        int n = C0040v.n(key, value, d2, obj2, a7.j(), a7.k(), a7.l());
                        if (n != -1) {
                            a7.f(n, d2);
                            a7.f229i--;
                            a7.f228h += 32;
                            return true;
                        }
                    }
                }
                return false;
            case 1:
                A a10 = (A) this.e;
                Map c6 = a10.c();
                if (c6 != null) {
                    return c6.keySet().remove(obj);
                }
                if (a10.h(obj) != A.m) {
                    return true;
                }
                return false;
            default:
                Map.Entry entry2 = (Map.Entry) obj;
                if (!contains(entry2)) {
                    return false;
                }
                ((D) this.e).remove(entry2.getKey());
                return true;
        }
    }

    public final int size() {
        switch (this.d) {
            case 0:
                return ((A) this.e).size();
            case 1:
                return ((A) this.e).size();
            default:
                return ((D) this.e).size();
        }
    }
}
