package df;

import Ae.a;
import Sf.g;
import Sf.n;
import We.C0891c;
import We.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import ne.C1182C;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;
import ne.z;

/* renamed from: df.k  reason: case insensitive filesystem */
public final class C0948k implements a {
    public final /* synthetic */ int d;
    public final C0951n e;

    public /* synthetic */ C0948k(C0951n nVar, int i2) {
        this.d = i2;
        this.e = nVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                Class[] declaredClasses = this.e.f4253o.f3891a.getDeclaredClasses();
                j.d(declaredClasses, "getDeclaredClasses(...)");
                return C1194l.p1(n.v0(n.u0(new g(C1192j.b0(declaredClasses), false, C0891c.g), C0891c.f3884h)));
            case 1:
                ArrayList arrayList = new ArrayList();
                for (Object next : this.e.f4253o.b()) {
                    if (((u) next).f3894a.isEnumConstant()) {
                        arrayList.add(next);
                    }
                }
                int Z = z.Z(C1196n.w0(arrayList, 10));
                if (Z < 16) {
                    Z = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Object next2 = it.next();
                    linkedHashMap.put(((u) next2).c(), next2);
                }
                return linkedHashMap;
            default:
                C0951n nVar = this.e;
                return C1182C.b0(nVar.b(), nVar.g());
        }
    }
}
