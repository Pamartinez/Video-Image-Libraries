package kotlin.jvm.internal;

import He.C0748d;
import He.C0750f;
import He.C0751g;
import He.j;
import He.q;
import He.s;
import cg.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class w {
    public C0748d b(Class cls) {
        return new d(cls);
    }

    public C0750f c(Class cls, String str) {
        return new n(cls, str);
    }

    public String g(f fVar) {
        String obj = fVar.getClass().getGenericInterfaces()[0].toString();
        if (obj.startsWith("kotlin.jvm.functions.")) {
            return obj.substring(21);
        }
        return obj;
    }

    public String h(k kVar) {
        return g(kVar);
    }

    public C0751g a(g gVar) {
        return gVar;
    }

    public j d(l lVar) {
        return lVar;
    }

    public q e(i iVar) {
        return iVar;
    }

    public s f(o oVar) {
        return oVar;
    }
}
