package If;

import Hf.B;
import Hf.C0754c;
import Hf.L;
import Hf.X;
import Hf.d0;
import Kf.d;
import Kf.e;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends C0754c {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f3458a;
    public final /* synthetic */ X b;

    public a(b bVar, X x9) {
        this.f3458a = bVar;
        this.b = x9;
    }

    public final e D(L l, d dVar) {
        j.e(l, "state");
        j.e(dVar, "type");
        b bVar = this.f3458a;
        B g02 = bVar.g0(this.b.g(bVar.o0(dVar), d0.INVARIANT));
        j.b(g02);
        return g02;
    }
}
