package Zf;

import Yf.h;
import cg.a;
import me.x;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements h {
    public final C1232h d;
    public final Object e;
    public final a f;

    public q(h hVar, C1232h hVar2) {
        this.d = hVar2;
        this.e = a.k(hVar2);
        this.f = new a(hVar, (C1227c) null, 2);
    }

    public final Object emit(Object obj, C1227c cVar) {
        Object a7 = k.a(this.d, obj, this.e, this.f, cVar);
        if (a7 == C1245a.COROUTINE_SUSPENDED) {
            return a7;
        }
        return x.f4917a;
    }
}
