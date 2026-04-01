package sf;

import Ae.b;
import Hf.C0774x;
import Hf.P;
import Hf.d0;
import kotlin.jvm.internal.j;

/* renamed from: sf.g  reason: case insensitive filesystem */
public final class C1280g implements b {
    public final /* synthetic */ int d;
    public final C1283j e;

    public /* synthetic */ C1280g(C1283j jVar, int i2) {
        this.d = i2;
        this.e = jVar;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                P p6 = (P) obj;
                j.e(p6, "it");
                if (p6.c()) {
                    return "*";
                }
                C0774x b = p6.b();
                j.d(b, "getType(...)");
                String Y = this.e.Y(b);
                if (p6.a() == d0.INVARIANT) {
                    return Y;
                }
                return p6.a() + ' ' + Y;
            default:
                C0774x xVar = (C0774x) obj;
                j.b(xVar);
                return this.e.Y(xVar);
        }
    }
}
