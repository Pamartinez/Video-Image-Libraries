package x0;

import J0.d;
import J0.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements u {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2076a;
    public final /* synthetic */ w b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f2077c;

    public /* synthetic */ r(w wVar, float f, int i2) {
        this.f2076a = i2;
        this.b = wVar;
        this.f2077c = f;
    }

    public final void run() {
        switch (this.f2076a) {
            case 0:
                w wVar = this.b;
                C0332j jVar = wVar.d;
                float f = this.f2077c;
                if (jVar == null) {
                    wVar.f2095j.add(new r(wVar, f, 0));
                    return;
                }
                d dVar = wVar.e;
                dVar.j(dVar.m, f.e(jVar.l, jVar.m, f));
                return;
            case 1:
                w wVar2 = this.b;
                C0332j jVar2 = wVar2.d;
                float f5 = this.f2077c;
                if (jVar2 == null) {
                    wVar2.f2095j.add(new r(wVar2, f5, 1));
                    return;
                } else {
                    wVar2.s((int) f.e(jVar2.l, jVar2.m, f5));
                    return;
                }
            default:
                this.b.u(this.f2077c);
                return;
        }
    }
}
