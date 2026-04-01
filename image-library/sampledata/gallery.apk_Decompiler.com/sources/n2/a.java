package N2;

import S2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Comparable {
    public final u d;
    public final int e;

    public a(u uVar, int i2) {
        if (i2 >= 0) {
            this.e = i2;
            this.d = uVar;
            return;
        }
        throw new IllegalArgumentException("handler < 0");
    }

    /* renamed from: a */
    public final int compareTo(a aVar) {
        int i2 = aVar.e;
        int i7 = this.e;
        if (i7 < i2) {
            return -1;
        }
        if (i7 > i2) {
            return 1;
        }
        return this.d.compareTo(aVar.d);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a) || compareTo((a) obj) != 0) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.d.hashCode() + (this.e * 31);
    }
}
