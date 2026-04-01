package me;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements Comparable {
    public final long d;

    public /* synthetic */ s(long j2) {
        this.d = j2;
    }

    public static String a(long j2) {
        if (j2 >= 0) {
            String l = Long.toString(j2, 10);
            j.d(l, "toString(...)");
            return l;
        }
        long j3 = (long) 10;
        long j8 = ((j2 >>> 1) / j3) << 1;
        long j10 = j2 - (j8 * j3);
        if (j10 >= j3) {
            j10 -= j3;
            j8++;
        }
        String l8 = Long.toString(j8, 10);
        j.d(l8, "toString(...)");
        String l10 = Long.toString(j10, 10);
        j.d(l10, "toString(...)");
        return l8.concat(l10);
    }

    public final int compareTo(Object obj) {
        int i2 = ((this.d ^ Long.MIN_VALUE) > (((s) obj).d ^ Long.MIN_VALUE) ? 1 : ((this.d ^ Long.MIN_VALUE) == (((s) obj).d ^ Long.MIN_VALUE) ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        if (i2 == 0) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        if (this.d != ((s) obj).d) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.d);
    }

    public final String toString() {
        return a(this.d);
    }
}
