package uf;

import Ae.a;
import He.C0748d;
import Hf.C0774x;
import Hf.P;
import Tf.v;
import ig.i;
import java.lang.reflect.Field;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import ne.C1182C;
import ne.C1195m;
import sf.C1283j;
import sf.C1287n;
import sf.C1288o;

public final class d implements a {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke() {
        C1287n nVar;
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                C0774x b = ((P) obj).b();
                j.d(b, "getType(...)");
                return b;
            default:
                C1288o oVar = ((C1283j) obj).f5084a;
                C1288o oVar2 = new C1288o();
                Class<C1288o> cls = C1288o.class;
                i g = j.g(cls.getDeclaredFields());
                while (g.hasNext()) {
                    Field field = (Field) g.next();
                    if ((field.getModifiers() & 8) == 0) {
                        field.setAccessible(true);
                        Object obj2 = field.get(oVar);
                        if (obj2 instanceof C1287n) {
                            nVar = (C1287n) obj2;
                        } else {
                            nVar = null;
                        }
                        if (nVar != null) {
                            String name = field.getName();
                            j.d(name, "getName(...)");
                            v.t0(name, "is");
                            C0748d b5 = kotlin.jvm.internal.v.f4727a.b(cls);
                            String name2 = field.getName();
                            StringBuilder sb2 = new StringBuilder("get");
                            String name3 = field.getName();
                            j.d(name3, "getName(...)");
                            if (name3.length() > 0) {
                                char upperCase = Character.toUpperCase(name3.charAt(0));
                                String substring = name3.substring(1);
                                j.d(substring, "substring(...)");
                                name3 = upperCase + substring;
                            }
                            sb2.append(name3);
                            new p(b5, name2, sb2.toString());
                            field.set(oVar2, new C1287n(nVar.d, oVar2));
                        }
                    }
                }
                C1283j jVar = C1283j.f5083c;
                oVar2.l(C1182C.b0(oVar2.h(), C1195m.q0(Ne.p.f3572p, Ne.p.q)));
                oVar2.f5099a = true;
                return new C1283j(oVar2);
        }
    }
}
