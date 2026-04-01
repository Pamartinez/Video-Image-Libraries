package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* renamed from: kg.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1124e extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final C1124e f4692c = new W(C1125f.f4694a);

    public final void d(a aVar, int i2, Object obj) {
        C1123d dVar = (C1123d) obj;
        j.e(dVar, "builder");
        boolean r = aVar.r(this.b, i2);
        dVar.b(dVar.d() + 1);
        boolean[] zArr = dVar.f4690a;
        int i7 = dVar.b;
        dVar.b = i7 + 1;
        zArr[i7] = r;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kg.d, java.lang.Object] */
    public final Object e(Object obj) {
        boolean[] zArr = (boolean[]) obj;
        j.e(zArr, "<this>");
        ? obj2 = new Object();
        obj2.f4690a = zArr;
        obj2.b = zArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new boolean[0];
    }
}
