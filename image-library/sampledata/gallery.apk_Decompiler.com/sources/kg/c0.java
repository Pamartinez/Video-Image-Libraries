package kg;

import jg.a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final c0 f4689c = new W(d0.f4691a);

    public final void d(a aVar, int i2, Object obj) {
        b0 b0Var = (b0) obj;
        j.e(b0Var, "builder");
        short h5 = aVar.h(this.b, i2);
        b0Var.b(b0Var.d() + 1);
        short[] sArr = b0Var.f4688a;
        int i7 = b0Var.b;
        b0Var.b = i7 + 1;
        sArr[i7] = h5;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kg.b0, java.lang.Object] */
    public final Object e(Object obj) {
        short[] sArr = (short[]) obj;
        j.e(sArr, "<this>");
        ? obj2 = new Object();
        obj2.f4688a = sArr;
        obj2.b = sArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new short[0];
    }
}
