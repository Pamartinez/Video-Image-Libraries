package x0;

import java.util.Arrays;

/* renamed from: x0.B  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0320B {

    /* renamed from: a  reason: collision with root package name */
    public final C0332j f2042a;
    public final Throwable b;

    public C0320B(C0332j jVar) {
        this.f2042a = jVar;
        this.b = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0320B)) {
            return false;
        }
        C0320B b5 = (C0320B) obj;
        C0332j jVar = this.f2042a;
        if (jVar != null && jVar.equals(b5.f2042a)) {
            return true;
        }
        Throwable th = this.b;
        if (th == null || b5.b == null) {
            return false;
        }
        return th.toString().equals(th.toString());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2042a, this.b});
    }

    public C0320B(Throwable th) {
        this.b = th;
        this.f2042a = null;
    }
}
