package fg;

import Ae.d;
import Qe.B;
import Vf.C0873j;
import Vf.C0874k;
import Vf.C0875l;
import Vf.E0;
import cg.s;
import me.x;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements C0873j, E0 {
    public final C0875l d;
    public final /* synthetic */ c e;

    public b(c cVar, C0875l lVar) {
        this.e = cVar;
        this.d = lVar;
    }

    public final void a(s sVar, int i2) {
        this.d.a(sVar, i2);
    }

    public final B b(Object obj, d dVar) {
        c cVar = this.e;
        C0874k kVar = new C0874k(cVar, this);
        B D = this.d.D((x) obj, kVar);
        if (D != null) {
            c.f4373h.set(cVar, (Object) null);
        }
        return D;
    }

    public final boolean d(Throwable th) {
        return this.d.d((Throwable) null);
    }

    public final C1232h getContext() {
        return this.d.f3866h;
    }

    public final boolean isActive() {
        return this.d.isActive();
    }

    public final void j(Object obj) {
        this.d.j(obj);
    }

    public final void resumeWith(Object obj) {
        this.d.resumeWith(obj);
    }
}
