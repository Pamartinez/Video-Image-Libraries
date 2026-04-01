package fg;

import Ad.f;
import Qe.B;
import Vf.C0873j;
import Vf.C0874k;
import Vf.C0875l;
import c0.C0086a;
import cg.a;
import cg.s;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class h {

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4375c;
    public static final /* synthetic */ AtomicLongFieldUpdater d;
    public static final /* synthetic */ AtomicReferenceFieldUpdater e;
    public static final /* synthetic */ AtomicLongFieldUpdater f;
    public static final /* synthetic */ AtomicIntegerFieldUpdater g;
    private volatile /* synthetic */ int _availablePermits$volatile;

    /* renamed from: a  reason: collision with root package name */
    public final int f4376a;
    public final C0874k b;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private volatile /* synthetic */ Object tail$volatile;

    static {
        Class<h> cls = h.class;
        Class<Object> cls2 = Object.class;
        f4375c = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "head$volatile");
        d = AtomicLongFieldUpdater.newUpdater(cls, "deqIdx$volatile");
        e = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "tail$volatile");
        f = AtomicLongFieldUpdater.newUpdater(cls, "enqIdx$volatile");
        g = AtomicIntegerFieldUpdater.newUpdater(cls, "_availablePermits$volatile");
    }

    public h(int i2) {
        this.f4376a = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException(C0086a.i(i2, "Semaphore should have at least 1 permit, but had ").toString());
        } else if (i2 >= 0) {
            k kVar = new k(0, (k) null, 2);
            this.head$volatile = kVar;
            this.tail$volatile = kVar;
            this._availablePermits$volatile = i2;
            this.b = new C0874k(4, (Object) this);
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "The number of acquired permits should be in 0..").toString());
        }
    }

    public final void a(b bVar) {
        Object b5;
        k kVar;
        b bVar2 = bVar;
        C0875l lVar = bVar2.d;
        c cVar = bVar2.e;
        while (true) {
            int andDecrement = g.getAndDecrement(this);
            if (andDecrement <= this.f4376a) {
                x xVar = x.f4917a;
                if (andDecrement > 0) {
                    c.f4373h.set(cVar, (Object) null);
                    lVar.A(xVar, lVar.f, new C0874k(0, (Object) new f(cVar, bVar2)));
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
                k kVar2 = (k) atomicReferenceFieldUpdater.get(this);
                long andIncrement = f.getAndIncrement(this);
                f fVar = f.d;
                long j2 = andIncrement / ((long) j.f);
                while (true) {
                    b5 = a.b(kVar2, j2, fVar);
                    if (a.e(b5)) {
                        break;
                    }
                    s c5 = a.c(b5);
                    while (true) {
                        s sVar = (s) atomicReferenceFieldUpdater.get(this);
                        kVar = kVar2;
                        if (sVar.f4032c >= c5.f4032c) {
                            break;
                        } else if (!c5.j()) {
                            break;
                        } else {
                            while (!atomicReferenceFieldUpdater.compareAndSet(this, sVar, c5)) {
                                if (atomicReferenceFieldUpdater.get(this) != sVar) {
                                    if (c5.f()) {
                                        c5.e();
                                    }
                                    kVar2 = kVar;
                                }
                            }
                            if (sVar.f()) {
                                sVar.e();
                            }
                        }
                    }
                    kVar2 = kVar;
                }
                k kVar3 = (k) a.c(b5);
                AtomicReferenceArray atomicReferenceArray = kVar3.e;
                int i2 = (int) (andIncrement % ((long) j.f));
                while (!atomicReferenceArray.compareAndSet(i2, (Object) null, bVar2)) {
                    if (atomicReferenceArray.get(i2) != null) {
                        B b8 = j.b;
                        B b10 = j.f4378c;
                        while (!atomicReferenceArray.compareAndSet(i2, b8, b10)) {
                            if (atomicReferenceArray.get(i2) != b8) {
                            }
                        }
                        c.f4373h.set(cVar, (Object) null);
                        lVar.A(xVar, lVar.f, new C0874k(0, (Object) new f(cVar, bVar2)));
                        return;
                    }
                }
                bVar2.a(kVar3, i2);
                return;
            }
        }
    }

    public final void b() {
        int i2;
        Object b5;
        boolean z;
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i7 = this.f4376a;
            if (andIncrement >= i7) {
                do {
                    i2 = atomicIntegerFieldUpdater.get(this);
                    if (i2 <= i7 || atomicIntegerFieldUpdater.compareAndSet(this, i2, i7)) {
                    }
                    i2 = atomicIntegerFieldUpdater.get(this);
                    break;
                } while (atomicIntegerFieldUpdater.compareAndSet(this, i2, i7));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i7).toString());
            } else if (andIncrement < 0) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4375c;
                k kVar = (k) atomicReferenceFieldUpdater.get(this);
                long andIncrement2 = d.getAndIncrement(this);
                long j2 = andIncrement2 / ((long) j.f);
                g gVar = g.d;
                while (true) {
                    b5 = a.b(kVar, j2, gVar);
                    if (a.e(b5)) {
                        break;
                    }
                    s c5 = a.c(b5);
                    while (true) {
                        s sVar = (s) atomicReferenceFieldUpdater.get(this);
                        if (sVar.f4032c >= c5.f4032c) {
                            break;
                        } else if (c5.j()) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(this, sVar, c5)) {
                                if (atomicReferenceFieldUpdater.get(this) != sVar) {
                                    if (c5.f()) {
                                        c5.e();
                                    }
                                }
                            }
                            if (sVar.f()) {
                                sVar.e();
                            }
                        }
                    }
                }
                k kVar2 = (k) a.c(b5);
                AtomicReferenceArray atomicReferenceArray = kVar2.e;
                kVar2.b();
                int i8 = (kVar2.f4032c > j2 ? 1 : (kVar2.f4032c == j2 ? 0 : -1));
                z = false;
                if (i8 <= 0) {
                    int i10 = (int) (andIncrement2 % ((long) j.f));
                    Object andSet = atomicReferenceArray.getAndSet(i10, j.b);
                    if (andSet == null) {
                        int i11 = j.f4377a;
                        int i12 = 0;
                        while (true) {
                            if (i12 >= i11) {
                                B b8 = j.b;
                                B b10 = j.d;
                                while (true) {
                                    if (!atomicReferenceArray.compareAndSet(i10, b8, b10)) {
                                        if (atomicReferenceArray.get(i10) != b8) {
                                            break;
                                        }
                                    } else {
                                        z = true;
                                        break;
                                    }
                                }
                                z = !z;
                                continue;
                            } else if (atomicReferenceArray.get(i10) == j.f4378c) {
                                break;
                            } else {
                                i12++;
                            }
                        }
                    } else if (andSet == j.e) {
                        continue;
                    } else if (andSet instanceof C0873j) {
                        C0873j jVar = (C0873j) andSet;
                        B b11 = jVar.b(x.f4917a, this.b);
                        if (b11 != null) {
                            jVar.j(b11);
                        } else {
                            continue;
                        }
                    } else {
                        throw new IllegalStateException(("unexpected: " + andSet).toString());
                    }
                    z = true;
                    continue;
                }
            } else {
                return;
            }
        } while (!z);
    }
}
