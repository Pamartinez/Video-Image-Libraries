package Zf;

import Ae.c;
import L1.d;
import Qe.B;
import cg.a;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    public static final B f3984a = new B("NULL", 2);
    public static final B b = new B("UNINITIALIZED", 2);

    /* JADX INFO: finally extract failed */
    public static final Object a(C1232h hVar, Object obj, Object obj2, c cVar, C1227c cVar2) {
        Object obj3;
        Object l = a.l(hVar, obj2);
        try {
            p pVar = new p(cVar2, hVar);
            if (cVar == null) {
                obj3 = d.x(obj, cVar, pVar);
            } else {
                y.c(2, cVar);
                obj3 = cVar.invoke(obj, pVar);
            }
            a.g(hVar, l);
            if (obj3 == C1245a.COROUTINE_SUSPENDED) {
                j.e(cVar2, "frame");
            }
            return obj3;
        } catch (Throwable th) {
            a.g(hVar, l);
            throw th;
        }
    }
}
