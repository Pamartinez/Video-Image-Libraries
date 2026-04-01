package A0;

import K0.a;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import x0.C0323a;
import x0.C0326d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f1a = new ArrayList(1);
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public final b f2c;
    public float d = 0.0f;
    public D0.e e;
    public Object f = null;
    public float g = -1.0f;

    /* renamed from: h  reason: collision with root package name */
    public float f3h = -1.0f;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: A0.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: A0.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: A0.b} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public e(java.util.List r3) {
        /*
            r2 = this;
            r2.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 1
            r0.<init>(r1)
            r2.f1a = r0
            r0 = 0
            r2.b = r0
            r0 = 0
            r2.d = r0
            r0 = 0
            r2.f = r0
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2.g = r0
            r2.f3h = r0
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0026
            ie.c r3 = new ie.c
            r3.<init>()
            goto L_0x0039
        L_0x0026:
            int r0 = r3.size()
            if (r0 != r1) goto L_0x0033
            A0.d r0 = new A0.d
            r0.<init>(r3)
        L_0x0031:
            r3 = r0
            goto L_0x0039
        L_0x0033:
            A0.c r0 = new A0.c
            r0.<init>(r3)
            goto L_0x0031
        L_0x0039:
            r2.f2c = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.e.<init>(java.util.List):void");
    }

    public final void a(a aVar) {
        this.f1a.add(aVar);
    }

    public final a b() {
        C0323a aVar = C0326d.f2049a;
        return this.f2c.b();
    }

    public float c() {
        if (this.f3h == -1.0f) {
            this.f3h = this.f2c.e();
        }
        return this.f3h;
    }

    public final float d() {
        Interpolator interpolator;
        a b5 = b();
        if (b5 == null || b5.c() || (interpolator = b5.d) == null) {
            return 0.0f;
        }
        return interpolator.getInterpolation(e());
    }

    public final float e() {
        if (this.b) {
            return 0.0f;
        }
        a b5 = b();
        if (b5.c()) {
            return 0.0f;
        }
        return (this.d - b5.b()) / (b5.a() - b5.b());
    }

    public Object f() {
        Object obj;
        float e7 = e();
        if (this.e == null && this.f2c.a(e7)) {
            return this.f;
        }
        a b5 = b();
        Interpolator interpolator = b5.e;
        Interpolator interpolator2 = b5.f;
        if (interpolator == null || interpolator2 == null) {
            obj = g(b5, d());
        } else {
            obj = h(b5, e7, interpolator.getInterpolation(e7), interpolator2.getInterpolation(e7));
        }
        this.f = obj;
        return obj;
    }

    public abstract Object g(a aVar, float f5);

    public Object h(a aVar, float f5, float f8, float f10) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    public void i() {
        C0323a aVar = C0326d.f2049a;
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f1a;
            if (i2 < arrayList.size()) {
                ((a) arrayList.get(i2)).a();
                i2++;
            } else {
                C0323a aVar2 = C0326d.f2049a;
                return;
            }
        }
    }

    public void j(float f5) {
        C0323a aVar = C0326d.f2049a;
        b bVar = this.f2c;
        if (!bVar.isEmpty()) {
            if (this.g == -1.0f) {
                this.g = bVar.d();
            }
            float f8 = this.g;
            if (f5 < f8) {
                if (f8 == -1.0f) {
                    this.g = bVar.d();
                }
                f5 = this.g;
            } else if (f5 > c()) {
                f5 = c();
            }
            if (f5 != this.d) {
                this.d = f5;
                if (bVar.c(f5)) {
                    i();
                }
            }
        }
    }

    public final void k(D0.e eVar) {
        D0.e eVar2 = this.e;
        if (eVar2 != null) {
            eVar2.getClass();
        }
        this.e = eVar;
    }
}
