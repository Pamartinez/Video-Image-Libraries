package Vf;

import Qe.B;
import cg.a;
import cg.f;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import me.x;

/* renamed from: Vf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0877n extends i0 {

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3869h;

    /* renamed from: i  reason: collision with root package name */
    public final C0875l f3870i;

    public /* synthetic */ C0877n(C0875l lVar, int i2) {
        this.f3869h = i2;
        this.f3870i = lVar;
    }

    public final boolean j() {
        switch (this.f3869h) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    public final void k(Throwable th) {
        switch (this.f3869h) {
            case 0:
                n0 i2 = i();
                C0875l lVar = this.f3870i;
                Throwable p6 = lVar.p(i2);
                if (lVar.v()) {
                    f fVar = (f) lVar.g;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f.k;
                    while (true) {
                        Object obj = atomicReferenceFieldUpdater.get(fVar);
                        B b = a.f4017c;
                        if (j.a(obj, b)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, b, p6)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != b) {
                                }
                            }
                            return;
                        } else if (!(obj instanceof Throwable)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, (Object) null)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                                }
                            }
                        } else {
                            return;
                        }
                    }
                }
                lVar.d(p6);
                if (!lVar.v()) {
                    lVar.n();
                    return;
                }
                return;
            default:
                this.f3870i.resumeWith(x.f4917a);
                return;
        }
    }
}
