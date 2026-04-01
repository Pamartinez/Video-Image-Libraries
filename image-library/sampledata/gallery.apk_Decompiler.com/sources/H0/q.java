package H0;

import A0.m;
import D0.e;
import I0.b;
import I0.c;
import K0.a;
import java.util.ArrayList;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q {

    /* renamed from: a  reason: collision with root package name */
    public static final e f324a = e.S("k");

    public static ArrayList a(c cVar, C0332j jVar, float f, F f5, boolean z) {
        boolean z3;
        F f8;
        float f10;
        C0332j jVar2;
        c cVar2;
        ArrayList arrayList = new ArrayList();
        if (cVar.n() == b.STRING) {
            jVar.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        cVar.c();
        while (cVar.h()) {
            if (cVar.q(f324a) != 0) {
                cVar.s();
            } else if (cVar.n() == b.BEGIN_ARRAY) {
                cVar.a();
                if (cVar.n() == b.NUMBER) {
                    c cVar3 = cVar;
                    C0332j jVar3 = jVar;
                    float f11 = f;
                    F f12 = f5;
                    boolean z7 = z;
                    a b = p.b(cVar3, jVar3, f11, f12, false, z7);
                    cVar2 = cVar3;
                    jVar2 = jVar3;
                    f10 = f11;
                    f8 = f12;
                    z3 = z7;
                    arrayList.add(b);
                } else {
                    cVar2 = cVar;
                    jVar2 = jVar;
                    f10 = f;
                    f8 = f5;
                    z3 = z;
                    while (cVar2.h()) {
                        arrayList.add(p.b(cVar2, jVar2, f10, f8, true, z3));
                    }
                }
                cVar2.f();
                cVar = cVar2;
                jVar = jVar2;
                f = f10;
                f5 = f8;
                z = z3;
            } else {
                c cVar4 = cVar;
                arrayList.add(p.b(cVar4, jVar, f, f5, false, z));
                cVar = cVar4;
            }
        }
        cVar.g();
        b(arrayList);
        return arrayList;
    }

    public static void b(ArrayList arrayList) {
        int i2;
        Object obj;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            i2 = size - 1;
            if (i7 >= i2) {
                break;
            }
            a aVar = (a) arrayList.get(i7);
            i7++;
            a aVar2 = (a) arrayList.get(i7);
            aVar.f371h = Float.valueOf(aVar2.g);
            if (aVar.f370c == null && (obj = aVar2.b) != null) {
                aVar.f370c = obj;
                if (aVar instanceof m) {
                    ((m) aVar).d();
                }
            }
        }
        a aVar3 = (a) arrayList.get(i2);
        if ((aVar3.b == null || aVar3.f370c == null) && arrayList.size() > 1) {
            arrayList.remove(aVar3);
        }
    }
}
