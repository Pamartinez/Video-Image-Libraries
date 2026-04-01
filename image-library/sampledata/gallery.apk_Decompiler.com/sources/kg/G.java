package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final G f4668c = new W(H.f4669a);

    public final void d(a aVar, int i2, Object obj) {
        F f = (F) obj;
        j.e(f, "builder");
        int x9 = aVar.x(this.b, i2);
        f.b(f.d() + 1);
        int[] iArr = f.f4667a;
        int i7 = f.b;
        f.b = i7 + 1;
        iArr[i7] = x9;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kg.F, java.lang.Object] */
    public final Object e(Object obj) {
        int[] iArr = (int[]) obj;
        j.e(iArr, "<this>");
        ? obj2 = new Object();
        obj2.f4667a = iArr;
        obj2.b = iArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new int[0];
    }
}
