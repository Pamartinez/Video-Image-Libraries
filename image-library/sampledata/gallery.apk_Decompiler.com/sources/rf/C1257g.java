package rf;

/* renamed from: rf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1257g {

    /* renamed from: a  reason: collision with root package name */
    public final Object f5062a;
    public final int b;

    public C1257g(int i2, Object obj) {
        this.f5062a = obj;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1257g)) {
            return false;
        }
        C1257g gVar = (C1257g) obj;
        if (this.f5062a == gVar.f5062a && this.b == gVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f5062a) * 65535) + this.b;
    }
}
