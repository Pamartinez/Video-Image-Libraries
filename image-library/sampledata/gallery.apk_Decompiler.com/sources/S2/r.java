package S2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends a {
    public final t d;
    public final t e;

    static {
        new r(new t("TYPE"), new t("Ljava/lang/Class;"));
    }

    public r(t tVar, t tVar2) {
        this.d = tVar;
        this.e = tVar2;
    }

    public final String a() {
        return this.d.a() + ':' + this.e.a();
    }

    public final int d(a aVar) {
        r rVar = (r) aVar;
        int c5 = this.d.compareTo(rVar.d);
        if (c5 != 0) {
            return c5;
        }
        return this.e.compareTo(rVar.e);
    }

    public final String e() {
        return "nat";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (!this.d.equals(rVar.d) || !this.e.equals(rVar.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.e.d.hashCode() ^ (this.d.d.hashCode() * 31);
    }

    public final String toString() {
        return "nat{" + a() + '}';
    }
}
