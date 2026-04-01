package O2;

import B1.a;
import N2.g;
import N2.l;
import N2.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends a {

    /* renamed from: j  reason: collision with root package name */
    public static final b f566j = new Object();

    public final String A(l lVar) {
        return a.c(lVar);
    }

    public final String B(l lVar) {
        return a.a(lVar);
    }

    public final boolean D(g gVar) {
        if (!(gVar instanceof y) || gVar.d.e.length != 0) {
            return false;
        }
        return true;
    }

    public final void a0(P0.b bVar, l lVar) {
        int l = ((y) lVar).l();
        bVar.m(a.M(lVar, 0));
        bVar.m((short) l);
        bVar.m((short) (l >> 16));
    }

    public final int h() {
        return 3;
    }
}
