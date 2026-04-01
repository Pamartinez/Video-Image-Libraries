package ee;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b0 extends Exception {
    public final a0 d;
    public final boolean e = true;

    public b0(a0 a0Var) {
        super(a0.c(a0Var), a0Var.f4291c);
        this.d = a0Var;
        fillInStackTrace();
    }

    public final synchronized Throwable fillInStackTrace() {
        Throwable th;
        if (this.e) {
            th = super.fillInStackTrace();
        } else {
            th = this;
        }
        return th;
    }
}
