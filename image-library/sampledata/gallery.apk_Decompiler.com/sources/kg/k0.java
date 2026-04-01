package kg;

import jg.a;
import kotlin.jvm.internal.j;
import me.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final k0 f4703c = new W(l0.f4705a);

    public final void d(a aVar, int i2, Object obj) {
        j0 j0Var = (j0) obj;
        j.e(j0Var, "builder");
        int f = aVar.E(this.b, i2).f();
        j0Var.b(j0Var.d() + 1);
        int[] iArr = j0Var.f4701a;
        int i7 = j0Var.b;
        j0Var.b = i7 + 1;
        iArr[i7] = f;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object, kg.j0] */
    public final Object e(Object obj) {
        int[] iArr = ((r) obj).d;
        j.e(iArr, "$this$toBuilder");
        ? obj2 = new Object();
        obj2.f4701a = iArr;
        obj2.b = iArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new r(new int[0]);
    }
}
