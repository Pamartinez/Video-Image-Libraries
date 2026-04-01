package eg;

import cg.a;
import cg.u;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4331a;
    public static final long b = a.i("kotlinx.coroutines.scheduler.resolution.ns", 100000, 1, Long.MAX_VALUE);

    /* renamed from: c  reason: collision with root package name */
    public static final int f4332c;
    public static final int d = a.j(2097150, 4, "kotlinx.coroutines.scheduler.max.pool.size");
    public static final long e = TimeUnit.SECONDS.toNanos(a.i("kotlinx.coroutines.scheduler.keep.alive.sec", 60, 1, Long.MAX_VALUE));
    public static final h f = h.f4330a;

    static {
        String str;
        int i2 = u.f4034a;
        try {
            str = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str == null) {
            str = "DefaultDispatcher";
        }
        f4331a = str;
        int i7 = u.f4034a;
        if (i7 < 2) {
            i7 = 2;
        }
        f4332c = a.j(i7, 8, "kotlinx.coroutines.scheduler.core.pool.size");
    }
}
