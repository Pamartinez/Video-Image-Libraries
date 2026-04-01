package H0;

import C0.b;
import D0.e;
import I0.c;
import android.graphics.PointF;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements F {
    public static final h d = new Object();
    public static final e e = e.S("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of", "ps", "sz");

    /* JADX WARNING: type inference failed for: r13v1, types: [java.lang.Object, C0.c] */
    public final Object g(c cVar, float f) {
        b bVar = b.CENTER;
        cVar.c();
        String str = null;
        float f5 = 0.0f;
        float f8 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        int i2 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z = true;
        String str2 = null;
        PointF pointF = null;
        PointF pointF2 = null;
        while (cVar.h()) {
            c cVar2 = cVar;
            switch (cVar2.q(e)) {
                case 0:
                    str = cVar.m();
                    break;
                case 1:
                    str2 = cVar.m();
                    break;
                case 2:
                    f5 = (float) cVar.j();
                    break;
                case 3:
                    int l = cVar.l();
                    b bVar2 = b.CENTER;
                    if (l <= bVar2.ordinal() && l >= 0) {
                        bVar = b.values()[l];
                        break;
                    } else {
                        bVar = bVar2;
                        break;
                    }
                case 4:
                    i2 = cVar.l();
                    break;
                case 5:
                    f8 = (float) cVar.j();
                    break;
                case 6:
                    f10 = (float) cVar.j();
                    break;
                case 7:
                    i7 = o.a(cVar);
                    break;
                case 8:
                    i8 = o.a(cVar);
                    break;
                case 9:
                    f11 = (float) cVar.j();
                    break;
                case 10:
                    z = cVar.i();
                    break;
                case 11:
                    cVar.a();
                    pointF = new PointF(((float) cVar.j()) * f, ((float) cVar.j()) * f);
                    cVar.f();
                    break;
                case 12:
                    cVar2.a();
                    pointF2 = new PointF(((float) cVar.j()) * f, ((float) cVar.j()) * f);
                    cVar.f();
                    break;
                default:
                    cVar2.r();
                    cVar2.s();
                    break;
            }
        }
        cVar.g();
        ? obj = new Object();
        obj.f87a = str;
        obj.b = str2;
        obj.f88c = f5;
        obj.d = bVar;
        obj.e = i2;
        obj.f = f8;
        obj.g = f10;
        obj.f89h = i7;
        obj.f90i = i8;
        obj.f91j = f11;
        obj.k = z;
        obj.l = pointF;
        obj.m = pointF2;
        return obj;
    }
}
