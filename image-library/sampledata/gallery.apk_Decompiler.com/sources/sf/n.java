package Sf;

import Ae.a;
import Ae.b;
import Bd.C0725a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1195m;
import ne.C1202t;
import o1.C0246a;

public abstract class n extends p {
    public static int o0(k kVar) {
        Iterator it = kVar.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            it.next();
            i2++;
            if (i2 < 0) {
                C1195m.u0();
                throw null;
            }
        }
        return i2;
    }

    public static g p0(k kVar, b bVar) {
        j.e(kVar, "<this>");
        return new g(kVar, true, bVar);
    }

    public static Object q0(k kVar) {
        Iterator it = kVar.iterator();
        if (!it.hasNext()) {
            return null;
        }
        return it.next();
    }

    public static final h r0(k kVar) {
        C0725a aVar = new C0725a(7);
        if (!(kVar instanceof u)) {
            return new h(kVar, new C0725a(8), aVar);
        }
        u uVar = (u) kVar;
        return new h(uVar.f3739a, uVar.b, aVar);
    }

    public static k s0(b bVar, Object obj) {
        j.e(bVar, "nextFunction");
        if (obj == null) {
            return e.f3730a;
        }
        return new j((a) new q(0, obj), bVar);
    }

    public static u t0(k kVar, b bVar) {
        j.e(bVar, "transform");
        return new u(kVar, bVar);
    }

    public static g u0(k kVar, b bVar) {
        j.e(bVar, "transform");
        return new g(new u(kVar, bVar), false, new C0725a(9));
    }

    public static List v0(k kVar) {
        Iterator it = kVar.iterator();
        if (!it.hasNext()) {
            return C1202t.d;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return C0246a.e0(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
