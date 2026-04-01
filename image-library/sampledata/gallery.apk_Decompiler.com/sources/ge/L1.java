package ge;

import He.F;
import java.util.IdentityHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L1 {
    public static final L1 d = new L1(new Q0(12));

    /* renamed from: a  reason: collision with root package name */
    public final IdentityHashMap f4459a = new IdentityHashMap();
    public final Q0 b;

    /* renamed from: c  reason: collision with root package name */
    public ScheduledExecutorService f4460c;

    public L1(Q0 q0) {
        this.b = q0;
    }

    public static Object a(K1 k12) {
        Object obj;
        L1 l12 = d;
        synchronized (l12) {
            try {
                J1 j12 = (J1) l12.f4459a.get(k12);
                if (j12 == null) {
                    j12 = new J1(k12.create());
                    l12.f4459a.put(k12, j12);
                }
                ScheduledFuture scheduledFuture = j12.f4452c;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    j12.f4452c = null;
                }
                j12.b++;
                obj = j12.f4451a;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return obj;
    }

    public static void b(K1 k12, Object obj) {
        boolean z;
        boolean z3;
        L1 l12 = d;
        synchronized (l12) {
            try {
                J1 j12 = (J1) l12.f4459a.get(k12);
                if (j12 != null) {
                    boolean z7 = false;
                    if (obj == j12.f4451a) {
                        z = true;
                    } else {
                        z = false;
                    }
                    F.i("Releasing the wrong instance", z);
                    if (j12.b > 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    F.t(z3, "Refcount has already reached zero");
                    int i2 = j12.b - 1;
                    j12.b = i2;
                    if (i2 == 0) {
                        if (j12.f4452c == null) {
                            z7 = true;
                        }
                        F.t(z7, "Destroy task already scheduled");
                        if (l12.f4460c == null) {
                            l12.b.getClass();
                            l12.f4460c = Executors.newSingleThreadScheduledExecutor(Z.c("grpc-shared-destroyer-%d"));
                        }
                        j12.f4452c = l12.f4460c.schedule(new C1046n0(new J(l12, j12, k12, obj, 2)), 1, TimeUnit.SECONDS);
                    }
                } else {
                    throw new IllegalArgumentException("No cached instance found for " + k12);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
