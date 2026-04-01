package P2;

import L2.a;
import P0.b;
import S2.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F extends o implements Comparable {
    public final t e;
    public E f;

    public F(t tVar) {
        if (tVar != null) {
            this.e = tVar;
            this.f = null;
            return;
        }
        throw new NullPointerException("value == null");
    }

    public final void a(C0056f fVar) {
        if (this.f == null) {
            z zVar = fVar.d;
            E e7 = new E(this.e);
            this.f = e7;
            zVar.k(e7);
        }
    }

    public final q b() {
        return q.TYPE_STRING_ID_ITEM;
    }

    public final int c() {
        return 4;
    }

    public final int compareTo(Object obj) {
        return this.e.compareTo(((F) obj).e);
    }

    public final void d(C0056f fVar, b bVar) {
        String str;
        int f5 = this.f.f();
        if (bVar.d()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f());
            sb2.append(' ');
            String a7 = this.e.a();
            if (a7.length() <= 98) {
                str = "";
            } else {
                a7 = a7.substring(0, 95);
                str = "...";
            }
            sb2.append("\"" + a7 + str + '\"');
            bVar.b(0, sb2.toString());
            bVar.b(4, "  string_data_off: ".concat(a.E(f5)));
        }
        bVar.l(f5);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof F)) {
            return false;
        }
        return this.e.equals(((F) obj).e);
    }

    public final int hashCode() {
        return this.e.d.hashCode();
    }
}
