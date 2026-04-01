package kg;

import jg.a;
import kotlin.jvm.internal.j;
import me.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final h0 f4698c = new W(i0.f4700a);

    public final void d(a aVar, int i2, Object obj) {
        g0 g0Var = (g0) obj;
        j.e(g0Var, "builder");
        byte G5 = aVar.E(this.b, i2).G();
        g0Var.b(g0Var.d() + 1);
        byte[] bArr = g0Var.f4696a;
        int i7 = g0Var.b;
        g0Var.b = i7 + 1;
        bArr[i7] = G5;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [kg.g0, java.lang.Object] */
    public final Object e(Object obj) {
        byte[] bArr = ((p) obj).d;
        j.e(bArr, "$this$toBuilder");
        ? obj2 = new Object();
        obj2.f4696a = bArr;
        obj2.b = bArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new p(new byte[0]);
    }
}
