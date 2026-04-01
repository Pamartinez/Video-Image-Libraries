package Hf;

import Ae.b;
import Af.p;
import If.f;
import java.util.List;
import kotlin.jvm.internal.j;

/* renamed from: Hf.y  reason: case insensitive filesystem */
public final class C0775y implements b {
    public final /* synthetic */ int d = 1;
    public final M e;
    public final List f;

    public C0775y(p pVar, I i2, M m, List list, boolean z) {
        this.e = m;
        this.f = list;
    }

    public final Object invoke(Object obj) {
        f fVar = (f) obj;
        switch (this.d) {
            case 0:
                j.e(fVar, "refiner");
                this.e.g();
                return null;
            default:
                j.e(fVar, "kotlinTypeRefiner");
                this.e.g();
                return null;
        }
    }

    public C0775y(I i2, M m, List list, boolean z) {
        this.e = m;
        this.f = list;
    }
}
