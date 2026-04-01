package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* renamed from: kg.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1143y extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final C1143y f4724c = new W(C1144z.f4725a);

    public final void d(a aVar, int i2, Object obj) {
        C1142x xVar = (C1142x) obj;
        j.e(xVar, "builder");
        float z = aVar.z(this.b, i2);
        xVar.b(xVar.d() + 1);
        float[] fArr = xVar.f4723a;
        int i7 = xVar.b;
        xVar.b = i7 + 1;
        fArr[i7] = z;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kg.x, java.lang.Object] */
    public final Object e(Object obj) {
        float[] fArr = (float[]) obj;
        j.e(fArr, "<this>");
        ? obj2 = new Object();
        obj2.f4723a = fArr;
        obj2.b = fArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new float[0];
    }
}
