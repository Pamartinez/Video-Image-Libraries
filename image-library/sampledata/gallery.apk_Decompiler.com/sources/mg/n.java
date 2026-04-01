package mg;

import ig.f;
import java.util.List;
import kotlin.jvm.internal.j;
import lg.C1174b;
import lg.l;
import lg.m;
import lg.s;
import lg.y;
import ne.C1194l;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends l {
    public final y m;
    public final List n;

    /* renamed from: o  reason: collision with root package name */
    public final int f4938o;

    /* renamed from: p  reason: collision with root package name */
    public int f4939p = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(C1174b bVar, y yVar) {
        super(bVar, yVar, (String) null, (f) null);
        j.e(bVar, "json");
        this.m = yVar;
        List k12 = C1194l.k1(yVar.d.keySet());
        this.n = k12;
        this.f4938o = k12.size() * 2;
    }

    public final String Q(f fVar, int i2) {
        j.e(fVar, "descriptor");
        return (String) this.n.get(i2 / 2);
    }

    public final l T() {
        return this.m;
    }

    public final y W() {
        return this.m;
    }

    public final void b(f fVar) {
        j.e(fVar, "descriptor");
    }

    public final int d(f fVar) {
        j.e(fVar, "descriptor");
        int i2 = this.f4939p;
        if (i2 >= this.f4938o - 1) {
            return -1;
        }
        int i7 = i2 + 1;
        this.f4939p = i7;
        return i7;
    }

    public final l i(String str) {
        j.e(str, "tag");
        if (this.f4939p % 2 != 0) {
            return (l) z.Y(str, this.m);
        }
        int i2 = m.f4903a;
        return new s(str, true);
    }
}
