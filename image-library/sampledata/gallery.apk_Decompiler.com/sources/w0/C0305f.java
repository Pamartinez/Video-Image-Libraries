package w0;

/* renamed from: w0.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0305f extends n {
    public final double e;

    public C0305f(double d) {
        super(o.IEEE_754_DOUBLE_PRECISION_FLOAT);
        this.e = d;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0305f) {
            C0305f fVar = (C0305f) obj;
            if (!super.equals(obj) || this.e != fVar.e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Double.valueOf(this.e).hashCode() ^ super.hashCode();
    }

    public final String toString() {
        return String.valueOf(this.e);
    }
}
