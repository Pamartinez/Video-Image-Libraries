package K0;

import E0.c;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final C0332j f369a;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public Object f370c;
    public final Interpolator d;
    public final Interpolator e;
    public final Interpolator f;
    public final float g;

    /* renamed from: h  reason: collision with root package name */
    public Float f371h;

    /* renamed from: i  reason: collision with root package name */
    public float f372i;

    /* renamed from: j  reason: collision with root package name */
    public float f373j;
    public int k;
    public int l;
    public float m;
    public float n;

    /* renamed from: o  reason: collision with root package name */
    public PointF f374o;

    /* renamed from: p  reason: collision with root package name */
    public PointF f375p;

    public a(C0332j jVar, Object obj, Object obj2, Interpolator interpolator, float f5, Float f8) {
        this.f372i = -3987645.8f;
        this.f373j = -3987645.8f;
        this.k = 784923401;
        this.l = 784923401;
        this.m = Float.MIN_VALUE;
        this.n = Float.MIN_VALUE;
        this.f374o = null;
        this.f375p = null;
        this.f369a = jVar;
        this.b = obj;
        this.f370c = obj2;
        this.d = interpolator;
        this.e = null;
        this.f = null;
        this.g = f5;
        this.f371h = f8;
    }

    public final float a() {
        C0332j jVar = this.f369a;
        if (jVar == null) {
            return 1.0f;
        }
        if (this.n == Float.MIN_VALUE) {
            if (this.f371h == null) {
                this.n = 1.0f;
            } else {
                this.n = ((this.f371h.floatValue() - this.g) / jVar.c()) + b();
            }
        }
        return this.n;
    }

    public final float b() {
        C0332j jVar = this.f369a;
        if (jVar == null) {
            return 0.0f;
        }
        if (this.m == Float.MIN_VALUE) {
            this.m = (this.g - jVar.l) / jVar.c();
        }
        return this.m;
    }

    public final boolean c() {
        if (this.d == null && this.e == null && this.f == null) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "Keyframe{startValue=" + this.b + ", endValue=" + this.f370c + ", startFrame=" + this.g + ", endFrame=" + this.f371h + ", interpolator=" + this.d + '}';
    }

    public a(C0332j jVar, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, float f5) {
        this.f372i = -3987645.8f;
        this.f373j = -3987645.8f;
        this.k = 784923401;
        this.l = 784923401;
        this.m = Float.MIN_VALUE;
        this.n = Float.MIN_VALUE;
        this.f374o = null;
        this.f375p = null;
        this.f369a = jVar;
        this.b = obj;
        this.f370c = obj2;
        this.d = null;
        this.e = interpolator;
        this.f = interpolator2;
        this.g = f5;
        this.f371h = null;
    }

    public a(C0332j jVar, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, float f5, Float f8) {
        this.f372i = -3987645.8f;
        this.f373j = -3987645.8f;
        this.k = 784923401;
        this.l = 784923401;
        this.m = Float.MIN_VALUE;
        this.n = Float.MIN_VALUE;
        this.f374o = null;
        this.f375p = null;
        this.f369a = jVar;
        this.b = obj;
        this.f370c = obj2;
        this.d = interpolator;
        this.e = interpolator2;
        this.f = interpolator3;
        this.g = f5;
        this.f371h = f8;
    }

    public a(Object obj) {
        this.f372i = -3987645.8f;
        this.f373j = -3987645.8f;
        this.k = 784923401;
        this.l = 784923401;
        this.m = Float.MIN_VALUE;
        this.n = Float.MIN_VALUE;
        this.f374o = null;
        this.f375p = null;
        this.f369a = null;
        this.b = obj;
        this.f370c = obj;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = Float.MIN_VALUE;
        this.f371h = Float.valueOf(Float.MAX_VALUE);
    }

    public a(c cVar, c cVar2) {
        this.f372i = -3987645.8f;
        this.f373j = -3987645.8f;
        this.k = 784923401;
        this.l = 784923401;
        this.m = Float.MIN_VALUE;
        this.n = Float.MIN_VALUE;
        this.f374o = null;
        this.f375p = null;
        this.f369a = null;
        this.b = cVar;
        this.f370c = cVar2;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = Float.MIN_VALUE;
        this.f371h = Float.valueOf(Float.MAX_VALUE);
    }
}
