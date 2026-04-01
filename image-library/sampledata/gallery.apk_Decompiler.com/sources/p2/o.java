package P2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o extends p {
    public int d = -1;

    public final int e() {
        int i2 = this.d;
        if (i2 >= 0) {
            return i2;
        }
        throw new RuntimeException("index not yet set");
    }

    public final String f() {
        return "[" + Integer.toHexString(this.d) + ']';
    }

    public final void g(int i2) {
        if (this.d == -1) {
            this.d = i2;
            return;
        }
        throw new RuntimeException("index already set");
    }
}
