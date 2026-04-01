package df;

import Ae.a;
import Qe.C0833x;
import Qe.V;
import We.C;
import We.o;
import cf.C0922a;
import cf.e;
import java.util.ArrayList;
import java.util.Iterator;
import ne.C1196n;
import xf.C1353d;

/* renamed from: df.g  reason: case insensitive filesystem */
public final class C0944g implements a {
    public final /* synthetic */ int d;
    public final C0946i e;

    public /* synthetic */ C0944g(C0946i iVar, int i2) {
        this.d = i2;
        this.e = iVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                C0946i iVar = this.e;
                if (C1353d.f(iVar) == null) {
                    return null;
                }
                ((C0922a) iVar.f4247j.d).w.getClass();
                return null;
            case 1:
                C0946i iVar2 = this.e;
                o oVar = iVar2.k;
                ArrayList typeParameters = oVar.getTypeParameters();
                ArrayList arrayList = new ArrayList(C1196n.w0(typeParameters, 10));
                Iterator it = typeParameters.iterator();
                while (it.hasNext()) {
                    C c5 = (C) it.next();
                    V a7 = ((e) iVar2.m.e).a(c5);
                    if (a7 != null) {
                        arrayList.add(a7);
                    } else {
                        throw new AssertionError("Parameter " + c5 + " surely belongs to class " + oVar + ", so it must be resolved");
                    }
                }
                return arrayList;
            default:
                return C0833x.c(this.e);
        }
    }
}
