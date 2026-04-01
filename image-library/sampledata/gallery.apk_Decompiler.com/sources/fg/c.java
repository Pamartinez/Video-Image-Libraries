package fg;

import L1.d;
import Qe.B;
import Vf.C0875l;
import Vf.D;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import me.x;
import re.C1245a;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends h implements a {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4373h = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "owner$volatile");
    private volatile /* synthetic */ Object owner$volatile = d.f4374a;

    public c() {
        super(1);
    }

    public final Object c(C1271c cVar) {
        int i2;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = h.g;
            int i7 = atomicIntegerFieldUpdater.get(this);
            int i8 = this.f4376a;
            if (i7 > i8) {
                do {
                    i2 = atomicIntegerFieldUpdater.get(this);
                    if (i2 <= i8) {
                        break;
                    }
                } while (atomicIntegerFieldUpdater.compareAndSet(this, i2, i8));
            } else {
                x xVar = x.f4917a;
                if (i7 <= 0) {
                    C0875l k = D.k(d.m(cVar));
                    try {
                        a(new b(this, k));
                        Object q = k.q();
                        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                        if (q != aVar) {
                            q = xVar;
                        }
                        if (q == aVar) {
                            return q;
                        }
                        return xVar;
                    } catch (Throwable th) {
                        k.y();
                        throw th;
                    }
                } else if (atomicIntegerFieldUpdater.compareAndSet(this, i7, i7 - 1)) {
                    f4373h.set(this, (Object) null);
                    return xVar;
                }
            }
        }
    }

    public final void d(Object obj) {
        while (Math.max(h.g.get(this), 0) == 0) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4373h;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            B b = d.f4374a;
            if (obj2 != b) {
                if (obj2 == obj || obj == null) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, b)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        }
                    }
                    b();
                    return;
                }
                throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Mutex@");
        sb2.append(D.j(this));
        sb2.append("[isLocked=");
        boolean z = false;
        if (Math.max(h.g.get(this), 0) == 0) {
            z = true;
        }
        sb2.append(z);
        sb2.append(",owner=");
        sb2.append(f4373h.get(this));
        sb2.append(']');
        return sb2.toString();
    }
}
