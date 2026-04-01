package me;

import Ae.a;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements f, Serializable {
    public static final AtomicReferenceFieldUpdater f = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "e");
    public volatile a d;
    public volatile Object e;

    public final Object getValue() {
        Object obj = this.e;
        u uVar = u.f4916a;
        if (obj != uVar) {
            return obj;
        }
        a aVar = this.d;
        if (aVar != null) {
            Object invoke = aVar.invoke();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, uVar, invoke)) {
                if (atomicReferenceFieldUpdater.get(this) != uVar) {
                }
            }
            this.d = null;
            return invoke;
        }
        return this.e;
    }

    public final boolean isInitialized() {
        if (this.e != u.f4916a) {
            return true;
        }
        return false;
    }

    public final String toString() {
        if (isInitialized()) {
            return String.valueOf(getValue());
        }
        return "Lazy value not initialized yet.";
    }
}
