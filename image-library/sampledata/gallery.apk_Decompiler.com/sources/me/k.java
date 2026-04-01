package me;

import java.io.Serializable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements Serializable {
    public final Object d;

    public /* synthetic */ k(Object obj) {
        this.d = obj;
    }

    public static final Throwable a(Object obj) {
        if (obj instanceof j) {
            return ((j) obj).d;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (!j.a(this.d, ((k) obj).d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.d;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.d;
        if (obj instanceof j) {
            return ((j) obj).toString();
        }
        return "Success(" + obj + ')';
    }
}
