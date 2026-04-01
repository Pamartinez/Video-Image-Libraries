package df;

import Ae.a;
import D1.f;
import Ve.b;
import cf.C0922a;
import ee.P;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jf.C1108h;
import kf.C1116b;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import yf.C1358b;

/* renamed from: df.o  reason: case insensitive filesystem */
public final class C0952o implements a {
    public final /* synthetic */ int d;
    public final C0954q e;

    public /* synthetic */ C0952o(C0954q qVar, int i2) {
        this.d = i2;
        this.e = qVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                C0954q qVar = this.e;
                C1108h hVar = ((C0922a) qVar.l.d).l;
                qVar.f3743i.b();
                hVar.getClass();
                return z.e0(new ArrayList());
            case 1:
                this.e.k.getClass();
                return new ArrayList(C1196n.w0(C1202t.d, 10));
            default:
                HashMap hashMap = new HashMap();
                for (Map.Entry entry : ((Map) f.y(this.e.m, C0954q.q[0])).entrySet()) {
                    C1358b c5 = C1358b.c((String) entry.getKey());
                    P p6 = ((b) entry.getValue()).b;
                    C1116b bVar = (C1116b) p6.f4277c;
                    int i2 = C0953p.f4255a[bVar.ordinal()];
                    if (i2 == 1) {
                        String str = (String) p6.f4278h;
                        if (bVar != C1116b.MULTIFILE_CLASS_PART) {
                            str = null;
                        }
                        if (str != null) {
                            hashMap.put(c5, C1358b.c(str));
                        }
                    } else if (i2 == 2) {
                        hashMap.put(c5, c5);
                    }
                }
                return hashMap;
        }
    }
}
