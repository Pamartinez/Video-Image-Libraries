package me;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements Serializable {
    public final Throwable d;

    public j(Throwable th) {
        kotlin.jvm.internal.j.e(th, "exception");
        this.d = th;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        if (kotlin.jvm.internal.j.a(this.d, ((j) obj).d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.d + ')';
    }
}
