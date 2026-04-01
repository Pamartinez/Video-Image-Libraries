package io.grpc;

import ee.Z;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Deadline implements Comparable {
    public static final Z g = new Z(1);

    /* renamed from: h  reason: collision with root package name */
    public static final long f4617h;

    /* renamed from: i  reason: collision with root package name */
    public static final long f4618i;

    /* renamed from: j  reason: collision with root package name */
    public static final long f4619j = TimeUnit.SECONDS.toNanos(1);
    public final Z d;
    public final long e;
    public volatile boolean f;

    static {
        long nanos = TimeUnit.DAYS.toNanos(36500);
        f4617h = nanos;
        f4618i = -nanos;
    }

    public Deadline(long j2) {
        boolean z;
        Z z3 = g;
        long nanoTime = System.nanoTime();
        this.d = z3;
        long min = Math.min(f4617h, Math.max(f4618i, j2));
        this.e = nanoTime + min;
        if (min <= 0) {
            z = true;
        } else {
            z = false;
        }
        this.f = z;
    }

    public final boolean a() {
        if (!this.f) {
            long j2 = this.e;
            this.d.getClass();
            if (j2 - System.nanoTime() > 0) {
                return false;
            }
            this.f = true;
        }
        return true;
    }

    public final long b() {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.d.getClass();
        long nanoTime = System.nanoTime();
        if (!this.f && this.e - nanoTime <= 0) {
            this.f = true;
        }
        return timeUnit.convert(this.e - nanoTime, timeUnit);
    }

    public final int compareTo(Object obj) {
        Deadline deadline = (Deadline) obj;
        Z z = deadline.d;
        Z z3 = this.d;
        if (z3 == z) {
            int i2 = ((this.e - deadline.e) > 0 ? 1 : ((this.e - deadline.e) == 0 ? 0 : -1));
            if (i2 < 0) {
                return -1;
            }
            if (i2 > 0) {
                return 1;
            }
            return 0;
        }
        throw new AssertionError("Tickers (" + z3 + " and " + deadline.d + ") don't match. Custom Ticker should only be used in tests!");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        Z z = deadline.d;
        Z z3 = this.d;
        if (z3 != null ? z3 != z : z != null) {
            return false;
        }
        if (this.e != deadline.e) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.asList(new Object[]{this.d, Long.valueOf(this.e)}).hashCode();
    }

    public final String toString() {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        long b = b();
        long abs = Math.abs(b);
        long j2 = f4619j;
        long j3 = abs / j2;
        long abs2 = Math.abs(b) % j2;
        StringBuilder sb2 = new StringBuilder();
        if (b < 0) {
            sb2.append('-');
        }
        sb2.append(j3);
        if (abs2 > 0) {
            sb2.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(abs2)}));
        }
        sb2.append("s from now");
        Z z = g;
        Z z3 = this.d;
        if (z3 != z) {
            sb2.append(" (ticker=" + z3 + ")");
        }
        return sb2.toString();
    }
}
