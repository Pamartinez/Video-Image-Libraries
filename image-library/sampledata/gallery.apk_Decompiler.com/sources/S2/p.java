package S2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p extends v {
    public final u d;
    public final r e;

    public p(u uVar, r rVar) {
        if (uVar != null) {
            this.d = uVar;
            this.e = rVar;
            return;
        }
        throw new NullPointerException("definingClass == null");
    }

    public final String a() {
        return this.d.d.a() + '.' + this.e.a();
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            p pVar = (p) obj;
            if (!this.d.equals(pVar.d) || !this.e.equals(pVar.e)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() ^ (this.d.hashCode() * 31);
    }

    public final String toString() {
        return e() + '{' + a() + '}';
    }
}
