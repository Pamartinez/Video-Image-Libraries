package cg;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4018a;
    public static final /* synthetic */ AtomicReferenceFieldUpdater b;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;

    static {
        Class<b> cls = b.class;
        Class<Object> cls2 = Object.class;
        f4018a = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_next$volatile");
        b = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_prev$volatile");
    }

    public b(s sVar) {
        this._prev$volatile = sVar;
    }

    public final void b() {
        b.set(this, (Object) null);
    }

    public final b c() {
        Object obj = f4018a.get(this);
        if (obj == a.f4016a) {
            return null;
        }
        return (b) obj;
    }

    public abstract boolean d();

    public final void e() {
        b bVar;
        b c5;
        if (c() != null) {
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
                b bVar2 = (b) atomicReferenceFieldUpdater.get(this);
                while (bVar2 != null && bVar2.d()) {
                    bVar2 = (b) atomicReferenceFieldUpdater.get(bVar2);
                }
                b c6 = c();
                j.b(c6);
                while (c6.d() && (c5 = c6.c()) != null) {
                    c6 = c5;
                }
                while (true) {
                    Object obj = atomicReferenceFieldUpdater.get(c6);
                    if (((b) obj) == null) {
                        bVar = null;
                    } else {
                        bVar = bVar2;
                    }
                    while (true) {
                        if (atomicReferenceFieldUpdater.compareAndSet(c6, obj, bVar)) {
                            break;
                        } else if (atomicReferenceFieldUpdater.get(c6) != obj) {
                        }
                    }
                }
                if (bVar2 != null) {
                    f4018a.set(bVar2, c6);
                }
                if ((!c6.d() || c6.c() == null) && (bVar2 == null || !bVar2.d())) {
                    return;
                }
            }
        }
    }
}
