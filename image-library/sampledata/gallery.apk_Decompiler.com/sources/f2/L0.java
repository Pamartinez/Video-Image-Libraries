package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L0 extends C0010c0 {
    public final transient Object g;

    public L0(Object obj) {
        obj.getClass();
        this.g = obj;
    }

    public final boolean contains(Object obj) {
        return this.g.equals(obj);
    }

    public final int hashCode() {
        return this.g.hashCode();
    }

    public final U p() {
        return U.B(this.g);
    }

    public final int q(int i2, Object[] objArr) {
        objArr[i2] = this.g;
        return i2 + 1;
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        return "[" + this.g.toString() + ']';
    }

    public final boolean u() {
        return false;
    }

    public final O0 v() {
        return new C0030m0(this.g);
    }
}
