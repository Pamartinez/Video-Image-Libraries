package ee;

import He.F;

/* renamed from: ee.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0976i {

    /* renamed from: a  reason: collision with root package name */
    public final C0975h f4298a;
    public final a0 b;

    public C0976i(C0975h hVar, a0 a0Var) {
        F.n(hVar, "state is null");
        this.f4298a = hVar;
        F.n(a0Var, "status is null");
        this.b = a0Var;
    }

    public static C0976i a(C0975h hVar) {
        boolean z;
        if (hVar != C0975h.TRANSIENT_FAILURE) {
            z = true;
        } else {
            z = false;
        }
        F.i("state is TRANSIENT_ERROR. Use forError() instead", z);
        return new C0976i(hVar, a0.e);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0976i)) {
            return false;
        }
        C0976i iVar = (C0976i) obj;
        if (!this.f4298a.equals(iVar.f4298a) || !this.b.equals(iVar.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.b.hashCode() ^ this.f4298a.hashCode();
    }

    public final String toString() {
        a0 a0Var = this.b;
        boolean e = a0Var.e();
        C0975h hVar = this.f4298a;
        if (e) {
            return hVar.toString();
        }
        return hVar + "(" + a0Var + ")";
    }
}
