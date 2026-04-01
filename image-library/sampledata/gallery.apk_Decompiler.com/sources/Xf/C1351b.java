package xf;

import Ae.b;
import Qe.C0814d;
import Qe.C0822l;
import kotlin.jvm.internal.j;

/* renamed from: xf.b  reason: case insensitive filesystem */
public final class C1351b implements b {
    public static final C1351b e = new C1351b(0);
    public final /* synthetic */ int d;

    public /* synthetic */ C1351b(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                C0822l lVar = (C0822l) obj;
                int i2 = C1353d.f5167a;
                j.e(lVar, "it");
                return lVar.g();
            default:
                C0814d dVar = (C0814d) obj;
                j.b(dVar);
                return C1353d.l(dVar);
        }
    }
}
