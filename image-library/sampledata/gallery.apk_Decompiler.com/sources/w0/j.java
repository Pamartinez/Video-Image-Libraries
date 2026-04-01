package W0;

import Q0.b;
import Tf.m;
import V0.g;
import V0.l;
import c1.C0089a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class j extends i {
    public static final int g = i.a(g.class);
    public final C0089a f;

    static {
        g.AUTO_DETECT_FIELDS.getClass();
        g.AUTO_DETECT_GETTERS.getClass();
        g.AUTO_DETECT_IS_GETTERS.getClass();
        g.AUTO_DETECT_SETTERS.getClass();
        g.AUTO_DETECT_CREATORS.getClass();
    }

    public j(a aVar, C0089a aVar2, m mVar, f fVar, f fVar2) {
        super(aVar, g);
        this.f = aVar2;
        int i2 = g.d;
    }

    public abstract j b(a aVar);

    public final j c(b bVar) {
        a aVar = this.e;
        if (bVar != aVar.f886j) {
            aVar = new a(aVar.e, aVar.f, aVar.d, aVar.f884h, aVar.f885i, bVar, aVar.g);
        }
        return b(aVar);
    }

    public j(l lVar) {
        super(lVar);
        lVar.getClass();
        this.f = lVar.f;
    }

    public j(j jVar, a aVar) {
        super(jVar, aVar);
        this.f = jVar.f;
    }

    public j(j jVar, int i2) {
        super(jVar, i2);
        jVar.getClass();
        this.f = jVar.f;
    }
}
