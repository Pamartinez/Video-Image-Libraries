package ge;

import B1.a;
import E2.k;
import ee.C0969b;
import ee.C0990x;
import ee.a0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class V implements C1064x {
    public void b(a0 a0Var) {
        f().b(a0Var);
    }

    public final C0990x c() {
        return f().c();
    }

    public void d(a0 a0Var) {
        f().d(a0Var);
    }

    public final Runnable e(O0 o0) {
        return f().e(o0);
    }

    public abstract C1064x f();

    public final C0969b getAttributes() {
        return f().getAttributes();
    }

    public final String toString() {
        k V = a.V(this);
        V.a(f(), "delegate");
        return V.toString();
    }
}
