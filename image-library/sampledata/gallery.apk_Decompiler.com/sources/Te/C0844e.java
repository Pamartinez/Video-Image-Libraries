package Te;

import Ff.w;
import Hf.M;
import Ne.i;
import Qe.C0819i;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import xf.C1353d;

/* renamed from: Te.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0844e implements M {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0845f f3773a;

    public C0844e(C0845f fVar) {
        this.f3773a = fVar;
    }

    public final i f() {
        return C1353d.e(this.f3773a);
    }

    public final C0819i g() {
        return this.f3773a;
    }

    public final List getParameters() {
        List list = ((w) this.f3773a).t;
        if (list != null) {
            return list;
        }
        j.k("typeConstructorParameters");
        throw null;
    }

    public final Collection h() {
        Collection h5 = ((w) this.f3773a).G0().s0().h();
        j.d(h5, "getSupertypes(...)");
        return h5;
    }

    public final boolean i() {
        return true;
    }

    public final String toString() {
        return "[typealias " + this.f3773a.getName().b() + ']';
    }
}
