package Te;

import Ae.a;
import Af.j;
import Af.p;
import Df.C0736b;
import Hf.C0754c;
import Hf.I;
import Hf.M;
import Hf.a0;
import Jf.i;
import Jf.k;
import Jf.l;
import java.util.List;

/* renamed from: Te.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0840a implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0841b e;

    public /* synthetic */ C0840a(C0841b bVar, int i2) {
        this.d = i2;
        this.e = bVar;
    }

    public final Object invoke() {
        int i2 = this.d;
        C0841b bVar = this.e;
        switch (i2) {
            case 0:
                p P6 = bVar.P();
                C0736b bVar2 = new C0736b(7, this);
                i iVar = a0.f3439a;
                if (l.f(bVar)) {
                    return l.c(k.UNABLE_TO_SUBSTITUTE_TYPE, bVar.toString());
                }
                M q = bVar.q();
                if (q == null) {
                    a0.a(12);
                    throw null;
                } else if (P6 != null) {
                    List d2 = a0.d(q.getParameters());
                    I.e.getClass();
                    return C0754c.w(I.f, q, d2, false, P6, bVar2);
                } else {
                    a0.a(13);
                    throw null;
                }
            case 1:
                return new j(bVar.P());
            default:
                return new u(bVar);
        }
    }
}
