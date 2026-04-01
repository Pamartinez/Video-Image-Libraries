package eg;

import A.a;
import N2.j;
import Qe.B;
import Vf.D;
import cg.k;
import cg.q;
import i.C0212a;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements Executor, Closeable {
    public static final /* synthetic */ AtomicLongFieldUpdater k;
    public static final /* synthetic */ AtomicLongFieldUpdater l;
    public static final /* synthetic */ AtomicIntegerFieldUpdater m;
    public static final B n = new B("NOT_IN_STACK", 2);
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;
    public final int d;
    public final int e;
    public final long f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final g f4327h;

    /* renamed from: i  reason: collision with root package name */
    public final g f4328i;

    /* renamed from: j  reason: collision with root package name */
    public final q f4329j;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    static {
        Class<d> cls = d.class;
        k = AtomicLongFieldUpdater.newUpdater(cls, "parkedWorkersStack$volatile");
        l = AtomicLongFieldUpdater.newUpdater(cls, "controlState$volatile");
        m = AtomicIntegerFieldUpdater.newUpdater(cls, "_isTerminated$volatile");
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [eg.g, cg.k] */
    /* JADX WARNING: type inference failed for: r4v4, types: [eg.g, cg.k] */
    public d(int i2, int i7, long j2, String str) {
        this.d = i2;
        this.e = i7;
        this.f = j2;
        this.g = str;
        if (i2 < 1) {
            throw new IllegalArgumentException(C0212a.j(i2, "Core pool size ", " should be at least 1").toString());
        } else if (i7 < i2) {
            throw new IllegalArgumentException(j.b(i7, i2, "Max pool size ", " should be greater than or equals to core pool size ").toString());
        } else if (i7 > 2097150) {
            throw new IllegalArgumentException(C0212a.j(i7, "Max pool size ", " should not exceed maximal supported number of threads 2097150").toString());
        } else if (j2 > 0) {
            this.f4327h = new k();
            this.f4328i = new k();
            this.f4329j = new q((i2 + 1) * 2);
            this.controlState$volatile = ((long) i2) << 42;
            this._isTerminated$volatile = 0;
        } else {
            throw new IllegalArgumentException(a.e(j2, "Idle worker keep alive time ", " must be positive").toString());
        }
    }

    public static /* synthetic */ void f(d dVar, Runnable runnable, int i2) {
        boolean z;
        if ((i2 & 4) != 0) {
            z = false;
        } else {
            z = true;
        }
        dVar.c(false, z, runnable);
    }

    public final int a() {
        boolean z;
        synchronized (this.f4329j) {
            try {
                if (m.get(this) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = l;
                long j2 = atomicLongFieldUpdater.get(this);
                int i2 = (int) (j2 & 2097151);
                int i7 = i2 - ((int) ((j2 & 4398044413952L) >> 21));
                if (i7 < 0) {
                    i7 = 0;
                }
                if (i7 >= this.d) {
                    return 0;
                }
                if (i2 >= this.e) {
                    return 0;
                }
                int i8 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i8 <= 0 || this.f4329j.b(i8) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                b bVar = new b(this, i8);
                this.f4329j.c(i8, bVar);
                if (i8 == ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    int i10 = i7 + 1;
                    bVar.start();
                    return i10;
                }
                throw new IllegalArgumentException("Failed requirement.");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c(boolean z, boolean z3, Runnable runnable) {
        j jVar;
        long j2;
        b bVar;
        boolean z7;
        c cVar;
        l.f.getClass();
        long nanoTime = System.nanoTime();
        if (runnable instanceof j) {
            jVar = (j) runnable;
            jVar.d = nanoTime;
            jVar.e = z;
        } else {
            jVar = new k(runnable, nanoTime, z);
        }
        boolean z9 = jVar.e;
        AtomicLongFieldUpdater atomicLongFieldUpdater = l;
        if (z9) {
            j2 = atomicLongFieldUpdater.addAndGet(this, 2097152);
        } else {
            j2 = 0;
        }
        Thread currentThread = Thread.currentThread();
        if (currentThread instanceof b) {
            bVar = (b) currentThread;
        } else {
            bVar = null;
        }
        if (bVar == null || !kotlin.jvm.internal.j.a(bVar.k, this)) {
            bVar = null;
        }
        boolean z10 = true;
        if (!(bVar == null || (cVar = bVar.f) == c.TERMINATED || (!jVar.e && cVar == c.BLOCKING))) {
            bVar.f4326j = true;
            n nVar = bVar.d;
            if (z3) {
                jVar = nVar.a(jVar);
            } else {
                nVar.getClass();
                j jVar2 = (j) n.b.getAndSet(nVar, jVar);
                if (jVar2 == null) {
                    jVar = null;
                } else {
                    jVar = nVar.a(jVar2);
                }
            }
        }
        if (jVar != null) {
            if (jVar.e) {
                z7 = this.f4328i.a(jVar);
            } else {
                z7 = this.f4327h.a(jVar);
            }
            if (!z7) {
                throw new RejectedExecutionException(C0212a.p(new StringBuilder(), this.g, " was terminated"));
            }
        }
        if (!z3 || bVar == null) {
            z10 = false;
        }
        if (z9) {
            if (!z10 && !i() && !h(j2)) {
                i();
            }
        } else if (!z10 && !i() && !h(atomicLongFieldUpdater.get(this))) {
            i();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r1 == null) goto L_0x008a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = m
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof eg.b
            r3 = 0
            if (r1 == 0) goto L_0x0017
            eg.b r0 = (eg.b) r0
            goto L_0x0018
        L_0x0017:
            r0 = r3
        L_0x0018:
            if (r0 == 0) goto L_0x0023
            eg.d r1 = r0.k
            boolean r1 = kotlin.jvm.internal.j.a(r1, r8)
            if (r1 == 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r0 = r3
        L_0x0024:
            cg.q r1 = r8.f4329j
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = l     // Catch:{ all -> 0x00c3 }
            long r4 = r4.get(r8)     // Catch:{ all -> 0x00c3 }
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L_0x0078
            r1 = r2
        L_0x0036:
            cg.q r5 = r8.f4329j
            java.lang.Object r5 = r5.b(r1)
            kotlin.jvm.internal.j.b(r5)
            eg.b r5 = (eg.b) r5
            if (r5 == r0) goto L_0x0073
        L_0x0043:
            java.lang.Thread$State r6 = r5.getState()
            java.lang.Thread$State r7 = java.lang.Thread.State.TERMINATED
            if (r6 == r7) goto L_0x0054
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L_0x0043
        L_0x0054:
            eg.n r5 = r5.d
            eg.g r6 = r8.f4328i
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = eg.n.b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            eg.j r7 = (eg.j) r7
            if (r7 == 0) goto L_0x0068
            r6.a(r7)
        L_0x0068:
            eg.j r7 = r5.b()
            if (r7 != 0) goto L_0x006f
            goto L_0x0073
        L_0x006f:
            r6.a(r7)
            goto L_0x0068
        L_0x0073:
            if (r1 == r4) goto L_0x0078
            int r1 = r1 + 1
            goto L_0x0036
        L_0x0078:
            eg.g r1 = r8.f4328i
            r1.b()
            eg.g r1 = r8.f4327h
            r1.b()
        L_0x0082:
            if (r0 == 0) goto L_0x008a
            eg.j r1 = r0.a(r2)
            if (r1 != 0) goto L_0x00b2
        L_0x008a:
            eg.g r1 = r8.f4327h
            java.lang.Object r1 = r1.d()
            eg.j r1 = (eg.j) r1
            if (r1 != 0) goto L_0x00b2
            eg.g r1 = r8.f4328i
            java.lang.Object r1 = r1.d()
            eg.j r1 = (eg.j) r1
            if (r1 != 0) goto L_0x00b2
            if (r0 == 0) goto L_0x00a5
            eg.c r1 = eg.c.TERMINATED
            r0.h(r1)
        L_0x00a5:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = k
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = l
            r0.set(r8, r1)
            return
        L_0x00b2:
            r1.run()     // Catch:{ all -> 0x00b6 }
            goto L_0x0082
        L_0x00b6:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L_0x0082
        L_0x00c3:
            r8 = move-exception
            monitor-exit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: eg.d.close():void");
    }

    public final void execute(Runnable runnable) {
        f(this, runnable, 6);
    }

    public final void g(b bVar, int i2, int i7) {
        while (true) {
            long j2 = k.get(this);
            int i8 = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & -2097152;
            if (i8 == i2) {
                if (i7 == 0) {
                    Object c5 = bVar.c();
                    while (true) {
                        if (c5 == n) {
                            i8 = -1;
                            break;
                        } else if (c5 == null) {
                            i8 = 0;
                            break;
                        } else {
                            b bVar2 = (b) c5;
                            int b = bVar2.b();
                            if (b != 0) {
                                i8 = b;
                                break;
                            }
                            c5 = bVar2.c();
                        }
                    }
                } else {
                    i8 = i7;
                }
            }
            if (i8 >= 0) {
                d dVar = this;
                if (!k.compareAndSet(dVar, j2, ((long) i8) | j3)) {
                    this = dVar;
                } else {
                    return;
                }
            }
        }
    }

    public final boolean h(long j2) {
        int i2 = ((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21));
        if (i2 < 0) {
            i2 = 0;
        }
        int i7 = this.d;
        if (i2 < i7) {
            int a7 = a();
            if (a7 == 1 && i7 > 1) {
                a();
            }
            if (a7 > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean i() {
        d dVar;
        B b;
        int i2;
        while (true) {
            long j2 = k.get(this);
            b bVar = (b) this.f4329j.b((int) (2097151 & j2));
            if (bVar == null) {
                bVar = null;
                dVar = this;
            } else {
                long j3 = (2097152 + j2) & -2097152;
                Object c5 = bVar.c();
                while (true) {
                    b = n;
                    if (c5 == b) {
                        i2 = -1;
                        break;
                    } else if (c5 == null) {
                        i2 = 0;
                        break;
                    } else {
                        b bVar2 = (b) c5;
                        i2 = bVar2.b();
                        if (i2 != 0) {
                            break;
                        }
                        long j8 = j2;
                        d dVar2 = this;
                        c5 = bVar2.c();
                        j2 = j8;
                    }
                }
                if (i2 >= 0) {
                    d dVar3 = this;
                    boolean compareAndSet = k.compareAndSet(dVar3, j2, ((long) i2) | j3);
                    dVar = dVar3;
                    if (compareAndSet) {
                        bVar.g(b);
                    }
                    this = dVar;
                } else {
                    continue;
                }
            }
            if (bVar == null) {
                return false;
            }
            if (b.l.compareAndSet(bVar, -1, 0)) {
                LockSupport.unpark(bVar);
                return true;
            }
            this = dVar;
        }
    }

    public final String toString() {
        int i2;
        ArrayList arrayList = new ArrayList();
        q qVar = this.f4329j;
        int a7 = qVar.a();
        int i7 = 0;
        int i8 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 1; i13 < a7; i13++) {
            b bVar = (b) qVar.b(i13);
            if (bVar != null) {
                n nVar = bVar.d;
                nVar.getClass();
                if (n.b.get(nVar) != null) {
                    i2 = (n.f4333c.get(nVar) - n.d.get(nVar)) + 1;
                } else {
                    i2 = n.f4333c.get(nVar) - n.d.get(nVar);
                }
                int i14 = a.f4323a[bVar.f.ordinal()];
                if (i14 == 1) {
                    i10++;
                } else if (i14 == 2) {
                    i8++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i2);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (i14 == 3) {
                    i7++;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(i2);
                    sb3.append('c');
                    arrayList.add(sb3.toString());
                } else if (i14 == 4) {
                    i11++;
                    if (i2 > 0) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(i2);
                        sb4.append('d');
                        arrayList.add(sb4.toString());
                    }
                } else if (i14 == 5) {
                    i12++;
                } else {
                    throw new RuntimeException();
                }
            }
        }
        long j2 = l.get(this);
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.g);
        sb5.append('@');
        sb5.append(D.j(this));
        sb5.append("[Pool Size {core = ");
        int i15 = this.d;
        sb5.append(i15);
        sb5.append(", max = ");
        j.x(sb5, this.e, "}, Worker States {CPU = ", i7, ", blocking = ");
        j.x(sb5, i8, ", parked = ", i10, ", dormant = ");
        j.x(sb5, i11, ", terminated = ", i12, "}, running workers queues = ");
        sb5.append(arrayList);
        sb5.append(", global CPU queue size = ");
        sb5.append(this.f4327h.c());
        sb5.append(", global blocking queue size = ");
        sb5.append(this.f4328i.c());
        sb5.append(", Control State {created workers= ");
        sb5.append((int) (2097151 & j2));
        sb5.append(", blocking tasks = ");
        sb5.append((int) ((4398044413952L & j2) >> 21));
        sb5.append(", CPUs acquired = ");
        sb5.append(i15 - ((int) ((j2 & 9223367638808264704L) >> 42)));
        sb5.append("}]");
        return sb5.toString();
    }
}
