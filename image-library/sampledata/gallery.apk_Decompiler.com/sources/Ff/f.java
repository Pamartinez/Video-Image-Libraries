package Ff;

import Ae.a;
import Af.m;
import Af.p;
import Hf.C0758g;
import Ye.c;
import java.util.Collection;
import kotlin.jvm.internal.j;

public final class f implements a {
    public final /* synthetic */ int d;
    public final h e;

    public /* synthetic */ f(h hVar, int i2) {
        this.d = i2;
        this.e = hVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                Af.f fVar = Af.f.m;
                p.f3319a.getClass();
                return this.e.i(fVar, m.e, c.WHEN_GET_ALL_DESCRIPTORS);
            default:
                h hVar = this.e;
                If.f fVar2 = hVar.g;
                k kVar = hVar.f3384j;
                fVar2.getClass();
                j.e(kVar, "classDescriptor");
                Collection h5 = ((C0758g) kVar.q()).h();
                j.d(h5, "getSupertypes(...)");
                return h5;
        }
    }
}
