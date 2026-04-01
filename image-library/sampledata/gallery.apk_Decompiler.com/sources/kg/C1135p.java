package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* renamed from: kg.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1135p extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final C1135p f4712c = new W(C1136q.f4714a);

    public final void d(a aVar, int i2, Object obj) {
        C1134o oVar = (C1134o) obj;
        j.e(oVar, "builder");
        double m = aVar.m(this.b, i2);
        oVar.b(oVar.d() + 1);
        double[] dArr = oVar.f4710a;
        int i7 = oVar.b;
        oVar.b = i7 + 1;
        dArr[i7] = m;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, kg.o] */
    public final Object e(Object obj) {
        double[] dArr = (double[]) obj;
        j.e(dArr, "<this>");
        ? obj2 = new Object();
        obj2.f4710a = dArr;
        obj2.b = dArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new double[0];
    }
}
