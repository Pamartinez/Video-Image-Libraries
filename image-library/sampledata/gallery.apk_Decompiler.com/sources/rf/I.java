package rf;

import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class I implements Iterator {
    public int d = -1;
    public boolean e;
    public Iterator f;
    public final /* synthetic */ D g;

    public I(D d2) {
        this.g = d2;
    }

    public final Iterator a() {
        if (this.f == null) {
            this.f = this.g.f.entrySet().iterator();
        }
        return this.f;
    }

    public final boolean hasNext() {
        if (this.d + 1 < this.g.e.size() || a().hasNext()) {
            return true;
        }
        return false;
    }

    public final Object next() {
        this.e = true;
        int i2 = this.d + 1;
        this.d = i2;
        D d2 = this.g;
        if (i2 < d2.e.size()) {
            return (Map.Entry) d2.e.get(this.d);
        }
        return (Map.Entry) a().next();
    }

    public final void remove() {
        if (this.e) {
            this.e = false;
            int i2 = D.f5053i;
            D d2 = this.g;
            d2.b();
            if (this.d < d2.e.size()) {
                int i7 = this.d;
                this.d = i7 - 1;
                d2.f(i7);
                return;
            }
            a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
