package Xf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends k {

    /* renamed from: a  reason: collision with root package name */
    public final Throwable f3910a;

    public j(Throwable th) {
        this.f3910a = th;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        if (kotlin.jvm.internal.j.a(this.f3910a, ((j) obj).f3910a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f3910a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    public final String toString() {
        return "Closed(" + this.f3910a + ')';
    }
}
