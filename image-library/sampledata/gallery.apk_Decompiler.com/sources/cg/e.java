package cg;

import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends RuntimeException {
    public final transient C1232h d;

    public e(C1232h hVar) {
        this.d = hVar;
    }

    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final String getLocalizedMessage() {
        return this.d.toString();
    }
}
