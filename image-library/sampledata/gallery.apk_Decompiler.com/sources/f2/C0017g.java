package F2;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* renamed from: F2.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0017g extends AbstractMap {
    public transient C0013e d;
    public transient C0044z e;
    public final transient Map f;
    public final /* synthetic */ C0009c g;

    public C0017g(C0009c cVar, Map map) {
        this.g = cVar;
        this.f = map;
    }

    public final P a(Map.Entry entry) {
        C0033o oVar;
        Object key = entry.getKey();
        List list = (List) ((Collection) entry.getValue());
        boolean z = list instanceof RandomAccess;
        C0009c cVar = this.g;
        if (z) {
            oVar = new C0033o(cVar, key, list, (C0033o) null);
        } else {
            oVar = new C0033o(cVar, key, list, (C0033o) null);
        }
        return new P(key, oVar);
    }

    public final void clear() {
        C0009c cVar = this.g;
        if (this.f == cVar.f256h) {
            cVar.d();
            return;
        }
        C0015f fVar = new C0015f(this);
        while (fVar.hasNext()) {
            fVar.next();
            fVar.remove();
        }
    }

    public final boolean containsKey(Object obj) {
        Map map = this.f;
        map.getClass();
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final Set entrySet() {
        C0013e eVar = this.d;
        if (eVar != null) {
            return eVar;
        }
        C0013e eVar2 = new C0013e(this);
        this.d = eVar2;
        return eVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj || this.f.equals(obj)) {
            return true;
        }
        return false;
    }

    public final Object get(Object obj) {
        Object obj2;
        C0033o oVar;
        Map map = this.f;
        map.getClass();
        try {
            obj2 = map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection == null) {
            return null;
        }
        List list = (List) collection;
        boolean z = list instanceof RandomAccess;
        C0009c cVar = this.g;
        if (z) {
            oVar = new C0033o(cVar, obj, list, (C0033o) null);
        } else {
            oVar = new C0033o(cVar, obj, list, (C0033o) null);
        }
        return oVar;
    }

    public final int hashCode() {
        return this.f.hashCode();
    }

    public Set keySet() {
        C0009c cVar = this.g;
        Set set = cVar.e;
        if (set != null) {
            return set;
        }
        Set g3 = cVar.g();
        cVar.e = g3;
        return g3;
    }

    public final Object remove(Object obj) {
        Collection collection = (Collection) this.f.remove(obj);
        if (collection == null) {
            return null;
        }
        C0009c cVar = this.g;
        Collection f5 = cVar.f();
        f5.addAll(collection);
        cVar.f257i -= collection.size();
        collection.clear();
        return f5;
    }

    public final int size() {
        return this.f.size();
    }

    public final String toString() {
        return this.f.toString();
    }

    public final Collection values() {
        C0044z zVar = this.e;
        if (zVar != null) {
            return zVar;
        }
        C0044z zVar2 = new C0044z(this);
        this.e = zVar2;
        return zVar2;
    }
}
