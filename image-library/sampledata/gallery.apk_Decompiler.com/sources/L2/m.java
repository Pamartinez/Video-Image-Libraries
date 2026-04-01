package L2;

import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashMap f400a;

    public m(int i2) {
        switch (i2) {
            case 1:
                this.f400a = new LinkedHashMap();
                return;
            default:
                this.f400a = new LinkedHashMap();
                return;
        }
    }

    public b a(q qVar) {
        int i2;
        LinkedHashMap linkedHashMap = c(qVar.f407a).f399h;
        if (!linkedHashMap.containsKey(qVar)) {
            if (qVar.f408c.equals("<init>")) {
                i2 = 65537;
            } else {
                i2 = 1;
            }
            k kVar = new k(qVar, i2);
            linkedHashMap.put(qVar, kVar);
            return kVar.f396c;
        }
        throw new IllegalStateException("already declared: " + qVar);
    }

    public void b(n nVar, int i2) {
        LinkedHashMap linkedHashMap = c(nVar.f401a).g;
        if (!linkedHashMap.containsKey(nVar)) {
            linkedHashMap.put(nVar, new j(nVar, i2));
            return;
        }
        throw new IllegalStateException("already declared: " + nVar);
    }

    public l c(r rVar) {
        LinkedHashMap linkedHashMap = this.f400a;
        l lVar = (l) linkedHashMap.get(rVar);
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(rVar);
        linkedHashMap.put(rVar, lVar2);
        return lVar2;
    }
}
