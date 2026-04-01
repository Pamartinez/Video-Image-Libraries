package N2;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements Comparable {
    public final int d;
    public final int e;
    public final b f;

    public c(int i2, int i7, b bVar) {
        if (i2 < 0) {
            throw new IllegalArgumentException("start < 0");
        } else if (i7 <= i2) {
            throw new IllegalArgumentException("end <= start");
        } else if (!bVar.d) {
            this.d = i2;
            this.e = i7;
            this.f = bVar;
        } else {
            throw new IllegalArgumentException("handlers.isMutable()");
        }
    }

    /* renamed from: a */
    public final int compareTo(c cVar) {
        int i2 = cVar.d;
        int i7 = this.d;
        if (i7 < i2) {
            return -1;
        }
        if (i7 > i2) {
            return 1;
        }
        int i8 = cVar.e;
        int i10 = this.e;
        if (i10 < i8) {
            return -1;
        }
        if (i10 > i8) {
            return 1;
        }
        return this.f.compareTo(cVar.f);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c) || compareTo((c) obj) != 0) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f.e) + (((this.d * 31) + this.e) * 31);
    }
}
