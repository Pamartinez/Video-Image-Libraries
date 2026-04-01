package S2;

import U2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends d implements Comparable {
    /* renamed from: g */
    public final int compareTo(b bVar) {
        int i2;
        int length = this.e.length;
        int length2 = bVar.e.length;
        if (length < length2) {
            i2 = length;
        } else {
            i2 = length2;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            int c5 = ((a) d(i7)).compareTo((a) bVar.d(i7));
            if (c5 != 0) {
                return c5;
            }
        }
        if (length < length2) {
            return -1;
        }
        if (length > length2) {
            return 1;
        }
        return 0;
    }
}
