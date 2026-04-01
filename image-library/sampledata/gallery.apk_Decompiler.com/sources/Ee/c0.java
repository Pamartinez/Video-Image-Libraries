package ee;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c0 extends RuntimeException {
    public final a0 d;
    public final M e;
    public final boolean f = true;

    public c0(a0 a0Var, M m) {
        super(a0.c(a0Var), a0Var.f4291c);
        this.d = a0Var;
        this.e = m;
        fillInStackTrace();
    }

    public final synchronized Throwable fillInStackTrace() {
        Throwable th;
        if (this.f) {
            th = super.fillInStackTrace();
        } else {
            th = this;
        }
        return th;
    }
}
