package ge;

import B1.a;
import B2.A;
import E2.k;
import He.F;
import P1.e;
import S1.j;
import com.google.android.material.datepicker.g;
import ee.C0971d;
import ee.C0979l;
import ee.C0984q;
import ee.M;
import ee.a0;
import io.grpc.Deadline;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* renamed from: ge.D  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1003D extends C0984q {
    public static final C1000A m = new C1000A(0);
    public final ScheduledFuture d;
    public final Executor e;
    public final C0979l f;
    public volatile boolean g;

    /* renamed from: h  reason: collision with root package name */
    public C0971d f4387h;

    /* renamed from: i  reason: collision with root package name */
    public C0984q f4388i;

    /* renamed from: j  reason: collision with root package name */
    public a0 f4389j;
    public List k = new ArrayList();
    public C1002C l;

    static {
        Logger.getLogger(C1003D.class.getName());
    }

    public C1003D(Executor executor, ScheduledExecutorService scheduledExecutorService, Deadline deadline) {
        ScheduledFuture<?> scheduledFuture;
        F.n(executor, "callExecutor");
        this.e = executor;
        F.n(scheduledExecutorService, "scheduler");
        C0979l b = C0979l.b();
        this.f = b;
        b.getClass();
        if (deadline == null) {
            scheduledFuture = null;
        } else {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long b5 = deadline.b();
            long abs = Math.abs(b5);
            TimeUnit timeUnit2 = TimeUnit.SECONDS;
            long nanos = abs / timeUnit2.toNanos(1);
            long abs2 = Math.abs(b5) % timeUnit2.toNanos(1);
            StringBuilder sb2 = new StringBuilder();
            if (b5 < 0) {
                sb2.append("ClientCall started after CallOptions deadline was exceeded. Deadline has been exceeded for ");
            } else {
                sb2.append("Deadline CallOptions will be exceeded in ");
            }
            sb2.append(nanos);
            sb2.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(abs2)}));
            sb2.append("s. ");
            scheduledFuture = scheduledExecutorService.schedule(new e(8, this, sb2), b5, timeUnit);
        }
        this.d = scheduledFuture;
    }

    public final void b(String str, Throwable th) {
        a0 a0Var;
        a0 a0Var2 = a0.f;
        if (str != null) {
            a0Var = a0Var2.g(str);
        } else {
            a0Var = a0Var2.g("Call cancelled without message");
        }
        if (th != null) {
            a0Var = a0Var.f(th);
        }
        m(a0Var, false);
    }

    public final void d() {
        n(new A(3, (Object) this));
    }

    public final void h(int i2) {
        if (this.g) {
            this.f4388i.h(i2);
        } else {
            n(new g(this, i2, 1));
        }
    }

    public final void i(Object obj) {
        if (this.g) {
            this.f4388i.i(obj);
        } else {
            n(new e(10, this, obj));
        }
    }

    public final void l(C0971d dVar, M m4) {
        boolean z;
        a0 a0Var;
        boolean z3;
        if (this.f4387h == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "already started");
        synchronized (this) {
            try {
                this.f4387h = dVar;
                a0Var = this.f4389j;
                z3 = this.g;
                if (!z3) {
                    C1002C c5 = new C1002C(dVar);
                    this.l = c5;
                    dVar = c5;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (a0Var != null) {
            this.e.execute(new C1001B(this, dVar, a0Var));
        } else if (z3) {
            this.f4388i.l(dVar, m4);
        } else {
            n(new j(this, dVar, m4, 2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0029, code lost:
        if (r1 == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002b, code lost:
        n(new P1.e(9, r4, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        if (r6 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        r4.e.execute(new ge.C1001B(r4, r6, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        o();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        r4 = (ge.A0) r4;
        r4.r.d.m.execute(new B2.A(9, (java.lang.Object) r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0057, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m(ee.a0 r5, boolean r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            ee.q r0 = r4.f4388i     // Catch:{ all -> 0x0021 }
            r1 = 1
            if (r0 != 0) goto L_0x0023
            ge.A r6 = m     // Catch:{ all -> 0x0021 }
            r2 = 0
            if (r0 != 0) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r1 = r2
        L_0x000d:
            java.lang.String r3 = "realCall already set to %s"
            He.F.q(r3, r0, r1)     // Catch:{ all -> 0x0021 }
            java.util.concurrent.ScheduledFuture r0 = r4.d     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0019
            r0.cancel(r2)     // Catch:{ all -> 0x0021 }
        L_0x0019:
            r4.f4388i = r6     // Catch:{ all -> 0x0021 }
            ee.d r6 = r4.f4387h     // Catch:{ all -> 0x0021 }
            r4.f4389j = r5     // Catch:{ all -> 0x0021 }
            r1 = r2
            goto L_0x0028
        L_0x0021:
            r5 = move-exception
            goto L_0x0058
        L_0x0023:
            if (r6 == 0) goto L_0x0027
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return
        L_0x0027:
            r6 = 0
        L_0x0028:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0036
            P1.e r6 = new P1.e
            r0 = 9
            r6.<init>(r0, r4, r5)
            r4.n(r6)
            goto L_0x0045
        L_0x0036:
            if (r6 == 0) goto L_0x0042
            java.util.concurrent.Executor r0 = r4.e
            ge.B r1 = new ge.B
            r1.<init>((ge.C1003D) r4, (ee.C0971d) r6, (ee.a0) r5)
            r0.execute(r1)
        L_0x0042:
            r4.o()
        L_0x0045:
            ge.A0 r4 = (ge.A0) r4
            ge.B0 r5 = r4.r
            ge.F0 r5 = r5.d
            ee.e0 r5 = r5.m
            B2.A r6 = new B2.A
            r0 = 9
            r6.<init>((int) r0, (java.lang.Object) r4)
            r5.execute(r6)
            return
        L_0x0058:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1003D.m(ee.a0, boolean):void");
    }

    public final void n(Runnable runnable) {
        synchronized (this) {
            try {
                if (!this.g) {
                    this.k.add(runnable);
                } else {
                    runnable.run();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r0.hasNext() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r3.e.execute(new ge.C1045n(r3, r0));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void o() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0005:
            monitor-enter(r3)
            java.util.List r1 = r3.k     // Catch:{ all -> 0x0024 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x0026
            r0 = 0
            r3.k = r0     // Catch:{ all -> 0x0024 }
            r0 = 1
            r3.g = r0     // Catch:{ all -> 0x0024 }
            ge.C r0 = r3.l     // Catch:{ all -> 0x0024 }
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0023
            java.util.concurrent.Executor r1 = r3.e
            ge.n r2 = new ge.n
            r2.<init>((ge.C1003D) r3, (ge.C1002C) r0)
            r1.execute(r2)
        L_0x0023:
            return
        L_0x0024:
            r0 = move-exception
            goto L_0x0044
        L_0x0026:
            java.util.List r1 = r3.k     // Catch:{ all -> 0x0024 }
            r3.k = r0     // Catch:{ all -> 0x0024 }
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            java.util.Iterator r0 = r1.iterator()
        L_0x002f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003f
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x002f
        L_0x003f:
            r1.clear()
            r0 = r1
            goto L_0x0005
        L_0x0044:
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1003D.o():void");
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4388i, "realCall");
        return V.toString();
    }
}
