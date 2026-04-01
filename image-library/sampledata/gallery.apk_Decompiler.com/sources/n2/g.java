package N2;

import L2.a;
import P0.b;
import R2.k;
import R2.l;
import R2.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public int f426a;
    public final i b;

    /* renamed from: c  reason: collision with root package name */
    public final p f427c;
    public final l d;

    public g(i iVar, p pVar, l lVar) {
        if (iVar == null) {
            throw new NullPointerException("opcode == null");
        } else if (pVar == null) {
            throw new NullPointerException("position == null");
        } else if (lVar != null) {
            this.f426a = -1;
            this.b = iVar;
            this.f427c = pVar;
            this.d = lVar;
        } else {
            throw new NullPointerException("registers == null");
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [N2.l, N2.x] */
    public static x f(p pVar, k kVar, k kVar2) {
        boolean z;
        i iVar;
        boolean z3 = false;
        if (kVar.c() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (kVar.e.getType().e == 9) {
            z3 = true;
        }
        int i2 = kVar.d;
        if ((kVar2.d | i2) < 16) {
            if (z3) {
                iVar = k.f516j;
            } else if (z) {
                iVar = k.d;
            } else {
                iVar = k.g;
            }
        } else if (i2 < 256) {
            if (z3) {
                iVar = k.k;
            } else if (z) {
                iVar = k.e;
            } else {
                iVar = k.f510h;
            }
        } else if (z3) {
            iVar = k.l;
        } else if (z) {
            iVar = k.f;
        } else {
            iVar = k.f513i;
        }
        return new l(iVar, pVar, l.i(kVar, kVar2));
    }

    public abstract String a();

    public abstract int b();

    public final int c() {
        int i2 = this.f426a;
        if (i2 >= 0) {
            return i2;
        }
        throw new RuntimeException("address not yet known");
    }

    public final String d() {
        int i2 = this.f426a;
        if (i2 != -1) {
            return String.format("%04x", new Object[]{Integer.valueOf(i2)});
        }
        return a.E(System.identityHashCode(this));
    }

    public abstract String e();

    public abstract g g(i iVar);

    public abstract g h(int i2);

    public abstract g i(l lVar);

    public abstract void j(b bVar);

    public final String toString() {
        boolean z;
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(d());
        stringBuffer.append(' ');
        stringBuffer.append(this.f427c);
        stringBuffer.append(": ");
        stringBuffer.append(this.b.a());
        l lVar = this.d;
        if (lVar.e.length != 0) {
            z = true;
            stringBuffer.append(lVar.f(" ", (String) null, true));
        } else {
            z = false;
        }
        String a7 = a();
        if (a7 != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append(' ');
            stringBuffer.append(a7);
        }
        return stringBuffer.toString();
    }
}
