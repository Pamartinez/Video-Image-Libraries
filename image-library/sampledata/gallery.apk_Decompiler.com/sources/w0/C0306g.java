package w0;

/* renamed from: w0.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0306g extends n {
    public final float e;

    public C0306g(o oVar, float f) {
        super(oVar);
        this.e = f;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0306g) {
            C0306g gVar = (C0306g) obj;
            if (!super.equals(obj) || this.e != gVar.e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Float.valueOf(this.e).hashCode() ^ super.hashCode();
    }
}
