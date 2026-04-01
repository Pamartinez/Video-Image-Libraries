package F2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* renamed from: F2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0011d implements Iterator {
    public final Iterator d;
    public Object e = null;
    public Collection f = null;
    public Iterator g = C0028l0.INSTANCE;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C0009c f258h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f259i;

    public C0011d(C0009c cVar, int i2) {
        this.f259i = i2;
        this.f258h = cVar;
        this.d = cVar.f256h.entrySet().iterator();
    }

    public final boolean hasNext() {
        if (this.d.hasNext() || this.g.hasNext()) {
            return true;
        }
        return false;
    }

    public final Object next() {
        if (!this.g.hasNext()) {
            Map.Entry entry = (Map.Entry) this.d.next();
            this.e = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.f = collection;
            this.g = collection.iterator();
        }
        Object obj = this.e;
        Object next = this.g.next();
        switch (this.f259i) {
            case 0:
                return next;
            default:
                return new P(obj, next);
        }
    }

    public final void remove() {
        this.g.remove();
        Collection collection = this.f;
        Objects.requireNonNull(collection);
        if (collection.isEmpty()) {
            this.d.remove();
        }
        C0009c cVar = this.f258h;
        cVar.f257i--;
    }
}
