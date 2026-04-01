package Te;

import Af.l;
import Gf.h;
import Gf.i;
import Gf.m;
import He.t;
import Qe.C0822l;
import Qe.C0824n;
import Qe.L;
import Re.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends C0852m implements L {
    public static final /* synthetic */ t[] l;
    public final z g;

    /* renamed from: h  reason: collision with root package name */
    public final C1236c f3806h;

    /* renamed from: i  reason: collision with root package name */
    public final i f3807i;

    /* renamed from: j  reason: collision with root package name */
    public final i f3808j;
    public final l k;

    static {
        kotlin.jvm.internal.w wVar = v.f4727a;
        Class<w> cls = w.class;
        l = new t[]{wVar.f(new p(wVar.b(cls), "fragments", "getFragments()Ljava/util/List;")), wVar.f(new p(wVar.b(cls), "empty", "getEmpty()Z"))};
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r4v4, types: [Gf.h, Gf.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w(z zVar, C1236c cVar, m mVar) {
        super(g.f3692a, cVar.g());
        j.e(cVar, "fqName");
        j.e(mVar, "storageManager");
        this.g = zVar;
        this.f3806h = cVar;
        this.f3807i = new h(mVar, new v(this, 0));
        this.f3808j = new h(mVar, new v(this, 1));
        this.k = new l(mVar, new v(this, 2));
    }

    public final boolean equals(Object obj) {
        L l8;
        if (obj instanceof L) {
            l8 = (L) obj;
        } else {
            l8 = null;
        }
        if (l8 == null) {
            return false;
        }
        w wVar = (w) l8;
        if (!j.a(this.f3806h, wVar.f3806h) || !j.a(this.g, wVar.g)) {
            return false;
        }
        return true;
    }

    public final C0822l g() {
        C1236c cVar = this.f3806h;
        if (cVar.d()) {
            return null;
        }
        return this.g.n0(cVar.e());
    }

    public final int hashCode() {
        return this.f3806h.hashCode() + (this.g.hashCode() * 31);
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.h(this, obj);
    }
}
