package q1;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements Comparable {
    public int d;
    public int e;

    public final int compareTo(Object obj) {
        e eVar = (e) obj;
        int i2 = this.e;
        int i7 = eVar.e;
        if (i2 != i7) {
            return i2 - i7;
        }
        return this.d - eVar.d;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Order{order=");
        sb2.append(this.e);
        sb2.append(", index=");
        return j.e(sb2, this.d, '}');
    }
}
