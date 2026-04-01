package N2;

import P0.b;
import R2.k;
import R2.l;
import R2.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends g {
    public x[] e;

    public m(p pVar, l lVar) {
        super(k.b, pVar, lVar);
        if (lVar.e.length != 0) {
            this.e = null;
            return;
        }
        throw new IllegalArgumentException("registers.size() == 0");
    }

    public final String a() {
        return null;
    }

    public final int b() {
        k();
        int i2 = 0;
        for (x b : this.e) {
            i2 += b.b();
        }
        return i2;
    }

    public final String e() {
        l lVar = this.d;
        int length = lVar.e.length;
        StringBuffer stringBuffer = new StringBuffer(100);
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            k kVar = (k) lVar.d(i7);
            x f = g.f(p.f773a, k.d(i2, kVar.e.getType()), kVar);
            if (i7 != 0) {
                stringBuffer.append(10);
            }
            stringBuffer.append(f.e());
            i2 += kVar.c();
        }
        return stringBuffer.toString();
    }

    public final g g(i iVar) {
        throw new RuntimeException("unsupported");
    }

    public final g h(int i2) {
        return i(this.d.k(i2));
    }

    public final g i(l lVar) {
        return new m(this.f427c, lVar);
    }

    public final void j(b bVar) {
        k();
        for (x j2 : this.e) {
            j2.j(bVar);
        }
    }

    public final void k() {
        if (this.e == null) {
            l lVar = this.d;
            int length = lVar.e.length;
            this.e = new x[length];
            int i2 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                k kVar = (k) lVar.d(i7);
                this.e[i7] = g.f(p.f773a, k.d(i2, kVar.e.getType()), kVar);
                i2 += kVar.c();
            }
        }
    }
}
