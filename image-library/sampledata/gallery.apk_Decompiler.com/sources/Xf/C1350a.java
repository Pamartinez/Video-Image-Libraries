package xf;

import Qe.C0814d;
import Qf.a;
import Te.Q;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import ne.C1196n;
import ne.C1202t;

/* renamed from: xf.a  reason: case insensitive filesystem */
public final class C1350a implements a {
    public static final C1350a e = new C1350a(0);
    public final /* synthetic */ int d;

    public /* synthetic */ C1350a(int i2) {
        this.d = i2;
    }

    public final Iterable a(Object obj) {
        Collection h5;
        switch (this.d) {
            case 0:
                int i2 = C1353d.f5167a;
                Collection h6 = ((Q) obj).h();
                ArrayList arrayList = new ArrayList(C1196n.w0(h6, 10));
                Iterator it = ((ArrayList) h6).iterator();
                while (it.hasNext()) {
                    arrayList.add(((Q) it.next()).a());
                }
                return arrayList;
            default:
                C0814d dVar = (C0814d) obj;
                if (dVar == null || (h5 = dVar.h()) == null) {
                    return C1202t.d;
                }
                return h5;
        }
    }
}
