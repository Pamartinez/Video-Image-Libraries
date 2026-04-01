package H0;

import I0.b;
import I0.c;
import android.graphics.Color;
import android.graphics.PointF;

/* renamed from: H0.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0050f implements F {
    public static final C0050f e = new C0050f(0);
    public static final C0050f f = new C0050f(1);
    public static final C0050f g = new C0050f(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0050f f309h = new C0050f(3);

    /* renamed from: i  reason: collision with root package name */
    public static final C0050f f310i = new C0050f(4);

    /* renamed from: j  reason: collision with root package name */
    public static final C0050f f311j = new C0050f(5);
    public final /* synthetic */ int d;

    public /* synthetic */ C0050f(int i2) {
        this.d = i2;
    }

    public final Object g(c cVar, float f5) {
        boolean z;
        double d2;
        boolean z3;
        switch (this.d) {
            case 0:
                if (cVar.n() == b.BEGIN_ARRAY) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    cVar.a();
                }
                double j2 = cVar.j();
                double j3 = cVar.j();
                double j8 = cVar.j();
                if (cVar.n() == b.NUMBER) {
                    d2 = cVar.j();
                } else {
                    d2 = 1.0d;
                }
                if (z) {
                    cVar.f();
                }
                if (j2 <= 1.0d && j3 <= 1.0d && j8 <= 1.0d) {
                    j2 *= 255.0d;
                    j3 *= 255.0d;
                    j8 *= 255.0d;
                    if (d2 <= 1.0d) {
                        d2 *= 255.0d;
                    }
                }
                return Integer.valueOf(Color.argb((int) d2, (int) j2, (int) j3, (int) j8));
            case 1:
                return Float.valueOf(o.d(cVar) * f5);
            case 2:
                return Integer.valueOf(Math.round(o.d(cVar) * f5));
            case 3:
                return o.b(cVar, f5);
            case 4:
                b n = cVar.n();
                if (n == b.BEGIN_ARRAY) {
                    return o.b(cVar, f5);
                }
                if (n == b.BEGIN_OBJECT) {
                    return o.b(cVar, f5);
                }
                if (n == b.NUMBER) {
                    PointF pointF = new PointF(((float) cVar.j()) * f5, ((float) cVar.j()) * f5);
                    while (cVar.h()) {
                        cVar.s();
                    }
                    return pointF;
                }
                throw new IllegalArgumentException("Cannot convert json to point. Next token is " + n);
            default:
                if (cVar.n() == b.BEGIN_ARRAY) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    cVar.a();
                }
                float j10 = (float) cVar.j();
                float j11 = (float) cVar.j();
                while (cVar.h()) {
                    cVar.s();
                }
                if (z3) {
                    cVar.f();
                }
                return new K0.c((j10 / 100.0f) * f5, (j11 / 100.0f) * f5);
        }
    }
}
