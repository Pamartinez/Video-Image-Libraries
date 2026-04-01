package ge;

import He.F;
import P1.e;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import ee.C0973f;
import ee.C0981n;
import ee.a0;
import he.C1076a;
import io.grpc.Deadline;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class L implements r {
    public volatile boolean d;
    public C1056t e;
    public r f;
    public a0 g;

    /* renamed from: h  reason: collision with root package name */
    public List f4454h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    public K f4455i;

    /* renamed from: j  reason: collision with root package name */
    public long f4456j;
    public long k;
    public ArrayList l = new ArrayList();

    public final void a(Runnable runnable) {
        boolean z;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        synchronized (this) {
            try {
                if (!this.d) {
                    this.f4454h.add(runnable);
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

    public final void b(C0981n nVar) {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        F.n(nVar, "decompressorRegistry");
        this.l.add(new e(15, this, nVar));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        if (r2.f.isEmpty() == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r2.f = null;
        r2.e = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        r3 = r2.f;
        r2.f = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0034, code lost:
        r5 = r3.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        if (r5.hasNext() == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        ((java.lang.Runnable) r5.next()).run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        r3.clear();
        r5 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0057, code lost:
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005f, code lost:
        if (r0.hasNext() == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0061, code lost:
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r2 == null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r5 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        monitor-enter(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0005:
            monitor-enter(r5)
            java.util.List r1 = r5.f4454h     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x0052
            r0 = 0
            r5.f4454h = r0     // Catch:{ all -> 0x0050 }
            r1 = 1
            r5.d = r1     // Catch:{ all -> 0x0050 }
            ge.K r2 = r5.f4455i     // Catch:{ all -> 0x0050 }
            monitor-exit(r5)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x004f
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L_0x001e:
            monitor-enter(r2)
            java.util.List r3 = r2.f     // Catch:{ all -> 0x002d }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x002f
            r2.f = r0     // Catch:{ all -> 0x002d }
            r2.e = r1     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r5 = move-exception
            goto L_0x004d
        L_0x002f:
            java.util.List r3 = r2.f     // Catch:{ all -> 0x002d }
            r2.f = r5     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            java.util.Iterator r5 = r3.iterator()
        L_0x0038:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0048
            java.lang.Object r4 = r5.next()
            java.lang.Runnable r4 = (java.lang.Runnable) r4
            r4.run()
            goto L_0x0038
        L_0x0048:
            r3.clear()
            r5 = r3
            goto L_0x001e
        L_0x004d:
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r5
        L_0x004f:
            return
        L_0x0050:
            r0 = move-exception
            goto L_0x0070
        L_0x0052:
            java.util.List r1 = r5.f4454h     // Catch:{ all -> 0x0050 }
            r5.f4454h = r0     // Catch:{ all -> 0x0050 }
            monitor-exit(r5)     // Catch:{ all -> 0x0050 }
            java.util.Iterator r0 = r1.iterator()
        L_0x005b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x006b
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x005b
        L_0x006b:
            r1.clear()
            r0 = r1
            goto L_0x0005
        L_0x0070:
            monitor-exit(r5)     // Catch:{ all -> 0x0050 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.L.c():void");
    }

    public final void d(C1056t tVar) {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.l = null;
        this.f.k(tVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e(G0.c r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            ge.t r0 = r5.e     // Catch:{ all -> 0x0007 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r5)     // Catch:{ all -> 0x0007 }
            return
        L_0x0007:
            r6 = move-exception
            goto L_0x003c
        L_0x0009:
            ge.r r0 = r5.f     // Catch:{ all -> 0x0007 }
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "buffered_nanos"
            long r1 = r5.k     // Catch:{ all -> 0x0007 }
            long r3 = r5.f4456j     // Catch:{ all -> 0x0007 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0007 }
            r6.i(r1, r0)     // Catch:{ all -> 0x0007 }
            ge.r r0 = r5.f     // Catch:{ all -> 0x0007 }
            r0.e(r6)     // Catch:{ all -> 0x0007 }
            goto L_0x003a
        L_0x0021:
            java.lang.String r0 = "buffered_nanos"
            long r1 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0007 }
            long r3 = r5.f4456j     // Catch:{ all -> 0x0007 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0007 }
            r6.i(r1, r0)     // Catch:{ all -> 0x0007 }
            java.lang.String r0 = "waiting_for_connection"
            java.lang.Object r6 = r6.e     // Catch:{ all -> 0x0007 }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ all -> 0x0007 }
            r6.add(r0)     // Catch:{ all -> 0x0007 }
        L_0x003a:
            monitor-exit(r5)     // Catch:{ all -> 0x0007 }
            return
        L_0x003c:
            monitor-exit(r5)     // Catch:{ all -> 0x0007 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.L.e(G0.c):void");
    }

    public final void f(int i2) {
        boolean z;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        if (this.d) {
            this.f.f(i2);
        } else {
            a(new H(this, i2, 0));
        }
    }

    public final void flush() {
        boolean z;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        if (this.d) {
            this.f.flush();
        } else {
            a(new I(this, 2));
        }
    }

    public final void g(int i2) {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        this.l.add(new H(this, i2, 1));
    }

    public final void h(int i2) {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        this.l.add(new H(this, i2, 2));
    }

    public final boolean isReady() {
        if (this.d) {
            return this.f.isReady();
        }
        return false;
    }

    public final void j() {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        this.l.add(new I(this, 0));
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [ee.M, java.lang.Object] */
    public final void k(C1056t tVar) {
        boolean z;
        a0 a0Var;
        boolean z3;
        F.n(tVar, "listener");
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "already started");
        synchronized (this) {
            try {
                a0Var = this.g;
                z3 = this.d;
                if (!z3) {
                    K k10 = new K(tVar);
                    this.f4455i = k10;
                    tVar = k10;
                }
                this.e = tVar;
                this.f4456j = System.nanoTime();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (a0Var != null) {
            tVar.u0(a0Var, C1054s.PROCESSED, new Object());
        } else if (z3) {
            d(tVar);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        if (r6 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        d(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        return new ge.I(r5, 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final ge.I l(ge.r r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            ge.r r0 = r5.f     // Catch:{ all -> 0x0008 }
            r1 = 0
            if (r0 == 0) goto L_0x000a
            monitor-exit(r5)     // Catch:{ all -> 0x0008 }
            return r1
        L_0x0008:
            r6 = move-exception
            goto L_0x003a
        L_0x000a:
            java.lang.String r0 = "stream"
            He.F.n(r6, r0)     // Catch:{ all -> 0x0008 }
            ge.r r0 = r5.f     // Catch:{ all -> 0x0008 }
            r2 = 1
            if (r0 != 0) goto L_0x0016
            r3 = r2
            goto L_0x0017
        L_0x0016:
            r3 = 0
        L_0x0017:
            java.lang.String r4 = "realStream already set to %s"
            He.F.q(r4, r0, r3)     // Catch:{ all -> 0x0008 }
            r5.f = r6     // Catch:{ all -> 0x0008 }
            long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0008 }
            r5.k = r3     // Catch:{ all -> 0x0008 }
            ge.t r6 = r5.e     // Catch:{ all -> 0x0008 }
            if (r6 != 0) goto L_0x002c
            r5.f4454h = r1     // Catch:{ all -> 0x0008 }
            r5.d = r2     // Catch:{ all -> 0x0008 }
        L_0x002c:
            monitor-exit(r5)     // Catch:{ all -> 0x0008 }
            if (r6 != 0) goto L_0x0030
            return r1
        L_0x0030:
            r5.d(r6)
            ge.I r6 = new ge.I
            r0 = 1
            r6.<init>(r5, r0)
            return r6
        L_0x003a:
            monitor-exit(r5)     // Catch:{ all -> 0x0008 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.L.l(ge.r):ge.I");
    }

    public final void t() {
        boolean z;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        a(new I(this, 3));
    }

    public final void u(Deadline deadline) {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        this.l.add(new e(16, this, deadline));
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [ee.M, java.lang.Object] */
    public void v(a0 a0Var) {
        boolean z;
        boolean z3 = false;
        boolean z7 = true;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        F.n(a0Var, KeywordInfo.EXTRA_BUNDLE_KEY_REASON);
        synchronized (this) {
            try {
                r rVar = this.f;
                if (rVar == null) {
                    Q0 q0 = Q0.e;
                    if (rVar != null) {
                        z7 = false;
                    }
                    F.q("realStream already set to %s", rVar, z7);
                    this.f = q0;
                    this.k = System.nanoTime();
                    this.g = a0Var;
                } else {
                    z3 = true;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z3) {
            a(new e(18, this, a0Var));
            return;
        }
        c();
        i();
        this.e.u0(a0Var, C1054s.PROCESSED, new Object());
    }

    public final void w(C0973f fVar) {
        boolean z;
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called before start");
        this.l.add(new e(14, this, fVar));
    }

    public final void x(C1076a aVar) {
        boolean z;
        if (this.e != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "May only be called after start");
        if (this.d) {
            this.f.x(aVar);
        } else {
            a(new e(17, this, aVar));
        }
    }

    public void i() {
    }
}
