package Z2;

import D0.e;
import X2.r;
import X2.s;
import X2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends r {
    public static final u[] e = {new Object(), new Object()};
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final d f969c;
    public final e d;

    public a(e eVar, d dVar) {
        super(s.PAYMENT);
        this.f969c = dVar;
        this.d = eVar;
        this.b = (String) eVar.e;
    }

    public final String a() {
        return this.b;
    }

    public final c d() {
        c cVar = c.SAMSUNG_PAY_INDONESIA;
        d dVar = this.f969c;
        if (cVar.a(dVar)) {
            return cVar;
        }
        c cVar2 = c.SAMSUNG_PAY_INDIA;
        if (cVar2.a(dVar)) {
            return cVar2;
        }
        c cVar3 = c.PIX;
        if (cVar3.a(dVar)) {
            return cVar3;
        }
        return c.OTHER;
    }
}
