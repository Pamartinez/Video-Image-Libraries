package S2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l extends n {
    public final int d;

    public l(int i2) {
        this.d = i2;
    }

    public final int d(a aVar) {
        int i2 = ((l) aVar).d;
        int i7 = this.d;
        if (i7 < i2) {
            return -1;
        }
        if (i7 > i2) {
            return 1;
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass() && this.d == ((l) obj).d) {
            return true;
        }
        return false;
    }

    public final boolean f() {
        return true;
    }

    public final int g() {
        return this.d;
    }

    public final long h() {
        return (long) this.d;
    }

    public final int hashCode() {
        return this.d;
    }
}
