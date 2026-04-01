package eg;

import Vf.C0886x;
import cg.a;
import cg.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends i {
    public static final f e;

    /* JADX WARNING: type inference failed for: r0v0, types: [Vf.x, eg.i, eg.f] */
    static {
        int i2 = l.f4332c;
        int i7 = l.d;
        long j2 = l.e;
        String str = l.f4331a;
        ? xVar = new C0886x();
        xVar.d = new d(i2, i7, j2, str);
        e = xVar;
    }

    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    public final C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        if (i2 < l.f4332c) {
            return super.limitedParallelism(i2, str);
        }
        if (str != null) {
            return new o(this, str);
        }
        return this;
    }

    public final String toString() {
        return "Dispatchers.Default";
    }
}
