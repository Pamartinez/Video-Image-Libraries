package me;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements Comparable {
    public final byte d;

    public /* synthetic */ o(byte b) {
        this.d = b;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return j.f(this.d & 255, ((o) obj).d & 255);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof o) && this.d == ((o) obj).d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Byte.hashCode(this.d);
    }

    public final String toString() {
        return String.valueOf(this.d & 255);
    }
}
