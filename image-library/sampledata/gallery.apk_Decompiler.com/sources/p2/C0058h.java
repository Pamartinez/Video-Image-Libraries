package P2;

import L2.a;
import P0.b;
import S2.h;
import a.C0068a;

/* renamed from: P2.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0058h extends C0059i implements Comparable {
    public final h e;

    public C0058h(h hVar, int i2) {
        super(i2);
        if (hVar != null) {
            this.e = hVar;
            return;
        }
        throw new NullPointerException("field == null");
    }

    public final String a() {
        return this.e.a();
    }

    public final int c(C0056f fVar, b bVar, int i2, int i7) {
        l lVar = fVar.f595h;
        h hVar = this.e;
        int l = lVar.l(hVar);
        int i8 = l - i2;
        boolean d = bVar.d();
        int i10 = this.d;
        if (d) {
            bVar.b(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(i7), hVar.a()}));
            bVar.b(C0068a.c0(i8), "    field_idx:    ".concat(a.E(l)));
            int c02 = C0068a.c0(i10);
            bVar.b(c02, "    access_flags: " + a.s(i10, 20703, 2));
        }
        bVar.o(i8);
        bVar.o(i10);
        return l;
    }

    public final int compareTo(Object obj) {
        return this.e.compareTo(((C0058h) obj).e);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof C0058h) && this.e.compareTo(((C0058h) obj).e) == 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode();
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(C0058h.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(a.D(this.d));
        stringBuffer.append(' ');
        stringBuffer.append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
