package Df;

import Ae.a;
import Ff.u;
import Gf.h;
import Gf.m;
import Hf.C0774x;
import Qe.C0822l;
import kotlin.jvm.internal.j;
import lf.G;
import vf.C1327g;

public final class s implements a {
    public final /* synthetic */ int d;
    public final w e;
    public final G f;
    public final u g;

    public /* synthetic */ s(w wVar, G g3, u uVar, int i2) {
        this.d = i2;
        this.e = wVar;
        this.f = g3;
        this.g = uVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                w wVar = this.e;
                m mVar = ((l) wVar.f3367a.f3358a).f3349a;
                s sVar = new s(wVar, this.f, this.g, 2);
                mVar.getClass();
                return new h(mVar, sVar);
            case 1:
                w wVar2 = this.e;
                m mVar2 = ((l) wVar2.f3367a.f3358a).f3349a;
                s sVar2 = new s(wVar2, this.f, this.g, 3);
                mVar2.getClass();
                return new h(mVar2, sVar2);
            case 2:
                w wVar3 = this.e;
                n nVar = wVar3.f3367a;
                z a7 = wVar3.a((C0822l) nVar.f3359c);
                j.b(a7);
                C0738d dVar = ((l) nVar.f3358a).e;
                C0774x returnType = this.g.getReturnType();
                j.d(returnType, "getReturnType(...)");
                return (C1327g) dVar.o(a7, this.f, returnType);
            default:
                w wVar4 = this.e;
                n nVar2 = wVar4.f3367a;
                z a10 = wVar4.a((C0822l) nVar2.f3359c);
                j.b(a10);
                C0738d dVar2 = ((l) nVar2.f3358a).e;
                C0774x returnType2 = this.g.getReturnType();
                j.d(returnType2, "getReturnType(...)");
                return (C1327g) dVar2.r(a10, this.f, returnType2);
        }
    }
}
