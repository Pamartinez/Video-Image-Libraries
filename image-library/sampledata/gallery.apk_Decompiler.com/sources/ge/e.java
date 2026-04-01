package Ge;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends c implements b {
    public static final e g = new c(1, 0, 1);

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        if (isEmpty() && ((e) obj).isEmpty()) {
            return true;
        }
        e eVar = (e) obj;
        if (this.d == eVar.d && this.e == eVar.e) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.d * 31) + this.e;
    }

    public final boolean isEmpty() {
        if (this.d > this.e) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return this.d + ".." + this.e;
    }
}
