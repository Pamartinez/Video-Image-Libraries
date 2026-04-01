package P2;

import L2.a;
import T2.b;
import T2.c;
import T2.e;
import U2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H extends A {

    /* renamed from: h  reason: collision with root package name */
    public final e f580h;

    public H(e eVar) {
        super(4, (((d) eVar).e.length * 2) + 4);
        this.f580h = eVar;
    }

    public final void a(C0056f fVar) {
        C c5 = fVar.f;
        e eVar = this.f580h;
        int length = ((d) eVar).e.length;
        for (int i2 = 0; i2 < length; i2++) {
            c5.q(eVar.getType(i2));
        }
    }

    public final q b() {
        return q.TYPE_TYPE_LIST;
    }

    public final int e(A a7) {
        e eVar = ((H) a7).f580h;
        b bVar = b.f;
        e eVar2 = this.f580h;
        int length = ((d) eVar2).e.length;
        int length2 = ((d) eVar).e.length;
        int min = Math.min(length, length2);
        for (int i2 = 0; i2 < min; i2++) {
            int compareTo = eVar2.getType(i2).d.compareTo(eVar.getType(i2).d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (length == length2) {
            return 0;
        }
        if (length < length2) {
            return -1;
        }
        return 1;
    }

    public final int hashCode() {
        b bVar = b.f;
        e eVar = this.f580h;
        int length = ((d) eVar).e.length;
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            i2 = (i2 * 31) + eVar.getType(i7).d.hashCode();
        }
        return i2;
    }

    public final void k(C0056f fVar, P0.b bVar) {
        C c5 = fVar.f;
        e eVar = this.f580h;
        int length = ((d) eVar).e.length;
        if (bVar.d()) {
            bVar.b(0, g() + " type_list");
            bVar.b(4, "  size: ".concat(a.E(length)));
            for (int i2 = 0; i2 < length; i2++) {
                c type = eVar.getType(i2);
                int n = c5.n(type);
                bVar.b(2, "  " + a.D(n) + " // " + type.a());
            }
        }
        bVar.l(length);
        for (int i7 = 0; i7 < length; i7++) {
            bVar.m(c5.n(eVar.getType(i7)));
        }
    }
}
