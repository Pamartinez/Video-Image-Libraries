package vf;

import Hf.B;
import Hf.C0774x;
import Jf.k;
import Jf.l;
import Qe.C;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0833x;
import kotlin.jvm.internal.j;
import me.i;
import qf.C1235b;
import qf.C1240g;
import tf.C1301e;

/* renamed from: vf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1329i extends C1327g {
    public final C1235b b;

    /* renamed from: c  reason: collision with root package name */
    public final C1240g f5159c;

    public C1329i(C1235b bVar, C1240g gVar) {
        super(new i(bVar, gVar));
        this.b = bVar;
        this.f5159c = gVar;
    }

    public final C0774x a(C c5) {
        B i2;
        j.e(c5, "module");
        C1235b bVar = this.b;
        C0816f d = C0833x.d(c5, bVar);
        if (d != null) {
            int i7 = C1301e.f5137a;
            if (!C1301e.n(d, C0817g.ENUM_CLASS)) {
                d = null;
            }
            if (!(d == null || (i2 = d.i()) == null)) {
                return i2;
            }
        }
        return l.c(k.ERROR_ENUM_TYPE, bVar.toString(), this.f5159c.d);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.b.f());
        sb2.append('.');
        sb2.append(this.f5159c);
        return sb2.toString();
    }
}
