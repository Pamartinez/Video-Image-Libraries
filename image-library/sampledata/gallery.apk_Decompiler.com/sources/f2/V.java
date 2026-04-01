package F2;

import java.io.Serializable;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class V extends C0038t implements Serializable {

    /* renamed from: h  reason: collision with root package name */
    public final transient D0 f250h;

    /* renamed from: i  reason: collision with root package name */
    public final transient int f251i;

    public V(D0 d0, int i2) {
        this.f250h = d0;
        this.f251i = i2;
    }

    public final Collection a() {
        Collection collection = this.d;
        if (collection == null) {
            collection = new C0006a0(this);
            this.d = collection;
        }
        return (O) collection;
    }

    public final boolean c(Object obj) {
        if (obj == null || !super.c(obj)) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    public Y b() {
        return this.f250h;
    }

    public final Collection get(Object obj) {
        U u = (U) this.f250h.get(obj);
        if (u != null) {
            return u;
        }
        G g = U.e;
        return y0.f278h;
    }

    public final boolean put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }
}
