package z0;

import A0.a;
import A0.e;
import E0.p;
import E0.q;
import F0.c;
import java.util.List;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements a, c {

    /* renamed from: a  reason: collision with root package name */
    public final w f2195a;
    public final e b;

    /* renamed from: c  reason: collision with root package name */
    public q f2196c;

    public s(w wVar, c cVar, p pVar) {
        this.f2195a = wVar;
        e p02 = pVar.f146a.p0();
        this.b = p02;
        cVar.f(p02);
        p02.a(this);
    }

    public static int c(int i2, int i7) {
        int i8 = i2 / i7;
        if ((i2 ^ i7) < 0 && i8 * i7 != i2) {
            i8--;
        }
        return i2 - (i8 * i7);
    }

    public final void a() {
        this.f2195a.invalidateSelf();
    }

    public final void b(List list, List list2) {
    }
}
