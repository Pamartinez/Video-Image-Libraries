package Vf;

import cg.j;
import cg.p;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i0 extends j implements O, C0861b0 {
    public n0 g;

    public final void a() {
        n0 i2 = i();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n0.d;
            Object obj = atomicReferenceFieldUpdater.get(i2);
            if (obj instanceof i0) {
                if (obj == this) {
                    Q q = D.f3840j;
                    while (true) {
                        if (!atomicReferenceFieldUpdater.compareAndSet(i2, obj, q)) {
                            if (atomicReferenceFieldUpdater.get(i2) != obj) {
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else if ((obj instanceof C0861b0) && ((C0861b0) obj).c() != null) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = j.d;
                    Object obj2 = atomicReferenceFieldUpdater2.get(this);
                    if (!(obj2 instanceof p)) {
                        if (obj2 == this) {
                            j jVar = (j) obj2;
                            return;
                        }
                        kotlin.jvm.internal.j.c(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
                        j jVar2 = (j) obj2;
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = j.f;
                        p pVar = (p) atomicReferenceFieldUpdater3.get(jVar2);
                        if (pVar == null) {
                            pVar = new p(jVar2);
                            atomicReferenceFieldUpdater3.set(jVar2, pVar);
                        }
                        while (true) {
                            if (atomicReferenceFieldUpdater2.compareAndSet(this, obj2, pVar)) {
                                jVar2.e();
                                return;
                            } else if (atomicReferenceFieldUpdater2.get(this) != obj2) {
                            }
                        }
                    } else {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

    public final q0 c() {
        return null;
    }

    public C0867e0 getParent() {
        return i();
    }

    public final n0 i() {
        n0 n0Var = this.g;
        if (n0Var != null) {
            return n0Var;
        }
        kotlin.jvm.internal.j.k("job");
        throw null;
    }

    public final boolean isActive() {
        return true;
    }

    public abstract boolean j();

    public abstract void k(Throwable th);

    public final String toString() {
        return getClass().getSimpleName() + '@' + D.j(this) + "[job@" + D.j(i()) + ']';
    }
}
