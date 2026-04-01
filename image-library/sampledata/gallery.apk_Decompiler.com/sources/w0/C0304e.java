package w0;

import java.util.Objects;

/* renamed from: w0.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0304e {

    /* renamed from: a  reason: collision with root package name */
    public final C0308i f1984a;
    public p b;

    public C0304e(C0308i iVar) {
        this.f1984a = iVar;
        Objects.requireNonNull(iVar, "majorType is null");
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0304e) {
            C0304e eVar = (C0304e) obj;
            C0308i iVar = eVar.f1984a;
            p pVar = this.b;
            C0308i iVar2 = this.f1984a;
            if (pVar != null) {
                if (!pVar.equals(eVar.b) || iVar2 != iVar) {
                    return false;
                }
                return true;
            } else if (eVar.b == null && iVar2 == iVar) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.f1984a, this.b});
    }
}
