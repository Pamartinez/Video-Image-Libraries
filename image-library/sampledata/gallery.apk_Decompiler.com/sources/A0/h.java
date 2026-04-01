package A0;

import B0.a;
import D0.b;
import D0.e;
import F0.c;
import F0.l;
import android.graphics.Color;
import android.graphics.Matrix;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements a {

    /* renamed from: a  reason: collision with root package name */
    public final c f5a;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final f f6c;
    public final i d;
    public final i e;
    public final i f;
    public final i g;

    /* renamed from: h  reason: collision with root package name */
    public float f7h = Float.NaN;

    /* renamed from: i  reason: collision with root package name */
    public float f8i = Float.NaN;

    /* renamed from: j  reason: collision with root package name */
    public float f9j = Float.NaN;
    public int k = 0;
    public final float[] l = new float[9];

    public h(a aVar, c cVar, a aVar2) {
        this.b = aVar;
        this.f5a = cVar;
        e p02 = ((D0.a) aVar2.d).p0();
        this.f6c = (f) p02;
        p02.a(this);
        cVar.f(p02);
        i C02 = ((b) aVar2.e).p0();
        this.d = C02;
        C02.a(this);
        cVar.f(C02);
        i C03 = ((b) aVar2.f).p0();
        this.e = C03;
        C03.a(this);
        cVar.f(C03);
        i C04 = ((b) aVar2.g).p0();
        this.f = C04;
        C04.a(this);
        cVar.f(C04);
        i C05 = ((b) aVar2.f34h).p0();
        this.g = C05;
        C05.a(this);
        cVar.f(C05);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, A0.a] */
    public final void a() {
        this.b.a();
    }

    public final void b(l lVar, Matrix matrix, int i2) {
        float floatValue = ((Float) this.f.f()).floatValue();
        double l8 = (double) (this.e.l() * 0.017453292f);
        float sin = ((float) Math.sin(l8)) * floatValue;
        float cos = ((float) Math.cos(l8 + 3.141592653589793d)) * floatValue;
        Matrix e7 = this.f5a.w.e();
        float[] fArr = this.l;
        e7.getValues(fArr);
        float f5 = fArr[0];
        float f8 = fArr[4];
        matrix.getValues(fArr);
        float f10 = fArr[0] / f5;
        float f11 = sin * f10;
        float f12 = cos * (fArr[4] / f8);
        int intValue = ((Integer) this.f6c.f()).intValue();
        int argb = Color.argb(Math.round((((Float) this.d.f()).floatValue() * ((float) i2)) / 255.0f), Color.red(intValue), Color.green(intValue), Color.blue(intValue));
        float max = Math.max(((Float) this.g.f()).floatValue() * f10, Float.MIN_VALUE);
        if (this.f7h != max || this.f8i != f11 || this.f9j != f12 || this.k != argb) {
            this.f7h = max;
            this.f8i = f11;
            this.f9j = f12;
            this.k = argb;
            lVar.setShadowLayer(max, f11, f12, argb);
        }
    }

    public final void c(e eVar) {
        this.d.k(new g(eVar));
    }
}
