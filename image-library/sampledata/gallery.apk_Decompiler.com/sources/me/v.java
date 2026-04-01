package me;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v implements Comparable {
    public final short d;

    public /* synthetic */ v(short s) {
        this.d = s;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return j.f(this.d & 65535, ((v) obj).d & 65535);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof v) && this.d == ((v) obj).d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Short.hashCode(this.d);
    }

    public final String toString() {
        return String.valueOf(this.d & 65535);
    }
}
