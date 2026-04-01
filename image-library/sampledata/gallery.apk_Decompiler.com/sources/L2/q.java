package L2;

import S2.r;
import S2.t;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public final r f407a;
    public final r b;

    /* renamed from: c  reason: collision with root package name */
    public final String f408c;
    public final s d;
    public final S2.q e;

    public q(r rVar, r rVar2, String str, s sVar) {
        if (rVar2 == null || str == null) {
            throw null;
        }
        this.f407a = rVar;
        this.b = rVar2;
        this.f408c = str;
        this.d = sVar;
        this.e = new S2.q(rVar.f413c, new r(new t(str), new t(a(false))));
    }

    public final String a(boolean z) {
        StringBuilder sb2 = new StringBuilder("(");
        if (z) {
            sb2.append(this.f407a.f412a);
        }
        for (r rVar : this.d.f414a) {
            sb2.append(rVar.f412a);
        }
        sb2.append(")");
        sb2.append(this.b.f412a);
        return sb2.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        if (!qVar.f407a.equals(this.f407a) || !qVar.f408c.equals(this.f408c) || !qVar.d.equals(this.d) || !qVar.b.equals(this.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int d2 = C0212a.d(C0212a.d(527, 31, this.f407a.f412a), 31, this.f408c);
        return this.b.f412a.hashCode() + ((Arrays.hashCode(this.d.f414a) + d2) * 31);
    }

    public final String toString() {
        return this.f407a + "." + this.f408c + "(" + this.d + ")";
    }
}
