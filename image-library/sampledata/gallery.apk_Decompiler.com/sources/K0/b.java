package K0;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;
import x2.C0347n;
import x2.C0348o;
import x2.C0349p;
import x2.C0351r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public float f376a;
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f377c;
    public float d;
    public float e;
    public Object f = new ArrayList();
    public Object g = new ArrayList();

    public b() {
        d(0.0f, 270.0f, 0.0f);
    }

    public void a(float f5) {
        float f8 = this.d;
        if (f8 != f5) {
            float f10 = ((f5 - f8) + 360.0f) % 360.0f;
            if (f10 <= 180.0f) {
                float f11 = this.b;
                float f12 = this.f377c;
                C0349p pVar = new C0349p(f11, f12, f11, f12);
                pVar.f = this.d;
                pVar.g = f10;
                ((ArrayList) this.g).add(new C0347n(pVar));
                this.d = f5;
            }
        }
    }

    public void b(Matrix matrix, Path path) {
        ArrayList arrayList = (ArrayList) this.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((C0351r) arrayList.get(i2)).a(matrix, path);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [x2.r, x2.q, java.lang.Object] */
    public void c(float f5, float f8) {
        ? rVar = new C0351r();
        rVar.b = f5;
        rVar.f2137c = f8;
        ((ArrayList) this.f).add(rVar);
        C0348o oVar = new C0348o(rVar, this.b, this.f377c);
        a(oVar.a() + 270.0f);
        ((ArrayList) this.g).add(oVar);
        this.d = oVar.a() + 270.0f;
        this.b = f5;
        this.f377c = f8;
    }

    public void d(float f5, float f8, float f10) {
        this.f376a = f5;
        this.b = 0.0f;
        this.f377c = f5;
        this.d = f8;
        this.e = (f8 + f10) % 360.0f;
        ((ArrayList) this.f).clear();
        ((ArrayList) this.g).clear();
    }
}
