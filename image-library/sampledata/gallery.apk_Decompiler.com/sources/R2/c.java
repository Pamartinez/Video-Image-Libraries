package R2;

import S2.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c extends f {

    /* renamed from: h  reason: collision with root package name */
    public final a f647h;

    public c(n nVar, p pVar, k kVar, l lVar, a aVar) {
        super(nVar, pVar, kVar, lVar);
        if (aVar != null) {
            this.f647h = aVar;
            return;
        }
        throw new NullPointerException("cst == null");
    }

    public String e() {
        return this.f647h.a();
    }
}
