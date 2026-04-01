package R2;

import T2.e;
import U2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends f {

    /* renamed from: h  reason: collision with root package name */
    public final e f775h;

    public r(n nVar, p pVar, l lVar, e eVar) {
        super(nVar, pVar, (k) null, lVar);
        if (nVar.e != 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (eVar != null) {
            this.f775h = eVar;
        } else {
            throw new NullPointerException("catches == null");
        }
    }

    public static String f(e eVar) {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("catch");
        int length = ((d) eVar).e.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(" ");
            stringBuffer.append(eVar.getType(i2).a());
        }
        return stringBuffer.toString();
    }

    public final void c(e eVar) {
        eVar.i(this);
    }

    public final e d() {
        return this.f775h;
    }

    public final String e() {
        return f(this.f775h);
    }
}
