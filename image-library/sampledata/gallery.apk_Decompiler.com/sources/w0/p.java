package w0;

import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends C0304e {

    /* renamed from: c  reason: collision with root package name */
    public final long f1990c;

    public p(long j2) {
        super(C0308i.TAG);
        this.f1990c = j2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (!super.equals(obj) || this.f1990c != pVar.f1990c) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Long.valueOf(this.f1990c).hashCode() ^ super.hashCode();
    }

    public final String toString() {
        return C0212a.o(new StringBuilder("Tag("), this.f1990c, ")");
    }
}
