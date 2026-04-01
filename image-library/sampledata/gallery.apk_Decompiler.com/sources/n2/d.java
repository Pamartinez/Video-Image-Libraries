package N2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends U2.d implements Comparable {
    public static final d f = new U2.d(0);

    public final int compareTo(Object obj) {
        d dVar = (d) obj;
        if (this != dVar) {
            int length = this.e.length;
            int length2 = dVar.e.length;
            int min = Math.min(length, length2);
            for (int i2 = 0; i2 < min; i2++) {
                int a7 = ((c) d(i2)).compareTo((c) dVar.d(i2));
                if (a7 != 0) {
                    return a7;
                }
            }
            if (length < length2) {
                return -1;
            }
            if (length > length2) {
                return 1;
            }
        }
        return 0;
    }
}
