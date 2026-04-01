package F2;

import He.F;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* renamed from: F2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0009c extends r implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public final transient Map f256h;

    /* renamed from: i  reason: collision with root package name */
    public transient int f257i;

    public C0009c(Map map) {
        F.j(map.isEmpty());
        this.f256h = map;
    }

    public final Collection a() {
        Collection collection = this.d;
        if (collection != null) {
            return collection;
        }
        C0036q qVar = new C0036q(this, 0);
        this.d = qVar;
        return qVar;
    }

    public final Map b() {
        Map map = this.g;
        if (map != null) {
            return map;
        }
        Map e = e();
        this.g = e;
        return e;
    }

    public final void d() {
        Map map = this.f256h;
        for (Collection clear : map.values()) {
            clear.clear();
        }
        map.clear();
        this.f257i = 0;
    }

    public Map e() {
        return new C0017g(this, this.f256h);
    }

    public abstract Collection f();

    public Set g() {
        return new C0019h(this, this.f256h);
    }

    public final Collection get(Object obj) {
        Collection collection = (Collection) this.f256h.get(obj);
        if (collection == null) {
            collection = f();
        }
        List list = (List) collection;
        if (list instanceof RandomAccess) {
            return new C0033o(this, obj, list, (C0033o) null);
        }
        return new C0033o(this, obj, list, (C0033o) null);
    }

    public final boolean put(Object obj, Object obj2) {
        Map map = this.f256h;
        Collection collection = (Collection) map.get(obj);
        if (collection == null) {
            Collection f = f();
            if (f.add(obj2)) {
                this.f257i++;
                map.put(obj, f);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(obj2)) {
            return false;
        } else {
            this.f257i++;
            return true;
        }
    }
}
