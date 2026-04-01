package kg;

import jg.a;
import kotlin.jvm.internal.j;
import me.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final n0 f4709c = new W(o0.f4711a);

    public final void d(a aVar, int i2, Object obj) {
        m0 m0Var = (m0) obj;
        j.e(m0Var, "builder");
        long j2 = aVar.E(this.b, i2).j();
        m0Var.b(m0Var.d() + 1);
        long[] jArr = m0Var.f4707a;
        int i7 = m0Var.b;
        m0Var.b = i7 + 1;
        jArr[i7] = j2;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [kg.m0, java.lang.Object] */
    public final Object e(Object obj) {
        long[] jArr = ((t) obj).d;
        j.e(jArr, "$this$toBuilder");
        ? obj2 = new Object();
        obj2.f4707a = jArr;
        obj2.b = jArr.length;
        obj2.b(10);
        return obj2;
    }

    public final Object h() {
        return new t(new long[0]);
    }
}
