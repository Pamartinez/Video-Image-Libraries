package F2;

import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z extends O0 {
    public final O0 d;
    public Object e = null;
    public O0 f = C0024j0.g;

    public Z(V v) {
        this.d = v.f250h.entrySet().iterator();
    }

    public final boolean hasNext() {
        if (this.f.hasNext() || this.d.hasNext()) {
            return true;
        }
        return false;
    }

    public final Object next() {
        if (!this.f.hasNext()) {
            Map.Entry entry = (Map.Entry) this.d.next();
            this.e = entry.getKey();
            this.f = ((O) entry.getValue()).iterator();
        }
        Object obj = this.e;
        Objects.requireNonNull(obj);
        return new P(obj, this.f.next());
    }
}
