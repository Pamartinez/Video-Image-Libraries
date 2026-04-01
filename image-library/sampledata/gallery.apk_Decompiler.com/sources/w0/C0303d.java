package w0;

/* renamed from: w0.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0303d extends C0304e {

    /* renamed from: c  reason: collision with root package name */
    public boolean f1983c = false;

    public C0303d(C0308i iVar) {
        super(iVar);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0303d)) {
            return false;
        }
        C0303d dVar = (C0303d) obj;
        if (!super.equals(obj) || this.f1983c != dVar.f1983c) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Boolean.valueOf(this.f1983c).hashCode() ^ super.hashCode();
    }
}
