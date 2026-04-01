package eg;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {
    public static final /* synthetic */ AtomicReferenceFieldUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f4333c;
    public static final /* synthetic */ AtomicIntegerFieldUpdater d;
    public static final /* synthetic */ AtomicIntegerFieldUpdater e;

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReferenceArray f4334a = new AtomicReferenceArray(128);
    private volatile /* synthetic */ int blockingTasksInBuffer$volatile;
    private volatile /* synthetic */ int consumerIndex$volatile;
    private volatile /* synthetic */ Object lastScheduledTask$volatile;
    private volatile /* synthetic */ int producerIndex$volatile;

    static {
        Class<n> cls = n.class;
        b = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "lastScheduledTask$volatile");
        f4333c = AtomicIntegerFieldUpdater.newUpdater(cls, "producerIndex$volatile");
        d = AtomicIntegerFieldUpdater.newUpdater(cls, "consumerIndex$volatile");
        e = AtomicIntegerFieldUpdater.newUpdater(cls, "blockingTasksInBuffer$volatile");
    }

    public final j a(j jVar) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4333c;
        if (atomicIntegerFieldUpdater.get(this) - d.get(this) == 127) {
            return jVar;
        }
        if (jVar.e) {
            e.incrementAndGet(this);
        }
        int i2 = atomicIntegerFieldUpdater.get(this) & 127;
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.f4334a;
            if (atomicReferenceArray.get(i2) != null) {
                Thread.yield();
            } else {
                atomicReferenceArray.lazySet(i2, jVar);
                atomicIntegerFieldUpdater.incrementAndGet(this);
                return null;
            }
        }
    }

    public final j b() {
        j jVar;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = d;
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 - f4333c.get(this) == 0) {
                return null;
            }
            int i7 = i2 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 + 1) && (jVar = (j) this.f4334a.getAndSet(i7, (Object) null)) != null) {
                if (jVar.e) {
                    e.decrementAndGet(this);
                }
                return jVar;
            }
        }
    }

    public final j c(int i2, boolean z) {
        int i7 = i2 & 127;
        AtomicReferenceArray atomicReferenceArray = this.f4334a;
        j jVar = (j) atomicReferenceArray.get(i7);
        if (jVar == null || jVar.e != z) {
            return null;
        }
        while (!atomicReferenceArray.compareAndSet(i7, jVar, (Object) null)) {
            if (atomicReferenceArray.get(i7) != jVar) {
                return null;
            }
        }
        if (z) {
            e.decrementAndGet(this);
        }
        return jVar;
    }
}
