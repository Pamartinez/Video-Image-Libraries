package E2;

import He.F;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public boolean f175a;
    public long b;

    public final void a() {
        F.t(!this.f175a, "This stopwatch is already running.");
        this.f175a = true;
        this.b = System.nanoTime();
    }

    public final String toString() {
        long j2;
        String str;
        if (this.f175a) {
            j2 = System.nanoTime() - this.b;
        } else {
            j2 = 0;
        }
        TimeUnit timeUnit = TimeUnit.DAYS;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (timeUnit.convert(j2, timeUnit2) <= 0) {
            timeUnit = TimeUnit.HOURS;
            if (timeUnit.convert(j2, timeUnit2) <= 0) {
                timeUnit = TimeUnit.MINUTES;
                if (timeUnit.convert(j2, timeUnit2) <= 0) {
                    timeUnit = TimeUnit.SECONDS;
                    if (timeUnit.convert(j2, timeUnit2) <= 0) {
                        timeUnit = TimeUnit.MILLISECONDS;
                        if (timeUnit.convert(j2, timeUnit2) <= 0) {
                            timeUnit = TimeUnit.MICROSECONDS;
                            if (timeUnit.convert(j2, timeUnit2) <= 0) {
                                timeUnit = timeUnit2;
                            }
                        }
                    }
                }
            }
        }
        double convert = ((double) j2) / ((double) timeUnit2.convert(1, timeUnit));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(convert)}));
        sb2.append(" ");
        switch (p.f174a[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = "s";
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = "h";
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        sb2.append(str);
        return sb2.toString();
    }
}
