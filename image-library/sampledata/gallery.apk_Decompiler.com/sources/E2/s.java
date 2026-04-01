package E2;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements r, Serializable {
    public final transient Object d = new Object();
    public final r e;
    public volatile transient boolean f;
    public transient Object g;

    public s(r rVar) {
        this.e = rVar;
    }

    public final Object get() {
        if (!this.f) {
            synchronized (this.d) {
                try {
                    if (!this.f) {
                        Object obj = this.e.get();
                        this.g = obj;
                        this.f = true;
                        return obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.g;
    }

    public final String toString() {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Suppliers.memoize(");
        if (this.f) {
            obj = "<supplier that returned " + this.g + ">";
        } else {
            obj = this.e;
        }
        sb2.append(obj);
        sb2.append(")");
        return sb2.toString();
    }
}
