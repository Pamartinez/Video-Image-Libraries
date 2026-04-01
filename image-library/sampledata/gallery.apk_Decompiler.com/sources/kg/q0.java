package kg;

import jg.a;
import kotlin.jvm.internal.j;
import me.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final q0 f4715c = new W(r0.f4717a);

    public final void d(a aVar, int i2, Object obj) {
        p0 p0Var = (p0) obj;
        j.e(p0Var, "builder");
        short o2 = aVar.E(this.b, i2).o();
        p0Var.b(p0Var.d() + 1);
        short[] sArr = p0Var.f4713a;
        int i7 = p0Var.b;
        p0Var.b = i7 + 1;
        sArr[i7] = o2;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [kg.p0, java.lang.Object] */
    public final Object e(Object obj) {
        short[] sArr = ((w) obj).d;
        j.e(sArr, "$this$toBuilder");
        ? obj2 = new Object();
        obj2.f4713a = sArr;
        obj2.b = sArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new w(new short[0]);
    }
}
