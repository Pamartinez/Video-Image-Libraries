package Vf;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.j;

/* renamed from: Vf.f0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0869f0 extends CancellationException {
    public final transient n0 d;

    public C0869f0(String str, Throwable th, n0 n0Var) {
        super(str);
        this.d = n0Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0869f0)) {
            return false;
        }
        C0869f0 f0Var = (C0869f0) obj;
        if (!j.a(f0Var.getMessage(), getMessage()) || !j.a(f0Var.d, this.d) || !j.a(f0Var.getCause(), getCause())) {
            return false;
        }
        return true;
    }

    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        int i2;
        String message = getMessage();
        j.b(message);
        int hashCode = (this.d.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        if (cause != null) {
            i2 = cause.hashCode();
        } else {
            i2 = 0;
        }
        return hashCode + i2;
    }

    public final String toString() {
        return super.toString() + "; job=" + this.d;
    }
}
