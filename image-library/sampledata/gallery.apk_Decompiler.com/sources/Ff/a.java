package Ff;

import D1.f;
import Gf.i;
import Gf.m;
import He.t;
import Re.b;
import Re.h;
import a.C0068a;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class a implements h {
    public static final /* synthetic */ t[] e;
    public final i d;

    static {
        w wVar = v.f4727a;
        e = new t[]{wVar.f(new p(wVar.b(a.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [Gf.h, Gf.i] */
    public a(Gf.p pVar, Ae.a aVar) {
        j.e(pVar, "storageManager");
        this.d = new Gf.h((m) pVar, aVar);
    }

    public final boolean g(C1236c cVar) {
        return C0068a.I(this, cVar);
    }

    public boolean isEmpty() {
        return ((List) f.y(this.d, e[0])).isEmpty();
    }

    public final Iterator iterator() {
        return ((List) f.y(this.d, e[0])).iterator();
    }

    public final b m(C1236c cVar) {
        return C0068a.v(this, cVar);
    }
}
