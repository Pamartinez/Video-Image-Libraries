package vf;

import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.I;
import Hf.c0;
import Hf.d0;
import Jf.k;
import Jf.l;
import Ne.i;
import Ne.p;
import Qe.C;
import Qe.C0816f;
import Qe.C0833x;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import o1.C0246a;
import qf.C1235b;

/* renamed from: vf.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1341u extends C1327g {
    public C1341u(C1235b bVar, int i2) {
        super(new C1339s(new C1326f(bVar, i2)));
    }

    public final C0774x a(C c5) {
        C0774x xVar;
        j.e(c5, "module");
        I.e.getClass();
        I i2 = I.f;
        i f = c5.f();
        f.getClass();
        C0816f i7 = f.i(p.Q.g());
        if (i7 != null) {
            Object obj = this.f5158a;
            C1340t tVar = (C1340t) obj;
            if (tVar instanceof C1338r) {
                xVar = ((C1338r) obj).f5162a;
            } else if (tVar instanceof C1339s) {
                C1326f fVar = ((C1339s) obj).f5163a;
                C1235b bVar = fVar.f5157a;
                int i8 = fVar.b;
                C0816f d = C0833x.d(c5, bVar);
                if (d == null) {
                    xVar = l.c(k.UNRESOLVED_KCLASS_CONSTANT_VALUE, bVar.toString(), String.valueOf(i8));
                } else {
                    B i10 = d.i();
                    j.d(i10, "getDefaultType(...)");
                    c0 M2 = c.M(i10);
                    for (int i11 = 0; i11 < i8; i11++) {
                        M2 = c5.f().h(d0.INVARIANT, M2);
                    }
                    xVar = M2;
                }
            } else {
                throw new RuntimeException();
            }
            return C0754c.t(i2, i7, C0246a.e0(new G(xVar)));
        }
        i.a(21);
        throw null;
    }
}
