package N2;

import B1.a;
import P0.b;
import R2.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l extends g {
    public final /* synthetic */ int e = 0;

    public /* synthetic */ l(i iVar, p pVar, R2.l lVar) {
        super(iVar, pVar, lVar);
    }

    public final int b() {
        switch (this.e) {
            case 0:
                return this.b.d.h();
            default:
                return 0;
        }
    }

    public String e() {
        i iVar = this.b;
        a aVar = iVar.d;
        aVar.getClass();
        String a7 = iVar.a();
        String A10 = aVar.A(this);
        String B = aVar.B(this);
        StringBuilder sb2 = new StringBuilder(100);
        sb2.append(a7);
        if (A10.length() != 0) {
            sb2.append(' ');
            sb2.append(A10);
        }
        if (B.length() != 0) {
            sb2.append(" // ");
            sb2.append(B);
        }
        return sb2.toString();
    }

    public g g(i iVar) {
        throw new RuntimeException("unsupported");
    }

    public g h(int i2) {
        switch (this.e) {
            case 0:
                return i(this.d.k(i2));
            default:
                return i(this.d.k(i2));
        }
    }

    public final void j(b bVar) {
        switch (this.e) {
            case 0:
                this.b.d.a0(bVar, this);
                return;
            default:
                return;
        }
    }

    public l(p pVar) {
        super(k.b, pVar, R2.l.f);
    }

    private final void k(b bVar) {
    }
}
