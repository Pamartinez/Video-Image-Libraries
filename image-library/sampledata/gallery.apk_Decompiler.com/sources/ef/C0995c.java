package ef;

import A0.l;
import Ae.a;
import D0.e;
import Hf.B;
import Hf.M;
import Qe.C0819i;
import Qe.V;
import We.q;
import java.util.Set;

/* renamed from: ef.c  reason: case insensitive filesystem */
public final class C0995c implements a {
    public final l d;
    public final V e;
    public final C0993a f;
    public final M g;

    /* renamed from: h  reason: collision with root package name */
    public final q f4319h;

    public C0995c(l lVar, V v, C0993a aVar, M m, q qVar) {
        this.d = lVar;
        this.e = v;
        this.f = aVar;
        this.g = m;
        this.f4319h = qVar;
    }

    public final Object invoke() {
        B b;
        e eVar = (e) this.d.g;
        C0819i g3 = this.g.g();
        if (g3 != null) {
            b = g3.i();
        } else {
            b = null;
        }
        return eVar.I(this.e, C0993a.a(C0993a.a(this.f, (C0994b) null, false, (Set) null, b, 31), (C0994b) null, this.f4319h.d(), (Set) null, (B) null, 59));
    }
}
