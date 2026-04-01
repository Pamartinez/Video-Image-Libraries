package Qe;

import Ae.b;
import Re.h;
import kotlin.jvm.internal.j;
import qf.C1236c;

public final class I implements b {
    public final /* synthetic */ int d;
    public final C1236c e;

    public /* synthetic */ I(C1236c cVar, int i2) {
        this.d = i2;
        this.e = cVar;
    }

    public final Object invoke(Object obj) {
        boolean z;
        switch (this.d) {
            case 0:
                C1236c cVar = (C1236c) obj;
                j.e(cVar, "it");
                if (cVar.d() || !cVar.e().equals(this.e)) {
                    z = false;
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                h hVar = (h) obj;
                j.e(hVar, "it");
                return hVar.m(this.e);
        }
    }
}
