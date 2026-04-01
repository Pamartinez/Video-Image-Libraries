package af;

import Af.g;
import B0.a;
import D1.f;
import Gf.h;
import Gf.i;
import Gf.m;
import He.t;
import We.C0893e;
import cf.C0922a;
import java.util.Map;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

/* renamed from: af.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0912j extends C0904b {
    public static final /* synthetic */ t[] g;
    public final i f;

    static {
        w wVar = v.f4727a;
        g = new t[]{wVar.f(new p(wVar.b(C0912j.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [Gf.h, Gf.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0912j(C0893e eVar, a aVar) {
        super(aVar, eVar, Ne.p.t);
        j.e(eVar, "annotation");
        j.e(aVar, "c");
        Gf.p pVar = ((C0922a) aVar.d).f4006a;
        g gVar = new g(27, this);
        m mVar = (m) pVar;
        mVar.getClass();
        this.f = new h(mVar, gVar);
    }

    public final Map a() {
        return (Map) f.y(this.f, g[0]);
    }
}
