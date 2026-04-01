package N2;

import R2.l;
import R2.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends l {
    public final e f;

    public y(i iVar, p pVar, l lVar, e eVar) {
        super(iVar, pVar, lVar);
        if (eVar != null) {
            this.f = eVar;
            return;
        }
        throw new NullPointerException("target == null");
    }

    public final String a() {
        e eVar = this.f;
        if (eVar == null) {
            return "????";
        }
        return eVar.d();
    }

    public final g g(i iVar) {
        return new y(iVar, this.f427c, this.d, this.f);
    }

    public final g i(l lVar) {
        return new y(this.b, this.f427c, lVar, this.f);
    }

    public final int l() {
        return this.f.c() - c();
    }

    public final boolean m() {
        if (this.f426a < 0 || this.f.f426a < 0) {
            return false;
        }
        return true;
    }

    public final y n(e eVar) {
        i iVar;
        i iVar2 = this.b;
        switch (iVar2.f428a) {
            case 50:
                iVar = k.Q;
                break;
            case 51:
                iVar = k.f469P;
                break;
            case 52:
                iVar = k.S;
                break;
            case 53:
                iVar = k.R;
                break;
            case 54:
                iVar = k.U;
                break;
            case 55:
                iVar = k.T;
                break;
            case 56:
                iVar = k.f482W;
                break;
            case 57:
                iVar = k.V;
                break;
            case 58:
                iVar = k.Y;
                break;
            case 59:
                iVar = k.f485X;
                break;
            case 60:
                iVar = k.a0;
                break;
            case 61:
                iVar = k.Z;
                break;
            default:
                throw new IllegalArgumentException("bogus opcode: " + iVar2);
        }
        return new y(iVar, this.f427c, this.d, eVar);
    }
}
