package S2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m extends n {
    public final long d;

    public m(long j2) {
        this.d = j2;
    }

    public final int d(a aVar) {
        long j2 = ((m) aVar).d;
        long j3 = this.d;
        if (j3 < j2) {
            return -1;
        }
        if (j3 > j2) {
            return 1;
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.d == ((m) obj).d) {
            return true;
        }
        return false;
    }

    public final boolean f() {
        long j2 = this.d;
        if (((long) ((int) j2)) == j2) {
            return true;
        }
        return false;
    }

    public final int g() {
        return (int) this.d;
    }

    public final long h() {
        return this.d;
    }

    public final int hashCode() {
        long j2 = this.d;
        return ((int) j2) ^ ((int) (j2 >> 32));
    }
}
