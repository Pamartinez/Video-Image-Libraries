package A0;

import C0.c;
import D0.e;
import K0.b;
import android.graphics.PointF;
import x0.I;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends e {
    public final /* synthetic */ b g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ e f19h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ c f20i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q(b bVar, e eVar, c cVar) {
        super(9);
        this.g = bVar;
        this.f19h = eVar;
        this.f20i = cVar;
    }

    public final Object K(b bVar) {
        Object obj;
        float f = bVar.f376a;
        float f5 = bVar.b;
        String str = ((c) bVar.f).f87a;
        String str2 = ((c) bVar.g).f87a;
        float f8 = bVar.f377c;
        float f10 = bVar.d;
        float f11 = bVar.e;
        b bVar2 = this.g;
        bVar2.f376a = f;
        bVar2.b = f5;
        bVar2.f = str;
        bVar2.g = str2;
        bVar2.f377c = f8;
        bVar2.d = f10;
        bVar2.e = f11;
        String str3 = (String) ((I) this.f19h.f);
        if (bVar.d == 1.0f) {
            obj = bVar.g;
        } else {
            obj = bVar.f;
        }
        c cVar = (c) obj;
        String str4 = cVar.b;
        float f12 = cVar.f88c;
        C0.b bVar3 = cVar.d;
        int i2 = cVar.e;
        float f13 = cVar.f;
        float f14 = cVar.g;
        int i7 = cVar.f89h;
        int i8 = cVar.f90i;
        float f15 = cVar.f91j;
        boolean z = cVar.k;
        PointF pointF = cVar.l;
        PointF pointF2 = cVar.m;
        c cVar2 = this.f20i;
        cVar2.f87a = str3;
        cVar2.b = str4;
        cVar2.f88c = f12;
        cVar2.d = bVar3;
        cVar2.e = i2;
        cVar2.f = f13;
        cVar2.g = f14;
        cVar2.f89h = i7;
        cVar2.f90i = i8;
        cVar2.f91j = f15;
        cVar2.k = z;
        cVar2.l = pointF;
        cVar2.m = pointF2;
        return cVar2;
    }
}
