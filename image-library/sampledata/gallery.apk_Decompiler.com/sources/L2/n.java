package L2;

import S2.h;
import S2.p;
import S2.r;
import S2.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    public final r f401a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final h f402c;

    /* JADX WARNING: type inference failed for: r4v2, types: [S2.p, S2.h] */
    public n(r rVar, r rVar2, String str) {
        if (rVar2 != null) {
            this.f401a = rVar;
            this.b = str;
            this.f402c = new p(rVar.f413c, new r(new t(str), new t(rVar2.f412a)));
            return;
        }
        throw null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (!nVar.f401a.equals(this.f401a) || !nVar.b.equals(this.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (this.b.hashCode() * 37) + this.f401a.f412a.hashCode();
    }

    public final String toString() {
        return this.f401a + "." + this.b;
    }
}
