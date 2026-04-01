package eg;

import Vf.C0886x;
import cg.a;
import cg.o;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends C0886x {
    public static final m d = new C0886x();

    public final void dispatchYield(C1232h hVar, Runnable runnable) {
        f.e.d.c(true, true, runnable);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        f.e.d.c(true, false, runnable);
    }

    public final C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        if (i2 < l.d) {
            return super.limitedParallelism(i2, str);
        }
        if (str != null) {
            return new o(this, str);
        }
        return this;
    }

    public final String toString() {
        return "Dispatchers.IO";
    }
}
