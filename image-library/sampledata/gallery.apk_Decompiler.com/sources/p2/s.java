package P2;

import L2.a;
import P0.b;
import S2.p;
import S2.r;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s extends n {
    public final p f;

    public s(p pVar) {
        super(pVar.d);
        this.f = pVar;
    }

    public final int c() {
        return 8;
    }

    public final void d(C0056f fVar, b bVar) {
        C c5 = fVar.f;
        C c6 = fVar.e;
        p pVar = this.f;
        r rVar = pVar.e;
        int m = c5.m(this.e);
        int l = c6.l(rVar.d);
        int h5 = h(fVar);
        if (bVar.d()) {
            bVar.b(0, f() + ' ' + pVar.a());
            bVar.b(2, "  class_idx: ".concat(a.D(m)));
            bVar.b(2, String.format("  %-10s %s", new Object[]{i().concat(NumericEnum.SEP), a.D(h5)}));
            bVar.b(4, "  name_idx:  ".concat(a.E(l)));
        }
        bVar.m(m);
        bVar.m(h5);
        bVar.l(l);
    }

    public abstract int h(C0056f fVar);

    public abstract String i();
}
