package J0;

import C0.a;
import E0.q;
import android.graphics.Path;
import android.graphics.PointF;
import i.C0212a;
import java.util.ArrayList;
import z0.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public static final PointF f362a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f, float f5, float f8) {
        return Math.max(f5, Math.min(f8, f));
    }

    public static int c(float f, float f5) {
        boolean z;
        int i2 = (int) f;
        int i7 = (int) f5;
        int i8 = i2 / i7;
        if ((i2 ^ i7) >= 0) {
            z = true;
        } else {
            z = false;
        }
        int i10 = i2 % i7;
        if (!z && i10 != 0) {
            i8--;
        }
        return i2 - (i7 * i8);
    }

    public static void d(q qVar, Path path) {
        Path path2;
        path.reset();
        PointF pointF = qVar.b;
        ArrayList arrayList = qVar.f147a;
        path.moveTo(pointF.x, pointF.y);
        float f = pointF.x;
        float f5 = pointF.y;
        PointF pointF2 = f362a;
        pointF2.set(f, f5);
        int i2 = 0;
        while (i2 < arrayList.size()) {
            a aVar = (a) arrayList.get(i2);
            PointF pointF3 = aVar.f85a;
            PointF pointF4 = aVar.b;
            PointF pointF5 = aVar.f86c;
            if (!pointF3.equals(pointF2) || !pointF4.equals(pointF5)) {
                path2 = path;
                path2.cubicTo(pointF3.x, pointF3.y, pointF4.x, pointF4.y, pointF5.x, pointF5.y);
            } else {
                path.lineTo(pointF5.x, pointF5.y);
                path2 = path;
            }
            pointF2.set(pointF5.x, pointF5.y);
            i2++;
            path = path2;
        }
        Path path3 = path;
        if (qVar.f148c) {
            path3.close();
        }
    }

    public static float e(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    public static void f(C0.f fVar, int i2, ArrayList arrayList, C0.f fVar2, k kVar) {
        if (fVar.a(i2, kVar.getName())) {
            String name = kVar.getName();
            C0.f fVar3 = new C0.f(fVar2);
            fVar3.f97a.add(name);
            C0.f fVar4 = new C0.f(fVar3);
            fVar4.b = kVar;
            arrayList.add(fVar4);
        }
    }
}
