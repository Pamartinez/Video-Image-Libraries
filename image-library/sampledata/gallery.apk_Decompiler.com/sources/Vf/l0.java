package Vf;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l0 implements C0861b0 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater e;
    public static final /* synthetic */ AtomicReferenceFieldUpdater f;
    public static final /* synthetic */ AtomicReferenceFieldUpdater g;
    private volatile /* synthetic */ Object _exceptionsHolder$volatile;
    private volatile /* synthetic */ int _isCompleting$volatile = 0;
    private volatile /* synthetic */ Object _rootCause$volatile;
    public final q0 d;

    static {
        Class<l0> cls = l0.class;
        e = AtomicIntegerFieldUpdater.newUpdater(cls, "_isCompleting$volatile");
        Class<Object> cls2 = Object.class;
        f = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_rootCause$volatile");
        g = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_exceptionsHolder$volatile");
    }

    public l0(q0 q0Var, Throwable th) {
        this.d = q0Var;
        this._rootCause$volatile = th;
    }

    public final void a(Throwable th) {
        Throwable b = b();
        if (b == null) {
            f.set(this, th);
        } else if (th != b) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                atomicReferenceFieldUpdater.set(this, th);
            } else if (obj instanceof Throwable) {
                if (th != obj) {
                    ArrayList arrayList = new ArrayList(4);
                    arrayList.add(obj);
                    arrayList.add(th);
                    atomicReferenceFieldUpdater.set(this, arrayList);
                }
            } else if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(th);
            } else {
                throw new IllegalStateException(("State is " + obj).toString());
            }
        }
    }

    public final Throwable b() {
        return (Throwable) f.get(this);
    }

    public final q0 c() {
        return this.d;
    }

    public final boolean d() {
        if (b() != null) {
            return true;
        }
        return false;
    }

    public final ArrayList e(Throwable th) {
        ArrayList arrayList;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            arrayList = new ArrayList(4);
        } else if (obj instanceof Throwable) {
            ArrayList arrayList2 = new ArrayList(4);
            arrayList2.add(obj);
            arrayList = arrayList2;
        } else if (obj instanceof ArrayList) {
            arrayList = (ArrayList) obj;
        } else {
            throw new IllegalStateException(("State is " + obj).toString());
        }
        Throwable b = b();
        if (b != null) {
            arrayList.add(0, b);
        }
        if (th != null && !th.equals(b)) {
            arrayList.add(th);
        }
        atomicReferenceFieldUpdater.set(this, D.f3838h);
        return arrayList;
    }

    public final boolean isActive() {
        if (b() == null) {
            return true;
        }
        return false;
    }

    public final String toString() {
        boolean z;
        StringBuilder sb2 = new StringBuilder("Finishing[cancelling=");
        sb2.append(d());
        sb2.append(", completing=");
        if (e.get(this) != 0) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(", rootCause=");
        sb2.append(b());
        sb2.append(", exceptions=");
        sb2.append(g.get(this));
        sb2.append(", list=");
        sb2.append(this.d);
        sb2.append(']');
        return sb2.toString();
    }
}
