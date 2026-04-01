package S2;

import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends p {
    public final int d(a aVar) {
        p pVar = (p) aVar;
        int c5 = this.d.compareTo(pVar.d);
        r rVar = this.e;
        if (c5 == 0) {
            c5 = rVar.d.compareTo(pVar.e.d);
        }
        if (c5 != 0) {
            return c5;
        }
        return rVar.e.compareTo(((h) aVar).e.e);
    }

    public final String e() {
        return "field";
    }

    public final c getType() {
        return c.g(this.e.e.d);
    }
}
