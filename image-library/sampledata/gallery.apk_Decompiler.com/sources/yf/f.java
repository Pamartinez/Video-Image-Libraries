package Yf;

import Zf.k;
import me.x;
import qe.C1227c;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements g {

    /* renamed from: a  reason: collision with root package name */
    public final g f3919a;

    public f(g gVar) {
        this.f3919a = gVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    public final Object collect(h hVar, C1227c cVar) {
        ? obj = new Object();
        obj.d = k.f3984a;
        Object collect = this.f3919a.collect(new e(this, obj, hVar), cVar);
        if (collect == C1245a.COROUTINE_SUSPENDED) {
            return collect;
        }
        return x.f4917a;
    }
}
