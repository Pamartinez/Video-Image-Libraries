package F2;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: F2.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0036q extends AbstractCollection {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0009c e;

    public /* synthetic */ C0036q(C0009c cVar, int i2) {
        this.d = i2;
        this.e = cVar;
    }

    public final void clear() {
        switch (this.d) {
            case 0:
                this.e.d();
                return;
            default:
                this.e.d();
                return;
        }
    }

    public final boolean contains(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                Collection collection = (Collection) this.e.b().get(key);
                if (collection == null || !collection.contains(value)) {
                    return false;
                }
                return true;
            default:
                return this.e.c(obj);
        }
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                return new C0011d(this.e, 1);
            default:
                return new C0011d(this.e, 0);
        }
    }

    public boolean remove(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return this.e.remove(entry.getKey(), entry.getValue());
            default:
                return super.remove(obj);
        }
    }

    public final int size() {
        switch (this.d) {
            case 0:
                return this.e.f257i;
            default:
                return this.e.f257i;
        }
    }
}
