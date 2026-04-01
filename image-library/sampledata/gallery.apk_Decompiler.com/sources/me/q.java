package me;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements Comparable {
    public final int d;

    public /* synthetic */ q(int i2) {
        this.d = i2;
    }

    public final int compareTo(Object obj) {
        return j.f(this.d ^ Integer.MIN_VALUE, ((q) obj).d ^ Integer.MIN_VALUE);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof q) && this.d == ((q) obj).d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.d);
    }

    public final String toString() {
        return String.valueOf(((long) this.d) & 4294967295L);
    }
}
