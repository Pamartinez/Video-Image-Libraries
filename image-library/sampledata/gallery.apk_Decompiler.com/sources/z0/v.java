package z0;

import A0.a;
import A0.i;
import D0.b;
import E0.o;
import E0.y;
import F0.c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v implements c, a {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f2199a;
    public final ArrayList b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    public final y f2200c;
    public final i d;
    public final i e;
    public final i f;

    public v(c cVar, o oVar) {
        this.f2199a = oVar.d;
        this.f2200c = (y) oVar.b;
        i C02 = oVar.f145c.p0();
        this.d = C02;
        i C03 = ((b) oVar.e).p0();
        this.e = C03;
        i C04 = ((b) oVar.f).p0();
        this.f = C04;
        cVar.f(C02);
        cVar.f(C03);
        cVar.f(C04);
        C02.a(this);
        C03.a(this);
        C04.a(this);
    }

    public final void a() {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.b;
            if (i2 < arrayList.size()) {
                ((a) arrayList.get(i2)).a();
                i2++;
            } else {
                return;
            }
        }
    }

    public final void c(a aVar) {
        this.b.add(aVar);
    }

    public final void b(List list, List list2) {
    }
}
