package F2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: F2.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0019h extends K0 {
    public final Map d;
    public final /* synthetic */ C0009c e;

    public C0019h(C0009c cVar, Map map) {
        this.e = cVar;
        map.getClass();
        this.d = map;
    }

    public final void clear() {
        Iterator it = iterator();
        while (true) {
            C0015f fVar = (C0015f) it;
            if (fVar.hasNext()) {
                fVar.next();
                fVar.remove();
            } else {
                return;
            }
        }
    }

    public final boolean contains(Object obj) {
        return this.d.containsKey(obj);
    }

    public final boolean containsAll(Collection collection) {
        return this.d.keySet().containsAll(collection);
    }

    public final boolean equals(Object obj) {
        if (this == obj || this.d.keySet().equals(obj)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.keySet().hashCode();
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        return new C0015f(this, this.d.entrySet().iterator());
    }

    public final boolean remove(Object obj) {
        int i2;
        Collection collection = (Collection) this.d.remove(obj);
        if (collection != null) {
            i2 = collection.size();
            collection.clear();
            this.e.f257i -= i2;
        } else {
            i2 = 0;
        }
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public final int size() {
        return this.d.size();
    }
}
