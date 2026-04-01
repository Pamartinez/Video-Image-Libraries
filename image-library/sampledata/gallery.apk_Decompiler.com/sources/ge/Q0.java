package ge;

import G0.c;
import He.F;
import ee.C0973f;
import ee.C0981n;
import ee.H;
import ee.L;
import ee.a0;
import he.C1076a;
import io.grpc.Deadline;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Q0 implements r, K1, L, H {
    public static final Q0 e = new Q0(0);
    public static final Q0 f = new Q0(1);
    public final /* synthetic */ int d;

    public /* synthetic */ Q0(int i2) {
        this.d = i2;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [ge.S, java.lang.Object] */
    public static S i() {
        ? obj = new Object();
        obj.f4473a = new Random();
        long nanos = TimeUnit.SECONDS.toNanos(1);
        obj.b = TimeUnit.MINUTES.toNanos(2);
        obj.f4474c = 1.6d;
        obj.d = 0.2d;
        obj.e = nanos;
        return obj;
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m63a(Object obj) {
        return (byte[]) obj;
    }

    public Object c(String str) {
        boolean z;
        boolean z3;
        if (str.length() > 0) {
            z = true;
        } else {
            z = false;
        }
        F.i("empty timeout", z);
        if (str.length() <= 9) {
            z3 = true;
        } else {
            z3 = false;
        }
        F.i("bad timeout format", z3);
        long parseLong = Long.parseLong(str.substring(0, str.length() - 1));
        char charAt = str.charAt(str.length() - 1);
        if (charAt == 'H') {
            return Long.valueOf(TimeUnit.HOURS.toNanos(parseLong));
        }
        if (charAt == 'M') {
            return Long.valueOf(TimeUnit.MINUTES.toNanos(parseLong));
        }
        if (charAt == 'S') {
            return Long.valueOf(TimeUnit.SECONDS.toNanos(parseLong));
        }
        if (charAt == 'u') {
            return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(parseLong));
        }
        if (charAt == 'm') {
            return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(parseLong));
        }
        if (charAt == 'n') {
            return Long.valueOf(parseLong);
        }
        throw new IllegalArgumentException("Invalid timeout unit: " + charAt);
    }

    public Object create() {
        switch (this.d) {
            case 5:
                return Executors.newCachedThreadPool(Z.c("grpc-default-executor-%d"));
            default:
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, Z.c("grpc-timer-%d"));
                try {
                    newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE}).invoke(newScheduledThreadPool, new Object[]{Boolean.TRUE});
                } catch (NoSuchMethodException unused) {
                } catch (RuntimeException e7) {
                    throw e7;
                } catch (Exception e8) {
                    throw new RuntimeException(e8);
                }
                return Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool);
        }
    }

    public void d(Object obj) {
        switch (this.d) {
            case 5:
                ((ExecutorService) ((Executor) obj)).shutdown();
                return;
            default:
                ((ScheduledExecutorService) obj).shutdown();
                return;
        }
    }

    public void e(c cVar) {
        ((ArrayList) cVar.e).add("noop");
    }

    public boolean isReady() {
        return false;
    }

    public String toString() {
        switch (this.d) {
            case 5:
                return "grpc-default-executor";
            default:
                return super.toString();
        }
    }

    public String a(Object obj) {
        Long l = (Long) obj;
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        if (l.longValue() < 0) {
            throw new IllegalArgumentException("Timeout too small");
        } else if (l.longValue() < 100000000) {
            return l + "n";
        } else if (l.longValue() < 100000000000L) {
            return timeUnit.toMicros(l.longValue()) + "u";
        } else if (l.longValue() < 100000000000000L) {
            return timeUnit.toMillis(l.longValue()) + "m";
        } else if (l.longValue() < 100000000000000000L) {
            return timeUnit.toSeconds(l.longValue()) + "S";
        } else if (l.longValue() < 6000000000000000000L) {
            return timeUnit.toMinutes(l.longValue()) + "M";
        } else {
            return timeUnit.toHours(l.longValue()) + "H";
        }
    }

    public void flush() {
    }

    public void j() {
    }

    public void t() {
    }

    public void b(C0981n nVar) {
    }

    public void f(int i2) {
    }

    public void g(int i2) {
    }

    public void h(int i2) {
    }

    public void k(C1056t tVar) {
    }

    public void u(Deadline deadline) {
    }

    public void v(a0 a0Var) {
    }

    public void w(C0973f fVar) {
    }

    public void x(C1076a aVar) {
    }
}
