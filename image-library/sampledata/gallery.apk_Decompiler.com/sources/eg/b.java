package eg;

import Qe.B;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends Thread {
    public static final /* synthetic */ AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(b.class, "workerCtl$volatile");
    public final n d = new n();
    public final u e = new Object();
    public c f = c.DORMANT;
    public long g;

    /* renamed from: h  reason: collision with root package name */
    public long f4324h;

    /* renamed from: i  reason: collision with root package name */
    public int f4325i;
    private volatile int indexInArray;

    /* renamed from: j  reason: collision with root package name */
    public boolean f4326j;
    public final /* synthetic */ d k;
    private volatile Object nextParkedWorker = d.n;
    private volatile /* synthetic */ int workerCtl$volatile;

    /* JADX WARNING: type inference failed for: r3v5, types: [kotlin.jvm.internal.u, java.lang.Object] */
    public b(d dVar, int i2) {
        this.k = dVar;
        setDaemon(true);
        setContextClassLoader(d.class.getClassLoader());
        int nanoTime = (int) System.nanoTime();
        this.f4325i = nanoTime == 0 ? 42 : nanoTime;
        f(i2);
    }

    public final j a(boolean z) {
        j e7;
        j e8;
        long j2;
        c cVar = this.f;
        c cVar2 = c.CPU_ACQUIRED;
        d dVar = this.k;
        j jVar = null;
        boolean z3 = true;
        n nVar = this.d;
        if (cVar != cVar2) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = d.l;
            do {
                j2 = atomicLongFieldUpdater.get(dVar);
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    nVar.getClass();
                    loop1:
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n.b;
                        j jVar2 = (j) atomicReferenceFieldUpdater.get(nVar);
                        if (jVar2 != null && jVar2.e) {
                            while (true) {
                                if (atomicReferenceFieldUpdater.compareAndSet(nVar, jVar2, (Object) null)) {
                                    jVar = jVar2;
                                    break loop1;
                                } else if (atomicReferenceFieldUpdater.get(nVar) != jVar2) {
                                }
                            }
                        }
                    }
                    int i2 = n.d.get(nVar);
                    int i7 = n.f4333c.get(nVar);
                    while (true) {
                        if (i2 != i7 && n.e.get(nVar) != 0) {
                            i7--;
                            j c5 = nVar.c(i7, true);
                            if (c5 != null) {
                                jVar = c5;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (jVar != null) {
                        return jVar;
                    }
                    j jVar3 = (j) dVar.f4328i.d();
                    if (jVar3 == null) {
                        return i(1);
                    }
                    return jVar3;
                }
            } while (!d.l.compareAndSet(dVar, j2, j2 - 4398046511104L));
            this.f = c.CPU_ACQUIRED;
        }
        if (z) {
            if (d(dVar.d * 2) != 0) {
                z3 = false;
            }
            if (z3 && (e8 = e()) != null) {
                return e8;
            }
            nVar.getClass();
            j jVar4 = (j) n.b.getAndSet(nVar, (Object) null);
            if (jVar4 == null) {
                jVar4 = nVar.b();
            }
            if (jVar4 != null) {
                return jVar4;
            }
            if (!z3 && (e7 = e()) != null) {
                return e7;
            }
        } else {
            j e9 = e();
            if (e9 != null) {
                return e9;
            }
        }
        return i(3);
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i2) {
        int i7 = this.f4325i;
        int i8 = i7 ^ (i7 << 13);
        int i10 = i8 ^ (i8 >> 17);
        int i11 = i10 ^ (i10 << 5);
        this.f4325i = i11;
        int i12 = i2 - 1;
        if ((i12 & i2) == 0) {
            return i12 & i11;
        }
        return (Integer.MAX_VALUE & i11) % i2;
    }

    public final j e() {
        int d2 = d(2);
        d dVar = this.k;
        if (d2 == 0) {
            j jVar = (j) dVar.f4327h.d();
            if (jVar != null) {
                return jVar;
            }
            return (j) dVar.f4328i.d();
        }
        j jVar2 = (j) dVar.f4328i.d();
        if (jVar2 != null) {
            return jVar2;
        }
        return (j) dVar.f4327h.d();
    }

    public final void f(int i2) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.k.g);
        sb2.append("-worker-");
        if (i2 == 0) {
            str = "TERMINATED";
        } else {
            str = String.valueOf(i2);
        }
        sb2.append(str);
        setName(sb2.toString());
        this.indexInArray = i2;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(c cVar) {
        boolean z;
        c cVar2 = this.f;
        if (cVar2 == c.CPU_ACQUIRED) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            d.l.addAndGet(this.k, 4398046511104L);
        }
        if (cVar2 != cVar) {
            this.f = cVar;
        }
        return z;
    }

    public final j i(int i2) {
        long j2;
        j jVar;
        long j3;
        long j8;
        j jVar2;
        int i7;
        boolean z;
        int i8 = i2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = d.l;
        d dVar = this.k;
        int i10 = (int) (atomicLongFieldUpdater.get(dVar) & 2097151);
        j jVar3 = null;
        if (i10 < 2) {
            return null;
        }
        int d2 = d(i10);
        int i11 = 0;
        long j10 = Long.MAX_VALUE;
        while (i11 < i10) {
            d2++;
            if (d2 > i10) {
                d2 = 1;
            }
            b bVar = (b) dVar.f4329j.b(d2);
            if (bVar != null && bVar != this) {
                n nVar = bVar.d;
                if (i8 == 3) {
                    jVar = nVar.b();
                    j2 = 0;
                } else {
                    nVar.getClass();
                    int i12 = n.d.get(nVar);
                    int i13 = n.f4333c.get(nVar);
                    if (i8 == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    while (true) {
                        if (i12 == i13) {
                            j2 = 0;
                            break;
                        }
                        j2 = 0;
                        if (z && n.e.get(nVar) == 0) {
                            break;
                        }
                        int i14 = i12 + 1;
                        jVar = nVar.c(i12, z);
                        if (jVar != null) {
                            break;
                        }
                        i12 = i14;
                    }
                    jVar = jVar3;
                }
                u uVar = this.e;
                if (jVar != null) {
                    uVar.d = jVar;
                    jVar2 = jVar3;
                    j8 = -1;
                    j3 = -1;
                } else {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n.b;
                        j jVar4 = (j) atomicReferenceFieldUpdater.get(nVar);
                        if (jVar4 == null) {
                            j3 = -1;
                            break;
                        }
                        j3 = -1;
                        if (jVar4.e) {
                            i7 = 1;
                        } else {
                            i7 = 2;
                        }
                        if ((i7 & i8) == 0) {
                            break;
                        }
                        l.f.getClass();
                        n nVar2 = nVar;
                        long nanoTime = System.nanoTime() - jVar4.d;
                        long j11 = l.b;
                        if (nanoTime < j11) {
                            j8 = j11 - nanoTime;
                            jVar2 = null;
                            break;
                        }
                        n nVar3 = nVar2;
                        do {
                            jVar2 = null;
                            if (atomicReferenceFieldUpdater.compareAndSet(nVar3, jVar4, (Object) null)) {
                                uVar.d = jVar4;
                                j8 = -1;
                                break;
                            }
                        } while (atomicReferenceFieldUpdater.get(nVar3) == jVar4);
                        nVar = nVar3;
                        jVar3 = null;
                    }
                    j8 = -2;
                    jVar2 = jVar3;
                }
                if (j8 == j3) {
                    j jVar5 = (j) uVar.d;
                    uVar.d = jVar2;
                    return jVar5;
                } else if (j8 > j2) {
                    j10 = Math.min(j10, j8);
                }
            }
            i11++;
            jVar3 = null;
        }
        if (j10 == Long.MAX_VALUE) {
            j10 = 0;
        }
        this.f4324h = j10;
        return null;
    }

    public final void run() {
        c cVar;
        c cVar2;
        long j2;
        boolean z;
        loop0:
        while (true) {
            boolean z3 = false;
            while (true) {
                if (d.m.get(this.k) == 0 && this.f != (cVar = c.TERMINATED)) {
                    j a7 = a(this.f4326j);
                    if (a7 != null) {
                        this.f4324h = 0;
                        d dVar = this.k;
                        this.g = 0;
                        if (this.f == c.PARKING) {
                            this.f = c.BLOCKING;
                        }
                        if (a7.e) {
                            if (h(c.BLOCKING) && !dVar.i() && !dVar.h(d.l.get(dVar))) {
                                dVar.i();
                            }
                            try {
                                a7.run();
                            } catch (Throwable th) {
                                Thread currentThread = Thread.currentThread();
                                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
                            }
                            d.l.addAndGet(dVar, -2097152);
                            if (this.f != cVar) {
                                this.f = c.DORMANT;
                            }
                        } else {
                            try {
                                a7.run();
                            } catch (Throwable th2) {
                                Thread currentThread2 = Thread.currentThread();
                                currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th2);
                            }
                        }
                    } else {
                        this.f4326j = false;
                        if (this.f4324h != 0) {
                            if (z3) {
                                h(c.PARKING);
                                Thread.interrupted();
                                LockSupport.parkNanos(this.f4324h);
                                this.f4324h = 0;
                                break;
                            }
                            z3 = true;
                        } else {
                            Object obj = this.nextParkedWorker;
                            B b = d.n;
                            if (obj != b) {
                                l.set(this, -1);
                                while (this.nextParkedWorker != d.n) {
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = l;
                                    if (atomicIntegerFieldUpdater.get(this) != -1) {
                                        break;
                                    }
                                    d dVar2 = this.k;
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = d.m;
                                    if (atomicIntegerFieldUpdater2.get(dVar2) != 0 || this.f == (cVar2 = c.TERMINATED)) {
                                        break;
                                    }
                                    h(c.PARKING);
                                    Thread.interrupted();
                                    if (this.g == 0) {
                                        j2 = 2097151;
                                        this.g = System.nanoTime() + this.k.f;
                                    } else {
                                        j2 = 2097151;
                                    }
                                    LockSupport.parkNanos(this.k.f);
                                    if (System.nanoTime() - this.g >= 0) {
                                        this.g = 0;
                                        d dVar3 = this.k;
                                        synchronized (dVar3.f4329j) {
                                            try {
                                                if (atomicIntegerFieldUpdater2.get(dVar3) != 0) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                if (!z) {
                                                    AtomicLongFieldUpdater atomicLongFieldUpdater = d.l;
                                                    if (((int) (atomicLongFieldUpdater.get(dVar3) & j2)) > dVar3.d) {
                                                        if (atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                            int i2 = this.indexInArray;
                                                            f(0);
                                                            dVar3.g(this, i2, 0);
                                                            int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(dVar3) & j2);
                                                            if (andDecrement != i2) {
                                                                Object b5 = dVar3.f4329j.b(andDecrement);
                                                                j.b(b5);
                                                                b bVar = (b) b5;
                                                                dVar3.f4329j.c(i2, bVar);
                                                                bVar.f(i2);
                                                                dVar3.g(bVar, andDecrement, i2);
                                                            }
                                                            dVar3.f4329j.c(andDecrement, (b) null);
                                                            this.f = cVar2;
                                                        }
                                                    }
                                                }
                                            } catch (Throwable th3) {
                                                throw th3;
                                            }
                                        }
                                    }
                                }
                            } else {
                                d dVar4 = this.k;
                                if (this.nextParkedWorker == b) {
                                    AtomicLongFieldUpdater atomicLongFieldUpdater2 = d.k;
                                    while (true) {
                                        long j3 = atomicLongFieldUpdater2.get(dVar4);
                                        int i7 = this.indexInArray;
                                        this.nextParkedWorker = dVar4.f4329j.b((int) (j3 & 2097151));
                                        d dVar5 = dVar4;
                                        if (d.k.compareAndSet(dVar5, j3, ((j3 + 2097152) & -2097152) | ((long) i7))) {
                                            break;
                                        }
                                        dVar4 = dVar5;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        h(c.TERMINATED);
    }
}
