package E2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements r {
    public static final t g = new t(0);
    public final Object d = new Object();
    public volatile r e;
    public Object f;

    public u(r rVar) {
        this.e = rVar;
    }

    public final Object get() {
        r rVar = this.e;
        t tVar = g;
        if (rVar != tVar) {
            synchronized (this.d) {
                try {
                    if (this.e != tVar) {
                        Object obj = this.e.get();
                        this.f = obj;
                        this.e = tVar;
                        return obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.f;
    }

    public final String toString() {
        Object obj = this.e;
        StringBuilder sb2 = new StringBuilder("Suppliers.memoize(");
        if (obj == g) {
            obj = "<supplier that returned " + this.f + ">";
        }
        sb2.append(obj);
        sb2.append(")");
        return sb2.toString();
    }
}
