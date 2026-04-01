package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final K f4672c = new W(L.f4673a);

    public final void d(a aVar, int i2, Object obj) {
        J j2 = (J) obj;
        j.e(j2, "builder");
        long t = aVar.t(this.b, i2);
        j2.b(j2.d() + 1);
        long[] jArr = j2.f4671a;
        int i7 = j2.b;
        j2.b = i7 + 1;
        jArr[i7] = t;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, kg.J] */
    public final Object e(Object obj) {
        long[] jArr = (long[]) obj;
        j.e(jArr, "<this>");
        ? obj2 = new Object();
        obj2.f4671a = jArr;
        obj2.b = jArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new long[0];
    }
}
